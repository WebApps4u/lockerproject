package com.lok.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.json.JSONObject;

import com.lok.config.ConfigurationJdbc;
import static com.lok.config.ConfigurationJdbc.connectionPool;

/**
 * This is a seperate application, that will be run by windows task manager It
 * will generate email by picking records from emailoutbound table Based on the
 * 'table' value, it will fetch the corresponding template from emailtemplate
 * table. Get the record for pk-table combination.
 * 
 * Using template and record, create email, then using SMTP server, sends an
 * email.
 * 
 * @author USER
 *
 */
public class EmailService extends Thread {

	// blocking queue for pushing data to produce emails
	private static BlockingQueue<EmailOutbound> emailQueue = new ArrayBlockingQueue<>(
			50);

	private static Map<String, String> queryTable = new HashMap<String, String>();
	private static Map<String, String> templateIds = new HashMap<String, String>();

	private static final String queryBill = "SELECT * FROM BILLRECORD t1, PARTYRECORD t2 WHERE t1.BNO=? and t2.KNO = t1.KNO ";
	private static final String queryTemplate = "SELECT * FROM EMAILTEMPLATE WHERE EMAILTEMPLATEID=?";

	private static final String insertEmailSent = "INSERT INTO EMAILSENT (OBJECTTYPE,OBJECTID,TEMPLATEID,TOLIST,FROMEMAIL,CCLIST,BODY,CREATEDBY) VALUES (?,?,?,?,?,?,?,?)";
	private static final String deleteEmailOutbound = "DELETE FROM EMAILOUTBOUND WHERE OBJECTID=? AND OBJECTTYPE=?";

	public EmailService() {
		queryTable.put("billrecord", queryBill);
		queryTable.put("emailtemplate", queryTemplate);

		// set the template IDs
		// A cache will be used to populate this list, since it is not changed
		// many times
		templateIds.put("billing", "1001");
		templateIds.put("prereminder", "1002");
		templateIds.put("reminder", "1003");
	}

	class EmailOutbound {

		private String pk;
		private String table;
		private String emailType;

		EmailOutbound(String pk, String table,String emailType) {
			this.pk = pk;
			this.table = table;
			this.emailType = emailType;
		}

		// Override compare method, to avoid duplicacy in the queue
		@Override
		public boolean equals(Object o) {

			if (!(o instanceof EmailOutbound)) {
				return false;
			}
			EmailOutbound toCompare = (EmailOutbound) o;
			if (o != null && pk.equalsIgnoreCase(toCompare.pk)
					&& table.equalsIgnoreCase(toCompare.table) && emailType.equalsIgnoreCase(toCompare.emailType)) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public int hashCode() {
			int hashCode = 1;

			hashCode = 31 * hashCode + (pk == null ? 0 : pk.hashCode());
			hashCode = 31 * hashCode + table.hashCode();
			hashCode = 31* hashCode +emailType.hashCode();
			hashCode = 31 * hashCode + 17;

			return hashCode;
		}

	}

	public static void main(String[] args) {

		ConfigurationJdbc.closeOpenConnection();
		ConfigurationJdbc.setPoolConnection();

		// create new object of email service
		// start the thread
		EmailService getEmailList = new EmailService() {
			public void run() {
				// invoke methods for filling up the emailQueue
				try {
					getEmailList();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		getEmailList.setDaemon(true);
		getEmailList.start();

		// create new object of email service
		// start second thread
		EmailService sendEmails = new EmailService() {
			public void run() {
				// invoke methods for sending the emails
				try {
					generateEmail();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		sendEmails.setDaemon(true);
		sendEmails.start();

		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConfigurationJdbc.closeOpenConnection();
		}
	}

	

	

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private JSONObject recordJson = new JSONObject();

	private Map<String, String> recordMap = new HashMap<String, String>();

	public void getEmailList() throws Exception {

		try {			
			
			while (!Thread.currentThread().isInterrupted()) {
				
				// This will load the MySQL driver, each DB has its own driver
				connect = connectionPool.getConnection();
				// Statements allow to issue SQL queries to the database
				statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_UPDATABLE);
				
				// Result set get the result of the SQL query
				resultSet = statement
						.executeQuery("select * from emailoutbound");
				
				while (resultSet.next()) {
					
					resultSet.refreshRow();
					// It is possible to get the columns via name
					// also possible to get the columns via the column number
					// which starts at 1
					// e.g. resultSet.getSTring(2);
					String objectId = resultSet.getString("objectId");
					String objectType = resultSet.getString("objectType");
					String emailType = resultSet.getString("emailType");
					System.out.println(" id , type " + objectId + " - "
							+ objectType);

					// add only if object is not there in the queue
					EmailOutbound obj = new EmailOutbound(objectId, objectType,emailType);
					if (!emailQueue.contains(obj)) {
						emailQueue.add(obj);
					}

				}
				
				//clear all connection and resources
				close();

				try {
					Thread.sleep(50000);
				} catch (InterruptedException e) {
				}
				
			}

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	/**
	 * Sends Emails using the shared queue of email
	 * 
	 */
	public void generateEmail() {
		try {
			connect = connectionPool.getConnection();

			// keep sending emails
			while (!Thread.currentThread().isInterrupted()) {

				// take the element from the queue
				EmailOutbound outbound = emailQueue.take();

				String sql = queryTable.get(outbound.table.toLowerCase());
				preparedStatement = connect.prepareStatement(sql);

				// set the id
				preparedStatement.setString(1, outbound.pk);

				// execute and get the result
				ResultSet resultSet = preparedStatement.executeQuery();

				writeMetaData(resultSet);

				closeResources();

				// Get the template using template id
				sql = queryTable.get("emailtemplate");
				preparedStatement = connect.prepareStatement(sql);

				// get the template id for the given table name
				preparedStatement.setString(1, templateIds.get(outbound.emailType));

				// Having details of the template
				resultSet = preparedStatement.executeQuery();

				Email.formatEmail(resultSet, recordMap);

				try {
					Email.sendEmail();
				} catch (MessagingException e) {
					// error sending mail
					e.printStackTrace();
					// TODO, update that email sending is failed
					preparedStatement.close();

					// do not update this record
					continue;
				}

				// TODO, update that email has been sent successfully

				closeResources();

				// Now update the email sent
				preparedStatement = connect.prepareStatement(insertEmailSent);

				ArrayList<String> laParams = new ArrayList<String>();

				// add required parametes
				laParams.add(0, outbound.table);
				laParams.add(1, outbound.pk);
				laParams.add(2, templateIds.get(outbound.pk));
				laParams.add(3, Email.to);
				laParams.add(4, Email.from);
				laParams.add(5, Email.cc);
				laParams.add(6, Email.body);
				// laParams.add(7, "sysdate()");
				laParams.add(7, "auto");

				for (int i = 0; i < laParams.size(); i++) {
					preparedStatement.setString(i + 1, laParams.get(i));
				}

				preparedStatement.executeUpdate();

				// close the open statement
				closeResources();

				// Delete record from emailoutbound table so it is not picked up
				// again
				preparedStatement = connect
						.prepareStatement(deleteEmailOutbound);

				// set object id, and object type to be removed
				preparedStatement.setString(1, outbound.pk);
				preparedStatement.setString(2, outbound.table);

				preparedStatement.executeUpdate();

				connect.commit();
				
				closeResources();

				// commit all the changes
				
			}
		} catch (SQLException e1) {

			// rollback everything
			try {
				connect.rollback();
			} catch (SQLException e2) {

			}

			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			try {
				connect.rollback();
			} catch (SQLException e2) {

			}
			e.printStackTrace();
		} finally {
			close();
		}
	}

	/**
	 * Form a json object out of the result set, which is will be used to
	 * replace replacement variables from the template
	 * 
	 * @param resultSet
	 * @throws SQLException
	 */
	private void writeMetaData(ResultSet rs) throws SQLException {
		// Now get some metadata from the database
		// Result set get the result of the SQL query

		// Get meta data and store into a variable
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();

		// Do not use while loop, since expecting only one record
		if (rs.next()) {
			for (int i = 1; i <= columns; i++) {

				try {
					recordMap.put(md.getColumnName(i),
							rs.getObject(i) != null ? rs.getObject(i)
									.toString() : "");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	// You need to close the resultSet
	private void close() {
		try {
			closeResources();

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
	
	//Close resources, other than connection
	private void closeResources() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (preparedStatement != null) {
				preparedStatement.close();
			}

		} catch (Exception e) {

		}
	}

}

/**
 * Inner class having specific methods to send emails Send only 1 email at a
 * time, all fields are static
 */
class Email {

	private static final String USER_NAME = "deepansh1987@gmail.com";
	private static final String PASSWORD = "9891653996";

	// Recipient's email ID needs to be mentioned.
	static String to = "";

	// Sender's email ID needs to be mentioned
	static String from = "";

	// Sender's email ID needs to be mentioned
	static String cc = "";

	// Sender's email ID needs to be mentioned
	static String body = "";

	// Sender's email ID needs to be mentioned
	static String sub = "";

	// Assuming you are sending email from localhost
	static String host = "smtp.gmail.com";

	// Get system properties
	static Properties properties = System.getProperties();

	// Get the default Session object.
	static Session session = null;

	static {
		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");

		session = Session.getDefaultInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(USER_NAME, PASSWORD);
					}
				});
	}

	/**
	 * Replaces content of template by the value using JSONObject
	 */
	public synchronized static void formatEmail(ResultSet resultSet,
			Map<String, String> record) throws SQLException

	{
		System.out.println("formatEmail 1 ");
		// Get next record
		if (resultSet.next()) {

			System.out.println("formatEmail 2 ");
			// Get to list
			to = getRepalcedString(resultSet.getString("tolist"), record);

			// Get bcc list
			cc = getRepalcedString(resultSet.getString("cclist"), record);

			// get cc list
			from = getRepalcedString(resultSet.getString("FROMEMAIL"), record);

			// get subject
			sub = getRepalcedString(resultSet.getString("subject"), record);

			// get body
			body = getRepalcedString(resultSet.getString("body"), record);
		}
		System.out.println("formatEmail 3 ");
	}

	/**
	 * Sending email
	 * 
	 * @throws MessagingException
	 */
	public synchronized static void sendEmail() throws MessagingException {
		try {

			System.out.println("sendEmail 1 ");
			if (!validateEmail()) {
				System.out.println("sendEmail 2 ");
				return;
			}
			System.out.println("sendEmail 3 ");
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));

			message.addRecipient(Message.RecipientType.CC, new InternetAddress(
					cc));

			// Set Subject: header field
			message.setSubject(sub);

			// Send the actual HTML message, as big as you like
			message.setContent(body, "text/html");

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
			throw mex;
		}
	}

	/**
	 * Replace content of passed string from corresponding json object TODO:
	 * This is under testing, may not be working fully
	 */
	public static String getRepalcedString(String in,
			Map<String, String> recordMap) {

		StrSubstitutor s = new StrSubstitutor(recordMap);

		return s.replace(in);
	}

	/**
	 * Check if email is valid.
	 * 
	 */
	public static boolean validateEmail() {
		boolean valid = false;

		if (to == null || to.equalsIgnoreCase("") || sub == null
				|| sub.equalsIgnoreCase("")) {
			valid = false;
		} else {
			valid = true;
		}

		return valid;
	}
}

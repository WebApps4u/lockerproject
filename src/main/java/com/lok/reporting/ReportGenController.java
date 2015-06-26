package com.lok.reporting;

/**
 * Uses connection pool from JDBC. Does not use spring for this. 
 * Queries are very fast with plain jdbc. The class is meant only for REPORTING
 * No update/Insert/Delete!!!
 */
import static com.lok.config.ConfigurationJdbc.connectionPool;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.lok.config.ConfigurationJdbc;
import com.lok.config.ConfigurationLok;
import com.lok.config.ConstantLok;
import com.lok.dao.AutoGenDAO;
import com.lok.model.BillRecord;

public class ReportGenController {

	protected boolean savedFlag = false;
	protected String queryId = "";
	protected ResultSet resultSet = null;
	protected JasperPrint jasperPrint = new JasperPrint();
	protected String templateName = "";
	protected String jrxmlFile = ""; // templateName.jrxml
	protected String jasperFile = ""; // templateName.jasper
	protected String printableFile = "";
	protected boolean saveAsPdf = false;
	protected boolean saveAsExcel = false;
	protected String executableQuery = "";
	protected Map<String, Object> parameters = new HashMap<String, Object>();

	// more common fields
	protected String fromDate = "";
	protected String toDate = "";
	protected String asOnDate = "";

	protected static final String REPORTING_PATH = ConfigurationLok
			.getFileUploadPath()
			+ File.separator
			+ ConstantLok.REPORTING_TEMPLATE;

	protected static final String SAVED_REPORTS = ConfigurationLok
			.getFileUploadPath() + File.separator + ConstantLok.REPORTING_SAVED;

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;

	public enum QUERY_LIST {
		BILL_STATUS,

	}

	// set the pool for connection if not already available\
	public ReportGenController() {
		ConfigurationJdbc.setPoolConnection();
	}

	public static void main(String[] args) {

		String sourceFileName = "E:\\CODES\\LOK_GIT\\lockerproject\\reporting\\OutstandingBills.jrxml";
		String sourceFileNameJ = "E:\\CODES\\LOK_GIT\\lockerproject\\reporting\\OutstandingBills.jasper";
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		System.out.println("Compiling Report Design ...");
		try {
			/**
			 * Compile the report to a file name same as the JRXML file name
			 */

			connect = connectionPool.getConnection();
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			// Result set get the result of the SQL query
			resultSet = statement
					.executeQuery("select * from billrecord where lamt is not null and lstxa is not null limit 2");

			String printFileName = null;
			Map parameters = new HashMap();

			parameters.put("ReportTitle", "Address Report");
			parameters.put("BaseDir", new File(
					"E:\\CODES\\LOK_GIT\\lockerproject\\images\\test"));
			try {

				JasperCompileManager.compileReportToFile(sourceFileName);

				printFileName = JasperFillManager.fillReportToFile(
						sourceFileNameJ, parameters, new JRResultSetDataSource(
								resultSet));

				if (printFileName != null) {
					/**
					 * 1- export to PDF
					 */
					JasperExportManager.exportReportToPdfFile(printFileName,
							"C://sample_report.pdf");

					/**
					 * 2- export to HTML
					 */
					JasperExportManager.exportReportToHtmlFile(printFileName,
							"C://sample_report.html");
				}
			} catch (JRException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("Done compiling!!! ...");
	}

	public static JasperPrint getReportTest(String query) {
		JasperPrint jasperPrint = null;
		try {
			ConfigurationJdbc.closeOpenConnection();
			ConfigurationJdbc.setPoolConnection();

			String sourceFileName = "E:\\CODES\\LOK_GIT\\lockerproject\\reporting\\OutstandingBills.jrxml";
			String sourceFileNameJ = "E:\\CODES\\LOK_GIT\\lockerproject\\reporting\\OutstandingBills.jasper";
			Connection connect = null;
			Statement statement = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			System.out.println("Compiling Report Design ...");

			/**
			 * Compile the report to a file name same as the JRXML file name
			 */

			connect = connectionPool.getConnection();
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			// Result set get the result of the SQL query
			resultSet = statement.executeQuery(query);

			String printFileName = null;
			Map parameters = new HashMap();

			parameters.put("ReportTitle", "Bill Report");
			parameters.put("BaseDir", new File(
					"E:\\CODES\\LOK_GIT\\lockerproject\\images\\test"));

			JasperCompileManager.compileReportToFile(sourceFileName);

			jasperPrint = JasperFillManager.fillReport(sourceFileNameJ,
					parameters, new JRResultSetDataSource(resultSet));

		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// closeResources();
		}

		return jasperPrint;
	}

	public JasperPrint getReportTest(HttpServletRequest request) {

		// get the saved flag, if it is true, it is one of the saved report,
		// simple statement with no parameters
		String savedFlag = request.getParameter("saved");

		if (StringUtils.equalsIgnoreCase(savedFlag, "true")) {
			this.savedFlag = true;
		}

		JasperPrint jasperPrint = null;
		try {

			String sourceFileName = "E:\\CODES\\LOK_GIT\\lockerproject\\reporting\\OutstandingBills.jrxml";
			String sourceFileNameJ = "E:\\CODES\\LOK_GIT\\lockerproject\\reporting\\OutstandingBills.jasper";
			Connection connect = null;
			Statement statement = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			System.out.println("Compiling Report Design ...");

			/**
			 * Compile the report to a file name same as the JRXML file name
			 */

			String printFileName = null;

			parameters.put("ReportTitle", "Bill Report");
			parameters.put("BaseDir", new File(
					"E:\\CODES\\LOK_GIT\\lockerproject\\images\\test"));

			JasperCompileManager.compileReportToFile(sourceFileName);

		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConfigurationJdbc.closeOpenConnection();
		}

		return jasperPrint;
	}

	/**
	 * Run the database query by getting the connection
	 * 
	 * @throws SQLException
	 */
	public void getResult() throws SQLException {

		connect = connectionPool.getConnection();
		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);

		// Result set get the result of the SQL query
		resultSet = statement.executeQuery(executableQuery);

	}

	/**
	 * Fill report
	 * 
	 * @throws JRException
	 */
	public void fillReport() throws JRException {

		// TODO
		// is compiled jasper exist
		// isCompiled()

		if (parameters == null || parameters.isEmpty()) {
			parameters = new HashMap<String, Object>();
		}

		if (resultSet != null) {
			jasperPrint = JasperFillManager.fillReport(jasperFile, parameters,
					new JRResultSetDataSource(resultSet));
		}

		// export to pdf is flag is set true
		if (saveAsPdf == true) {
			Calendar date = Calendar.getInstance();
			String fileSave = SAVED_REPORTS + File.separator + templateName
					+ "_" + date.getTimeInMillis() + ".pdf";
			JasperExportManager.exportReportToPdfFile(jasperPrint, fileSave);
		}
	}

	/**
	 * Compile the jrxml, if not compiled earlier, or is being forced to compile
	 * again
	 * 
	 * @throws JRException
	 */

	public void compileReport(boolean forceCompile) throws JRException {

		if (StringUtils.isBlank(templateName)) {
			return;
		}

		if (forceCompile == true) {

			if (StringUtils.isBlank(jrxmlFile)) {
				jrxmlFile = REPORTING_PATH + File.separator + templateName
						+ ".jrxml";
			}
			// not saving in-memory, bug can stop whole application, better save
			// to file system, can be reusable later
			JasperCompileManager.compileReportToFile(jrxmlFile);

			// update compile file path
			jasperFile = REPORTING_PATH + File.separator + templateName
					+ ".jasper";
		}

		// if jasperFile path is not updated, get the path based on jrxmlfile
		if (StringUtils.isBlank(jasperFile)) {
			jasperFile = REPORTING_PATH + File.separator + templateName
					+ ".jasper";
		}
	}

	/**
	 * Compile the jrxml
	 * 
	 * @throws JRException
	 */
	public void compileReport() throws JRException {
		compileReport(false);
	}

	// Close resources, other than connection
	public final void closeResources() {
		try {
			if (connect != null) {
				connect.close();
			}
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

	// execute reporting. Overloaded with be used to run custom sql
	protected void execute(boolean forceCompile) throws JRException,
			SQLException {
		compileReport(forceCompile);
		getResult();
		fillReport();
	}

	// execute reporting. Overloaded with be used to run custom sql
	protected void execute() throws JRException, SQLException {
		compileReport(false);
		getResult();
		fillReport();
	}

	// Unimplemented methods, which extracting class must implement

	// sets title and name of the report and other important configurations
	// public abstract void fillParameters(Map<String,Object> parameters);

	// public abstract void setQuery();

	protected void validateCustomQuery() {
		if (StringUtils.isBlank(fromDate) || StringUtils.isBlank(toDate)
				|| StringUtils.isBlank(asOnDate)) {
			throw new IllegalArgumentException(
					" From, to and as on dates are mandatory for custom reports ");
		}
	}
}

/*
 * store all the sqls for saved reports
 */
class ReportsSql {

	final static String firstQuery = " where 1 =1 ";
	final static String SELECT = " SELECT ";
	final static String FROM = " FROM ";

	final static String currentYear = " and year(bdt)= year(sysdate()) ";
	final static String outstandingBills = " and bflg <> '*'";
	final static String allColumns = " * ";
	final static String billDetails = " billrecord ";
	
	//custom constant
	final static String inPeriod = " and date(%s) >= '%s' and date (%s) <= '%s' ";     // e.g. and date(bfdt) >= '2014-03-21' and date(bfdt) <=  '2015-03-21'

	// objects for single query
	String tableName = "";
	String columns = ""; // select abc, def, or, select *
	String query = ""; // use template queries, or add own query parameters

	// query which will be build using different queries
	public static StringBuilder customSql;

	/*
	 * @name: name of the saved search
	 * 
	 * @firstQuery boolean true if it is the first query, add 1=1 at the
	 * beginning
	 */
	public static String getSavedSql(String name, boolean firstQuery) {

		// start with and
		String sql = "";

		if (firstQuery == true) {
			sql = " 1=1 ";
		}

		switch (name) {
		case "currentyear":
			sql += currentYear + outstandingBills;
			break;
		case "outstandingbills":
			sql += outstandingBills;
			break;
		}

		return sql;
	}

	public static String getSavedSql(String name) {
		return getSavedSql(name, true);
	}

	/**
	 * Build query, based on table, column and conditions
	 */
	public String build() throws IllegalArgumentException {

		if (StringUtils.isBlank(tableName) || StringUtils.isBlank(columns)) {
			throw new IllegalArgumentException(
					" Table and columns are mandatory");
		}

		StringBuilder finalQuery = new StringBuilder();

		finalQuery.append(SELECT);
		finalQuery.append(columns);
		finalQuery.append(FROM);
		finalQuery.append(tableName);
		finalQuery.append(firstQuery);
		finalQuery.append(query);

		return finalQuery.toString();

	}
	
	public void addQuery(String query){
		this.query+= query;
	}

}

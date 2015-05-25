/**
This hold the business logic for the application
it will act as a binding between REST api and Service layer (which is DB layer in this app)
 * 
 */
package com.lok.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.codehaus.plexus.util.StringUtils;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.google.gson.JsonParser;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.lok.config.ConfigurationLok;
import com.lok.model.BillRecord;
import com.lok.model.BillRecordField;
import com.lok.model.EmailOutbound;
import com.lok.model.PartyRecord;
import com.lok.model.PartyRecordField;
import com.lok.model.ReturnMessage;
import com.lok.service.BillRecordService;
import com.lok.service.EmailOutboundService;
import com.lok.service.PartyRecordService;
import com.lok.service.impl.LokUtility;

/**
 * @author USER Holds business logic for all bill related functions
 *
 */
public class BillRecordController extends BaseController<BillRecordService> {

	// add for logging
	private static Logger logger = Logger.getLogger(BillRecordController.class);

	// load required service
	private BillRecordService billRecordService;

	// requires party details
	private PartyRecordController partyCntrl = new PartyRecordController();

	/*
	 * store all the sqls for saved reports
	 */
	private static class ReportsSql {

		final static String currentYear = " and year(bdt)= year(sysdate()) ";
		final static String outstandingBills = "and bflg <> '*'";

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

	}

	// Inner class for creating dynamic sql
	private class CustomDynamicQuery {

		public StringBuilder sql;

		final static String inYear = " year(bdt) = year('%s')";
		final static String billDateEqual = " Date(bdt) = '%s' ";

		CustomDynamicQuery() {
			sql = new StringBuilder(" 1=1 ");
		}

		//
		public CustomDynamicQuery createQuery(String queryConstant,
				String... params) {

			and(queryConstant, params);

			return this;
		}

		// Keep adding new query
		public CustomDynamicQuery and(String queryConstant, String... params) {

			sql.append(" and ");
			// return the formatted query
			sql.append(String.format(queryConstant, params));

			return this;
		}

		//
		public CustomDynamicQuery or(String queryConstant, String... params) {

			sql.append(" or ");
			// return the formatted query
			sql.append(String.format(queryConstant, params));

			return this;
		}

		// this will return the final sql formed
		public String build() {
			return sql.toString();
		}
	}

	public BillRecordController() {
		// Need to call super class to create service
		super(BillRecordService.class);
		logger.debug(" enter constructor BillRecordController");

		// get the bean
		// billRecordService = context.getBean(BillRecordService.class);

		billRecordService = getService();
		logger.debug(" Exit constructor BillRecordController with billRecordService"
				+ billRecordService);
	}

	/**
	 * Search the record with bill num and is not released yet
	 */
	public BillRecord getBillRecord(String billnum) {

		logger.debug(" enter constructor BillRecordController.getBillRecord() with billnum "
				+ billnum);
		BillRecord record = null;

		try {

			record = billRecordService.findById(billnum);

			logger.info("BillRecordController.getBillRecord() booking number for bill num "
					+ billnum + " is -> " + record.getLSNO());
		} catch (NullPointerException e) {
			logger.info(" No record BillRecordController.getBillRecord() found for billnum "
					+ billnum);
		} catch (Exception e) {
			logger.error(" Exception caught in BillRecordController.getBillRecord() -> "
					+ e.getMessage());
		}
		return record;
	}

	/**
	 * Get bill details along with additional required attributes Currently
	 * provides Key details
	 */
	public JSONObject getBillDetails(String billnum) {

		logger.debug(" enter constructor BillRecordController.getBillDetails() with billnum "
				+ billnum);
		JSONObject billDetails = null;
		try {

			BillRecord billRecord = billRecordService.findById(billnum);

			// create json out of it
			JSONObject billjson = new JSONObject(billRecord);
			// parse date
			LokUtility.changeDateFormat(BillRecord.class, billjson);

			// Get party details
			// create json out of it
			JSONObject partyjson = partyCntrl.getActiveKeyRecord(billRecord
					.getKNO());
			// parse date
			LokUtility.changeDateFormat(PartyRecord.class, partyjson);

			// create a flat json structure to return
			// merge two json using jsonparser
			billDetails = LokUtility.mergeJSON(billjson, partyjson, true);

		} catch (NullPointerException e) {
			logger.info(" No record BillRecordController.getBillDetails() found for billnum "
					+ billnum);
		} catch (Exception e) {
			logger.error(" Exception caught in BillRecordController.getBillDetails() -> "
					+ e.getMessage());
			e.printStackTrace();
		}
		return billDetails;
	}

	/**
	 * Returns the list of unpaid bills
	 * 
	 * @param: Keynum for which all the unpaid bills is to be fetched
	 * @param: sortBy column name or attribute on which sorting is to be done
	 */
	public List<BillRecord> getUnpaidBills(String keynum, String sortBy) {

		logger.debug(" enter BillRecordController.unpaidBills() with keynum "
				+ keynum + " sortBy " + sortBy);

		List<BillRecord> listBills = null;
		try {
			listBills = getUnpaidBills(keynum);
			Collections.sort(listBills);
		} catch (Exception e) {
			logger.error(" Exception caught in BillRecordController.unpaidBills(keynum,sortby) -> "
					+ e.getMessage());
			e.printStackTrace();
		}
		return listBills;

	}

	/**
	 * Returns the list of unpaid bills
	 * 
	 * @param: Keynum for which all the unpaid bills is to be fetched
	 * 
	 */
	public List<BillRecord> getUnpaidBills(String keynum) {

		logger.debug(" enter BillRecordController.unpaidBills() with keynum "
				+ keynum);

		List<BillRecord> listBills = new ArrayList<BillRecord>();
		try {

			Search search = new Search();
			search.addFilterEqual(BillRecordField.KNO.toString(), keynum);
			
			
			// No receipt number			
			search.addFilterOr(Filter.isNull(BillRecordField.LRCTN.toString()),Filter.equal(BillRecordField.LRCTN.toString(),""));
			
			listBills = billRecordService.search(search);

			// will not throw null pointer since already initialized
			logger.info(" List of unpaid Bills " + listBills.toString());

		} catch (Exception e) {
			logger.error(" Exception caught in BillRecordController.unpaidBills(keynum) -> "
					+ e.getMessage());
			e.printStackTrace();
		}
		return listBills;

	}

	/**
	 * Get all unpaid Bills
	 */
	public List<BillRecord> getAllUnpaidBills() {

		logger.debug(" enter BillRecordController.getAllUnpaidBills() ");

		List<BillRecord> listBills = new ArrayList<BillRecord>();
		try {

			Search search = new Search();
			search.addFilterNull(BillRecordField.LRCTN.toString()); // No
																	// receipt
																	// number
			listBills = billRecordService.search(search);

			// will not throw null pointer since already initialized
			logger.info(" List of unpaid Bills " + listBills.toString());

		} catch (Exception e) {
			logger.error(" Exception caught in BillRecordController.getAllUnpaidBills -> "
					+ e.getMessage());
			e.printStackTrace();
		}
		return listBills;

	}

	/**
	 * Update the Bill Record
	 */
	public ReturnMessage updateBillRecord(BillRecord record) {

		logger.debug(" enter constructor BillRecordController.updateBillRecord() ");
		ReturnMessage msg = new ReturnMessage();
		try {

			if (record.getLPYBA() < 0) {
				msg.setErrMsg("Open Key Already Exists in the System");
				msg.setStatus(ReturnMessage.StatusOfMessage.FAILURE);
				return msg;
			}

			// TODO Validate all the key details. It should recalculate
			// everything again, just to make sure any false input
			// validateBillRecord(record);

			if (StringUtils.isBlank(record.getBNO())
					|| record.getBNO().equalsIgnoreCase("NEW")) {
				// Get the new Bill number from DB
				record.setBNO(MasterSeqRecordController
						.generateNextId(BillRecord.class));

			}

			// Get the party details, which needs to be updated
			PartyRecord partyRecord = partyCntrl.getActiveKeyRecordBean(record
					.getKNO());

			// all the previous outstanding has been added to this bill
			partyRecord.setPOA(0D);

			// if advance is given, it should be updated by subtracting the
			// current amount paid.
			// it cannot be negative
			if (record.getLADV() > 0) {

				if (record.getLPYBA() > 0) {
					// all advance is used in new bill, thus clear advance
					// available
					partyRecord.setLSDA(0D);
				} else {
					// bill is cleared, and so advance amount as well
					record.setBFLG("*");
					record.setLRNO(partyRecord.getLRNO());

					partyRecord.setPOA(0D);
				}
			}

			// update rent due date for party master
			partyRecord.setLRDD(DateUtils.addDays(record.getBTDT(), 1));

			// call the service method to update
			billRecordService.save(record);

			// if the email is to be sent, create entry into email outbound
			// table
			// email outbound is rarely used from UI, thus creating local class
			// to get the context

			if (partyRecord.getSENDEMAIL1().equalsIgnoreCase("Y") || record.getSENDEMAIL().equalsIgnoreCase("Y")) {
				
				EmailOutbound email = new EmailOutbound();
				email.setObjectId(record.getBNO());
				email.setObjectType("billrecord");
				email.setEmailType("billing");
				
				//No need of this local class, since it is never reused
/*				class EmailContrl extends BaseController<EmailOutboundService> {

					EmailContrl() {
						super(EmailOutboundService.class);
					}

				}
				;

				new EmailContrl().getService().save(email);
*/			
				new BaseController<EmailOutboundService>(EmailOutboundService.class).getService().save(email);	
			}
			
			
			// update party master
			partyCntrl.getService().save(partyRecord);

			// assuming saved successfully if no error is thrown
			msg.setSuccessMsg(ReturnMessage.SuccessSet.UPDATE_SUCCESS
					.toString() + record.getBNO());
			msg.setObj(record.getBNO());

		} catch (Exception e) {
			logger.error(" Exception caught in BillRecordController.updateBillRecord() -> "
					+ e.getMessage());
			e.printStackTrace();

			msg.setErrMsg(ReturnMessage.ErrorSet.UNKNOWN_ERROR.toString());
		}

		return msg;
	}

	/**
	 * Auto generate bills for the given month
	 */
	public void generateAutoBills(String month) {

	}

	/**
	 * Get bill details for the month-year
	 */
	public List<BillRecord> getBills(String dueMonth, String dueYear) {
		logger.debug(" enter BillRecordController.getBills() month-year"
				+ dueMonth + "-" + dueYear);

		List<BillRecord> listBills = new ArrayList<BillRecord>();
		try {

			// have to create start date and end date from month and year
			// not sure how to use db functions here
			Search search = new Search();

			// create filter to get bills having bfdt in the given month-year
			search.addFilterCustom(" month(bfdt) ='" + dueMonth
					+ "' and year(bfdt)='" + dueYear + "'");

			listBills = billRecordService.search(search);

			// will not throw null pointer since already initialized
			logger.info(" List of Bills " + listBills.toString());

		} catch (Exception e) {
			logger.error(" Exception caught in BillRecordController.getBills -> "
					+ e.getMessage());
			e.printStackTrace();
		}
		return listBills;
	}

	// Reports starts below

	/*
	 * Return the saved search result Going forward, the saved search query will
	 * be in database or in properties file, so no code change is required
	 * 
	 * @param name Name of the saved search
	 * 
	 * @return JSON result of the
	 */
	public List<BillRecord> getSavedReport(String name) {

		logger.debug(" enter BillRecordController.getSavedReport() name "
				+ name);

		List<BillRecord> listBills = new ArrayList<BillRecord>();
		try {

			// have to create start date and end date from month and year
			// not sure how to use db functions here
			Search search = new Search();

			// create filter to get bills having bfdt in the given month-year
			search.addFilterCustom(ReportsSql.getSavedSql(name));

			listBills = billRecordService.search(search);

			// will not throw null pointer since already initialized
			logger.info(" List of Bills " + listBills.toString());

		} catch (Exception e) {
			logger.error(" Exception caught in BillRecordController.getSavedReport -> "
					+ e.getMessage());
			e.printStackTrace();
		}
		return listBills;
	}

	/**
	 * Create smart queries for different types of custom searches It should be
	 * more intelligent so as not to change on adding new options
	 */
	public List<BillRecord> getCustomReport(
			MultivaluedMap<String, String> allParams) {
		logger.debug(" enter BillRecordController.getCustomReport() params "
				+ allParams);

		List<BillRecord> listBills = new ArrayList<BillRecord>();
		try {

			// have to create start date and end date from month and year
			// not sure how to use db functions here
			Search search = new Search();

			String name = allParams.getFirst("name");
			String fromDate = allParams.getFirst("from-date");
			String toDate = allParams.getFirst("to-date");

			switch (name) {

			case "asondate":
				search.addFilterCustom(new CustomDynamicQuery().createQuery(
						CustomDynamicQuery.billDateEqual, fromDate).build());
				break;

			case "inyear":
				search.addFilterCustom(new CustomDynamicQuery().createQuery(
						CustomDynamicQuery.inYear, fromDate).build());
				break;

			default:
				// No search criteria found, send back error
				throw new IllegalArgumentException();
			}
			// create filter to get bills having bfdt in the given month-year
			// search.addFilterCustom(savedReportsSql.getSql(name));

			listBills = billRecordService.search(search);

			// will not throw null pointer since already initialized
			logger.info(" List of Bills " + listBills.toString());

		} catch (Exception e) {
			logger.error(" Exception caught in BillRecordController.getCustomReport -> "
					+ e.getMessage());
			e.printStackTrace();
		}
		return listBills;
	}
}

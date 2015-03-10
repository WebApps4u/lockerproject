/**
This hold the business logic for the application
it will act as a binding between REST api and Service layer (which is DB layer in this app)
 * 
 */
package com.lok.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.google.gson.JsonParser;
import com.googlecode.genericdao.search.Search;
import com.lok.config.ConfigurationLok;
import com.lok.model.BillRecord;
import com.lok.model.BillRecordField;
import com.lok.model.PartyRecord;
import com.lok.model.PartyRecordField;
import com.lok.model.ReturnMessage;
import com.lok.service.BillRecordService;
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
			
			//create json out of it
			JSONObject billjson = new JSONObject(billRecord);
			//parse date
			LokUtility.changeDateFormat(BillRecord.class, billjson);

			// Get party details
			PartyRecord partyRecord = partyCntrl.getActiveKeyRecord(billRecord
					.getKNO());
			//create json out of it
			JSONObject partyjson = new JSONObject(partyRecord);
			//parse date
			LokUtility.changeDateFormat(PartyRecord.class, partyjson);

			
			// create a flat json structure to return
			// merge two json using jsonparser
			billDetails = LokUtility.mergeJSON(billjson,
					partyjson, true);

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
			search.addFilterNull(BillRecordField.LRCTN.toString()); // No
																	// receipt
																	// number
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

			// call the service method to update
			billRecordService.save(record);

			// assuming saved successfully if no error is thrown
			msg.setSuccessMsg(ReturnMessage.SuccessSet.UPDATE_SUCCESS
					.toString());

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
	public void generateAutoBills(String month){
		
		
	}
}

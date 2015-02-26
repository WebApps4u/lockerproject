/**
This hold the business logic for the application
it will act as a binding between REST api and Service layer (which is DB layer in this app)
 * 
 */
package com.lok.controller;

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
import com.lok.service.BillRecordService;
import com.lok.service.impl.LokUtility;

/**
 * @author USER Holds business logic for all bill related functions
 *
 */
public class BillRecordController {

	// add for logging
	private static Logger logger = Logger.getLogger(BillRecordController.class);

	// load required services and session factory
	@Autowired
	private SessionFactory sessionFactory;

	// load required service
	@Autowired
	private BillRecordService billRecordService;
	
	//requires party details
	private PartyRecordController partyCntrl = new PartyRecordController();

	private ApplicationContext context = null; // invoke only when required
												// using constructor

	public BillRecordController() {

		logger.debug(" enter constructor BillRecordController");
		// get the context
		context = ConfigurationLok.getAppContext();

		// get the bean
		billRecordService = context.getBean(BillRecordService.class);
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
            
			 BillRecord billRecord =  billRecordService.findById(billnum); 
			 
			 //Get party details
			 PartyRecord partyRecord = partyCntrl.getActiveKeyRecord(billRecord.getKNO());
			 
			 //create a flat json structure to return
			 //merge two json using jsonparser
			 billDetails =  LokUtility.mergeJSON(new JSONObject(billRecord), new JSONObject(partyRecord), true)  ;
			 
		
		}catch(NullPointerException e){
			logger.info(" No record BillRecordController.getBillDetails() found for billnum "
					+ billnum);
		}
		catch (Exception e) {
			logger.error(" Exception caught in BillRecordController.getBillDetails() -> "
					+ e.getMessage());
			e.printStackTrace();
		}
		return billDetails;
	}

}

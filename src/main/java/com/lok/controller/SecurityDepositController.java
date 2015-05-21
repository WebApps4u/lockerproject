package com.lok.controller;

import org.apache.log4j.Logger;
import org.codehaus.plexus.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.lok.model.BillRecord;
import com.lok.model.PartyRecord;
import com.lok.model.ReceiptRecord;
import com.lok.model.ReturnMessage;
import com.lok.model.SecurityDeposit;
import com.lok.service.SecurityDepositService;
import com.lok.service.impl.LokUtility;

public class SecurityDepositController extends BaseController<SecurityDepositService>{

	// add for logging
	private static Logger logger = Logger
			.getLogger(SecurityDepositController.class);

	// Load the service
	private SecurityDepositService sdrcptService;

	public SecurityDepositController() {
		super(SecurityDepositService.class);
		logger.debug(" enter constructor SecurityDepositController");

		// get the bean
		sdrcptService = getService();
		logger.debug(" exit constructor SecurityDepositController");
	}
	
	// create the new sd receipt 
		public ReturnMessage createSDReceipt(SecurityDeposit record) {
			logger.debug(" enter  SecurityDepositController.createSDReceipt() ");

			ReturnMessage msg = new ReturnMessage();

			try {
				
				//Check if receipt is already exist, record is for update then
				if (StringUtils.isBlank(record.getSRCN())
						|| record.getSRCN().equalsIgnoreCase("NEW")) {
					// Get the new SD number from DB
					record.setSRCN(MasterSeqRecordController
							.generateNextId(SecurityDeposit.class));

				}

				String nextSRCN = record.getSRCN();

				// call the service method to update
				sdrcptService.save(record);

				// assuming saved successfully if no error is thrown
				msg.setSuccessMsg(ReturnMessage.SuccessSet.INSERT_SUCCESS
						.toString() + " : " + nextSRCN);

				msg.setObj(nextSRCN);

			} catch (IllegalArgumentException e) {

				logger.error(" Seq not generated in SecurityDepositController.createSDReceipt() -> "
						+ e.getMessage());
				e.printStackTrace();
				msg.setErrMsg(ReturnMessage.ErrorSet.UNKNOWN_ERROR.toString());
			} catch (Exception e) {
				logger.error(" Exception caught in SecurityDepositController.createSDReceipt() -> "
						+ e.getMessage());
				e.printStackTrace();

				msg.setErrMsg(ReturnMessage.ErrorSet.UNKNOWN_ERROR.toString());
			}

			return msg;
		}

		public JSONObject getSDReceiptDetails(String rcptnum,PartyRecordController partyCntrl) {
			logger.debug(" enter  SecurityDepositController.getSDReceiptDetails() with rcpt num "
					+ rcptnum);
			JSONObject rcptDetails = null;
			try {

				SecurityDeposit sdRecord = sdrcptService.findById(rcptnum);

				JSONObject rcptJson = new JSONObject(sdRecord);
				// correct the date format
				LokUtility.changeDateFormat(SecurityDeposit.class, rcptJson);

				// Get party details
				// create json out of it
				JSONObject partyjson = partyCntrl.getActiveKeyRecord(sdRecord
						.getSKNO());
				// parse date
				LokUtility.changeDateFormat(PartyRecord.class, partyjson);

				// create a flat json structure to return
				// merge two json using jsonparser
				rcptDetails = LokUtility.mergeJSON(rcptJson, partyjson, true);


			} catch (NullPointerException e) {
				logger.info(" No record  SecurityDepositController.getSDReceiptDetails() found for rcptnum "
						+ rcptnum);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(" Exception caught in  SecurityDepositController.getSDReceiptDetails() -> "
						+ e.getMessage());
				e.printStackTrace();
			}
			return rcptDetails;
		}

}

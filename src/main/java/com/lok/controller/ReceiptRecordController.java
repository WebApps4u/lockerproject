package com.lok.controller;

import org.apache.log4j.Logger;
import org.codehaus.plexus.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.googlecode.genericdao.search.Search;
import com.lok.model.BillRecord;
import com.lok.model.Lok_master_seq;
import com.lok.model.PartyRecord;
import com.lok.model.ReceiptRecord;
import com.lok.model.ReturnMessage;
import com.lok.service.ReceiptRecordService;
import com.lok.service.impl.LokUtility;

public class ReceiptRecordController extends
		BaseController<ReceiptRecordService> {

	// add for logging
	private static Logger logger = Logger
			.getLogger(ReceiptRecordController.class);

	// Load the service
	private ReceiptRecordService receiptService;

	public ReceiptRecordController() {
		super(ReceiptRecordService.class);
		logger.debug(" enter constructor ReceiptRecordController");

		// get the bean
		receiptService = getService();
		logger.debug(" exit constructor ReceiptRecordController");
	}

	// create the new receipt followed by update of key and bill
	public ReturnMessage createReceipt(ReceiptRecord record,
			PartyRecordController partyContrl, BillRecordController billContrl) {
		logger.debug(" enter  ReceiptRecordController.createReceipt() ");

		ReturnMessage msg = new ReturnMessage();

		try {
			
			//Check if receipt is already exist, record is for update then
			if (StringUtils.isBlank(record.getRCTN())
					|| record.getRCTN().equalsIgnoreCase("NEW")) {
				// Get the new Bill number from DB
				record.setRCTN(MasterSeqRecordController
						.generateNextId(ReceiptRecord.class));

			}

			String nextRCTN = record.getRCTN();

			// call the service method to update
			receiptService.save(record);

			System.out.println(" rec out  " + record.getRCOUT());

			// update the party record
			// get the party record based on key no and booking number
			PartyRecord partyRecord = partyContrl.getActiveKeyRecordBean(record
					.getRKNO());

			// set the partyRecord with update receipt details
			partyRecord.setPOA(record.getRCOUT());
			partyRecord.setLPA(record.getRADV());
			partyRecord.setLRND(record.getRCTD());

			// receipt info
			partyRecord.setLRNO(nextRCTN); // this should be the next generated
											// id

			// TODO update receipt date

			partyContrl.updatePartyRecord(partyRecord);

			// get the bill records
			// there can be multiple bills, separated by spaces
			String[] billNums = {};

			if (record.getRBNO() != null && record.getRBNO().trim() != null) {
				billNums = record.getRBNO().trim().split(" ");
			}

			for (String bill : billNums) {
				BillRecord billRecord = billContrl.getBillRecord(bill);
				billRecord.setBFLG("*");
				billRecord.setLRCTN(nextRCTN);

				billContrl.updateBillRecord(billRecord);
			}

			// assuming saved successfully if no error is thrown
			msg.setSuccessMsg(ReturnMessage.SuccessSet.INSERT_SUCCESS
					.toString() + " : " + nextRCTN);

			msg.setObj(nextRCTN);

		} catch (IllegalArgumentException e) {

			logger.error(" Seq not generated in ReceiptRecordController.createReceipt() -> "
					+ e.getMessage());
			e.printStackTrace();
			msg.setErrMsg(ReturnMessage.ErrorSet.UNKNOWN_ERROR.toString());
		} catch (Exception e) {
			logger.error(" Exception caught in ReceiptRecordController.createReceipt() -> "
					+ e.getMessage());
			e.printStackTrace();

			msg.setErrMsg(ReturnMessage.ErrorSet.UNKNOWN_ERROR.toString());
		}

		return msg;
	}

	public JSONObject getReceiptDetails(String rcptnum,
			PartyRecordController partyCntrl, BillRecordController billCntrl) {
		logger.debug(" enter  ReceiptRecordController.getReceiptDetails() with rcpt num "
				+ rcptnum);
		JSONObject rcptDetails = null;
		try {

			ReceiptRecord receiptRecord = receiptService.findById(rcptnum);

			JSONObject rcptJson = new JSONObject(receiptRecord);
			// correct the date format
			LokUtility.changeDateFormat(ReceiptRecord.class, rcptJson);

			String[] billNums = receiptRecord.getRBNO() != null ? receiptRecord
					.getRBNO().split(" ") : new String[0];
			
			JSONArray paidBillarr = new JSONArray();
			if (billNums.length != 0) {
				BillRecord[] billRecord = billCntrl.getService().findByIds(
						billNums);

				
				// create jsonobjects and then put in jsonarray
				for (int i = 0; i < billRecord.length; i++) {

					// create jsonobject and put in the array
					JSONObject obj = new JSONObject(billRecord[i]);

					// convert the date fields to the desired format
					LokUtility.changeDateFormat(BillRecord.class, obj);

					paidBillarr.put(obj);
				}
			}

			// Get party details
			// create json out of it
			JSONObject partyjson = partyCntrl.getActiveKeyRecord(receiptRecord
					.getRKNO());
			// parse date
			LokUtility.changeDateFormat(PartyRecord.class, partyjson);

			// create a flat json structure to return
			// merge two json using jsonparser
			rcptDetails = LokUtility.mergeJSON(rcptJson, partyjson, true);

			// Append the unpaid bills to the list
			if (rcptDetails != null && rcptDetails.length() != 0) {

				// allDetails.append("billlist", unpaidBillarr);
				rcptDetails.put("bills", paidBillarr);
			}

		} catch (NullPointerException e) {
			logger.info(" No record  ReceiptRecordController.getReceiptDetails() found for rcptnum "
					+ rcptnum);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(" Exception caught in  ReceiptRecordController.getReceiptDetails() -> "
					+ e.getMessage());
			e.printStackTrace();
		}
		return rcptDetails;
	}

}

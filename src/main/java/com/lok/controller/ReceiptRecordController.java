package com.lok.controller;

import org.apache.log4j.Logger;

import com.lok.model.BillRecord;
import com.lok.model.PartyRecord;
import com.lok.model.ReceiptRecord;
import com.lok.model.ReturnMessage;
import com.lok.service.ReceiptRecordService;

public class ReceiptRecordController extends BaseController<ReceiptRecordService> {

	   // add for logging
		private static Logger logger = Logger.getLogger(BillRecordController.class);

       //Load the service
		private ReceiptRecordService receiptService;
		
		public ReceiptRecordController() {
            super(ReceiptRecordService.class);
			logger.debug(" enter constructor ReceiptRecordController");

			// get the bean
			receiptService = getService();
			logger.debug(" exit constructor ReceiptRecordController");
		}
		
		//create the new receipt followed by update of key and bill
		public ReturnMessage createReceipt(ReceiptRecord record,PartyRecordController partyContrl,BillRecordController billContrl){
			logger.debug(" enter  ReceiptRecordController.createReceipt() ");
			ReturnMessage msg = new ReturnMessage();
			
			//get key and party controller for update
			partyContrl = new PartyRecordController();
			billContrl = new BillRecordController();
			
			try {

				// call the service method to update
				receiptService.save(record);
				
				System.out.println(" id "+record.getRCTN());

//				//update the party record
//				//get the party record based on key no and booking number
//				PartyRecord partyRecord = partyContrl.getActiveKeyRecord(record.getRKNO());
//				
//				//set the partyRecord with update receipt details
//				partyRecord.setPOA(record.getRCOUT());
//				partyRecord.setLPA(record.getRADV());
//				
//				//receipt info
//				partyRecord.setLRNO(lRNO);
//				
//				partyContrl.updatePartyRecord(partyRecord);
//				
//				//get the bill record
//				//there can be multiple bills, separated by spaces 
//				String[] billNums = record.getRBNO().split(" ");
//				
//				for(String bill: billNums){
//					BillRecord billRecord = billContrl.getBillRecord(bill);
//					
//				
//				}
				
				
				// assuming saved successfully if no error is thrown
				msg.setSuccessMsg(ReturnMessage.SuccessSet.INSERT_SUCCESS
						.toString());

			} catch (Exception e) {
				logger.error(" Exception caught in BillRecordController.updateBillRecord() -> "
						+ e.getMessage());
				e.printStackTrace();

				msg.setErrMsg(ReturnMessage.ErrorSet.UNKNOWN_ERROR.toString());
			}

			return msg;
		}
		

}

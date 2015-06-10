package com.lok.controller;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.plexus.util.StringUtils;

import com.lok.model.AccessRecord;
import com.lok.model.PartyRecord;
import com.lok.model.ReturnMessage;
import com.lok.model.ReturnMessage.StatusOfMessage;
import com.lok.service.AccessRecordService;

public class AccessRecordController extends BaseController<AccessRecordService> {
	
	// add for logging
		private static Logger logger = Logger.getLogger(BillRecordController.class);

		// load required service
		private AccessRecordService accessRecordService;

		// requires party details
		private PartyRecordController partyCntrl = new PartyRecordController();

		public AccessRecordController() {
			// Need to call super class to create service
			super(AccessRecordService.class);
			logger.debug(" enter constructor AccessRecordController");

			// get the bean
			// billRecordService = context.getBean(BillRecordService.class);

			accessRecordService = getService();
			logger.debug(" Exit constructor AccessRecordController with accessRecordService"
					+ accessRecordService);
		}
		
		//create new records
		/**
		 * Update the Access Record
		 */
		public ReturnMessage updateAccessRecord(AccessRecord record){
			
			logger.debug(" enter constructor AccessRecordController.updateAccessRecord() "+record);
			ReturnMessage msg = new ReturnMessage();
			try{
				
				//TODO Validate all the key details. It should recalculate everything again, just to make sure any false input
				//validatePartyRecord(record);
				
				//call the service method to update
				accessRecordService.save(record);
				
				//assuming saved successfully if no error is thrown
				msg.setSuccessMsg(ReturnMessage.SuccessSet.UPDATE_SUCCESS.toString()+record.getACCESSRECORDID());
				
				msg.setObj(record.getACCESSRECORDID());
				msg.setStatus(StatusOfMessage.SUCCESS);
				
			}catch(Exception e){
				logger.error(" Exception caught in AccessRecordController.updateAccessRecord() -> "+e.getMessage());
				e.printStackTrace();
				
				msg.setErrMsg(ReturnMessage.ErrorSet.UNKNOWN_ERROR.toString());
				
			}
			
			return msg;
		}
		
		/**
		 * Update list the Access Record
		 */
		public ReturnMessage updateAccessRecord(List<AccessRecord> listRecords){
			
			logger.debug(" enter constructor AccessRecordController.updateAccessRecord() list "+listRecords);
			ReturnMessage msg = new ReturnMessage();
			try{
				
				Iterator<AccessRecord> itr = listRecords.iterator();
				
				while(itr.hasNext()){
					msg = updateAccessRecord(itr.next());
					
					//if not success, then terminate will returning error
					if(!msg.getStatus().equalsIgnoreCase(StatusOfMessage.SUCCESS.toString())){
						return msg;
					}
				}
				msg.setSuccessMsg(ReturnMessage.SuccessSet.UPDATE_SUCCESS.toString());
				
			}catch(Exception e){
				logger.error(" Exception caught in AccessRecordController.updateAccessRecord() -> "+e.getMessage());
				e.printStackTrace();
				
				msg.setErrMsg(ReturnMessage.ErrorSet.UNKNOWN_ERROR.toString());
				
			}
			
			return msg;
		}

}

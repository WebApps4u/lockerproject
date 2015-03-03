/**
This hold the business logic for the application
it will act as a binding between REST api and Service layer (which is DB layer in this app)
 * 
 */
package com.lok.controller;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.genericdao.search.Search;
import com.lok.model.PartyRecord;
import com.lok.model.PartyRecordField;
import com.lok.model.ReturnMessage;
import com.lok.service.PartyRecordService;

/**
 * @author USER
 * Holds business logic for all party related functions
 *
 */
public class PartyRecordController extends BaseController<PartyRecordService>{
	
	//add for logging
	private static Logger logger = Logger.getLogger(PartyRecordController.class);
	
	//load required services and session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	//load required service
	private PartyRecordService partyRecordService;
	
	public PartyRecordController(){
		super(PartyRecordService.class);
		logger.debug(" enter constructor PartyRecordController");
		
		// get the service bean
		partyRecordService = getService();
		logger.debug(" Exit constructor PartyRecordController with partyRecordService"+partyRecordService);
	}
	
	/**
	 * Search the record with key num and is not released yet
	 */
	public PartyRecord getActiveKeyRecord(String keynum){
		
		logger.debug(" enter constructor PartyRecordController.getActiveKeyRecord() with keynum "+keynum);
		PartyRecord record = null;
		
		try{
			
			//object to search object using generic dao
			Search search = new Search();
			search.addFilterEqual(PartyRecordField.KNO.toString(), keynum);
			
			//add another filter to fetch only Alive records
			//which will have released box unchecked
			search.addFilterIn(PartyRecordField.RBOX.toString(),"","F","f");
			
			record = partyRecordService.search(search).get(0);  //only one record is expected for this condition
			
			logger.info("PartyRecordController.getActiveKeyRecord() booking number for key num "+keynum+" is -> "+record.getLSNO());
		}catch(NullPointerException e){
			logger.info(" No record PartyRecordController.getActiveKeyRecord() found for keynum "+keynum);
		}
		catch(Exception e){
			logger.error(" Exception caught in PartyRecordController.getActiveKeyRecord() -> "+e.getMessage());
		}
		return record;
	}
	
	/**
	 * Update the Party Record
	 */
	public ReturnMessage updatePartyRecord(PartyRecord record){
		
		logger.debug(" enter constructor PartyRecordController.updatePartyRecord() ");
		ReturnMessage msg = new ReturnMessage();
		try{
			
			//call the service method to update
			partyRecordService.save(record);
			
			//assuming saved successfully if no error is thrown
			msg.setSuccessMsg(ReturnMessage.SuccessSet.UPDATE_SUCCESS.toString());
			
		}catch(Exception e){
			logger.error(" Exception caught in PartyRecordController.updatePartyRecord() -> "+e.getMessage());
			e.printStackTrace();
			
			msg.setErrMsg(ReturnMessage.ErrorSet.UNKNOWN_ERROR.toString());
		}
		
		return msg;
	}

}

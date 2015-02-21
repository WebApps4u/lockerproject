/**
This hold the business logic for the application
it will act as a binding between REST api and Service layer (which is DB layer in this app)
 * 
 */
package com.lok.controller;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.googlecode.genericdao.search.Search;
import com.lok.config.ConfigurationLok;
import com.lok.model.PartyRecord;
import com.lok.model.PartyRecordField;
import com.lok.service.PartyRecordService;

/**
 * @author USER
 * Holds business logic for all party related functions
 *
 */
public class PartyRecordController {
	
	//add for logging
	private static Logger logger = Logger.getLogger(PartyRecordController.class);
	
	//load required services and session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	//load required service
	@Autowired
	private PartyRecordService partyRecordService;
	
	private ApplicationContext context = null;  //invoke only when required using constructor
	
	public PartyRecordController(){
		
		logger.debug(" enter constructor PartyRecordController");
		//get the context 
		context = ConfigurationLok.getAppContext();
		
		// get the bean
		partyRecordService = context.getBean(PartyRecordService.class);
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

}

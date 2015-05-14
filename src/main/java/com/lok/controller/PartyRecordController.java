/**
This hold the business logic for the application
it will act as a binding between REST api and Service layer (which is DB layer in this app)
 * 
 */
package com.lok.controller;

import org.apache.log4j.Logger;
import org.codehaus.plexus.util.StringUtils;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.genericdao.search.Search;
import com.lok.model.CustomerDetails;
import com.lok.model.PartyRecord;
import com.lok.model.PartyRecordField;
import com.lok.model.ReturnMessage;
import com.lok.service.PartyRecordService;
import com.lok.service.impl.LokUtility;

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
	public JSONObject getActiveKeyRecord(String keynum){
		
		logger.debug(" enter constructor PartyRecordController.getActiveKeyRecord() with keynum "+keynum);
		JSONObject partyRecord = null;
		
		try{
					
			PartyRecord partyBean = getActiveKeyRecordBean(keynum);
			
			//Controller for customer details
			CustomerDetailsController custContrl = new CustomerDetailsController();
			
			//check if customer id is available, if yes, get the customer details 
			if(StringUtils.isNotBlank(partyBean.getFIRSTCUSTOMER())){
				
				//Get details of first customer
				CustomerDetails custDetails = custContrl.getCustomerRecordBean(partyBean.getFIRSTCUSTOMER());
				
				//update the KYC docs, first name, email, address etch to the party
				//since the column names are different, cannot use merge json feature
				
				partyBean.setPNM2(custDetails.getFIRSTNAME());
				partyBean.setPNM3(custDetails.getLASTNAME());
				
				partyBean.setEMAILID(custDetails.getEMAILID());
				partyBean.setPAD1(custDetails.getADDRESS1());
				
				//if PP, AADHAR, PHOTO, etc path is having some value, means it is available, tick as Y
				if(StringUtils.isNotBlank(custDetails.getPPPATH()) || StringUtils.isNotBlank(custDetails.getAADHARPATH())){
					partyBean.setKYC11("Y");
				}
				
				if(StringUtils.isNotBlank(custDetails.getPHOTOPATH())){
					partyBean.setKYC14("Y");
				}
				//TODO: Add more columns to be updated. 
			}
			partyRecord = new JSONObject(partyBean);
			
			//create json out of it
			//change the date format
			LokUtility.changeDateFormat(PartyRecord.class, partyRecord);
			
			logger.info("PartyRecordController.getActiveKeyRecord() booking number for key num "+keynum+" is -> "+partyRecord);
		}catch(NullPointerException e){
			logger.info(" No record PartyRecordController.getActiveKeyRecord() found for keynum "+keynum);
		}
		catch(Exception e){
			logger.error(" Exception caught in PartyRecordController.getActiveKeyRecord() -> "+e.getMessage());
		}
		return partyRecord;
	}
	
	/**
	 * Search the record with key num and is not released yet
	 */
	public PartyRecord getActiveKeyRecordBean(String keynum){
		
		logger.debug(" enter constructor PartyRecordController.getActiveKeyRecordBean() with keynum "+keynum);
		PartyRecord record = null;
		
		try{
			
			
			//object to search object using generic dao
			Search search = new Search();
			search.addFilterEqual(PartyRecordField.KNO.toString(), keynum);
			
			//add another filter to fetch only Alive records
			//which will have released box unchecked
			//search.addFilterIn(PartyRecordField.RBOX.toString(),"","F","f");
			
			//Modified on 14 May 2015
			//The release condition is Release flag should be R
			search.addFilterNotIn(PartyRecordField.RELS.toString(),"R","r");
			
			record = partyRecordService.search(search).get(0);  //only one record is expected for this condition
			
					
			logger.info("PartyRecordController.getActiveKeyRecordBean() booking number for key num "+keynum+" is -> "+record);
		}catch(NullPointerException e){
			logger.info(" No record PartyRecordController.getActiveKeyRecordBean() found for keynum "+keynum);
		}
		catch(Exception e){
			logger.error(" Exception caught in PartyRecordController.getActiveKeyRecordBean() -> "+e.getMessage());
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
			
			//TODO Validate all the key details. It should recalculate everything again, just to make sure any false input
			//validatePartyRecord(record);
			
			//check if key already active, and booking number is NEW. It is invalid, and should be returned with error
			if(getActiveKeyRecordBean(record.getKNO())!=null && (StringUtils.isBlank(record.getLSNO())   ||record.getLSNO().equalsIgnoreCase("NEW"))){
				msg.setErrMsg("Open Key Already Exists in the System");
				msg.setStatus(ReturnMessage.StatusOfMessage.FAILURE);
				return msg;
			}
			else if (StringUtils.isBlank(record.getLSNO())   ||record.getLSNO().equalsIgnoreCase("NEW")){
				//Get the new Booking number from DB
				record.setLSNO(MasterSeqRecordController.generateNextId(PartyRecord.class));
			}
			
			//call the service method to update
			partyRecordService.save(record);
			
			//assuming saved successfully if no error is thrown
			msg.setSuccessMsg(ReturnMessage.SuccessSet.UPDATE_SUCCESS.toString()+record.getLSNO());
			
			msg.setObj(record.getLSNO());
			
		}catch(Exception e){
			logger.error(" Exception caught in PartyRecordController.updatePartyRecord() -> "+e.getMessage());
			e.printStackTrace();
			
			msg.setErrMsg(ReturnMessage.ErrorSet.UNKNOWN_ERROR.toString());
			
		}
		
		return msg;
	}

	//TODO: Check if customer id is valid. If not, then throw an exception
	//it updates the key details and link with the customer. 
	public void linkCustomer(String keynum, String customerid,String index) throws Exception{
		
		logger.debug(" enter linkCustomer keynum & customerid "+keynum+" : "+customerid);
		
		PartyRecord partyRecord = getActiveKeyRecordBean(keynum);
		
		//based on the index of the customer, update 1st, 2nd or 3rd customer id
		switch(index){
		case "1":
			partyRecord.setFIRSTCUSTOMER(customerid);
			break;
		case "2":
			partyRecord.setSECONDCUSTOMER(customerid);
			break;
		case "3":
			partyRecord.setTHIRDCUSTOMER(customerid);
			break;
		}
		
		//update party Record
		partyRecordService.save(partyRecord);
		
	}
	
	//TODO: Check if customer id is valid. If not, then throw an exception
		//it updates the key details and link with the customer. 
		public void delinkCustomer(String keynum,String index) throws Exception{
			
			logger.debug(" enter linkCustomer keynum & customerid "+keynum);
			
			PartyRecord partyRecord = getActiveKeyRecordBean(keynum);
			
			//based on the index of the customer, update 1st, 2nd or 3rd customer id
			switch(index){
			case "1":
				partyRecord.setFIRSTCUSTOMER("");
				break;
			case "2":
				partyRecord.setSECONDCUSTOMER("");
				break;
			case "3":
				partyRecord.setTHIRDCUSTOMER("");
				break;
			}
			
			//update party Record
			partyRecordService.save(partyRecord);
			
		}

}

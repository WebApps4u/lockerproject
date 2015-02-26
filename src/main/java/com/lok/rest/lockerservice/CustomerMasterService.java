package com.lok.rest.lockerservice;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.GET;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonParser;
import com.lok.controller.BillRecordController;
import com.lok.controller.PartyRecordController;
import com.lok.model.BillRecord;
import com.lok.model.PartyRecord;

@Path("/lockerservice")
public class CustomerMasterService {

	//add for logging
	private static Logger logger = Logger.getLogger(CustomerMasterService.class);
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		//TODO Keep this format at the properties level
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	dateFormat.setLenient(false);
	webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	/**
	 * Return booking details in the form of
	 * json
	 * key would be mapped to the field names to 
	 * populate correct field. 
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/keydetails/{keynum}")
	public PartyRecord getKeyDetails(@PathParam("keynum") String keyNum){
		
		logger.debug(" enter CustomerMasterService.getKeyDetails() with keynum "+keyNum);
		PartyRecord partyRecord = null;
		try{
			
			PartyRecordController contrl = new PartyRecordController();
			partyRecord = contrl.getActiveKeyRecord(keyNum);
			
			
		}catch(Exception e){
			//log to the logger
		}
		return partyRecord;
	}
	
	/**
	 * Return the Bill Details in the form of JSON
	 * Bill number will be provided
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/billdetails/{billnum}")
	public String getBillDetails(@PathParam("billnum") String billnum){
		
		logger.debug(" enter CustomerMasterService.getBillDetails() with billnum "+billnum);
		
		JSONObject billDetails = null;
		
		try{
			
			BillRecordController contrl = new BillRecordController();
			billDetails = contrl.getBillDetails(billnum);
			
			
		}catch(Exception e){
			//log to the logger
		}
		return billDetails!=null?billDetails.toString():"";
	}
	
}

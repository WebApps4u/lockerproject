package com.lok.rest.lockerservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lok.bean.KeyDetails;
import com.lok.controller.PartyRecordController;
import com.lok.model.PartyRecord;

@Path("/lockerservice")
public class CustomerMasterService {

	//add for logging
	private static Logger logger = Logger.getLogger(CustomerMasterService.class);
	
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
}

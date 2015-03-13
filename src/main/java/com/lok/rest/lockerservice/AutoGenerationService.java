package com.lok.rest.lockerservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.lok.controller.AutoGenController;
import com.lok.model.PartyRecord;

@Path("/autogen")
public class AutoGenerationService {

	//add for logging
	private static Logger logger = Logger.getLogger(AutoGenerationService.class);
	AutoGenController contrl = new AutoGenController();
	
	/**
	 * Generates bills lying in given month and year 
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/bills")
	public void genBills(@QueryParam("dueMonth") String dueMonth,
									@QueryParam("dueYear") String dueYear){
		logger.debug(" enter AutoGeneration.getBills() with dueMonth-dueYear "+dueMonth+"-"+dueYear);
		
		try{
			
			contrl.initiateBillGeneration(dueMonth,dueYear);
			
		}catch(Exception e){
			//log to the logger
		}
	}
	
		
}

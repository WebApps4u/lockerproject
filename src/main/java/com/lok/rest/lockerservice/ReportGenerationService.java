package com.lok.rest.lockerservice;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.lok.controller.BillRecordController;
import com.lok.controller.PartyRecordController;
import com.lok.model.BillRecord;

/**
 * All reports are generated using this service class
 * 
 * @author USER
 *
 */

@Path("/reports")
public class ReportGenerationService {

	private static Logger logger = Logger
			.getLogger(CustomerMasterService.class);

	PartyRecordController partyContrl = new PartyRecordController();
	BillRecordController billContrl = new BillRecordController();

	/*
	 * Get the bill report based on the type it can be 'saved' or 'custom' Based
	 * on different option select, controller is invoked
	 * 
	 * @return: returns JSON String of bill details
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/bills")
	public String getBillReport(@Context UriInfo uriInfo) {
		logger.debug(" enter CustomerMasterService.getBillReport() ");

		JSONObject billsJson = null;
		try {
			
			//Get the query parameters
			MultivaluedMap<String, String> allParams = uriInfo.getQueryParameters();
			
			//Get the type of the report
			final String type = allParams.getFirst("type");
			
			//if type is saved, get the name of the saved search
			switch(type){
			case "saved":
				
				allParams.getFirst("name");
				break;
			case "custom":
				//Pass all the additional parameters to the controller
				break;
			}
			
			
					
			List<BillRecord> allBills = null;
		} catch (Exception e) {
			// log to the logger
		}

		return billsJson != null ? billsJson.toString() : "";
	}
}

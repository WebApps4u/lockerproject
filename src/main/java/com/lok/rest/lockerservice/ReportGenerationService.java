package com.lok.rest.lockerservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.lok.controller.AutoGenController;
import com.lok.controller.BillRecordController;
import com.lok.controller.PartyRecordController;
import com.lok.model.BillRecord;
import com.lok.reporting.ReportGenController;
import com.lok.service.impl.LokUtility;

/**
 * All reports are generated using this service class
 * 
 * @author USER
 *
 */

@Path("/reports")
public class ReportGenerationService {

	private static Logger logger = Logger
			.getLogger(ReportGenerationService.class);

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
	@Path("/bills/{type}")
	public String getBillReport(@PathParam("type") String type,  @Context UriInfo uriInfo) {
		logger.debug(" enter CustomerMasterService.getBillReport() ");

		JSONObject billsJson = null;
		try {
			
			List<BillRecord> allBills = new ArrayList<BillRecord>();
			
			//Get the query parameters
			MultivaluedMap<String, String> allParams = uriInfo.getQueryParameters();
			
			//if type is saved, get the name of the saved search
			switch(type){
			case "saved":
				
				allBills = billContrl.getSavedReport(allParams.getFirst("name"));
				break;
			case "custom":
				//Pass all the additional parameters to the controller
				allBills = billContrl.getCustomReport(allParams);
				break;
			}
			
			JSONArray billarr = new JSONArray();
			//create jsonobjects and then put in jsonarray
			for (int i=0;i<allBills.size();i++){
				
				//create jsonobject and put in the array
				JSONObject obj = new JSONObject(allBills.get(i));
				
				//convert the date fields to the desired format
				LokUtility.changeDateFormat(BillRecord.class, obj);
				
				billarr.put(obj);
			}
			
			billsJson = new JSONObject();
			
			//put arrays of bills to key 'bills'
			billsJson.put("bills", billarr);

		} catch (Exception e) {
			// log to the logger
			e.printStackTrace();
		}

		return billsJson != null ? billsJson.toString() : "";
	}
}

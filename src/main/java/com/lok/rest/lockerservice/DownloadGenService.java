package com.lok.rest.lockerservice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.lok.controller.BillRecordController;
import com.lok.excelutil.DownloadService;
import com.lok.model.BillRecord;
import com.lok.service.impl.LokUtility;

@Path("/downloads")
public class DownloadGenService {

	//add for logging
	private static Logger logger = Logger.getLogger(CustomerMasterService.class);
	
	BillRecordController billContrl = new BillRecordController();
	
	@GET
	@Path("/billreport/{type}")
	public String getBillReport(@Context HttpServletResponse response,  @PathParam("type") String type,  @Context UriInfo uriInfo) {
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
			
			//Have not formed singleton since it will be used by different beans
			//Rather, it should be Multiple registration singleton, where any new bean will add its instance
			//TODO Make DownloadService as multiple singleton
			new DownloadService<BillRecord>(BillRecord.class).downloadXLS(response, allBills);

		} catch (Exception e) {
			// log to the logger
			e.printStackTrace();
		}
		return "";
	}
}

package com.lok.rest.lockerservice;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.JsonParser;
import com.lok.controller.AccessRecordController;
import com.lok.controller.BillRecordController;
import com.lok.controller.CustomerDetailsController;
import com.lok.controller.MasterSeqRecordController;
import com.lok.controller.PartyRecordController;
import com.lok.controller.ReceiptRecordController;
import com.lok.controller.SecurityDepositController;
import com.lok.model.AccessRecord;
import com.lok.model.BillRecord;
import com.lok.model.CustomerDetails;
import com.lok.model.PartyRecord;
import com.lok.model.ReceiptRecord;
import com.lok.model.ReturnMessage;
import com.lok.model.SecurityDeposit;
import com.lok.service.impl.LokUtility;

@Path("/lockerservice")
public class CustomerMasterService {

	//add for logging
	private static Logger logger = Logger.getLogger(CustomerMasterService.class);
	
	PartyRecordController partyContrl = new PartyRecordController();
	BillRecordController billContrl = new BillRecordController();
	ReceiptRecordController receiptContrl = new ReceiptRecordController();
	CustomerDetailsController custDetailsContrl = new CustomerDetailsController();
	SecurityDepositController sdContrl = new SecurityDepositController();
	AccessRecordController accessContrl = new AccessRecordController();
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder webDataBinder) {
		//TODO Keep this format at the properties level
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	dateFormat.setLenient(false);
	webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	System.out.println( " init method called");
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
	public String getKeyDetails(@PathParam("keynum") String keyNum){
		
		logger.debug(" enter CustomerMasterService.getKeyDetails() with keynum "+keyNum);
		JSONObject partyRecord = null;
		try{
			
			
			partyRecord = partyContrl.getActiveKeyRecord(keyNum);
			
			
		}catch(Exception e){
			//log to the logger
			logger.debug(" Excep CustomerMasterService.getKeyDetails() with keynum "+keyNum);
		}
		
		return partyRecord!=null?partyRecord.toString():"";
	}
	
	/**
	 * Update the Key details of an existing key
	 */
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/keydetails/")
	@JsonIgnoreProperties(ignoreUnknown=true)
	public Response updateKeyDetails(PartyRecord partyRecord){
		
		JSONObject output = null;
		try{
			
			ReturnMessage msg = partyContrl.updatePartyRecord(partyRecord);
			
			if(msg==null){
				
				//Set the default error message to unknown
				output = new JSONObject(new ReturnMessage().setDefaultErr());
			}
			else{
				output = new JSONObject(msg);
			}
		}catch(Exception e){
			//log to the logger
		}
		
		return Response.status(200).entity(output.toString()).build();
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
			
			
			billDetails = billContrl.getBillDetails(billnum);
			
			
		}catch(Exception e){
			//log to the logger
		}
		return billDetails!=null?billDetails.toString():"";
	}
	
	
	
	/**
	 * Get the key details along with all the unpaid bills
	 * In the order of latest bill at the top
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/unpaidbills/{keynum}")
	public String getKeyDetailsUnpaidBills(@PathParam("keynum") String keynum){
		
		logger.debug(" enter CustomerMasterService.getKeyDetailsUnpaidBills() with billnum "+keynum);
		
		JSONObject allDetails = null;
		
		try{
			
			List<BillRecord> unpaidBills = null;
			
			unpaidBills = billContrl.getUnpaidBills(keynum,"BDT");
			
			
			JSONArray unpaidBillarr = new JSONArray();
			//create jsonobjects and then put in jsonarray
			for (int i=0;i<unpaidBills.size();i++){
				
				//create jsonobject and put in the array
				JSONObject obj = new JSONObject(unpaidBills.get(i));
				
				//convert the date fields to the desired format
				LokUtility.changeDateFormat(BillRecord.class, obj);
				
				unpaidBillarr.put(obj);
			}
			
			//get the key details

			allDetails = partyContrl.getActiveKeyRecord(keynum);
			LokUtility.changeDateFormat(PartyRecord.class, allDetails);
			
			//Append the unpaid bills to the list
			if(allDetails!=null && allDetails.length()!=0){
				
				//allDetails.append("billlist", unpaidBillarr);
				allDetails.put("bills",unpaidBillarr);
			}
			
		}catch(Exception e){
			//log to the logger
		}
		return allDetails!=null?allDetails.toString():"";
	} 
	
	/**
	 * Update the Bill details of an existing bill
	 */
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/billdetails/")
	public Response updateBillDetails(BillRecord billRecord){
		
		JSONObject output = null;
		try{
			
			ReturnMessage msg = billContrl.updateBillRecord(billRecord);
			
			if(msg==null){
				
				//Set the default error message to unknown
				output = new JSONObject(new ReturnMessage().setDefaultErr());
			}
			else{
				output = new JSONObject(msg);
			}
		}catch(Exception e){
			//log to the logger
		}
		
		return Response.status(200).entity(output.toString()).build();
	}
	
	/**
	 * Generates bills lying in given month and year 
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/bills")
	public String getBills(@QueryParam("dueMonth") String dueMonth,
									@QueryParam("dueYear") String dueYear){
		logger.debug(" enter CustomerMasterService.getBills() with dueMonth-dueYear "+dueMonth+"-"+dueYear);
		
		JSONObject billsJson = null;
		try{
			
			List<BillRecord> allBills = null;
			
			allBills = billContrl.getBills(dueMonth, dueYear);
			
			
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
			
		}
		catch(Exception e){
			//log to the logger
		}
		
		return billsJson!=null?billsJson.toString():"";
	}
	
	
	//Receipt section starts
	/**
	 * Create a new receipt with update to party and bill tables
	 */
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/receipt/")
	public Response createReceipt(ReceiptRecord receiptRecord){
		
		JSONObject output = null;
		try{
			
			ReturnMessage msg = receiptContrl.createReceipt(receiptRecord,partyContrl,billContrl);
			
			if(msg==null){
				
				//Set the default error message to unknown
				output = new JSONObject(new ReturnMessage().setDefaultErr());
			}
			else{
				output = new JSONObject(msg);
			}
		}catch(Exception e){
			//log to the logger
		}
		
		return Response.status(200).entity(output.toString()).build();
	}
	
	//Get receipt details
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/receipt/{recnum}")
	public String getReceiptDetails(@PathParam("recnum") String recnum){
		
		logger.debug(" enter CustomerMasterService.getReceiptDetails() with receipt num "+recnum);
		
		JSONObject rcptDetails = null;
		
		try{
			
			
			rcptDetails = receiptContrl.getReceiptDetails(recnum,partyContrl,billContrl);
			
			
		}catch(Exception e){
			//log to the logger
		}
		return rcptDetails!=null?rcptDetails.toString():"";
		
	}
	
	//Get customer details. It will fetch KYC documents details, name, email & address for the given customer
	//NOT READY
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/link/{keynum}/{customerid}/{index}")
	public String getCustomerDetails(@PathParam("customerid") String customerid,
									@PathParam("keyid") String keynum,
									@PathParam("index") String index){
		logger.debug(" enter CustomerMasterService.getKeyDetailsUnpaidBills() with billnum "+customerid);
		
		JSONObject allDetails = null;
		
		try{
			
			partyContrl.linkCustomer(keynum,customerid,index);
			
			//get the customer details to be shown to key details
			//it has specific fields, thus cannot be used directly
			CustomerDetails custDetails = custDetailsContrl.getCustomerRecordBean(customerid);
			
			//set the 
			
			
		}catch(Exception e){
			//log to the logger
		}
		return allDetails!=null?allDetails.toString():"";
	}
	
	
	/**
	 * Security Deposit Receipt Section
	 */
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/sdreceipt/")
	public Response createSDReceipt(SecurityDeposit sdRcptRecord){
		
		JSONObject output = null;
		try{
			
			ReturnMessage msg = sdContrl.createSDReceipt(sdRcptRecord);
			
			if(msg==null){
				
				//Set the default error message to unknown
				output = new JSONObject(new ReturnMessage().setDefaultErr());
			}
			else{
				output = new JSONObject(msg);
			}
		}catch(Exception e){
			//log to the logger
		}
		
		return Response.status(200).entity(output.toString()).build();
	}
	
	//Get receipt details
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/sdreceipt/{sdrecnum}")
	public String getSDReceiptDetails(@PathParam("sdrecnum") String sdrecnum){
		
		logger.debug(" enter CustomerMasterService.getSDReceiptDetails() with sd receipt num "+sdrecnum);
		
		JSONObject rcptDetails = null;
		
		try{
			
			
			rcptDetails = sdContrl.getSDReceiptDetails(sdrecnum,partyContrl);
			
			
		}catch(Exception e){
			//log to the logger
		}
		return rcptDetails!=null?rcptDetails.toString():"";
		
	}
	
	//Access record
	//Creates multiple entries to access records. It deciphers json list to map AccessRecord pojo
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/accessrecords/")
	public Response createAccessRecords(String accessJsonList){
		JSONObject output = null;
		try{
			
			ReturnMessage msg = null;
			
		    List<AccessRecord> accessRecords = LokUtility.<AccessRecord>DeserializeObject(accessJsonList,AccessRecord.class);
			
		    msg = accessContrl.updateAccessRecord(accessRecords);
			if(msg==null){
				
				//Set the default error message to unknown
				output = new JSONObject(new ReturnMessage().setDefaultErr());
			}
			else{
				output = new JSONObject(msg);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Response.status(200).entity(output.toString()).build();
	}
	
}

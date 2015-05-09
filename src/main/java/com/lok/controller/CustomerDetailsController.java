package com.lok.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import com.lok.config.ConfigurationLok;
import com.lok.config.ConstantLok;
import com.lok.model.CustomerDetails;
import com.lok.model.ReceiptRecord;
import com.lok.model.ReturnMessage;
import com.lok.service.BillRecordService;
import com.lok.service.CustomerDetailsService;
import com.lok.service.impl.LokUtility;

public class CustomerDetailsController extends BaseController<CustomerDetailsService> {

	// add for logging
	private static Logger logger = Logger.getLogger(CustomerDetailsController.class);
	
	// load required service
	private CustomerDetailsService custDetailsService;
	
	public CustomerDetailsController() {
		// Need to call super class to create service
		super(CustomerDetailsService.class);
		logger.debug(" enter constructor BillRecordController");

		// get the bean
		// billRecordService = context.getBean(BillRecordService.class);

		custDetailsService = getService();
		logger.debug(" Exit constructor BillRecordController with billRecordService"
				+ custDetailsService);
	}

	/**
	 * Creates new Customer details
	 * 
	 * Weird parameter, required for file upload during customer creation
	 */
	public ReturnMessage createNewCustomer(List<FileItem> listOfFileItems){
		
		logger.debug(" Enter into createNewCustomern "+listOfFileItems );
		ReturnMessage retrnMessage = new ReturnMessage();
		
		try{
			
			//create customer id from next sequence for customer details
			String nextCustomerId = MasterSeqRecordController.generateNextId(CustomerDetails.class);
			
			//create directory structure tree for KYC documents
			DefaultMutableTreeNode kycDir = new DefaultMutableTreeNode(ConstantLok.KYC_UPLOAD_DIRECTORY);
			
			DefaultMutableTreeNode custId = new DefaultMutableTreeNode(nextCustomerId);
						
			//make customer id folder child to KYC directory
			kycDir.add(custId);
			
			
			DefaultMutableTreeNode photo = new DefaultMutableTreeNode("PHOTO_FILE");
			DefaultMutableTreeNode pp = new DefaultMutableTreeNode("PP_FILE");
			DefaultMutableTreeNode aadhar = new DefaultMutableTreeNode("AADHAR_FILE");
			
			//create PAN, AADHAR, PP folders inside customerid folder
			custId.add(photo);
			custId.add(pp);
			custId.add(aadhar);
			
			Iterator<FileItem> itr = listOfFileItems.iterator();
			
			while(itr.hasNext()){
				
				//Get each item. Suggesting that customer details is created here only
				FileItem item = itr.next();
				if(!item.isFormField()){
					
					//update specific node
					DefaultMutableTreeNode file = new DefaultMutableTreeNode(item);
					
					//according to the item, add it to the appropriate folder
					switch( item.getFieldName()){
					
					case "PHOTO_FILE":
						photo.add(file);
						break;
					case "PP_FILE":
						pp.add(file);
						break;
					case "AADHAR_FILE":
						aadhar.add(file);
						break;
					}
				}
			}
			
			//create files in the system
			LokUtility.uploadAllFiles(ConfigurationLok.getFileUploadPath(), kycDir);
			
			//TODO, make it generic rather than hard coding
			
			//while creating node, make entry in customer details object for path locations of various attributes of customer
			
			
			retrnMessage.setSuccessMsg(ReturnMessage.SuccessSet.INSERT_SUCCESS.toString());
			
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
			retrnMessage.setDefaultErr();
			}
		return retrnMessage;
	}
	
	
	/**
	 * Get the customer details if already exists
	 */
	public JSONObject getCustomerDetails(String custId){
		JSONObject obj = new JSONObject();
		
		return obj;
	}
}

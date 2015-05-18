package com.lok.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import com.googlecode.genericdao.search.Search;
import com.lok.config.ConfigurationLok;
import com.lok.config.ConstantLok;
import com.lok.model.CustomerDetails;
import com.lok.model.CustomerDetailsMap;
import com.lok.model.PartyRecord;
import com.lok.model.PartyRecordField;
import com.lok.model.ReceiptRecord;
import com.lok.model.ReturnMessage;
import com.lok.service.BillRecordService;
import com.lok.service.CustomerDetailsService;
import com.lok.service.impl.LokUtility;

public class CustomerDetailsController extends
		BaseController<CustomerDetailsService> {

	// add for logging
	private static Logger logger = Logger
			.getLogger(CustomerDetailsController.class);

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
	public ReturnMessage createNewCustomer(List<FileItem> listOfFileItems) {

		logger.debug(" Enter into createNewCustomern " + listOfFileItems);
		ReturnMessage retrnMessage = new ReturnMessage();

		try {

			// create customer id from next sequence for customer details
			String nextCustomerId = MasterSeqRecordController
					.generateNextId(CustomerDetails.class);
			
			//Create new customer details object, which is the entity to be saved to database
			CustomerDetails custDetails = new CustomerDetails();
			
			custDetails.setCUSTOMERID(nextCustomerId);

			// create directory structure tree for KYC documents
			DefaultMutableTreeNode kycDir = new DefaultMutableTreeNode(
					ConstantLok.KYC_UPLOAD_DIRECTORY);

			DefaultMutableTreeNode custId = new DefaultMutableTreeNode(
					nextCustomerId);

			// make customer id folder child to KYC directory
			kycDir.add(custId);

			Iterator<FileItem> itr = listOfFileItems.iterator();

			while (itr.hasNext()) {

				// Get each item. Suggesting that customer details is created
				// here only
				FileItem item = itr.next();
				if (!item.isFormField()) {

					// Create folder, with the name of the file type, and then
					// add file node by adding the file item to it.
					DefaultMutableTreeNode folder = new DefaultMutableTreeNode(
							item.getFieldName());

					// update specific node
					DefaultMutableTreeNode file = new DefaultMutableTreeNode(
							item);

					// add file node to the folder
					folder.add(file);

					// add folder to the parent customer id folder
					custId.add(folder);
					
					// while creating node, make entry in customer details object for
					// path locations of various attributes of customer
					try{
						String beanProp = CustomerDetailsMap.getDescriptionByCode(item.getFieldName());
						String filePath = kycDir+File.separator+custId+File.separator+folder+File.separator+item.getName();
					BeanUtils.setProperty(custDetails, beanProp, filePath);
					}catch(Exception e){
						e.printStackTrace();
						logger.warn(" Object is not updated : File path "+e);
					}
				}
				else{
					//normal field, should be updated by the value
					try{
						String beanProp = CustomerDetailsMap.getDescriptionByCode(item.getFieldName());
					BeanUtils.setProperty(custDetails, beanProp, item.getString());
					}catch(Exception e){
						e.printStackTrace();
						logger.warn(" Object is not updated string value "+e);
					}
				}
			}

			// create files in the system
			LokUtility.uploadAllFiles(ConfigurationLok.getFileUploadPath(),
					kycDir);

			custDetailsService.save(custDetails);

			
			retrnMessage.setSuccessMsg(ReturnMessage.SuccessSet.INSERT_SUCCESS
					.toString()+ " Customer id: "+nextCustomerId);

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			retrnMessage.setDefaultErr();
		}

		return retrnMessage;
	}
	
	/**
	 * Creates new Customer details
	 * 
	 * Weird parameter, required for file upload during customer creation
	 */
	public CustomerDetails createNewCustomerT(List<FileItem> listOfFileItems,String incustId) {

		logger.debug(" Enter into createNewCustomern " + listOfFileItems);
		ReturnMessage retrnMessage = new ReturnMessage();
		
		//Create new customer details object, which is the entity to be saved to database
		CustomerDetails custDetails = null;

		try {

			// create customer id from next sequence for customer details
			String nextCustomerId = "";
			
			if(StringUtils.isEmpty(incustId) || incustId.equalsIgnoreCase("NEW")){
			
			//listOfFileItems.indexOf();
			nextCustomerId = MasterSeqRecordController
					.generateNextId(CustomerDetails.class);
			
			custDetails = new CustomerDetails();
			custDetails.setCUSTOMERID(nextCustomerId);
			}else{
				
				custDetails = custDetailsService.findById(incustId);
				nextCustomerId = custDetails.getCUSTOMERID();
			}
			
			//if custDetails is null, throw error invalid customer id
			if(custDetails == null){
				throw new Exception(" Invalid Customer id");
			}
			// create directory structure tree for KYC documents
			DefaultMutableTreeNode kycDir = new DefaultMutableTreeNode(
					ConstantLok.KYC_UPLOAD_DIRECTORY);

			DefaultMutableTreeNode custId = new DefaultMutableTreeNode(
					nextCustomerId);

			// make customer id folder child to KYC directory
			kycDir.add(custId);

			Iterator<FileItem> itr = listOfFileItems.iterator();

			while (itr.hasNext()) {

				// Get each item. Suggesting that customer details is created
				// here only
				FileItem item = itr.next();
				if (!item.isFormField()) {

					// Create folder, with the name of the file type, and then
					// add file node by adding the file item to it.
					DefaultMutableTreeNode folder = new DefaultMutableTreeNode(
							item.getFieldName());

					// update specific node
					DefaultMutableTreeNode file = new DefaultMutableTreeNode(
							item);

					// add file node to the folder
					folder.add(file);

					// add folder to the parent customer id folder
					custId.add(folder);
					
					// while creating node, make entry in customer details object for
					// path locations of various attributes of customer
					try{
						String beanProp = CustomerDetailsMap.getDescriptionByCode(item.getFieldName());
						String filePath = kycDir+"/"+custId+"/"+folder+"/"+item.getName();
					BeanUtils.setProperty(custDetails, beanProp, filePath);
					}catch(Exception e){
						e.printStackTrace();
						logger.warn(" Object is not updated : File path "+e);
					}
				}
				else{
					//normal field, should be updated by the value
					try{
						String beanProp = CustomerDetailsMap.getDescriptionByCode(item.getFieldName());
					BeanUtils.setProperty(custDetails, beanProp, item.getString());
					}catch(Exception e){
						e.printStackTrace();
						logger.warn(" Object is not updated string value "+e);
					}
				}
			}

			// create files in the system
			LokUtility.uploadAllFiles(ConfigurationLok.getFileUploadPath(),
					kycDir);

			custDetailsService.save(custDetails);

			
			retrnMessage.setSuccessMsg(ReturnMessage.SuccessSet.INSERT_SUCCESS
					.toString()+ " Customer id: "+nextCustomerId);

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			retrnMessage.setDefaultErr();
		}

		return custDetails;
	}

	/**
	 * Get the customer details if already exists
	 */
	public JSONObject getCustomerDetails(String custId) {
		JSONObject obj = new JSONObject(getCustomerRecordBean(custId));

		return obj;
	}
	
	/**
	 * Search the record with customerid 
	 */
	public CustomerDetails getCustomerRecordBean(String customerid){
		
		logger.debug(" enter constructor CustomerDetailsController.getActiveKeyRecordBean() with customerid "+customerid);
		CustomerDetails record = null;
		
		try{
			record = custDetailsService.findById(customerid);
		}
		catch(Exception e){
			logger.error(" Exception caught in CustomerDetailsController.getActiveKeyRecordBean() with customerid "+customerid +e.getMessage());
		}
		return record;
	}
}

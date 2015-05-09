package com.lok.rest.lockerservice;

/**
 * Service for all the upload files
 */
import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONObject;

import com.lok.config.ConstantLok;
import com.lok.controller.CustomerDetailsController;
import com.lok.service.impl.LokUtility;

@Path("/upload")
public class UploadService {

	// add for logging
	private static Logger logger = Logger.getLogger(UploadService.class);
	
	//Customer details controller 
	CustomerDetailsController custDetailsCntrl = new CustomerDetailsController();

	/**
	 * Upload customer kyc documents
	 * 
	 * TODO: Not ready for editing, only for uploading new
	 */

	@POST
	@Path("/customerdetails/")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response uploadCustomerDetails(// FormDataMultiPart input
			// @FormDataParam("file") InputStream uploadedInputStream,
			// @FormDataParam("file") FormDataContentDisposition fileDetail
			@Context HttpServletRequest request) {

		JSONObject output = new JSONObject();
		
		try {
			custDetailsCntrl.createNewCustomer(LokUtility.getFileItemsFromRequest(request));
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(200).entity(output.toString()).build();
	}

}

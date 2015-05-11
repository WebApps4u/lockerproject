package com.lok.rest.lockerservice;

/**
 * Service for all the upload files
 */
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
import org.glassfish.jersey.server.mvc.Template;
import org.glassfish.jersey.server.mvc.Viewable;
import org.json.JSONObject;

import com.lok.config.ConstantLok;
import com.lok.controller.CustomerDetailsController;
import com.lok.model.CustomerDetails;
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
	 * @throws IOException 
	 * @throws ServletException 
	 */

	@POST
	@Path("/customerdetails/")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public void uploadCustomerDetails(// FormDataMultiPart input
			// @FormDataParam("file") InputStream uploadedInputStream,
			// @FormDataParam("file") FormDataContentDisposition fileDetail
			@Context HttpServletRequest request,
			@Context HttpServletResponse response
			) throws IOException, ServletException {

		JSONObject output = new JSONObject();
		
		try {
		//	custDetailsCntrl.createNewCustomer(LokUtility.getFileItemsFromRequest(request));
			
			CustomerDetails custDetails = custDetailsCntrl.createNewCustomerT(LokUtility.getFileItemsFromRequest(request));
			
			request.setAttribute("customer", custDetails);
			
			request.getRequestDispatcher("/jsp/lockerservice/custmaster/customerkyc.jsp").forward(request,response);
		//	response.sendRedirect("/Locker_Financial_Society/jsp/lockerservice/custmaster/customerkyc.jsp");
			
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//return Response.status(200).entity(output.toString()).build();
	}

/*	@GET
	@Path("/{customerid}")
	@Template(name="/lockerservice/custmaster/customerkyc.jsp")
	public CustomerDetails getCustomerDetails(@PathParam("customerid") String customerid){
		
		return custDetailsCntrl.getCustomerRecordBean(customerid);
	}*/
	
	@GET
	@Path("/test/{customerid}")
	public Viewable getCustomerDetails(@PathParam("customerid") String customerid){
		
		//return new Viewable("/jsp/lockerservice/custmaster/customerkyc",custDetailsCntrl.getCustomerRecordBean(customerid));
		return new Viewable("/jsp/lockerservice/custmaster/customerkyc");
	}
	

}

package com.lok.rest.lockerservice;

import java.io.IOException;

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
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.glassfish.jersey.server.mvc.Viewable;
import org.json.JSONObject;

import com.lok.controller.EmailTemplateController;
import com.lok.controller.PartyRecordController;
import com.lok.model.CustomerDetails;
import com.lok.model.EmailTemplate;
import com.lok.model.ReturnMessage;
import com.lok.service.impl.LokUtility;

@Path("/adminservice")
public class AdminService {

	// add for logging
	private static Logger logger = Logger
			.getLogger(CustomerMasterService.class);

	EmailTemplateController emailTempContrl = new EmailTemplateController();

	/**
	 * Update Email template for Bill
	 * 
	 * TODO: Not ready for editing, only for uploading new
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/billtemplate")
	public Response updateBillEmailTemplate(EmailTemplate emailTemplate)
			throws IOException, ServletException {

		JSONObject output = null;
		try {
			ReturnMessage msg = emailTempContrl.updateTemplate(emailTemplate);

			if (msg == null) {

				// Set the default error message to unknown
				output = new JSONObject(new ReturnMessage().setDefaultErr());
			} else {
				output = new JSONObject(msg);
			}
		} catch (Exception e) {
			// log to the logger
		}
		return Response.status(200).entity(output.toString()).build();
	}

	/*
	 * @GET
	 * 
	 * @Path("/{customerid}")
	 * 
	 * @Template(name="/lockerservice/custmaster/customerkyc.jsp") public
	 * CustomerDetails getCustomerDetails(@PathParam("customerid") String
	 * customerid){
	 * 
	 * return custDetailsCntrl.getCustomerRecordBean(customerid); }
	 */

	@GET
	@Path("/billtemplate/{templateId}")
	public Viewable getCustomerDetails(
			@PathParam("templateId") String templateId) {

		EmailTemplate template = emailTempContrl.getTemplateBean(templateId);
		
		return new Viewable("/jsp/admin/emailtemplate/billtemplate",
				template);
	}

}

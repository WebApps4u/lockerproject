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
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONObject;

import com.lok.config.ConstantLok;

@Path("/upload")
public class UploadService {

	// add for logging
	private static Logger logger = Logger.getLogger(UploadService.class);

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
		// configures some settings
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(ConstantLok.THRESHOLD_SIZE);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(ConstantLok.MAX_FILE_SIZE);
		upload.setSizeMax(ConstantLok.REQUEST_SIZE);

		// constructs the directory path to store upload file
		String uploadPath = "D:\\locker"
				+ File.separator + ConstantLok.UPLOAD_DIRECTORY;
		// creates the directory if it does not exist
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			boolean dir = uploadDir.mkdirs();
			System.out.println(" dir "+dir);
		}

		try {
			// parses the request's content to extract file data
			List formItems = upload.parseRequest(request);
			Iterator iter = formItems.iterator();

			// iterates over form's fields
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				// processes only fields that are not form fields
				if (!item.isFormField()) {
					String fileName = new File(item.getName()).getName();
					String filePath = uploadPath + File.separator + fileName;
					File storeFile = new File(filePath);

					// saves the file on disk
					item.write(storeFile);
				}
			}
			request.setAttribute("message",
					"Upload has been done successfully!");
		} catch (Exception ex) {
			request.setAttribute("message",
					"There was an error: " + ex.getMessage());
			ex.printStackTrace();
		}

		// List<BodyPart> inBodyParts = input.getBodyParts();

		return Response.status(200).entity(output.toString()).build();
	}

}

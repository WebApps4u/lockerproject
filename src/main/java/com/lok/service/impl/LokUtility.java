package com.lok.service.impl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import com.lok.config.ConfigurationLok;
import com.lok.config.ConstantLok;
import com.lok.controller.BillRecordController;
import com.lok.model.AccessRecord;

/**
 * Contains Utility methods that can be reused across all application
 * 
 * @author USER
 *
 */
public class LokUtility {

	// add for logging
	private static Logger logger = Logger.getLogger(LokUtility.class);

	/**
	 * Merge the two json
	 * 
	 * @param base
	 *            : The base jsonobject in which other json is to be merged
	 * @param toAppend
	 *            : The second jsonobject which needs to append
	 * @param bOverite
	 *            : Boolean field, if true, in case of collision, base value is
	 *            kept if false, in case of collision, array will be formed
	 *            keeping the key Currently not supported the nestification
	 */
	public static JSONObject mergeJSON(JSONObject base, JSONObject toAppend,
			boolean bOverite) throws JSONException {
		logger.debug(" enter mergeJSON LokUtility.mergeJSON() with billnum ");

		JSONObject mergedContent = null;

		// currently not supporting the nesting
		if (!bOverite) {
			throw new UnsupportedOperationException(" Nesting not available");
		}

		mergedContent = new JSONObject(base, JSONObject.getNames(base));
		for (String crunchifyKey : JSONObject.getNames(toAppend)) {
			mergedContent.put(crunchifyKey, toAppend.get(crunchifyKey));
		}

		return mergedContent;
	}

	/**
	 * Changes the dateformat fields of the JSONObject based on the class object
	 * passed change the values in original object
	 */
	public static void changeDateFormat(Class<?> beanClass, JSONObject obj) {
		logger.debug(" enter changeDateFormat LokUtility.changeDateFormat()  ");
		try {

			for (Field field : beanClass.getDeclaredFields()) {
				if (field.getType().isAssignableFrom(Date.class)) {

					// get the name of that field
					String key = field.getName();

					// get the value from json
					String value = (String) obj.getString(key);
					
					//Get only the date part, if null, then replace it by empty String
					obj.put(key, (value==null||value.equals("null"))?"":value.split(" ")[0]);
				}
			}
		} catch (JSONException e) {
			logger.debug(" enter changeDateFormat LokUtility.changeDateFormat()  "
					+ e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * Store all files together Common utility to store all the files at the
	 * given path Accept Map having files and its path to be stored at It does
	 * not calculate any path It will create new directory if does not exist
	 * 
	 * Accepts all the files along with complete directory structure.
	 */

	public static void uploadAllFiles(String path, DefaultMutableTreeNode node)
			throws IllegalArgumentException, Exception {
		logger.debug(" uploadAllFiles with path " + path + " node as " + node);
		// if path location is empty error out
		if (StringUtils.isEmpty(path)) {
			throw new IllegalArgumentException(" Invalid path for file upload ");
		}
		if (node.getUserObject() instanceof FileItem) {
			FileItem item = (FileItem) node.getUserObject();

			// item can be accidently left blank. This might be the final
			// directory
			// leave the file creation then
			if (StringUtils.isNotBlank(item.getName())) {
				path = path + File.separator + item.getName();

				File storeFile = new File(path);

				// saves the file on disk
				item.write(storeFile);
			}
		}

		else if (node.getUserObject() instanceof String) {
			String dir = (String) node.getUserObject();

			path = path + File.separator + dir;
			File crDir = new File(path);

			if (!crDir.exists()) {
				crDir.mkdirs();
			}
		} else {
			throw new IllegalArgumentException(
					" this class object is not allowed in Tree Node "
							+ node.getClass());
		}

		// if leaf node, then create file and done
		if (node.isLeaf()) {
			return;
		} else {

			// cannot remove this warning, since node function does not have
			// generic type safety in place
			Enumeration children = node.children();

			while (children.hasMoreElements()) {
				uploadAllFiles(path,
						(DefaultMutableTreeNode) children.nextElement());
			}
		}

	}

	/**
	 * It parse the request and returns enumeration for file item from servlet
	 * request
	 * 
	 * @throws FileUploadException
	 */
	public static List<FileItem> getFileItemsFromRequest(
			HttpServletRequest request) throws FileUploadException {

		logger.debug(" enter getFileItemsFromRequest with request " + request);
		// configures some settings
		return getUploadInstance().parseRequest(request);

	}

	private static ServletFileUpload uploadInstance = null;
	
	//Returns the instance of upload, singleton
	public static ServletFileUpload getUploadInstance(){
		
		if(uploadInstance==null){
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(ConstantLok.THRESHOLD_SIZE);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			uploadInstance = new ServletFileUpload(factory);
			uploadInstance.setFileSizeMax(ConstantLok.MAX_FILE_SIZE);
			uploadInstance.setSizeMax(ConstantLok.REQUEST_SIZE);
		}
		return uploadInstance;
	}
	
	//Create array of objects of bean class file from string which is serialized json
	//it will check key for hyphen (-), if found, then it would consider it as parameter which is
	//part of one record. If there is no hyphen, then that would be considered as common parameter which 
	//will go in all the records
	//e.g. { "key":"1111-W", "abc":"111s" , "custname-0":"dee", "custname-1":"axx", "attendedby-0":"axd","attenedby-1","kss"}
	//        common           common          record 1            record 2             record 1             record 2
	public static <T> List<T> DeserializeObject(String serializedList,Class<T> beanClass) throws JSONException, JsonParseException, JsonMappingException, IOException{
		
		List<T> listOfObj = new ArrayList<T>();
		JSONObject obj = new JSONObject(serializedList);
		
		//create json array to hold each row to be inserted to bean class object
		JSONArray jsonArray = new JSONArray();
		
		Iterator<String> itr = obj.keys();
		
		JSONObject currentObj= null;
		//Get all the fields of bean class
		
		//keep storing all the common data, will be added to each row later
		JSONObject commonFields = new JSONObject();
		
		
		//go through all the keys of json to find out common object list and maintain list of 
		//items to be inserted as separate records
		while(itr.hasNext()){
			
			//get the json key
			String key = itr.next();
			
			//check if key has hyphen (-), if it does, then it should be parsed to get name, and put in appropirate array position
			
				
				//get the number after hyphen, this is position where this value should be put in array
				String field_num[] = StringUtils.split(key, "-");      //has to be only one hyphen in the name
				
				String field = field_num[0];
				String num   = "";
				
				//get the second parameter only if exists
				if(field_num.length>1){
					num = field_num[1];
				}
				
				
				if (StringUtils.isBlank(num)){
					//common field
					//add this to common fields
					commonFields.put(key, obj.get(key));
				}
				else if(StringUtils.isNumeric(num)){
					
					int index = Integer.parseInt(num)-1;
					
					try{
					currentObj = (JSONObject) jsonArray.get(index);
					}
					catch ( JSONException e ) { //no better way to handle the index out of bound exception
						currentObj = new JSONObject();
						jsonArray.put(index,currentObj);
					}
					
					currentObj.put(field, obj.get(key));
				}
				else{
					throw new IllegalArgumentException(" Invalid value after - for list: "+num+ " : it should be numeric. Value found "+key);
				}
			
		}

		
		
		ObjectMapper obj1 = new ObjectMapper();
		
		for (int i=0;i<jsonArray.length();i++){
			
			JSONObject oneRecord = jsonArray.getJSONObject(i);
			
			Iterator<String> itrCommon = commonFields.keys();
			
			//add common fields to final list
			while(itrCommon.hasNext()){
				String eachKey = itrCommon.next();
				oneRecord.put(eachKey, commonFields.get(eachKey));
			}
			
			String jsonString = oneRecord.toString();
		//add to the list of objects
		   listOfObj.add((T) obj1.readValue(jsonString,beanClass));
		}
		
		return listOfObj ;
	}
	
}

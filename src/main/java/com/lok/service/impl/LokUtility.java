package com.lok.service.impl;

import java.lang.reflect.Field;
import java.sql.Date;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.lok.controller.BillRecordController;

/**
 * Contains Utility methods that can be reused
 * across all application
 * @author USER
 *
 */
public class LokUtility {

	// add for logging
	private static Logger logger = Logger.getLogger(LokUtility.class);
 
	/**
	 * Merge the two json
	 * 
	 * @param base : The base jsonobject in which other json is to be merged
	 * @param toAppend : The second jsonobject which needs to append
	 * @param bOverite : Boolean field, if true, in case of collision, base value is kept
	 *                   if false, in case of collision, array will be formed keeping the key
	 * Currently not supported the nestification

	 */
	public static JSONObject mergeJSON (JSONObject base, JSONObject toAppend, boolean bOverite)
	throws JSONException{
		logger.debug(" enter mergeJSON LokUtility.mergeJSON() with billnum ");
		
		JSONObject mergedContent = null;
		
		//currently not supporting the nesting
		if(!bOverite){
			throw new UnsupportedOperationException(" Nesting not available");
		}
		
		mergedContent = new JSONObject(base, JSONObject.getNames(base));
		for (String crunchifyKey : JSONObject.getNames(toAppend)) {
			mergedContent.put(crunchifyKey, toAppend.get(crunchifyKey));
		}
		
		return mergedContent;
	}
	
	
	/**
	 * Changes the dateformat fields of the 
	 * JSONObject based on the class object passed
	 * change the values in original object
	 */
	public static void changeDateFormat(Class<?> beanClass, JSONObject obj){
		logger.debug(" enter changeDateFormat LokUtility.changeDateFormat()  ");
		try{
		
		
		for (Field field : beanClass.getDeclaredFields()) {
	         if (field.getType().isAssignableFrom(Date.class)) {
	            
	        	 //get the name of that field
	        	 String key = field.getName();
	        	 
	        	 //get the value from json
	        	 String value = (String)obj.getString(key);
	        	 obj.put(key, value.split(" ")[0]);
	         }
	      }
		}catch(JSONException e){
			logger.debug(" enter changeDateFormat LokUtility.changeDateFormat()  "+e.getMessage());
			e.printStackTrace();
		}

	}
		
}


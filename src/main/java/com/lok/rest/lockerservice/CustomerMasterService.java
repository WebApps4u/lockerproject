package com.lok.rest.lockerservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lok.bean.KeyDetails;

@Path("/lockerservice")
public class CustomerMasterService {

	/**
	 * Return booking details in the form of
	 * json
	 * key would be mapped to the field names to 
	 * populate correct field. 
	 */
	@GET
	@Path("/keydetails/{keynum}")
	public KeyDetails getKeyDetails(@PathParam("keynum") String keyNum){
		
		KeyDetails keyRecord = null;
		try{
			
		}catch(Exception e){
			//log to the logger
		}
		return keyRecord;
	}
}

package com.lok.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmailOutbound implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String objectId;  // Primary key 
	
	@Id
	private String objectType; //table name of above primary key
	
	@Id 
	private String emailType;  // it can be billing, reminder, prereminder etc

	/**
	 * @return the objectId
	 */
	public String getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId the objectId to set
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	/**
	 * @return the objectType
	 */
	public String getObjectType() {
		return objectType;
	}

	/**
	 * @param objectType the objectType to set
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	/**
	 * @return the emailType
	 */
	public String getEmailType() {
		return emailType;
	}

	/**
	 * @param emailType the emailType to set
	 */
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
	
}

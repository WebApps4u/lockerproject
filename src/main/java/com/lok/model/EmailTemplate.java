package com.lok.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmailTemplate implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String EMAILTEMPLATEID;
	
	private String FROMEMAIL;
	
	private String TOLIST;
	
	private String CCLIST;
	
	private String SUBJECT;
	
	private String BODY;
	
	private String DATECREATED;

	/**
	 * @return the eMAILTEMPLATEID
	 */
	public String getEMAILTEMPLATEID() {
		return EMAILTEMPLATEID;
	}

	/**
	 * @param eMAILTEMPLATEID the eMAILTEMPLATEID to set
	 */
	public void setEMAILTEMPLATEID(String eMAILTEMPLATEID) {
		EMAILTEMPLATEID = eMAILTEMPLATEID;
	}

	/**
	 * @return the fROMEMAIL
	 */
	public String getFROMEMAIL() {
		return FROMEMAIL;
	}

	/**
	 * @param fROMEMAIL the fROMEMAIL to set
	 */
	public void setFROMEMAIL(String fROMEMAIL) {
		FROMEMAIL = fROMEMAIL;
	}

	/**
	 * @return the tOLIST
	 */
	public String getTOLIST() {
		return TOLIST;
	}

	/**
	 * @param tOLIST the tOLIST to set
	 */
	public void setTOLIST(String tOLIST) {
		TOLIST = tOLIST;
	}

	/**
	 * @return the cCLIST
	 */
	public String getCCLIST() {
		return CCLIST;
	}

	/**
	 * @param cCLIST the cCLIST to set
	 */
	public void setCCLIST(String cCLIST) {
		CCLIST = cCLIST;
	}

	/**
	 * @return the sUBJECT
	 */
	public String getSUBJECT() {
		return SUBJECT;
	}

	/**
	 * @param sUBJECT the sUBJECT to set
	 */
	public void setSUBJECT(String sUBJECT) {
		SUBJECT = sUBJECT;
	}

	/**
	 * @return the bODY
	 */
	public String getBODY() {
		return BODY;
	}

	/**
	 * @param bODY the bODY to set
	 */
	public void setBODY(String bODY) {
		BODY = bODY;
	}

	/**
	 * @return the dATECREATED
	 */
	public String getDATECREATED() {
		return DATECREATED;
	}

	/**
	 * @param dATECREATED the dATECREATED to set
	 */
	public void setDATECREATED(String dATECREATED) {
		DATECREATED = dATECREATED;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

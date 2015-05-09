package com.lok.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Implementation for Customer_table entity
 * 
 * @author Deepansh
 *
 */
@Entity
public class CustomerDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String CUSTOMERID;
	private String FIRSTNAME;
	private String LASTNAME;
	private String PPPATH;
	private String AADHARPATH;
	private String DLICENSEPATH;
	private String ELECTRICITY;
	private String PHOTOPATH;
	private String SIGNPATH;
	private String EMAILID;
	private String ALTEMAILID;
	private String MOBILENUM;
	private String ALTMOBILENUM;
	private String PHONENUM;
	private String ALTPHONENUM;
	private String ADDRESS1;
	private String ADDRESS2;
	private String ADDRESS3;
	private String CITY;
	private String STATE;
	private String COUNTRY;
	private String PINCODE;
	private String LANDMARK;
	private String CREATEDBY;
	private Date CREATEDDATE;
	private String MODIFIEDBY;
	private Date MODIFIEDDATE;
	private String NOTES;
	private String FOLIONUM;
	/**
	 * @return the cUSTOMERID
	 */
	public String getCUSTOMERID() {
		return CUSTOMERID;
	}
	/**
	 * @param cUSTOMERID the cUSTOMERID to set
	 */
	public void setCUSTOMERID(String cUSTOMERID) {
		CUSTOMERID = cUSTOMERID;
	}
	/**
	 * @return the fIRSTNAME
	 */
	public String getFIRSTNAME() {
		return FIRSTNAME;
	}
	/**
	 * @param fIRSTNAME the fIRSTNAME to set
	 */
	public void setFIRSTNAME(String fIRSTNAME) {
		FIRSTNAME = fIRSTNAME;
	}
	/**
	 * @return the lASTNAME
	 */
	public String getLASTNAME() {
		return LASTNAME;
	}
	/**
	 * @param lASTNAME the lASTNAME to set
	 */
	public void setLASTNAME(String lASTNAME) {
		LASTNAME = lASTNAME;
	}
	/**
	 * @return the pPPATH
	 */
	public String getPPPATH() {
		return PPPATH;
	}
	/**
	 * @param pPPATH the pPPATH to set
	 */
	public void setPPPATH(String pPPATH) {
		PPPATH = pPPATH;
	}
	/**
	 * @return the aADHARPATH
	 */
	public String getAADHARPATH() {
		return AADHARPATH;
	}
	/**
	 * @param aADHARPATH the aADHARPATH to set
	 */
	public void setAADHARPATH(String aADHARPATH) {
		AADHARPATH = aADHARPATH;
	}
	/**
	 * @return the dLICENSEPATH
	 */
	public String getDLICENSEPATH() {
		return DLICENSEPATH;
	}
	/**
	 * @param dLICENSEPATH the dLICENSEPATH to set
	 */
	public void setDLICENSEPATH(String dLICENSEPATH) {
		DLICENSEPATH = dLICENSEPATH;
	}
	/**
	 * @return the eLECTRICITY
	 */
	public String getELECTRICITY() {
		return ELECTRICITY;
	}
	/**
	 * @param eLECTRICITY the eLECTRICITY to set
	 */
	public void setELECTRICITY(String eLECTRICITY) {
		ELECTRICITY = eLECTRICITY;
	}
	/**
	 * @return the pHOTOPATH
	 */
	public String getPHOTOPATH() {
		return PHOTOPATH;
	}
	/**
	 * @param pHOTOPATH the pHOTOPATH to set
	 */
	public void setPHOTOPATH(String pHOTOPATH) {
		PHOTOPATH = pHOTOPATH;
	}
	/**
	 * @return the sIGNPATH
	 */
	public String getSIGNPATH() {
		return SIGNPATH;
	}
	/**
	 * @param sIGNPATH the sIGNPATH to set
	 */
	public void setSIGNPATH(String sIGNPATH) {
		SIGNPATH = sIGNPATH;
	}
	/**
	 * @return the eMAILID
	 */
	public String getEMAILID() {
		return EMAILID;
	}
	/**
	 * @param eMAILID the eMAILID to set
	 */
	public void setEMAILID(String eMAILID) {
		EMAILID = eMAILID;
	}
	/**
	 * @return the aLTEMAILID
	 */
	public String getALTEMAILID() {
		return ALTEMAILID;
	}
	/**
	 * @param aLTEMAILID the aLTEMAILID to set
	 */
	public void setALTEMAILID(String aLTEMAILID) {
		ALTEMAILID = aLTEMAILID;
	}
	/**
	 * @return the mOBILENUM
	 */
	public String getMOBILENUM() {
		return MOBILENUM;
	}
	/**
	 * @param mOBILENUM the mOBILENUM to set
	 */
	public void setMOBILENUM(String mOBILENUM) {
		MOBILENUM = mOBILENUM;
	}
	/**
	 * @return the aLTMOBILENUM
	 */
	public String getALTMOBILENUM() {
		return ALTMOBILENUM;
	}
	/**
	 * @param aLTMOBILENUM the aLTMOBILENUM to set
	 */
	public void setALTMOBILENUM(String aLTMOBILENUM) {
		ALTMOBILENUM = aLTMOBILENUM;
	}
	/**
	 * @return the pHONENUM
	 */
	public String getPHONENUM() {
		return PHONENUM;
	}
	/**
	 * @param pHONENUM the pHONENUM to set
	 */
	public void setPHONENUM(String pHONENUM) {
		PHONENUM = pHONENUM;
	}
	/**
	 * @return the aLTPHONENUM
	 */
	public String getALTPHONENUM() {
		return ALTPHONENUM;
	}
	/**
	 * @param aLTPHONENUM the aLTPHONENUM to set
	 */
	public void setALTPHONENUM(String aLTPHONENUM) {
		ALTPHONENUM = aLTPHONENUM;
	}
	/**
	 * @return the aDDRESS1
	 */
	public String getADDRESS1() {
		return ADDRESS1;
	}
	/**
	 * @param aDDRESS1 the aDDRESS1 to set
	 */
	public void setADDRESS1(String aDDRESS1) {
		ADDRESS1 = aDDRESS1;
	}
	/**
	 * @return the aDDRESS2
	 */
	public String getADDRESS2() {
		return ADDRESS2;
	}
	/**
	 * @param aDDRESS2 the aDDRESS2 to set
	 */
	public void setADDRESS2(String aDDRESS2) {
		ADDRESS2 = aDDRESS2;
	}
	/**
	 * @return the aDDRESS3
	 */
	public String getADDRESS3() {
		return ADDRESS3;
	}
	/**
	 * @param aDDRESS3 the aDDRESS3 to set
	 */
	public void setADDRESS3(String aDDRESS3) {
		ADDRESS3 = aDDRESS3;
	}
	/**
	 * @return the cITY
	 */
	public String getCITY() {
		return CITY;
	}
	/**
	 * @param cITY the cITY to set
	 */
	public void setCITY(String cITY) {
		CITY = cITY;
	}
	/**
	 * @return the sTATE
	 */
	public String getSTATE() {
		return STATE;
	}
	/**
	 * @param sTATE the sTATE to set
	 */
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	/**
	 * @return the cOUNTRY
	 */
	public String getCOUNTRY() {
		return COUNTRY;
	}
	/**
	 * @param cOUNTRY the cOUNTRY to set
	 */
	public void setCOUNTRY(String cOUNTRY) {
		COUNTRY = cOUNTRY;
	}
	/**
	 * @return the pINCODE
	 */
	public String getPINCODE() {
		return PINCODE;
	}
	/**
	 * @param pINCODE the pINCODE to set
	 */
	public void setPINCODE(String pINCODE) {
		PINCODE = pINCODE;
	}
	/**
	 * @return the lANDMARK
	 */
	public String getLANDMARK() {
		return LANDMARK;
	}
	/**
	 * @param lANDMARK the lANDMARK to set
	 */
	public void setLANDMARK(String lANDMARK) {
		LANDMARK = lANDMARK;
	}
	/**
	 * @return the cREATEDBY
	 */
	public String getCREATEDBY() {
		return CREATEDBY;
	}
	/**
	 * @param cREATEDBY the cREATEDBY to set
	 */
	public void setCREATEDBY(String cREATEDBY) {
		CREATEDBY = cREATEDBY;
	}
	/**
	 * @return the cREATEDDATE
	 */
	public Date getCREATEDDATE() {
		return CREATEDDATE;
	}
	/**
	 * @param cREATEDDATE the cREATEDDATE to set
	 */
	public void setCREATEDDATE(Date cREATEDDATE) {
		CREATEDDATE = cREATEDDATE;
	}
	/**
	 * @return the mODIFIEDBY
	 */
	public String getMODIFIEDBY() {
		return MODIFIEDBY;
	}
	/**
	 * @param mODIFIEDBY the mODIFIEDBY to set
	 */
	public void setMODIFIEDBY(String mODIFIEDBY) {
		MODIFIEDBY = mODIFIEDBY;
	}
	/**
	 * @return the mODIFIEDDATE
	 */
	public Date getMODIFIEDDATE() {
		return MODIFIEDDATE;
	}
	/**
	 * @param mODIFIEDDATE the mODIFIEDDATE to set
	 */
	public void setMODIFIEDDATE(Date mODIFIEDDATE) {
		MODIFIEDDATE = mODIFIEDDATE;
	}
	/**
	 * @return the nOTES
	 */
	public String getNOTES() {
		return NOTES;
	}
	/**
	 * @param nOTES the nOTES to set
	 */
	public void setNOTES(String nOTES) {
		NOTES = nOTES;
	}
	/**
	 * @return the fOLIONUM
	 */
	public String getFOLIONUM() {
		return FOLIONUM;
	}
	/**
	 * @param fOLIONUM the fOLIONUM to set
	 */
	public void setFOLIONUM(String fOLIONUM) {
		FOLIONUM = fOLIONUM;
	}
	
	
}

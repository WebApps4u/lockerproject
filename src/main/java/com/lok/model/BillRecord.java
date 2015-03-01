package com.lok.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Implementation for LRNT table entity
 * 
 * @author USER
 *
 */
@Entity
public class BillRecord implements Serializable,Comparable<BillRecord> {

	// Bill number
	@Id
	private String BNO;
	// Bill date
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date BDT;
	// From date of bill
	private Date BFDT;
	// To date of bill
	private Date BTDT;

	// key number for billing
	private String KNO;
	// Booking number
	private String LSNO;
	// release
	private String LRELS;
	// Locker Rent
	private Long LAMT;

	// Previous outstanding
	private Long LOUT;
	// total amount to be paid LAMT+LOUT, current year payment
	private Long LCP;
	// Locker Advance payment
	private Long LADV;
	// Service tax rate
	private Long LSTXR;

	// Service tax amount calcualated
	private Long LSTXA;
	// Receipt number, if there is any advance payment
	private String LRNO;
	// payable amount
	private Long LPYBA;
	// Bill clarify flag
	private String BFLG;

	// Receipt number, will be updated by Receipt workflow
	private String LRCTN;
	// Receipt date
	private Date LRCTD;

	// reminder dates
	// pre reminder date
	private Date PREMD;
	// number of reminders, will be updated by each reminders
	private Long REMN;
	// Reminder 1 date
	private Date REMD1;
	// penalty for 1st reminder
	private Long REMDA1;


	// Reminder 2 date
	private Date REMD2;
	// penalty for 2 reminder
	private Long REMDA2;
	
	// Reminder 3 date
	private Date REMD3;
	// penalty for 3 reminder
	private Long REMDA3;
	
	// Reminder 4 date
	private Date REMD4;
	// penalty for 4 reminder
	private Long REMDA4;
    
	// Reminder 5 date
	private Date REMD5;
	// penalty for 5 reminder
	private Long REMDA5;
	
	// Reminder 6 date
	private Date REMD6;
	// penalty for 6 reminder
	private Long REMDA6;
	
	private static final Long serialVersionUID = 1L;

	public BillRecord() {
		super();
	}
	
	/**
	 * Compare based on the BDT (billing date)
	 */
	public int compareTo(BillRecord objToCompare){
		
		int i = 1;
		
		try{
			if(objToCompare!=null && objToCompare.BDT!=null){
				return this.BDT.compareTo(objToCompare.BDT);
			}
		}catch(Exception e){
			
		}
		return i;
	}

	/**
	 * @return the bNO
	 */
	public String getBNO() {
		return BNO;
	}

	/**
	 * @param bNO the bNO to set
	 */
	public void setBNO(String bNO) {
		BNO = bNO;
	}

	/**
	 * @return the bDT
	 */
	public Date getBDT() {
		return BDT;
	}

	/**
	 * @param bDT the bDT to set
	 */
	public void setBDT(Date bDT) {
		BDT = bDT;
	}

	/**
	 * @return the bFDT
	 */
	public Date getBFDT() {
		return BFDT;
	}

	/**
	 * @param bFDT the bFDT to set
	 */
	public void setBFDT(Date bFDT) {
		BFDT = bFDT;
	}

	/**
	 * @return the bTDT
	 */
	public Date getBTDT() {
		return BTDT;
	}

	/**
	 * @param bTDT the bTDT to set
	 */
	public void setBTDT(Date bTDT) {
		BTDT = bTDT;
	}

	/**
	 * @return the kNO
	 */
	public String getKNO() {
		return KNO;
	}

	/**
	 * @param kNO the kNO to set
	 */
	public void setKNO(String kNO) {
		KNO = kNO;
	}

	/**
	 * @return the lSNO
	 */
	public String getLSNO() {
		return LSNO;
	}

	/**
	 * @param lSNO the lSNO to set
	 */
	public void setLSNO(String lSNO) {
		LSNO = lSNO;
	}

	/**
	 * @return the lRELS
	 */
	public String getLRELS() {
		return LRELS;
	}

	/**
	 * @param lRELS the lRELS to set
	 */
	public void setLRELS(String lRELS) {
		LRELS = lRELS;
	}

	/**
	 * @return the lAMT
	 */
	public Long getLAMT() {
		return LAMT;
	}

	/**
	 * @param lAMT the lAMT to set
	 */
	public void setLAMT(Long lAMT) {
		LAMT = lAMT;
	}

	/**
	 * @return the lOUT
	 */
	public Long getLOUT() {
		return LOUT;
	}

	/**
	 * @param lOUT the lOUT to set
	 */
	public void setLOUT(Long lOUT) {
		LOUT = lOUT;
	}

	/**
	 * @return the lCP
	 */
	public Long getLCP() {
		return LCP;
	}

	/**
	 * @param lCP the lCP to set
	 */
	public void setLCP(Long lCP) {
		LCP = lCP;
	}

	/**
	 * @return the lADV
	 */
	public Long getLADV() {
		return LADV;
	}

	/**
	 * @param lADV the lADV to set
	 */
	public void setLADV(Long lADV) {
		LADV = lADV;
	}

	/**
	 * @return the lSTXR
	 */
	public Long getLSTXR() {
		return LSTXR;
	}

	/**
	 * @param lSTXR the lSTXR to set
	 */
	public void setLSTXR(Long lSTXR) {
		LSTXR = lSTXR;
	}

	/**
	 * @return the lSTXA
	 */
	public Long getLSTXA() {
		return LSTXA;
	}

	/**
	 * @param lSTXA the lSTXA to set
	 */
	public void setLSTXA(Long lSTXA) {
		LSTXA = lSTXA;
	}

	/**
	 * @return the lRNO
	 */
	public String getLRNO() {
		return LRNO;
	}

	/**
	 * @param lRNO the lRNO to set
	 */
	public void setLRNO(String lRNO) {
		LRNO = lRNO;
	}

	/**
	 * @return the lPYBA
	 */
	public Long getLPYBA() {
		return LPYBA;
	}

	/**
	 * @param lPYBA the lPYBA to set
	 */
	public void setLPYBA(Long lPYBA) {
		LPYBA = lPYBA;
	}

	/**
	 * @return the bFLG
	 */
	public String getBFLG() {
		return BFLG;
	}

	/**
	 * @param bFLG the bFLG to set
	 */
	public void setBFLG(String bFLG) {
		BFLG = bFLG;
	}

	/**
	 * @return the lRCTN
	 */
	public String getLRCTN() {
		return LRCTN;
	}

	/**
	 * @param lRCTN the lRCTN to set
	 */
	public void setLRCTN(String lRCTN) {
		LRCTN = lRCTN;
	}

	/**
	 * @return the lRCTD
	 */
	public Date getLRCTD() {
		return LRCTD;
	}

	/**
	 * @param lRCTD the lRCTD to set
	 */
	public void setLRCTD(Date lRCTD) {
		LRCTD = lRCTD;
	}

	/**
	 * @return the pREMD
	 */
	public Date getPREMD() {
		return PREMD;
	}

	/**
	 * @param pREMD the pREMD to set
	 */
	public void setPREMD(Date pREMD) {
		PREMD = pREMD;
	}

	/**
	 * @return the rEMN
	 */
	public Long getREMN() {
		return REMN;
	}

	/**
	 * @param rEMN the rEMN to set
	 */
	public void setREMN(Long rEMN) {
		REMN = rEMN;
	}

	/**
	 * @return the rEMD1
	 */
	public Date getREMD1() {
		return REMD1;
	}

	/**
	 * @param rEMD1 the rEMD1 to set
	 */
	public void setREMD1(Date rEMD1) {
		REMD1 = rEMD1;
	}

	/**
	 * @return the rEMDA1
	 */
	public Long getREMDA1() {
		return REMDA1;
	}

	/**
	 * @param rEMDA1 the rEMDA1 to set
	 */
	public void setREMDA1(Long rEMDA1) {
		REMDA1 = rEMDA1;
	}

	/**
	 * @return the rEMD2
	 */
	public Date getREMD2() {
		return REMD2;
	}

	/**
	 * @param rEMD2 the rEMD2 to set
	 */
	public void setREMD2(Date rEMD2) {
		REMD2 = rEMD2;
	}

	/**
	 * @return the rEMDA2
	 */
	public Long getREMDA2() {
		return REMDA2;
	}

	/**
	 * @param rEMDA2 the rEMDA2 to set
	 */
	public void setREMDA2(Long rEMDA2) {
		REMDA2 = rEMDA2;
	}

	/**
	 * @return the rEMD3
	 */
	public Date getREMD3() {
		return REMD3;
	}

	/**
	 * @param rEMD3 the rEMD3 to set
	 */
	public void setREMD3(Date rEMD3) {
		REMD3 = rEMD3;
	}

	/**
	 * @return the rEMDA3
	 */
	public Long getREMDA3() {
		return REMDA3;
	}

	/**
	 * @param rEMDA3 the rEMDA3 to set
	 */
	public void setREMDA3(Long rEMDA3) {
		REMDA3 = rEMDA3;
	}

	/**
	 * @return the rEMD4
	 */
	public Date getREMD4() {
		return REMD4;
	}

	/**
	 * @param rEMD4 the rEMD4 to set
	 */
	public void setREMD4(Date rEMD4) {
		REMD4 = rEMD4;
	}

	/**
	 * @return the rEMDA4
	 */
	public Long getREMDA4() {
		return REMDA4;
	}

	/**
	 * @param rEMDA4 the rEMDA4 to set
	 */
	public void setREMDA4(Long rEMDA4) {
		REMDA4 = rEMDA4;
	}

	/**
	 * @return the rEMD5
	 */
	public Date getREMD5() {
		return REMD5;
	}

	/**
	 * @param rEMD5 the rEMD5 to set
	 */
	public void setREMD5(Date rEMD5) {
		REMD5 = rEMD5;
	}

	/**
	 * @return the rEMDA5
	 */
	public Long getREMDA5() {
		return REMDA5;
	}

	/**
	 * @param rEMDA5 the rEMDA5 to set
	 */
	public void setREMDA5(Long rEMDA5) {
		REMDA5 = rEMDA5;
	}

	/**
	 * @return the rEMD6
	 */
	public Date getREMD6() {
		return REMD6;
	}

	/**
	 * @param rEMD6 the rEMD6 to set
	 */
	public void setREMD6(Date rEMD6) {
		REMD6 = rEMD6;
	}

	/**
	 * @return the rEMDA6
	 */
	public Long getREMDA6() {
		return REMDA6;
	}

	/**
	 * @param rEMDA6 the rEMDA6 to set
	 */
	public void setREMDA6(Long rEMDA6) {
		REMDA6 = rEMDA6;
	}

	/**
	 * @return the serialversionuid
	 */
	public static Long getSerialversionuid() {
		return serialVersionUID;
	}

}
package com.lok.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.lok.excelutil.DownloadableAsExcel;

/**
 * Implementation for LRNT table entity
 * 
 * @author USER
 *
 */
@Entity
public class BillRecord implements Serializable,Comparable<BillRecord>,DownloadableAsExcel {

	// Bill number
	@Id
	private String BNO;
	// Bill date
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date BDT;
	// From date of bill
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date BFDT;
	// To date of bill
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date BTDT;

	// key number for billing
	private String KNO;
	// Booking number
	private String LSNO;
	// release
	private String LRELS;
	// Locker Rent
	private Double LAMT;

	// Previous outstanding
	private Double LOUT;
	// total amount to be paid LAMT+LOUT, current year payment
	private Double LCP;
	// Locker Advance payment
	private Double LADV;
	// Service tax rate
	private Double LSTXR;

	// Service tax amount calcualated
	private Double LSTXA;
	// Receipt number, if there is any advance payment
	private String LRNO;
	// payable amount
	private Double LPYBA;
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
	private Double REMN;
	// Reminder 1 date
	private Date REMD1;
	// penalty for 1st reminder
	private Double REMDA1 = 0D;


	// Reminder 2 date
	private Date REMD2;
	// penalty for 2 reminder
	private Double REMDA2=0D;
	
	// Reminder 3 date
	private Date REMD3;
	// penalty for 3 reminder
	private Double REMDA3= 0D;
	
	// Reminder 4 date
	private Date REMD4;
	// penalty for 4 reminder
	private Double REMDA4=0D;
    
	// Reminder 5 date
	private Date REMD5;
	// penalty for 5 reminder
	private Double REMDA5=0D;
	
	// Reminder 6 date
	private Date REMD6;
	// penalty for 6 reminder
	private Double REMDA6=0D;
	
	private static final long serialVersionUID = 1L;

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
	public Double getLAMT() {
		return LAMT;
	}

	/**
	 * @param lAMT the lAMT to set
	 */
	public void setLAMT(Double lAMT) {
		LAMT = lAMT;
	}

	/**
	 * @return the lOUT
	 */
	public Double getLOUT() {
		return LOUT;
	}

	/**
	 * @param lOUT the lOUT to set
	 */
	public void setLOUT(Double lOUT) {
		LOUT = lOUT;
	}

	/**
	 * @return the lCP
	 */
	public Double getLCP() {
		return LCP;
	}

	/**
	 * @param lCP the lCP to set
	 */
	public void setLCP(Double lCP) {
		LCP = lCP;
	}

	/**
	 * @return the lADV
	 */
	public Double getLADV() {
		return LADV;
	}

	/**
	 * @param lADV the lADV to set
	 */
	public void setLADV(Double lADV) {
		LADV = lADV;
	}

	/**
	 * @return the lSTXR
	 */
	public Double getLSTXR() {
		return LSTXR;
	}

	/**
	 * @param lSTXR the lSTXR to set
	 */
	public void setLSTXR(Double lSTXR) {
		LSTXR = lSTXR;
	}

	/**
	 * @return the lSTXA
	 */
	public Double getLSTXA() {
		return LSTXA;
	}

	/**
	 * @param lSTXA the lSTXA to set
	 */
	public void setLSTXA(Double lSTXA) {
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
	public Double getLPYBA() {
		return LPYBA;
	}

	/**
	 * @param lPYBA the lPYBA to set
	 */
	public void setLPYBA(Double lPYBA) {
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
	public Double getREMN() {
		return REMN;
	}

	/**
	 * @param rEMN the rEMN to set
	 */
	public void setREMN(Double rEMN) {
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
	public Double getREMDA1() {
		return REMDA1;
	}

	/**
	 * @param rEMDA1 the rEMDA1 to set
	 */
	public void setREMDA1(Double rEMDA1) {
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
	public Double getREMDA2() {
		return REMDA2;
	}

	/**
	 * @param rEMDA2 the rEMDA2 to set
	 */
	public void setREMDA2(Double rEMDA2) {
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
	public Double getREMDA3() {
		return REMDA3;
	}

	/**
	 * @param rEMDA3 the rEMDA3 to set
	 */
	public void setREMDA3(Double rEMDA3) {
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
	public Double getREMDA4() {
		return REMDA4;
	}

	/**
	 * @param rEMDA4 the rEMDA4 to set
	 */
	public void setREMDA4(Double rEMDA4) {
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
	public Double getREMDA5() {
		return REMDA5;
	}

	/**
	 * @param rEMDA5 the rEMDA5 to set
	 */
	public void setREMDA5(Double rEMDA5) {
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
	public Double getREMDA6() {
		return REMDA6;
	}

	/**
	 * @param rEMDA6 the rEMDA6 to set
	 */
	public void setREMDA6(Double rEMDA6) {
		REMDA6 = rEMDA6;
	}

	/**
	 * @return the serialversionuid
	 */
	public static Long getSerialversionuid() {
		return serialVersionUID;
	}

}
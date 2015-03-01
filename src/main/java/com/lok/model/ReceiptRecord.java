package com.lok.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Implementation of Receipt (RCT) table
 * 
 * @author USER
 *
 */

@Entity
public class ReceiptRecord implements Serializable {

	@Id
	// Receipt tx number
	private String RCTN;
	// Receipt tx date
	private Date RCTD;
	// Key number for the Receipt
	private String RKNO;

	// Receipt locker Serial num
	private String RLSNO;
	// Locker rent
	private Long RRNT;
	// Advance Payment
	private Long RADVP;

	// previous outstanding
	private Long RPOUT;
	// Interest
	private Long RINT;
	// incidental charges
	private Long RINC;

	// Bank charges
	private Long RBC;
	// Application Fees
	private Long RAPF;
	//legal fee
	private Long RGLF;
	//additional advance (for future)
	private Long RADV;
	
	//advance rent
	private Long RADVRT;
	//advance tax
	private Long RADVST;
	
	//access charges
	private Long RACH;
	//new outstanding
	private Long RCOUT;
	//suspense
	private Long RSUS;
	//breaking charges
	private Long BRKCH;

	//storage access num
	private String RSAN;
	//storage access tax rate
	private Long STAXR;
	//storage access tax amount
	private Long STAXA;
	//Grand total
	private Long RGTOT;

	//type of payment (cash, cheque..)
	private String RTYP;
	//cheque num
	private String RCHDN;
	//cheque date
	private Date RCHD;
	//bank name (for cheque payment)
	private String RBKN;
	//paid amount
	private Long RCHA;
    
	//remarks
	private String RREM;
	//receipt from date
	private Date RFDT;
	//receipt to date
	private Date RTDT;
	//receipt bill number
	private String RBNO;
	//bill date
	private Date RBND;
	
	private static final Long serialVersionUID = 1L;

	public ReceiptRecord() {
		super();
	}

	/**
	 * @return the rCTN
	 */
	public String getRCTN() {
		return RCTN;
	}

	/**
	 * @param rCTN the rCTN to set
	 */
	public void setRCTN(String rCTN) {
		RCTN = rCTN;
	}

	/**
	 * @return the rCTD
	 */
	public Date getRCTD() {
		return RCTD;
	}

	/**
	 * @param rCTD the rCTD to set
	 */
	public void setRCTD(Date rCTD) {
		RCTD = rCTD;
	}

	/**
	 * @return the rKNO
	 */
	public String getRKNO() {
		return RKNO;
	}

	/**
	 * @param rKNO the rKNO to set
	 */
	public void setRKNO(String rKNO) {
		RKNO = rKNO;
	}

	/**
	 * @return the rLSNO
	 */
	public String getRLSNO() {
		return RLSNO;
	}

	/**
	 * @param rLSNO the rLSNO to set
	 */
	public void setRLSNO(String rLSNO) {
		RLSNO = rLSNO;
	}

	/**
	 * @return the rRNT
	 */
	public Long getRRNT() {
		return RRNT;
	}

	/**
	 * @param rRNT the rRNT to set
	 */
	public void setRRNT(Long rRNT) {
		RRNT = rRNT;
	}

	/**
	 * @return the rADVP
	 */
	public Long getRADVP() {
		return RADVP;
	}

	/**
	 * @param rADVP the rADVP to set
	 */
	public void setRADVP(Long rADVP) {
		RADVP = rADVP;
	}

	/**
	 * @return the rPOUT
	 */
	public Long getRPOUT() {
		return RPOUT;
	}

	/**
	 * @param rPOUT the rPOUT to set
	 */
	public void setRPOUT(Long rPOUT) {
		RPOUT = rPOUT;
	}

	/**
	 * @return the rINT
	 */
	public Long getRINT() {
		return RINT;
	}

	/**
	 * @param rINT the rINT to set
	 */
	public void setRINT(Long rINT) {
		RINT = rINT;
	}

	/**
	 * @return the rINC
	 */
	public Long getRINC() {
		return RINC;
	}

	/**
	 * @param rINC the rINC to set
	 */
	public void setRINC(Long rINC) {
		RINC = rINC;
	}

	/**
	 * @return the rBC
	 */
	public Long getRBC() {
		return RBC;
	}

	/**
	 * @param rBC the rBC to set
	 */
	public void setRBC(Long rBC) {
		RBC = rBC;
	}

	/**
	 * @return the rAPF
	 */
	public Long getRAPF() {
		return RAPF;
	}

	/**
	 * @param rAPF the rAPF to set
	 */
	public void setRAPF(Long rAPF) {
		RAPF = rAPF;
	}

	/**
	 * @return the rGLF
	 */
	public Long getRGLF() {
		return RGLF;
	}

	/**
	 * @param rGLF the rGLF to set
	 */
	public void setRGLF(Long rGLF) {
		RGLF = rGLF;
	}

	/**
	 * @return the rADV
	 */
	public Long getRADV() {
		return RADV;
	}

	/**
	 * @param rADV the rADV to set
	 */
	public void setRADV(Long rADV) {
		RADV = rADV;
	}

	/**
	 * @return the rADVRT
	 */
	public Long getRADVRT() {
		return RADVRT;
	}

	/**
	 * @param rADVRT the rADVRT to set
	 */
	public void setRADVRT(Long rADVRT) {
		RADVRT = rADVRT;
	}

	/**
	 * @return the rADVST
	 */
	public Long getRADVST() {
		return RADVST;
	}

	/**
	 * @param rADVST the rADVST to set
	 */
	public void setRADVST(Long rADVST) {
		RADVST = rADVST;
	}

	/**
	 * @return the rACH
	 */
	public Long getRACH() {
		return RACH;
	}

	/**
	 * @param rACH the rACH to set
	 */
	public void setRACH(Long rACH) {
		RACH = rACH;
	}

	/**
	 * @return the rCOUT
	 */
	public Long getRCOUT() {
		return RCOUT;
	}

	/**
	 * @param rCOUT the rCOUT to set
	 */
	public void setRCOUT(Long rCOUT) {
		RCOUT = rCOUT;
	}

	/**
	 * @return the rSUS
	 */
	public Long getRSUS() {
		return RSUS;
	}

	/**
	 * @param rSUS the rSUS to set
	 */
	public void setRSUS(Long rSUS) {
		RSUS = rSUS;
	}

	/**
	 * @return the bRKCH
	 */
	public Long getBRKCH() {
		return BRKCH;
	}

	/**
	 * @param bRKCH the bRKCH to set
	 */
	public void setBRKCH(Long bRKCH) {
		BRKCH = bRKCH;
	}

	/**
	 * @return the rSAN
	 */
	public String getRSAN() {
		return RSAN;
	}

	/**
	 * @param rSAN the rSAN to set
	 */
	public void setRSAN(String rSAN) {
		RSAN = rSAN;
	}

	/**
	 * @return the sTAXR
	 */
	public Long getSTAXR() {
		return STAXR;
	}

	/**
	 * @param sTAXR the sTAXR to set
	 */
	public void setSTAXR(Long sTAXR) {
		STAXR = sTAXR;
	}

	/**
	 * @return the sTAXA
	 */
	public Long getSTAXA() {
		return STAXA;
	}

	/**
	 * @param sTAXA the sTAXA to set
	 */
	public void setSTAXA(Long sTAXA) {
		STAXA = sTAXA;
	}

	/**
	 * @return the rGTOT
	 */
	public Long getRGTOT() {
		return RGTOT;
	}

	/**
	 * @param rGTOT the rGTOT to set
	 */
	public void setRGTOT(Long rGTOT) {
		RGTOT = rGTOT;
	}

	/**
	 * @return the rTYP
	 */
	public String getRTYP() {
		return RTYP;
	}

	/**
	 * @param rTYP the rTYP to set
	 */
	public void setRTYP(String rTYP) {
		RTYP = rTYP;
	}

	/**
	 * @return the rCHDN
	 */
	public String getRCHDN() {
		return RCHDN;
	}

	/**
	 * @param rCHDN the rCHDN to set
	 */
	public void setRCHDN(String rCHDN) {
		RCHDN = rCHDN;
	}

	/**
	 * @return the rCHD
	 */
	public Date getRCHD() {
		return RCHD;
	}

	/**
	 * @param rCHD the rCHD to set
	 */
	public void setRCHD(Date rCHD) {
		RCHD = rCHD;
	}

	/**
	 * @return the rBKN
	 */
	public String getRBKN() {
		return RBKN;
	}

	/**
	 * @param rBKN the rBKN to set
	 */
	public void setRBKN(String rBKN) {
		RBKN = rBKN;
	}

	/**
	 * @return the rCHA
	 */
	public Long getRCHA() {
		return RCHA;
	}

	/**
	 * @param rCHA the rCHA to set
	 */
	public void setRCHA(Long rCHA) {
		RCHA = rCHA;
	}

	/**
	 * @return the rREM
	 */
	public String getRREM() {
		return RREM;
	}

	/**
	 * @param rREM the rREM to set
	 */
	public void setRREM(String rREM) {
		RREM = rREM;
	}

	/**
	 * @return the rFDT
	 */
	public Date getRFDT() {
		return RFDT;
	}

	/**
	 * @param rFDT the rFDT to set
	 */
	public void setRFDT(Date rFDT) {
		RFDT = rFDT;
	}

	/**
	 * @return the rTDT
	 */
	public Date getRTDT() {
		return RTDT;
	}

	/**
	 * @param rTDT the rTDT to set
	 */
	public void setRTDT(Date rTDT) {
		RTDT = rTDT;
	}

	/**
	 * @return the rBNO
	 */
	public String getRBNO() {
		return RBNO;
	}

	/**
	 * @param rBNO the rBNO to set
	 */
	public void setRBNO(String rBNO) {
		RBNO = rBNO;
	}

	/**
	 * @return the rBND
	 */
	public Date getRBND() {
		return RBND;
	}

	/**
	 * @param rBND the rBND to set
	 */
	public void setRBND(Date rBND) {
		RBND = rBND;
	}

	/**
	 * @return the serialversionuid
	 */
	public static Long getSerialversionuid() {
		return serialVersionUID;
	}

}
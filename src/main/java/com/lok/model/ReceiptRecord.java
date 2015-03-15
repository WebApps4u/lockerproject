package com.lok.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Implementation of Receipt (RCT) table
 * 
 * @author USER
 *
 */

@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
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
	private Double RRNT;
	// Advance Payment
	private Double RADVP;

	// previous outstanding
	private Double RPOUT;
	// Interest
	private Double RINT;
	// incidental charges
	private Double RINC;

	// Bank charges
	private Double RBC;
	// Application Fees
	private Double RAPF;
	//legal fee
	private Double RGLF;
	//additional advance (for future)
	private Double RADV;
	
	//advance rent
	private Double RADVRT;
	//advance tax
	private Double RADVST;
	
	//access charges
	private Double RACH;
	//new outstanding
	private Double RCOUT;
	//suspense
	private Double RSUS;
	//breaking charges
	private Double BRKCH;

	//storage access num
	private String RSAN;
	//storage access tax rate
	private Double STAXR;
	//storage access tax amount
	private Double STAXA;
	//Grand total
	private Double RGTOT;

	//type of payment (cash, cheque..)
	private String RTYP;
	//cheque num
	private String RCHDN;
	//cheque date
	private Date RCHD;
	//bank name (for cheque payment)
	private String RBKN;
	//paid amount
	private Double RCHA;
    
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
	public Double getRRNT() {
		return RRNT;
	}

	/**
	 * @param rRNT the rRNT to set
	 */
	public void setRRNT(Double rRNT) {
		RRNT = rRNT;
	}

	/**
	 * @return the rADVP
	 */
	public Double getRADVP() {
		return RADVP;
	}

	/**
	 * @param rADVP the rADVP to set
	 */
	public void setRADVP(Double rADVP) {
		RADVP = rADVP;
	}

	/**
	 * @return the rPOUT
	 */
	public Double getRPOUT() {
		return RPOUT;
	}

	/**
	 * @param rPOUT the rPOUT to set
	 */
	public void setRPOUT(Double rPOUT) {
		RPOUT = rPOUT;
	}

	/**
	 * @return the rINT
	 */
	public Double getRINT() {
		return RINT;
	}

	/**
	 * @param rINT the rINT to set
	 */
	public void setRINT(Double rINT) {
		RINT = rINT;
	}

	/**
	 * @return the rINC
	 */
	public Double getRINC() {
		return RINC;
	}

	/**
	 * @param rINC the rINC to set
	 */
	public void setRINC(Double rINC) {
		RINC = rINC;
	}

	/**
	 * @return the rBC
	 */
	public Double getRBC() {
		return RBC;
	}

	/**
	 * @param rBC the rBC to set
	 */
	public void setRBC(Double rBC) {
		RBC = rBC;
	}

	/**
	 * @return the rAPF
	 */
	public Double getRAPF() {
		return RAPF;
	}

	/**
	 * @param rAPF the rAPF to set
	 */
	public void setRAPF(Double rAPF) {
		RAPF = rAPF;
	}

	/**
	 * @return the rGLF
	 */
	public Double getRGLF() {
		return RGLF;
	}

	/**
	 * @param rGLF the rGLF to set
	 */
	public void setRGLF(Double rGLF) {
		RGLF = rGLF;
	}

	/**
	 * @return the rADV
	 */
	public Double getRADV() {
		return RADV;
	}

	/**
	 * @param rADV the rADV to set
	 */
	public void setRADV(Double rADV) {
		RADV = rADV;
	}

	/**
	 * @return the rADVRT
	 */
	public Double getRADVRT() {
		return RADVRT;
	}

	/**
	 * @param rADVRT the rADVRT to set
	 */
	public void setRADVRT(Double rADVRT) {
		RADVRT = rADVRT;
	}

	/**
	 * @return the rADVST
	 */
	public Double getRADVST() {
		return RADVST;
	}

	/**
	 * @param rADVST the rADVST to set
	 */
	public void setRADVST(Double rADVST) {
		RADVST = rADVST;
	}

	/**
	 * @return the rACH
	 */
	public Double getRACH() {
		return RACH;
	}

	/**
	 * @param rACH the rACH to set
	 */
	public void setRACH(Double rACH) {
		RACH = rACH;
	}

	/**
	 * @return the rCOUT
	 */
	public Double getRCOUT() {
		return RCOUT;
	}

	/**
	 * @param rCOUT the rCOUT to set
	 */
	public void setRCOUT(Double rCOUT) {
		RCOUT = rCOUT;
	}

	/**
	 * @return the rSUS
	 */
	public Double getRSUS() {
		return RSUS;
	}

	/**
	 * @param rSUS the rSUS to set
	 */
	public void setRSUS(Double rSUS) {
		RSUS = rSUS;
	}

	/**
	 * @return the bRKCH
	 */
	public Double getBRKCH() {
		return BRKCH;
	}

	/**
	 * @param bRKCH the bRKCH to set
	 */
	public void setBRKCH(Double bRKCH) {
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
	public Double getSTAXR() {
		return STAXR;
	}

	/**
	 * @param sTAXR the sTAXR to set
	 */
	public void setSTAXR(Double sTAXR) {
		STAXR = sTAXR;
	}

	/**
	 * @return the sTAXA
	 */
	public Double getSTAXA() {
		return STAXA;
	}

	/**
	 * @param sTAXA the sTAXA to set
	 */
	public void setSTAXA(Double sTAXA) {
		STAXA = sTAXA;
	}

	/**
	 * @return the rGTOT
	 */
	public Double getRGTOT() {
		return RGTOT;
	}

	/**
	 * @param rGTOT the rGTOT to set
	 */
	public void setRGTOT(Double rGTOT) {
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
	public Double getRCHA() {
		return RCHA;
	}

	/**
	 * @param rCHA the rCHA to set
	 */
	public void setRCHA(Double rCHA) {
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
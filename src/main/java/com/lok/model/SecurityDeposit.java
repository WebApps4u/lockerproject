package com.lok.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SecurityDeposit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String SKNO;
	private String SLSNO;
	
	@Id
	private String SRCN;
	private Date   SRCD;
	private String SRCMT;
	private String SPTYP;
	private Double SCAMT;
	private Double SBAMT;
	private String SCHDN;
	private Date SCHDD;
	private String SBKN;
	private String SREM;
	private String REMK;
	/**
	 * @return the sKNO
	 */
	public String getSKNO() {
		return SKNO;
	}
	/**
	 * @param sKNO the sKNO to set
	 */
	public void setSKNO(String sKNO) {
		SKNO = sKNO;
	}
	/**
	 * @return the sLSNO
	 */
	public String getSLSNO() {
		return SLSNO;
	}
	/**
	 * @param sLSNO the sLSNO to set
	 */
	public void setSLSNO(String sLSNO) {
		SLSNO = sLSNO;
	}
	/**
	 * @return the sRCN
	 */
	public String getSRCN() {
		return SRCN;
	}
	/**
	 * @param sRCN the sRCN to set
	 */
	public void setSRCN(String sRCN) {
		SRCN = sRCN;
	}
	/**
	 * @return the sRCD
	 */
	public Date getSRCD() {
		return SRCD;
	}
	/**
	 * @param sRCD the sRCD to set
	 */
	public void setSRCD(Date sRCD) {
		SRCD = sRCD;
	}
	/**
	 * @return the sRCMT
	 */
	public String getSRCMT() {
		return SRCMT;
	}
	/**
	 * @param sRCMT the sRCMT to set
	 */
	public void setSRCMT(String sRCMT) {
		SRCMT = sRCMT;
	}
	/**
	 * @return the sPTYP
	 */
	public String getSPTYP() {
		return SPTYP;
	}
	/**
	 * @param sPTYP the sPTYP to set
	 */
	public void setSPTYP(String sPTYP) {
		SPTYP = sPTYP;
	}
	/**
	 * @return the sCAMT
	 */
	public Double getSCAMT() {
		return SCAMT;
	}
	/**
	 * @param sCAMT the sCAMT to set
	 */
	public void setSCAMT(Double sCAMT) {
		SCAMT = sCAMT;
	}
	/**
	 * @return the sBAMT
	 */
	public Double getSBAMT() {
		return SBAMT;
	}
	/**
	 * @param sBAMT the sBAMT to set
	 */
	public void setSBAMT(Double sBAMT) {
		SBAMT = sBAMT;
	}
	/**
	 * @return the sCHDN
	 */
	public String getSCHDN() {
		return SCHDN;
	}
	/**
	 * @param sCHDN the sCHDN to set
	 */
	public void setSCHDN(String sCHDN) {
		SCHDN = sCHDN;
	}
	/**
	 * @return the sCHDD
	 */
	public Date getSCHDD() {
		return SCHDD;
	}
	/**
	 * @param sCHDD the sCHDD to set
	 */
	public void setSCHDD(Date sCHDD) {
		SCHDD = sCHDD;
	}
	/**
	 * @return the sBKN
	 */
	public String getSBKN() {
		return SBKN;
	}
	/**
	 * @param sBKN the sBKN to set
	 */
	public void setSBKN(String sBKN) {
		SBKN = sBKN;
	}
	/**
	 * @return the sREM
	 */
	public String getSREM() {
		return SREM;
	}
	/**
	 * @param sREM the sREM to set
	 */
	public void setSREM(String sREM) {
		SREM = sREM;
	}
	/**
	 * @return the rEMK
	 */
	public String getREMK() {
		return REMK;
	}
	/**
	 * @param rEMK the rEMK to set
	 */
	public void setREMK(String rEMK) {
		REMK = rEMK;
	}
	
}

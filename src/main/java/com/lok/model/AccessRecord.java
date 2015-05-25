package com.lok.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.lok.excelutil.DownloadableAsExcel;

@Entity
public class AccessRecord implements Serializable, DownloadableAsExcel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ACCESSRECORDID;

	private String AKNO;
	private String ALNO;
	private String ALSNO;
	private String ANM;
	private Date ATD;
	private String AFT;
	private Double ATT;
	private String ATTB;
	private String AREM;

	/**
	 * @return the aCCESSRECORDID
	 */
	public Integer getACCESSRECORDID() {
		return ACCESSRECORDID;
	}

	/**
	 * @param aCCESSRECORDID
	 *            the aCCESSRECORDID to set
	 */
	public void setACCESSRECORDID(Integer aCCESSRECORDID) {
		ACCESSRECORDID = aCCESSRECORDID;
	}

	/**
	 * @return the aKNO
	 */
	public String getAKNO() {
		return AKNO;
	}

	/**
	 * @param aKNO
	 *            the aKNO to set
	 */
	public void setAKNO(String aKNO) {
		AKNO = aKNO;
	}

	/**
	 * @return the aLNO
	 */
	public String getALNO() {
		return ALNO;
	}

	/**
	 * @param aLNO
	 *            the aLNO to set
	 */
	public void setALNO(String aLNO) {
		ALNO = aLNO;
	}

	/**
	 * @return the aLSNO
	 */
	public String getALSNO() {
		return ALSNO;
	}

	/**
	 * @param aLSNO
	 *            the aLSNO to set
	 */
	public void setALSNO(String aLSNO) {
		ALSNO = aLSNO;
	}

	/**
	 * @return the aNM
	 */
	public String getANM() {
		return ANM;
	}

	/**
	 * @param aNM
	 *            the aNM to set
	 */
	public void setANM(String aNM) {
		ANM = aNM;
	}

	/**
	 * @return the aTD
	 */
	public Date getATD() {
		return ATD;
	}

	/**
	 * @param aTD
	 *            the aTD to set
	 */
	public void setATD(Date aTD) {
		ATD = aTD;
	}

	/**
	 * @return the aFT
	 */
	public String getAFT() {
		return AFT;
	}

	/**
	 * @param aFT
	 *            the aFT to set
	 */
	public void setAFT(String aFT) {
		AFT = aFT;
	}

	/**
	 * @return the aTT
	 */
	public Double getATT() {
		return ATT;
	}

	/**
	 * @param aTT
	 *            the aTT to set
	 */
	public void setATT(Double aTT) {
		ATT = aTT;
	}

	/**
	 * @return the aTTB
	 */
	public String getATTB() {
		return ATTB;
	}

	/**
	 * @param aTTB
	 *            the aTTB to set
	 */
	public void setATTB(String aTTB) {
		ATTB = aTTB;
	}

	/**
	 * @return the aREM
	 */
	public String getAREM() {
		return AREM;
	}

	/**
	 * @param aREM
	 *            the aREM to set
	 */
	public void setAREM(String aREM) {
		AREM = aREM;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

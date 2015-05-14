package com.lok.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.Double;
import java.lang.String;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ForeignKey;

/**
 * Entity implementation class for Entity: PartyRecord
 *
 */
@Entity
@IdClass(PartyRecordPK.class)
public class PartyRecord implements Serializable {
		
	//key number
	//@Id 
	private String KNO;
	//locker number
	private String LNO;
	//Locker Rent due date
	private Date LRDD;
	//Release status
	private String RELS;
	//release date
	private Date RELSD;
	//old booking number
	private String LSNO2;
	//New booking number
	@Id
	private String LSNO;
	//
	private Date LSDT;
	//Locker Rent
	private Double LOKR;
	//locker deposit advance
	private Double LSDA;
	//Previous outstanding
	private Double POA;
	//
	private Double LPA;
	//Advance payment
	private Double PCRA;
	//Advance tax
	private Double PCRAST;
	//Last Receipt number
	private String LRNO;
	//Last receipt number
	private Date LRND;
	//
	private String RBOX;
	//folio number
	private String FLON;
	//Name initial, Mr. Mrs.
	private String PNM1;
	//first name
	private String PNM2;
	//last name
	private String PNM3;
	//Second user name
	private String PNM4;
	//third user name
	private String PNM5;
	//deputy user
	private String PNM6D;
	//1st user address, line 1
	private String PAD1;
	//1st user address, line 2
	private String PAD2;
	//1st user address, line 3
	private String PAD3;
	//1st user address, line 4
	private String PAD4;
	//private phone number
	//comma seperated in original data, thus keeping it to String
	private String PHN;
	//old locker rent
	private Double OLOKR;
	//OBSELETE
	private String ACSN;
	//Email id
	private String EMAILID;
	//Stop Billing - Y or N
	private String STPBL;
	//stop billing date
	private Date STPBD;
	//User code- logged in user
	private Double PUCD;
	//2nd user address, line 1
	private String PAD41;
	//2nd user address, line 2
	private String PAD42;
	//2nd user address, line 3
	private String PAD43;
	//2nd user address, line 4
	private String PAD44;
	//3rd user address, line 1
	private String PAD51;
	//3rd user address, line 2
	private String PAD52;
	//3rd user address, line 3
	private String PAD53;
	//3rd user address, line 4
	private String PAD54;
	//1st user KYC doc1
	private String KYC11;
	//1st user KYC doc12
	private String KYC12;
	//1st user KYC doc13
	private String KYC13;
	//1st user KYC doc14
	private String KYC14;
	//2nd user KYC doc1
	private String KYC21;
	//2nd user KYC doc1
	private String KYC22;
	//2nd user KYC doc1
	private String KYC23;
	//2nd user KYC doc1
	private String KYC24;
	//3rd user KYC doc1
	private String KYC31;
	//3rd user KYC doc1
	private String KYC32;
	//3rd user KYC doc1
	private String KYC33;
	//3rd user KYC doc1
	private String KYC34;
	//4th user KYC doc1
	private String KYC41;
	//4th user KYC doc1
	private String KYC42;
	//4th user KYC doc1
	private String KYC43;
	//4th user KYC doc1
	private String KYC44;
	//remarks
	private String REMARKS;
	
	//added for KYC documents, add customer
	private String FIRSTCUSTOMER;
	
	private String SECONDCUSTOMER;
	private String THIRDCUSTOMER;
					
	private static final Long serialVersionUID = 1L;

	public PartyRecord() {
		super();
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
	 * @return the lNO
	 */
	public String getLNO() {
		return LNO;
	}

	/**
	 * @param lNO the lNO to set
	 */
	public void setLNO(String lNO) {
		LNO = lNO;
	}

	/**
	 * @return the lRDD
	 */
	public Date getLRDD() {
		return LRDD;
	}

	/**
	 * @param lRDD the lRDD to set
	 */
	public void setLRDD(Date lRDD) {
		LRDD = lRDD;
	}

	/**
	 * @return the rELS
	 */
	public String getRELS() {
		return RELS;
	}

	/**
	 * @param rELS the rELS to set
	 */
	public void setRELS(String rELS) {
		RELS = rELS;
	}

	/**
	 * @return the rELSD
	 */
	public Date getRELSD() {
		return RELSD;
	}

	/**
	 * @param rELSD the rELSD to set
	 */
	public void setRELSD(Date rELSD) {
		RELSD = rELSD;
	}

	/**
	 * @return the lSNO2
	 */
	public String getLSNO2() {
		return LSNO2;
	}

	/**
	 * @param lSNO2 the lSNO2 to set
	 */
	public void setLSNO2(String lSNO2) {
		LSNO2 = lSNO2;
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
	 * @return the lSDT
	 */
	public Date getLSDT() {
		return LSDT;
	}

	/**
	 * @param lSDT the lSDT to set
	 */
	public void setLSDT(Date lSDT) {
		LSDT = lSDT;
	}

	/**
	 * @return the lOKR
	 */
	public Double getLOKR() {
		return LOKR;
	}

	/**
	 * @param lOKR the lOKR to set
	 */
	public void setLOKR(Double lOKR) {
		LOKR = lOKR;
	}

	/**
	 * @return the lSDA
	 */
	public Double getLSDA() {
		return LSDA;
	}

	/**
	 * @param lSDA the lSDA to set
	 */
	public void setLSDA(Double lSDA) {
		LSDA = lSDA;
	}

	/**
	 * @return the pOA
	 */
	public Double getPOA() {
		return POA;
	}

	/**
	 * @param pOA the pOA to set
	 */
	public void setPOA(Double pOA) {
		POA = pOA;
	}

	/**
	 * @return the lPA
	 */
	public Double getLPA() {
		return LPA;
	}

	/**
	 * @param lPA the lPA to set
	 */
	public void setLPA(Double lPA) {
		LPA = lPA;
	}

	/**
	 * @return the pCRA
	 */
	public Double getPCRA() {
		return PCRA;
	}

	/**
	 * @param pCRA the pCRA to set
	 */
	public void setPCRA(Double pCRA) {
		PCRA = pCRA;
	}

	/**
	 * @return the pCRAST
	 */
	public Double getPCRAST() {
		return PCRAST;
	}

	/**
	 * @param pCRAST the pCRAST to set
	 */
	public void setPCRAST(Double pCRAST) {
		PCRAST = pCRAST;
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
	 * @return the lRND
	 */
	public Date getLRND() {
		return LRND;
	}

	/**
	 * @param lRND the lRND to set
	 */
	public void setLRND(Date lRND) {
		LRND = lRND;
	}

	/**
	 * @return the rBOX
	 */
	public String getRBOX() {
		return RBOX;
	}

	/**
	 * @param rBOX the rBOX to set
	 */
	public void setRBOX(String rBOX) {
		RBOX = rBOX;
	}

	/**
	 * @return the fLON
	 */
	public String getFLON() {
		return FLON;
	}

	/**
	 * @param fLON the fLON to set
	 */
	public void setFLON(String fLON) {
		FLON = fLON;
	}

	/**
	 * @return the pNM1
	 */
	public String getPNM1() {
		return PNM1;
	}

	/**
	 * @param pNM1 the pNM1 to set
	 */
	public void setPNM1(String pNM1) {
		PNM1 = pNM1;
	}

	/**
	 * @return the pNM2
	 */
	public String getPNM2() {
		return PNM2;
	}

	/**
	 * @param pNM2 the pNM2 to set
	 */
	public void setPNM2(String pNM2) {
		PNM2 = pNM2;
	}

	/**
	 * @return the pNM3
	 */
	public String getPNM3() {
		return PNM3;
	}

	/**
	 * @param pNM3 the pNM3 to set
	 */
	public void setPNM3(String pNM3) {
		PNM3 = pNM3;
	}

	/**
	 * @return the pNM4
	 */
	public String getPNM4() {
		return PNM4;
	}

	/**
	 * @param pNM4 the pNM4 to set
	 */
	public void setPNM4(String pNM4) {
		PNM4 = pNM4;
	}

	/**
	 * @return the pNM5
	 */
	public String getPNM5() {
		return PNM5;
	}

	/**
	 * @param pNM5 the pNM5 to set
	 */
	public void setPNM5(String pNM5) {
		PNM5 = pNM5;
	}

	/**
	 * @return the pNM6D
	 */
	public String getPNM6D() {
		return PNM6D;
	}

	/**
	 * @param pNM6D the pNM6D to set
	 */
	public void setPNM6D(String pNM6D) {
		PNM6D = pNM6D;
	}

	/**
	 * @return the pAD1
	 */
	public String getPAD1() {
		return PAD1;
	}

	/**
	 * @param pAD1 the pAD1 to set
	 */
	public void setPAD1(String pAD1) {
		PAD1 = pAD1;
	}

	/**
	 * @return the pAD2
	 */
	public String getPAD2() {
		return PAD2;
	}

	/**
	 * @param pAD2 the pAD2 to set
	 */
	public void setPAD2(String pAD2) {
		PAD2 = pAD2;
	}

	/**
	 * @return the pAD3
	 */
	public String getPAD3() {
		return PAD3;
	}

	/**
	 * @param pAD3 the pAD3 to set
	 */
	public void setPAD3(String pAD3) {
		PAD3 = pAD3;
	}

	/**
	 * @return the pAD4
	 */
	public String getPAD4() {
		return PAD4;
	}

	/**
	 * @param pAD4 the pAD4 to set
	 */
	public void setPAD4(String pAD4) {
		PAD4 = pAD4;
	}

	/**
	 * @return the pHN
	 */
	public String getPHN() {
		return PHN;
	}

	/**
	 * @param pHN the pHN to set
	 */
	public void setPHN(String pHN) {
		PHN = pHN;
	}

	/**
	 * @return the oLOKR
	 */
	public Double getOLOKR() {
		return OLOKR;
	}

	/**
	 * @param oLOKR the oLOKR to set
	 */
	public void setOLOKR(Double oLOKR) {
		OLOKR = oLOKR;
	}

	/**
	 * @return the aCSN
	 */
	public String getACSN() {
		return ACSN;
	}

	/**
	 * @param aCSN the aCSN to set
	 */
	public void setACSN(String aCSN) {
		ACSN = aCSN;
	}

	/**
	 * @return the email
	 */
	public String getEMAILID() {
		return EMAILID;
	}

	/**
	 * @param email the email to set
	 */
	public void setEMAILID(String emailId) {
		this.EMAILID = emailId;
	}

	/**
	 * @return the sTPBL
	 */
	public String getSTPBL() {
		return STPBL;
	}

	/**
	 * @param sTPBL the sTPBL to set
	 */
	public void setSTPBL(String sTPBL) {
		STPBL = sTPBL;
	}

	/**
	 * @return the sTPBD
	 */
	public Date getSTPBD() {
		return STPBD;
	}

	/**
	 * @param sTPBD the sTPBD to set
	 */
	public void setSTPBD(Date sTPBD) {
		STPBD = sTPBD;
	}

	/**
	 * @return the pUCD
	 */
	public Double getPUCD() {
		return PUCD;
	}

	/**
	 * @param pUCD the pUCD to set
	 */
	public void setPUCD(Double pUCD) {
		PUCD = pUCD;
	}

	/**
	 * @return the pAD41
	 */
	public String getPAD41() {
		return PAD41;
	}

	/**
	 * @param pAD41 the pAD41 to set
	 */
	public void setPAD41(String pAD41) {
		PAD41 = pAD41;
	}

	/**
	 * @return the pAD42
	 */
	public String getPAD42() {
		return PAD42;
	}

	/**
	 * @param pAD42 the pAD42 to set
	 */
	public void setPAD42(String pAD42) {
		PAD42 = pAD42;
	}

	/**
	 * @return the pAD43
	 */
	public String getPAD43() {
		return PAD43;
	}

	/**
	 * @param pAD43 the pAD43 to set
	 */
	public void setPAD43(String pAD43) {
		PAD43 = pAD43;
	}

	/**
	 * @return the pAD44
	 */
	public String getPAD44() {
		return PAD44;
	}

	/**
	 * @param pAD44 the pAD44 to set
	 */
	public void setPAD44(String pAD44) {
		PAD44 = pAD44;
	}

	/**
	 * @return the pAD51
	 */
	public String getPAD51() {
		return PAD51;
	}

	/**
	 * @param pAD51 the pAD51 to set
	 */
	public void setPAD51(String pAD51) {
		PAD51 = pAD51;
	}

	/**
	 * @return the pAD52
	 */
	public String getPAD52() {
		return PAD52;
	}

	/**
	 * @param pAD52 the pAD52 to set
	 */
	public void setPAD52(String pAD52) {
		PAD52 = pAD52;
	}

	/**
	 * @return the pAD53
	 */
	public String getPAD53() {
		return PAD53;
	}

	/**
	 * @param pAD53 the pAD53 to set
	 */
	public void setPAD53(String pAD53) {
		PAD53 = pAD53;
	}

	/**
	 * @return the pAD54
	 */
	public String getPAD54() {
		return PAD54;
	}

	/**
	 * @param pAD54 the pAD54 to set
	 */
	public void setPAD54(String pAD54) {
		PAD54 = pAD54;
	}

	/**
	 * @return the kYC11
	 */
	public String getKYC11() {
		return KYC11;
	}

	/**
	 * @param kYC11 the kYC11 to set
	 */
	public void setKYC11(String kYC11) {
		KYC11 = kYC11;
	}

	/**
	 * @return the kYC12
	 */
	public String getKYC12() {
		return KYC12;
	}

	/**
	 * @param kYC12 the kYC12 to set
	 */
	public void setKYC12(String kYC12) {
		KYC12 = kYC12;
	}

	/**
	 * @return the kYC13
	 */
	public String getKYC13() {
		return KYC13;
	}

	/**
	 * @param kYC13 the kYC13 to set
	 */
	public void setKYC13(String kYC13) {
		KYC13 = kYC13;
	}

	/**
	 * @return the kYC14
	 */
	public String getKYC14() {
		return KYC14;
	}

	/**
	 * @param kYC14 the kYC14 to set
	 */
	public void setKYC14(String kYC14) {
		KYC14 = kYC14;
	}

	/**
	 * @return the kYC21
	 */
	public String getKYC21() {
		return KYC21;
	}

	/**
	 * @param kYC21 the kYC21 to set
	 */
	public void setKYC21(String kYC21) {
		KYC21 = kYC21;
	}

	/**
	 * @return the kYC22
	 */
	public String getKYC22() {
		return KYC22;
	}

	/**
	 * @param kYC22 the kYC22 to set
	 */
	public void setKYC22(String kYC22) {
		KYC22 = kYC22;
	}

	/**
	 * @return the kYC23
	 */
	public String getKYC23() {
		return KYC23;
	}

	/**
	 * @param kYC23 the kYC23 to set
	 */
	public void setKYC23(String kYC23) {
		KYC23 = kYC23;
	}

	/**
	 * @return the kYC24
	 */
	public String getKYC24() {
		return KYC24;
	}

	/**
	 * @param kYC24 the kYC24 to set
	 */
	public void setKYC24(String kYC24) {
		KYC24 = kYC24;
	}

	/**
	 * @return the kYC31
	 */
	public String getKYC31() {
		return KYC31;
	}

	/**
	 * @param kYC31 the kYC31 to set
	 */
	public void setKYC31(String kYC31) {
		KYC31 = kYC31;
	}

	/**
	 * @return the kYC32
	 */
	public String getKYC32() {
		return KYC32;
	}

	/**
	 * @param kYC32 the kYC32 to set
	 */
	public void setKYC32(String kYC32) {
		KYC32 = kYC32;
	}

	/**
	 * @return the kYC33
	 */
	public String getKYC33() {
		return KYC33;
	}

	/**
	 * @param kYC33 the kYC33 to set
	 */
	public void setKYC33(String kYC33) {
		KYC33 = kYC33;
	}

	/**
	 * @return the kYC34
	 */
	public String getKYC34() {
		return KYC34;
	}

	/**
	 * @param kYC34 the kYC34 to set
	 */
	public void setKYC34(String kYC34) {
		KYC34 = kYC34;
	}

	/**
	 * @return the kYC41
	 */
	public String getKYC41() {
		return KYC41;
	}

	/**
	 * @param kYC41 the kYC41 to set
	 */
	public void setKYC41(String kYC41) {
		KYC41 = kYC41;
	}

	/**
	 * @return the kYC42
	 */
	public String getKYC42() {
		return KYC42;
	}

	/**
	 * @param kYC42 the kYC42 to set
	 */
	public void setKYC42(String kYC42) {
		KYC42 = kYC42;
	}

	/**
	 * @return the kYC43
	 */
	public String getKYC43() {
		return KYC43;
	}

	/**
	 * @param kYC43 the kYC43 to set
	 */
	public void setKYC43(String kYC43) {
		KYC43 = kYC43;
	}

	/**
	 * @return the kYC44
	 */
	public String getKYC44() {
		return KYC44;
	}

	/**
	 * @param kYC44 the kYC44 to set
	 */
	public void setKYC44(String kYC44) {
		KYC44 = kYC44;
	}

	/**
	 * @return the serialversionuid
	 */
	public static Long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the rEMARKS
	 */
	public String getREMARKS() {
		return REMARKS;
	}

	/**
	 * @param rEMARKS the rEMARKS to set
	 */
	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}

	/**
	 * @return the fIRSTCUSTOMER
	 */
	public String getFIRSTCUSTOMER() {
		return FIRSTCUSTOMER;
	}

	/**
	 * @param fIRSTCUSTOMER the fIRSTCUSTOMER to set
	 */
	public void setFIRSTCUSTOMER(String fIRSTCUSTOMER) {
		FIRSTCUSTOMER = fIRSTCUSTOMER;
	}

	/**
	 * @return the sECONDCUSTOMER
	 */
	public String getSECONDCUSTOMER() {
		return SECONDCUSTOMER;
	}

	/**
	 * @param sECONDCUSTOMER the sECONDCUSTOMER to set
	 */
	public void setSECONDCUSTOMER(String sECONDCUSTOMER) {
		SECONDCUSTOMER = sECONDCUSTOMER;
	}

	/**
	 * @return the tHIRDCUSTOMER
	 */
	public String getTHIRDCUSTOMER() {
		return THIRDCUSTOMER;
	}

	/**
	 * @param tHIRDCUSTOMER the tHIRDCUSTOMER to set
	 */
	public void setTHIRDCUSTOMER(String tHIRDCUSTOMER) {
		THIRDCUSTOMER = tHIRDCUSTOMER;
	}   
	   
}
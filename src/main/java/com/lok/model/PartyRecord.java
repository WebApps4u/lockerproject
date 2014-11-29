package com.lok.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ForeignKey;

/**
 * Entity implementation class for Entity: BookingShoots
 *
 */
@Entity

public class PartyRecord implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookingID;
	private String customerName;
	private String customerEmail;
	private Date shootDate;
	private Date bookingDate;
	private String bookingStatus;
	private String planID;
	private Integer numberOfDays;
	private Integer numberOfPhotos;
	private String immediateBooking;
	private Long priceRatePerPhoto;
	private String paymentType;
	private Long advancePaid;
	private String bookingMadeBy;
	private String selectedLocation;

	private static final long serialVersionUID = 1L;

	public PartyRecord() {
		super();
	}   
	public Long getBookingID() {
		return this.bookingID;
	}

	public void setBookingID(Long bookingID) {
		this.bookingID = bookingID;
	}   
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}   
	public String getCustomerEmail() {
		return this.customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}   
	public Date getShootDate() {
		return this.shootDate;
	}

	public void setShootDate(Date shootDate) {
		this.shootDate = shootDate;
	}   
	public Date getBookingDate() {
		return this.bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}   
	public String getBookingStatus() {
		return this.bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}   
	public String getPlanID() {
		return this.planID;
	}

	public void setPlanID(String planID) {
		this.planID = planID;
	}   
	public Integer getNumberOfDays() {
		return this.numberOfDays;
	}

	public void setNumberOfDays(Integer numberOfDays) {
		this.numberOfDays = numberOfDays;
	}   
	public Integer getNumberOfPhotos() {
		return this.numberOfPhotos;
	}

	public void setNumberOfPhotos(Integer numberOfPhotos) {
		this.numberOfPhotos = numberOfPhotos;
	}   
	public String getImmediateBooking() {
		return this.immediateBooking;
	}

	public void setImmediateBooking(String immediateBooking) {
		this.immediateBooking = immediateBooking;
	}   
	public Long getPriceRatePerPhoto() {
		return this.priceRatePerPhoto;
	}

	public void setPriceRatePerPhoto(Long priceRatePerPhoto) {
		this.priceRatePerPhoto = priceRatePerPhoto;
	}   
	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}   
	public Long getAdvancePaid() {
		return this.advancePaid;
	}

	public void setAdvancePaid(Long advancePaid) {
		this.advancePaid = advancePaid;
	}   
	
	public String getBookingMadeBy() {
		return this.bookingMadeBy;
	}

	public void setBookingMadeBy(String bookingMadeBy) {
		this.bookingMadeBy = bookingMadeBy;
	}
	public String getSelectedLocation() {
		return selectedLocation;
	}
	public void setSelectedLocation(String selectedLocation) {
		this.selectedLocation = selectedLocation;
	}
   
}

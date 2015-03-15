package com.lok.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Implementation of entity for Master seq table
 * @author USER
 *
 */

@Entity
public class Lok_master_seq implements Serializable {

	@Id
	private Long idlok_all_seq;
	
	//Name of the table
	private String object_type;
	
	//last seq for that table
	private Long last_seq;
	
	//last updated by user
	private String last_updated_by;
	
	//last Updated date
	private Date last_updated_date;
	
	//initial which will be appended with the seq
	private String object_intial;
	
	
	
	private static final Long serialVersionUID = 1L;



	/**
	 * @return the idlok_all_seq
	 */
	public Long getIdlok_all_seq() {
		return idlok_all_seq;
	}



	/**
	 * @param idlok_all_seq the idlok_all_seq to set
	 */
	public void setIdlok_all_seq(Long idlok_all_seq) {
		this.idlok_all_seq = idlok_all_seq;
	}



	/**
	 * @return the object_type
	 */
	public String getObject_type() {
		return object_type;
	}



	/**
	 * @param object_type the object_type to set
	 */
	public void setObject_type(String object_type) {
		this.object_type = object_type;
	}



	/**
	 * @return the last_seq
	 */
	public Long getLast_seq() {
		return last_seq;
	}



	/**
	 * @param last_seq the last_seq to set
	 */
	public void setLast_seq(Long last_seq) {
		this.last_seq = last_seq;
	}



	/**
	 * @return the last_updated_by
	 */
	public String getLast_updated_by() {
		return last_updated_by;
	}



	/**
	 * @param last_updated_by the last_updated_by to set
	 */
	public void setLast_updated_by(String last_updated_by) {
		this.last_updated_by = last_updated_by;
	}



	/**
	 * @return the last_updated_date
	 */
	public Date getLast_updated_date() {
		return last_updated_date;
	}



	/**
	 * @param last_updated_date the last_updated_date to set
	 */
	public void setLast_updated_date(Date last_updated_date) {
		this.last_updated_date = last_updated_date;
	}



	/**
	 * @return the object_intial
	 */
	public String getObject_intial() {
		return object_intial;
	}



	/**
	 * @param object_intial the object_intial to set
	 */
	public void setObject_intial(String object_intial) {
		this.object_intial = object_intial;
	}



	/**
	 * @return the serialversionuid
	 */
	public static Long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

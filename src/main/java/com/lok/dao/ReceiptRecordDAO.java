package com.lok.dao;



import com.lok.model.ReceiptRecord;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

/**
 * <p>
 * Interface for the ReceiptRecordDAO . This is created very simply by extending
 * GenericDAO and specifying the type for the entity class (ReceiptRecord) and the
 * type of its identifier (Long).
 * 
 * <p>
 * As a matter of best practice other components reference this interface rather
 * than the implementation of the DAO itself.
 * 
 * @author deepansh
 * 
 */
public interface ReceiptRecordDAO extends GenericDAO<ReceiptRecord, String> {

}
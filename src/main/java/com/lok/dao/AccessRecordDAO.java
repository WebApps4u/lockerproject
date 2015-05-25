package com.lok.dao;



import com.lok.model.AccessRecord;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

/**
 * <p>
 * Interface for the AccessRecordDAO . This is created very simply by extending
 * GenericDAO and specifying the type for the entity class (AccessRecord) and the
 * type of its identifier (Long).
 * 
 * <p>
 * As a matter of best practice other components reference this interface rather
 * than the implementation of the DAO itself.
 * 
 * @author deepansh
 * 
 */
public interface AccessRecordDAO extends GenericDAO<AccessRecord, String> {

}
package com.lok.dao;

import com.lok.model.Lok_master_seq;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

/**
 * <p>
 * Interface for the BillRecordDAO . This is created very simply by extending
 * GenericDAO and specifying the type for the entity class (BillRecord) and the
 * type of its identifier (Long).
 * 
 * <p>
 * As a matter of best practice other components reference this interface rather
 * than the implementation of the DAO itself.
 * 
 * @author deepansh
 * 
 */
public interface Lok_master_seqDAO extends GenericDAO<Lok_master_seq, Long> {

}
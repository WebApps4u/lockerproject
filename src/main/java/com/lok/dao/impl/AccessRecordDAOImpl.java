package com.lok.dao.impl;

import org.springframework.stereotype.Repository;

import com.lok.dao.BaseDAO;
import com.lok.dao.AccessRecordDAO;
import com.lok.model.AccessRecord;

@Repository
public class AccessRecordDAOImpl extends BaseDAO<AccessRecord, String> implements AccessRecordDAO {

}

package com.lok.dao.impl;

import org.springframework.stereotype.Repository;

import com.base.dao.BaseDAO;
import com.lok.dao.PartyRecordDAO;
import com.lok.model.PartyRecord;

@Repository
public class PartyRecordDAOImpl extends BaseDAO<PartyRecord, Long> implements PartyRecordDAO {

}

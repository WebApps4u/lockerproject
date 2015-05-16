package com.lok.dao.impl;

import org.springframework.stereotype.Repository;

import com.lok.dao.BaseDAO;
import com.lok.dao.BillRecordDAO;
import com.lok.dao.EmailOutboundDAO;
import com.lok.model.BillRecord;
import com.lok.model.EmailOutbound;

@Repository
public class EmailOutboundDAOImpl extends BaseDAO<EmailOutbound, String> implements EmailOutboundDAO {

}

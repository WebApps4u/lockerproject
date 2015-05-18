package com.lok.dao.impl;

import org.springframework.stereotype.Repository;

import com.lok.dao.BaseDAO;
import com.lok.dao.BillRecordDAO;
import com.lok.dao.EmailTemplateDAO;
import com.lok.model.BillRecord;
import com.lok.model.EmailTemplate;

@Repository
public class EmailTemplateDAOImpl extends BaseDAO<EmailTemplate, String> implements EmailTemplateDAO {

}

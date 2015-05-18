package com.lok.service;

import java.util.List;

import com.lok.model.BillRecord;
import com.lok.model.EmailTemplate;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

public interface EmailTemplateService {

	public void save(EmailTemplate emailTemplate);

    public List<EmailTemplate> findAll();

    public List<EmailTemplate> search(ISearch search);

    public SearchResult<EmailTemplate> searchAndCount(ISearch search);

    public boolean remove(EmailTemplate emailTemplate);

    public void flush();
    
    public EmailTemplate findById(String id);
    
    
}

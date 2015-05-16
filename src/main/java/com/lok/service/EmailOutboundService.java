package com.lok.service;

import java.util.List;

import com.lok.model.EmailOutbound;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

public interface EmailOutboundService {

	public void save(EmailOutbound emailOutbound);

    public List<EmailOutbound> findAll();

    public List<EmailOutbound> search(ISearch search);

    public SearchResult<EmailOutbound> searchAndCount(ISearch search);

    public boolean remove(EmailOutbound emailOutbound);

    public void flush();
    
    
}

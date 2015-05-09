package com.lok.service;

import java.util.List;

import com.lok.model.CustomerDetails;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

public interface CustomerDetailsService {

	public void save(CustomerDetails customerDetail);

    public void delete(String id);

    public List<CustomerDetails> findAll();

    public List<CustomerDetails> search(ISearch search);

    public SearchResult<CustomerDetails> searchAndCount(ISearch search);

    public CustomerDetails findById(String id);

    public CustomerDetails findByName(String name);

    public void flush();
    
    
}

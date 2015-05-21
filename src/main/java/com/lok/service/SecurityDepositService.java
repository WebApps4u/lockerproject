package com.lok.service;

import java.util.List;

import com.lok.model.SecurityDeposit;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

public interface SecurityDepositService {

	public void save(SecurityDeposit securityDeposit);

    public void delete(String id);

    public List<SecurityDeposit> findAll();

    public List<SecurityDeposit> search(ISearch search);

    public SearchResult<SecurityDeposit> searchAndCount(ISearch search);

    public SecurityDeposit findById(String id);

    public SecurityDeposit findByName(String name);

    public void flush();
    
    public SecurityDeposit[] findByIds(String... Ids );
    
}

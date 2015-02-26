package com.lok.service;

import java.util.List;

import com.lok.model.BillRecord;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

public interface BillRecordService {

	public void save(BillRecord billRecord);

    public void delete(String id);

    public List<BillRecord> findAll();

    public List<BillRecord> search(ISearch search);

    public SearchResult<BillRecord> searchAndCount(ISearch search);

    public BillRecord findById(String id);

    public BillRecord findByName(String name);

    public void flush();
    
    
}

package com.lok.service;

import java.util.List;

import com.lok.model.AccessRecord;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

public interface AccessRecordService {

	public void save(AccessRecord accessRecord);

    public void delete(String id);

    public List<AccessRecord> findAll();

    public List<AccessRecord> search(ISearch search);

    public SearchResult<AccessRecord> searchAndCount(ISearch search);

    public AccessRecord findById(String id);

    public AccessRecord findByName(String name);

    public void flush();
    
    public AccessRecord[] findByIds(String... Ids );
    
}

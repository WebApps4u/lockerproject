package com.lok.service;

import java.util.List;

import com.lok.model.PartyRecord;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

public interface PartyRecordService {

	public void save(PartyRecord bookingShoot);

    public void delete(Long id);

    public List<PartyRecord> findAll();

    public List<PartyRecord> search(ISearch search);

    public SearchResult<PartyRecord> searchAndCount(ISearch search);

    public PartyRecord findById(Long id);

    public PartyRecord findByName(String name);

    public void flush();
    
    
}

package com.lok.service;

import java.util.List;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
import com.lok.model.Lok_master_seq;

public interface MasterSeqService {
	
	public void save(Lok_master_seq master_seqRecrod);

    public void delete(String id);

    public List<Lok_master_seq> findAll();

    public List<Lok_master_seq> search(ISearch search);

    public SearchResult<Lok_master_seq> searchAndCount(ISearch search);

    public Lok_master_seq findById(Long id);

    public Lok_master_seq findByName(String name);

    public void flush();

}

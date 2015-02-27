package com.lok.service;

import java.util.List;

import com.lok.model.ReceiptRecord;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

public interface ReceiptRecordService {

	public void save(ReceiptRecord receiptRecord);

    public void delete(String id);

    public List<ReceiptRecord> findAll();

    public List<ReceiptRecord> search(ISearch search);

    public SearchResult<ReceiptRecord> searchAndCount(ISearch search);

    public ReceiptRecord findById(String id);

    public ReceiptRecord findByName(String name);

    public void flush();
    
    
}

package com.lok.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lok.dao.ReceiptRecordDAO;
import com.lok.model.ReceiptRecord;
import com.lok.service.ReceiptRecordService;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Service
@Transactional
public class ReceiptRecordServiceImpl implements ReceiptRecordService{

	@Autowired
	ReceiptRecordDAO receiptRecordDAO;
	private static ReceiptRecordService INSTANCE=null;
	
	
    public void setReceiptRecordDAO(ReceiptRecordDAO dao) {
            this.receiptRecordDAO = dao;
    }
	
	/**
	 * Singleton Instance for services
	 * @return
	 */
	public static ReceiptRecordService getInstance(){
	
		if(INSTANCE==null){
			INSTANCE = new ReceiptRecordServiceImpl();
		}
		
		return INSTANCE;
	}

    public ReceiptRecordDAO getReceiptRecordDAO() {
		return receiptRecordDAO;
	}

	public void save(ReceiptRecord receiptRecord) {
    	receiptRecordDAO.save(receiptRecord);
    }

    public List<ReceiptRecord> findAll() {
            return receiptRecordDAO.findAll();
    }

    public ReceiptRecord findByName(String name) {
            if (name == null)
                    return null;
            return receiptRecordDAO.searchUnique(new Search().addFilterEqual("name", name));
    }

    public void flush() {
    	receiptRecordDAO.flush();
    }

    public List<ReceiptRecord> search(ISearch search) {
            return receiptRecordDAO.search(search);
    }

    public ReceiptRecord findById(String id) {
            return receiptRecordDAO.find(id);
    }

    public void delete(String id) {
    	receiptRecordDAO.removeById(id);
    }

    public SearchResult<ReceiptRecord> searchAndCount(ISearch search) {
            return receiptRecordDAO.searchAndCount(search);
    }
}

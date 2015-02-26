package com.lok.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lok.dao.BillRecordDAO;
import com.lok.model.BillRecord;
import com.lok.service.BillRecordService;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Service
@Transactional
public class BillRecordServiceImpl implements BillRecordService{

	@Autowired
	BillRecordDAO billRecordDAO;
	private static BillRecordService INSTANCE=null;
	
	
    public void setBillRecordDAO(BillRecordDAO dao) {
            this.billRecordDAO = dao;
    }
	
	/**
	 * Singleton Instance for services
	 * @return
	 */
	public static BillRecordService getInstance(){
	
		if(INSTANCE==null){
			INSTANCE = new BillRecordServiceImpl();
		}
		
		return INSTANCE;
	}

    public BillRecordDAO getBillRecordDAO() {
		return billRecordDAO;
	}

	public void save(BillRecord billRecord) {
    	billRecordDAO.save(billRecord);
    }

    public List<BillRecord> findAll() {
            return billRecordDAO.findAll();
    }

    public BillRecord findByName(String name) {
            if (name == null)
                    return null;
            return billRecordDAO.searchUnique(new Search().addFilterEqual("name", name));
    }

    public void flush() {
    	billRecordDAO.flush();
    }

    public List<BillRecord> search(ISearch search) {
            return billRecordDAO.search(search);
    }

    public BillRecord findById(String id) {
            return billRecordDAO.find(id);
    }

    public void delete(String id) {
    	billRecordDAO.removeById(id);
    }

    public SearchResult<BillRecord> searchAndCount(ISearch search) {
            return billRecordDAO.searchAndCount(search);
    }
}

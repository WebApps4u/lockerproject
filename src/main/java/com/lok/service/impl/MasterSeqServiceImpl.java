package com.lok.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lok.dao.Lok_master_seqDAO;
import com.lok.model.Lok_master_seq;
import com.lok.service.MasterSeqService;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Service
@Transactional
public class MasterSeqServiceImpl implements MasterSeqService{

	@Autowired
	Lok_master_seqDAO lok_master_seqDAO;
	private static MasterSeqService INSTANCE=null;
	
	
    public void setLok_master_seqDAO(Lok_master_seqDAO dao) {
            this.lok_master_seqDAO = dao;
    }
	
	/**
	 * Singleton Instance for services
	 * @return
	 */
	public static MasterSeqService getInstance(){
	
		if(INSTANCE==null){
			INSTANCE = new MasterSeqServiceImpl();
		}
		
		return INSTANCE;
	}

    public Lok_master_seqDAO getLok_master_seqDAO() {
		return lok_master_seqDAO;
	}

	public void save(Lok_master_seq billRecord) {
    	lok_master_seqDAO.save(billRecord);
    }

    public List<Lok_master_seq> findAll() {
            return lok_master_seqDAO.findAll();
    }

    public Lok_master_seq findByName(String name) {
            if (name == null)
                    return null;
            return lok_master_seqDAO.searchUnique(new Search().addFilterEqual("name", name));
    }

    public void flush() {
    	lok_master_seqDAO.flush();
    }

    public List<Lok_master_seq> search(ISearch search) {
            return lok_master_seqDAO.search(search);
    }

    public Lok_master_seq findById(Long id) {
            return lok_master_seqDAO.find(id);
    }

    public void delete(String id) {
    	//
    }

    public SearchResult<Lok_master_seq> searchAndCount(ISearch search) {
            return lok_master_seqDAO.searchAndCount(search);
    }
}

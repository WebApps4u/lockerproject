package com.lok.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lok.dao.PartyRecordDAO;
import com.lok.model.PartyRecord;
import com.lok.service.PartyRecordService;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Service
@Transactional
public class PartyRecordServiceImpl implements PartyRecordService{

	@Autowired
	PartyRecordDAO partyRecordDAO;
	private static PartyRecordService INSTANCE=null;
	
	
    public void setPartyRecordDAO(PartyRecordDAO dao) {
            this.partyRecordDAO = dao;
    }
	
	/**
	 * Singleton Instance for services
	 * @return
	 */
	public static PartyRecordService getInstance(){
	
		if(INSTANCE==null){
			INSTANCE = new PartyRecordServiceImpl();
		}
		
		return INSTANCE;
	}

    public PartyRecordDAO getPartyRecordDAO() {
		return partyRecordDAO;
	}

	public void save(PartyRecord partyRecord) {
    	partyRecordDAO.save(partyRecord);
    }

    public List<PartyRecord> findAll() {
            return partyRecordDAO.findAll();
    }

    public PartyRecord findByName(String name) {
            if (name == null)
                    return null;
            return partyRecordDAO.searchUnique(new Search().addFilterEqual("name", name));
    }

    public void flush() {
    	partyRecordDAO.flush();
    }

    public List<PartyRecord> search(ISearch search) {
            return partyRecordDAO.search(search);
    }

    public PartyRecord findById(Long id) {
            return partyRecordDAO.find(id);
    }

    public void delete(Long id) {
    	partyRecordDAO.removeById(id);
    }

    public SearchResult<PartyRecord> searchAndCount(ISearch search) {
            return partyRecordDAO.searchAndCount(search);
    }
}

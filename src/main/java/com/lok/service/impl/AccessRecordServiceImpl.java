package com.lok.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lok.dao.AccessRecordDAO;
import com.lok.model.AccessRecord;
import com.lok.service.AccessRecordService;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Service
@Transactional
public class AccessRecordServiceImpl implements AccessRecordService{

	@Autowired
	AccessRecordDAO accessRecordDAO;
	private static AccessRecordService INSTANCE=null;
	
	
    public void setAccessRecordDAO(AccessRecordDAO dao) {
            this.accessRecordDAO = dao;
    }
	
	/**
	 * Singleton Instance for services
	 * @return
	 */
	public static AccessRecordService getInstance(){
	
		if(INSTANCE==null){
			INSTANCE = new AccessRecordServiceImpl();
		}
		
		return INSTANCE;
	}

    public AccessRecordDAO getAccessRecordDAO() {
		return accessRecordDAO;
	}

	public void save(AccessRecord accessRecord) {
    	accessRecordDAO.save(accessRecord);
    }

    public List<AccessRecord> findAll() {
            return accessRecordDAO.findAll();
    }

    public AccessRecord findByName(String name) {
            if (name == null)
                    return null;
            return accessRecordDAO.searchUnique(new Search().addFilterEqual("name", name));
    }

    public void flush() {
    	accessRecordDAO.flush();
    }

    public List<AccessRecord> search(ISearch search) {
            return accessRecordDAO.search(search);
    }

    public AccessRecord findById(String id) {
            return accessRecordDAO.find(id);
    }

    public void delete(String id) {
    	accessRecordDAO.removeById(id);
    }

    public SearchResult<AccessRecord> searchAndCount(ISearch search) {
            return accessRecordDAO.searchAndCount(search);
    }
    
    public AccessRecord[] findByIds(String... Ids ){
    	return accessRecordDAO.find(Ids);
    }
}

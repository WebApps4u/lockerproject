package com.lok.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lok.dao.SecurityDepositDAO;
import com.lok.model.SecurityDeposit;
import com.lok.service.SecurityDepositService;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Service
@Transactional
public class SecurityDepositServiceImpl implements SecurityDepositService{

	@Autowired
	SecurityDepositDAO securityDepositDAO;
	private static SecurityDepositService INSTANCE=null;
	
	
    public void setSecurityDepositDAO(SecurityDepositDAO dao) {
            this.securityDepositDAO = dao;
    }
	
	/**
	 * Singleton Instance for services
	 * @return
	 */
	public static SecurityDepositService getInstance(){
	
		if(INSTANCE==null){
			INSTANCE = new SecurityDepositServiceImpl();
		}
		
		return INSTANCE;
	}

    public SecurityDepositDAO getSecurityDepositDAO() {
		return securityDepositDAO;
	}

	public void save(SecurityDeposit securityDeposit) {
    	securityDepositDAO.save(securityDeposit);
    }

    public List<SecurityDeposit> findAll() {
            return securityDepositDAO.findAll();
    }

    public SecurityDeposit findByName(String name) {
            if (name == null)
                    return null;
            return securityDepositDAO.searchUnique(new Search().addFilterEqual("name", name));
    }

    public void flush() {
    	securityDepositDAO.flush();
    }

    public List<SecurityDeposit> search(ISearch search) {
            return securityDepositDAO.search(search);
    }

    public SecurityDeposit findById(String id) {
            return securityDepositDAO.find(id);
    }

    public void delete(String id) {
    	securityDepositDAO.removeById(id);
    }

    public SearchResult<SecurityDeposit> searchAndCount(ISearch search) {
            return securityDepositDAO.searchAndCount(search);
    }
    
    public SecurityDeposit[] findByIds(String... Ids ){
    	return securityDepositDAO.find(Ids);
    }
}

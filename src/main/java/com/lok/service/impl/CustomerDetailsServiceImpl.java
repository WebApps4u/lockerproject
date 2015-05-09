package com.lok.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lok.dao.CustomerDetailsDAO;
import com.lok.model.CustomerDetails;
import com.lok.service.CustomerDetailsService;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Service
@Transactional
public class CustomerDetailsServiceImpl implements CustomerDetailsService{

	@Autowired
	CustomerDetailsDAO custDetailsDAO;
	private static CustomerDetailsService INSTANCE=null;
	
	
    public void setCustomerDetailsDAO(CustomerDetailsDAO dao) {
            this.custDetailsDAO = dao;
    }
	
	/**
	 * Singleton Instance for services
	 * @return
	 */
	public static CustomerDetailsService getInstance(){
	
		if(INSTANCE==null){
			INSTANCE = new CustomerDetailsServiceImpl();
		}
		
		return INSTANCE;
	}

    public CustomerDetailsDAO getCustomerDetailsDAO() {
		return custDetailsDAO;
	}

	public void save(CustomerDetails billRecord) {
    	custDetailsDAO.save(billRecord);
    }

    public List<CustomerDetails> findAll() {
            return custDetailsDAO.findAll();
    }

    public CustomerDetails findByName(String name) {
            if (name == null)
                    return null;
            return custDetailsDAO.searchUnique(new Search().addFilterEqual("name", name));
    }

    public void flush() {
    	custDetailsDAO.flush();
    }

    public List<CustomerDetails> search(ISearch search) {
            return custDetailsDAO.search(search);
    }

    public CustomerDetails findById(String id) {
            return custDetailsDAO.find(id);
    }

    public void delete(String id) {
    	custDetailsDAO.removeById(id);
    }

    public SearchResult<CustomerDetails> searchAndCount(ISearch search) {
            return custDetailsDAO.searchAndCount(search);
    }
}

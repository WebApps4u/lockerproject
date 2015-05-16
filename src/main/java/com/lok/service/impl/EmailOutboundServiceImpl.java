package com.lok.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lok.dao.EmailOutboundDAO;
import com.lok.model.EmailOutbound;
import com.lok.service.EmailOutboundService;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Service
@Transactional
public class EmailOutboundServiceImpl implements EmailOutboundService{

	@Autowired
	EmailOutboundDAO emailOutboundDAO;
	private static EmailOutboundService INSTANCE=null;
	
	
    public void setEmailOutboundDAO(EmailOutboundDAO dao) {
            this.emailOutboundDAO = dao;
    }
	
	/**
	 * Singleton Instance for services
	 * @return
	 */
	public static EmailOutboundService getInstance(){
	
		if(INSTANCE==null){
			INSTANCE = new EmailOutboundServiceImpl();
		}
		
		return INSTANCE;
	}

    public EmailOutboundDAO getEmailOutboundDAO() {
		return emailOutboundDAO;
	}

	public void save(EmailOutbound emailOutbound) {
    	emailOutboundDAO.save(emailOutbound);
    }

    public List<EmailOutbound> findAll() {
            return emailOutboundDAO.findAll();
    }


    public void flush() {
    	emailOutboundDAO.flush();
    }

    public List<EmailOutbound> search(ISearch search) {
            return emailOutboundDAO.search(search);
    }


    public SearchResult<EmailOutbound> searchAndCount(ISearch search) {
            return emailOutboundDAO.searchAndCount(search);
    }

	/* (non-Javadoc)
	 * @see com.lok.service.EmailOutboundService#remove(com.lok.model.EmailOutbound)
	 */
	@Override
	public boolean remove(EmailOutbound emailOutbound) {
		
		return emailOutboundDAO.remove(emailOutbound);
	}
    
    
}

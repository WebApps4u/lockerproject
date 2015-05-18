package com.lok.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lok.dao.EmailTemplateDAO;
import com.lok.model.BillRecord;
import com.lok.model.EmailTemplate;
import com.lok.service.EmailTemplateService;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Service
@Transactional
public class EmailTemplateServiceImpl implements EmailTemplateService{

	@Autowired
	EmailTemplateDAO emailTemplateDAO;
	private static EmailTemplateService INSTANCE=null;
	
	
    public void setEmailTemplateDAO(EmailTemplateDAO dao) {
            this.emailTemplateDAO = dao;
    }
	
	/**
	 * Singleton Instance for services
	 * @return
	 */
	public static EmailTemplateService getInstance(){
	
		if(INSTANCE==null){
			INSTANCE = new EmailTemplateServiceImpl();
		}
		
		return INSTANCE;
	}

    public EmailTemplateDAO getEmailTemplateDAO() {
		return emailTemplateDAO;
	}

	public void save(EmailTemplate emailTemplate) {
    	emailTemplateDAO.save(emailTemplate);
    }

    public List<EmailTemplate> findAll() {
            return emailTemplateDAO.findAll();
    }


    public void flush() {
    	emailTemplateDAO.flush();
    }

    public List<EmailTemplate> search(ISearch search) {
            return emailTemplateDAO.search(search);
    }


    public SearchResult<EmailTemplate> searchAndCount(ISearch search) {
            return emailTemplateDAO.searchAndCount(search);
    }

	/* (non-Javadoc)
	 * @see com.lok.service.EmailTemplateService#remove(com.lok.model.EmailTemplate)
	 */
	@Override
	public boolean remove(EmailTemplate emailTemplate) {
		
		return emailTemplateDAO.remove(emailTemplate);
	}
    
	 public EmailTemplate findById(String id) {
         return emailTemplateDAO.find(id);
 }
    
}

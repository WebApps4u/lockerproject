package com.lok.controller;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lok.model.EmailTemplate;
import com.lok.model.ReturnMessage;
import com.lok.model.ReturnMessage.StatusOfMessage;
import com.lok.service.EmailTemplateService;

public class EmailTemplateController extends BaseController<EmailTemplateService>{
	
	//add for logging
		private static Logger logger = Logger.getLogger(EmailTemplateController.class);
		
		//load required services and session factory
		@Autowired
		private SessionFactory sessionFactory;
		
		//load required service
		private EmailTemplateService emailTemplateService;
		
		public EmailTemplateController(){
			super(EmailTemplateService.class);
			logger.debug(" enter constructor EmailTemplateService");
			
			// get the service bean
			emailTemplateService = getService();
			logger.debug(" Exit constructor PartyRecordController with partyRecordService"+emailTemplateService);
		}
		
		/**
		 * Update the bill template
		 * 
		 */
		public ReturnMessage updateTemplate(EmailTemplate template){
			ReturnMessage msg = new ReturnMessage();
			
			try{
				emailTemplateService.save(template);
			
				msg.setStatus(StatusOfMessage.SUCCESS);
				msg.setSuccessMsg(ReturnMessage.SuccessSet.UPDATE_SUCCESS.toString());
			}catch(Exception e){
				e.printStackTrace();
				logger.error(" Error occurred while saving bill template "+e.getMessage());
				msg.setDefaultErr();
			}
			return msg;
		}
		
		/**
		 * Based on the email temlate id, sends back the data
		 */
		public EmailTemplate getTemplateBean(String emailTempId){
			return emailTemplateService.findById(emailTempId);
		}
}

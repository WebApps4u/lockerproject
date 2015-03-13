package com.lok.controller;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.lok.config.ConfigurationLok;

public class BaseController<T> {

	// add for logging
		private static Logger logger = Logger.getLogger(BaseController.class);

		// load required services and session factory
		@Autowired
		protected SessionFactory sessionFactory;

		protected ApplicationContext context = null; // invoke only when required
													// using constructor
		
		//private Class<T> classType;
		
		//create the controller variable
        @Autowired
		private T service;
		
        //Constructor to only load the context without any service
        public BaseController(){
        	logger.debug(" enter constructor BaseController");
			// get the context
			context = ConfigurationLok.getAppContext();
			
			logger.debug(" Exit constructor BaseController");
        }
		
		public BaseController(Class<T> classType){
			logger.debug(" enter constructor BaseController");
			// get the context
			context = ConfigurationLok.getAppContext();
			
			service = context.getBean(classType);
			logger.debug(" Exit constructor BaseController with service "+service);
		}
		
		//get the service
		protected T getService(){
			return service; 
		}
}

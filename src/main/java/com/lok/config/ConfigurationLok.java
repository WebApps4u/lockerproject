package com.lok.config;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import com.lok.controller.PartyRecordController;

/**
 * Have all the required configuration to boot the application
 * @author USER
 *
 */
public class ConfigurationLok {

	//add for logging
	private static Logger logger = Logger.getLogger(PartyRecordController.class);
		
	//to get the context of all the services
	private static ApplicationContext context =
		    null;
	
	//All the paths of the xml
	
	
	
	//All the default path, if not provided by the system property
	private static final String defaultContextPath = "/com/lok/config/PartyRecordServiceTests-context.xml";
	
	
	//make it singleton
	private ConfigurationLok(){};
	
	/**
	 * Return the Context required to load beans
	 * @param path path of the xml file which has config
	 * @return
	 */
	public static ApplicationContext getAppContext(String path){
		
		logger.debug(" enter Configuration.getAppContext(String)");
		logger.debug(" path value "+path);
		if(context == null){
			context = new ClassPathXmlApplicationContext(path);
			logger.debug(" context object for path "+path+" is -> "+context);
		}
		
		logger.debug(" exit Configuration.getAppContext(String)");
		return context;
	}
	
	/**
	 * 
	 */
	
	//get the app context to load bean 
	public static ApplicationContext getAppContext(){
		
		logger.debug(" enter Configuration.getAppContext()");
		String path = System.getenv(Constant.CONFIG_ENV);
		
		if (StringUtils.isEmpty(path)){
			
			//get context from default path
			path = defaultContextPath;
		}
		logger.debug(" exit Configuration.getAppContext()");
		return getAppContext(path);
	}
}
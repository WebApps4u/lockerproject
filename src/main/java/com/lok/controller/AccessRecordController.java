package com.lok.controller;

import org.apache.log4j.Logger;

import com.lok.service.AccessRecordService;

public class AccessRecordController extends BaseController<AccessRecordService> {
	
	// add for logging
		private static Logger logger = Logger.getLogger(BillRecordController.class);

		// load required service
		private AccessRecordService accessRecordService;

		// requires party details
		private PartyRecordController partyCntrl = new PartyRecordController();

		public AccessRecordController() {
			// Need to call super class to create service
			super(AccessRecordService.class);
			logger.debug(" enter constructor AccessRecordController");

			// get the bean
			// billRecordService = context.getBean(BillRecordService.class);

			accessRecordService = getService();
			logger.debug(" Exit constructor AccessRecordController with accessRecordService"
					+ accessRecordService);
		}

}

package com.lok.controller;

import org.apache.log4j.Logger;
import com.lok.service.ReceiptRecordService;

public class ReceiptRecordController extends BaseController<ReceiptRecordService> {

	   // add for logging
		private static Logger logger = Logger.getLogger(BillRecordController.class);

       //Load the service
		private ReceiptRecordService receiptService;
		
		public ReceiptRecordController() {
            super(ReceiptRecordService.class);
			logger.debug(" enter constructor ReceiptRecordController");

			// get the bean
			receiptService = getService();
			logger.debug(" exit constructor ReceiptRecordController");
		}

}

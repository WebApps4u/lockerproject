package com.lok.testing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lok.model.BillRecord;
import com.lok.service.impl.LokUtility;

public class TestUtilityFunctions {

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		Date date = new Date();
		System.out.println(" date1 "+date);
		
		String dateFormat = "dd-MM-yyyy";
		SimpleDateFormat parse = new SimpleDateFormat(dateFormat);
		 System.out.println(" formatted "+parse.format(date));
		String parsedDate = parse.format(date);
		 
		 date = parse.parse(parsedDate);
		 
		 System.out.println(" date "+date);
	}

}

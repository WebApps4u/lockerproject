package com.lok.controller;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.lok.dao.AutoGenDAO;


public class AutoGenController extends BaseController<AutoGenDAO>{

	private AutoGenDAO contrl;
	
	public AutoGenController(){
		
		super(AutoGenDAO.class);
		
		contrl =  getService(); 
	}
	
	//test method
	public void getDate(){
		
		try{
		System.out.println(" control "+contrl);
		contrl.getDate();
		}catch(Exception e){
			System.out.println(" e ");
			e.printStackTrace();
		}
	}
	
	/**
	 * Invokes the auto_bill_generation proc from database
	 * by passing dueMonth and dueyear
	 * 
	 * It invokes new thread and resumes the application
	 */
	public void initiateBillGeneration(final String dueMonth,final String dueYear){
		
		Thread thread = new Thread(){
			public void run(){
				contrl.initiateBillGeneration(dueMonth, dueYear);
			}
		};
		
		thread.start();
		
		
	}
	
}

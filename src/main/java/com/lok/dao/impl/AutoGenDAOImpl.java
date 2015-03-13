package com.lok.dao.impl;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lok.dao.AutoGenDAO;

@Repository
public class AutoGenDAOImpl implements AutoGenDAO{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

  //test method for accessing jdbc queries
    public void getDate(){
    	try{
    
    

    	}catch(Exception e){
    		e.printStackTrace();
    	}

    }
    
    /**
     * Invokes auto_bill_generation proc
     */
    public void initiateBillGeneration(String dueMonth,String dueYear){
    	
    	    
    		this.jdbcTemplate.update("call auto_bill_generation(?,?)",dueMonth,dueYear);
    	    }
}

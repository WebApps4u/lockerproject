package com.lok.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public interface AutoGenDAO {

	 
    public void getDate();
    
    public void initiateBillGeneration(String dueMonth, String dueYear);
}
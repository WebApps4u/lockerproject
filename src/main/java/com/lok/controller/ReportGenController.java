package com.lok.controller;

import static com.lok.config.ConfigurationJdbc.connectionPool;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import org.springframework.jdbc.core.JdbcTemplate;

import com.lok.config.ConfigurationJdbc;
import com.lok.dao.AutoGenDAO;
import com.lok.model.BillRecord;

public class ReportGenController extends BaseController<AutoGenDAO>{

	private AutoGenDAO contrl;
	
	public ReportGenController(){
		
		super(AutoGenDAO.class);
		
		contrl =  getService(); 
		jdbcTemplate = contrl.getDataSource();
	}
	//jdbc template object
	private JdbcTemplate jdbcTemplate;
	

	
	
	 public static void main(String[] args) {
		 ConfigurationJdbc.closeOpenConnection();
			ConfigurationJdbc.setPoolConnection();
		 
		    String sourceFileName = "E:\\CODES\\LOK_GIT\\lockerproject\\reporting\\OutstandingBills.jrxml";
		   String sourceFileNameJ = "E:\\CODES\\LOK_GIT\\lockerproject\\reporting\\OutstandingBills.jasper";
			Connection connect = null;
			 Statement statement = null;
			 PreparedStatement preparedStatement = null;
			 ResultSet resultSet = null;
	      System.out.println("Compiling Report Design ...");
	      try {
	         /**
	          * Compile the report to a file name same as
	          * the JRXML file name
	          */
	         
	    	  connect = connectionPool.getConnection();
				// Statements allow to issue SQL queries to the database
				statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_UPDATABLE);
				
				// Result set get the result of the SQL query
				resultSet = statement
						.executeQuery("select * from billrecord where lamt is not null and lstxa is not null limit 2");
	    	  
	         
	         String printFileName = null;
	         Map parameters = new HashMap();
	         
	         parameters.put("ReportTitle", "Address Report");
			parameters.put("BaseDir",new File( "E:\\CODES\\LOK_GIT\\lockerproject\\images\\test"));
	         try {
	        	 
	        	 JasperCompileManager.compileReportToFile(sourceFileName);
	        	 printFileName =JasperFillManager.fillReportToFile(
	            sourceFileNameJ,
	            parameters,
	            new JRResultSetDataSource(resultSet));
	            
	            
	            if (printFileName != null) {
	                /**
	                 * 1- export to PDF
	                 */
	                JasperExportManager.exportReportToPdfFile(printFileName,
	                      "C://sample_report.pdf");

	                /**
	                 * 2- export to HTML
	                 */
	                JasperExportManager.exportReportToHtmlFile(printFileName,
	                      "C://sample_report.html");
	            }
	         } catch (JRException e) {
	            e.printStackTrace();
	         }
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }finally{
	    	  ConfigurationJdbc.closeOpenConnection();
	      }
	      System.out.println("Done compiling!!! ...");
	   }
	
}

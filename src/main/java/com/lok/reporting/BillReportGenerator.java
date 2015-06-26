package com.lok.reporting;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Logger;
import org.codehaus.plexus.util.StringUtils;

import com.lok.config.ConfigurationJdbc;
import com.lok.model.BillRecordField;
import com.lok.rest.lockerservice.ReportGenerationService;

public class BillReportGenerator extends ReportGenController{

	private static Logger logger = Logger
			.getLogger(BillReportGenerator.class);

	private final String DEFAULT_TEMPLATE_NAME = "OUTS_BILLS";
	
	//
 public JasperPrint getReportTest(HttpServletRequest request){
		 
	 logger.debug(" enter BillReportGenerator.getReportTest() "+request);
		 //get the saved flag, if it is true, it is one of the saved report, simple statement with no parameters
		 String savedFlag = request.getParameter("saved");
		 
		 if(StringUtils.equalsIgnoreCase(savedFlag, "true")){
			 this.savedFlag = true;
		 }
		 
		 //get template id, there can be different templates for billing. Fetch the template based on the requested id
		 //if template id is not provided, set the default template
		 String tempId = request.getParameter("tempid");
		 
		 if (StringUtils.isBlank(tempId)){
			 templateName = DEFAULT_TEMPLATE_NAME;
		 }
		 else{
			 // fetch template name from cache, or map of id and 
			 templateName = tempId;
		 }
		 
		 //Get the id for the query, delete the job based of custom or already saved queries
		 queryId = request.getParameter("id");
		 
		 //set mandatory fields for 
		 fromDate = request.getParameter("fromdate");
		 toDate   = request.getParameter("todate");
		 asOnDate = request.getParameter("asondate");
		 
		 try{
			 setQuery();
			 execute(true);
	     } catch (JRException e) {
	        e.printStackTrace();
	     }catch (SQLException e){
	    	 e.printStackTrace();
	     }
		 catch(Exception e){
	    	 e.printStackTrace();
	     }finally{
	    	 closeResources();
	     }
		 
		 return jasperPrint;
	 }
 
 	/**
 	 * Set the query based of requested id
 	 * @throws Exception 
 	 */
 	private void setQuery() {
 		if(savedFlag){
 			setPreSavedQuery();
 		}
 		else{
 			setCustomQuery();
 		}
 	}
 	/**
 	 * Set queries that are pre saved
 	 */
 	private void setPreSavedQuery() { 
 		logger.debug(" enter BillReportGenerator.setPreSavedQuery() ");
 		ReportsSql sql = new ReportsSql();
 		
 		sql.tableName = ReportsSql.billDetails;
 		sql.columns  = ReportsSql.allColumns;
 		
 		switch (queryId){
 		case "bill_1":   //current year outstanding
 			  sql.query = ReportsSql.currentYear+ReportsSql.outstandingBills;
 			  break;
 		case "bill_3": //current year bills raised
 			sql.query = ReportsSql.currentYear;
 			break;
 		default:
 			sql.query = ReportsSql.currentYear+ReportsSql.outstandingBills;
 		}
 		
 		executableQuery = sql.build();
 	}
 	
 	/**
 	 * Set Custom Query. 
 	 */
 	private void setCustomQuery() { 
 		logger.debug(" enter BillReportGenerator.setCustomQuery() ");
 		
 		//validate conditions for custom query
 		//from and to date and as on dates are mandatory. It should default to current date from caller
 		validateCustomQuery();
 		
 		ReportsSql sql = new ReportsSql();
 		
 		sql.tableName = ReportsSql.billDetails;
 		sql.columns  = ReportsSql.allColumns;
 		
 		switch (queryId){
 		case "bill_4":   //current year outstanding
 			  sql.query = ReportsSql.outstandingBills;
 			  break;
 		case "bill_5": //current year bills raised
 			//sql.query = ReportsSql.currentYear;
 			break;
 		default:
 			sql.query = ReportsSql.currentYear+ReportsSql.outstandingBills;
 		}
 		
 		//add formatted inYear clause
 		//TODO, remove hardcoding
 		 sql.addQuery(String.format(ReportsSql.inPeriod, "BFDT",fromDate,"BFDT",toDate));
 		
 		executableQuery = sql.build();
 	}
 	
}


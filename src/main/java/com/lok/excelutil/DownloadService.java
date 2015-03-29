package com.lok.excelutil;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONArray;

import com.lok.controller.BillRecordController;
import com.lok.dao.BillRecordDAO;
import com.lok.service.BillRecordService;
import com.lok.service.impl.BillRecordServiceImpl;

/**
 * Provides all required public methods to download excel
 * it accepts the Bean class which are Downloadable, by implementing a marking interface
 * Not all beans are allowed to create Excel
 * It reply by directly using response object
 * @author Deepansh Aggarwal
 * @param <T>
 *
 */
public class DownloadService<T extends DownloadableAsExcel> {

	// add for logging
	private static Logger logger = Logger.getLogger(DownloadService.class);
	
	//private static DownloadService INSTANCE=null;
	
	//the class for which excel is to be formed
	private Class<T> beanClass; 
	
	//all the fields
	private String[] fieldsName;
	

	//Set the class which will be used several places
	public DownloadService(Class<T> beanClass){
		this.beanClass = beanClass;
	}
	/**
	  * Processes the download for Excel format.
	  * It does the following steps:
	  * <pre>1. Create new workbook
	  * 2. Create new worksheet
	  * 3. Define starting indices for rows and columns
	  * 4. Build layout 
	  * 5. Fill report
	  * 6. Set the HttpServletResponse properties
	  * 7. Write to the output stream
	  * </pre>
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	  */
	 @SuppressWarnings("unchecked")
	 public void downloadXLS(HttpServletResponse response,List<T> dataSource) throws ClassNotFoundException,IllegalArgumentException, NoSuchFieldException, SecurityException {
	  logger.debug("Downloading Excel report");
	  
	  // 1. Create new workbook
	  HSSFWorkbook workbook = new HSSFWorkbook();
	   
	  // 2. Create new worksheet
	  HSSFSheet worksheet = workbook.createSheet("Report Worksheet");
	   
	  // 3. Define starting indices for rows and columns
	  int startRowIndex = 0;
	  int startColIndex = 0;
	   
	  // 4. Build layout 
	  // Build title, date, and column headers
	//Extract all the information related to the input source

	  //This will generate all fields as well
	  Layouter.buildReport(worksheet, startRowIndex, startColIndex,getFieldsName(dataSource));
	 
	  // 5. Fill report
	  FillManager.fillReport(worksheet, startRowIndex+2, startColIndex, dataSource,this.fieldsName,this.beanClass);
	   
	  // 6. Set the response properties
	  String fileName = this.beanClass.getSimpleName()+".xls";
	  response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	  // Make sure to set the correct content type
	  response.setContentType("application/vnd.ms-excel");
	   
	  //7. Write to the output stream
	  Writer.write(response, worksheet);
	 }
	 
	 /**
	  * This method will accept list of JSONObject and creates excel out of it
	  * Currently not supported
	  */
	  public void downloadXLS(HttpServletResponse response,JSONArray dataSource) throws ClassNotFoundException,IllegalArgumentException{
		  
	  }
	  
	  /**
	   * Returns the names of the fields
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	   */
	  private String[] getFieldsName(List<T> dataSource) throws NoSuchFieldException, SecurityException{
		  		  
		  //Get all the fields
		 Field[] fields = this.beanClass.getDeclaredFields();
		  
		  //initialize array with the length of the field array
		  this.fieldsName = new String[fields.length];
		  
		  for(int i=0;i<fields.length;i++){
			  fieldsName[i]=fields[i].getName();
		  }
		  
		  return fieldsName;
		 
	  }
}

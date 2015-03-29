package com.lok.excelutil;

import java.util.Date;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
 
/**
 * Builds the report layout, the template, the design, the pattern or whatever synonym you may want to call it.
 * 
 * @author Krams at {@link http://krams915@blogspot.com}
 */
class Layouter {
 
 private static Logger logger = Logger.getLogger("service");
  
 /**
  * Builds the report layout. 
  * <p>
  * This doesn't have any data yet. This is your template.
  */
 public static void buildReport(HSSFSheet worksheet, int startRowIndex, int startColIndex,String[] fields) {
  // Set column widths
	 
	  int size = fields.length;
	  
	  for(int i=0;i<size;i++){
		  worksheet.setColumnWidth(i, 5000);
	  }
   
  // Build the title and date headers
  buildTitle(worksheet, startRowIndex, startColIndex);
  // Build the column headers
  buildHeaders(worksheet, startRowIndex, startColIndex,fields);
 }
  
 /**
  * Builds the report title and the date header
  * 
  * @param worksheet
  * @param startRowIndex starting row offset
  * @param startColIndex starting column offset
  */
 public static void buildTitle(HSSFSheet worksheet, int startRowIndex, int startColIndex) {
  // Create font style for the report title
  Font fontTitle = worksheet.getWorkbook().createFont();
  fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);
  fontTitle.setFontHeight((short) 280);
   
        // Create cell style for the report title
        HSSFCellStyle cellStyleTitle = worksheet.getWorkbook().createCellStyle();
        cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyleTitle.setWrapText(true);
        cellStyleTitle.setFont(fontTitle);
   
        // Create report title
  HSSFRow rowTitle = worksheet.createRow((short) startRowIndex);
  rowTitle.setHeight((short) 500);
  HSSFCell cellTitle = rowTitle.createCell(startColIndex);
  cellTitle.setCellValue("Locker Report");
  cellTitle.setCellStyle(cellStyleTitle);
   
  // Create merged region for the report title
  worksheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
   
  // Create date header
  HSSFRow dateTitle = worksheet.createRow((short) startRowIndex +1);
  HSSFCell cellDate = dateTitle.createCell(startColIndex);
  cellDate.setCellValue("This report was generated at " + new Date());
 }
  
 /**
  * Builds the column headers
  * 
  * @param worksheet
  * @param startRowIndex starting row offset
  * @param startColIndex starting column offset
  * @param Array of the fields of the each column to be written
  */
 public static void buildHeaders(HSSFSheet worksheet, int startRowIndex, int startColIndex,String[] fields) {
  // Create font style for the headers
  Font font = worksheet.getWorkbook().createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
 
        // Create cell style for the headers
  HSSFCellStyle headerCellStyle = worksheet.getWorkbook().createCellStyle();
  headerCellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
  headerCellStyle.setFillPattern(CellStyle.FINE_DOTS);
  headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
  headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
  headerCellStyle.setWrapText(true);
  headerCellStyle.setFont(font);
  headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
   
  // Create the column headers
  HSSFRow rowHeader = worksheet.createRow((short) startRowIndex +2);
  rowHeader.setHeight((short) 500);
   
  //for each column addition
  int i=0;
  for (String cellHeader: fields){
	  
	  HSSFCell cell1 = rowHeader.createCell(startColIndex+i++);
	  cell1.setCellValue(cellHeader);
	  cell1.setCellStyle(headerCellStyle);
  }
 }
}
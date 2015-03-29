package com.lok.excelutil;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Fill the excel sheets. Not available outside the package
 * 
 * @author USER
 *
 */
class FillManager {

	/**
	 * Fills the report with content
	 * 
	 * @param worksheet
	 * @param startRowIndex
	 *            starting row offset
	 * @param startColIndex
	 *            starting column offset
	 * @param datasource
	 *            the data source
	 */
	public static <T> void fillReport(HSSFSheet worksheet, int startRowIndex,
			int startColIndex, final List<T> datasource, String[] fields,
			Class<T> beanClass) {
		// Row offset
		// startRowIndex += 2;

		// Create cell style for the body
		HSSFCellStyle bodyCellStyle = worksheet.getWorkbook().createCellStyle();
		bodyCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		bodyCellStyle.setWrapText(true);

		int size = datasource.size();

		// Create body
		for (int i = 0; i < size; i++) {
			// Create a new row
			HSSFRow row = worksheet.createRow((short) i + startRowIndex + 1);

			//get each row object
			Object obj = datasource.get(i);
			for (int j = 0; j < fields.length; j++) {
				String field = fields[j];
				// The field name is available
				// Class name is available
				// Invoke the read method for a type class by passing each
				// object
				Object value = null;
				try {

					// Retrieving first object by subtracting i by 2, it is
					// because i starts from 2
					value = new PropertyDescriptor(field, beanClass)
							.getReadMethod().invoke(obj);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IntrospectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NullPointerException e) {
					// should Continue if read method is not found for a field
					e.printStackTrace();
				}

				// Add this value to the excel
				// Retrieve the id value
				HSSFCell cell1 = row.createCell(startColIndex + j);

				// save all the values as String
				// null check, place blank value for null
				cell1.setCellValue(value != null ? value.toString() : "");
				cell1.setCellStyle(bodyCellStyle);

			}
		}
	}
}

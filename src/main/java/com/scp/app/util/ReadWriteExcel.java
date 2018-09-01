package com.scp.app.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWriteExcel {
//Apache poi -- <2007   HSSFWorkbook
	//Apache poi -ooxml >=2007 C:\Users\Yogesh\Desktop\AM.xlsx  XSSFWorkbook
	
	
	public static void main(String[] args) throws InvalidFormatException, IOException {
	//	readExcel();
		writeIntoExcel();
	}

	private static void writeIntoExcel() throws IOException {
			File file = new File("C:\\Users\\Yogesh\\Desktop\\New folder (3)\\EmpDB.xlsx");
	      //Create blank workbook
	      XSSFWorkbook workbook = new XSSFWorkbook();
	      //Create a blank sheet
	      XSSFSheet spreadsheet = workbook.createSheet( "Employee Info ");
	      //Create row object
	      XSSFRow row;
	      List<Emp> listOfemps = DataBaseUtil.fetchAllDataFromDB();
	      System.out.println("ExcelRW - listOfEms" +listOfemps);
	      
	      for(int i=0;i<listOfemps.size();i++){
	    	  row = spreadsheet.createRow(i);
	    	  
	    	  Cell cellForId=	row.createCell(0);
	    	  Cell cellForFirstName = row.createCell(2);
	    	  Cell cellForAddress = row.createCell(4);
	    	  
	    	  Emp emp = listOfemps.get(i);
	    	  
	    	  cellForId.setCellType(0);
	    	  cellForFirstName.setCellType(1);
	    	  cellForAddress.setCellType(1);
	    	  
	    	  cellForId.setCellValue(emp.getEmpId());
	    	  cellForFirstName.setCellValue(emp.getEmpName());
	    	  cellForAddress.setCellValue(emp.getEmpAddress());
	      }
	      
	      
	      
	       //Write the workbook in file system
	      FileOutputStream out = new FileOutputStream(file);
	      workbook.write(out);
	      out.close();
	      System.out.println("Writesheet.xlsx written successfully");

	}

	private static void readExcel() throws InvalidFormatException, IOException {
		
		File file = new File("C:\\Users\\Yogesh\\Desktop\\AM.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(file);
		XSSFSheet sheet = workBook.getSheet("Sheet3");
		Iterator<Row> rows = sheet.rowIterator();
		while(rows.hasNext()){
					Row row = rows.next();
						Iterator<Cell> cells = row.cellIterator();
						while(cells.hasNext()){
							Cell cell = cells.next();
							if(cell.getCellType()==0){
							//	System.out.println((int)cell.getNumericCellValue());
							}else{
								System.out.print(" -- "+cell.getStringCellValue());
							}
						}
		}

		
	}
	
}

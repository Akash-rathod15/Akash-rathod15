package com.scp.app.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadTestDataFromExcel {
//C:\\Users\\Yogesh\\Desktop\\Notes\\testData.xlsx
	/*public static void main(String[] args) throws InvalidFormatException, IOException {
		System.out.println(getTestData());
	}*/

	
	public static Object[][]convertTwoDimenationArray() throws InvalidFormatException, IOException{
		List<UserTestData> listOfScenarios = getTestData();
		Object[][] objs = new Object[listOfScenarios.size()][3];
		for(int i=0;i<listOfScenarios.size();i++){
					UserTestData testData = listOfScenarios.get(i);
					objs[i][0]=testData.getUserName().equals("NA") ? "" : testData.getUserName();
					objs[i][1]=testData.getPassword().equals("NA") ? "" : testData.getPassword();
					objs[i][2]=testData.getExpectedMsg();
		}
		return objs;
	}
	
	
	public static List<UserTestData> getTestData() throws InvalidFormatException, IOException{
		File file = new File("C:\\Users\\Yogesh\\Desktop\\Notes\\testData.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(file);
		XSSFSheet sheet = workBook.getSheet("Sheet1");
		Iterator<Row> rows = sheet.rowIterator();
		boolean isHeader = true;
		UserTestData obj = null;
		List<UserTestData> listOfScenarios = new ArrayList<UserTestData>();
		while(rows.hasNext()){
					Row row = rows.next();
					obj = new UserTestData();
					if(isHeader){
						isHeader=false;
						continue;
					}
					
						Iterator<Cell> cells = row.cellIterator();
						int count =0;
						while(cells.hasNext()){
							Cell cell = cells.next();
							if(count==0)
								obj.setUserName(cell.getStringCellValue());
							if(count==1)
								obj.setPassword(cell.getStringCellValue());
							if(count==2)
								obj.setExpectedMsg(cell.getStringCellValue());
							count++;
						}
						
						listOfScenarios.add(obj);
		}

	return listOfScenarios;	
	}
}


class UserTestData{
		String userName;
		String password;
		String expectedMsg;
		
		
		
		
		public UserTestData() {
			super();
		}



		@Override
		public String toString() {
			return "\n UserTestData [userName=" + userName + ", password="
					+ password + ", expectedMsg=" + expectedMsg + "]";
		}



		public UserTestData(String userName, String password, String expectedMsg) {
			super();
			this.userName = userName;
			this.password = password;
			this.expectedMsg = expectedMsg;
		}



		public String getUserName() {
			return userName;
		}



		public void setUserName(String userName) {
			this.userName = userName;
		}



		public String getPassword() {
			return password;
		}



		public void setPassword(String password) {
			this.password = password;
		}



		public String getExpectedMsg() {
			return expectedMsg;
		}



		public void setExpectedMsg(String expectedMsg) {
			this.expectedMsg = expectedMsg;
		}
		
		
		
		
}




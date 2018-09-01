	package com.scp.orange.hrm.login;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.scp.app.constants.AppConstants;
import com.scp.app.constants.AppConstants.BrowserNames;
import com.scp.app.util.AppUtil;
import com.scp.app.util.ReadTestDataFromExcel;
import com.scp.app.web.pageobjects.OrangeHRMDashaboard;
import com.scp.app.web.pageobjects.OrangeHRMLogin;
	
	public class OrangeHRMLoginTest {

		@DataProvider(name="data")
		public Object[][] getTestData(){
			try {
				return ReadTestDataFromExcel.convertTwoDimenationArray();
			} catch (InvalidFormatException | IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		
		@Test(dataProvider="data")
		public void verifyUserLoginCredentials(String username,String password,String emsg){
			
			System.out.println("Step1 - Launch Url and Enter ");
			System.out.println("Expected - Orange HRM Login page should be displayed..!");
			WebDriver driver = AppUtil.initializeBrowser(BrowserNames.Firefox, AppConstants.ORANGE_HRM_APP_URL);
			OrangeHRMLogin loginPage=PageFactory.initElements(driver,OrangeHRMLogin.class);
			
			System.out.println("Step1 - Enter UserName and Password(" +username+", : "+password+", : " +emsg +")");
			System.out.println("Expected - Dashboard page should be displayed..!");
			loginPage.enterUserName(username);
			loginPage.enterPassword(password);
			
			if(emsg.equalsIgnoreCase("success")){
				OrangeHRMDashaboard dashboardPage = loginPage.clickLoginWithSuccess();
				Assert.assertEquals("Welcome Admin",dashboardPage.getWelcomeMsg());
			}else{
				loginPage.clickLoginWithError();
				Assert.assertEquals(emsg, loginPage.getErrorMessage());
			}
			
		}
		
	}

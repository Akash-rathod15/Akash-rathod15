package com.scp.app.web.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.scp.app.util.AppUtil;

public class OrangeHRMLogin {
	
	@FindBy(id="txtUsername")
	WebElement username;     //Webelement username = driver.findelement(By.id('txtUsername'));
	
	@FindBy(id="txtPassword")
	WebElement password;
	
	@FindBy(id="btnLogin")
	WebElement loginBtn;
	
	@FindBy(id="spanMessage")
	WebElement errorMsg;

	
	public void enterUserName(String name){
			username.clear();
			username.sendKeys(name);
	}
	
	public void enterPassword(String pwd){
		password.clear();
		password.sendKeys(pwd);
	}
	
	public OrangeHRMDashaboard clickLoginWithSuccess(){
		loginBtn.click();
		return PageFactory.initElements(AppUtil.getWebdriver(),OrangeHRMDashaboard.class);
	}
	
	public String clickLoginWithError(){
		loginBtn.click();
		return getErrorMessage();
	}

	public String getErrorMessage() {
			try{
				return errorMsg.getText();
			}catch(NoSuchElementException e){
				return null;
			}
				
			
	}
	
	
}

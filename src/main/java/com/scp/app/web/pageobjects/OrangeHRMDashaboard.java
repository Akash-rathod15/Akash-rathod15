package com.scp.app.web.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrangeHRMDashaboard {

		@FindBy(id="welcome")
		WebElement welcomeAdmin;
	
		
		/**
		 * pageobject - webelements + related behaviours(method/operations)
		 */

		public String getWelcomeMsg(){
			try{
					return welcomeAdmin.getText();
			}catch(NoSuchElementException e){
				return null;
			}
		}
		
	
}

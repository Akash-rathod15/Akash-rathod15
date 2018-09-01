package com.scp;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestSelenium {
	
	
	/**
	 * selenium 2
	 * 		Firefox is the default browser
	 * 		except firefox every other browser required explicit driver path
	 * 		Firefox -- <47
	 * 		java 7 and java 8
	 * 
	 * selenium 3
	 * 		no default browser
	 * 		every browser required explicit driver path
	 * 		firefox >50
	 * 		java8
	 * 
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {

		//Apache Maven -- configuration
		//maven project
		//simple automation
		//2 vs 3
		//methods -- webdriver and webelements 
		
		
		System.setProperty("webdriver.gecko.driver", "E:\\MySofts\\geckodriver-v0.19.0-win64\\geckodriver.exe");
		
		WebDriver driver = new FirefoxDriver();
		
		driver.get("http://toolsqa.com/automation-practice-form/");
		WebElement firstnameInput = driver.findElement(By.name("firstname"));
		
		WebElement lastnameInput = driver.findElement(By.name("lastname"));
		List<WebElement> genderTypes = driver.findElements(By.name("sex"));
		List<WebElement> professionssss= driver.findElements(By.name("profession"));
		WebElement continents = driver.findElement(By.id("continents"));
		WebElement seleniumCmds = driver.findElement(By.id("selenium_commands"));
		
		
		
		
		firstnameInput.sendKeys("ABCD");
		lastnameInput.sendKeys("PQRS");
		
		Thread.sleep(1500);
		
		String userchoice = "Male";
		
		for (WebElement gender : genderTypes) {
				if(gender.getAttribute("value").equals(userchoice)){
					gender.click();
					break;
				}
		}
	
		Thread.sleep(1500);
		
		String [] userProf = {"Manual Tester","Automation Tester"};
		
		
		for (String item : userProf) {
			for (WebElement prof : professionssss) {
				if(prof.getAttribute("value").equals(item)){
					prof.click();
					break;
				}
		}
		}
		
		Thread.sleep(1500);
		String userContinent = "South America";
		Select dropDown = new Select(continents);
		dropDown.selectByVisibleText(userContinent);
		
		Thread.sleep(1500);
		
		String userContinents[] = {"Navigation Commands","Wait Commands"};
		Select multiDropDown = new Select(seleniumCmds);
		for (String cmd : userContinents) {
			multiDropDown.selectByVisibleText(cmd);
		}
		
	}

}

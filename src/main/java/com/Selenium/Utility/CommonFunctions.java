package com.Selenium.Utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonFunctions  extends WebDrivers{
	
	
	@FindBy(xpath="//span[text()='My Account']")
	public static WebElement all;
	
	@FindBy(id="//*")
	public static WebElement alls;
	
	/*
	 * public void convertWebElementToBy(WebElement webelement) { By by=null;
	 * System.out.println(webelement.toString()); return by;
	 * 
	 * }
	 */
	
	
	static {
		
		
		PageFactory.initElements(webdriver, CommonFunctions.class);
	
	}
	
     public void WebdriverClick(WebElement webelement) {
    	 
    	 
		 
    	WebDriverWait webdriverwait=new WebDriverWait(webdriver,60);
    	webdriverwait.pollingEvery(100, TimeUnit.MILLISECONDS);//This will check the ExpectedCondition every 100 milli sec by default it's 500 milli secs
        webdriverwait.until(ExpectedConditions.visibilityOf(webelement));
    	System.err.println( webelement.toString());
    	webelement.click();
    	 
    	 
     }

	

}

package com.Selenium.Utility;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WebDrivers extends GlobalDriverCap{

	   

	
	public WebDriver getDriverInstance(String Browser,String BrowserConfig) {
		
		
		

		switch (Browser) {

		case "chrome":

			           ChromeDriversCap ChromeCap=new ChromeDriversCap(BrowserConfig);
			           return webdriver;
			           
            
			

		case "firefox":
			
			
			
			          FireFoxProfileCap FIreFoxCap=new FireFoxProfileCap(BrowserConfig);
			          return webdriver;
			          
                          
			
			
		default:
			System.out.println("No Browser is Given to launch");
			new SkipException("Beacuse of Empty Browser this is Stopped");
			break;
		}
		return null;

	}

}


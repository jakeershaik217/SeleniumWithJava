package com.Selenium.Utility;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;

import javax.swing.text.DateFormatter;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ChromeDriversCap extends GlobalDriverCap{
	

	public ChromeDriversCap chromeDriversCap;
	
	  static {
		  
		  
		    driverspath = "\\src\\main\\resources\\drivers\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + driverspath); 
		  
		  
		  
		  
	  }
	


	public 	ChromeDriversCap getDriverinstance(String Config) {
		
		if(chromeDriversCap==null) {
			
		return	chromeDriversCap=new ChromeDriversCap(Config);
			
		}else {
			
			
			return chromeDriversCap;
			
		}
		
		
		
		
		
	}
	
	public ChromeDriversCap(String BrowserConfigurations) {

		switch(BrowserConfigurations) {
		
		case "chromeoptions":createChromeOptions();
		                     setChromeOptions();
		                     break;
		case "chromecap"    :createChromeOptions();
		                     setCapabilities();
		                     break;
		default             :createChromeOptions();
		                     setChromeOptions(); 
		                     setCapabilities();
		                     break;
		
		
		
		
		}
		
		webdriver=new ChromeDriver(Options);
		

	}
	
	public void createChromeOptions() {
		
		
		Options = new ChromeOptions();
	}
	
	
	public void setChromeOptions(){
		
	
		
		List<String> OptionsAdd = new ArrayList<String>();

		/*
		 * This is not working in ChromeDriver V 79 might be fix in V 80/81 See below
		 * Link
		 * 
		 * @ https://chromedriver.storage.googleapis.com/81.0.4044.69/notes.txt
		 */

		// OptionsAdd.add("make-default-browser");

		OptionsAdd.add("--start-maximized");
		OptionsAdd.add("--disable-extensions");
		OptionsAdd.add("disable-popup-blocking");
		OptionsAdd.add("--disable-infobars");
		OptionsAdd.add("incognito");
		OptionsAdd.add("--disable-device-discovery-notifications");
		OptionsAdd.add("version");
		
 
		// adding extension file to chrome
		// Options.addExtensions(new File("/path/to/extension.crx"));

		// Add the WebDriver proxy capability.
		Proxy proxy = new Proxy();
		proxy.setHttpProxy("myhttpproxy:3337");
		// Options.setCapability("proxy", proxy);

		Options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");

		// This is to set pref of chrome to disable the keep or harm popup while
		// downloading
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "downloadFiles");
		Options.setExperimentalOption("prefs", prefs);

		
		
		Options.setAcceptInsecureCerts(true);//this to accept un secured certificates
		/*
		 * Options.addArguments("--window-size=800,600");//will set windows size
		 * Options.addArguments("--window-position=600,700");//will set position of
		 * window
		 */		
		
		Options.addArguments(OptionsAdd);
	}
	
	public void setCapabilities() {
		
		ChromeCap = DesiredCapabilities.chrome();
		// Both Are Used FOr Same Purpose
		ChromeCap.setBrowserName("chrome");
		ChromeCap.setCapability(CapabilityType.BROWSER_NAME, "chrome");
		// Both Are Used FOr Same Purpose
		ChromeCap.setVersion("79.0");
		ChromeCap.setCapability(CapabilityType.BROWSER_VERSION, "79.0.3945.88");
		ChromeCap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);// accepts SSL Certificates


		// Enable performance logging
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
		ChromeCap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

		
		
		// Both are Same
		//ChromeCap.setCapability(ChromeOptions.CAPABILITY, Options);
		//Options.addArguments(OptionsAdd);

		// This is use to add Capabilities to chrome Options class
		Options.merge(ChromeCap);
		
			
		
	}
	
	
}

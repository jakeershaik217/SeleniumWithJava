package com.Selenium.Utility;

import java.io.File;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;

public class FireFoxProfileCap extends GlobalDriverCap{
	
	public FireFoxProfileCap firefoxprofileCap;
	
	
	static {
		  
		  
	    driverspath = "\\src\\main\\resources\\drivers\\geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + driverspath); 
	  
	  
	  
	  
          }
	
	
	public 	FireFoxProfileCap getDriverinstance(String Config) {
		
		if(firefoxprofileCap==null) {
			
		return	firefoxprofileCap=new FireFoxProfileCap(Config);
			
		}else {
			
			
			return firefoxprofileCap;
			
		}
		
		
		
		
		
	}
	
	public FireFoxProfileCap(String BrowserConfigurations) {

		switch(BrowserConfigurations) {
		
		case "firefoxoptions":setFireFoxOptions();
		                     break;
		case "firefoxprofile":setFireFoxOptions();
                              setFireFoxProfiles();
                              break;
		default             :setFireFoxOptions();
			                 setFireFoxProfiles();
                             break;
		        
		
		
		
		
		}
		
		webdriver=new FirefoxDriver(fireFoxoptions);

	}

	
	public void setFireFoxOptions() {
		
		fireFoxoptions=new FirefoxOptions();
		//Go to FireFox and type in URL about:config to see all avaialble config
		fireFoxoptions.addPreference("dom.webnotifications.enabled", true);
		
		
		
	}
	
   public void setFireFoxProfiles() {
		
	 //Creating new Firefox profile and adding extension in that Profile and this profile will be deleted once the Firefox instance is closed

		FireFoxprofile=new FirefoxProfile(new File("C:\\Users\\shaik\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles"));
		FireFoxprofile.addExtension(new File("C:\\Users\\shaik\\Downloads\\adblock.xpi"));
		fireFoxoptions.setProfile(FireFoxprofile);
		
		/*
		 * This is to get the created Profile in FireFox ProfilesIni profile=new ProfilesIni();
		 * fireFoxoptions=profile.getProfile("NameOf the Profile");
		 * fireFoxoptions.setProfile(FireFoxprofile);
		 * 
		 */
		
		
		
		
	}
	


}

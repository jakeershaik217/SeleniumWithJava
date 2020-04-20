package com.selenium.test;

import java.util.Date;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.testng.annotations.*;

import com.Selenium.Utility.ChromeDriversCap;
import com.Selenium.Utility.CommonFunctions;
import com.Selenium.Utility.WebDrivers;

public class webpageconfigurations extends CommonFunctions{
	
	public WebDriver driver;
	public Set<Cookie> browserCookies;
	public WebDrivers webdriversclass;
	public ChromeDriversCap chromeDriversCap;
	
	@Parameters({"browserName","Configurations"})
	@BeforeTest
	public void launchBrowser(String browserName,String Configurations) {
		
		webdriversclass=new WebDrivers();
		driver= webdriversclass.getDriverInstance(browserName,Configurations);
		driver.get("https://www.mbusa.com/en/home");
		
		
	}
	
	@Test(priority=0)
	public void setCookies() {
		
		
		Date date=new Date();
		date.setDate(15);
		date.setMonth(4);
		date.setYear(2020);
		Cookie cookie=new Cookie("sessioncookie","123456","","",date,false);
		browserCookies.add(cookie);
			
			
			
		}
	@Test(priority=1)
		public void getCookies() {
			
			
			browserCookies=driver.manage().getCookies();
			browserCookies.forEach(k -> System.out.println(k.getName()));
			
			WebElement e;
			Action ab=new Action() {
				
				@Override
				public void perform() {
					// TODO Auto-generated method stub
					
				}
			};
			
			
			
			
			
		}
	
	
	
	@AfterTest
	public void close() {
		
	driver.quit();	
		
		
	}

}

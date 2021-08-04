package com.selenium.test;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Selenium.Utility.WebDrivers;

public class ShadowDOM {
	
	static WebDriver driver;
	
	public static WebElement expandRootElement(WebElement element) {
		WebElement ele = (WebElement) ((JavascriptExecutor)driver)
	.executeScript("return arguments[0].shadowRoot", element);
		return ele;
	}
	public static void main(String[] args) throws IOException {
		WebDrivers wb=new WebDrivers();
		driver=wb.getDriverInstance("chrome", "chromeoptions");
		driver.get("https://trailhead.salesforce.com/");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		File file=new File("C:\\Users\\shaik\\OneDrive\\Desktop\\shadowDOM.txt");
		FileWriter fo=new FileWriter(file);
		fo.write(driver.getPageSource());
		fo.close();
	}

}

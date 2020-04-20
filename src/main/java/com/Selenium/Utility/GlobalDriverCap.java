package com.Selenium.Utility;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class GlobalDriverCap {
	
	public static WebDriver webdriver;
	public static String driverspath;
	protected ChromeOptions Options;
	protected FirefoxProfile FireFoxprofile;
	protected FirefoxOptions fireFoxoptions;
	protected DesiredCapabilities ChromeCap;

}

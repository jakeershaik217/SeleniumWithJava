package com.Selenium.Utility;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import jdk.nio.Channels.SelectableChannelCloser;

public class CommonFunctions extends WebDrivers {

	@FindBy(xpath = "//button[text()='Vehicles']")
	public static WebElement VehiclesElement;

	@FindBy(xpath = "//a[text()='Example Source on GitHub']/following-sibling::img")
	public static WebElement source;

	@FindBy(xpath = "//a[text()='Example Source on GitHub']/following-sibling::div")
	public static WebElement destination;

	@FindBy(xpath = "//button[text()='Vehicles']")
	public static  WebElement webelement;

	public static By Vehicles = By.xpath("//button[text()='Vehicles']");

	/*
	 * public void convertWebElementToBy(WebElement webelement) { By by=null;
	 * System.out.println(webelement.toString()); return by;
	 * 
	 * }
	 */
	public WebDriverWait webdriverwait;
	public ActionsClass actionsclass;
	public SelectClass selectClass;
	public JavascriptExecutorClass javascriptExecutorClass;

	public CommonFunctions() {

		webdriverwait = new WebDriverWait(webdriver, 10);
		actionsclass = ActionsClass.getActionsClassInstance();
		javascriptExecutorClass=JavascriptExecutorClass.getjavascriptexecutorReference();

	}
	
	public CommonFunctions(WebElement ele) {
		
		
		selectClass = SelectClass.getSelectClassInstance(ele);
		
	}

	public void WebdriverClick(WebElement webelement) {

		webdriverwait = new WebDriverWait(webdriver, 60);
		webdriverwait.pollingEvery(100, TimeUnit.MILLISECONDS);// This will check the ExpectedCondition every 100 milli
																// sec by default it's 500 milli secs
		webdriverwait.until(ExpectedConditions.visibilityOf(webelement));
		System.err.println(webelement.toString());
		webelement.click();

	}

	public void mouseclick(By webelement) {

		webdriverwait.until(ExpectedConditions.elementToBeClickable(webelement));
		actionsclass.clickonElement(webelement);

	}

	public void mouseclick(WebElement webelement) {

		webdriverwait.until(ExpectedConditions.elementToBeClickable(webelement));
		actionsclass.clickonElement(webelement);

	}

	public void scroll() {

		webdriverwait.until(ExpectedConditions.elementToBeClickable(source));
		actionsclass.actions.dragAndDrop(source, destination);

	}

	public void switchtoIframe(String Name, String id, Integer index, WebElement element) {

		if (Name != null) {

			webdriver.switchTo().frame(Name);
		} else if (id != null) {

			webdriver.switchTo().frame(id);

		} else if (index != null) {

			webdriver.switchTo().frame(index);

		} else if (element != null) {

			webdriver.switchTo().frame(element);

		}

	}

	public void dragandDrop(WebElement src, WebElement tar) {

		webdriverwait.until(ExpectedConditions.visibilityOf(src));
		actionsclass.dragANDdrop(src, tar, null, null, 2000L);

	}

	public void contextclick(WebElement elemet) {

		if (elemet != null) {

			webdriverwait.until(ExpectedConditions.visibilityOf(elemet));
			actionsclass.Contextclick(elemet);

		} else {

			actionsclass.Contextclick(null);

		}

	}

	public void KeysEvent(Keys k1, Keys k2, Keys k3, WebElement ele) {

		actionsclass.keyEvents(k1, k2, k3, ele);

	}

	public void selectvalueindropDown(String  Value,String VisibileText,Integer index) {

		selectClass.selectAvalueInDropdown(Value, VisibileText, index);

	}

	public static void main(String[] args) throws InterruptedException, IOException, JSONException {

		webdriver = new WebDrivers().getDriverInstance("chrome", "all");
		webdriver.get("https://www.mbusa.com/en/home");
		PageFactory.initElements(webdriver, CommonFunctions.class);
		CommonFunctions cm = new CommonFunctions();
		webdriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		
		// cm.switchtoIframe(null, null, 0, null);
		// (
		// (JavascriptExecutor)webdriver).executeScript("arguments[0].scrollIntoView(true);",source);
		// Thread.sleep(6000);
		// cm.dragandDrop(source, destination);
		//cm.contextclick(webelement);

		// cm.KeysEvent(Keys.SHIFT, Keys.CONTROL, Keys.DOWN, webelement);

		/*
		 * cm.selectClass.selectAllValuesInDropdown();
		 * cm.selectClass.getAllselectedValuesInDropdown();
		 */
	      cm. javascriptExecutorClass.pageHandling(1);
		/*
		 * cm.javascriptExecutorClass.scrollPage(webelement, 1500, 1600);
		 * cm.javascriptExecutorClass.javascriptClick(webelement);
		 */
	      
	      cm.javascriptExecutorClass.mouseOver(webelement);
	
	}

}

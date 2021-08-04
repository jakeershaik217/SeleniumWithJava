package com.Selenium.Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsClass extends GlobalDriverCap{
	
	
	public static ActionsClass actionsClass;
	public Actions actions;
	public static ActionsClass getActionsClassInstance() {
		
		if(actionsClass==null) {
			
		return 	actionsClass=new ActionsClass();
			
		}else {
			
			
			
			return actionsClass;
		}
		
		
		
	}
	
	public ActionsClass() {
		
		
		actions=new Actions(webdriver);
		
	}
	
	public void clickonElement(WebElement element) {
		
		actions.moveToElement(element).click().perform();
		
		
		
	}
	
public void clickonElement(By element) {
	
		WebElement Element=webdriver.findElement(element);
		actions.moveToElement(Element).click().perform();
		
		
		
	}
   public void dragANDdrop(WebElement Src,WebElement Tar,Integer x,Integer y,Long PauseTimeInMillsec) {
	   
	   
	   
    if(x == null || y== null) {
    	
    	actions.clickAndHold(Src).pause(PauseTimeInMillsec).moveToElement(Tar).pause(PauseTimeInMillsec).release(Tar).perform();
    	
    }else {
    	
    	
    	actions.clickAndHold(Src).pause(PauseTimeInMillsec).moveByOffset(x, y).pause(PauseTimeInMillsec).release().perform();
    	
    }
	
	
}
	
	
   public void Contextclick(WebElement element) {
	   
	   if(element!=null) {
		   
		   actions.contextClick(element).perform();  
		   
	   }else {
		   
		   actions.contextClick().perform();  
		   
	   }
		
		
	}
	
	
	public void keyEvents(Keys key1,Keys key2,Keys key3,WebElement elemet) {
		
		
		actions.clickAndHold(elemet).keyDown(key1).sendKeys(key2).keyUp(key3).perform();
		
	}
	

}

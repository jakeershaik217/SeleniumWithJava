package com.Selenium.Utility;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JavascriptExecutorClass extends GlobalDriverCap {
	
	public static String JS_Refresh="window.history.go();";
	public static String JS_forward="window.history.forward();";
	public static String JS_Back="window.history.back();";

	public static JavascriptExecutorClass javascriptExecutorClass;
	public JavascriptExecutor js;

	public static JavascriptExecutorClass getjavascriptexecutorReference() {

		if (javascriptExecutorClass == null) {

			return javascriptExecutorClass = new JavascriptExecutorClass();
		} else {

			return javascriptExecutorClass;
		}

	}

	public JavascriptExecutorClass() {

		js = ((JavascriptExecutor) webdriver);

	}

	/**
	 * 
	 * This will be used for page refresh
	 *  history page forward
	 *  history page backward
	 * @param function
	 */

	public void pageHandling(String function) {

		switch(function) {
		case "refresh": js.executeScript(JS_Refresh);
		                break;
		case "forward": js.executeScript(JS_forward);
                         break;
		case "back": js.executeScript(JS_Back);
                         break;
		default  :   js.executeScript(JS_Refresh);
                     break;
		
		
		}

	}
	
	/**
	 * 
	 *  number 1 is history backward
	 *  number-1 is history forward
	 *  number 0 is Page Refresh
	 * @param number
	 */
	
	public void pageHandling(int number) {
		
        if(number == 0 || number == -1 || number == 1)
		          js.executeScript("window.history.go("+number+")");

	}
	

	
	/**
	 * This will do scroll to Element,scroll By x and Y offset values
	 */
	
	public void scrollPage(WebElement element, int x, int y) {
		
		
		if(element!=null) {
			
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			
			
		}else {
			
			
			js.executeScript("window.scroll("+x+","+y+")");
			
		}

	}
	
	
	
   public void javascriptClick(WebElement element) {
	   
	   
	   js.executeScript("arguments[0].click();", element);
	   
	   
   }
   
   
   public void fillinputbox(WebElement ele,Object obj) {
	   
	   js.executeScript("arguments[0].setAttribute('value', '"+obj+"');", ele);
	   
	   
   }
   
   public void mouseOver(WebElement ele) {
	   
	   String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
	   
      js.executeScript(mouseOverScript, ele);
   
   }
}

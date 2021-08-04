package com.selenium.test;
import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup; 
import org.jsoup.nodes.Document; 
import org.jsoup.nodes.Element; 
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification; 
 
public class JsoupTesting { 
   public static void mai11n(String[] args){ 
      JsoupTesting test = new JsoupTesting(); 
      test.extractDataWithJsoup("https://groww.in/mutual-funds/filter"); 
   } 
   
   public static void main(String[] args) throws IOException {
	    HashMap<String,String> Headers=new HashMap<String,String>();
	    Headers.put("end-type", "1");
	    Headers.put("plan-type", "direct");
	    Headers.put("exclude", "suspended-plans");
	    Headers.put("tab", "snapshot");
	    Headers.put("output", "html-data");
	    
	    Response res=(Response) RestAssured.given().headers(Headers).accept(ContentType.ANY).when().baseUri("https://www.valueresearchonline.com/funds/selector-data/category/100/equity-large-cap/");
	   System.out.println(res);
	}
 
   public void extractDataWithJsoup(String href){ 
      Document doc = null; 
      try { 
         doc = Jsoup.connect(href).timeout(10*1000).userAgent
             ("Mozilla").ignoreHttpErrors(true).get(); 
      } catch (IOException e) { 
         //Your exception handling here 
      } 
      if(doc != null){ 
         Elements links = doc.select("div.s11ResultSec a"); 
         for (Element link : links) { 
            String linkHref = link.attr("href"); 
            String linkText = link.text(); 
            String linkOuterHtml = link.outerHtml(); 
            String linkInnerHtml = link.html(); 
            System.out.println(linkHref + "t" + linkText + "t"  + 
                linkOuterHtml + "t" + linkInnerHtml); 
         } 
      } 
   } 
} 

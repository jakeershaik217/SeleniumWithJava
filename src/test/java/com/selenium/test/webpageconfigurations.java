package com.selenium.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.testng.annotations.*;

import com.Selenium.Utility.ChromeDriversCap;
import com.Selenium.Utility.CommonFunctions;
import com.Selenium.Utility.WebDrivers;

import io.restassured.path.json.JsonPath;

public class webpageconfigurations extends CommonFunctions {

	public WebDriver driver;
	public Set<Cookie> browserCookies;
	public WebDrivers webdriversclass;
	public ChromeDriversCap chromeDriversCap;
	public File file;
	public String RelativeCookiePath = "//src//main//resources//Resources//Cookies.txt";

	@Parameters({ "browserName", "Configurations" })
	@BeforeTest
	public void launchBrowser(String browserName, String Configurations) {

		webdriversclass = new WebDrivers();
		driver = webdriversclass.getDriverInstance(browserName, Configurations);
		driver.get("https://www.facebook.com/");
		// driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.findElement(By.name("email")).sendKeys("username");
		driver.findElement(By.name("pass")).sendKeys("password");
		driver.findElement(By.id("u_0_b")).click();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}


	@Test(priority = 2,enabled=false)
	public void getCookies() throws IOException {

		System.out.println("-----------------------Cookies-------------------------------");
		file = new File(System.getProperty("user.dir") + RelativeCookiePath);
		browserCookies = driver.manage().getCookies();
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("{"+"\n"+"\"Cookies\":[");
		

		browserCookies.forEach(k -> {String Cookie="{"+"\n\"domain\" : \""+k.getDomain()+"\","+
				"\n\"name\" :\""+k.getName()+"\","+
				"\n\"value\" :\""+k.getValue()+"\","+
				"\n\"path\" : \""+k.getPath()+"\" } ,";
		
		try {
			bw.write(Cookie);
			 bw.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 
		});

		/*
		 * Iterator<Cookie> itr=browserCookies.iterator(); while(itr.hasNext()) {
		 * 
		 * String Cookie="{"+"\n\" domain \" : \""+itr.next().getDomain()+"\","+
		 * "\n\" name : \""+itr.next().getName()+"\","+
		 * "\n\" value \" : \""+itr.next().getValue()+"\","+
		 * "\n\" path \" : \""+itr.next().getPath()+"\",";
		 * }
		 */
		

	bw.newLine();
	bw.write("]}");
	bw.close();

	}

	
	@Test(priority=3)
	public void setCookies() throws IOException, JSONException {
		
		String Str;
		String JsonString="";
		file = new File(System.getProperty("user.dir") + RelativeCookiePath);
		FileReader fr=new FileReader(file);
		BufferedReader br=new BufferedReader(fr);
		while((Str=br.readLine())!=null) {
			
			
			JsonString=JsonString+Str;
			
			
		}
		
		JsonPath Js=new JsonPath(JsonString);
		JSONObject jsonObj=new JSONObject(JsonString);
		JSONArray jsonArray=jsonObj.getJSONArray("Cookies");
		
		for(int i=0;i<jsonArray.length();i++) {
			
			
			
			Cookie cookie=new Cookie(Js.getString("Cookies["+i+"].name"), Js.getString("Cookies["+i+"].value"), Js.getString("Cookies["+i+"].domain"),Js.getString("Cookies["+i+"].path"),null);
			driver.manage().addCookie(cookie);
			
			
			
			
		}
		
		
		
		
		
	}
	
	
	@Test(priority=4)
	public void RefreshBrowser() throws InterruptedException {
		
		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.navigate().refresh();

		
	}
	@Test(priority = 0, enabled = false)
	public void setinitialBrowserWindow() throws InterruptedException {

		// This will set the position of browser
		Point point = new Point(0, 0);
		driver.manage().window().setPosition(point);

		// This will set the length and width of browser
		Dimension d = new Dimension(1500, 2500);
		driver.manage().window().setSize(d);

		// This is used to make the browser as full screen
		driver.manage().window().fullscreen();

		// it will maximize the browser
		driver.manage().window().maximize();

		// Using logEntries call we can print browser logs

		// we can use the below line for LogType."different types";
		LogEntries log = driver.manage().logs().get(LogType.BROWSER);

		Iterator<LogEntry> logentry = log.iterator();

		while (logentry.hasNext()) {

			System.out.println(logentry.next());

		}

		/*
		 * Set<String> alllogs=driver.manage().logs().getAvailableLogTypes();
		 * Iterator<String> alllogsitr=alllogs.iterator(); while(alllogsitr.hasNext()) {
		 * 
		 * 
		 * System.out.println(alllogsitr.next()); }
		 */

	}

	@Test(priority = 1)
	public void getBrowserWindowdimensions() throws InterruptedException {

		Point p = driver.manage().window().getPosition();
		System.out.println("Current Browser's X axis :: " + p.getX() + " And Y axis :: " + p.getY());

		Dimension d = driver.manage().window().getSize();
		System.out.println("Current Browser's height :: " + d.getHeight() + " And width :: " + d.getWidth());

	}

	@AfterTest
	public void close() {

		driver.quit();

	}

}

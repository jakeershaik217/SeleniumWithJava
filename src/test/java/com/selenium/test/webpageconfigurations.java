package com.selenium.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import com.Selenium.Utility.ChromeDriversCap;
import com.Selenium.Utility.CommonFunctions;
import com.Selenium.Utility.WebDrivers;

import io.restassured.path.json.JsonPath;

public class webpageconfigurations{

	public WebDriver driver;
	public Set<Cookie> browserCookies;
 	public WebDrivers webdriversclass;
	public ChromeDriversCap chromeDriversCap;
	CommonFunctions cf;
	JavascriptExecutor js;
	HashMap<String, String>  SectorLinks;
	HashMap<String, HashMap<String,Double>>  MFData;
	HashMap<String, String>  MutualFunds;
	String FundURL= "https://www.valueresearchonline.com/funds/";
	XSSFWorkbook workbook;
	XSSFSheet Sheet;
	int rowNum=1;
	int columnNum = 0;
	String Companyame = "";
	String Sector = "";
	String Percent = "";
	String Cap = "";
	
    @Parameters({"browserName","Configurations"})
	@BeforeTest(enabled=true)
	public void launchBrowser(String browserName,String Configurations) throws InterruptedException, IOException {

		webdriversclass = new WebDrivers();
		driver = webdriversclass.getDriverInstance(browserName, Configurations);
		driver.get("https://groww.in/mutual-funds/filter?q=&fundSize=&categories=%7B%22Equity%22%3A%7B%22type%22%3A%22category%22%2C%22value%22%3A%22Equity%22%2C%22subCategories%22%3A%5B%5D%7D%7D&sortBy=3");
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		cf=new CommonFunctions();
		js=(JavascriptExecutor)driver;
		
	}
    
    public void scrollabelHeight() throws InterruptedException, IOException {

    long height=(long)js.executeScript("return document.documentElement.scrollHeight;");
    for(long scroll=0;scroll<height;scroll += 400) {
    	js.executeScript("window.scrollBy(0,400);");
    	Thread.sleep(1000);
    	height=(long)js.executeScript("return document.documentElement.scrollHeight;");
    	
    }
		
	}
    
    public void RunBeforeTest() throws InterruptedException, Exception {
    	
    	
		// driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
    	cf.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.id("profile-menu")));
		driver.findElement(By.id("profile-menu")).click();
    	cf.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Log in']")));
    	driver.findElement(By.xpath("//a[text()='Log in']")).click();
    	cf.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
		driver.findElement(By.id("username")).sendKeys("shaik.jakeerhussain768@gmail.com");
		cf.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.id("proceed-btn")));
		driver.findElement(By.id("proceed-btn")).click();
		cf.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_password")));
		driver.findElement(By.id("login_password")).sendKeys("jakeer786#");
		cf.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-btn")));
		driver.findElement(By.id("login-btn")).click();
		Thread.sleep(5000);
		FileInputStream fin=new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/resources/Resources/Extract.xlsx"));
   	    workbook = new XSSFWorkbook(fin);
   	    Sheet=workbook.getSheetAt(0);
   	    MFData=new HashMap<String, HashMap<String,Double>>();
    	
    	
    	
    }


	
	
	
     @Test(priority=1,enabled = false)
     public void performFunctions() throws InterruptedException {
    	 
    	js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
    	cf.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dataTables_scrollBody']//table/tbody/tr//a")));
    	SectorLinks=new HashMap<>();
    	List<WebElement> SectorTable=driver.findElements(By.xpath("//div[@class='dataTables_scrollBody']//table/tbody/tr//a"));
    	SectorTable.forEach((n) -> SectorLinks.put(n.getText(), n.getAttribute("href")));
    	SectorLinks.forEach((k,v) -> System.out.println(k+" -- "+v));
    	 
    	 
    	 
     }
     
     public String extractWebPageData(String URL) throws IOException, InterruptedException {
    	 
         URL url = new URL(URL);
         String html = "";
         URLConnection con = url.openConnection();
         con.setConnectTimeout(7000);
         Thread.sleep(15000);
         InputStream is =con.getInputStream();
         BufferedReader br = new BufferedReader(new InputStreamReader(is));
         String line = "";

         while ((line = br.readLine()) != null) {
        	 html=html+line;
         }
     return html;
    	 
     }
     
     public void extractDataFromMF(String Cap,String URL,String CompanyName) throws InterruptedException, IOException, InvalidFormatException {

    	this.Cap=Cap;
    	Document document=Jsoup.parse(extractWebPageData(URL));  
    	HashMap<String,Double> company=null;
    	
    	
    	company=new HashMap<String,Double>();
    	for(Element row : document.select("table#equity-holdings-table tbody tr")) {
    		
    		if(row.select("td:nth-child(2)").text().toString().trim().equals("")) {
    			continue;
    		}else {
    		
    			Companyame = row.select("td:nth-child(1)").text().toString().trim();
    			Sector = row.select("td:nth-child(2)").text().toString().trim();
    			Percent = row.select("td:last-child").text().toString().trim();
    			
    			
    			company.put(row.select("td:nth-child(1)").text().toString().trim(),Double.parseDouble(row.select("td:last-child").text().toString().trim()));
    			
    		}
    		columnNum = 0;
    		rowNum += 1;
    	}
    	MFData.put(CompanyName,company);
    	
    	
    	
    	
    	
     }
     
     @Test(priority=0,enabled=false)
     public void extractSectorLinks() throws InterruptedException, IOException {
    	 
     	Document document=Jsoup.parse(extractWebPageData(FundURL));  
     	SectorLinks=new HashMap<>();
     	for(Element row : document.select("table#short-term-table tbody tr")) {
     		if(row.select("td:nth-child(1) > a").text().equals("")) {
     			continue;
     		}else {
     		
     			SectorLinks.put(row.select("td:nth-child(1) > a").text(), "https://www.valueresearchonline.com"+row.select("td:nth-child(1) > a").attr("href"));
     		}
     	}
     	SectorLinks.forEach((k,v) -> System.out.println(k+" -- "+v));
      }
     
     @Test(priority=1,enabled=false)
     public void extractMFlinkinASection() throws InterruptedException, IOException, InvalidFormatException {
    	 
    	Set<String> SectorLinkKets=SectorLinks.keySet();
    	
    	for(String Key : SectorLinkKets) {
			 if(!Key.contains("Equity")) {
			  continue; }
			 
    		driver.navigate().to(SectorLinks.get(Key));
    		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        	cf.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dataTables_scrollBody']//table/tbody/tr//td[2]/a")));
        	List<WebElement> SectorTable=driver.findElements(By.xpath("//div[@class='dataTables_scrollBody']//table/tbody/tr//td[2]/a"));
        	MutualFunds=new HashMap<>();
        	SectorTable.forEach((n) -> MutualFunds.put(n.getText(), n.getAttribute("href")));
        	Set<String> KeySet=MutualFunds.keySet();
        	for(String Keys :KeySet) {
        		System.out.println(Keys);
        		if(Keys!=null || Keys!="") {
        			extractDataFromMF(Key,MutualFunds.get(Keys), Keys);
        		}
        	}
			/*
			 * FileWriter fw=new FileWriter("C:\\Users\\shaik\\OneDrive\\Desktop\\del.txt");
			 * MutualFunds.forEach((k,v) -> { try { fw.write(k+"     "+v+"\n"); } catch
			 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
			 * }); fw.close();
			 */
        	
        	
    		
    	}
     	
     	
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

	@Test(priority = 0, enabled=false)
	public void getBrowserWindowdimensions() throws InterruptedException {

		Point p = driver.manage().window().getPosition();
		System.out.println("Current Browser's X axis :: " + p.getX() + " And Y axis :: " + p.getY());

		Dimension d = driver.manage().window().getSize();
		System.out.println("Current Browser's height :: " + d.getHeight() + " And width :: " + d.getWidth());

	}
	
	
	@Test(priority = 0)
	public void getAllURLS() throws InterruptedException, IOException {
		
		scrollabelHeight();
		List<WebElement> linksEl=driver.findElements(By.xpath("//div[@class='s11ResultSec']/div/a"));
		List<String> links=new ArrayList<String>();
		linksEl.forEach((k) -> links.add(k.getAttribute("href")));
		FileWriter fw=new FileWriter(new File("C:\\Users\\shaik\\OneDrive\\Desktop\\del.txt"));
		links.forEach((k) -> {
			try {
				fw.write(k + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		fw.close();
		
      }
		

	
	
	

	@AfterTest(enabled=false)
	public void close() throws IOException {
        List<HashMap<String,Double>> HashMapCollection=new ArrayList<HashMap<String,Double>>();
    	
    	MFData.keySet().forEach((k) -> HashMapCollection.add(MFData.get(k)));
    	HashMapCollection.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
    	System.out.println(HashMapCollection);
		Sheet.createRow(rowNum).createCell(columnNum).setCellValue(Cap);
		columnNum += 1;
		Sheet.getRow(rowNum).createCell(columnNum).setCellValue(Companyame);
		columnNum += 1;
		Sheet.getRow(rowNum).createCell(columnNum).setCellValue(Sector);
		columnNum += 1;
		Sheet.getRow(rowNum).createCell(columnNum).setCellValue(Double.parseDouble(Percent));
		FileOutputStream fout=new FileOutputStream(new File(System.getProperty("user.dir")+"/src/main/resources/Resources/Extract.xlsx"));
    	workbook.write(fout);
    	workbook.close();
		driver.quit();

	}

}

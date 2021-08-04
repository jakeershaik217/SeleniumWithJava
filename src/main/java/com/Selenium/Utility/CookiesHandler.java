package com.Selenium.Utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.Cookie;

import io.restassured.path.json.JsonPath;

public class CookiesHandler extends GlobalDriverCap{
	
	protected File file;
	String RelativeCookiePath="//src//main//resources//Resources//Cookies.txt";
	
	public Set<Cookie> getCookies() throws IOException{
		
		System.out.println("-----------------------Cookies-------------------------------");
		file = new File(System.getProperty("user.dir") + RelativeCookiePath);
		browserCookies = webdriver.manage().getCookies();
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

	

	bw.newLine();
	bw.write("]}");
	bw.close();
		
		return browserCookies;
		
	}
	
	public void addCookies() throws JSONException, IOException {
		
		
		
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
			webdriver.manage().addCookie(cookie);
			
			
			
			
		}
		
	
		
		
		
		
		
		
	}

}

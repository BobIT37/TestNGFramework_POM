package com.qa.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	WebDriver driver;
	Properties prop;
	
	public WebDriver init_driver(String browserName) {
		
		System.out.println("Browser name is: "+ browserName);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			driver = new SafariDriver();
		}
		else {
			System.out.println("Browser name "+ browserName + " is not found");
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//driver.get("");
		
		return driver;
		
		
	}
	
	public Properties init_properites() {
		
		prop = new Properties();
		String path = "/Users/bobit/Documents/workspace/TestNGPOM/"
				+ "src/main/java/com/qa/hubspot/config/config.properties";
		
		try {
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		}catch (FileNotFoundException e) {
			System.out.println("some issue occured with config properties... Correct config file");
		}catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
}

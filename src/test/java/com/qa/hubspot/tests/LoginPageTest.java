package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.LoginPage;

public class LoginPageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	
	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_properites();
		String browserName = prop.getProperty("browser");
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
	}
	
	@Test(priority=1, description="get title as HubSpot Login")
	public void verifyPageTitleTest() throws InterruptedException {
		Thread.sleep(5000);
		String title = loginPage.getPageTitle();
		System.out.println("login page title "+ title);
		Assert.assertEquals(title, "HubSpot Login");
	}
	
	@Test(priority=2, description="verify signup lin in Login page")
	public void verifySignUpLink() {
		Assert.assertTrue(loginPage.checkSignUpLink());
	}
	
	@Test(priority=3, description="login page wrong credentials")
	public void loginTest() throws InterruptedException {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(2000);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	

}

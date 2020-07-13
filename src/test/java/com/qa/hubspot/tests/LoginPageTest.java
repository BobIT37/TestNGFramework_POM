package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

public class LoginPageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	Credentials userCred;
	
	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_properites();
		String browserName = prop.getProperty("browser");
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1, description="get title as HubSpot Login", enabled = true)
	public void verifyPageTitleTest() throws InterruptedException {
		//Thread.sleep(5000);
		String title = loginPage.getPageTitle();
		System.out.println("login page title "+ title);
		Assert.assertEquals(title, AppConstants.LOGIN_HOME_PAGE_TITLE);
	}
	
	@Test(priority=2, description="verify signup lin in Login page", enabled=true)
	public void verifySignUpLink() {
		Assert.assertTrue(loginPage.checkSignUpLink());
	}
	
	@Test(priority=3, description="login page wrong credentials", enabled=true)
	public void loginTest() {
		HomePage homePage = loginPage.doLogin(userCred);
		String accountName = homePage.getLoggedInUserAccount();
		System.out.println("logged in account name: "+ accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountname"));
		
	}
	
	@DataProvider
	public Object[][] getLoginInvalidData() {
		
		Object data[][] = {{"bill@gmail.com", "test12345"},
				           {"jimy@gmail.com", " "},
				           {" ", "test1234"},
				           {"yummy", "yummy"},
				           {" ", " "}};
		return data;
	}
	
	@Test(priority=4, dataProvider= "getLoginInvalidData", enabled=false)
	public void login_invalidTestCase(String username, String pwd) {
		
		userCred.setAppUsername(username);
		userCred.setAppPassword(pwd);
		loginPage.doLogin(userCred);
		Assert.assertTrue(loginPage.checkLoginErrorMessage());
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	

}

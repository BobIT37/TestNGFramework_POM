package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;

public class LoginPage {

	WebDriver driver;
	ElementUtil elementUtil;
	
	//1. locators
	By emailId = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	
	//Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//Page actions
	public String getPageTitle() {
		return elementUtil.doGetPageTitle();
	}
	
	public boolean checkSignUpLink() {
		return elementUtil.doIsDisplayed(signUpLink);
	}
	
	public HomePage doLogin(Credentials userCred) {
		elementUtil.doSendKeys(emailId, userCred.getAppUsername());
		elementUtil.doSendKeys(password, userCred.getAppPassword());
		elementUtil.doClick(loginButton);
		
//		driver.findElement(emailId).sendKeys(username);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginButton).click();
		
		return new HomePage(driver);
	}
	
	
	
	
	
	
}

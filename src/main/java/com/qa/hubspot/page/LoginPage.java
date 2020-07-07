package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	WebDriver driver;
	
	//1. locators
	By emailId = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	
	//Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//Page actions
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public boolean checkSignUpLink() {
		return driver.findElement(signUpLink).isDisplayed();
	}
	
	public void doLogin(String username, String pwd) {
		driver.findElement(emailId).sendKeys(username);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginButton).click();
	}
	
	
	
	
	
	
}

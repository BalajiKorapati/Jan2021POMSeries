package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utills.Constants;
import com.qa.opencart.utills.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//Constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//By Locators
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPwdLnk=By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
	
	//public page actions(methods)
	public String getLoginPageTitle() {
		return elementUtil.doGetPageTitleWithIs(10, Constants.LOGIN_PAGE_TITLE);
	}
	
	public String getLoginPageUrl() {
		return elementUtil.getPageURL();
	}
	
	public boolean isForgotPwdLinkExist() {
		return elementUtil.doIsDisplayed(forgotPwdLnk);
	}
	
	public AccountsPage doLogin(String user, String pwd) {
		elementUtil.doSendKeys(username, user);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		
		return new AccountsPage(driver);
	}
}

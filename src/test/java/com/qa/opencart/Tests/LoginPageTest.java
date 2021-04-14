package com.qa.opencart.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utills.Constants;

public class LoginPageTest extends BaseTest{
	
	@Test(priority = 1)
	public void verifyTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login page title is "+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void verifyUrlTest() {
		Assert.assertTrue(loginPage.getLoginPageUrl().contains(Constants.LOGIN_PAGE_URL));
	}
	
	@Test(priority = 4)
	public void loginToApplication(){
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 3)
	public void verifyForgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
}

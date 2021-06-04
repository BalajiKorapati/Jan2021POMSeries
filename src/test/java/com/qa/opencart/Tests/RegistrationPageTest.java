package com.qa.opencart.Tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utills.Constants;
import com.qa.opencart.utills.ExcelUtil;

public class RegistrationPageTest extends BaseTest{

	@BeforeClass
	public void setupRegister() {
		registrationPage = loginPage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		Object regData[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	//testautomation123@gmail.com
	public String getRandomNumber() {
		Random randomGenerator = new Random();
		String email = "testautomation" + randomGenerator.nextInt(1000) + "@gmail.com";
		return email;
	}
	
	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstName, String lastName, String telephone, 
									String password, String subsribe) {
		
		Assert.assertTrue(registrationPage.accountRegistration(firstName,  lastName, 
								getRandomNumber(),  telephone, password,  subsribe));
	}
}

package com.qa.opencart.Tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

public class ProductPageTest extends BaseTest{
	
	SoftAssert softAssert = new SoftAssert();
	
	@BeforeClass
	public void accPageSetup() {
		accountsPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] searchData(){
		return new Object[][] {{"Macbook"},{"iMac"}, {"iPhone"}};
	}
	
	@Test(dataProvider = "searchData")
	public void productCountTest(String productName) {
		searchResultsPage = accountsPage.doSearch(productName);
		Assert.assertTrue(searchResultsPage.getProductResultsCount() > 0);
	}
	
	@Test(enabled=false)
	public void productImagesTest() {
		searchResultsPage = accountsPage.doSearch("Macbook");
		productInfoPage = searchResultsPage.selectProductFromResults("MacBook Pro");
		Assert.assertTrue(productInfoPage.getProductImagesCount() == 4);
	}
	
	@Test(enabled=false)
	public void captureProductDetails() {
		searchResultsPage=accountsPage.doSearch("Macbook");
		productInfoPage=searchResultsPage.selectProductFromResults("MacBook Pro");
		Map<String, String> prodDetails = productInfoPage.captureProductDesDetails();
		prodDetails.forEach((k,v)->System.out.println(k+" : "+v));
		
		
		softAssert.assertEquals(prodDetails.get("Brand"), "Apple");
		softAssert.assertEquals(prodDetails.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(prodDetails.get("price"), "$2,000.00");

//		softAssert.assertAll();
	}
}

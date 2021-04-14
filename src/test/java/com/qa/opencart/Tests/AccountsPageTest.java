package com.qa.opencart.Tests;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utills.Constants;
import com.qa.opencart.utills.Error;

public class AccountsPageTest extends BaseTest{

	
	@BeforeClass
	public void accPageSetup() {
		accountsPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void verifyAccPageTitleTest() {
		String title = accountsPage.getAccPageTitle();
		System.out.println("Titile is : "+title);
		Assert.assertEquals(title, Constants.ACCOUNTS_PAGE_TITLE, Error.ACCOUNTS_PAGE_TITLE_ERROR_MSG);
	}
	
	@Test
	public void verifyAccPageHeaderTest() {
		String header=accountsPage.getAccPageHeader();
		System.out.println(header);
		Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER, Error.ACCOUNTS_PAGE_HEADER_ERROR_MSG);
	}
	
	@Test
	public void accSectionsListTest() {
		List<String> secList = accountsPage.getAccSelectionsList();
		secList.stream().forEach(e -> System.out.println(e));
		Collections.sort(Constants.EXP_ACC_SEC_LIST);
		Assert.assertEquals(secList, Constants.EXP_ACC_SEC_LIST, Error.EXP_ACC_SEC_LIST_ERROR_MSG);
	}
	
	@Test
	public void verifyLogoutLinkTest() {
		Assert.assertTrue(accountsPage.isLogoutExist());
	}
}

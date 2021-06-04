package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utills.Constants;
import com.qa.opencart.utills.ElementUtil;

public class AccountsPage {
	
	WebDriver driver;
	private ElementUtil elementUtil;

	private By accHeaders = By.cssSelector("div#content h2");
	private By header = By.cssSelector("div#logo a");
	private By logoutLink = By.linkText("Logout");
	private By searchName=By.name("search");
	private By searchButton = By.cssSelector("div#search button");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getAccPageTitle() {
		return elementUtil.waitForTitle(5, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	public void getAccPageUrl() {
		elementUtil.getPageUrl();
	}
	
	public String getAccPageHeader() {
		return elementUtil.doGetText(header);
	}
	
	public List<String> getAccSelectionsList() {
		List<String> secListi = new ArrayList<String>(); 
		List<WebElement> list = elementUtil.waitForVisibilityOfElements(accHeaders, 5);
		
		for (WebElement ele : list) {
			secListi.add(ele.getText());
		}
		Collections.sort(secListi);
		return secListi;
	}
	
	public boolean isLogoutExist() {
		return elementUtil.doIsDisplayed(logoutLink);
	}
	
	public SearchResultsPage doSearch(String productName) {
		System.out.println("Searching the product Name : "+ productName);
		elementUtil.doSendKeys(searchName, productName);
		elementUtil.doClick(searchButton);
		
		return new SearchResultsPage(driver);
		
	}
}

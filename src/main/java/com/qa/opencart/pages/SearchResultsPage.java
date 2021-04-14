package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utills.ElementUtil;

public class SearchResultsPage {

	WebDriver driver;
	private ElementUtil elementUtil;
	
	public SearchResultsPage(WebDriver driver) {
		this.driver= driver;
		elementUtil = new ElementUtil(driver);
	}
	
	private By SearchItemResult = By.cssSelector("div.product-thumb");
	private By productSearchResult = By.cssSelector("div.caption h4 a");
	
	public int getProductResultsCount() {
		return elementUtil.getElements(SearchItemResult).size();
	}
	
	public ProductInfoPage selectProductFromResults(String productName) {
		List<WebElement> productList = elementUtil.getElements(productSearchResult);
		
		for (WebElement ele : productList) {
			if(ele.getText().equals(productName)) {
				ele.click();
				break;
			}
		}
		
		return new ProductInfoPage(driver);
	}
}

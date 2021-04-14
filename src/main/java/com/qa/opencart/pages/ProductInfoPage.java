package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utills.ElementUtil;

public class ProductInfoPage {

	WebDriver driver;
	private ElementUtil elementUtil;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver= driver;
		elementUtil = new ElementUtil(driver);
	}
	
	private By productDetails=By.cssSelector("div.col-sm-4 ul:nth-of-type(1) li");
	private By productPriceDetails=By.cssSelector("div.col-sm-4 ul:nth-of-type(2) li");
	private By addToCart=By.id("button-cart");
	private By addToCartSuccessMsg=By.cssSelector("div.alert.alert-success.alert-dismissible");
	private By productImages = By.cssSelector("ul.thumbnails li img");
	
	public int getProductImagesCount() {
		return elementUtil.getElements(productImages).size();
	}
	
	public Map<String, String> captureProductDesDetails() {
		Map<String, String> productValues = new HashMap<String, String>();
		List<WebElement> productDesDetails = elementUtil.getElements(productDetails);
		for (WebElement ele : productDesDetails) {
			String details[]=ele.getText().split(":");
			String productKey=details[0];
			String productValue=details[1];
			
			productValues.put(productKey, productValue);
		}


		// price:
		List<WebElement> priceList = elementUtil.getElements(productPriceDetails);
		String price = priceList.get(0).getText().trim();
		String Exprice = priceList.get(1).getText().split(":")[1];

		productValues.put("price", price);
		productValues.put("ExTaxPrice", Exprice);

		return productValues;
	}
	
	public void addToCart() {
		elementUtil.doClick(addToCart);
	}

	public String getSuccessMessage() {
		return elementUtil.doGetText(addToCartSuccessMsg);
	}
}

package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddToCartPage {
	
	WebDriver driver;
	
	public AddToCartPage(WebDriver driver) {
		this.driver=driver;
		
	}

	By addToCart = By.id("cart");
	
	public void addToCartMethod() {
		System.out.println("Add to cart");
	}
}

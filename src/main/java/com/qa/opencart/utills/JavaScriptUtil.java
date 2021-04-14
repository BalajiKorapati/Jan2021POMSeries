package com.qa.opencart.utills;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	private WebDriver driver;
	public JavaScriptUtil(WebDriver driver) {
		this.driver=driver;
	}

	public String getTitleJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		return js.executeScript("return document.title;").toString();
	}
	
	public void generateAlert(String message) {
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		js.executeScript("alert('"+message+"')").toString();
	}
	
	public String getPageInnerText() {
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		return js.executeScript("return document.documentElement.innerText;").toString();
	}
	
	public void drawBorder(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}
	
	public void flash(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		String bgColor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 15; i++) {
			changeColor("rgb(0,200,0)", element);
			changeColor(bgColor, element);
		}
	}
	
	public void changeColor(String color, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		js.executeScript("arguments[0].style.backgroundColor ='"+ color+"'", element);
		
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	
	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		js.executeScript("arguments[0].click();", element);
	}
	
	public void sendKeysWithID(String id, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		js.executeScript("document.getElementById('"+id+"').value='"+value+"'");
	}
	
	public void scrollPageDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public void scrollPageDown(String height) {
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		js.executeScript("window.scrollTo(0,'"+height+"')");
	}
	
	public void scrollPageUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}
	
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		js.executeScript("arguments[0].scrollIntoView(true);",element);
	}
}

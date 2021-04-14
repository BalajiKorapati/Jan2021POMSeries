package com.qa.opencart.utills;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}
	/** This Method will finds the element and returns WebElement **/
	/** This Message is added from eclipse local windows machine **/
	/** This Message is added in GitHub editor **/
	
	public WebElement getElement(By locator) {
		WebElement element = null;
		
		if(DriverFactory.highlight.equals("true")) {
			
			try {
				element= driver.findElement(locator);
				jsUtil.flash(element);
			} catch (Exception e) {
				System.out.println("Element could not be fould: "+locator);
			}
		}
		return element;
	}
	
	public List<WebElement> getElements(By locator) {
		List<WebElement> elements = null;
		try {
			elements= driver.findElements(locator);
		} catch (Exception e) {
			System.out.println("Element could not be fould: "+locator);
		}
		
		return elements;
	}
	
	public String getPageURL() {
		return driver.getCurrentUrl();
	}
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}
	
	public String doGetText(By locator) {
		String text=getElement(locator).getText();
		return text;
	}
	
	public boolean doIsDisplayed(By locator) {
		boolean flag=getElement(locator).isDisplayed();
		return flag;
	}
	
	public String doGetAlertMessage(Alert alert) {
		return alert.getText();
	}
	
	//**********************Actions Utils******************************//
	
	public void doActionsSendKeys(By locator, String value) {
		
		Actions ac=new Actions(driver);
		ac.sendKeys(getElement(locator), value).perform();
	}
	public void doActionsClick(By locator) {
		
		Actions ac=new Actions(driver);
		ac.click(getElement(locator)).perform();
	}
	
	//**********************Drop Down Utils****************************//
	
	public void doSelectDropDownByVisibleText(By locator, String value) {
		WebElement element=getElement(locator);
		
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}
	
	public void doSelectDropDownByIndex(By locator, int value) {
		WebElement day=getElement(locator);
		
		Select select = new Select(day);
		select.selectByIndex(value);
	}
	
	public void doSelectDropDownByValue(By locator, String value) {
		WebElement day=getElement(locator);
		
		Select select = new Select(day);
		select.selectByValue(value);
	}
	
	public List<String> doGetDropDownOptionValues(By locator) {
		List<String> optionsList=new ArrayList<String>();
		
		Select select=new Select(getElement(locator));
		List<WebElement> dropList = select.getOptions();
		System.out.println(dropList.size());
		
		for(int i=0; i<dropList.size(); i++) {
			String text=dropList.get(i).getText();
//			System.out.println(text);
			optionsList.add(text);
		}
		
		return optionsList;
	}
	
	public void doSelectValuesFromDropDownWithoutSelectClass(By locator, String value) {
		
		List<WebElement> cListElement = getElements(locator);
//		System.out.println(cListElement.size());
		for (int i = 0; i < cListElement.size(); i++) {
			String text=cListElement.get(i).getText();
			if(text.equals(value)) {
				cListElement.get(i).click();
				break;
			}
		}
	}
	
	/************************Wait Utils*******************************/
	
	public String doGetPageTitleWithIs(int timeout, String value) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleIs(value));
		return driver.getTitle();
	}
	public String doGetPageTitleWithContains(int timeout, String value) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(value));
		return driver.getTitle();
	}
	
	public String doGetPageCurrentUrl(int timeout, String urlValue) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.urlContains(urlValue));
		return driver.getCurrentUrl();
	}
	
	public WebElement doWaitForElementToPresent(int timeout, By locator) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public WebElement doWaitForElementToVisible(int timeout, By locator) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}
	
	public List<WebElement> doWaitForElementsToVisible(int timeout, By locator) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	public WebElement doWaitForElementToClickable(int timeout, By locator) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public void doClickWhenReady(int timeout, By locator) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}
	
	public Alert doWaitForAlertMessage(int timeout) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public boolean doIsElementDisplayed(By locator, int timeOut) {
		boolean flag = false;
		for (int i = 0; i < timeOut; i++) {
			try {
				flag = driver.findElement(locator).isDisplayed();
				break;
			} catch (Exception e) {
				System.out.println("Waiting for element to be present "+i+" Seconds");
				try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
				}
			}
		}
		return flag;
	}
	
	public WebElement doGetWebElement(By locator, int timeOut) {
		WebElement element = null;
		for (int i = 0; i < timeOut; i++) {
			try {
				element = driver.findElement(locator);
				break;
			} catch (Exception e) {
				System.out.println("Waiting to get WebElement "+i+" Seconds");
				try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
				}
			}
		}
		return element;
	}
}


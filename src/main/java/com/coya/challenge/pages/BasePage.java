package com.coya.challenge.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver ; 
	public JavascriptExecutor jse ; 
	final WebDriverWait driverWait;
	public int time = 150;

	public BasePage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
		driverWait = new WebDriverWait(driver, time);
	}
	
	protected static void clickButton(WebElement button) 
	{
		button.click();
	}
	
	protected static void setTextElementText(WebElement textElement , String value) 
	{
		textElement.clear();
		textElement.sendKeys(value);
	}
	
	public void scrollToBottom() 
	
	{
		jse.executeScript("scrollBy(0,2500)"); 
	}
	
	public void clearText(WebElement element) 
	{
		element.clear();
	}
	public void waitForVisibilityOf(WebElement element) {
		driverWait.until(ExpectedConditions.visibilityOf(element));
	}
	public void goBack() {
		driver.navigate().back();
	}
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	public String getPageURL() {
		return driver.getCurrentUrl();

	}

}

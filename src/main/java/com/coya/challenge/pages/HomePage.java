package com.coya.challenge.pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage  extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver; 
	}
	@FindBy(xpath="/html/body/article/header/div/div[2]/ul/li[1]/a")
	private WebElement engLangLink;
	
	@FindBy(xpath="/html/body/article/header/div/div[2]/ul/li[3]/a")
	private WebElement deLangLink;
	
	@FindBy(xpath = "/html/body/article/header/div/nav/ul/li")
	private List<WebElement> mainMenu;
	
	 @FindBy(id="mce-EMAIL")
	 WebElement emailTextBox ; 

	 @FindBy(id="mc-embedded-subscribe")
	WebElement subscribeBtn ; 
	
	@FindBy(linkText="Jobs")
	WebElement jobsLink ; 
	
	@FindBy(css="div[class='formstatus error'] ")
	public WebElement errorMessage ; 
	
	public HomePage changeLanguage(String requiredLang) {
		
		if(requiredLang.equals("EN")) {
			waitForVisibilityOf(engLangLink);
			clickButton(engLangLink);
		}else {
			waitForVisibilityOf(deLangLink);
			clickButton(deLangLink);
		}
		
		return this;
	}
	public boolean isValidPageURL(String expectedURL) {
		return driver.getCurrentUrl().endsWith(expectedURL);

	}
	public int getMainMenuCount() {
		return mainMenu.size();
	}
	
	public void openJobsPage() 

	{
		scrollToBottom();
		clickButton(jobsLink);
	}
	public void subscribeToNewsletter(String email) 

	{
		scrollToBottom();
		waitForVisibilityOf(emailTextBox);
		setTextElementText(emailTextBox, email);
		subscribeBtn.submit();
	}
}

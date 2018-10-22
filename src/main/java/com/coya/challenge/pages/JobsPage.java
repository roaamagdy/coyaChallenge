package com.coya.challenge.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JobsPage extends BasePage{

	public JobsPage(WebDriver driver) {
		super(driver);
	}
	
	 @FindBy(xpath = "//*[@id=\"wrapper\"]/div[2]/div/h4")
	 public WebElement findJobMessage;
	 
	 @FindBy(id="searchjob")
	 WebElement searchTextBox ; 

	 @FindBy(xpath="//*[@id=\"wrapper\"]/div[2]/div/form/button")
	WebElement searchBtn ; 

	 //Get first result 
	 @FindBy(xpath = "//*[@id=\"all\"]/div[1]/div[2]/div[1]/a")
	 public WebElement jobResult;
	 
	 
	public void jobSearch(String jobName) 
	{
		waitForVisibilityOf(searchTextBox);
		setTextElementText(searchTextBox, jobName);
		clickButton(searchBtn);
	}
	
	public void openJobDetails() 
	{
		waitForVisibilityOf(jobResult);
		clickButton(jobResult);
	}
}
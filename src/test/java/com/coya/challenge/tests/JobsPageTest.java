package com.coya.challenge.tests;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.coya.challenge.pages.HomePage;
import com.coya.challenge.pages.JobsPage;

public class JobsPageTest extends BaseTest{

	HomePage homeObject ; 
	JobsPage jobsObject ; 
	
	String searchText;
	List<String> tabs;
	
	@BeforeTest
	public void init() {		
		homeObject = new HomePage(driver); 
		jobsObject = new JobsPage(driver); 
		searchText = jsonData.getData("Data").get("searchText");
	}
	
	@Test
	public void openJobsPage() {
		homeObject.openJobsPage();
		tabs=getTabs();
		
	    driver.switchTo().window(tabs.get(1)); //switches to new opened tab
	    
		//Check that page opened in English language
		assertTrue(homeObject.isValidPageURL("/?language=en"), "Invalid URL is opened");

		assertTrue(jobsObject.findJobMessage.getText().contains("FIND A JOB"));
		 driver.switchTo().window(tabs.get(0));
	}
	
	@Test(dependsOnMethods="openJobsPage")
	public void jobSearch() 
	{ 
		driver.switchTo().window(tabs.get(1)); //switches to new opened tab
		jobsObject.jobSearch(searchText);
		jobsObject.openJobDetails();
		assertTrue(driver.getPageSource().toLowerCase().contains(searchText.toLowerCase()));
		
		//Switch back to the main page
	    driver.switchTo().window(tabs.get(0));
	}
	
	
}

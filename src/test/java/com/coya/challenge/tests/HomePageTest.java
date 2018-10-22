package com.coya.challenge.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.coya.challenge.pages.HomePage;

public class HomePageTest extends BaseTest{

	HomePage homeObject ; 
	String email;
	
	@BeforeTest
	public void init() {		
		homeObject = new HomePage(driver); 
		email = jsonData.getData("Data").get("testMail");
	}
	
	@Test
	public void changeLanguage() 
	{
		homeObject=	homeObject.changeLanguage("EN");
		assertTrue(homeObject.isValidPageURL("/en"), "Invalid URL is opened");
	}
	@Test
	public void checkMenuSize() 
	{
		int menuElementsSize=	homeObject.getMainMenuCount();
		//Coya logo, Login , 3 main links
		assertEquals(menuElementsSize, 5,"Menu size is not correct");
	}
	
	@Test(dependsOnMethods="changeLanguage")
	public void subscribeToNewsletter() 
	{
		homeObject.subscribeToNewsletter(email);
		List<String>tabs=getTabs();
		driver.switchTo().window(tabs.get(1));
		assertTrue(homeObject.errorMessage.isDisplayed());
		assertTrue(homeObject.errorMessage.getText().equalsIgnoreCase("There are errors below"));
		//Close current tab (Newsletter subscription)
		driver.close();
		 
		driver.switchTo().window(tabs.get(0));
	}
}

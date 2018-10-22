package com.coya.challenge.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.coya.challenge.utils.DataReader;
import com.coya.challenge.utils.Helper;

public class BaseTest {

	public static String baseURL = "https://coya.com/" ; 
	public static DataReader jsonData;
	public static WebDriver driver;

	@BeforeSuite
	@Parameters({"browser"})
	public void initialize(@Optional("chrome") String browserName) 
	{
		jsonData = new DataReader();
		baseURL = jsonData.getData("Data").get("baseURL");
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver(); 
		}

		else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/resources/drivers/geckodriver.exe");
			driver = new FirefoxDriver(); 
		}

		else if (browserName.equalsIgnoreCase("ie")) 
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/resources/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver(); 
		}


		else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver(); 
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.navigate().to(baseURL);
	} 

	public List<String> getTabs(){
		List<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		return tabs;
	}
	
	@AfterSuite
	public void stopDriver() 
	{
		driver.quit();
	
	}

	@AfterMethod
	public void screenshotOnFailure(ITestResult result) 
	{
		if (result.getStatus() == ITestResult.FAILURE)
		{
			System.out.println("Failed!");
			System.out.println("Taking Screenshot....");
			Helper.captureScreenshot(driver, result.getName());
		}
	}
}

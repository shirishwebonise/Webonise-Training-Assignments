package com.tripadvisor.selenium;

import io.ddavison.conductor.Browser;
import io.ddavison.conductor.Config;
import io.ddavison.conductor.Locomotive;

import org.junit.AfterClass;
import org.junit.BeforeClass;
//import org.apache.commons.lang3.Validate;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;

import com.tripadvisor.selenium.HomePage;

@Config(
        browser = Browser.CHROME,
        url     = "https://www.tripadvisor.in"
)
public class TripAdvisorTest extends Locomotive{
	
//	private static WebDriver driver;
//	@BeforeClass
//	public void setUp() {
//		driver = new ChromeDriver();
//	}
//	@AfterClass
//	public void tearDown() {
//		driver.close();
//		driver.quit();
//	}
	
	@Test
    public void testTabFlightsExits() {
		validatePresent(HomePage.LOC_LNK_FLIGHTSTAB);
    }
	
	@Test
	public void testFlightsTabNavigatesToPage(){
		//WebElement tabFlights = driver.findElement(By.cssSelector(HomePage.LOC_LNK_FLIGHTSTAB));
		//tabFlights.click();
	}
}

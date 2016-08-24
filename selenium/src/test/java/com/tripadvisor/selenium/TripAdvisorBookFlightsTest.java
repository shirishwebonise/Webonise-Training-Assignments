package com.tripadvisor.selenium;
import io.ddavison.conductor.Browser;
import io.ddavison.conductor.Config;
import io.ddavison.conductor.Locomotive;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tripadvisor.selenium.HomePage;

@Config(
        browser = Browser.CHROME,
        url     = "https://www.tripadvisor.in"
)
public class TripAdvisorBookFlightsTest extends Locomotive{
	WebDriverWait wait = new WebDriverWait(driver, 60);
	
	@Test
	public void testNavigationOnClickToFlightsTab() throws Exception{
		
		clickFlightsTab();
		
		waitAndInputFromAirport("pune");
		waitAndInputToAirport("delhi");
		inputInDate(10, 9, 2016);
		inputOutDate(12, 9, 2016);
		
		Thread.sleep(1000);
		
		clickCheckFaresButton();
		
		closePopUpThatAppears();
		
		clickSortByMoreOptionsButton();
		
		Thread.sleep(1000);
		
		selectOptionBestValueOfTimeAndMoney();
		
		String rate = waitAndGetTextFromSubmitButton();
		
		System.out.println(rate);
		
		Thread.sleep(10000);
	}

	private void inputInDate(int i, int j, int k) throws Exception{
		By xPathInDate = By.xpath("//*[@id='date_picker_in_0']");
		click(xPathInDate);
		
		//*[@id="overlayInnerDiv"]/div/div[2]/table/tbody/tr[2]/td[6]/div
		
		//clickOnDate();
		
		//Thread.sleep(2000);
	}
	private void inputOutDate(int i, int j, int k) throws Exception{
		By xPathInDate = By.xpath("//*[@id='date_picker_out_0']");
		click(xPathInDate);
		
		//*[@id="overlayInnerDiv"]/div/div[2]/table/tbody/tr[2]/td[6]/div
		
		//clickOnDate();
		
		//Thread.sleep(2000);
	}

	private String waitAndGetTextFromSubmitButton() {
		By xPathRateButton = By.xpath("//*[@id='taplc_flight_list_0']/div/div[2]/div[1]/div/div/div[1]/div[2]/a/div/span");
		wait.until(ExpectedConditions.visibilityOfElementLocated(xPathRateButton));
		//wait.until(ExpectedConditions.elementToBeClickable(xPathRateButton));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingOverlay")));
		WebElement elemRateButton = driver.findElement(xPathRateButton);
		return elemRateButton.getText();
	}

	private void selectOptionBestValueOfTimeAndMoney() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='sort_sub_items']/div[2]/label")));
		WebElement elemOption = driver.findElement(By.xpath("//*[@id='sort_sub_items']/div[2]/label"));
		elemOption.click();
	}

	private void clickSortByMoreOptionsButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sort_item_more")));
		WebElement elemMore = driver.findElement(By.className("sort_item_more"));
		elemMore.click();
	}

	private void closePopUpThatAppears() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui_close_x")));
		WebElement elemCloseModal = driver.findElement(By.className("ui_close_x"));
		elemCloseModal.click();
	}

	private void clickCheckFaresButton() {
		WebElement searchFlights = driver.findElement(By.id("CHECK_FARES_BUTTON"));
		searchFlights.click();
	}

	private void waitAndInputToAirport(String toAirportText)
			throws InterruptedException {
		By bySelectorToAirportTextBox = By.cssSelector(FlightDealsPage.TEXTBOX_AIRPORT_TO);
		wait.until(ExpectedConditions.elementToBeClickable(bySelectorToAirportTextBox));
		WebElement eleToAirport = driver.findElement(bySelectorToAirportTextBox);
		eleToAirport.click();
		eleToAirport.clear();
		
		eleToAirport.sendKeys(toAirportText);
		Thread.sleep(3000);
		eleToAirport.sendKeys(Keys.ENTER);
	}

	private void waitAndInputFromAirport(String fromAirportInitialText) throws InterruptedException {
		By bySelectorFromAirportTextBox = By.cssSelector(FlightDealsPage.TEXTBOX_AIRPORT_FROM);
		wait.until(ExpectedConditions.elementToBeClickable(bySelectorFromAirportTextBox));
 		WebElement eleFromAirport = driver.findElement(bySelectorFromAirportTextBox);
 		eleFromAirport.click();
		eleFromAirport.clear();
		eleFromAirport.sendKeys(fromAirportInitialText);
		Thread.sleep(3000);
		eleFromAirport.sendKeys(Keys.ENTER);
	}

	private void clickFlightsTab() {
		By bySelectorFlightsTab = By.cssSelector(HomePage.LOC_LINK_FLIGHTSTAB);
		wait.until(ExpectedConditions.elementToBeClickable(bySelectorFlightsTab));
		WebElement eleFlightsTab = driver.findElement(bySelectorFlightsTab);
		eleFlightsTab.click();
	}
	
}

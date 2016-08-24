package com.tripadvisor.selenium;

import io.ddavison.conductor.Browser;
import io.ddavison.conductor.Config;
import io.ddavison.conductor.Locomotive;

import org.junit.Test;

@Config(
        browser = Browser.CHROME,
        url     = "https://www.tripadvisor.in/CheapFlightsHome"
)
public class FlightDealsPageTest extends Locomotive{
	@Test
	public void testPresentTextBoxesFromLocation() throws Exception {
		validatePresent(FlightDealsPage.TEXTBOX_AIRPORT_FROM);
	}
	@Test
	public void testPresentTextBoxesToLocation() throws Exception {
		validatePresent(FlightDealsPage.TEXTBOX_AIRPORT_TO);
	}
	@Test
	public void testSetTextOnTextBoxFrom(){
		setText(FlightDealsPage.TEXTBOX_AIRPORT_FROM, "Pune")
        .validateText(FlightDealsPage.TEXTBOX_AIRPORT_FROM, "Pune");
	}
	@Test
	public void testSetTextOnTextBoxTo(){
		setText(FlightDealsPage.TEXTBOX_AIRPORT_TO, "Delhi")
        .validateText(FlightDealsPage.TEXTBOX_AIRPORT_TO, "Delhi");
	}
}

package com.tripadvisor.selenium;

import org.junit.Test;
import io.ddavison.conductor.Browser;
import io.ddavison.conductor.Config;
import io.ddavison.conductor.Locomotive;

@Config(
        browser = Browser.CHROME,
        url     = "https://www.tripadvisor.in"
)
public class HomePageTest extends Locomotive{
	@Test
    public void testTabFlightsExits() {
		validatePresent(HomePage.LOC_LINK_FLIGHTSTAB);
    }
}

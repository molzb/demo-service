package com.syniverse.demo.selenium;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class SmokeTest extends SeleniumBase {

	@Test
	public void verifyDummyPage() throws IOException {
		gotoUrl(LOCAL_BASE_URL + "/hello");
		assertTrue("The dummy page should contain 'Hello World'", driver.getPageSource().contains("Hello World"));
	}

	@Test
	public void smokeManager() throws IOException {
		assertTrue(gotoUrl(LOCAL_BASE_URL + "/index.html"));
		assertTrue(gotoUrl(LOCAL_BASE_URL + "/mdmCountries.html#"));
	}
}
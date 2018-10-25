package com.syniverse.demo.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CountryPageTest extends SeleniumBase {
	@Test
	public void checkH1() throws IOException {
		assertTrue(gotoUrl(LOCAL_BASE_URL + "/mdmCountries.html"));
		String h1Text = driver.findElement(By.tagName("h1")).getText().trim();
		assertEquals("Checking the H1 Text of the Countries page", "MDM Countries", h1Text);
	} 

	@Test
	public void checkClickAddCountry() throws IOException {
		assertTrue(gotoUrl(LOCAL_BASE_URL + "/mdmCountries.html"));
		WebElement btn = driver.findElement(By.id("btnAddCountry"));
		btn.click();

		sleep(1000);

		WebElement modalAddCountry = driver.findElement(By.id("modalCountry"));
		assertTrue("When clicking 'Add Country', a modal comes up", modalAddCountry.isDisplayed());

		WebElement closeButton = driver.findElement(By.id("btnClose"));
		closeButton.click();
		sleep(500);
		assertTrue("When clicking 'Close', the modal window closes", !modalAddCountry.isDisplayed());
	}

	@Test
	public void checkTableSearch() throws IOException {
		assertTrue(gotoUrl(LOCAL_BASE_URL + "/mdmCountries.html"));

		WebElement countriesTable = driver.findElement(By.id("tblCountries"));
		assertTrue(countriesTable.isDisplayed());

		WebElement numberOfRowsSpan = driver.findElement(By.id("numberOfRows"));
		int numberOfRows = Integer.parseInt(numberOfRowsSpan.getText());
		assertTrue("There should be 30<x<100 rows in the countries table, but there are" + numberOfRows + " rows",
				numberOfRows > 30 && numberOfRows < 100);

		WebElement txtSearch = driver.findElement(By.id("txtSearch"));
		txtSearch.sendKeys("ARM");
		sleep(500);
		numberOfRows = Integer.parseInt(numberOfRowsSpan.getText());
		assertEquals("After filtering for 'ARM', the countries table should contain only 1 row.", 1, numberOfRows);
		takeScreenshot("afterSearch");
	}

}
package com.syniverse.demo.selenium.loesungen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.syniverse.demo.selenium.SeleniumBase;

public class SyniverseDotComTest extends SeleniumBase {
	// TODO Wir gehen auf die Webseite http://central.syniverse.com/Pages/home.aspx
	//      und nutzen die Suchfunktion dieser Seite, um den Begriff "Awesome" zu finden

	@Test
	public void testSyniverseDotCom() throws IOException, InterruptedException {
		// TODO 1.1) Gehe auf URL http://central.syniverse.com/Pages/home.aspx, 
		// 		   benutze die richtige Methode aus SeleniumBase
		//		   Schreibe "this." + String/Leertaste, um die verfügbaren Methoden anzuzeigen
		this.gotoUrl("http://central.syniverse.com/Pages/home.aspx");
		//		1.2) Überprüfe den Webseitentitel mit assertEquals
		assertEquals(driver.getTitle(), "Home"); 
		
		// TODO 2.1) Wähle das Webelement für die Suche,
		//			 die ID ist "ctl00_PlaceHolderSearchArea_ctl01_S3031AEBB_InputKeywords"
		// 		   Stelle sicher (assertTrue aus JUnit), dass das Element sichtbar ist
		WebElement inputKeywords = driver.findElement(By.cssSelector("#ctl00_PlaceHolderSearchArea_ctl01_S3031AEBB_InputKeywords"));
		assertTrue(inputKeywords.isDisplayed());
		// TODO 2.2) Schreibe in das Eingabefeld für die Suche den Text "Awesome"
		inputKeywords.sendKeys("Awesome");
//		inputKeywords.sendKeys(Keys.RETURN);

		// TODO 3.1) Klicke auf den Suchbutton mit der ID ctl00_PlaceHolderSearchArea_ctl01_S3031AEBB_go
		WebElement searchBtn = driver.findElement(By.id("ctl00_PlaceHolderSearchArea_ctl01_S3031AEBB_go"));
		//      3.2) assertTrue, ob er angezeigt ist
		assertTrue(searchBtn.isDisplayed());
		//	    3.3) Auf Suchbutton klicken
		searchBtn.click();
		Thread.sleep(5000L);
		
		// TODO 4.1) Du solltest jetzt auf der Ergebnisseite sein.
		// 			 Nimm den Ergebnistext (das Element hat die Klasse "ms-descriptiontext")
		WebElement searchResultDescription = driver.findElement(By.className("ms-descriptiontext"));
		//			 und validiere (assertTrue oder assertEquals) das Ergebnis, nutze getText() von WebElement
		assertEquals(searchResultDescription.getText(), "The search request was unable to connect to the Search Service.");
		
		// TODO 5.1) Mache einen Screenshot
		takeScreenshot("awesome");
		Thread.sleep(5000L);
	}
}

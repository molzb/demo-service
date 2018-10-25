package com.syniverse.demo.selenium.uebungen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.syniverse.demo.selenium.SeleniumBase;

/**
 * TODO Wir gehen auf die Webseite http://central.syniverse.com/Pages/home.aspx
 *		und nutzen die Suchfunktion dieser Seite, um den Begriff "Awesome" zu finden
 */
public class SyniverseDotComTest extends SeleniumBase {

	@Test
	public void testSyniverseDotCom() throws IOException, InterruptedException {
		// TODO 1.1) Gehe auf URL http://central.syniverse.com/Pages/home.aspx, 
		// 		   benutze die richtige Methode aus SeleniumBase.
		//		   Schreibe "this." + String/Leertaste, um die verfügbaren Methoden anzuzeigen
		// this.aufURLgehen(URL)

		//		1.2) Überprüfe den Webseitentitel mit assertEquals / assertTrue, 
		//			 benutze das driver-Objekt der Basisklasse
		// assertIrgendwas(TODO); 

		
		// TODO 2.1) Wähle das Webelement für die Suche,
		//			 die ID ist "ctl00_PlaceHolderSearchArea_ctl01_S3031AEBB_InputKeywords".
		WebElement inputKeywords = null; // Nutze driver.findElement(TODO);
		// 		   Stelle sicher (assertTrue / assertEquals aus JUnit), dass das Element sichtbar ist
		//assertTrue/assertEquals(TODO);

		// TODO 2.2) Schreibe in das Eingabefeld für die Suche den Text "Awesome"
		//inputKeywords.schreib("Awesome");


		// TODO 3.1) Klicke auf den Suchbutton mit der ID ctl00_PlaceHolderSearchArea_ctl01_S3031AEBB_go
		WebElement searchBtn = null; // driver.findElement(TODO);
		//      3.2) assertTrue, ob er angezeigt ist
		//			 ...
		//	    3.3) Auf Suchbutton klicken
		//			...
		Thread.sleep(2000L);

		// TODO 4.1) Du solltest jetzt auf der Ergebnisseite sein.
		// 			 Nimm den Ergebnistext (das Element hat die Klasse "ms-descriptiontext")
		// WebElement searchResultDescription = ...;
		//			 und validiere (assertTrue oder assertEquals) das Ergebnis, nutze getText() von WebElement
		//assertIrgendwas(was im Ergebnistext drinsteht);
		
		// TODO 5.1) Mache einen Screenshot
		// this. (Strg+Leertaste)
	}
	
	public void ignoreThat() {
		assertEquals(1, 1);
		assertTrue(1 == 1);
	}
}

package com.syniverse.demo.selenium.uebungen;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// Aufgabe: Wir wollen die Seite www.microsoft.com im Internet Explorer testen
// TODO
// 1) pom.xml:  Füge eine neue Dependency für den Internet-Explorer-WebDriver hinzu.
//				Das Artifakt heißt <artifactId>selenium-ie-driver</artifactId>.
//				Du solltest in Eclipse (links) unter Maven Dependencies diesen Eintrag sehen:
// 					selenium-ie-driver-3.9.1

// 					<dependency>
//	 					<groupId>org.seleniumhq.selenium</groupId>
//	 					<artifactId>selenium-ie-driver</artifactId>
//	 					<scope>test</scope>
//					</dependency>
// 2) Kopiere den IEDriverServer64.exe (in demo-service/seleniumNativeDriver) nach /java/selenium
// 3) Siehe Methode testMicrosoftPageInIE()
public class MinimalIT {
	@Test
	public void testGooglePageInChrome() {
		System.setProperty("webdriver.chrome.driver", "C:/java/selenium/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 860));
		driver.get("https://www.google.de");
		assertEquals(driver.getTitle(), "Google");
		driver.quit();
	}

	@Test
	public void testMicrosoftPageInIE() {
		// TODO 3.1) System-Property für webdriver.ie.driver richtig setzen
		System.setProperty("webdriver.ie.driver", "TODO");

		// TODO 3.2) 'WebDriver' doppelklicken und 'Ctrl+T' (zeigt abgeleitete Klassen) drücken. 
		//			 Wie heißt die richtige Klasse für den Internet Explorer?
		WebDriver driver = new ChromeDriver();	// ChromeDriver durch passenden IEDriver ersetzen 
		driver.manage().window().setSize(new Dimension(1200, 860));

		// TODO 3.3) microsoft.com aufrufen und den Titel prüfen
		driver.get("TODO");
		assertEquals(driver.getTitle(), "TODO");

		driver.quit();
	}
}

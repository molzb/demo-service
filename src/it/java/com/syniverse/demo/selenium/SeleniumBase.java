package com.syniverse.demo.selenium;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SeleniumBase {
	private static Logger logger = LoggerFactory.getLogger(SeleniumBase.class.getName());

	protected static final String LOCAL_BASE_URL = "http://localhost:8888";
	protected static final int SLEEP = 5000;
	private static final File SCREENSHOT_DIR = new File("screenshots");
	private static SimpleDateFormat sdfTime = new SimpleDateFormat("HH_mm_ss");

	protected static WebDriver driver;

	@BeforeClass
	public static void beforeClass() {
		if (SCREENSHOT_DIR.mkdir()) {
			System.out.println(SCREENSHOT_DIR.getAbsolutePath() + " created");
		}
	}

	@Before
	public void setUpClass() {
		System.setProperty("webdriver.chrome.driver", "C:/java/selenium/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, SECONDS);
		driver.manage().window().setSize(new Dimension(1200, 860));
	}

	@After
	public void tearDownClass() {
		driver.quit();
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	protected void takeScreenshot(String name) throws IOException {
		String suffix = sdfTime.format(new Date()) + ".png";
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(SCREENSHOT_DIR, name + suffix);
		Files.copy(Paths.get(screenshotFile.toURI()), new BufferedOutputStream(new FileOutputStream(destFile)));
		logger.info(destFile.getAbsolutePath());
	}

	public boolean gotoUrl(String url) throws IOException {
		driver.get(url);
		logger.info("gotoUrl " + url);
		sleep(SLEEP);
		takeScreenshot(url.substring(url.lastIndexOf('/')));
		return driver.getCurrentUrl().equals(url);
	}

	protected void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ex) {
			logger.warn(ex.getMessage(), ex);
		}
	}
}

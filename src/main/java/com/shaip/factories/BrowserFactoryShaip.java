package com.shaip.factories;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public final class BrowserFactoryShaip {

	private BrowserFactoryShaip()
	{
		
		
	}


	public static WebDriver createBrowserInstance(String browser) throws MalformedURLException {

		// WebDriver driver = null;
		RemoteWebDriver driver = null;

		if (browser.equalsIgnoreCase("Chrome")) {

			System.out.println("chrome browser will gonna open");
			System.out.println("OS NAME--->" + System.getProperty("os.name"));

			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.silentOutput", "true");
			ChromeOptions options = new ChromeOptions();

			 options.setExperimentalOption("useAutomationExtension", false);
			 options.setExperimentalOption("excludeSwitches",
			 Collections.singletonList("enable-automation")); Map<String, Object> prefs =
			 new HashMap<String, Object>(); prefs.put("credentials_enable_service",false); prefs.put("profile.password_manager_enabled", false);
			 options.setExperimentalOption("prefs", prefs);
			 
			driver = new ChromeDriver(options);

		} else if (browser.equalsIgnoreCase("ChromeHeadless")) {

			System.out.println("OS NAME--->" + System.getProperty("os.name"));

			WebDriverManager.chromedriver().setup();

			System.setProperty("webdriver.chrome.silentOutput", "true");
			final ChromeOptions chromeOptions = new ChromeOptions();
	        chromeOptions.addArguments("--headless");
			chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
	        chromeOptions.addArguments("disable-infobars");
	        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
	        chromeOptions.setExperimentalOption("useAutomationExtension", false);
	        chromeOptions.addArguments("--disable-gpu");
	        chromeOptions.addArguments("--disable-extensions");
	        chromeOptions.addArguments("--no-sandbox");
	        chromeOptions.addArguments("--disable-dev-shm-usage");
	        chromeOptions.addArguments("--window-size=1536x864");
	        chromeOptions.addArguments("--always-authorize-plugins");
	        chromeOptions.addArguments("enable-automation");
	        chromeOptions.addArguments("--disable-browser-side-navigation");
			driver = new ChromeDriver(chromeOptions);

		}

		else if (browser.equalsIgnoreCase("ffHeadless")) {

		
			WebDriverManager.firefoxdriver().setup();

			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("-private");
			firefoxOptions.addArguments("--headless");
			firefoxOptions.addArguments("--disable-web-security");
	        firefoxOptions.addArguments("--allow-running-insecure-content");
	        final FirefoxProfile profile = new FirefoxProfile();
	        profile.setAcceptUntrustedCertificates(true);
	        profile.setAssumeUntrustedCertificateIssuer(false);
	        profile.setPreference("pageLoadStrategy", "normal");
	        driver = new FirefoxDriver(firefoxOptions);


		}
		
		else if (browser.equalsIgnoreCase("ff")) {

			
			WebDriverManager.firefoxdriver().setup();

			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("--disable-web-security");
	        firefoxOptions.addArguments("--allow-running-insecure-content");
	        final FirefoxProfile profile = new FirefoxProfile();
	        profile.setAcceptUntrustedCertificates(true);
	        profile.setAssumeUntrustedCertificateIssuer(false);
	        profile.setPreference("pageLoadStrategy", "normal");
	        driver = new FirefoxDriver(firefoxOptions);


		}
		else if (browser.equalsIgnoreCase("ie")) {

			WebDriverManager.iedriver().setup();
			InternetExplorerOptions iOptions = new InternetExplorerOptions();
			iOptions.addCommandSwitches("-private");

			driver = new InternetExplorerDriver(iOptions);
		}
		else if (browser.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.setCapability("UseChromium", true);
			edgeOptions.setHeadless(true);
		    driver = new EdgeDriver(edgeOptions);

		}
		else if (browser.equalsIgnoreCase("edgeheadless")) {

			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.setCapability("UseChromium", true);
			edgeOptions.setHeadless(true);
		    driver = new EdgeDriver(edgeOptions);

		}
		return driver;
	}

}

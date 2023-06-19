package com.shaip.factories;

import java.net.MalformedURLException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.shaip.base.FrameworkContstants;

public final class DriverBase{

	private DriverBase() {}

	public static WebDriverWait wait;
	public static  Capabilities cap;

	
	
	public static  void initDrivers(String browser) throws Exception {

		System.out.println("In Test base shaip  " + browser);

		if (Objects.isNull(DriverFactoryShaip.getDriver())) {
			try {
				DriverFactoryShaip.setDriver(BrowserFactoryShaip.createBrowserInstance(browser));

			} catch (MalformedURLException e) {
				throw new Exception("Please check the capabilities of browser");
			}
			
		}
	
		wait = new WebDriverWait(DriverFactoryShaip.getDriver(), FrameworkContstants.getExplicitwait());
		System.out.println("Browser inoked is--------->  " + browser);
		DriverFactoryShaip.getDriver().manage().window().maximize();
		//DriverFactoryShaip.getDriver().manage().deleteAllCookies();
		DriverFactoryShaip.getDriver().manage().timeouts().implicitlyWait(FrameworkContstants.getImplicitwait());
	    cap = ((RemoteWebDriver) DriverFactoryShaip.getDriver()).getCapabilities();


		

	}
	
	public static Capabilities getCapabilities() {


	    cap = ((RemoteWebDriver) DriverFactoryShaip.getDriver()).getCapabilities();
		return cap;

	}
	
	public static void quitDriver() {


		if (Objects.nonNull(DriverFactoryShaip.getDriver())) {
			System.out.println("After method setup going to be finished");
			DriverFactoryShaip.closeBrowser();
		}
	}

	


	
}

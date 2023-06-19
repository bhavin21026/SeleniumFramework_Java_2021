package com.shaip.factories;

import java.util.Objects;

import org.openqa.selenium.WebDriver;

public final class DriverFactoryShaip {

	// Singleton design Pattern
	// private constructor so that no one else can create object of this class
	

	private DriverFactoryShaip() {}

	// factory design pattern --> define separate factory methods for creating
	// objects and create objects by calling that methods
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public static WebDriver getDriver() {

		return driver.get();
	}

	 public static void setDriver(WebDriver driverParm) {
		if (Objects.nonNull(driverParm)) {
			driver.set(driverParm);
		}
	}

	 public static void closeBrowser() {
		driver.get().quit();
		driver.remove();
	}
}

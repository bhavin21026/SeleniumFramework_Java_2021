package com.shaip.base;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaip.factories.DriverFactoryShaip;

public class ShaipTestDataMaker {

	protected ShaipTestDataMaker() {

	}

	protected  String getDate()

	{

		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyHHmmss");
		Date date = new Date();
		String actualDate = format.format(date);
		return actualDate;

	}
	
	protected String trimDate(String date)

	{

		
		String[] parts = date.split("/");

		String year = parts[2].substring(parts[2].length() - 2);

		String newDate = parts[0] + "/" + parts[1] + "/" + year;
		
		return newDate;

	}



	
	protected String getTomorrow()

	{

		SimpleDateFormat format = new SimpleDateFormat("dd");
		Date date = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		String actualDate = format.format(date);
		return actualDate;

	}
	
	protected String getTomorrowMonthYear()

	{

		SimpleDateFormat format = new SimpleDateFormat("MMM YYYY");
		Date date = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		String actualDate = format.format(date);
		return actualDate;

	}
	
	protected String getDayAfterTomorrowMonthYear()

	{

		SimpleDateFormat format = new SimpleDateFormat("MMM YYYY");
		Date date = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 2);
		date = c.getTime();
		String actualDate = format.format(date);
		return actualDate;

	}
	
	protected String getDayAfterTomorrow()

	{

		SimpleDateFormat format = new SimpleDateFormat("dd");
		Date date = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE,2);
		date = c.getTime();
		String actualDate = format.format(date);
		return actualDate;

	}

	
	protected String getCurrentMonthYear()

	{

		SimpleDateFormat format = new SimpleDateFormat("MMM YYYY");
		Date date = new Date();
		String actualDate = format.format(date);
		System.out.println(actualDate);
		return actualDate;

	}
	
	public String generateNewEmail()

	{

		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyHHmmss");
		Date date = new Date();
		String actualDate = format.format(date);
		String email = "automation.shaip+" + actualDate + "@shaip.com";
		return email;

	}

	protected String generateNewCustomerName()

	{

		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyHHmmss");
		Date date = new Date();
		String actualDate = format.format(date);

		String customer = "Automation Customer_" + actualDate;
		return customer;

	}

	protected String generateNewVendorName()

	{

		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyHHmmss");
		Date date = new Date();
		String actualDate = format.format(date);

		String vendor = "Automation Vendor" + actualDate;
		return vendor;

	}

	protected String captureScreenshot() {

		File src = ((TakesScreenshot) DriverFactoryShaip.getDriver()).getScreenshotAs(OutputType.FILE);
		byte[] fileContent = null;
		try {
			fileContent = FileUtils.readFileToByteArray(src);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String Base64StringofScreenshot = "data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);

		String testcaseName = "FailedTestCase";

		String screenshotPath = FrameworkContstants.getScreenShotPath() + testcaseName + ".jpeg";

		File dest = new File(screenshotPath);

		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Base64StringofScreenshot;

	}

	protected String captureScreenshotCustom(String name, String role) {

		File src = ((TakesScreenshot) DriverFactoryShaip.getDriver()).getScreenshotAs(OutputType.FILE);
		byte[] fileContent = null;
		try {
			fileContent = FileUtils.readFileToByteArray(src);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String Base64StringofScreenshot = "data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);

		String testcaseName = "FailedTestCase_" + name + "_" + role;

		String screenshotPath = FrameworkContstants.getScreenShotPath() + testcaseName + ".jpeg";

		File dest = new File(screenshotPath);

		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Base64StringofScreenshot;

	}

}

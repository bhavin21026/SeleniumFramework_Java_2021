package com.shaip.basetest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaip.base.FrameworkContstants;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.reportng.ExtentFactoryShaip;

public class TestCommons {

	// extent operations
	
	protected TestCommons() {}

	

	protected ExtentTest setupExtentReport(String testName, String desc, String author) {

		return ExtentFactoryShaip.startTest(testName, desc, author);

	}
	
	protected ExtentTest setupChildExtentReport(String testName, String desc, String author) {

		return ExtentFactoryShaip.startTest(testName, desc, author);

	}

	protected static void giveTestDataAndRoleInfo(HashMap<String, String> testData, String key, String role) {

		ExtentFactoryShaip.getTest().info(MarkupHelper.createLabel("USER ==> " + testData.get(role), ExtentColor.PINK));
		ExtentFactoryShaip.getTest()
				.info(MarkupHelper.createLabel("EXECUTED SCINARIO ==> " + testData.get(key), ExtentColor.PINK));
		ExtentFactoryShaip.getTest()
				.info(MarkupHelper.createLabel("Test Data used for this execution is as below", ExtentColor.INDIGO));
		ExtentFactoryShaip.getTest().info(MarkupHelper.createOrderedList(testData).getMarkup());

	}

	protected static void giveTestDataInfo(HashMap<String, String> testData, String key, String role) {

		ExtentFactoryShaip.getTest().info(MarkupHelper.createLabel("USER ==> " + role, ExtentColor.PINK));
		ExtentFactoryShaip.getTest()
				.info(MarkupHelper.createLabel("EXECUTED SCINARIO ==> " + testData.get(key), ExtentColor.PINK));
		ExtentFactoryShaip.getTest()
				.info(MarkupHelper.createLabel("Test Data used for this execution is as below", ExtentColor.INDIGO));
		ExtentFactoryShaip.getTest().info(MarkupHelper.createOrderedList(testData).getMarkup());

	}

	protected static void giveTestRoleInfo(HashMap<String, String> testData, String role) {

		ExtentFactoryShaip.getTest().info(MarkupHelper.createLabel("USER ==> " + testData.get(role), ExtentColor.PINK));

		ExtentFactoryShaip.getTest()
				.info(MarkupHelper.createLabel("Test Data used for this execution is as below", ExtentColor.INDIGO));
		ExtentFactoryShaip.getTest().info(MarkupHelper.createOrderedList(testData).getMarkup());

	}

	protected static void giveUserInfo(String role) {

		ExtentFactoryShaip.getTest().info(MarkupHelper.createLabel("USER ==> " + role, ExtentColor.PINK));

	}

	protected void navigateTo(String linkName) {
		try {

			DriverFactoryShaip.getDriver().navigate().to(linkName);
			ExtentFactoryShaip.getTest().log(Status.PASS, "Navigation of link done Successfully! ");
		} catch (Exception e) {
			// log failure in extent
		}
	}

	protected List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {

		// convert json file content to json string
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}

	protected Object[][] getData(String portal, String filename) throws IOException {

		List<HashMap<String, String>> l = getJsonData(
				FrameworkContstants.getJsonDataPath() + portal + "/" + filename + ".json");

		Object[][] obj = new Object[l.size()][1];

		Iterator<HashMap<String, String>> iterator = l.iterator();
		int i = 0;
		// iterate AL using while-loop
		while (iterator.hasNext()) {

			HashMap<String, String> testData = iterator.next();
			obj[i][0] = testData;
			i++;

		}

		return obj;

	}
	
	
	

}

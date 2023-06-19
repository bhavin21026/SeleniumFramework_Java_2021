package com.shaip.reportng;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.shaip.annotations.FrameworkAnnotation;
import com.shaip.enums.CategoryType;
import com.shaip.enums.PortalType;
import com.shaip.enums.story;

/**
 * extentTestMap holds the information of thread ids and ExtentTest instances.
 * ExtentReports instance created by calling createExtentReports() method from
 * ExtentManager. At startTest() method, an instance of ExtentTest created and
 * put into extentTestMap with current thread id. At getTest() method, return
 * ExtentTest instance in extentTestMap by using current thread id.
 */
public final class ExtentFactoryShaip {

	private ExtentFactoryShaip() {
	}

	private static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
	private static ExtentReports extentObject = ExtentReportngShaip.createExtentReports();

	public static synchronized ExtentTest getTest() {

		ExtentTest test = extentTestMap.get((int) (long) Thread.currentThread().getId());
		return test;

	}

	

	public static synchronized void endTest() {
		extentObject.removeTest(extentTestMap.get((int) (long) Thread.currentThread().getId()));
	}

	public static synchronized ExtentTest startTest(String testName, String desc, String author) {
		
		System.out.println("TEST CASE STARTED-----> " +desc+" For user "+"("+author+")");
		ExtentTest test = extentObject.createTest(testName, desc).assignAuthor(author);
		extentTestMap.put((int) Thread.currentThread().getId(), test);
		return test;
	}
	
	public static synchronized ExtentTest startChildTest(String testName, String desc, String author,String category, String type,String portal) {
		System.out.println("TEST CASE STARTED-----> " +desc+" For user "+"("+author+")");

		ExtentTest test = extentObject.createTest(testName, desc).assignAuthor(author).assignCategory(category).assignDevice(type).assignDevice(portal);
		extentTestMap.put((int) Thread.currentThread().getId(), test);
		return test;
	}

	public static void addCategories(CategoryType[] categories) {
		for (CategoryType temp : categories) {
			ExtentFactoryShaip.getTest().assignDevice(temp.toString());
		}
	}

	public static void addStory(story[] storyId) {
		for (story temp : storyId) {
			ExtentFactoryShaip.getTest().assignCategory(temp.toString());
		}
	}

	public static void addPortal(PortalType[] portal) {
		for (PortalType temp : portal) {
			ExtentFactoryShaip.getTest().assignDevice(temp.toString());

		}
	}

	public static void addTestDetails(ITestResult result) {

		ExtentFactoryShaip.addCategories(result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(FrameworkAnnotation.class).category());
		ExtentFactoryShaip.addStory(result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(FrameworkAnnotation.class).storyId());
		ExtentFactoryShaip.addPortal(result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(FrameworkAnnotation.class).portal());

	}

}

package com.shaip.base;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.reportng.ExtentFactoryShaip;

public class ActionEngineShaip extends ShaipTestDataMaker {

	protected ActionEngineShaip() {

	}

	@FindBy(xpath = "//div[@class='content']//p")
	WebElement toaster_Message;
	
	@FindBy(xpath = "//div[@class='toast-root']")
	WebElement toaster_root;

	@FindBy(xpath = "//mat-icon[text()='clear']")
	WebElement btn_closeToaster;
	
	@FindBy(xpath ="//div[@id='loaderWrapper' and contains(@class,'loader-wrapper')]")
	WebElement progressbar;
	

	protected SoftAssert sa = new SoftAssert();
	protected static WebDriverWait wait = new WebDriverWait(DriverFactoryShaip.getDriver(),
			FrameworkContstants.getExplicitwait());
	protected static WebDriverWait wait2 = new WebDriverWait(DriverFactoryShaip.getDriver(),
			FrameworkContstants.getExplicitwait2());

	// Customized sendkeys method-> To log sendkeys message for every occ.
	protected void sendKeys_custom(WebElement element, String fieldName, String valueToBeSent) {
		try {

			element.sendKeys(valueToBeSent);
			// ExtentTest test=ExtentFactoryShaip.getTest();
			// System.out.println("lets check");
			// log success message in exgent report
			System.out.println( fieldName + "==> Ented value as:==>  " + valueToBeSent);
			ExtentFactoryShaip.getTest().log(Status.PASS, fieldName + "==> Ented value as:==>  " + valueToBeSent);
		} catch (Exception e) {
			// log failure in extent
		}
	}

	// custom click method to log evey click action in to extent report
	protected void click_custom(WebElement element, String fieldName) {
		try {

			element.click();
			// log success message in exgent report
			// ExtentTest test=ExtentFactoryShaip.getTest();
			// System.out.println("lets check");
			System.out.println(fieldName + "==> Clicked Successfully! ");

			ExtentFactoryShaip.getTest().log(Status.PASS, fieldName + "==> Clicked Successfully! ");
		} catch (Exception e) {
			// log failure in extent
		}
	}

	protected void doubleClick_custom(WebElement element, String fieldName, WebDriver driver) {
		try {

			Actions act = new Actions(driver);
			act.doubleClick(element).build().perform();
			// log success message in exgent report
			// ExtentTest test=ExtentFactoryShaip.getTest();
			// System.out.println("lets check");
			ExtentFactoryShaip.getTest().log(Status.PASS, fieldName + "==> Clicked Successfully! ");
		} catch (Exception e) {
			// log failure in extent
		}
	}

	// clear data from field
	protected void clear_custom(WebElement element, String fieldName) {
		try {
			element.clear();
			Thread.sleep(250);
			System.out.println(fieldName + "==> Data Cleared Successfully! ");

			ExtentFactoryShaip.getTest().log(Status.PASS, fieldName + "==> Data Cleared Successfully! ");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// custom mouseHover
	protected void moveToElement_custom(WebElement element, String fieldName) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) DriverFactoryShaip.getDriver();
			executor.executeScript("arguments[0].scrollIntoView(true);", element);
			Actions actions = new Actions(DriverFactoryShaip.getDriver());
			actions.moveToElement(element).build().perform();
			ExtentFactoryShaip.getTest().log(Status.PASS, fieldName + "==> Mouse hovered Successfully! ");
			Thread.sleep(1000);
		} catch (Exception e) {

		}
	}

	protected void execute_click(WebElement element, String fieldName) {
		try {

			JavascriptExecutor executor = (JavascriptExecutor) DriverFactoryShaip.getDriver();
			executor.executeScript("arguments[0].click();", element);
			System.out.println(fieldName + "==> Clicked Successfully! ");

			ExtentFactoryShaip.getTest().log(Status.PASS, fieldName + "==> Clicked Successfully! ");
		} catch (Exception e) {
			// log failure in extent
		}
	}
	
	protected String execute_getText(WebElement element, String fieldName) {
		try {

			JavascriptExecutor executor = (JavascriptExecutor) DriverFactoryShaip.getDriver();
			String text = (String) executor.executeScript("return arguments[0].value", element);  
			System.out.println("Text written on the toster message is- " + text);
			ExtentFactoryShaip.getTest().log(Status.PASS, fieldName + "==> Clicked Successfully! ");
			return text;
		} catch (Exception e) {
			// log failure in extent
			return null;
		}
	}

	// check if element is Present
	protected boolean isElementPresent_custom(WebElement element, String fieldName) {
		boolean flag = false;
		try {
			flag = element.isDisplayed();
			ExtentFactoryShaip.getTest().log(Status.PASS, fieldName + "==> Presence of field is: " + flag);
			return flag;
		} catch (Exception e) {
			return flag;
		}
	}

	// String Asserts
	protected void assert_custom(String actualValue, String expvalue, String locatorName) {

		System.out.println("In assert custom method");

		if (actualValue.equalsIgnoreCase(expvalue.trim())) {
			System.out.println("Assert match");

			sa.assertEquals(actualValue, expvalue);
			ExtentFactoryShaip.getTest().log(Status.PASS, "String Assertion is successful on field " + locatorName
					+ " Expected value was: " + expvalue + " actual value is: " + actualValue);
			sa.assertAll();

		}

		else {
			System.out.println("Assert fail....");

			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");

			ExtentFactoryShaip.getTest().log(Status.FAIL,
					MarkupHelper.createLabel("String Assertion is unsuccessful on field " + locatorName
							+ " Expected value was: " + expvalue + " actual value is: " + actualValue,
							ExtentColor.RED));

			sa.assertAll();

		}

	}

	protected void assert_contains(String actualValue, String expvalue, String locatorName) {

		if (actualValue.contains(expvalue.trim())) {

			ExtentFactoryShaip.getTest().log(Status.PASS, "String Assertion is successful on field " + locatorName
					+ " Expected value was: " + expvalue + " actual value is: " + actualValue);
			sa.assertAll();

		}

		else {

			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL,
					MarkupHelper.createLabel("String Assertion is unsuccessful on field " + locatorName
							+ " Expected value was: " + expvalue + " actual value is: " + actualValue,
							ExtentColor.RED));
			sa.assertAll();

		}
	}

	// Get text from webelement
	protected String getText_custom(WebElement element, String fieldName) {
		String text = "";
		try {
			text = element.getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ");
			ExtentFactoryShaip.getTest().log(Status.PASS, fieldName + "==> Retrived Text is: " + text);
			System.out.println( fieldName + "==> Retrived Text is: " + text);

			return text;
		} catch (Exception e) {

		}
		return text;
	}

	protected String getAttributes(WebElement element, String fieldName) {
		String text = "";
		try {
			text = element.getAttribute("value");
			ExtentFactoryShaip.getTest().log(Status.PASS, fieldName + "==> Retrived Text is: " + text);
			System.out.println( fieldName + "==> Retrived Text is: " + text);

			return text;
		} catch (Exception e) {

		}
		return text;
	}

	protected void waitForVisibility(WebElement element) {

		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			ExtentFactoryShaip.getTest().log(Status.INFO, element.toString()+"is visble on the screen");
			System.out.println( element.toString()+"is visble on the screen");

		} catch (WebDriverException e) {

			e.printStackTrace();

		}

	}
	
	protected void waitMoreForVisibility(WebElement element) {

		try {
			wait2.until(ExpectedConditions.visibilityOf(element));
			ExtentFactoryShaip.getTest().log(Status.INFO, element.toString()+"is visble on the screen");
			System.out.println( element.toString()+"is visble on the screen");

		} catch (WebDriverException e) {

			e.printStackTrace();

		}

	}

	protected void waitForVisibilityOfAll(List<WebElement> element) {

		wait.until(ExpectedConditions.visibilityOfAllElements(element));

	}

	protected void waitUntilClickable(WebElement element) {

		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			System.out.println( element.toString()+"is Clickable");

		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		 catch (TimeoutException e) {
			e.printStackTrace();
		}
	}

	protected void waitForProcessBarToGo() throws InterruptedException {

		WebElement processBar = null;
		try {
			
			processBar=DriverFactoryShaip.getDriver()
					.findElement(By.id("loaderIcon"));
			
			if (wait.until(ExpectedConditions.visibilityOf(processBar))!= null) {
				System.out.println("processbar displayed");

				wait.until(ExpectedConditions.invisibilityOf(processBar));
				System.out.println("processbar gone");
				//Thread.sleep(1000);

			}
		} catch (NoSuchElementException e) {
			//e.printStackTrace();
		}
		 catch (TimeoutException e) {
			//e.printStackTrace();
		}

	}
	
	protected void waitForProcessBar() throws InterruptedException {

		WebElement processBar = null;
		try {
			processBar = DriverFactoryShaip.getDriver()
					.findElement(By.id("loaderIcon"));

			if (wait.until(ExpectedConditions.visibilityOf(processBar)) != null) {
				System.out.println("processbar displayed");

			
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		 catch (TimeoutException e) {
			e.printStackTrace();
		}

	}

	protected void clearDrpdownText(WebElement element) throws InterruptedException {

		element.sendKeys(Keys.chord(Keys.CONTROL, "A"));
		element.sendKeys(Keys.chord(Keys.BACK_SPACE));
		element.clear();

	}

	protected void clearText(WebElement element) throws InterruptedException {

		Actions actions = new Actions(DriverFactoryShaip.getDriver());

		actions.click(element).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).build()
				.perform();

		// element.sendKeys(Keys.chord(Keys.CONTROL,"A"));
		// Thread.sleep(5000);
		// element.sendKeys(Keys.chord(Keys.DELETE));
		// element.sendKeys(Keys.chord(Keys.CONTROL,"A"));

		// element.sendKeys(Keys.chord(Keys.BACK_SPACE));
		// element.clear();

	}

	protected void AssertCheck(WebElement element, String text) throws InterruptedException {

		String getString = getText_custom(element, element.toString());

		if (!getString.contains(text)) {
			sa.assertEquals(getString, text, "Not matching Expected and Actual");
			ExtentFactoryShaip.getTest().log(Status.FAIL, "String Assertion fail on field " + element
					+ " Expected value was: " + text + " actual value is: " + getString);

		}

		sa.assertAll();
		Thread.sleep(1000);

	}

	protected void setupExtentReport(String testName, String desc, String author, String category, String type,
			String portal) {

		ExtentFactoryShaip.startChildTest(testName, desc, author, category, type, portal);

	}
	
	protected static void giveTestRoleInfo(HashMap<String, String> testData, String role) {

		ExtentFactoryShaip.getTest().info(MarkupHelper.createLabel("USER ==> " + testData.get(role), ExtentColor.PINK));

		ExtentFactoryShaip.getTest()
				.info(MarkupHelper.createLabel("Test Data used for this execution is as below", ExtentColor.INDIGO));
		ExtentFactoryShaip.getTest().info(MarkupHelper.createOrderedList(testData).getMarkup());

	}

	public void verifyToaster(String Message,String ToasterName) throws InterruptedException {

		try {
		
			waitForVisibility(toaster_root);
			String toasterMessages = getText_custom(toaster_Message,ToasterName+" "+ "Toaster Message");
			System.out.println("###############################>>>>>>>>"+toasterMessages);
			assert_custom(toasterMessages.trim(), Message.trim(),ToasterName);
			execute_click(btn_closeToaster, "Close Toaster");
			Thread.sleep(1000);
			sa.assertAll();

		


		}catch (WebDriverException e) {

			e.printStackTrace();
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL,
					MarkupHelper.createLabel("Toaster Message has not been displayed", ExtentColor.RED));
			sa.assertAll();
		}

	}
	
	public void verifyContainsToaster(String Message,String ToasterName) throws InterruptedException {

		try {
		
			waitForVisibility(toaster_root);waitForVisibility(toaster_root);
			String toasterMessages = getText_custom(toaster_Message,ToasterName+" "+ "Toaster Message");
			assert_contains(toasterMessages.trim(), Message.trim(),ToasterName);
			execute_click(btn_closeToaster, "Close Toaster");
			Thread.sleep(1000);
			sa.assertAll();

		


		}catch (WebDriverException e) {

			e.printStackTrace();
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL,
					MarkupHelper.createLabel("Toaster Message has not been displayed", ExtentColor.RED));
			sa.assertAll();
		}

	}
	
	

		 
	    
}

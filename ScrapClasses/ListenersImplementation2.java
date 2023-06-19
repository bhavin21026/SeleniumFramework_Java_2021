/*package shaip_utilitiescomponents;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import shaip_base.DriverFactoryShaip;
import shaip_base.ExtentFactoryShaip;
import shaip_base.ExtentReportngShaip;

public class ListenersImplementation2 implements ITestListener {
	// JiraOperations jiraOps = new JiraOperations();
	static ExtentReports report;
	static ExtentTest test;

	public void onTestStart(ITestResult result) {

		// before each test case

		System.out.println("ON test start -creating extent test");
		test = report.createTest(result.getMethod().getDescription(), result.getMethod().getMethodName());

		ExtentFactoryShaip.getInstance().setExtent(test);
	}

	public void onTestSuccess(ITestResult result) {

		System.out.println("Passing extent test");
		ExtentFactoryShaip.getInstance().getExtent().log(Status.PASS, MarkupHelper
				.createLabel("Test Case: " + result.getMethod().getDescription() + " is Passed.", ExtentColor.GREEN));
		ExtentFactoryShaip.getInstance().removeExtentObject();
	}

	public void onTestFailure(ITestResult result) {
		ExtentFactoryShaip.getInstance().getExtent().log(Status.FAIL, MarkupHelper
				.createLabel("Test Case: " + result.getMethod().getMethodName() + " is Failed.", ExtentColor.RED));
		ExtentFactoryShaip.getInstance().getExtent().log(Status.FAIL, result.getThrowable());

		ExtentFactoryShaip.getInstance().getExtent().addScreenCaptureFromPath(captureScreenshot(result),
				"Test case failure screenshot");
		ExtentFactoryShaip.getInstance().removeExtentObject();

	}

	private String captureScreenshot(ITestResult result) {
		// TODO Auto-generated method stub

		File src = ((TakesScreenshot) DriverFactoryShaip.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);
		String testcaseName = result.getMethod().getMethodName();
		String screenshotPath = System.getProperty("user.dir") + "\\Shaip_Resources\\Screenshots\\" + testcaseName
				+ ".jpeg";
		File dest = new File(screenshotPath);

		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenshotPath;
	}

	public void onTestSkipped(ITestResult result) {
		ExtentFactoryShaip.getInstance().getExtent().log(Status.SKIP,
				"Test Case: " + result.getMethod().getMethodName() + " is skipped.");
		ExtentFactoryShaip.getInstance().removeExtentObject();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onTestFailedWithTimeout(ITestResult result) {
	}

	public void onStart(ITestContext context) {
		try {

			System.out.println("Extent setup Method invokded");
			
				report = ExtentReportngShaip.setupExtentReport();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onFinish(ITestContext context) {
		// close extent

		System.out.println("Extent setup FINISHED-FLUSHED");
		 report.flush();
	}

}*/

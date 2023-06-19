package com.shaip.reportng;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.shaip.base.FrameworkContstants;
import com.shaip.utils.PropertiesOperationsShaip;

public final class ExtentReportngShaip {

	private ExtentReportngShaip() {
	}

	private static ExtentReports extent;

	public synchronized static ExtentReports createExtentReports() {

		if (Objects.isNull(extent)) {
			
			extent = new ExtentReports();

			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
			Date date = new Date();
			String actualDate = format.format(date);

			String ReportName = "ShaipCloudQAAutomationReport";

			String reportPath = FrameworkContstants.getExtentReportPath() + ReportName + ".html";

			ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath).viewConfigurer().viewOrder()
					.as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.AUTHOR, ViewName.CATEGORY,
							ViewName.DEVICE })
					.apply();

			sparkReport.config().setDocumentTitle("ShaipCloud2.0 Automation Report");
			sparkReport.config().setTheme(Theme.STANDARD);
			sparkReport.config().setReportName("ShaipCloud 2.0 QA Automation Report");

			extent.attachReporter(sparkReport);

			extent.setSystemInfo("Executed on Environment: ", PropertiesOperationsShaip.getPropertyValueByKey("urlCP"));
			extent.setSystemInfo("Executed on Browser: ", PropertiesOperationsShaip.getPropertyValueByKey("browser"));
			extent.setSystemInfo("Executed on OS: ", System.getProperty("os.name"));
			extent.setSystemInfo("Executed by User: ", System.getProperty("user.name"));
			extent.setSystemInfo("Date Of Execution: ", actualDate);

		}
		return extent;

	}

	public static void flushReports() {
		if (Objects.nonNull(extent)) {
			extent.flush();
		}
		ExtentFactoryShaip.endTest();
	}

}

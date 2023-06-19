package shaip_base;

import com.aventstack.extentreports.ExtentTest;


public class ExtentFactoryShaip {
	//Singleton design Pattern
	//private constructor so that no one else can create object of this class
	private ExtentFactoryShaip() {
		
	}
	
	private static ExtentFactoryShaip shaipExtent  = new ExtentFactoryShaip();
	
	public static ExtentFactoryShaip getInstance() {
		return shaipExtent;
	}
	
	
	//factory design pattern --> define separate factory methods for creating objects and create objects by calling that methods
	ThreadLocal<ExtentTest> extent = new ThreadLocal<ExtentTest>();
	
	public ExtentTest getExtent() {
		
		return extent.get();
	}
	
	public void setExtent(ExtentTest extentTestObject) {
		extent.set(extentTestObject);
	}
	
	public void removeExtentObject() {
		extent.remove();
	}
	
	publiv void demo()
	
	{
		
		/*System.out.println("****I AM IN Fail Listernes*****");

		ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper
				.createLabel("Test Case: " + result.getMethod().getMethodName() + " is Failed.", ExtentColor.RED));
		File src = ((TakesScreenshot) DriverFactoryShaip.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);
		String testcaseName = result.getMethod().getMethodName();
		String screenshotPath = "/var/lib/jenkins/workspace/others/test-automation/Shaip_Resources/Screenshots/"+testcaseName+".png";

		//String screenshotPath = System.getProperty("user.dir") + "/Shaip_Resources/Screenshots/"+ testcaseName+".jpeg";
		System.out.println("****Screenshot captured path***** : "+screenshotPath);

		File dest = new File(screenshotPath);

		// Take a ScreenShot
				String scrBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				// convert the BASE64 to File type
				File file = OutputType.FILE.convertFromBase64Png(scrBase64);
				// store the converted file as Image on D driver
				FileUtils.copyFile(file, new File("D:BASE64-Conerted-Image.png"), true);

		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ExtentFactoryShaip.getTest().addScreenCaptureFromPath(screenshotPath,
				"Test case failure screenshot");	
		System.out.println("Screenshot captured");
		ExtentFactoryShaip.getTest().log(Status.FAIL, result.getThrowable());*/
		
		
	}
}






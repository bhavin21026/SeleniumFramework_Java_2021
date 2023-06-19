package com.shaip.page.cp;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.shaip.base.ActionEngineShaip;
import com.shaip.factories.DriverBase;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.page.ep.LoginPage_EP;
import com.shaip.reportng.ExtentFactoryShaip;
import com.shaip.utils.PropertiesOperationsShaip;


public class LoginPage_CP extends ActionEngineShaip {

	public static WebDriver webdrivers;

	public LoginPage_CP() {

		wait = new WebDriverWait(DriverFactoryShaip.getDriver(), Duration.ofSeconds(10));
		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	@FindBy(id = "emailAddress")
	WebElement txt_userName;

	@FindBy(id = "password")
	WebElement txt_password;

	@FindBy(id = "signIn")
	WebElement btn_signIn;

	@FindBy(xpath = "//img[@class='loader-icon']")
	WebElement processBar;
	
	@FindBy(id = "forgotPassword")
	WebElement lnk_forgetPassword;
	
	@FindBy(xpath = "//div[@class='content']//p")
	WebElement toaster_Message;

	@FindBy(xpath = "//mat-icon[text()='clear']")
	WebElement btn_closeToaster;

	@FindBy(xpath = "(//mat-error)[1]")
	WebElement error_userName;

	@FindBy(xpath = "(//mat-error)[2]")
	WebElement error_password;
	
	@FindBy(linkText = "Sign Up")
	WebElement lnk_signUp;
	
	
	

	 public  LoginPage_CP navigateToCP(String uriKey) {
		 
		  
		    String url = PropertiesOperationsShaip.getPropertyValueByKey(uriKey);
		  
		 	DriverFactoryShaip.getDriver().navigate().to(url);
	        return this;
	    }

	 public LoginPage_CP enterEmail(String email) {

			waitUntilClickable(txt_userName);
			click_custom(txt_userName, "Username Field");
			sendKeys_custom(txt_userName, "Username Field", email);
			return this;


		}

		public LoginPage_CP enterPassword(String password) {

			waitUntilClickable(txt_password);
			click_custom(txt_password, "Password Field");
			sendKeys_custom(txt_password, "Password Field", password);
			return this;

		}

		public LoginPage_CP doSignIn() throws InterruptedException {

			//waitUntilClickable(btn_signIn);
			Thread.sleep(2000);
			click_custom(btn_signIn, "SignIn button");
			//System.out.println("Welcome to home page");
			return this;


		}

	

	
	
	public SignUpPage_CP clickOnSignUp() throws InterruptedException

	{

		click_custom(lnk_signUp, "lnk_signUp Link");
		Thread.sleep(2000);
		// LoggerShaip.info("Sign up link clicked");
		return new SignUpPage_CP();

	}



	public LandingPage_CP loginToCP(String email, String password) throws InterruptedException {

		enterEmail(email);
		enterPassword(password);
		doSignIn();
		waitForProcessBarToGo();
		//Thread.sleep(20000);
		return new LandingPage_CP();

	}
	
	public LandingPage_CP loginToCPPortal(String email, String password) throws InterruptedException {

		enterEmail(email);
		enterPassword(password);
		doSignIn();
		return new LandingPage_CP();

	}

	public ForgotPassword navToForgotPassword() throws InterruptedException {

		click_custom(lnk_forgetPassword, "ForgetPassowrd Link");
		return new ForgotPassword();

	}
	
	
	public void verifyValidations(String enums) throws InterruptedException

	{
		Thread.sleep(1000);

		if (enums.equalsIgnoreCase("email")) {

			String validationMessage = getText_custom(error_userName, "Email validation message");
			try {
				assert_custom(validationMessage, "Please enter email address",
						"Wrong validation message getting displayed");
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		} else if (enums.equalsIgnoreCase("password"))

		{
			String validationMessage = getText_custom(error_userName, "Password validation message");
			try {
				assert_custom(validationMessage, "Please enter password",
						"Wrong validation message getting displayed");
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (enums.equalsIgnoreCase("both")) {
			String validationMessageUN = getText_custom(error_userName, "Email validation message");
			String validationMessagePW = getText_custom(error_password, "Password validation message");
			try {
				assert_custom(validationMessagePW, "Please enter password",
						"Wrong validation message getting displayed");
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				assert_custom(validationMessageUN, "Please enter email address",
						"Wrong validation message getting displayed");
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sa.assertAll();
	
	}
	
	public void isLogoutSuccessfull(String URI) throws InterruptedException {

		String currentURL=DriverFactoryShaip.getDriver().getCurrentUrl();
		
		if(URI.equalsIgnoreCase("CP"))
			assert_custom(currentURL, "https://qaworkforce.shaip.com/#/sign-in", "Logout");
		else
			assert_custom(currentURL, "https://qaenterprise.shaip.com/#/sign-in", "Logout");

		
		sa.assertAll();
	
	}
	
	
	public LoginPage_CP verifyErrorToaster() throws InterruptedException {

		Thread.sleep(1000);
		if(wait.until(ExpectedConditions.visibilityOf(toaster_Message))!=null)
		{
			sa.assertTrue(toaster_Message.isDisplayed(), "Toaster message is not getting displayed");
			String toasterMessages = getText_custom(toaster_Message, "Toaster Message");
			assert_custom(toasterMessages, "Incorrect email address or password",
					"Wrong toaster message getting displayed");
			Thread.sleep(1000);
			click_custom(btn_closeToaster, "Close Toaster");
			Thread.sleep(1000);
			sa.assertAll();

			
		}else
		{
			ExtentFactoryShaip.getTest().addScreenCaptureFromPath(captureScreenshot(),
					"Test case failure screenshot");	
			ExtentFactoryShaip.getTest().log(Status.FAIL, "Toaster Message has not been displayed");
		}
			sa.assertAll();

		return this;
		

	}
	
	
public LoginPage_CP verifyDisabledUserErrorToaster() throws InterruptedException {

		
		
		verifyToaster("User is disabled","Login Error");
		return this;
		
		
		

	}

	
}


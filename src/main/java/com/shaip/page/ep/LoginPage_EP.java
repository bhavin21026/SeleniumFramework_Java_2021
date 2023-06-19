package com.shaip.page.ep;

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
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.page.cp.ForgotPassword;
import com.shaip.reportng.ExtentFactoryShaip;
import com.shaip.utils.PropertiesOperationsShaip;

public final class LoginPage_EP extends ActionEngineShaip {

	public static WebDriver webdrivers;

	public LoginPage_EP() {

		
		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	@FindBy(id = "emailAddress")
	private WebElement txt_userName;

	@FindBy(id = "password")
	private WebElement txt_password;

	@FindBy(id = "signInButton")
	private WebElement btn_signIn;

	@FindBy(xpath = "//img[@class='loader-icon']")
	private WebElement processBar;
	
	@FindBy(id = "forgotPassword")
	private WebElement lnk_forgetPassword;
	
	@FindBy(xpath = "//div[@class='content']//p")
	private WebElement toaster_Message;

	@FindBy(id = "toastDismissButton")
	private WebElement btn_closeToaster;

	@FindBy(xpath = "//mat-error[contains(text(),'Please enter email address')]")
	private WebElement error_userName;

	@FindBy(xpath = "//mat-error[contains(text(),'Please enter password')]")
	private WebElement error_password;
	
	

	 public  LoginPage_EP navigateToEP(String uriKey) {
		 
		  
		    String url = PropertiesOperationsShaip.getPropertyValueByKey(uriKey);
		  
		 	DriverFactoryShaip.getDriver().navigate().to(url);
	        return this;
	    }

	
	

	public LoginPage_EP enterEmail(String email) {

		waitUntilClickable(txt_userName);
		click_custom(txt_userName, "Username Field");
		sendKeys_custom(txt_userName, "Username Field", email);
		return this;


	}

	public LoginPage_EP enterPassword(String password) {

		waitUntilClickable(txt_password);
		click_custom(txt_password, "Password Field");
		sendKeys_custom(txt_password, "Password Field", password);
		return this;

	}

	public LoginPage_EP doSignIn() throws InterruptedException {

		waitUntilClickable(btn_signIn);
		Thread.sleep(1000);
		click_custom(btn_signIn, "SignIn button");
		//System.out.println("Welcome to home page");
		return this;


	}

	public LandingPage_EP loginToEP(String email, String password) throws InterruptedException {

		enterEmail(email);
		enterPassword(password);
		doSignIn();
		waitForProcessBarToGo();
		return new LandingPage_EP();

	}
	
	public LoginPage_EP loginToEPPortal(String email, String password) throws InterruptedException {

		enterEmail(email);
		enterPassword(password);
		doSignIn();
		return this;

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
			String validationMessage = getText_custom(error_password, "Password validation message");
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
	
	
	public void verifyErrorToaster() throws InterruptedException {

		verifyToaster("Incorrect email address or password","Toaster message getting displayed");

	}

	
}

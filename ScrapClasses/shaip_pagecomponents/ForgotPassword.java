package shaip_pagecomponents;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.factories.ExtentFactoryShaip;
import com.shaip.factories.TestBaseShaip;

public class ForgotPassword extends TestBaseShaip {
	
	
	public ForgotPassword() {

		wait = new WebDriverWait(DriverFactoryShaip.getInstance().getDriver(), 30);
		PageFactory.initElements(DriverFactoryShaip.getInstance().getDriver(), this);

	}

	@FindBy(id = "forgotPasswordLink")
	WebElement lnk_forgotPassword;
	
	@FindBy(xpath = "//header//child::h2")
	WebElement div_verifyForgotPasswordTitle;
	
	@FindBy(xpath = "//shaip-page-header[@title='Verify Email Address']//following::div[1]//span")
	WebElement div_verifySentEmail;
	
	@FindBy(xpath = "//input[@formcontrolname='emailAddress']")
	WebElement txt_emailAddress;
	
	@FindBy(xpath = "//span[contains(text(),'Submit')]//parent::button")
	WebElement btn_submit;
	
	//reset password fields
	
	@FindBy(name = "resetNewPassword")
	WebElement txt_newPassword;
	
	@FindBy(name = "resetConfirmPassword")
	WebElement txt_confirmNewPassword;
	
	@FindBy(xpath = "//span[contains(text(),'Submit')]//parent::button")
	WebElement btn_resetPassword;
	
	@FindBy(xpath = "//div[@class='content']//p")
	WebElement toaster_Message;

	@FindBy(xpath = "//mat-icon[text()='clear']")
	WebElement btn_closeToaster;

	
	
	public ForgotPassword resetnewPassword(String newPassword) {

		waitUntilClickable(txt_newPassword);
		click_custom(txt_newPassword, "txt_newPassword Field");
		sendKeys_custom(txt_newPassword, " txt_newPassword Field", newPassword);
		return this;


	}
	
	public ForgotPassword resetConfirmPassword(String newConfirmPassword) 

	{
		waitUntilClickable(txt_confirmNewPassword);
		click_custom(txt_confirmNewPassword, "newConfirmPassword Field");
		sendKeys_custom(txt_confirmNewPassword, " newConfirmPassword Field", newConfirmPassword);
		return this;
	}
	
	
	public ForgotPassword clickOnResetPassword() throws InterruptedException

	{
		waitUntilClickable(btn_resetPassword);
		click_custom(btn_resetPassword, "Reset password button");
		return this;

	}
	
	
	public ForgotPassword enterEmail(String email) {

		waitUntilClickable(txt_emailAddress);
		click_custom(txt_emailAddress, "forgot email Field");
		sendKeys_custom(txt_emailAddress, " forgot email Field", email);
		return this;


	}
	
	public ForgotPassword clickOnSubmit() throws InterruptedException

	{

		click_custom(btn_submit, "Submit button");
		Thread.sleep(2000);
		waitForProcessBarToGo();
		// LoggerShaip.info("Sign up link clicked");
		return this;
	}
	
	
	public ForgotPassword clickOnForgotPassword() throws InterruptedException

	{
		waitUntilClickable(lnk_forgotPassword);
		click_custom(lnk_forgotPassword, "lnk_forgotPassword");
		return this;

	}
	
	public ForgotPassword isForgotPasswordMailSent(String email) throws InterruptedException {

		Thread.sleep(2000);
		String header = getText_custom(div_verifyForgotPasswordTitle, "Forgot password verification");
		assert_custom(header, "Verify Email Address", "Forgot password page is open  successfully....");
		String header2 = getText_custom(div_verifySentEmail, "Forgot password mail verification");
		assert_custom(header2, email, "Forgot password email sent to wrong user ....");
		sa.assertAll();
		Thread.sleep(2000);
		return this;

	}

	
	public ForgotPassword isForgotPasswordPageOpened() throws InterruptedException {

		Thread.sleep(2000);
		String header = getText_custom(div_verifyForgotPasswordTitle, "Forgot password page verification");
		assert_custom(header, "Forgot Password", "Forgot password page open successfully....");	
		sa.assertAll();
		Thread.sleep(1000);
		return this;

	}
	
	public ForgotPassword isResetPasswordPageOpened() throws InterruptedException {

		Thread.sleep(2000);
		String header = getText_custom(div_verifyForgotPasswordTitle, "Reset Password page verification");
		assert_custom(header, "Reset Password", "Reset Password page open successfully....");	
		sa.assertAll();
		Thread.sleep(1000);
		return this;

	}
	
public  ForgotPassword waitForPasswordResetSuccessMessageToaster() throws InterruptedException {

		
		
		if(wait.until(ExpectedConditions.visibilityOf(toaster_Message))!=null)
		{
			sa.assertTrue(toaster_Message.isDisplayed(), "Toaster message is not getting displayed");
			String toasterMessages = getText_custom(toaster_Message, "Toaster Message");
			assert_custom(toasterMessages, "The password has been reset successfully","Toaster message getting displayed");
			Thread.sleep(1000);
			click_custom(btn_closeToaster, "Close Toaster");
			Thread.sleep(1000);
			sa.assertAll();

			
		}else
		{
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),"Test case failure screenshot");		
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper
					.createLabel("Toaster Message has not been displayed", ExtentColor.RED));
			sa.assertAll();
		}
		
		return this;
		

	}
	
	

}

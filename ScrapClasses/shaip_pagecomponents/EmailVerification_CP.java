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

public class EmailVerification_CP extends TestBaseShaip {
	
	
	
	public EmailVerification_CP() {

		wait = new WebDriverWait(DriverFactoryShaip.getInstance().getDriver(), 30);
		PageFactory.initElements(DriverFactoryShaip.getInstance().getDriver(), this);

	}
	
	
	
	
	@FindBy(xpath = "//div[@class='content']//p")
	WebElement toaster_Message;

	@FindBy(xpath = "//mat-icon[text()='clear']")
	WebElement btn_closeToaster;
	
	@FindBy(xpath = "//section//div[1]")
	WebElement verifyExpiryMessage;
	
	
	
	public void doVerifyLinkExpiry() throws InterruptedException {
		
		Thread.sleep(2000);
		String header=getText_custom(verifyExpiryMessage, "Link expiry checking");
		sa.assertEquals(header, "Your email verification link expired", "Link has not been expired");
		sa.assertAll();
		
	}
	
	public EmailVerification_CP waitForEmailVerifiedMessageToaster() throws InterruptedException {

		
		
		if(wait.until(ExpectedConditions.visibilityOf(toaster_Message))!=null)
		{
			sa.assertTrue(toaster_Message.isDisplayed(), "Toaster message is not getting displayed");
			String toasterMessages = getText_custom(toaster_Message, "Toaster Message");
			assert_custom(toasterMessages, "Your email address has been verified",
					"Toaster message getting displayed");
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

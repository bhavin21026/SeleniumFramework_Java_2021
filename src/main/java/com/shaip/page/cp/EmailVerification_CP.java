package com.shaip.page.cp;

import java.time.Duration;

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
import com.shaip.reportng.ExtentFactoryShaip;

public class EmailVerification_CP extends ActionEngineShaip {
	
	
	
	public EmailVerification_CP() {

		wait = new WebDriverWait(DriverFactoryShaip.getDriver(), Duration.ofSeconds(10));
		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

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

		
		
		
		verifyToaster("Your email address has been verified","Email Verification Toaster");
		return this;
		
		

	}

}

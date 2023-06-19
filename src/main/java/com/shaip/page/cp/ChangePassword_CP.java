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

public class ChangePassword_CP extends ActionEngineShaip {

	public ChangePassword_CP() {

		wait = new WebDriverWait(DriverFactoryShaip.getDriver(), Duration.ofSeconds(10));
		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	// Profile elements

	@FindBy(name = "oldPassword")
	private WebElement txt_oldPassword;

	@FindBy(name = "newPassword")
	private WebElement txt_newPassword;

	@FindBy(name = "confirmPassword")
	private WebElement txt_confirmPassword;

	@FindBy(name = "accChangePwdSave")
	private WebElement btn_saveNewPassword;

	@FindBy(xpath = "//div[@class='content']//p")
	private WebElement toaster_Message;

	@FindBy(xpath = "//mat-icon[text()='clear']")
	private WebElement btn_closeToaster;

	@FindBy(id = "changePasswordTab")
	private WebElement tab_changePasswordTab;

	// ChangePassword Validations

	@FindBy(xpath = "//mat-error")
	private WebElement validation_changePassword;
	
	@FindBy(xpath = "(//mat-error)[1]")
	private WebElement validation_oldPass;
	@FindBy(xpath = "(//mat-error)[2]")
	private WebElement validation_newPass;
	@FindBy(xpath = "(//mat-error)[3]")
	private WebElement validation_conPass;



	public ChangePassword_CP clickOnChangePasswordTab() throws InterruptedException

	{

		waitForProcessBarToGo();
		click_custom(tab_changePasswordTab, "change Password Tab");
		Thread.sleep(1000);
		return this;

	}

	public ChangePassword_CP enterOldPassword(String error_oldPass) throws InterruptedException

	{
		Thread.sleep(1000);
		click_custom(txt_oldPassword, "txt_oldPassword");
		sendKeys_custom(txt_oldPassword, "txt_oldPassword", error_oldPass);
		return this;

	}

	public ChangePassword_CP enterNewPassword(String error_newPass) throws InterruptedException

	{
		click_custom(txt_newPassword, "txt_newPassword");
		sendKeys_custom(txt_newPassword, "txt_newPassword", error_newPass);
		return this;

	}

	public ChangePassword_CP enterConfirmPassword(String error_cpass) throws InterruptedException

	{
		click_custom(txt_confirmPassword, "txt_confirmPassword");
		sendKeys_custom(txt_confirmPassword, "txt_confirmPassword", error_cpass);

		return this;

	}

	public ChangePassword_CP clickOnSave() throws InterruptedException

	{

		click_custom(btn_saveNewPassword, "Save update password ");
		Thread.sleep(1000);
		return this;

	}

	public ChangePassword_CP verifyPasswordUpdateSuccessToaster() throws InterruptedException {

		
		verifyToaster("The password has been updated successfully","Change Password Toaster");
		return this;

	}

	public ChangePassword_CP verifyPasswordIncorrectErrorToaster() throws InterruptedException {



		verifyToaster("The Old Password is incorrect","Change Password Toaster");
		return this;

	}

	public ChangePassword_CP verifyChangePasswordValidations(String enums) throws InterruptedException

	{
		Thread.sleep(1000);

		if (enums.equalsIgnoreCase("txt_oldPassword")) {

			String validationMessage = getText_custom(validation_changePassword, "Old Passowrd validation message");
			assert_custom(validationMessage, "Please enter old password", "Change Password");

		} else if (enums.equalsIgnoreCase("txt_newPassword"))

		{
			String validationMessageLN = getText_custom(validation_changePassword, "New Passowrd validation message");
			assert_custom(validationMessageLN, "Please enter new password",
					"Change Password");
		} else if (enums.equalsIgnoreCase("cpassword"))

		{
			String validationMessageEmail = getText_custom(validation_changePassword, "Confirm Passowrd validation message");
			assert_custom(validationMessageEmail, "Please enter confirm password",
					"Change Password");
		} else if (enums.equalsIgnoreCase("both"))

		{
			String validationMessagePW = getText_custom(validation_changePassword, "confirm Password validation message");
			assert_custom(validationMessagePW, "Passwords don't match", "Change Password");
		} else if (enums.equalsIgnoreCase("server"))

		{
			verifyPasswordIncorrectErrorToaster();
		}

		else if (enums.equalsIgnoreCase("weak"))

		{
			String validationMessagePW = getText_custom(validation_changePassword, "Weak Password validation message");
			assert_custom(validationMessagePW, "Password is weak", "Change Password");
		} else if (enums.equalsIgnoreCase("same"))

		{
			String validationMessagePW = getText_custom(validation_changePassword, "New Password same as Old validation");
			assert_custom(validationMessagePW, "Old and new password cannot be the same",
					"Change Password");

		}

		else if (enums.equalsIgnoreCase("all")) {
			String validationMessageOP = getText_custom(validation_oldPass, "OP validation message");
			String validationMessageNP = getText_custom(validation_newPass, "NP validation message");
			String validationMessageCP = getText_custom(validation_conPass, "CP validation message");
			assert_custom(validationMessageOP, "Please enter old password",
					"Change Password");
			assert_custom(validationMessageNP, "Please enter new password",
					"Change Password");
			assert_custom(validationMessageCP, "Please enter confirm password",
					"Change Password");
		}
		sa.assertAll();
		return this;
	}

}

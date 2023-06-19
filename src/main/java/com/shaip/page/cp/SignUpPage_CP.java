package com.shaip.page.cp;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.shaip.base.ActionEngineShaip;
import com.shaip.enums.story;
import com.shaip.factories.DriverBase;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.reportng.ExtentFactoryShaip;

public class SignUpPage_CP extends ActionEngineShaip {

	public SignUpPage_CP() {

		wait = new WebDriverWait(DriverFactoryShaip.getDriver(), Duration.ofSeconds(10));
		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	@FindBy(name = "firstName")
	WebElement txt_firstName;

	@FindBy(name = "lastName")
	WebElement txt_lastName;

	@FindBy(name = "signUpEmailAddress")
	WebElement txt_emailAddress;

	@FindBy(name = "signUpPassword")
	WebElement txt_password;

	@FindBy(name = "confirmPassword")
	WebElement txt_confirmPassword;

	@FindBy(name = "country")
	WebElement drp_customerCountry;

	@FindBy(xpath = "//mat-checkbox[@formcontrolname='isAgree' or @id='mat-checkbox-1']")
	WebElement chk_isAgree;

	@FindBy(name = "signUpSubmit")
	WebElement btn_signUp;

	@FindBy(xpath = "//header//child::h2")
	WebElement hdr_verifySignUp;

	@FindBy(xpath = "//app-sign-up[@class='ng-star-inserted']//div[1]//span")
	WebElement verifySentEmail;
	
	@FindBy(xpath = "//div[@class='left-panel']")
	WebElement div_leftPanel;

	// Registration Validations

	@FindBy(xpath = "(//mat-error)[1]")
	WebElement error_validationMessage;

	@FindBy(xpath = "(//mat-error)[1]")
	WebElement error_firstName;

	@FindBy(xpath = "(//mat-error)[1]")
	WebElement error_validFirstName;

	@FindBy(xpath = "(//mat-error)[2]")
	WebElement error_lastName;

	@FindBy(xpath = "(//mat-error)[3]")
	WebElement error_email;

	@FindBy(xpath = "(//mat-error)[4]")
	WebElement error_password;

	@FindBy(xpath = "(//mat-error)[5]")
	WebElement error_confirmPassword;

	@FindBy(xpath = "(//mat-error)[6]")
	WebElement error_selectedCountry;

	public SignUpPage_CP enterFirstName(String Name)

	{

		click_custom(txt_firstName, "First Name");
		sendKeys_custom(txt_firstName, "First Name", Name);
		// LoggerShaip.info("First name written");
		return this;

	}

	public SignUpPage_CP enterLastName(String lastName)

	{

		click_custom(txt_lastName, "Last Name");
		sendKeys_custom(txt_lastName, "Last Name", lastName);
		return this;

	}

	public SignUpPage_CP enterEmaiID(String email)

	{

		click_custom(txt_emailAddress, "Email");
		sendKeys_custom(txt_emailAddress, "Email", email);
		return this;

	}

	public SignUpPage_CP enterPassword(String password)

	{
		if (!password.equals("")) {
			click_custom(txt_password, "Email");
			sendKeys_custom(txt_password, "Email", password);

		}
		return this;
	}

	public SignUpPage_CP enterConfirmPassword(String cofPass)

	{
		if (!cofPass.equals("")) {
		click_custom(txt_confirmPassword, "Email");
		sendKeys_custom(txt_confirmPassword, "Email", cofPass);
		}
		return this;

	}

	public SignUpPage_CP clickOnSubmitUserBtn() throws InterruptedException

	{

		Thread.sleep(1000);
		click_custom(btn_signUp, "Save User");
		// LoggerShaip.info("submit button clicked");
		return this;

	}

	public SignUpPage_CP clickOnIAgree() throws InterruptedException

	{
		Thread.sleep(1000);
		waitUntilClickable(chk_isAgree);
		click_custom(chk_isAgree, "I Agree");
		System.out.println("Agree button clicked");
		// LoggerShaip.info("Agree button clicked");
		return this;

	}

	public SignUpPage_CP doRegistration(ITestContext context) throws InterruptedException

	{

		String email = generateNewEmail();
		String txt_password = "Test@12345";
		Thread.sleep(2000);
		enterFirstName("QA");
		enterLastName("AutomationUser");
		enterEmaiID(email);
		enterPassword(txt_password);
		enterConfirmPassword(txt_password);
		selectCountry("India");
		clickOnIAgree();
		clickOnSubmitUserBtn();
		waitForProcessBarToGo();
		context.setAttribute("newSignupUser", email);
		context.setAttribute("signupPassword", txt_password);
		context.setAttribute("signupFN", "QA");
		context.setAttribute("signupLN", "AutomationUser");
		context.setAttribute("signupCountry", "India");
		return this;

	}

	public void verifySignUpComplete(String email) throws InterruptedException {

		Thread.sleep(2000);
		String header = getText_custom(hdr_verifySignUp, "Signup verification");
		assert_custom(header, "Verify Email Address", "Sign up is completed successfully....");
		String header2 = getText_custom(verifySentEmail, "Signup mail verification");
		assert_custom(header2, email, "Sign up email sent to wrong user ....");
		sa.assertAll();
		Thread.sleep(2000);

	}

	public SignUpPage_CP selectCountry(String countryName) throws InterruptedException

	{
		System.out.println("*********************Going to click on country dropdown**********************");
		Thread.sleep(1000);
		click_custom(drp_customerCountry, "Country Dropdown");
		System.out.println("Going to write on country Name");

		sendKeys_custom(drp_customerCountry, "Country Dropdown", countryName);
		Thread.sleep(1000);
		List<WebElement> countryToBeSelected = DriverFactoryShaip.getDriver()
				.findElements(By.xpath("//span[@class='mat-option-text']"));
		// System.out.println(countryToBeSelected.size());

		Thread.sleep(1000);

		for (int i = 0; i < countryToBeSelected.size(); i++) {

			System.out.println("*********************Searching Countries**********************"
					+ countryToBeSelected.get(i).toString());

			String country = countryToBeSelected.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
					.replaceAll("\n", " ");

			if (country.equalsIgnoreCase(countryName))

			{
				System.out.println("*********************COUNTRY FOUND**********************"
						+ countryToBeSelected.get(i).toString());

				click_custom(countryToBeSelected.get(i), countryName);
				break;
			}
			
			

		}
		click_custom(div_leftPanel,"Closing country dropdown");
		
		return this;

	}

	public SignUpPage_CP verifySignUpFormValidations(String enums) throws InterruptedException

	{
		Thread.sleep(1000);

		if (enums.equalsIgnoreCase("First Name")) {

			isBlankFieldAllowed(txt_firstName, enums, "Please enter first name");
			verifyMaxAndMinLengthOfField(txt_firstName, enums, 75, 2);
			isSpecialCharactersAllowed(txt_firstName, enums, "Please enter a valid first name");
			checkFormMinimumLimits(txt_firstName, enums, "Please enter a valid first name");
			checkFormMaximuLimits(txt_firstName, enums, 75);

		} else if (enums.equalsIgnoreCase("Last Name"))

		{
			isBlankFieldAllowed(txt_lastName, enums, "Please enter last name");
			verifyMaxAndMinLengthOfField(txt_lastName, enums, 75, 2);
			isSpecialCharactersAllowed(txt_lastName, enums, "Please enter a valid last name");
			checkFormMinimumLimits(txt_lastName, enums, "Please enter a valid last name");
			checkFormMaximuLimits(txt_lastName, enums, 75);
		} else if (enums.equalsIgnoreCase("Email"))

		{
			isBlankFieldAllowed(txt_emailAddress, enums, "Please enter email address");
			isSpecialCharactersAllowed(txt_emailAddress, enums, "Please enter a valid email address");
			isWhiteSpaceAllowed(txt_emailAddress, enums, "Please enter email address");
			checkFormMinimumLimits(txt_emailAddress, enums, "Please enter a valid email address");

		} else if (enums.equalsIgnoreCase("Password"))

		{
			click_custom(txt_password, "Password field");
			click_custom(txt_emailAddress, "Email field");

			
			isBlankFieldAllowed(txt_password, enums, "Please enter password");
			isWhiteSpaceAllowed(txt_password, enums, "Please enter password");
			isPasswordWeak(txt_password, enums, "Password is weak");
			verifyMaxAndMinLengthOfField(txt_password, enums, 16, 7);
			checkFormMinimumLimits(txt_password, enums, "Please enter valid password");

		} else if (enums.equalsIgnoreCase("Confirm Password"))

		{
			click_custom(txt_confirmPassword, "Confirm Password field");
			sendKeys_custom(txt_password, "Password field", "Test@123");
			isBlankFieldAllowed(txt_confirmPassword, enums, "Please enter confirm password");
			isWhiteSpaceAllowed(txt_confirmPassword, enums, "Please enter confirm password");
			verifyMaxAndMinLengthOfField(txt_confirmPassword, enums, 16, 7);
			sendKeys_custom(txt_password, enums, "Test@1234");
			isConfirmPasswordSameAsPassword(txt_confirmPassword, enums, "Passwords don't match");

		}

		else if (enums.equalsIgnoreCase("Country"))

		{
			isBlankFieldAllowed(drp_customerCountry, enums,"Please select country");

		}

		else if (enums.equalsIgnoreCase("All")) {
			
			setupExtentReport(
					"CP: Verify system behaviour when user try to sign up without filling mandatory fileds (Sign up form)",
					"Sign Up- Negative Scenarios", "General", story.SignUp_42.toString().toString(), "SIT", "CP");
			String validationMessageFN = getText_custom(error_firstName, "First Name validation message");
			String validationMessageLN = getText_custom(error_lastName, "Last Name validation message");
			String validationMessageEmail = getText_custom(error_email, "Email validation message");
			String validationMessagePW = getText_custom(error_password, "password validation message");
			String validationMessageCPW = getText_custom(error_confirmPassword, "password validation message");
			String validationMessageCountry = getText_custom(error_selectedCountry, "Country validation message");

			assert_custom(validationMessageFN, "Please enter first name", "Wrong validation message getting displayed");
			assert_custom(validationMessageLN, "Please enter last name", "Wrong validation message getting displayed");
			assert_custom(validationMessageEmail, "Please enter email address",
					"Wrong validation message getting displayed");
			assert_custom(validationMessagePW, "Please enter password", "Wrong validation message getting displayed");
			assert_custom(validationMessageCPW, "Please enter confirm password",
					"Wrong validation message getting displayed");
			assert_custom(validationMessageCountry, "Please select country",
					"Wrong validation message getting displayed");
		}
		sa.assertAll();
		return this;
	}

	public SignUpPage_CP verifyMaxAndMinLengthOfField(WebElement element, String enums, int Max, int Min)
			throws InterruptedException

	{
		setupExtentReport(
				"CP: Verify " + enums
						+ "field (Signup form) should not allow more than 75 characters and less than 2 characters",
				"Sign Up- Negative Scenarios", "General", story.SignUp_42.toString(), "SIT", "CP");

		element.clear();
		int minLengthDefined = Integer.parseInt(element.getAttribute("minlength"));
		int maxLengthDefined = Integer.parseInt(element.getAttribute("maxlength"));

		if (minLengthDefined == 0 || maxLengthDefined == 0) {

			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Either minimum or maximum lengh has not been set for the webelement : " + element,
							ExtentColor.RED));
			sa.assertAll();

		}

		else {
			if (minLengthDefined == Min && maxLengthDefined == Max) {

				ExtentFactoryShaip.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Minium and Maximum both limits has been set as expected on webelement : " + element,
								ExtentColor.GREEN));
				sa.assertAll();
			} else {
				ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
						"Test case failure screenshot");
				ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper.createLabel(
						"Minium and Maximum both limits has not been set as expected on webelement : " + element,
						ExtentColor.RED));
				sa.assertAll();

			}
		}
		return this;

	}

	public void isSpecialCharactersAllowed(WebElement element, String enums, String message)
			throws InterruptedException {

		setupExtentReport(
				"CP: Verify " + enums
						+ " field (SignUp form) should not allow special characters to enter except space,(.)",
				"Sign Up- Negative Scenarios", "General", story.SignUp_42.toString(), "SIT", "CP");
		clear_custom(element, enums);
		sendKeys_custom(element, enums, "%$#@!*&^)+_");
		String validationMessage = getText_custom(error_validationMessage, enums + " validation message");
		assert_custom(validationMessage, message, enums + " (Sign Up)");

	}

	public void isBlankFieldAllowed(WebElement element, String enums, String message) throws InterruptedException {

		setupExtentReport("CP: Verify " + enums + " field (SignUp form) should not allow to left blank",
				"Sign Up- Negative Scenarios", "General", story.SignUp_42.toString(), "SIT", "CP");
		/*setupExtentReport("CP: Verify " + enums + " field (SignUp form) should not allow to left blank",
				"Sign Up- Negative Scenarios", "General", story.SignUp_42.toString(), "SIT", "CP");*/
		String validationMessage = getText_custom(error_validationMessage, enums + " validation message");
		assert_contains(validationMessage, message, enums + " (Sign Up)");

	}

	public void isWhiteSpaceAllowed(WebElement element, String enums, String message) throws InterruptedException {

		setupExtentReport("CP: Verify" + enums + " field (SignUp form) should not allow white space as input",
				"Sign Up- Negative Scenarios", "General", story.SignUp_42.toString(), "SIT", "CP");
		clear_custom(element, enums);
		sendKeys_custom(element, enums, " ");
		String validationMessage = getText_custom(error_validationMessage, enums + " validation message");
		assert_contains(validationMessage, message, enums + " (Sign Up)");

	}

	public void isPasswordWeak(WebElement element, String enums, String message) throws InterruptedException {

		setupExtentReport("CP: Verify entered password is weak validation when user enter weak passoword (SignUp form)",
				"Sign Up- Negative Scenarios", "General", story.SignUp_42.toString(), "SIT", "CP");
		clear_custom(element, enums);
		sendKeys_custom(element, enums, "Test1234");
		String validationMessage = getText_custom(error_validationMessage, enums + " validation message");
		assert_contains(validationMessage, message, enums + " (Sign Up)");

	}

	public void isConfirmPasswordSameAsPassword(WebElement element, String enums, String message)
			throws InterruptedException {

		setupExtentReport("CP: Verify validation when Password and Confirm password is not same (SignUp form)",
				"Sign Up- Negative Scenarios", "General", story.SignUp_42.toString(), "SIT", "CP");
		clear_custom(element, enums);
		sendKeys_custom(element, enums, "Test@1234567");
		String validationMessage = getText_custom(error_validationMessage, enums + " validation message");
		assert_contains(validationMessage, message, enums + " (Sign Up)");

	}

	public void checkFormMinimumLimits(WebElement element, String enums, String message) throws InterruptedException {

		setupExtentReport("CP: Verify minimum characters limits allowed on " + enums + " field (SignUp form)",
				"Sign Up- Negative Scenarios", "General", story.SignUp_42.toString(), "SIT", "CP");
		clear_custom(element, enums);
		sendKeys_custom(element, enums + " with one Characters only", "A");
		String validationMessage = getText_custom(error_validationMessage, enums + " validation message");
		assert_custom(validationMessage, message, enums + " (Sign Up)");

	}

	public void checkFormMaximuLimits(WebElement element, String enums, int Max) throws InterruptedException {

		setupExtentReport("CP: Verify maximum characters limits allowed on " + enums + " field (SignUp form)",
				"Sign Up- Negative Scenarios", "General", story.SignUp_42.toString(), "SIT", "CP");
		clear_custom(element, enums);
		String largeText = "LoremIpsumissi mplydummytextoftheprintingandtypesettingindustrytesttesttestttestt";
		sendKeys_custom(element, enums + " with one Characters only", largeText);
		String typedValue = element.getAttribute("value");
		int size = typedValue.length();

		if (size == Max) {

			ExtentFactoryShaip.getTest().log(Status.PASS, MarkupHelper.createLabel(
					" Maximum  limit has been set as expected on webelement : " + element, ExtentColor.GREEN));
			sa.assertAll();
		}

		else {

			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper.createLabel(
					"Maximum  limit has not been set as expected on webelement : " + element, ExtentColor.RED));
			sa.assertAll();
		}

	}

}

package com.shaip.testcase.cp;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shaip.annotations.FrameworkAnnotation;
import com.shaip.basetest.BaseTest;
import com.shaip.enums.CategoryType;
import com.shaip.enums.PortalType;
import com.shaip.enums.story;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.page.cp.ChangePassword_CP;
import com.shaip.page.cp.EmailVerification_CP;
import com.shaip.utils.EmailSearcher;


public  class VerifyContributorRegistrationFunctionality_CP extends BaseTest   {

	
	
	EmailVerification_CP emailVerify;
	ChangePassword_CP cp;
	String getVerificationLink = null;
	String newEmail = null;
	
	//.loginToCP("parth.parikh@shaip.com", "P@ssw0rd@123")

	

	@Test(priority = 1, dataProvider ="Registration",enabled=true,groups={"SIT","42"})
	@FrameworkAnnotation(category= {CategoryType.SIT},storyId= {story.SignUp_42},portal= {PortalType.CP})

	public void VerifySignUpPageValidations(Object obj1,Method method) throws Throwable {

	

		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		setupExtentReport("CP: Verify SignUp page functionality for negative scenarios like below", method.getName(),"General");
		//giveTestDataInfo(testData,"TestName","Guest User");

	loginPagecp
		.navigateToCP("urlCP")
		.clickOnSignUp()
		.enterFirstName(testData.get("FirstName"))
		.enterLastName(testData.get("LastName"))
		.enterEmaiID(testData.get("Email"))
		.enterPassword(testData.get("Password"))
		.enterConfirmPassword(testData.get("Cpassword"))
		.selectCountry(testData.get("Country"))
		.clickOnIAgree()
		.clickOnSubmitUserBtn()
		.verifySignUpFormValidations(testData.get("Enum"));

	}
	
	


	@Test(priority = 2,enabled=true,groups={"Smoke","42"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.SignUp_42},portal= {PortalType.CP})

	public void VerifyContributorRegistrationFunctionality(ITestContext context,Method method) throws Throwable {

		
		setupExtentReport("CP: Verify that as a Guest user, I should be able to sign up to the portal", method.getName(),"General");
		
		
		 loginPagecp
		 		.navigateToCP("urlCP")
		 		.clickOnSignUp()
		 		.doRegistration(context)	
		 		.verifySignUpComplete((String) context.getAttribute("newSignupUser"));

		
	}
	
	@Test(priority = 3,enabled=true,dependsOnMethods = "VerifyContributorRegistrationFunctionality",groups={"Smoke","42"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.SignUp_42},portal= {PortalType.CP})

	public void VerifySignUpEmailVerificationFunctionality(Method method) throws Throwable {

		setupExtentReport("CP: Verify that as a Guest user, I should be able to receive signup verification email on registered email", method.getName(),"General");
		
		
		emailVerify = new EmailVerification_CP();

		getVerificationLink = EmailSearcher.doEmailVerification();
		navigateTo(getVerificationLink);
		emailVerify.waitForEmailVerifiedMessageToaster();

	}
	
	
	
	
	

	@Test(priority = 4,enabled=true,dependsOnMethods = "VerifySignUpEmailVerificationFunctionality",groups={"Smoke","42"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.SignUp_42},portal= {PortalType.CP})

	public void Verifyexpiryoflink(Method method) throws Throwable {

		setupExtentReport("CP: Verify system should display the expired link page if user is still processing with the old sign up verification link", method.getName(),"General");

		
		emailVerify = new EmailVerification_CP();

		DriverFactoryShaip.getDriver().navigate().to(getVerificationLink);
		emailVerify.doVerifyLinkExpiry();

	}

	@Test(priority = 5,enabled=true,dependsOnMethods = "Verifyexpiryoflink",groups={"Smoke","42"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.SignUp_42},portal= {PortalType.CP})

	public void VerifyLoginWithNewRegisteredFreelancerUser(ITestContext context,Method method) throws Throwable {

		setupExtentReport("CP: Verify that guest/Freelancer user should be able to login into portal once sign up verification completed", method.getName(),"General");
		giveUserInfo("NEWLY SIGNED UP FREELANCER ===> "+(String) context.getAttribute("newSignupUser"));

		
		loginPagecp
			.navigateToCP("urlCP")
			.loginToCP((String) context.getAttribute("newSignupUser"), (String) context.getAttribute("signupPassword"))
			.verifyTermsAndConditionPopUp()
			.clickOnAgreeButton()
			.verifyShaipURI()
			.clickOnProfileMenu()
			.clickOnAccount()
			.verifyBasicInformationOfRegisteredUser((String) context.getAttribute("signupFN"), (String) context.getAttribute("signupLN"), (String) context.getAttribute("newSignupUser"), (String) context.getAttribute("signupCountry"));

	}

		
	
	@DataProvider(name = "Registration")
	public Object[][] getTestData() throws IOException {

		return getData("CP","registration_cp");
	}

	
	
	
	
	
	
	


	

	

	

}

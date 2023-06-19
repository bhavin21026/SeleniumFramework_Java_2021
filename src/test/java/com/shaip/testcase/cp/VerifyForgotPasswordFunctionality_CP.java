package com.shaip.testcase.cp;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.shaip.annotations.FrameworkAnnotation;
import com.shaip.basetest.BaseTest;
import com.shaip.enums.CategoryType;
import com.shaip.enums.PortalType;
import com.shaip.enums.story;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.page.cp.ChangePassword_CP;
import com.shaip.page.cp.EmailVerification_CP;
import com.shaip.page.cp.ForgotPassword;
import com.shaip.page.cp.LandingPage_CP;
import com.shaip.utils.DB_Operations;
import com.shaip.utils.EmailSearcher;
import com.shaip.utils.PropertiesOperationsShaip;

public class VerifyForgotPasswordFunctionality_CP extends BaseTest {
	
	
	EmailVerification_CP emailVerify;
	ChangePassword_CP cp;
	String getVerificationLink = null;
	String newEmail = null;
	DB_Operations db = new DB_Operations();

	
	
	@Test(priority = 1,enabled=true,groups={"39"})
	@FrameworkAnnotation(category= {CategoryType.NONE},storyId= {story.ForgotPassword_CP_39},portal= {PortalType.CP})

	public void VerifyForgotPasswordFunctionality(ITestContext context,Method method) throws Throwable {

		setupExtentReport("CP: Verify Forgot Password functionality", method.getName(),"General");
		giveUserInfo("VENDOR USER ===> "+PropertiesOperationsShaip.getPropertyValueByKey("cpusername2"));

		ForgotPassword forgot = new ForgotPassword();
		emailVerify = new EmailVerification_CP();


	loginPagecp
		 .navigateToCP("urlCP")
		 .loginToCP(PropertiesOperationsShaip.getPropertyValueByKey("cpusername2"), PropertiesOperationsShaip.getPropertyValueByKey("cppassword2"))
	     .verifyTermsAndConditionPopUp()
		 .clickOnAgreeButton()
		 .verifyShaipURI()
		 .clickOnNotifications()
		 .deleteAllNotification("Reset Password")
		 .clickOnProfileMenu()
		 .clickOnSignOut()
		 .isLogoutSuccessfull("CP");
	
  forgot
		 .clickOnForgotPassword()
		 .isForgotPasswordPageOpened()
		 .enterEmail(PropertiesOperationsShaip.getPropertyValueByKey("cpusername2"))
		 .clickOnSubmit()
		 .isForgotPasswordMailSent(PropertiesOperationsShaip.getPropertyValueByKey("cpusername2"));

		 getVerificationLink = EmailSearcher.doFortgotPasswordEmailVerification();
		 DriverFactoryShaip.getDriver().navigate().to(getVerificationLink);
		
  forgot
		 .isResetPasswordPageOpened()
		 .resetnewPassword("Testreset@123")
		 .resetConfirmPassword("Testreset@123")
		 .clickOnResetPassword()
		 .waitForPasswordResetSuccessMessageToaster();

	    
	}
	
	
	@Test(priority = 2,enabled=true,dependsOnMethods  = "VerifyForgotPasswordFunctionality",groups={"39"})
	@FrameworkAnnotation(category= {CategoryType.NONE},storyId= {story.ForgotPassword_CP_39},portal= {PortalType.CP})

	public void VerifyLoginWithNewlyResetPassword(ITestContext context,Method method) throws Throwable {

		setupExtentReport("CP: Verify that as a user i should be able login into portal with newly reset password", method.getName(),"General");
		giveUserInfo("VENDOR USER ===> "+PropertiesOperationsShaip.getPropertyValueByKey("cpusername2"));

		emailVerify = new EmailVerification_CP();


		loginPagecp
		 .navigateToCP("urlCP")
		 .loginToCP(PropertiesOperationsShaip.getPropertyValueByKey("cpusername2"), "Testreset@123")
	     .verifyTermsAndConditionPopUp()
		 .clickOnAgreeButton()
		 .verifyShaipURI();
		

	    
	}
	
	
	
	@Test(priority = 3,enabled=true,dependsOnMethods = "VerifyForgotPasswordFunctionality",groups={"39"})
	@FrameworkAnnotation(category= {CategoryType.NONE},storyId= {story.ForgotPassword_CP_39},portal= {PortalType.CP})

	public void VerifyPasswordChangedNotification(ITestContext context,Method method) throws Throwable {

		setupExtentReport("CP: System shall have an ability to generate a notification when the User RESET their password", method.getName(),"General");
		giveUserInfo("VENDOR USER ===> "+PropertiesOperationsShaip.getPropertyValueByKey("cpusername2"));

		LandingPage_CP lp=new LandingPage_CP();

		loginPagecp
		 .navigateToCP("urlCP")
		 .loginToCP(PropertiesOperationsShaip.getPropertyValueByKey("cpusername2"),  "Testreset@123")
	     .verifyTermsAndConditionPopUp()
		 .clickOnAgreeButton()
		 .verifyShaipURI();
		  db.resetSqlPassword("Test@123",PropertiesOperationsShaip.getPropertyValueByKey("cpusername2"));

		lp
		 .clickOnNotifications()
		 .verifyResetPasswordNotification()
		 .verifyMarkAsReadNotification("Reset Password")
		 .verifyReadNotificationCount()
		 .verifyMarkAsUnReadNotification("Reset Password")
		 .verifyUnreadNotificationCount();
		
		


		

	}
	

}

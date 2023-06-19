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
import com.shaip.page.cp.ChangePassword_CP;
import com.shaip.page.cp.LandingPage_CP;
import com.shaip.utils.DB_Operations;
import com.shaip.utils.PropertiesOperationsShaip;


public class VerifyChangePasswordFunctionality_CP extends BaseTest {
	
	
	ChangePassword_CP cp;
	DB_Operations db = new DB_Operations();

	
	@Test(priority = 1, dataProvider = "changePassword",enabled=true,groups={"SIT","36"})
	@FrameworkAnnotation(category= {CategoryType.SIT},storyId= {story.ChangePassword_CP_36},portal= {PortalType.CP})

	public void VerifyChangePasswordFunctionalityForNegativeScenarios(Object obj1,ITestContext context,Method method) throws Throwable {

		
		cp = new ChangePassword_CP();

		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("CP: Verify Change Password functionality with negative scenarios like ("+testData.get("TestName")+")", method.getName(),"General");
		giveTestDataInfo(testData,"TestName",PropertiesOperationsShaip.getPropertyValueByKey("cpusername1"));
	
		loginPagecp
			   .navigateToCP("urlCP")
			   .loginToCP(PropertiesOperationsShaip.getPropertyValueByKey("cpusername1"),PropertiesOperationsShaip.getPropertyValueByKey("cppassword1"))
		       .verifyTermsAndConditionPopUp()
		       .clickOnAgreeButton()
		       .verifyShaipURI()
		       .clickOnProfileMenu()
		       .clickOnAccount();
		cp.clickOnChangePasswordTab()
			   .enterOldPassword(testData.get("OldPassword"))
			   .enterNewPassword(testData.get("NewPassword"))
			   .enterConfirmPassword(testData.get("Cpassword"))
		   	   .clickOnSave()
			   .verifyChangePasswordValidations(testData.get("Enum"));

	}
	
	@Test(priority = 2,enabled=true,groups={"Smoke","36"})
	@FrameworkAnnotation(category= {CategoryType.SIT},storyId= {story.ChangePassword_CP_36},portal= {PortalType.CP})

	public void VerifyChangePasswordFunctionality(ITestContext context,Method method) throws Throwable {

		
		

		
		String updatedPassword="Updated@12345";
		
		cp = new ChangePassword_CP();
		
		setupExtentReport("CP: Verify that as a user i should be able to change my password and login with same", method.getName(),"General");
		giveUserInfo(PropertiesOperationsShaip.getPropertyValueByKey("cpusername1"));
	

		loginPagecp
			.navigateToCP("urlCP")
		    .loginToCP(PropertiesOperationsShaip.getPropertyValueByKey("cpusername1"),PropertiesOperationsShaip.getPropertyValueByKey("cppassword1"))
			.verifyTermsAndConditionPopUp()
			.clickOnAgreeButton()
			.verifyShaipURI()
			.clickOnNotifications()
			.deleteAllNotification("Change Password")
	       	.clickOnProfileMenu()
	       	.clickOnAccount();
		cp.clickOnChangePasswordTab()
			.enterOldPassword("Test@123")
			.enterNewPassword(updatedPassword)
			.enterConfirmPassword(updatedPassword)
			.clickOnSave()
			.verifyPasswordUpdateSuccessToaster();
		    context.setAttribute("updatedPassword", updatedPassword);
		    

	}
	
	
	
	@Test(priority = 3,enabled=true,dependsOnMethods = "VerifyChangePasswordFunctionality",groups={"SIT","Smoke","41","36"})
	@FrameworkAnnotation(category= {CategoryType.SIT,CategoryType.SMOKE},storyId= {story.ChangePassword_CP_36},portal= {PortalType.CP})

	public void VerifyPasswordChangedNotification(ITestContext context,Method method) throws Throwable {

		setupExtentReport("CP: System shall have an ability to generate a notification when the User change their password", method.getName(),"General");
		giveUserInfo(PropertiesOperationsShaip.getPropertyValueByKey("cpusername1"));

		LandingPage_CP lp=new LandingPage_CP();

		
		loginPagecp
		 .navigateToCP("urlCP")
		 .loginToCP(PropertiesOperationsShaip.getPropertyValueByKey("cpusername1"), (String) context.getAttribute("updatedPassword"))
	     .verifyTermsAndConditionPopUp()
		 .clickOnAgreeButton()
		 .verifyShaipURI();
		  db.resetSqlPassword("Test@123",PropertiesOperationsShaip.getPropertyValueByKey("cpusername1"));

		lp
		 .clickOnNotifications()
		 .verifyChangedPasswordNotification()
		 .verifyMarkAsReadNotification("Change Password")
		 .verifyReadNotificationCount()
		 .verifyMarkAsUnReadNotification("Change Password")
		 .verifyUnreadNotificationCount();

		
		
		


		

	}
	

	
	
	
	@DataProvider(name = "changePassword")
	public Object[][] getTestData2() throws IOException {

		return getData("CP","changePassword_cp");
	}

	
	
	
	
	
	
	

}

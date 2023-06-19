package com.shaip.testcase.cp;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shaip.annotations.FrameworkAnnotation;
import com.shaip.basetest.BaseTest;
import com.shaip.enums.CategoryType;
import com.shaip.enums.PortalType;
import com.shaip.enums.story;
import com.shaip.utils.PropertiesOperationsShaip;

public class VerifyLoginAndLogoutWithDifferentRoles_CP extends BaseTest{


	@Test(dataProvider = "LoginCreationData",groups = {"Smoke","SIT","27","28"},enabled=true,priority=1)
	@FrameworkAnnotation(category= {CategoryType.SIT,CategoryType.SMOKE},storyId= {story.Login_Logout_CP_SC2_27_28},portal= {PortalType.CP})

	public void verifyLoginAndLogoutWithDifferentUsersCP(Object obj1,Method method) throws Throwable {

		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("CP: Verify user is able to 'Login' and 'Logout' with a valid Credentials with ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		
		
		loginPagecp
				.navigateToCP("urlCP")
				.loginToCP(testData.get("UserName"), testData.get("Password"))
				.verifyTermsAndConditionPopUp()
				.clickOnAgreeButton()
				.verifyShaipURI()
				.verifyExpandAndCollapseMenuBarFunctionalityCP()
				.clickOnProfileMenu()
				.clickOnSignOut()
				.isLogoutSuccessfull("CP");
		
		

	}
	
	@Test(groups = {"SIT","27"},enabled=true,priority=2)
	@FrameworkAnnotation(category= {CategoryType.SIT},storyId= {story.Login_Logout_CP_SC2_27_28},portal= {PortalType.CP})

	public void verifyLoginWithInactiveaUserCP(Method method) throws Throwable {

	
		setupExtentReport("CP: Verify the user login when user is inactive", method.getName(),"General");
		giveUserInfo("Inactive user (Freelancer)");
		
		
		
		loginPagecp
				.navigateToCP("urlCP")
				.loginToCPPortal(PropertiesOperationsShaip.getPropertyValueByKey("inactiveusername"),PropertiesOperationsShaip.getPropertyValueByKey("inactivepassword"));
		loginPagecp.verifyDisabledUserErrorToaster();
		

	}
	
	
	
	
	
	// Dataprovider method --> return object array

	@DataProvider(name = "LoginCreationData")
	public Object[][] getTestData() throws IOException {

		return getData("CP","login_cp");
	}

}

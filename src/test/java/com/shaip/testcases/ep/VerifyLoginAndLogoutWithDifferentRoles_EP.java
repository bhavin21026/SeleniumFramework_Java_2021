package com.shaip.testcases.ep;

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

public class VerifyLoginAndLogoutWithDifferentRoles_EP extends BaseTest {

	

	//,groups= {"smoke"}
	@Test(dataProvider = "LoginCreationData",groups={"Smoke","73","74",})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Login_Logout_EP_SC2_73_74},portal= {PortalType.EP})
	public void verifyLoginWithDifferentUsers(Object obj1,Method method) throws Throwable {

		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify user is able to 'Login' and 'Logout' with a valid Credentials ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.verifyExpandAndCollapseMenuBarFunctionalityEP()
			.clickOnProfileMenu()
			.clickOnSignOut()
			.isLogoutSuccessfull("EP");

	}

	// Dataprovider method --> return object array
	 @DataProvider(name = "LoginCreationData")
	public Object[][] getTestData() throws IOException {

		return getData("EP","login");
	}

}

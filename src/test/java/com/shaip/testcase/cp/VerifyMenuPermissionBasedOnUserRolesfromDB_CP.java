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

public class VerifyMenuPermissionBasedOnUserRolesfromDB_CP extends BaseTest {

	

	
	@Test(dataProvider ="LoginCreationData",groups = {"30"})
	@FrameworkAnnotation(category= {CategoryType.NONE},storyId= {story.PrimarySecondary_Menu_Permission_CP_30},portal= {PortalType.CP})

	public void verifyMenuPermissionsForDifferentUsers(Object obj1,Method method) throws Throwable {

		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		setupExtentReport("CP: Verify user should be able to access primary and secondary menu based on user roles ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");

		
	loginPagecp
		.navigateToCP("urlCP")
		.loginToCP(testData.get("UserName"), testData.get("Password"))
		.verifyTermsAndConditionPopUp()
		.clickOnAgreeButton()
		.verifyShaipURI()
		.verifyPrimaryAndSecondaryMenuPermissionsFromDB(testData.get("UserName"));
		

	}

	
	@DataProvider(name = "LoginCreationData")
	public Object[][] getTestData() throws IOException {

		return getData("CP","login_cp");
	}

}

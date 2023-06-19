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

public class VerifyDeisagreeTermsFunctionality_CP extends BaseTest {


	@Test(dataProvider = "LoginCreationData",enabled=true,groups={"Smoke","29"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.TermsAndCondition_CP_29},portal= {PortalType.CP})

	public void verifyLoginWithDifferentUsers(Object obj1,Method method) throws Throwable {
		
	
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		setupExtentReport("CP: Verify that user should not be able to access portal if Disagree and Exit the Terms and Condition for ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		
		
		loginPagecp
			.navigateToCP("urlCP")
			.loginToCP(testData.get("UserName"), testData.get("Password"))
			.verifyTermsAndConditionPopUp()
			.clickOnDisagreeButton()
			.isLogoutSuccessfull("CP");
		
		
		

	}

	@DataProvider(name = "LoginCreationData")
	public Object[][] getTestData() throws IOException {

		return getData("CP","login_cp");
	}

}

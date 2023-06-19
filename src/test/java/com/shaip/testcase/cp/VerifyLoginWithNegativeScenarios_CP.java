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

public class VerifyLoginWithNegativeScenarios_CP extends BaseTest {

	

	@Test(dataProvider = "InvalidCredentials", priority = 1, groups = {"SIT","27","28"},enabled=true)
	@FrameworkAnnotation(category= {CategoryType.SIT},storyId= {story.Login_Logout_CP_SC2_27_28},portal= {PortalType.CP})

	public void verifyLoginWithInvalidcredentials(Object obj1, Method method) throws Throwable {

		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		setupExtentReport("CP: Verify login with invalid credentials ("+testData.get("TestName")+")",
				method.getName(),"General");
		giveTestDataInfo(testData, "TestName", "Any User");

		loginPagecp
		   .navigateToCP("urlCP")
		   .loginToCPPortal(testData.get("UserName"), testData.get("Password"));
		loginPagecp
	       .verifyErrorToaster();

	}

	@Test(dataProvider = "invalidFormat", priority = 2, groups = {"SIT","27","28"},enabled=true)
	@FrameworkAnnotation(category= {CategoryType.SIT},storyId= {story.Login_Logout_CP_SC2_27_28},portal= {PortalType.CP})

	public void verifyLoginWithInvalidcredentialsFormats(Object obj1, Method method) throws Throwable {

		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		setupExtentReport(
				"CP: Verify login with negative scenarios and invalid formats ("+testData.get("TestName")+")",
				method.getName(), "General");
		giveTestDataInfo(testData, "TestName", "Any User");

		loginPagecp
		   .navigateToCP("urlCP").enterEmail(testData.get("UserName"))
		   .enterPassword(testData.get("Password"))
		   .doSignIn()
		   .verifyValidations(testData.get("Enum"));

	}

	@DataProvider(name = "InvalidCredentials")
	public Object[][] getTestData() throws IOException {

		return getData("CP","InvalidCredentials_cp");
	}

	
	
	@DataProvider(name = "invalidFormat")
	public Object[][] getTestData2() throws IOException {

		return getData("CP","Invaliduser_cp");
	}

}

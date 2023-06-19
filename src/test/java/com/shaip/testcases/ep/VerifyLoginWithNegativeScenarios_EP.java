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

public class VerifyLoginWithNegativeScenarios_EP extends BaseTest {

	
	

	
	@Test(dataProvider = "InvalidCredentials",priority=1,groups={"SIT","73","74"})
	@FrameworkAnnotation(category= {CategoryType.SIT},storyId= {story.Login_Logout_EP_SC2_73_74},portal= {PortalType.EP})
	public void verifyLoginWithInvalidcredentials(Object obj1,Method method) throws Throwable {

		
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		setupExtentReport("EP: Verify login with invalid credentials ("+testData.get("TestName")+")", method.getName(),"General");
		giveTestDataInfo(testData,"TestName","Any User");
		
		
		loginPage
		   .navigateToEP("urlEP")
		   .loginToEPPortal(testData.get("UserName"), testData.get("Password"))
    	   .verifyErrorToaster();
		
	}
	
	
	
	@Test(dataProvider = "invalidFormat",priority=2,groups={"SIT","73","74"},enabled=true)
	@FrameworkAnnotation(category= {CategoryType.SIT},storyId= {story.Login_Logout_EP_SC2_73_74},portal= {PortalType.EP})
	public void verifyLoginWithInvalidcredentialsFormats(Object obj1,Method method) throws Throwable {

		
	
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		setupExtentReport("EP: Verify login with negative scenarios and invalid formats ("+testData.get("TestName")+")", method.getName(),"General");
		giveTestDataInfo(testData,"TestName","Any User");
		
	loginPage
	    .navigateToEP("urlEP")
		.enterEmail(testData.get("UserName"))
	    .enterPassword(testData.get("Password"))
		.doSignIn()
		.verifyValidations(testData.get("Enum"));
		

	}

	
	@DataProvider(name = "InvalidCredentials")
	public Object[][] getTestData() throws IOException {

		return getData("EP","InvalidCredentials");
	}

	
	
	@DataProvider(name = "invalidFormat")
	public Object[][] getTestData2() throws IOException {

		return getData("EP","Invaliduser");
	}

}

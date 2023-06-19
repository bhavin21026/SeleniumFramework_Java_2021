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
import com.shaip.page.cp.HelpPage_CP;

public class VerifyHelpMenuPagesAccessibleToUserFunctionality_CP extends BaseTest {

	
	HelpPage_CP helpcp;
	String username= "automation.shaip+9@shaip.com";  //Automation Freelancer user
    String password="Test@123";
	
	
	//,groups= {"smoke"}
	@Test(priority=1,enabled=true,dataProvider = "LoginCreationData",groups={"Smoke","33"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.HelpMenus_CP_33},portal= {PortalType.CP})

	public void verifyHelpMenuPagesAccessible(Object obj1,Method method) throws Throwable {
		
		
		
		helpcp=new HelpPage_CP();
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("CP: Verify that user should be able to access the Help and Various support features for ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		
		loginPagecp
		 .navigateToCP("urlCP")
		 .loginToCP(testData.get("UserName"), testData.get("Password"))
	     .verifyTermsAndConditionPopUp()
		 .clickOnAgreeButton()
		 .verifyShaipURI();
		helpcp
		.clickOnHelpMenu()
		.clickOnFAQ()
		.getAccessOfHelpSubMenu("FAQs")
		.clickOnHelpMenu()
		.clickOnContactUs()
		.getAccessOfHelpSubMenu("Contact Us")
		.clickOnHelpMenu()
		.clickOnPrivacyPolicy()
		.getAccessOfHelpSubMenu("Privacy Policy")
		.clickOnHelpMenu()
		.clickOnTermsOfService()
		.getAccessOfHelpSubMenu("Terms Of Service");

			
		
		
		
		
	}
	
	
	@DataProvider(name = "LoginCreationData")
	public Object[][] getTestData() throws IOException {

		return getData("CP","login_cp");
	}
	
	
	

}

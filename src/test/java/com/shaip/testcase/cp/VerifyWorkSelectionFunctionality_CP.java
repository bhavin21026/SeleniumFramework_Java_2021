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
import com.shaip.page.cp.WorkSelection_CP;


public class VerifyWorkSelectionFunctionality_CP extends BaseTest {

	
	public WorkSelection_CP work;
	//String username= "automation.shaip+9@shaip.com";  //Automation Freelancer user
    //String password="Test@123";

	
	
	
	//,groups= {"smoke"}
	@Test(priority=1,enabled=true,dataProvider="LoginCreationData",groups= {"Smoke","62"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.WorkSelection_CP_62},portal= {PortalType.CP})

	public void verifyWorkSelection(Object obj1, Method method) throws Throwable {

		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		setupExtentReport("CP: Verify list of all work type and list of categories available under work selection section for ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		work=new WorkSelection_CP();
	    
		loginPagecp
		 	.navigateToCP("urlCP")
		 	.loginToCP(testData.get("UserName"), testData.get("Password"))
			.verifyTermsAndConditionPopUp()
			.clickOnAgreeButton()
			.verifyShaipURI()
			.clickOnProfileMenu()
			.clickOnAccount();
		work
			.clickOnWorkSelection()
			.verifyWorkPreferencesTypeAndCategory()
			.clickOnSaveWorkSelection()
			.verifyWorkSelectionSuccessToaster();
			Thread.sleep(1000);
		
	}
	
	
	@Test(priority=2,enabled=true,dataProvider="LoginCreationData",groups= {"Smoke","62"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.WorkSelection_CP_62},portal= {PortalType.CP})

	public void verifySelectedWorkTypeAndCategory(Object obj1,Method method) throws Throwable {

		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		setupExtentReport("CP: Verify that user should be able to do work selection based on their proficiency for ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		work=new WorkSelection_CP();
	    
		loginPagecp
		 	.navigateToCP("urlCP")
		 	.loginToCP(testData.get("UserName"), testData.get("Password"))
			.verifyTermsAndConditionPopUp()
			.clickOnAgreeButton()
			.verifyShaipURI()
			.clickOnProfileMenu()
			.clickOnAccount();
		work
		.clickOnWorkSelection()
		.verifyWorkPreferencesTypeAndCategory()
		.clickOnSaveWorkSelection()
		.verifyWorkSelectionSuccessToaster()
		.verifySelectedTypeAndCategory();
		 Thread.sleep(1000);
		
	}
	
	@DataProvider(name = "LoginCreationData")
	public Object[][] getTestData() throws IOException {

		return getData("CP","login_cp");
	}
	
	

}

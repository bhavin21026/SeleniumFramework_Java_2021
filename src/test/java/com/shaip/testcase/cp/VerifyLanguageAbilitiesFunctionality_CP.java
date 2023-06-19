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
import com.shaip.page.cp.AccountPage_CP;
import com.shaip.page.cp.LanguageAbilities_CP;

public class VerifyLanguageAbilitiesFunctionality_CP extends BaseTest {

	
	AccountPage_CP account;
	LanguageAbilities_CP language;
	String username= "automation.shaip+9@shaip.com";  //Automation Freelancer user
    String password="Test@123";
	
	
	
	//,groups= {"smoke"}
	@Test(priority=1,enabled=true,dataProvider="LoginCreationData",groups= {"Smoke","64"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.LanguageAbilities_CP_64},portal= {PortalType.CP})

	public void verifyLanguageAbilitiesSelection(Object obj1,Method method) throws Throwable {

		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		setupExtentReport("CP: Verify that user should be able to add Language Abilities for ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		language=new LanguageAbilities_CP();
	    
		
		loginPagecp
		 	.navigateToCP("urlCP")
		 	.loginToCP(testData.get("UserName"), testData.get("Password"))
			.verifyTermsAndConditionPopUp()
			.clickOnAgreeButton()
			.verifyShaipURI()
			.clickOnProfileMenu()
			.clickOnAccount();
		
		
		language
			.clickOnLanguageAbilitiesSection()
			.checkForExistingLanguage()
			.clickOnAddLanguage()
			.selectLanguageAbilities("AKan","Ghana","Native")
			.selectUsersRegion("Bono")
			.clickOnSave()
			.verifyLanguageSuccessToaster();
		
		     
		
	}
	
	
	@Test(priority=2,enabled=true,dataProvider="LoginCreationData",groups= {"SIT","64"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.LanguageAbilities_CP_64},portal= {PortalType.CP})

	public void verifySameLanguageAddedAgainFunctionality(Object obj1,Method method) throws Throwable {

		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		setupExtentReport("CP: Verify system behaviour when user try to add same language twice under 'Add Language Abilities' section for ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		language=new LanguageAbilities_CP();
	  
		
	    loginPagecp
	    	.navigateToCP("urlCP")
	    	.loginToCP(testData.get("UserName"), testData.get("Password"))
			.verifyTermsAndConditionPopUp()
			.clickOnAgreeButton()
			.verifyShaipURI()
			.clickOnProfileMenu()
			.clickOnAccount();
	    language
			.clickOnLanguageAbilitiesSection()
		    .checkForExistingLanguage()
			.clickOnAddLanguage()
			.selectLanguageAbilities("Hindi","India","Native")
			.selectUsersRegion("Bihar")
			.clickOnSave()
		    .verifyLanguageSuccessToaster()
			.clickOnAddLanguage()
			.selectLanguageAbilities("Hindi","India","Native")
			.selectUsersRegion("Bihar")
			.clickOnSave()
			.verifyDuplicateLanguageErrorToaster();
		
		
	}
	
	@Test(priority=3,enabled=true,dataProvider="LoginCreationData",groups= {"SIT","64"})
	@FrameworkAnnotation(category= {CategoryType.SIT},storyId= {story.LanguageAbilities_CP_64},portal= {PortalType.CP})

	public void verifyMaximumLanguageCanBeAdded(Object obj1,Method method) throws Throwable {

		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		setupExtentReport("CP: Verify Maximum number of allowed languages for language abilities (Max four allowed) for ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		language=new LanguageAbilities_CP();
	  
		
	    loginPagecp
	    	.navigateToCP("urlCP")
	    	.loginToCP(testData.get("UserName"), testData.get("Password"))
			.verifyTermsAndConditionPopUp()
			.clickOnAgreeButton()
			.verifyShaipURI()
			.clickOnProfileMenu()
			.clickOnAccount();
	    language
		    .clickOnLanguageAbilitiesSection()
		    .checkForExistingLanguage()
			.clickOnAddLanguage()
			.selectLanguageAbilities("Hindi","India","Native")
			.selectUsersRegion("Bihar")
			.clickOnSave()
		    .verifyLanguageSuccessToaster()
			.clickOnAddLanguage()
			.selectLanguageAbilities("Bambara","Mali","Fluent")
			.clickOnSave()
			.verifyLanguageSuccessToaster()
			.clickOnAddLanguage()
			.selectLanguageAbilities("Afrikaans","Namibia","Intermediate")
			.clickOnSave()
			.verifyLanguageSuccessToaster()
			.clickOnAddLanguage()
			.selectLanguageAbilities("English","Russia","Basic")
			.clickOnSave()
			.verifyLanguageSuccessToaster()
			.verifyAddLanguageButtonState();
			
			Thread.sleep(5000);
		
	}
	
	
	@Test(priority=4,enabled=true,dataProvider="LoginCreationData",groups= {"SIT","64"})
	@FrameworkAnnotation(category= {CategoryType.SIT},storyId= {story.LanguageAbilities_CP_64},portal= {PortalType.CP})

	public void verifyEditLanguageAbilities(Object obj1,Method method) throws Throwable {

		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		setupExtentReport("CP: Verify Edit functionality for added language abilities for ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		language=new LanguageAbilities_CP();
	   
		
	    loginPagecp
	    	.navigateToCP("urlCP")
	    	.loginToCP(testData.get("UserName"), testData.get("Password"))
			.verifyTermsAndConditionPopUp()
			.clickOnAgreeButton()
			.verifyShaipURI()
			.clickOnProfileMenu()
			.clickOnAccount();
         language
		    .clickOnLanguageAbilitiesSection()
         	.checkForExistingLanguage()
         	.clickOnAddLanguage()
			.selectLanguageAbilities("English","Russia","Basic")
			.clickOnSave()
			.verifyLanguageSuccessToaster()
			.clickOnEditLanguageAbilities()
			.editLanguageAbilities("AKan","Ghana","Native")
			.selectUsersRegion("Bono")
			.clickOnSave()
			.verifyLanguageUpdatedSuccessfullyToaster();
			
		
	}
	
	@Test(priority=5,enabled=true,dataProvider="LoginCreationData",groups= {"SIT","64"})
	@FrameworkAnnotation(category= {CategoryType.SIT},storyId= {story.LanguageAbilities_CP_64},portal= {PortalType.CP})

	public void verifyDeleteLanguageAbilities(Object obj1,Method method) throws Throwable {

		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		setupExtentReport("CP: Verify Delete functionality for added language abilities for ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		language=new LanguageAbilities_CP();
	   
		
	    loginPagecp
	    	.navigateToCP("urlCP")
	    	.loginToCP(testData.get("UserName"), testData.get("Password"))
			.verifyTermsAndConditionPopUp()
			.clickOnAgreeButton()
			.verifyShaipURI()
			.clickOnProfileMenu()
			.clickOnAccount();
	    language
		    .clickOnLanguageAbilitiesSection()
	     	.checkForExistingLanguage()
	     	.clickOnAddLanguage()
			.selectLanguageAbilities("English","Russia","Basic")
			.clickOnSave()
			.verifyLanguageSuccessToaster()
			.clickOnDeleteLanguageAbilities();
			
	
			
		
	}
	
	@DataProvider(name = "LoginCreationData")
	public Object[][] getTestData() throws IOException {

		return getData("CP","login_cp");
	}
	
	
	
	
	
	

}

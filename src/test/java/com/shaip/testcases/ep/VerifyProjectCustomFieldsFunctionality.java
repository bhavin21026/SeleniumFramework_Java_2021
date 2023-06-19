package com.shaip.testcases.ep;

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
import com.shaip.enums.ProjectType;
import com.shaip.enums.story;
import com.shaip.page.ep.CustomFieldProjectsPage_EP;
import com.shaip.page.ep.CustomerUsersPage_EP;
import com.shaip.page.ep.MultipleCustomFieldProjectsPage_EP;

public final class VerifyProjectCustomFieldsFunctionality extends BaseTest {

	private VerifyProjectCustomFieldsFunctionality() {}
	
	

	
	
	@Test(priority=1,dataProvider="PermissionMatrix",enabled=false,groups = {"Smoke","788"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Manage_Custom_Field_EP_788},portal= {PortalType.EP})

	public void verifyCreateCustomFieldButtonAccessAC(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify create custom field button access to permitted user ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.searchProject("Collection");
	   new CustomFieldProjectsPage_EP()
		   .clickOnMoreOptions()
		   .clickOnSettings()
		   .clickOnCustomFieldTab()
	       .verifyAddCustomFieledButtonPermissionAccess(testData.get("UserRole"),testData.get("CreateCustomField"));

		
	}
	
	@Test(priority=2,dataProvider="createCustomField",enabled=true,groups = {"Smoke","804"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.CheckboxCustomField_EP_804},portal= {PortalType.EP})

	public void verifyAddCustomFieldsFunctionality_AC(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		setupExtentReport("CP: Verify Custom fields creation functionality for Audio Collection project", method.getName(),testData.get("UserRole"));

		
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.clickOnAddProjectButton()
			.createNewProject(ProjectType.AudioCollection)
			.addBasicInformationOfProject(ProjectType.AudioCollection,context,"QA","Test")
			.searchCustomer((String) context.getAttribute("ProjectName"));
	   new CustomFieldProjectsPage_EP()
		   .clickOnMoreOptions()
		   .clickOnSettings()
		   .clickOnCustomFieldTab()
		   .addCustomFields(testData.get("UserRole"),method.getName(),testData,"Collection");
	       

		
	}
	
	@Test(priority=3,dataProvider="createCustomField",enabled=true,groups = {"Smoke","695"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.RadioCustomField_EP_695},portal= {PortalType.EP})

	public void verifyAddCustomFieldsFunctionality_AT(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		setupExtentReport("CP: Verify Custom fields creation functionality for Audio Transcription project", method.getName(),testData.get("UserRole"));

		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.clickOnAddProjectButton()
			.createNewProject(ProjectType.AudioTranscription)
			.addBasicInformationOfProject(ProjectType.AudioTranscription,context,"Automation","Test")
			.searchCustomer((String) context.getAttribute("ProjectName"));
	   new CustomFieldProjectsPage_EP()
		   .clickOnMoreOptions()
		   .clickOnSettings()
		   .clickOnCustomFieldTab()
		   .addCustomFields(testData.get("UserRole"),method.getName(),testData,"Transcription");
	   
	      
	       

		
	}
	

	
	
	
	
	@DataProvider(name = "PermissionMatrix")
	public Object[][] getTestData() throws IOException {

		return getData("EP","PermissionMatrix");
	}
	
	@DataProvider(name = "createCustomField")
	public Object[][] getTestData2() throws IOException {

		return getData("EP","createCustomField");
	}


}

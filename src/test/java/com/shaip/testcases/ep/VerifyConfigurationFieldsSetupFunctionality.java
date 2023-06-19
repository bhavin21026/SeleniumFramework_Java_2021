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
import com.shaip.page.ep.ConfigurationField_EP;
import com.shaip.page.ep.CustomFieldProjectsPage_EP;
import com.shaip.page.ep.CustomerUsersPage_EP;
import com.shaip.page.ep.ProjectsPage_EP;
import com.shaip.page.ep.TeamMemberPage_EP;
import com.shaip.page.ep.WorkFlowProjectPage_EP;

public final class VerifyConfigurationFieldsSetupFunctionality extends BaseTest {

	private VerifyConfigurationFieldsSetupFunctionality() {}
	
	
	@Test(priority=1,dataProvider="project",enabled=true,groups = {"Smoke","153"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.ManageConfiguration_EP_153},portal= {PortalType.EP})

	public void verifyConfigurationFieldsSetupForAudioTranscriptionProject(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify all configuration fields for Audio Transcription project ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.clickOnAddProjectButton()
			.createNewProject(ProjectType.AudioTranscription)
			.addBasicInformationOfProject(ProjectType.AudioTranscription,context,"QA","Test")
			.searchCustomer((String) context.getAttribute("ProjectName"));
	   new CustomFieldProjectsPage_EP()
		    .clickOnMoreOptions();
	   new ConfigurationField_EP()
	   		.clickOnSettings()
	   		.verifyAllConfigurationFieldsAvailable(ProjectType.AudioTranscription)
	   		.setupConfigFieldsForAT("44.1 Khz","24 bit","300","1600","multiple");
	   
	}

	@Test(priority=2,dataProvider="project",enabled=true,groups = {"Smoke","153"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.ManageConfiguration_EP_153},portal= {PortalType.EP})

	public void verifyConfigurationFieldsSetupForAudioCollectionProject(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify all configuration fields for Audio Collection project ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
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
		    .clickOnMoreOptions();
	   new ConfigurationField_EP()
	   		.clickOnSettings()
	   		.verifyAllConfigurationFieldsAvailable(ProjectType.AudioCollection)
	   		.setupConfigFieldsForAC("24 Khz","16 bit","10","300","single");
	   
	}

	
	
	
	
	@DataProvider(name = "PermissionMatrix")
	public Object[][] getTestData() throws IOException {

		return getData("EP","PermissionMatrix");
	}
	
	@DataProvider(name = "project")
	public Object[][] getTestData2() throws IOException {

		return getData("EP","project");
	}


}

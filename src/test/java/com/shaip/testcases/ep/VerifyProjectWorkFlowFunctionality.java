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
import com.shaip.page.ep.WorkFlowProjectPage_EP;

public final class VerifyProjectWorkFlowFunctionality extends BaseTest {

	private VerifyProjectWorkFlowFunctionality() {}
	
	
	@Test(priority=1,dataProvider="PermissionMatrix",enabled=true,groups = {"Smoke","154"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.AddAndEditWorkflowSteps_EP_154},portal= {PortalType.EP})

	public void verifyCreateWorkflowButtonAccess(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: As permitted User, I should have access to create workflow steps ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.searchProject("Collection");
	   new CustomFieldProjectsPage_EP()
		    .clickOnMoreOptions();
	   new WorkFlowProjectPage_EP()
		   	.clickOnWorkflow()
		 	.verifyAddWorkflowStepsPermissionAccess(testData.get("UserRole"),testData.get("AddWorkflowStep"));

	}

	
	
	@Test(priority=2,dataProvider="project",enabled=true,groups = {"Smoke","2905"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.ViewProjectWorkflowSteps_EP_2905},portal= {PortalType.EP})

	public void verifyDefaultWorkflowSteps(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: As a User, I should be able to view the project workflow steps ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.clickOnAddProjectButton()
			.createNewProject(ProjectType.AudioCollection)
			.addBasicInformationOfProject(ProjectType.AudioCollection,context,"Qa","Test")
			.searchCustomer((String) context.getAttribute("ProjectName"));
	   new CustomFieldProjectsPage_EP()
		    .clickOnMoreOptions();
	   new WorkFlowProjectPage_EP()
		   	.clickOnWorkflow()
		 	.verifyDefaultWorkFlowSteps(ProjectType.AudioCollection);
	}
	
	@Test(priority=3,dataProvider="project",enabled=true,groups = {"Smoke","154"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.AddAndEditWorkflowSteps_EP_154},portal= {PortalType.EP})

	public void verifyCreateNewWorkflowStepFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: As a User, I should be able to add the project workflow steps ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.clickOnAddProjectButton()
			.createNewProject(ProjectType.AudioCollection)
			.addBasicInformationOfProject(ProjectType.AudioCollection,context,"Qa","Test")
			.searchCustomer((String) context.getAttribute("ProjectName"));
	   new CustomFieldProjectsPage_EP()
		    .clickOnMoreOptions();
	   new WorkFlowProjectPage_EP()
		   	.clickOnWorkflow()
		   	.addNewWorkflowStep(context)
		   	.verifyWorkflowSuccessToaster()
		   	.verifyAddedWorkflowStepDetails(context,"stepName");
		
	}
	
	@Test(priority=4,dataProvider="project",enabled=true,groups = {"Smoke","154"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.AddAndEditWorkflowSteps_EP_154},portal= {PortalType.EP})

	public void verifyEditWorkflowStepFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: As a User, I should be able to Edit project workflow steps ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.clickOnAddProjectButton()
			.createNewProject(ProjectType.AudioCollection)
			.addBasicInformationOfProject(ProjectType.AudioCollection,context,"Qa","Test")
			.searchCustomer((String) context.getAttribute("ProjectName"));
	   new CustomFieldProjectsPage_EP()
		    .clickOnMoreOptions();
	   new WorkFlowProjectPage_EP()
		   	.clickOnWorkflow()
		   	.addNewWorkflowStep(context)
		   	.verifyWorkflowSuccessToaster()
		   	.verifyAddedWorkflowStepDetails(context,"stepName")
		   	.editWorkflowDetails(context)
		   	.verifyEditWorkflowSuccessToaster()
		   	.verifyAddedWorkflowStepDetails(context, "updatedStep");
		
	}
	
	@Test(priority=5,dataProvider="project",enabled=true,groups = {"Smoke","2904"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.DeleteWorkflowSteps_EP_2904},portal= {PortalType.EP})

	public void verifyDeleteWorkflowStepFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: As a User, I should be able to Delete project workflow steps ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.clickOnAddProjectButton()
			.createNewProject(ProjectType.AudioCollection)
			.addBasicInformationOfProject(ProjectType.AudioCollection,context,"Qa","Test")
			.searchCustomer((String) context.getAttribute("ProjectName"));
	   new CustomFieldProjectsPage_EP()
		    .clickOnMoreOptions();
	   new WorkFlowProjectPage_EP()
		   	.clickOnWorkflow()
		   	.addNewWorkflowStep(context)
		   	.verifyWorkflowSuccessToaster()
		   	.verifyAddedWorkflowStepDetails(context,"stepName")
		   	.deleteWorkflowStep(context)
		   	.verifyDeleteWorkflowSuccessToaster();
		   	
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

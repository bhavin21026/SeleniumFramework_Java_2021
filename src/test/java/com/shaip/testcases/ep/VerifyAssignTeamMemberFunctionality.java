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
import com.shaip.page.ep.ProjectsPage_EP;
import com.shaip.page.ep.TeamMemberPage_EP;
import com.shaip.page.ep.WorkFlowProjectPage_EP;

public final class VerifyAssignTeamMemberFunctionality extends BaseTest {

	private VerifyAssignTeamMemberFunctionality() {}
	
	
	@Test(priority=1,dataProvider="PermissionMatrix",enabled=true,groups = {"Smoke","155"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.AddTeamMembers_EP_155},portal= {PortalType.EP})

	public void verifyAssignTeamMemberButtonAccess(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: As permitted User, I should have access to assign team members to worlflow step ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.searchProject("Collection");
	   new CustomFieldProjectsPage_EP()
		    .clickOnMoreOptions();
	   new TeamMemberPage_EP()
	   		.clickOnTeams()
	   		.verifyAssignTeamMemberPermissionAccess(testData.get("UserRole"),testData.get("AddTeamMember"));
	   
	}

	
	
	@Test(priority=2,dataProvider="teamAssignment",enabled=true,groups = {"Smoke","2906"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.DynamicTeamAssignmentTabs_EP_2906},portal= {PortalType.EP})

	public void verifyViewDynamicTeamAssignmentTabs(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: As a User, I should be able view dynamic team assignment tabs based on workflow steps ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
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
	   new WorkFlowProjectPage_EP()
		   	.clickOnWorkflow()
		   	.addNewWorkflowStep(context)
		   	.verifyWorkflowSuccessToaster()
		   	.verifyAddedWorkflowStepDetails(context,"stepName")
		   	.clickOnHeaderBack();
	   new ProjectsPage_EP()
	   		.searchCustomer((String) context.getAttribute("ProjectName"));
	   new CustomFieldProjectsPage_EP()
	    	.clickOnMoreOptions();
	   new TeamMemberPage_EP()
	   		.clickOnTeams()
	   		.verifyDynamicTeamAssignmenTabsDetails(ProjectType.AudioCollection);
	   
 
	       

		
	}
	
	@Test(priority=3,dataProvider="teamAssignment",enabled=true,groups = {"Smoke","155"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.AddTeamMembers_EP_155},portal= {PortalType.EP})

	public void verifyAssignTeamMemberFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: As a User, I should be able to add team members to the workflow step ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
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
	   new WorkFlowProjectPage_EP()
		   	.clickOnWorkflow()
		   	.addNewWorkflowStep(context)
		   	.verifyWorkflowSuccessToaster()
		   	.verifyAddedWorkflowStepDetails(context,"stepName")
	   		.clickOnHeaderBack();
	   new ProjectsPage_EP()
		   	.searchCustomer((String) context.getAttribute("ProjectName"));
	   new CustomFieldProjectsPage_EP()
	    	.clickOnMoreOptions();
	   new TeamMemberPage_EP()
	   		.clickOnTeams()
	   		.assignMembers(context);
	   
 
	       

		
	}
	
	@Test(priority=4,dataProvider="teamAssignment",enabled=true,groups = {"Smoke","2907"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.RemoveTeamMembers_EP_2907},portal= {PortalType.EP})

	public void verifyRemoveTeamMemberFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: As a User, I should be able to remove team members to the workflow step ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
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
	   new WorkFlowProjectPage_EP()
		   	.clickOnWorkflow()
		   	.addNewWorkflowStep(context)
		   	.verifyWorkflowSuccessToaster()
		   	.verifyAddedWorkflowStepDetails(context,"stepName")
	   		.clickOnHeaderBack();
	   new ProjectsPage_EP()
		   	.searchCustomer((String) context.getAttribute("ProjectName"));
	   new CustomFieldProjectsPage_EP()
	    	.clickOnMoreOptions();
	   new TeamMemberPage_EP()
	   		.clickOnTeams()
	   		.assignMembers(context)
	   		.removeMember();
	   
 
	       

		
	}
	
	
	
	@DataProvider(name = "PermissionMatrix")
	public Object[][] getTestData() throws IOException {

		return getData("EP","PermissionMatrix");
	}
	
	@DataProvider(name = "teamAssignment")
	public Object[][] getTestData2() throws IOException {

		return getData("EP","teamAssignment");
	}


}

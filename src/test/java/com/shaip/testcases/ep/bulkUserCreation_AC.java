package com.shaip.testcases.ep;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shaip.annotations.FrameworkAnnotation;
import com.shaip.base.FrameworkContstants;
import com.shaip.base.ProjectConfigsAC;
import com.shaip.basetest.BaseTest;
import com.shaip.enums.CategoryType;
import com.shaip.enums.PortalType;
import com.shaip.enums.ProjectType;
import com.shaip.enums.story;
import com.shaip.page.cp.ProjectsPage_CP;
import com.shaip.page.cp.taskExecutionScreen_CP;
import com.shaip.page.ep.BatchesPage_EP;
import com.shaip.page.ep.ConfigurationField_EP;
import com.shaip.page.ep.CustomFieldProjectsPage_EP;
import com.shaip.page.ep.CustomerUsersPage_EP;
import com.shaip.page.ep.ProjectsPage_EP;
import com.shaip.page.ep.TeamMemberPage_EP;
import com.shaip.page.ep.WorkFlowProjectPage_EP;
import com.shaip.utils.DB_Operations;
import com.shaip.utils.PropertiesOperationsShaip;

public final class bulkUserCreation_AC extends BaseTest {

	private bulkUserCreation_AC() {}
	
	
	
	@Test(priority=2,dataProvider="project2",enabled=true,groups = {"SIT","Smoke","1017"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.AudioCollectionWorkFlow_CP_1017},portal= {PortalType.EP})

	public void createEndToEndProjectWithUsers_AC(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify End to end Audio collection project flow functionality" , method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.clickOnAddProjectButton()
			.createNewProject(ProjectType.AudioCollection)
			.addBasicInformationOfProject(ProjectType.AudioCollection,context,"JM Performance","Performance JMeter")
			.searchCustomer((String) context.getAttribute("ProjectName"))
			.publishProject();
		new CustomFieldProjectsPage_EP()
		    .clickOnMoreOptions();
	    new ConfigurationField_EP()
	   		.clickOnSettings()
	   		.setupConfigFieldsForAC(ProjectConfigsAC.setRate(),ProjectConfigsAC.setDepth(),ProjectConfigsAC.setMin_AC2(),ProjectConfigsAC.setMax_AC(),"single")
	    	.clickOnHeaderBack();
	    /*new ProjectsPage_EP()
   			.searchCustomer((String) context.getAttribute("ProjectName"));
	    new CustomFieldProjectsPage_EP()
	    	.clickOnMoreOptions();
	    new WorkFlowProjectPage_EP()
		   	.clickOnWorkflow()
		   	.addNewWorkflowStep(context)
		   	.verifyWorkflowSuccessToaster()
		   	.verifyAddedWorkflowStepDetails(context,"stepName")
	    	.clickOnHeaderBack();*/
	    new ProjectsPage_EP()
	   		.searchCustomer((String) context.getAttribute("ProjectName"));
	    new CustomFieldProjectsPage_EP()
    		.clickOnMoreOptions();
	    new TeamMemberPage_EP()
   			.clickOnTeams()
   			.assignProject(context,"aotomation.shaip+perfTest")
   			.clickOnHeaderBack();
	    new ProjectsPage_EP()
			.searchCustomer((String) context.getAttribute("ProjectName"));
	    /* new CustomFieldProjectsPage_EP()
    		.clickOnMoreOptions();
	   new BatchesPage_EP()
			.clickOnBatches()
			.addNewBatch(context,ProjectType.AudioCollection,ProjectConfigsAC.setBatchName(),ProjectConfigsAC.setBatchTotal(),10);*/
			
			
			
	}
	
	@Test(priority=1,dataProvider="project2",enabled=false,groups = {"Smoke","1017"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.AudioCollectionWorkFlow_CP_1017},portal= {PortalType.CP})

	public void createMultipleCustomerContributorUSers(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("CP: Multiple customer contributor user creation" , method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnAdministration()
			.clickOnUsers()
			.clickOnAddButton()
			.clickAddBulkUserMenu()
			.uploadBulkUserFile();	
		
		Thread.sleep(120000);
		
		DB_Operations db = new DB_Operations();
		db.resetAllPassword("Test@123");

		    
	        
			
	   
			
	}
	
	


	@DataProvider(name = "project2")
	public Object[][] getTestData() throws IOException {

		return getData("EP","project2");
	}
	
	

}

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
import com.shaip.page.ep.ProjectsPage_EP;
import com.shaip.page.ep.TeamMemberPage_EP;
import com.shaip.page.ep.WorkFlowProjectPage_EP;
import com.shaip.utils.PropertiesOperationsShaip;

public final class bulkTaskCreation_AC extends BaseTest {

	private bulkTaskCreation_AC() {}
	
	
	
	@Test(priority=1,dataProvider="project2",enabled=false,groups = {"SIT","Smoke","1017"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.AudioCollectionWorkFlow_CP_1017},portal= {PortalType.EP})

	public void createEndToEndProject_AC(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
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
			.addBasicInformationOfProject(ProjectType.AudioCollection,context,ProjectConfigsAC.setProjectName(),ProjectConfigsAC.setProjectDisplayName())
			.searchCustomer((String) context.getAttribute("ProjectName"))
			.publishProject();
		new CustomFieldProjectsPage_EP()
		    .clickOnMoreOptions();
	    new ConfigurationField_EP()
	   		.clickOnSettings()
	   		.setupConfigFieldsForAC(ProjectConfigsAC.setRate(),ProjectConfigsAC.setDepth(),ProjectConfigsAC.setMin_AC2(),ProjectConfigsAC.setMax_AC(),"single")
	    	.clickOnHeaderBack();
	    new ProjectsPage_EP()
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
   			.clickOnHeaderBack();
	    new ProjectsPage_EP()
			.searchCustomer((String) context.getAttribute("ProjectName"));
	    new CustomFieldProjectsPage_EP()
    		.clickOnMoreOptions();
	    new BatchesPage_EP()
			.clickOnBatches()
			.addNewBatch(context,ProjectType.AudioCollection,ProjectConfigsAC.setBatchName(),ProjectConfigsAC.setBatchTotal())
			.verifiedCreatedBatch(context.getAttribute("batch").toString());
			
			
	}
	
	@Test(priority=2,dataProvider="project2",enabled=false,groups = {"Smoke","1017"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.AudioCollectionWorkFlow_CP_1017},portal= {PortalType.CP})

	public void verifyCreatedProjectAtCP(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("CP: As a contributor user, I should be able to view assigned project so I can work on it" , method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		
		 loginPagecp
	         .navigateToCP("urlCP")
			 .loginToCP(PropertiesOperationsShaip.getPropertyValueByKey("collect"),PropertiesOperationsShaip.getPropertyValueByKey("c_password"))
	         .verifyTermsAndConditionPopUp()
	         .clickOnAgreeButton()
	         .verifyShaipURI()
	         .clickOnProjects()
		     //.searchCustomer((String) context.getAttribute("DisplayName"));
	         .searchProject("AutoBulkTask_QA(Audio Collection)15012022143244");
	        
			
	   
			
	}
	
	
	
	
	@Test(priority=3,dataProvider="project2",enabled=true,groups = {"SIT","3589"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.TaskExecutionScreen_3589},portal= {PortalType.EP})

	public void verifyCollectToQAFlowFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("CP: Verify 'COLLECT TO QA' workflow for all batch task in Audio collection project" , method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		
		 loginPagecp
	         .navigateToCP("urlCP")
			 .loginToCP(PropertiesOperationsShaip.getPropertyValueByKey("collect"),PropertiesOperationsShaip.getPropertyValueByKey("c_password"))
	         .verifyTermsAndConditionPopUp()
	         .clickOnAgreeButton()
	         .verifyShaipURI()
	         .clickOnProjects()
		     //.searchCustomer((String) context.getAttribute("DisplayName"))
	         .searchProject("IngestionChangeQA(Audio Collection)14022022110629")
	         .clickOnTasksList();
		 new taskExecutionScreen_CP()
	         .collectAllAssignedTask("QA");
		 
		
	     
	    
	         
	    

			
	}
	
	@Test(priority=4,dataProvider="project2",enabled=false,groups = {"SIT","3589"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.TaskExecutionScreen_3589},portal= {PortalType.EP})

	public void verifyQAToCQAFlowFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		
		 
		 setupExtentReport("CP: Verify 'QA TO CQA' workflow for all batch task in Audio collection project" , method.getName(),testData.get("UserRole"));
		 giveTestRoleInfo(testData,"UserRole");
		 
		 loginPagecp
	         .navigateToCP("urlCP")
			 .loginToCP(PropertiesOperationsShaip.getPropertyValueByKey("QA"),PropertiesOperationsShaip.getPropertyValueByKey("qa_password"))
	         .verifyTermsAndConditionPopUp()
	         .clickOnAgreeButton()
	         .verifyShaipURI()
	         .clickOnProjects()
		     //.searchCustomer((String) context.getAttribute("DisplayName"))
	         .searchProject("AutoBulkTask_QA(Audio Collection)15012022143244")
	         .clickOnTasksList();
	     new taskExecutionScreen_CP()
	         .verifyCollectedTask("CQA","QA");
	     
			
	}
	
	@Test(priority=5,dataProvider="project2",enabled=true,groups = {"SIT","3589"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.TaskExecutionScreen_3589},portal= {PortalType.EP})

	public void verifyCQAToCompleteFlowFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		
		 
		 setupExtentReport("CP: Verify 'CQA TO Complete' workflow for all batch task in Audio collection project" , method.getName(),testData.get("UserRole"));
		 giveTestRoleInfo(testData,"UserRole");
		 
	     loginPagecp
         .navigateToCP("urlCP")
			 .loginToCP(PropertiesOperationsShaip.getPropertyValueByKey("QA"),PropertiesOperationsShaip.getPropertyValueByKey("qa_password"))
	         .verifyTermsAndConditionPopUp()
	         .clickOnAgreeButton()
	         .verifyShaipURI()
	         .clickOnProjects()
		     //.searchCustomer((String) context.getAttribute("DisplayName"))
		     .searchProject("IngestionChangeQA(Audio Collection)14022022110629")
	         .clickOnTasksList();
	     new taskExecutionScreen_CP()
	         .verifyCompleteTask("Complete","QA");
			
	}




	
	
	
	
	
	@DataProvider(name = "project2")
	public Object[][] getTestData() throws IOException {

		return getData("EP","project2");
	}
	
	

}

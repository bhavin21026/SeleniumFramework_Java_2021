package com.shaip.testcases.ep;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shaip.annotations.FrameworkAnnotation;
import com.shaip.base.FrameworkContstants;
import com.shaip.base.ProjectConfigsAT;
import com.shaip.basetest.BaseTest;
import com.shaip.enums.CategoryType;
import com.shaip.enums.PortalType;
import com.shaip.enums.ProjectType;
import com.shaip.enums.story;
import com.shaip.page.cp.taskExecutionScreen_CP;
import com.shaip.page.ep.BatchesPage_EP;
import com.shaip.page.ep.ConfigurationField_EP;
import com.shaip.page.ep.CustomFieldProjectsPage_EP;
import com.shaip.page.ep.CustomerUsersPage_EP;
import com.shaip.page.ep.ProjectsPage_EP;
import com.shaip.page.ep.TeamMemberPage_EP;
import com.shaip.page.ep.WorkFlowProjectPage_EP;
import com.shaip.utils.PropertiesOperationsShaip;

public final class VerifyEndToEndProjectFlow_AT extends BaseTest {

	private VerifyEndToEndProjectFlow_AT() {}
	
	
	
	@Test(priority=1,dataProvider="project",enabled=true,groups = {"SIT","2824"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.DataImportFromCloudUpload_2824},portal= {PortalType.EP})

	public void createEndToEndProject_AT(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify End to end project flow functionality" , method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.clickOnAddProjectButton()
			.createNewProject(ProjectType.AudioTranscription)
			.addBasicInformationOfProject(ProjectType.AudioTranscription,context,ProjectConfigsAT.setProjectName(),ProjectConfigsAT.setProjectDisplayName())
			.searchCustomer((String) context.getAttribute("ProjectName"))
			.publishProject();
		new CustomFieldProjectsPage_EP()
		    .clickOnMoreOptions();
	    new ConfigurationField_EP()
	   		.clickOnSettings()
	   		.setupAllConfigFieldsForAT(ProjectConfigsAT.setRate(),ProjectConfigsAT.setDepth(),ProjectConfigsAT.setMin_AT(),ProjectConfigsAT.setMax_AT(),"multiple");
		new CustomFieldProjectsPage_EP()
	   		.clickOnCustomFieldTab()
	   		//.addCustomFields()
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
			.addNewBatch(context,ProjectType.AudioTranscription,ProjectConfigsAT.setBatchName(),ProjectConfigsAT.setBatchTotal())
			.verifiedCreatedBatch(context.getAttribute("batch").toString())
			.clickOnCloudUpload()
			.setupAmazonS3ForSync(FrameworkContstants.getAccessKeyAmazoneS3(),FrameworkContstants.getSecreatKeyAmazoneS3(),FrameworkContstants.getBucketNameAmazoneS3(),FrameworkContstants.getFolderNameAmazoneS3())
			.isFilesUploadedSuccessfully("6", "4", "2");
	   
			
	}
	
	@Test(priority=2,dataProvider="project",enabled=true,groups = {"SIT","2824"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.DataImportFromCloudUpload_2824},portal= {PortalType.EP})

	public void verifyProjectAtCPside(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify project operation at cp side" , method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		
		 loginPagecp
	         .navigateToCP("urlCP")
			 .loginToCP(PropertiesOperationsShaip.getPropertyValueByKey("annotator"),PropertiesOperationsShaip.getPropertyValueByKey("a_password"))
	         .verifyTermsAndConditionPopUp()
	         .clickOnAgreeButton()
	         .verifyShaipURI()
	         .clickOnProjects()
		     //.searchCustomer((String) context.getAttribute("ProjectName"));
	         .searchProject("Automation(Audio Transcription)31122021133643")
	         .clickOnTasksList()
	         .clickOnTaskButton();
		 new taskExecutionScreen_CP()
	         .swithToTaskExecutionScreen()
	         .isTaskExecutionScreenOpened((String) context.getAttribute("DisplayName"));


			
	   
			
	}


	
	
	
	
	
	@DataProvider(name = "project")
	public Object[][] getTestData() throws IOException {

		return getData("EP","project");
	}
	
	

}

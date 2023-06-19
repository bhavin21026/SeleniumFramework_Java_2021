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
import com.shaip.page.ep.BatchesPage_EP;
import com.shaip.page.ep.ConfigurationField_EP;
import com.shaip.page.ep.CustomFieldProjectsPage_EP;
import com.shaip.page.ep.CustomerUsersPage_EP;
import com.shaip.page.ep.ProjectsPage_EP;
import com.shaip.page.ep.TeamMemberPage_EP;
import com.shaip.page.ep.WorkFlowProjectPage_EP;

public final class VerifyBatchesCreationFunctionality extends BaseTest {

	private VerifyBatchesCreationFunctionality() {}
	
	
	@Test(priority=1,dataProvider="PermissionMatrix",enabled=true,groups = {"Smoke","2422"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.ManageBatches_EP_2422},portal= {PortalType.EP})

	public void verifyAssignTeamMemberButtonAccess(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("Verify create batches button access to permitted user("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.searchProject("Collection");
	   new CustomFieldProjectsPage_EP()
		    .clickOnMoreOptions();
	   new BatchesPage_EP()
	   		.clickOnBatches()
	   		.verifyCreateBatchPermissionAccess(testData.get("UserRole"),testData.get("AddBatch"));
	   
	}
	

	
	@Test(priority=2,dataProvider="batchCreation",enabled=true,groups = {"Smoke","2422"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.ManageBatches_EP_2422},portal= {PortalType.EP})

	public void verifyViewDynamicTeamAssignmentTabs_AT(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: As a User, I should be able to create batches for Audio Transcription project ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
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
	   new BatchesPage_EP()
  			.clickOnBatches()
  			.addNewBatch(context,ProjectType.AudioTranscription,ProjectConfigsAT.setBatchName(),ProjectConfigsAT.setBatchTotal())
  			.verifiedCreatedBatch(context.getAttribute("batch").toString());
  			
	   
 
	       

		
	}
	
	@Test(priority=3,dataProvider="batchCreation",enabled=true,groups = {"Smoke","2422"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.ManageBatches_EP_2422},portal= {PortalType.EP})

	public void verifyViewDynamicTeamAssignmentTabs_AC(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: As a User, I should be able to create batches for Audio Collection project ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
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
	   new BatchesPage_EP()
  			.clickOnBatches()
  			.addNewBatch(context,ProjectType.AudioCollection,ProjectConfigsAT.setBatchName(),ProjectConfigsAT.setBatchTotal())
  			.verifiedCreatedBatch(context.getAttribute("batch").toString());
  		
	   
 
	       

		
	}

	

	@Test(priority=4,dataProvider="batchCreation",enabled=false,groups = {"Smoke","2824"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.DataImportFromCloudUpload_2824},portal= {PortalType.EP})

	public void verifyImportDataFromCloudUpload(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: As a User, I should be able to import data reference for Audio Transcription project from cloud storage ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
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
	   new BatchesPage_EP()
			.clickOnBatches()
			.addNewBatch(context,ProjectType.AudioTranscription,ProjectConfigsAT.setBatchName(),ProjectConfigsAT.setBatchTotal())
			.verifiedCreatedBatch(context.getAttribute("batch").toString())
			.clickOnCloudUpload()
			.setupAmazonS3ForSync(FrameworkContstants.getAccessKeyAmazoneS3(),FrameworkContstants.getSecreatKeyAmazoneS3(),FrameworkContstants.getBucketNameAmazoneS3(),FrameworkContstants.getFolderNameAmazoneS3());
			
	   
 
	       

		
	}

	@Test(priority=5,dataProvider="batchCreation",enabled=false,groups = {"Smoke","157"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.DataImportFromLocalStorage_EP_157},portal= {PortalType.EP})

	public void verifyImportDataFromLocalStorageUpload(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: As a User, I should be able to import data reference for Audio Transcription project from local storage ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.clickOnAddProjectButton()
			.createNewProject(ProjectType.AudioTranscription)
			.addBasicInformationOfProject(ProjectType.AudioTranscription,context,ProjectConfigsAT.setProjectName(),ProjectConfigsAT.setProjectDisplayName())
			.searchCustomer((String) context.getAttribute("ProjectName"));
		new CustomFieldProjectsPage_EP()
		    .clickOnMoreOptions();
	    new ConfigurationField_EP()
	   		.clickOnSettings()
	   		.setupConfigFieldsForAT(ProjectConfigsAT.setRate(),ProjectConfigsAT.setDepth(),ProjectConfigsAT.setMin_AT(),ProjectConfigsAT.setMax_AT(),"multiple")
	   		.clickOnHeaderBack();
	    new ProjectsPage_EP()
			.searchCustomer((String) context.getAttribute("ProjectName"));
	    new CustomFieldProjectsPage_EP()
	    	.clickOnMoreOptions();
	    new BatchesPage_EP()
			.clickOnBatches()
			.addNewBatch(context,ProjectType.AudioTranscription,ProjectConfigsAT.setBatchName(),ProjectConfigsAT.setBatchTotal())
			.searchBatch("AutoBatch_AT")
			.verifiedCreatedBatch(context.getAttribute("batch").toString())
			.clickOnLoaclUpload()
			.uploadAudioFilesFromLocal()
  			.isLocalFilesUploadedSuccessfully("2", "2", "0");

		
	}

	
	
	
	
	@DataProvider(name = "PermissionMatrix")
	public Object[][] getTestData() throws IOException {

		return getData("EP","PermissionMatrix");
	}
	
	@DataProvider(name = "batchCreation")
	public Object[][] getTestData2() throws IOException {

		return getData("EP","batchCreation");
	}


}

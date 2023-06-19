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

public final class VerifyImportFromCloudStorageFunctionality extends BaseTest {

	private VerifyImportFromCloudStorageFunctionality() {}
	
	
	
	@Test(priority=1,dataProvider="audioUpload",enabled=true,groups = {"SMOKE","2824"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.DataImportFromCloudUpload_2824},portal= {PortalType.EP})

	public void verifyImportDataFromCloudUploadSingleRateDepthSeletion(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Project having configuration like ("+testData.get("Rate")+"-"+testData.get("Depth")+")should only allow to upload audio file of ("+testData.get("Rate")+"-"+testData.get("Depth")+")" , method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.clickOnAddProjectButton()
			.createNewProject(ProjectType.AudioTranscription)
			.addBasicInformationOfProject(ProjectType.AudioTranscription,context,testData.get("ProjectName"),testData.get("ProjectDisplayName"))
			.searchCustomer((String) context.getAttribute("ProjectName"));
		new CustomFieldProjectsPage_EP()
		    .clickOnMoreOptions();
	    new ConfigurationField_EP()
	   		.clickOnSettings()
	   		.setupConfigFieldsForAT(testData.get("Rate"),testData.get("Depth"),testData.get("Min"),testData.get("Max"),"single")
	   		.clickOnHeaderBack();
	    new ProjectsPage_EP()
   			.searchCustomer((String) context.getAttribute("ProjectName"));
	    new CustomFieldProjectsPage_EP()
	    	.clickOnMoreOptions();
	    new BatchesPage_EP()
			.clickOnBatches()
			.addNewBatch(context,ProjectType.AudioTranscription,testData.get("BatchName"),testData.get("BatchTotal"))
			.verifiedCreatedBatch(context.getAttribute("batch").toString())
			.clickOnCloudUpload()
			.setupAmazonS3ForSync(testData.get("AccessKey"),testData.get("Skey"),testData.get("BucketName"),testData.get("FolderName"))
			.isFilesUploadedSuccessfully("1", "1", "0")
			.verifyWithUplaodingInvalidFiles(context, testData.get("UserRole"), method.getName(),testData);
			
	   
	}

	
	@Test(priority=2,dataProvider="batchCreation",enabled=true,groups = {"SMOKE","2824"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.DataImportFromCloudUpload_2824},portal= {PortalType.EP})

	public void verifyImportDataFromCloudUploadMultipleRateDepthSeletion(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify import from cloud functionality when all sampling depth and Sample depth are selected while doing configuration fields setups" , method.getName(),testData.get("UserRole"));
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
			.verifiedCreatedBatch(context.getAttribute("batch").toString())
			.clickOnCloudUpload()
			.setupAmazonS3ForSync(FrameworkContstants.getAccessKeyAmazoneS3(),FrameworkContstants.getSecreatKeyAmazoneS3(),FrameworkContstants.getBucketNameAmazoneS3(),FrameworkContstants.getFolderNameAmazoneS3())
	    	.isFilesUploadedSuccessfully("22", "21", "1");
	}

	
	
	
	
	
	@DataProvider(name = "batchCreation")
	public Object[][] getTestData() throws IOException {

		return getData("EP","batchCreation");
	}
	
	@DataProvider(name = "audioUpload")
	public Object[][] getTestData2() throws IOException {

		return getData("EP","audioUpload");
	}
	
	

}

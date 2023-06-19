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
import com.shaip.page.ep.CustomerUsersPage_EP;

public final class VerifyProjectCreationFunctionality extends BaseTest {

	private VerifyProjectCreationFunctionality() {}
	
	

	
	@Test(priority=1,dataProvider="PermissionMatrix",enabled=true,groups = {"Smoke","149"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Project_Types_EP_149},portal= {PortalType.EP})

	public void verifyProjectCreationAccess(Object obj1,Method method) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify create project button access to permitted user ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.verifyAddProjectButtonPermissionAccess(testData.get("UserRole"),testData.get("CreateProject"));
			
		
	}
	
	
	@Test(priority=2,dataProvider="createProject",enabled=true,groups = {"Smoke","149"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Project_Types_EP_149},portal= {PortalType.EP})

	public void verifyProjectTypeCategories(Object obj1,Method method) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that As a User, I should be able to see the various project types available to be created ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.clickOnAddProjectButton()
			.verifyProjectTypeCategories()
			.verifyProjectTypessBasedOnSelectedCategories();
			
		
	}
	
	@Test(priority=3,dataProvider="createProject",enabled=true,groups = {"Smoke","147"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Project_Creation_EP_147},portal= {PortalType.EP})

	public void verifyCreateProjectFunctionality_AC(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that As a User, I should be able to create Audio collection project and can search same("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
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

		
			
			
		
	}
	
	
	
	
	@Test(priority=4,dataProvider="createProject",enabled=true,groups = {"SIT","Smoke","148"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Edit_Project_EP_148},portal= {PortalType.EP})

	public void verifyEditProjectFunctionality_AC(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that As a User, I should be able to edit Audio collection project and can search same("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.clickOnAddProjectButton()
			.createNewProject(ProjectType.AudioCollection)
			.addBasicInformationOfProject(ProjectType.AudioCollection,context,"QA","Test")
			.searchCustomer((String) context.getAttribute("ProjectName"))
			.clickOnEditProject()
			.editAndVerifyProjectDetails(ProjectType.AudioCollection,context)	
			.searchCustomer((String) context.getAttribute("ProjectUpdatedName"));

		
			
			
		
	}
	
	
	
	
	@Test(priority=5,dataProvider="createProject",enabled=true,groups = {"Smoke","147"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Project_Creation_EP_147},portal= {PortalType.EP})

	public void verifyCreateProjectFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that As a User, I should be able to create Audio Transcription project and can search same ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
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

		
			
			
		
	}
	
	
	
	
	@Test(priority=6,dataProvider="createProject",enabled=true,groups = {"SIT","Smoke","148"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Edit_Project_EP_148},portal= {PortalType.EP})

	public void verifyEditProjectFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that As a User,  I should be able to edit Audio Transcription project and can search same  ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.clickOnAddProjectButton()
			.createNewProject(ProjectType.AudioTranscription)
			.addBasicInformationOfProject(ProjectType.AudioTranscription,context,"QA","Test")
			.searchCustomer((String) context.getAttribute("ProjectName"))
			.clickOnEditProject()
			.editAndVerifyProjectDetails(ProjectType.AudioTranscription,context)
			.searchCustomer((String) context.getAttribute("ProjectUpdatedName"));

		
			
			
		
	}
	
	@Test(priority=7,dataProvider="createProject",enabled=true,groups = {"Smoke","147"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Project_Creation_EP_147},portal= {PortalType.EP})

	public void verifyUploadGuidelinesValidFormatFunctionality_AC(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that As a User, I should be able to uplod  guidelines with valid file format in project("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.clickOnAddProjectButton()
			.createNewProject(ProjectType.AudioCollection)
			.addBasicInformationAndGuidelines(ProjectType.AudioCollection,context,"QA","Test")
			.searchCustomer((String) context.getAttribute("ProjectName"));

		
			
			
		
	}
	
	@Test(priority=8,dataProvider="createProject",enabled=true,groups = {"Smoke","147"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Project_Creation_EP_147},portal= {PortalType.EP})

	public void verifyUploadGuidelinesInvalidFormatFunctionality_AC(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify Upload guidelines functionality when user tries to add file with size of more than 2 MB ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.clickOnAddProjectButton()
			.createNewProject(ProjectType.AudioCollection)
			.uploadGuidelineWithInvalidFomrat()
			.verifyGuidelineErrorToaster();

		
			
			
		
	}
	
	@Test(priority=9,dataProvider="createProject",enabled=true,groups = {"Smoke","2546"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.DuplicateGuidelines_EP_2546},portal= {PortalType.EP})

	public void verifyUploadDuplicateGuidelines(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: User should not be allowed to add duplicate files in guidlines("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.clickOnAddProjectButton()
			.createNewProject(ProjectType.AudioCollection)
			.uploadDuplicateFiles()
			.verifyDuplicateFileErrorToaster();

		
			
			
		
	}
	
	@Test(priority=10,dataProvider="createProject",enabled=true,groups = {"Smoke","147"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Project_Creation_EP_147},portal= {PortalType.EP})

	public void verifyMaxLimitOfUploadGuidelines(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that choose file button should become disable after 5 files uploaded("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.clickOnAddProjectButton()
			.createNewProject(ProjectType.AudioCollection)
			.uploadGuidelines()
			.verifyChooseFileButtonState();

		
			
			
		
	}
	
	@Test(priority=11,dataProvider="createProject",enabled=true,groups = {"Smoke","147"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Project_Creation_EP_147},portal= {PortalType.EP})

	public void verifyEditGuidelinesFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: As a User I should be able to Edit uploaded guidelines("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.clickOnAddProjectButton()
			.createNewProject(ProjectType.AudioCollection)
			.EdituploadedGuidelines();

		
			
			
		
	}
	
	
	
	@Test(priority=12,dataProvider="createProject",enabled=true,groups = {"Smoke","147"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Project_Creation_EP_147},portal= {PortalType.EP})

	public void verifyDeletedGuidelinesFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: As a User I should be able to Delete uploaded guidelines("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.clickOnAddProjectButton()
			.createNewProject(ProjectType.AudioCollection)
			.uploadGuidelines()
			.deleteGuidelines();

		
			
			
		
	}
	
	//Project status
	
	
	
	
	@Test(priority=13,dataProvider="createProject",enabled=true,groups = {"Smoke","145"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.ProjectStatusChange_EP_145},portal= {PortalType.EP})

	public void verifyProjectChangeStatusFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: As a User, I should be able to access Project Status change button on project grid ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnProjects()
			.clickOnAddProjectButton()
			.createNewProject(ProjectType.AudioCollection)
			.addBasicInformationOfProject(ProjectType.AudioCollection,context,"QA","Test")
			.searchCustomer((String) context.getAttribute("ProjectName"))
			.changeProjectStatus(testData.get("UserRole"),testData.get("UserRole"))
			.completeProject(testData.get("UserRole"),testData.get("UserRole"))
			.makeProjectArchive((String) context.getAttribute("ProjectName"),testData.get("UserRole"),testData.get("UserRole"))
			.makeProjectUnArchive((String) context.getAttribute("ProjectName"),testData.get("UserRole"),testData.get("UserRole"));

		
		
	}
	
	
	
	@DataProvider(name = "PermissionMatrix")
	public Object[][] getTestData() throws IOException {

		return getData("EP","PermissionMatrix");
	}
	
	@DataProvider(name = "createProject")
	public Object[][] getTestData2() throws IOException {

		return getData("EP","createProject");
	}


}

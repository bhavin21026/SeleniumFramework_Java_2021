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
import com.shaip.enums.story;
import com.shaip.page.ep.CustomerUsersPage_EP;
import com.shaip.page.ep.VendorUsersPage_EP;

public class VerifyVendorUserFunctionality extends BaseTest {

	
	

	@Test(priority=1,dataProvider="createVendor",enabled=true,groups = { "Smoke","417"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Create_Vendor_User_EP_417},portal= {PortalType.EP})

	public void verifyAddNewVendorUserFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that valid user should be able create new 'Vendor Contributor/Administrator' for their Vendor organization ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnAdministration()
			.clickOnVendors()
			.createNewVendor(testData.get("UserRole"),testData.get("Create_Vendor"),context)
			.waitForSuccessToasterMessage()
			.searchVendor((String) context.getAttribute("vendorName"));
		new VendorUsersPage_EP()
			.clickOnUsersTab()
			.clickAddUserMenu()
			.addNewVendorUserForSelectedCustomerOrganization(context, testData.get("UserRole"),testData.get("Create_Vendor"))
			.waitForVendorUserCreatedSuccessToasterMessage()
			.verifyInvitationEmailAndSignIn(context,testData.get("UserRole"),method.getName(),testData);
		
		
			
		
	}
	
	@Test(priority=2,dataProvider="createVendor",enabled=true,groups = {"Smoke","417"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Create_Vendor_User_EP_417},portal= {PortalType.EP})
	public void verifySearchVendorUserFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that user should able to search existing 'Vendor Contributor/Administrator' using Global Search ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
		.navigateToEP("urlEP")
		.loginToEP(testData.get("UserName"), testData.get("Password"))
		.verifyShaipURI()
		.clickOnAdministration()
		.clickOnVendors()
		.createNewVendor(testData.get("UserRole"),testData.get("Create_Vendor"),context)
		.waitForSuccessToasterMessage()
		.searchVendor((String) context.getAttribute("vendorName"));
		new VendorUsersPage_EP()
		.clickOnUsersTab()
		.clickAddUserMenu()
		.addNewVendorUserForSelectedCustomerOrganization(context, testData.get("UserRole"),testData.get("Create_Vendor"))
		.waitForVendorUserCreatedSuccessToasterMessage()
	    .searchVendorUser((String) context.getAttribute("vendorUserName"));

	
		

	
		
	}
	
	@Test(priority=3,dataProvider="createVendor",enabled=true,groups = {"Smoke","426"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.EditVendorUser_EP_426},portal= {PortalType.EP})
	public void verifyEditVendorUserFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that valid user should able to edit 'Vendor Contributor/Administrator' details from Vendor user listing page("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
		.navigateToEP("urlEP")
		.loginToEP(testData.get("UserName"), testData.get("Password"))
		.verifyShaipURI()
		.clickOnAdministration()
		.clickOnVendors()
		.createNewVendor(testData.get("UserRole"),testData.get("Create_Vendor"),context)
		.waitForSuccessToasterMessage()
		.searchVendor((String) context.getAttribute("vendorName"));
		new VendorUsersPage_EP()
		.clickOnUsersTab()
		.clickAddUserMenu()
		.addNewVendorUserForSelectedCustomerOrganization(context, testData.get("UserRole"),testData.get("Create_Customer"))
		.waitForVendorUserCreatedSuccessToasterMessage()
	    .editAndVerifyVendorUserFromListingPage(context, testData.get("UserRole"),testData.get("Create_Vendor"));

	}
	
	
	@Test(priority=4,dataProvider="createVendor",enabled=true,groups = { "Smoke","447"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Activate_Deactivate_Vendor_User_EP_447},portal= {PortalType.EP})

	public void verifyDeactivateVendorUserFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: As a User, I should be able to activate/deactivate a Vendor Contributor/Administrator ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnAdministration()
			.clickOnVendors()
			.createNewVendor(testData.get("UserRole"),testData.get("Create_Vendor"),context)
			.waitForSuccessToasterMessage()
			.searchVendor((String) context.getAttribute("vendorName"));
		new VendorUsersPage_EP()
			.clickOnUsersTab()
			.clickAddUserMenu()
			.addNewVendorUserForSelectedCustomerOrganization(context, testData.get("UserRole"),testData.get("Create_Vendor"))
			.waitForVendorUserCreatedSuccessToasterMessage()
			.deactivateUser()
			.activateUser();
		
		
			
		
	}
		
	
	
	
	
	
	@DataProvider(name = "PermissionMatrix")
	public Object[][] getTestData() throws IOException {

		return getData("EP","PermissionMatrix");
	}
	
	@DataProvider(name = "createVendor")
	public Object[][] getTestData2() throws IOException {

		return getData("EP","createVendor");
	}


}

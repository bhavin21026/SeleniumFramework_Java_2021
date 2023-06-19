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
import com.shaip.page.ep.VendorUsersPage_EP;

public class VerifyVendorModuleFunctionality extends BaseTest {

	
	

	
	@Test(priority=1,dataProvider="PermissionMatrix",enabled=true,groups = { "Smoke","134"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Create_Vendor_EP_134},portal= {PortalType.EP})

	public void verifyCreateVendorAccessPermission(Object obj1,Method method) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that only permitted user can create vendor using 'Add Vendor' button ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnAdministration()
			.clickOnVendors()
			.verifyAddVendorButtonPermissionAccess(testData.get("UserRole"),testData.get("Create_Vendor"));
			
		
	}
	
	@Test(priority=2,dataProvider="createVendor",enabled=true,groups = { "Smoke","134"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Create_Vendor_EP_134},portal= {PortalType.EP})

	public void verifyCreateVendorFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that valid user should able to create new 'Vendor'("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnAdministration()
			.clickOnVendors()
			.createNewVendor(testData.get("UserRole"),testData.get("Create_Vendor"),context)
			.waitForSuccessToasterMessage();
			
		
	}
	
	@Test(priority=3,dataProvider="createVendor",enabled=true,groups = { "Smoke","134"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Create_Vendor_EP_134},portal= {PortalType.EP})

	public void verifySearchCustomerFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that user should able to search  'Vendor' using Global Search ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
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
			
		
	}
	
	@Test(priority=4,dataProvider="createVendor",enabled=true,groups = { "Smoke","132"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Edit_Vendor_EP_132},portal= {PortalType.EP})

	public void editAndVerifyCustomerDetailsFromListingPage(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that valid user should able to edit 'Vendor' details from Vendor listing page ("+testData.get("UserRole")+")",method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnAdministration()
			.clickOnVendors()
			.createNewVendor(testData.get("UserRole"),testData.get("Create_Vendor"),context)
			.waitForSuccessToasterMessage()
			.editAndVerifyVendorFromListing(context,testData.get("UserRole"),testData.get("Create_Vendor"));
			
		
	}
	
	@Test(priority=5,dataProvider="createVendor",enabled=true,groups = { "Smoke","132"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Create_Vendor_EP_134},portal= {PortalType.EP})

	public void VerifyEditCustomerFunctionalityFromDetailPage(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that valid user should able to edit 'Vendor' details from Vendor details page ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnAdministration()
			.clickOnVendors()
			.createNewVendor(testData.get("UserRole"),testData.get("Create_Vendor"),context)
			.waitForSuccessToasterMessage()
			.editAndVerifyVendorFromDetailsPage(context, testData.get("UserRole"),testData.get("Create_Vendor"));
			
		
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

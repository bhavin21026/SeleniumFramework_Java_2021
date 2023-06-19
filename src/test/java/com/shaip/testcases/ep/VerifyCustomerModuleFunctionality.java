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
import com.shaip.utils.EmailSearcher;

public class VerifyCustomerModuleFunctionality extends BaseTest {

	
	
	

	
	@Test(priority=1,dataProvider="PermissionMatrix",enabled=true,groups = {"Smoke","133"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Create_Customer_EP_133},portal= {PortalType.EP})

	public void verifyCreateCustomerAccessPermission(Object obj1,Method method) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that only permitted user can create customer using 'Add Customer' button ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnAdministration()
			.clickOnCustomers()
			.verifyAddCustomerButtonPermissionAccess(testData.get("UserRole"),testData.get("Create_Customer"));
			
		
	}
	
	@Test(priority=2,dataProvider="createCustomer",enabled=true,groups = {"Smoke","133"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Create_Customer_EP_133},portal= {PortalType.EP})
	public void verifyCreateCustomerFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that valid user should able to create new 'Customer'("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnAdministration()
			.clickOnCustomers()
			.createNewCustomer(testData.get("UserRole"),testData.get("Create_Customer"),context)
			.waitForCreateCustomerSuccessToasterMessage();
		sa.assertAll();
		
		
			
		
	}
	
	@Test(priority=3,dataProvider="createCustomer",enabled=true,groups = {"Smoke","133"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Create_Customer_EP_133},portal= {PortalType.EP})
	public void verifySearchCustomerFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that user should able to search  'Customer' using Global Search ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnAdministration()
			.clickOnCustomers()
			.createNewCustomer(testData.get("UserRole"),testData.get("Create_Customer"),context)
			.waitForCreateCustomerSuccessToasterMessage()
			.searchCustomer((String) context.getAttribute("customerName"));

			
		
	}
	
	@Test(priority=4,dataProvider="createCustomer",enabled=true,groups = {"Smoke","131"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Edit_Customer_EP_131},portal= {PortalType.EP})
	public void editAndVerifyCustomerDetailsFromListingPage(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that valid user should able to edit 'Customer' details from customer listing page ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnAdministration()
			.clickOnCustomers()
			.createNewCustomer(testData.get("UserRole"),testData.get("Create_Customer"),context)
			.waitForCreateCustomerSuccessToasterMessage()
			.editAndVerifyCustomerFromListing(context,testData.get("UserRole"),testData.get("Create_Customer"));
			
		
	}
	
	@Test(priority=5,dataProvider="createCustomer",enabled=true,groups = {"Smoke","131"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Edit_Customer_EP_131},portal= {PortalType.EP})
	public void VerifyEditCustomerFunctionalityFromDetailPage(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that valid user should able to edit 'Customer' details from customer details page ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnAdministration()
			.clickOnCustomers()
			.createNewCustomer(testData.get("UserRole"),testData.get("Create_Customer"),context)
			.waitForCreateCustomerSuccessToasterMessage()
			.editAndVerifyCustomerFromDetailsPage(context, testData.get("UserRole"),testData.get("Create_Customer"));
			
		
	}
	
	
	@Test(priority=6,dataProvider="createCustomer",enabled=true,groups = {"Smoke","420"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Create_Customer_User_EP_420},portal= {PortalType.EP})
	public void verifyAddNewCustomerUserFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

	
		CustomerUsersPage_EP cuser=new CustomerUsersPage_EP();
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		giveTestRoleInfo(testData,"UserRole");
		
		loginPage
			.navigateToEP("urlEP")
			.loginToEP(testData.get("UserName"), testData.get("Password"))
			.verifyShaipURI()
			.clickOnAdministration()
			.clickOnCustomers()
			.createNewCustomer(testData.get("UserRole"),testData.get("Create_Customer"),context)
			.waitForCreateCustomerSuccessToasterMessage()
			.searchCustomer((String) context.getAttribute("customerName"));
		
		
		cuser
			.clickOnUsersTab()
			.clickAddUserMenu()
			.addNewCustomerUserForSelectedCustomerOrganization(context)
			.waitForCustomerUserCreatedSuccessToasterMessage();
		
		
			
		
	}
	
	
	
	
	
	@DataProvider(name = "PermissionMatrix")
	public Object[][] getTestData() throws IOException {

		return getData("EP","PermissionMatrix");
	}
	
	@DataProvider(name = "createCustomer")
	public Object[][] getTestData2() throws IOException {

		return getData("EP","createCustomer");
	}


}

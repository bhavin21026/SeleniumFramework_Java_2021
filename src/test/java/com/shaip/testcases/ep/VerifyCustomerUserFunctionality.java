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
import com.shaip.page.cp.EmailVerification_CP;
import com.shaip.page.ep.CustomerUsersPage_EP;
import com.shaip.utils.EmailSearcher;

public class VerifyCustomerUserFunctionality extends BaseTest {

	
	@Test(priority=1,dataProvider="createCustomer",enabled=true,groups = {"Smoke","420"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Create_Customer_User_EP_420},portal= {PortalType.EP})
	public void verifyAddNewCustomerUserFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that valid user should be able create new 'Customer Contributor/Administrator' for their customer organization ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
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
	    new CustomerUsersPage_EP()
			.clickOnUsersTab()
			.clickAddUserMenu()
			.addNewCustomerUserForSelectedCustomerOrganization(context)
			.waitForCustomerUserCreatedSuccessToasterMessage()
		    .verifyInvitationEmailAndSignIn(context,testData.get("UserRole"),method.getName(),testData);
		    
		
			
		
	}
	

	
	@Test(priority=2,dataProvider="createCustomer",enabled=true,groups = {"Smoke","420"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Create_Customer_User_EP_420},portal= {PortalType.EP})
	public void verifySearchCustomerUserFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

	
	
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that user should able to search existing 'Customer Contributor/Administrator' using Global Search ("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
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
        new CustomerUsersPage_EP()
		.clickOnUsersTab()
		.clickAddUserMenu()
		.addNewCustomerUserForSelectedCustomerOrganization(context)
		.waitForCustomerUserCreatedSuccessToasterMessage()
	    .searchCustomerUser((String) context.getAttribute("customerUserName"));

	
		
	}
	
	@Test(priority=3,dataProvider="createCustomer",enabled=true,groups = {"Smoke","423"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.EditCustomerUser_EP_423},portal= {PortalType.EP})
	public void verifyEditCustomerUserFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: Verify that valid user should able to edit 'Customer Contributor/Administrator' details from customer user listing page("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
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
        new CustomerUsersPage_EP()
		.clickOnUsersTab()
		.clickAddUserMenu()
		.addNewCustomerUserForSelectedCustomerOrganization(context)
		.waitForCustomerUserCreatedSuccessToasterMessage()
	    .editAndVerifyCustomerUserFromListingPage(context, testData.get("UserRole"),testData.get("Create_Customer"));

	
		
	}
	
	@Test(priority=4,dataProvider="createCustomer",enabled=true,groups = {"Smoke","450"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.Activate_Deactivate_Customer_User_EP_450},portal= {PortalType.EP})
	public void verifyDeactivateCustomerUserFunctionality(Object obj1,Method method,ITestContext context) throws Throwable {

		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		setupExtentReport("EP: As a User, I should be able to activate/deactivate a Customer Contributor/Administrator("+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
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
        new CustomerUsersPage_EP()
		.clickOnUsersTab()
		.clickAddUserMenu()
		.addNewCustomerUserForSelectedCustomerOrganization(context)
		.waitForCustomerUserCreatedSuccessToasterMessage()
		.searchCustomerUser((String) context.getAttribute("customerUserName"))
        .deactivateUser()
        .activateUser();

	   

	
		
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

package com.shaip.page.ep;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestContext;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.shaip.base.ActionEngineShaip;
import com.shaip.factories.DriverBase;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.reportng.ExtentFactoryShaip;

public class CustomersPage_EP extends ActionEngineShaip {

	public CustomersPage_EP() {

		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	
	
	@FindBy(id  = "add")
	WebElement btn_addCustomer;
	
	@FindBy(xpath  = "//div[@class='mat-dialog-title']")
	WebElement hdr_addCustomer;


	// Create customer

	@FindBy(name = "name")
	WebElement txt_customerName;

	@FindBy(name = "description")
	WebElement txt_customerDescription;

	@FindBy(name = "country")
	WebElement drp_customerCountry;

	@FindBy(name = "state")
	WebElement drp_customerState;

	@FindBy(xpath = "//div[@role='listbox' or @id='mat-autocomplete-22']")
	WebElement list_countryListing;

	@FindBy(id = "saveButton")
	WebElement btn_saveCustomer;

	// Create listing

	@FindBy(id = "dtGlobalSearch")
	WebElement txt_globalSearch;

	@FindBy(css = "table > tbody > tr > td:nth-child(2)")
	WebElement searchedEntity;

	@FindBy(tagName = "h2")
	WebElement customerHeader;

	@FindBy(id = "editIcon")
	WebElement btn_editCustomerfrmList;

	@FindBy(id = "editButton")
	WebElement btn_EditCustomerfrmDetail;

	@FindBy(xpath = "(//span[@class='detail'])[2]")
	WebElement customerDes;

	@FindBy(xpath = "(//span[@class='detail'])[3]")
	WebElement customerCou;

	@FindBy(xpath = "(//span[@class='detail'])[4]")
	WebElement customerST;
	
	@FindBy(xpath = "//div[@class='content']//p")
	WebElement toaster_Message;

	@FindBy(xpath = "//mat-icon[text()='clear']")
	WebElement btn_closeToaster;


	

	

	
	
	public CustomersPage_EP verifyAddCustomerButtonPermissionAccess(String user,String createPermission) throws InterruptedException

	{
		
			
			if(createPermission.equalsIgnoreCase("Yes"))
			{
			clickOnAddCustomerButton();
			}
			else
			{
			verifyAddCustomerButtonIsDisabled(user);	
			}
		
			sa.assertAll();
			return this;

		
		}
		
		
	
	
	
	public void verifyAddCustomerButtonIsDisabled(String user)

	{
		boolean isDisabled = false;
		try {
		String classes = btn_addCustomer.getAttribute("disabled");
		isDisabled = classes.equalsIgnoreCase("true");
		
		if(isDisabled)
		{
			sa.assertTrue(isDisabled,"Add customer button should not be visible to this user----> "+user);
			ExtentFactoryShaip.getTest().log(Status.PASS,"Add customer button should  be visible to this user ---->  " +user);
			sa.assertAll();
			
		}
		else
		{
			sa.assertTrue(isDisabled,"Add customer button should not be visible to this user----> "+user);
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),"Test case failure screenshot");		
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper
					.createLabel("Add customer button should not be visible to this user ---->  " +user, ExtentColor.RED));
			sa.assertAll();
			
			
		}
		}
		catch (Exception e) {
			sa.assertTrue(isDisabled,"Add customer button should not be visible to this user----> "+user);
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),"Test case failure screenshot");		
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper
					.createLabel("Add customer button should not be visible to this user ---->  " +user, ExtentColor.RED));
			sa.assertAll();
		}
	
	}
	
	
	

	

	
	public CustomersPage_EP createNewCustomer(String user,String permission,ITestContext context)
			throws InterruptedException

	{
		
		
		String name=generateNewCustomerName();
	    clickOnAddCustomerButton();
		enterCustomerName(name);
		enterCustomerDescription("This customer is created by automation script, Please do nor delete");
		selectCountry("India");
		selectState("Gujarat");
		waitUntilClickable(btn_saveCustomer);
		clickOnSave();
		context.setAttribute("customerName",name);
		return this;
		
	}
	
	

	public CustomersPage_EP editAndVerifyCustomerFromListing(ITestContext context,String user,String permission) throws InterruptedException

	{
		
		waitForProcessBarToGo();
		String searchEntity=(String) context.getAttribute("customerName");
		
		String updatedName="Automation QA_"+getDate();
		String updatedDes="This customer is created by automation script, do nor delete";
		String updatedCou="Australia";
		String updatedSta="Victoria";
		
		waitUntilClickable(txt_globalSearch);
		click_custom(txt_globalSearch, searchEntity);
		sendKeys_custom(txt_globalSearch, "Global Search", searchEntity);
		Thread.sleep(2000);
		click_custom(btn_editCustomerfrmList, "Edit customer icon");
		//waitForProcessBarToGo();
		updateExistingCustomer(updatedName, updatedDes, updatedCou,updatedSta);
		Thread.sleep(2000);
		verifyUpdatedCustomerInformation(updatedName,updatedName, updatedName, updatedCou,updatedSta);
		
		context.setAttribute("customerNameList_"+user,updatedName);
		
		
		return this;

	}

	public void editAndVerifyCustomerFromDetailsPage(ITestContext context,String user,String permission) throws InterruptedException

	{

		String updatedNameD="Automation Customer_"+getDate();
		String updatedDesD="This is created by automation script, Please do nor delete";
		String updatedCouD="India";
		String updatedStaD="Gujarat";
		String searchEntity=(String) context.getAttribute("customerName");
		
		
		waitForProcessBarToGo();
		waitUntilClickable(txt_globalSearch);
		click_custom(txt_globalSearch, searchEntity);
		sendKeys_custom(txt_globalSearch, "Global Search", searchEntity);
		Thread.sleep(2000);
		waitUntilClickable(searchedEntity);
		click_custom(searchedEntity, "Searched Result");
		//waitForProcessBarToGo();
		waitUntilClickable(btn_EditCustomerfrmDetail);
		click_custom(btn_EditCustomerfrmDetail, "Edit customer Button");
		waitForProcessBarToGo();
		updateExistingCustomer(updatedNameD, updatedDesD, updatedCouD, updatedStaD);
		Thread.sleep(2000);
		verifyUpdatedCustomerInformationFromDetail(updatedNameD, updatedNameD, updatedDesD, updatedCouD,
				updatedStaD);
		
		context.setAttribute("customerNameDetail_"+user,updatedNameD);
		
		

	}

	public void verifyUpdatedCustomerInformationFromDetail(String searchEntity, String Name, String Description,
			String countryName, String stateName) throws InterruptedException

	{

		String customerNam = getText_custom(customerHeader, "Updated Name");
		String customerDesc = getText_custom(customerDes, "Updated Description");
		String drp_customerCountry = getText_custom(customerCou, "Updated Country");
		String drp_customerState = getText_custom(customerST, "Updated State");

		if (!customerNam.equalsIgnoreCase(Name)) {

			assert_custom(customerNam, Name, "Name not get updated");

		}
		if (!customerDesc.equalsIgnoreCase(Description)) {

			assert_custom(customerDesc, Description, "Description not get updated");
		}
		if (!drp_customerCountry.equalsIgnoreCase(countryName)) {

			assert_custom(drp_customerCountry, countryName, "Country not get updated");
		}
		if (!drp_customerState.equalsIgnoreCase(stateName)) {

			assert_custom(drp_customerState, stateName, "State not get updated");
		}

		sa.assertAll();

	}

	public void verifyUpdatedCustomerInformation(String searchEntity, String Name, String Description,
			String countryName, String stateName) throws InterruptedException

	{
		clearDrpdownText(txt_globalSearch);
		click_custom(txt_globalSearch, searchEntity);
		sendKeys_custom(txt_globalSearch, "Global Search", searchEntity);
		Thread.sleep(2000);
		click_custom(searchedEntity, "Searched Result");
		waitForProcessBarToGo();
		String customerNam = getText_custom(customerHeader, "Updated Name");
		String customerDesc = getText_custom(customerDes, "Updated Description");
		String drp_customerCountry = getText_custom(customerCou, "Updated Country");
		String drp_customerState = getText_custom(customerST, "Updated State");

		if (!customerNam.equalsIgnoreCase(Name) && !customerDesc.equalsIgnoreCase(Description)
				&& !drp_customerCountry.equalsIgnoreCase(countryName) && !drp_customerState.equalsIgnoreCase(stateName)) {

			assert_custom(customerNam, Name, "Name not get updated");
			assert_custom(customerDesc, Description, "Description not get updated");
			assert_custom(drp_customerCountry, countryName, "Country not get updated");
			assert_custom(drp_customerState, stateName, "State not get updated");

		}

		sa.assertAll();

	}

	public CustomersPage_EP searchCustomer(String searchEntity) throws InterruptedException

	{
		waitUntilClickable(txt_globalSearch);
		click_custom(txt_globalSearch, searchEntity);
		sendKeys_custom(txt_globalSearch, "Global Search", searchEntity);
		Thread.sleep(2000);
		click_custom(searchedEntity, "Searched Result");
		waitForProcessBarToGo();

		String searchedResult = getText_custom(customerHeader, "Searched Result");
		System.out.println("searched result" + searchedResult);

		if (!searchedResult.equalsIgnoreCase(searchEntity)) {

			//sa.fail("Customer search is failed, not found any search record, for searched customer---> "+searchEntity);
			ExtentFactoryShaip.getTest().log(Status.FAIL,"Customer search is failed, not found any search record, searched customer--> " +searchEntity);
			

		}
		Thread.sleep(2000);
		sa.assertAll();
		return this;
	}

	public CustomersPage_EP clickOnAddCustomerButton()

	{
		waitUntilClickable(btn_addCustomer);
		click_custom(btn_addCustomer, "Add Customer Button");
		waitForVisibility(hdr_addCustomer);
	    String customerTitle=getText_custom(hdr_addCustomer,"Add Customer Button Title");
		
		assert_contains(customerTitle, "Add Customer", "Add Customer Button");
		
		return this;

	}

	public void clickOnSave()

	{

		click_custom(btn_saveCustomer, "SaveButton");

	}

	public void enterCustomerName(String Name)

	{
		waitUntilClickable(txt_customerName);
		click_custom(txt_customerName, "CustomerDescription");
		sendKeys_custom(txt_customerName, "CustomerName", Name);
		//waitUntilClickable(txt_customerName);
	}

	public void enterCustomerDescription(String Description)

	{
		waitUntilClickable(txt_customerDescription);
		click_custom(txt_customerDescription, "CustomerDescription");
		sendKeys_custom(txt_customerDescription, "CustomerDescription", Description);

	}

	public void selectCountry(String countryName) throws InterruptedException

	{
		Thread.sleep(1000);
		click_custom(drp_customerCountry, "Country Dropdown");
		sendKeys_custom(drp_customerCountry, "Country Dropdown", countryName);
		Thread.sleep(1000);
		List<WebElement> countryToBeSelected = DriverFactoryShaip.getDriver()
				.findElements(By.xpath("//span[@class='mat-option-text']"));
		// System.out.println(countryToBeSelected.size());

		Thread.sleep(1000);

		for (int i = 0; i < countryToBeSelected.size(); i++) {

			String country = getText_custom(countryToBeSelected.get(i), "select country");

			if (country.equalsIgnoreCase(countryName))

			{
				click_custom(countryToBeSelected.get(i), countryName);
				waitForProcessBarToGo();
				break;
			}

		}

	}

	public void selectState(String customerStates) throws InterruptedException

	{
		Thread.sleep(1000);

		click_custom(drp_customerState, "State Dropdown");
		sendKeys_custom(drp_customerState, "State Dropdown", customerStates);
		Thread.sleep(1000);
		List<WebElement> stateToBeSelected = DriverFactoryShaip.getDriver()
				.findElements(By.xpath("//span[@class='mat-option-text']"));
		// System.out.println(countryToBeSelected.size());

		Thread.sleep(1000);

		for (int i = 0; i < stateToBeSelected.size(); i++) {

			String state =  getText_custom(stateToBeSelected.get(i), "select state");

			if (state.equalsIgnoreCase(customerStates))

			{
				click_custom(stateToBeSelected.get(i), customerStates);
				break;
			}
		}

	}

	

	public void updateExistingCustomer(String Name, String Description, String countryName, String stateName)
			throws InterruptedException

	{
		
		waitForVisibility(txt_customerName);
		clearText(txt_customerName);
		enterCustomerName(Name);
		clearText(txt_customerDescription);
		enterCustomerDescription(Description);
		clearDrpdownText(drp_customerCountry);
		selectCountry(countryName);
		clearDrpdownText(drp_customerState);
		selectState(stateName);
		waitUntilClickable(btn_saveCustomer);
		clickOnSave();
		waitForUpdateCustomerToasterMessage();

	}

	public CustomersPage_EP waitForCreateCustomerSuccessToasterMessage() throws InterruptedException {

		verifyToaster("The customer has been created successfully","Toaster message getting displayed");
		return this;
	}

	public CustomersPage_EP waitForUpdateCustomerToasterMessage() throws InterruptedException {

		
		
		verifyToaster("The customer has been updated successfully","Toaster message getting displayed");
		return this;


	}
	
}

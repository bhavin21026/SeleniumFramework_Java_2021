package com.shaip.page.ep;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.shaip.base.ActionEngineShaip;
import com.shaip.factories.DriverBase;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.reportng.ExtentFactoryShaip;

public class VendorsPage_EP extends ActionEngineShaip {

	public static String vendorName = null;
	public static String vendorNameDetail = null;

	public VendorsPage_EP() {

		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	@FindBy(id = "add")
	WebElement btn_addVendor;

	// Create Vendor

	@FindBy(name = "name")
	WebElement txt_vendorName;

	@FindBy(name = "description")
	WebElement txt_vendorDescription;

	@FindBy(name = "country")
	WebElement drp_vendorCountry;

	@FindBy(name = "state")
	WebElement drp_vendorState;

	@FindBy(xpath = "//div[@role='listbox' or @id='mat-autocomplete-22']")
	WebElement list_vendorListing;

	@FindBy(id = "saveButton")
	WebElement btn_saveVendor;

	// Create listing

	@FindBy(id = "dtGlobalSearch")
	WebElement txt_globalSearch;

	@FindBy(css = "table > tbody > tr > td:nth-child(2)")
	WebElement searchedEntity;

	@FindBy(tagName = "h2")
	WebElement hdr_vendorHeader;

	@FindBy(id = "editIcon")
	WebElement btn_editVendorfrmList;

	@FindBy(id = "editButton")
	WebElement btn_EditVendorfrmDetail;

	@FindBy(id = "saveButton")
	WebElement btn_updateVendor;

	@FindBy(xpath = "//div[@class='mat-dialog-title']")
	WebElement hdr_addVendor;

	@FindBy(xpath = "(//span[@class='detail'])[2]")
	WebElement vendorDes;

	@FindBy(xpath = "(//span[@class='detail'])[3]")
	WebElement vendorCou;

	@FindBy(xpath = "(//span[@class='detail'])[4]")
	WebElement vendorST;

	@FindBy(xpath = "//div[@class='content']//p")
	WebElement toaster_Message;

	@FindBy(xpath = "//mat-icon[text()='clear']")
	WebElement btn_closeToaster;

	public VendorsPage_EP verifyAddVendorButtonPermissionAccess(String user, String createPermission)
			throws InterruptedException

	{

		if (createPermission.equalsIgnoreCase("Yes")) {
			clickOnAddVendorButton();
		} else {
			verifyAddVendorButtonIsDisabled(user);
		}

		sa.assertAll();
		return this;

	}

	public void verifyAddVendorButtonIsDisabled(String user)

	{

		boolean isDisabled = false;
		try {
			String classes = btn_addVendor.getAttribute("disabled");
			isDisabled = classes.equalsIgnoreCase("true");

			if (isDisabled) {
				sa.assertTrue(isDisabled, "Add vendor button should  be visible to this user----> " + user);
				ExtentFactoryShaip.getTest().log(Status.PASS,
						"Add customer button should  be visible to this user ---->  " + user);
				sa.assertAll();

			} else {
				sa.assertTrue(isDisabled, "Add vendor button should not be visible to this user----> " + user);
				ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
						"Test case failure screenshot");
				ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper.createLabel(
						"Add vendor button should not be visible to this user ---->  " + user, ExtentColor.RED));
				sa.assertAll();

			}
		}

		catch (Exception e) {
			sa.assertTrue(isDisabled, "Add customer button should not be visible to this user----> " + user);
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper.createLabel(
					"Add customer button should not be visible to this user ---->  " + user, ExtentColor.RED));
			sa.assertAll();
		}

	}

	public void editAndVerifyVendorFromListing(String searchEntity, String UpdatedName, String UpdatedDescription,
			String UpdatedcountryName, String UpdatedstateName) throws InterruptedException

	{
		waitUntilClickable(txt_globalSearch);
		click_custom(txt_globalSearch, searchEntity);
		sendKeys_custom(txt_globalSearch, "Global Search", searchEntity);
		Thread.sleep(2000);
		click_custom(btn_editVendorfrmList, "Edit vendor icon");
		waitForProcessBarToGo();
		updateExistingVendor(UpdatedName, UpdatedDescription, UpdatedcountryName, UpdatedstateName);
		Thread.sleep(2000);
		verifyUpdatedVendorInformation(UpdatedName, UpdatedName, UpdatedDescription, UpdatedcountryName,
				UpdatedstateName);

	}

	public void editAndVerifyVendorFromDetailsPage(String searchEntity, String UpdatedName, String UpdatedDescription,
			String UpdatedcountryName, String UpdatedstateName) throws InterruptedException

	{
		waitUntilClickable(txt_globalSearch);
		click_custom(txt_globalSearch, searchEntity);
		sendKeys_custom(txt_globalSearch, "Global Search", searchEntity);
		Thread.sleep(2000);
		click_custom(searchedEntity, "Searched Result");
		click_custom(btn_EditVendorfrmDetail, "Edit vendor Button");
		waitForProcessBarToGo();
		updateExistingVendor(UpdatedName, UpdatedDescription, UpdatedcountryName, UpdatedstateName);
		Thread.sleep(2000);
		verifyUpdatedVendorInformationFromDetail(UpdatedName, UpdatedName, UpdatedDescription, UpdatedcountryName,
				UpdatedstateName);

	}

	public void verifyUpdatedVendorInformationFromDetail(String searchEntity, String Name, String Description,
			String countryName, String stateName) throws InterruptedException

	{

		String customerNam = getText_custom(hdr_vendorHeader, "Updated Name");
		String customerDesc = getText_custom(vendorDes, "Updated Description");
		String customerCountry = getText_custom(vendorCou, "Updated Country");
		String customerState = getText_custom(vendorST, "Updated State");

		if (!customerNam.equalsIgnoreCase(Name)) {

			sa.assertEquals(customerNam, Name, "Name not get updated");

		}
		if (!customerDesc.equalsIgnoreCase(Description)) {

			sa.assertEquals(customerDesc, Description, "Description not get updated");
		}
		if (!customerCountry.equalsIgnoreCase(countryName)) {

			sa.assertEquals(customerCountry, countryName, "Country not get updated");
		}
		if (!customerState.equalsIgnoreCase(stateName)) {

			sa.assertEquals(customerState, stateName, "State not get updated");
		}

		sa.assertAll();

	}

	public void verifyUpdatedVendorInformation(String searchEntity, String Name, String Description, String countryName,
			String stateName) throws InterruptedException

	{
		clearText(txt_globalSearch);
		click_custom(txt_globalSearch, searchEntity);
		sendKeys_custom(txt_globalSearch, "Global Search", searchEntity);
		Thread.sleep(2000);
		click_custom(searchedEntity, "Searched Result");
		waitForProcessBarToGo();
		String customerNam = getText_custom(hdr_vendorHeader, "Updated Name");
		String customerDesc = getText_custom(vendorDes, "Updated Description");
		String customerCountry = getText_custom(vendorCou, "Updated Country");
		String customerState = getText_custom(vendorST, "Updated State");

		if (!customerNam.equalsIgnoreCase(Name) && !customerDesc.equalsIgnoreCase(Description)
				&& !customerCountry.equalsIgnoreCase(countryName) && !customerState.equalsIgnoreCase(stateName)) {

			sa.assertEquals(customerNam, Name, "Name not get updated");
			sa.assertEquals(customerDesc, Description, "Description not get updated");
			sa.assertEquals(customerCountry, countryName, "Country not get updated");
			sa.assertEquals(customerState, stateName, "State not get updated");

		}

	}

	public VendorsPage_EP searchVendor(String searchEntity) throws InterruptedException

	{
		waitUntilClickable(txt_globalSearch);
		click_custom(txt_globalSearch, searchEntity);
		sendKeys_custom(txt_globalSearch, "Global Search", searchEntity);
		Thread.sleep(2000);
		click_custom(searchedEntity, "Searched Result");
		waitForProcessBarToGo();

		String searchedResult = getText_custom(hdr_vendorHeader, "Searched Result");
		System.out.println("searched result" + searchedResult);

		if (!searchedResult.equalsIgnoreCase(searchEntity)) {

			sa.fail("Vendor search is failed, not found any search record, for searched Vendor---> " + searchEntity);
			ExtentFactoryShaip.getTest().log(Status.FAIL,
					"Vendor search is failed, not found any search record, searched Vendor--> " + searchEntity);

		}
		Thread.sleep(2000);
		sa.assertAll();
		return this;
	}

	public VendorsPage_EP clickOnAddVendorButton()

	{
		waitUntilClickable(btn_addVendor);
		click_custom(btn_addVendor, "Add vendor Button");
		waitForVisibility(hdr_addVendor);
		String customerTitle = getText_custom(hdr_addVendor, "Add vendor Button Title");
		assert_contains(customerTitle, "Add Vendor", "Add vendor Button");
		return this;

	}

	public void clickOnSave()

	{

		click_custom(btn_saveVendor, "SaveButton");

	}

	public void enterVendorName(String Name)

	{
		waitUntilClickable(txt_vendorName);
		click_custom(txt_vendorName, "CustomerDescription");
		sendKeys_custom(txt_vendorName, "CustomerName", Name);
	}

	public void enterVendorDescription(String Description)

	{
		waitUntilClickable(txt_vendorDescription);
		click_custom(txt_vendorDescription, "CustomerDescription");
		sendKeys_custom(txt_vendorDescription, "CustomerDescription", Description);

	}

	public void selectCountry(String countryName) throws InterruptedException

	{
		Thread.sleep(1000);
		click_custom(drp_vendorCountry, "Country Dropdown");
		sendKeys_custom(drp_vendorCountry, "Country Dropdown", countryName);
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

		click_custom(drp_vendorState, "State Dropdown");
		Thread.sleep(1000);
		List<WebElement> stateToBeSelected = DriverFactoryShaip.getDriver()
				.findElements(By.xpath("//span[@class='mat-option-text']"));
		// System.out.println(countryToBeSelected.size());

		Thread.sleep(1000);

		for (int i = 0; i < stateToBeSelected.size(); i++) {

			String state = getText_custom(stateToBeSelected.get(i), "select state");

			if (state.equalsIgnoreCase(customerStates))

			{
				click_custom(stateToBeSelected.get(i), customerStates);
				break;
			}
		}

	}

	public VendorsPage_EP createNewVendor(String user, String permission, ITestContext context)
			throws InterruptedException

	{

		String name = generateNewVendorName();
		clickOnAddVendorButton();
		enterVendorName(name);
		enterVendorDescription("This vendor is created by automation script, Please do nor delete");
		selectCountry("India");
		selectState("Gujarat");
		waitUntilClickable(btn_saveVendor);
		clickOnSave();

		context.setAttribute("vendorName", name);
		return this;

	}

	public VendorsPage_EP editAndVerifyVendorFromListing(ITestContext context, String user, String permission)
			throws InterruptedException

	{
		String searchEntity = (String) context.getAttribute("vendorName");
		String updatedName = "Automation QA" + getDate();
		String updatedDes = "This vendor is created by automation script, do nor delete";
		String updatedCou = "Australia";
		String updatedSta = "Victoria";

		waitForProcessBarToGo();
		waitUntilClickable(txt_globalSearch);
		click_custom(txt_globalSearch, searchEntity);
		sendKeys_custom(txt_globalSearch, "Global Search", searchEntity);
		Thread.sleep(2000);
		click_custom(btn_editVendorfrmList, "Edit vendor icon");
		// waitForProcessBarToGo();
		updateExistingVendor(updatedName, updatedDes, updatedCou, updatedSta);
		Thread.sleep(2000);
		verifyUpdatedVendorInformation(updatedName, updatedName, updatedName, updatedCou, updatedSta);

		// vendorNameDetail="vendorNameList_"+user;
		context.setAttribute("vendorNameList_" + user, updatedName);
		context.setAttribute("vendorDesList", updatedDes);
		context.setAttribute("vendorCountryList", updatedCou);
		context.setAttribute("vendorStateList", updatedSta);

		return this;

	}

	public void editAndVerifyVendorFromDetailsPage(ITestContext context, String user, String permission)
			throws InterruptedException

	{

		String updatedNameD = "Automation vendor" + getDate();
		String updatedDesD = "This is created by automation script, Please do nor delete";
		String updatedCouD = "India";
		String updatedStaD = "Gujarat";
		String searchEntity = (String) context.getAttribute("vendorNameList_" + user);

		waitForProcessBarToGo();
		waitUntilClickable(txt_globalSearch);
		click_custom(txt_globalSearch, searchEntity);
		sendKeys_custom(txt_globalSearch, "Global Search", searchEntity);
		Thread.sleep(2000);
		waitUntilClickable(searchedEntity);
		click_custom(searchedEntity, "Searched Result");
		waitForProcessBarToGo();
		waitUntilClickable(btn_EditVendorfrmDetail);
		click_custom(btn_EditVendorfrmDetail, "Edit vendor Button");
		waitForProcessBarToGo();
		updateExistingVendor(updatedNameD, updatedDesD, updatedCouD, updatedStaD);
		Thread.sleep(2000);
		verifyUpdatedVendorInformationFromDetail(updatedNameD, updatedNameD, updatedDesD, updatedCouD, updatedStaD);

		context.setAttribute("vendorNameDetail_" + user, updatedNameD);

	}

	public void updateExistingVendor(String Name, String Description, String countryName, String stateName)
			throws InterruptedException

	{
		waitForVisibility(txt_vendorName);
		clearText(txt_vendorName);
		enterVendorName(Name);
		clearText(txt_vendorDescription);
		enterVendorDescription(Description);
		clearText(drp_vendorCountry);
		selectCountry(countryName);
		clearText(drp_vendorState);
		selectState(stateName);
		waitUntilClickable(btn_saveVendor);
		clickOnSave();
		waitForUpdateToasterMessage();

	}

	public VendorsPage_EP waitForSuccessToasterMessage() throws InterruptedException {

		
		verifyToaster("The vendor has been created successfully","Toaster message getting displayed");

		return this;

	}

	public void waitForUpdateToasterMessage() throws InterruptedException {

		
		verifyToaster("The vendor has been updated successfully","Toaster message getting displayed");

	}
}

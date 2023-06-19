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
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.page.cp.LanguageAbilities_CP;
import com.shaip.reportng.ExtentFactoryShaip;

public class MultipleCustomFieldProjectsPage_EP extends ActionEngineShaip {

	public MultipleCustomFieldProjectsPage_EP() {

		
		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	// Project elements

	@FindBy(xpath = "(//button[@id='more'])[1]")
	private WebElement btn_More;
	
	@FindBy(xpath = "(//button[@id='settings'])[1]")
	private WebElement btn_settings;
	
	@FindBy(xpath = "(//div[@role='tab'])[2]")
	private WebElement tab_CustomFieled;

	@FindBy(id = "submitPVButton")
	private WebElement btn_addCustomField;
	
	@FindBy(id = "add")
	private WebElement btn_addCustom;
	
	@FindBy(xpath = "//div[@class='mat-dialog-title']")
	WebElement hdr_addCustomField;
	
	@FindBy(xpath = "//button[@aria-label='Previous month']")
	WebElement btn_previousMonth;

	@FindBy(xpath = "//button[@aria-label='Next month']")
	WebElement btn_nextMonth;
	
	//custom fields
	
	@FindBy(id = "inputType")
	private WebElement drp_inputType;
	@FindBy(xpath = "//div[@class='content']//p")
	private WebElement toaster_Message;

	@FindBy(xpath = "//mat-icon[text()='clear']")
	private WebElement btn_closeToaster;
	
	//Checkbox
	
	@FindBy(id = "checkbox")
	private WebElement drp_checkbox;
	
	@FindBy(name = "key")
	private WebElement txt_name;
	@FindBy(name = "label")
	private WebElement txt_label;
	@FindBy(name = "description")
	private WebElement txt_description;
	
	@FindBy(id = "isEditable-input")
	private WebElement btn_Editable;
	@FindBy(id = "isVisible-input")
	private WebElement btn_Visible;
	@FindBy(id = "isRequired-input")
	private WebElement btn_Required;
	@FindBy(id = "keyPV")
	private WebElement txt_Key;
	@FindBy(id = "valuePV")
	private WebElement txt_Value;
	@FindBy(id = "submitPVButton")
	private WebElement txt_submitPV;
	@FindBy(id = "saveCFButton")
	private WebElement btn_saveCustomField;
	@FindBy(id = "deleteButton")
	private WebElement btn_deleteField;
	
	@FindBy(id = "cdConfirmButton")
	private WebElement btn_deleteConfField;
	
	@FindBy(xpath = "//td[3]")
	private WebElement hdr_addedField;
	
	@FindBy(xpath = "//span[contains(@class,'mat-button-wrapper')]//span")
	WebElement cal_MonthYear;

	
	//Date
	@FindBy(id = "date")
	private WebElement drp_date;
	
	@FindBy(id = "default")
	private WebElement drp_picker;;
	
	//Device
	@FindBy(id = "device")
	private WebElement drp_device;
	
	//dropdown
	@FindBy(id = "dropdown")
	private WebElement drp_dropdown;
	
	//input
	@FindBy(id = "input")
	private WebElement drp_input;
	@FindBy(name = "default")
	private WebElement txt_inputDefault;
	@FindBy(name = "minLength")
	private WebElement txt_minLength;
	@FindBy(id = "maxLength")
	private WebElement txt_maxLength;
	
	//location
	@FindBy(id = "location")
	private WebElement drp_location;
	//multiselect dropdown

	@FindBy(id = "multiSelectDropdown")
	private WebElement drp_multiSelectDropdown;
	@FindBy(id = "subType")
	private WebElement drp_subType;
	//radio
	@FindBy(id = "radio")
	private WebElement drp_radio;
	@FindBy(id = "textarea")
	private WebElement drp_textarea;
	
	
	
	public MultipleCustomFieldProjectsPage_EP checkForExistingFields() throws InterruptedException

	{
		System.out.println("Checking for existing fields");
		Thread.sleep(2000);

		if (DriverFactoryShaip.getDriver().findElements(By.id("deleteButton"))
				.size() != 0)

		{
			List<WebElement> languagedAdded = DriverFactoryShaip.getDriver()
					.findElements(By.id("deleteButton"));

			for (int i = 0; i < languagedAdded.size(); i++) {
				clickOnDeleteCustomFields();
				System.out.println("Delete existing fields");


			}
			Thread.sleep(2000);
		}
		return this;

	}
	
	public MultipleCustomFieldProjectsPage_EP clickOnDeleteCustomFields() throws InterruptedException

	{

		Thread.sleep(1000);
		click_custom(btn_deleteField, "Delete custom fields");
		Thread.sleep(1000);
		click_custom(btn_deleteConfField, "Delete Confirm");
		verifyDeleteSuccessToaster();

		return this;

	}
	

	public MultipleCustomFieldProjectsPage_EP addCheckboxCustomField() throws InterruptedException

	{

		for (int i=0;i<4;i++)
		{
		clickOnAddCustomFieldButton();
		clickOnInputType();	    
		String name="AutoCheckbox"+getDate();
		String labels="Auto Checkbox";
		String Des="This checkbox created using automation script.Do not delete it.";

		waitUntilClickable(drp_checkbox);
		click_custom(drp_checkbox, "Check box");
		//waitForProcessBarToGo();
		waitUntilClickable(txt_name);
		sendKeys_custom(txt_name,"Check box name",name);
		sendKeys_custom(txt_label,"Check box name",labels);
		sendKeys_custom(txt_description,"Check box Description",Des);
		verifySwitchOnStates(btn_Editable,"Is Editable");
		verifySwitchOnStates(btn_Visible,"Is Visible");
		verifySwitchOnStates(btn_Required,"Is Required");
		sendKeys_custom(txt_Key,"Check box key","Audio");
		sendKeys_custom(txt_Value,"Check box value","MP3");
		click_custom(btn_addCustomField, "Add custom field");
		Thread.sleep(1000);	
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		isCustomFieldAdded(name);
		
		}
		
		
		return this;

	}
	
	public MultipleCustomFieldProjectsPage_EP addDateCustomField() throws InterruptedException

	{

		for (int i=0;i<5;i++)
		{
		clickOnAddCustomFieldButton();
		clickOnInputType();	
		
		String name="AutoDate"+getDate();
		String labels="Auto Date";
		String Des="This Date field created using automation script.Do not delete it.";

		waitUntilClickable(drp_date);
		click_custom(drp_date, "Date Field");
		Thread.sleep(2000);	
		sendKeys_custom(txt_name,"Date field name",name);
		sendKeys_custom(txt_label,"Date field name",labels);
		sendKeys_custom(txt_description,"Date field Description",Des);
		verifySwitchOnStates(btn_Editable,"Is Editable");
		verifySwitchOnStates(btn_Visible,"Is Visible");
		verifySwitchOnStates(btn_Required,"Is Required");
		setDefaultDate();
		Thread.sleep(1000);	
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		isCustomFieldAdded(name);
		
		}
		return this;

	}
	
	
	public MultipleCustomFieldProjectsPage_EP addDeviceCustomField() throws InterruptedException

	{

		String name="AutoDevice"+getDate();
		String labels="Auto Device";
		String Des="This Device field created using automation script.Do not delete it.";

		waitUntilClickable(drp_device);
		click_custom(drp_device, "Device Field");
		Thread.sleep(2000);	
		sendKeys_custom(txt_name,"Device field name",name);
		sendKeys_custom(txt_label,"Device field name",labels);
		sendKeys_custom(txt_description,"Device field Description",Des);
		verifySwitchOFFStates(btn_Editable,"Is Editable");
		verifySwitchOnStates(btn_Visible,"Is Visible");
		verifySwitchOnStates(btn_Required,"Is Required");
		Thread.sleep(1000);	
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		isCustomFieldAdded(name);
		
		
		return this;

	}
	
	
	public MultipleCustomFieldProjectsPage_EP addDropdownCustomField() throws InterruptedException

	{

		for (int i=0;i<5;i++)
		{
		clickOnAddCustomFieldButton();
		clickOnInputType();	
		String name="AutoDropdown"+getDate();
		String labels="Auto Dropdown";
		String Des="This Dropdown field created using automation script.Do not delete it.";
		waitUntilClickable(drp_dropdown);
		click_custom(drp_dropdown, "Dropdown Field");
		Thread.sleep(2000);	
		sendKeys_custom(txt_name,"Dropdown field name",name);
		sendKeys_custom(txt_label,"Dropdown field name",labels);
		sendKeys_custom(txt_description,"Dropdown field Description",Des);
		verifySwitchOnStates(btn_Editable,"Is Editable");
		verifySwitchOnStates(btn_Visible,"Is Visible");
		verifySwitchOnStates(btn_Required,"Is Required");
		sendKeys_custom(txt_Key,"dropdown key","Audio");
		sendKeys_custom(txt_Value,"dropdown value","MP3");
		click_custom(btn_addCustomField, "Add custom field");
		Thread.sleep(1000);	
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		isCustomFieldAdded(name);
		
		}
		return this;

	}
	
	public MultipleCustomFieldProjectsPage_EP addInputCustomField() throws InterruptedException

	{
		for (int i=0;i<5;i++)
		{
		clickOnAddCustomFieldButton();
		clickOnInputType();	
		
		String name="AutoInput"+getDate();
		String labels="Auto Input";
		String Des="This Input field created using automation script.Do not delete it.";
		waitUntilClickable(drp_input);
		click_custom(drp_input, "Input Field");
		Thread.sleep(2000);	
		sendKeys_custom(txt_name,"Input field name",name);
		sendKeys_custom(txt_label,"Input field name",labels);
		sendKeys_custom(txt_description,"Input field Description",Des);
		verifySwitchOnStates(btn_Editable,"Is Editable");
		verifySwitchOnStates(btn_Visible,"Is Visible");
		verifySwitchOnStates(btn_Required,"Is Required");
		sendKeys_custom(txt_inputDefault,"Input Default","TestQA");
		sendKeys_custom(txt_minLength,"Input min length","4");
		sendKeys_custom(txt_maxLength,"Input max length","12");
		Thread.sleep(1000);	
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		isCustomFieldAdded(name);
		
		}
		return this;

	}
	
	public MultipleCustomFieldProjectsPage_EP addLocationCustomField() throws InterruptedException

	{

		String name="AutoLocation"+getDate();
		String labels="Auto Location";
		String Des="This Location field created using automation script.Do not delete it.";

		waitUntilClickable(drp_location);
		click_custom(drp_location, "Location Field");
		Thread.sleep(2000);	
		sendKeys_custom(txt_name,"Location field name",name);
		sendKeys_custom(txt_label,"Location field name",labels);
		sendKeys_custom(txt_description,"Location field Description",Des);
		verifySwitchOFFStates(btn_Editable,"Is Editable");
		verifySwitchOnStates(btn_Visible,"Is Visible");
		verifySwitchOnStates(btn_Required,"Is Required");
		Thread.sleep(1000);	
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		isCustomFieldAdded(name);
		
		
		return this;

	}
	
	public MultipleCustomFieldProjectsPage_EP addMultiDropdownCustomField() throws InterruptedException

	{
		for (int i=0;i<5;i++)
		{
		clickOnAddCustomFieldButton();
		clickOnInputType();	

		String name="AutoMultiDropdown"+getDate();
		String labels="Auto MultiDropdown";
		String Des="This MultiDropdown field created using automation script.Do not delete it.";
		waitUntilClickable(drp_multiSelectDropdown);
		click_custom(drp_multiSelectDropdown, "MultiDropdown Field");
		Thread.sleep(2000);	
		sendKeys_custom(txt_name,"MultiDropdown field name",name);
		sendKeys_custom(txt_label,"MultiDropdown field name",labels);
		sendKeys_custom(txt_description,"MultiDropdown field Description",Des);
		verifySwitchOnStates(btn_Editable,"Is Editable");
		verifySwitchOnStates(btn_Visible,"Is Visible");
		verifySwitchOnStates(btn_Required,"Is Required");
		sendKeys_custom(txt_Key,"dropdown key","Audio");
		sendKeys_custom(txt_Value,"dropdown value","MP3");
		click_custom(btn_addCustomField, "Add custom field");
		Thread.sleep(1000);	
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		isCustomFieldAdded(name);
		
		}
		return this;

	}
	
	public MultipleCustomFieldProjectsPage_EP addRadioCustomField() throws InterruptedException

	{

		for (int i=0;i<5;i++)
		{
		clickOnAddCustomFieldButton();
		clickOnInputType();	
		String name="AutoRadio"+getDate();
		String labels="Auto Radio";
		String Des="This Radio field created using automation script.Do not delete it.";
		waitUntilClickable(drp_radio);
		click_custom(drp_radio, "Radio Field");
		Thread.sleep(2000);	
		sendKeys_custom(txt_name,"Radio field name",name);
		sendKeys_custom(txt_label,"Radio field name",labels);
		sendKeys_custom(txt_description,"Radio field Description",Des);
		verifySwitchOnStates(btn_Editable,"Is Editable");
		verifySwitchOnStates(btn_Visible,"Is Visible");
		verifySwitchOnStates(btn_Required,"Is Required");
		sendKeys_custom(txt_Key,"Radio key","Audio");
		sendKeys_custom(txt_Value,"Radio value","MP3");
		click_custom(btn_addCustomField, "Add custom field");
		Thread.sleep(1000);	
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		isCustomFieldAdded(name);
		
		}
		return this;

	}
	
	public MultipleCustomFieldProjectsPage_EP addTextAreaCustomField() throws InterruptedException

	{

		for (int i=0;i<8;i++)
		{
		clickOnAddCustomFieldButton();
		clickOnInputType();	
		String name="AutoTextArea"+getDate();
		String labels="Auto TextArea";
		String Des="This TextArea field created using automation script.Do not delete it.";
		waitUntilClickable(drp_textarea);
		click_custom(drp_textarea, "TextArea Field");
		Thread.sleep(2000);	
		sendKeys_custom(txt_name,"TextArea field name",name);
		sendKeys_custom(txt_label,"TextArea field name",labels);
		sendKeys_custom(txt_description,"TextArea field Description",Des);
		verifySwitchOnStates(btn_Editable,"Is Editable");
		verifySwitchOnStates(btn_Visible,"Is Visible");
		verifySwitchOnStates(btn_Required,"Is Required");
		sendKeys_custom(txt_inputDefault,"TextArea Default","TestQA");
		sendKeys_custom(txt_minLength,"TextArea min length","4");
		sendKeys_custom(txt_maxLength,"TextArea max length","22");
		Thread.sleep(1000);	
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		isCustomFieldAdded(name);
		
		}
		
		return this;

	}
	
	
	public void setDefaultDate() throws InterruptedException

	{

		String dt_StartDate = getTomorrow();
		String tomorrowMonth=getTomorrowMonthYear();
		drp_picker.click();
		Thread.sleep(2000);
		waitForVisibility(cal_MonthYear);
		String currentMonthYear=getText_custom(cal_MonthYear, "Current Month Year");
		
		if (!currentMonthYear.equalsIgnoreCase(tomorrowMonth)) {
		
			btn_nextMonth.click();
			Thread.sleep(2000);

		}

		WebElement picker = DriverFactoryShaip.getDriver().findElement(By.xpath("//tbody[@class='mat-calendar-body']"));

		List<WebElement> Days = picker.findElements(By.xpath("tr//td[@role='gridcell']//div[1]"));

		int totalelementsfind = Days.size();
		String DayValue = null;


		int flag = 0;
		for (int i = 0; i < totalelementsfind; i++) {

			DayValue = Days.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ");

			if (DayValue.length() <= 1)

			{
				DayValue = "0" + DayValue;

			}
			if (DayValue.equalsIgnoreCase(dt_StartDate)) {
				Days.get(i).click();
				flag = 1;
				break;
			}

		}
		ExtentFactoryShaip.getTest().log(Status.INFO, "Start date selected as "+DayValue);
		ExtentFactoryShaip.getTest().log(Status.INFO, "Start Date selected for project");

	}

	
	
	public MultipleCustomFieldProjectsPage_EP isCustomFieldAdded(String addedField) throws InterruptedException

	{
		waitUntilClickable(hdr_addedField);
		Thread.sleep(2000);
		String searchedResult = getText_custom(hdr_addedField, "Added Custom Field");
		System.out.println("searched Field" + searchedResult);

		if (!searchedResult.equalsIgnoreCase(addedField)) {

			//sa.fail("Project search is failed, not found any search record, for searched customer---> "+searchEntity);
			ExtentFactoryShaip.getTest().log(Status.FAIL,"Custom field has not been added successfully");
			

		}
		Thread.sleep(2000);
		sa.assertAll();
		return this;
	}


	
	public void verifySwitchOnStates(WebElement element,String locator)

	{
		boolean isDisabled = false;
		try {
		String btnState = element.getAttribute("aria-checked");
		isDisabled = btnState.equalsIgnoreCase("true");
		
		if(isDisabled)
		{
			sa.assertTrue(isDisabled,locator+" "+"button has not been in Switch ON mode by default");
			ExtentFactoryShaip.getTest().log(Status.PASS,locator+" "+"button is in Switch ON mode by default as expected.");
			sa.assertAll();
			
		}
		else
		{
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),"Test case failure screenshot");		
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper
					.createLabel(locator+" "+"button has not been in Switch ON mode by default", ExtentColor.RED));
			sa.assertAll();
			
			
		}
		}
		catch (Exception e) {
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),"Test case failure screenshot");		
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper
					.createLabel(locator+" "+"button has not been in Switch ON mode by default", ExtentColor.RED));
			sa.assertAll();
		}
	
	}
	
	public void verifySwitchOFFStates(WebElement element,String locator)

	{
		boolean isDisabled = false;
		try {
		String btnState = element.getAttribute("aria-checked");
		isDisabled = btnState.equalsIgnoreCase("false");
		
		if(isDisabled)
		{
			sa.assertTrue(isDisabled,locator+" "+"button has not been in Switch OFF mode by default");
			ExtentFactoryShaip.getTest().log(Status.PASS,locator+" "+"button is in Switch OFF mode by default as expected.");
			sa.assertAll();
			
		}
		else
		{
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),"Test case failure screenshot");		
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper
					.createLabel(locator+" "+"button has not been in Switch OFF mode by default", ExtentColor.RED));
			sa.assertAll();
			
			
		}
		}
		catch (Exception e) {
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),"Test case failure screenshot");		
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper
					.createLabel(locator+" "+"button has not been in Switch OFF mode by default", ExtentColor.RED));
			sa.assertAll();
		}
	
	}
	
	
	public MultipleCustomFieldProjectsPage_EP clickOnInputType()

	{

		waitUntilClickable(drp_inputType);
		click_custom(drp_inputType, "Type of Custom Field");
		return this;

	}
	
	public MultipleCustomFieldProjectsPage_EP clickOnMoreOptions()

	{

		moveToElement_custom(btn_More, "More Options");
		click_custom(btn_More, "More Options");
		return this;

	}
	
	public MultipleCustomFieldProjectsPage_EP clickOnSettings()

	{

		waitUntilClickable(btn_settings);
		click_custom(btn_settings, "Settings Project");
		try {
			waitForProcessBarToGo();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;

	}
	
	public MultipleCustomFieldProjectsPage_EP clickOnCustomFieldTab()

	{

		waitUntilClickable(tab_CustomFieled);
		click_custom(tab_CustomFieled, "Custom field Tab");
		return this;

	}
	
	
	public MultipleCustomFieldProjectsPage_EP verifyAddCustomFieledButtonPermissionAccess(String user, String createPermission)
			throws InterruptedException

	{

		if (createPermission.equalsIgnoreCase("Yes")) {
			clickOnAddCustomFieldButton();
		} else {
			verifyAddCustomerButtonIsDisabled(user);
		}

		sa.assertAll();
		return this;

	}

	public void verifyAddCustomerButtonIsDisabled(String user)

	{	
		boolean isDisabled = false;
		try {
		String classes = btn_addCustom.getAttribute("disabled");
		 isDisabled = classes.equalsIgnoreCase("true");

		if (isDisabled) {
			
			sa.assertTrue(isDisabled, "Add Custom Field button should not be visible to this user as expected----> " + user);
			ExtentFactoryShaip.getTest().log(Status.PASS,
					"Add Custom Field button should not be visible to this user as expected ---->  " + user);
			sa.assertAll();

		} else {
			sa.assertTrue(isDisabled, "Add Custom Field button should not be visible to this user----> " + user);
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper.createLabel(
					"Add Custom Field button should not be visible to this user ---->  " + user, ExtentColor.RED));
			sa.assertAll();

		}
		
		}catch (Exception e) {
			e.printStackTrace();
			sa.assertTrue(isDisabled, "Add Custom Field button should not be visible to this user----> " + user);
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper.createLabel(
					"Add Custom Field button should not be visible to this user ---->  " + user, ExtentColor.RED));
			sa.assertAll();
		}

	}
	
	public MultipleCustomFieldProjectsPage_EP clickOnAddCustomFieldButton()

	{
		waitUntilClickable(btn_addCustom);
		click_custom(btn_addCustom, "Add Custom Field Button");
		waitForVisibility(hdr_addCustomField);
		String projectTitle = getText_custom(hdr_addCustomField, "Add Custom Field Title");
		assert_contains(projectTitle, "Add Custom Field", "Add Custom Field Screen");

		return this;

	}

	public MultipleCustomFieldProjectsPage_EP verifyDeleteSuccessToaster() throws InterruptedException {

		
		verifyToaster("The custom field have been deleted successfully", "Custom field Toaster");

		return this;

	}
	
	public MultipleCustomFieldProjectsPage_EP verifyCustomFieldAddedSuccessToaster() throws InterruptedException {

		
		verifyToaster("The custom field have been added successfully", "Custom field Toaster");

		return this;

	}
	
	
	
}

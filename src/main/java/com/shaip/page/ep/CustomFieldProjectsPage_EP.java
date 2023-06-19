package com.shaip.page.ep;

import java.util.HashMap;
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
import com.shaip.enums.story;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.page.cp.LanguageAbilities_CP;
import com.shaip.reportng.ExtentFactoryShaip;

public class CustomFieldProjectsPage_EP extends ActionEngineShaip {

	public CustomFieldProjectsPage_EP() {

		
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
	
	@FindBy(id = "bulkAddPVButton")
	private WebElement btn_bulkAdd;
	
	@FindBy(id = "valuePair")
	private WebElement txt_keyvalue;
	
	@FindBy(id = "saveBulkPVButton")
	private WebElement btn_saveKeyValues;
	
	@FindBy(id = "subType")
	private WebElement drp_subtype;
	@FindBy(id = "email")
	private WebElement tpl_Email;
	@FindBy(id = "number")
	private WebElement tpl_Number;
	@FindBy(id = "autocomplete")
	private WebElement tpl_autocomplete;
	
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
	
	@FindBy(id = "headerBackIcon")
	private WebElement btn_headerBack;

	@FindBy(id = "dtGlobalSearch")
	private WebElement txt_globalSearch;
	
	//Date
	@FindBy(id = "date")
	private WebElement drp_date;
	
	@FindBy(id = "defaultValue")
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
	
	
	
	
	
	public CustomFieldProjectsPage_EP checkForExistingFields() throws InterruptedException

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
	
	public CustomFieldProjectsPage_EP clickOnDeleteCustomFields() throws InterruptedException

	{

		Thread.sleep(1000);
		click_custom(btn_deleteField, "Delete custom fields");
		Thread.sleep(1000);
		click_custom(btn_deleteConfField, "Delete Confirm");
		verifyDeleteSuccessToaster();

		return this;

	}
	

	public CustomFieldProjectsPage_EP addCheckboxCustomField(String UserRole,String methodName, HashMap<String, String> testData,String type) throws InterruptedException

	{

		setupExtentReport("EP: As a User, I should be able to create checkbox custom field in Audio "+type+" type project ("+UserRole+")", methodName,UserRole,story.CheckboxCustomField_EP_804.toString(),"Smoke","EP");
		giveTestRoleInfo(testData,"UserRole");

		String name="favoriteSinger";
		String labels="Favorite Singer";
		String Des="Select your favorite singer (using autoscript)";

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
		sendKeys_custom(txt_Key,"Check box key","SonuNigam");
		sendKeys_custom(txt_Value,"Check box value","Sonu Nigam");
		click_custom(btn_addCustomField, "Add custom field");
		Thread.sleep(1000);	
		sendKeys_custom(txt_Key,"Check box key","AlkaYagnik");
		sendKeys_custom(txt_Value,"Check box value","Alka Yagnik");
		click_custom(btn_addCustomField, "Add custom field");
		Thread.sleep(1000);	
		sendKeys_custom(txt_Key,"Check box key","KumarShanu");
		sendKeys_custom(txt_Value,"Check box value","Kumar Shanu");
		click_custom(btn_addCustomField, "Add custom field");
		Thread.sleep(1000);	
		sendKeys_custom(txt_Key,"Check box key","ArijitSingh");
		sendKeys_custom(txt_Value,"Check box value","Arijit Singh");
		click_custom(btn_addCustomField, "Add custom field");
		Thread.sleep(1000);	
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		//isCustomFieldAdded(name);
		
		
		return this;

	}
	
	public CustomFieldProjectsPage_EP addCheckboxCustomField2(String UserRole,String methodName, HashMap<String, String> testData,String type) throws InterruptedException

	{

		

		String name="MedicationPrefrence";
		String labels="Medication Prefrence";
		String Des="Select your Medication Prefrence (using autoscript)";
		String pair="allopathy Allopathy\r\n"
				+ "homeopathy Homeopathy\r\n"
				+ "clinicaltrial ClinicalTrial\r\n"
				+ "ayurvedic Ayurvedic";

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
		waitUntilClickable(btn_bulkAdd);
		click_custom(btn_bulkAdd, "Bulk Country Add");
		Thread.sleep(1000);
		waitForVisibility(btn_saveKeyValues);
		click_custom(txt_keyvalue, "KeyValues");
		sendKeys_custom(txt_keyvalue, "KeyValues", pair);
		waitUntilClickable(btn_saveKeyValues);
		click_custom(btn_saveKeyValues, " Save KeyValues");
		Thread.sleep(1000);
		waitUntilClickable(btn_saveCustomField);
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		//isCustomFieldAdded(name);
		
		
		return this;

	}
	
	public CustomFieldProjectsPage_EP addDateCustomField(String UserRole,String methodName,HashMap<String, String> testData,String type) throws InterruptedException

	{
		setupExtentReport("EP: As a User, I should be able to create Date custom field in Audio "+type+" type project ("+UserRole+")", methodName,UserRole,story.DateCustomField_EP_807.toString(),"Smoke","EP");
		giveTestRoleInfo(testData,"UserRole");

		String name="ExecutionDate";
		String labels="Execution Date";
		String Des="Select Execution Date (using autoscript)";

		waitUntilClickable(drp_date);
		click_custom(drp_date, "Date Field");
		Thread.sleep(2000);	
		sendKeys_custom(txt_name,"Date field name",name);
		sendKeys_custom(txt_label,"Date field name",labels);
		sendKeys_custom(txt_description,"Date field Description",Des);
		verifySwitchOnStates(btn_Editable,"Is Editable");
		verifySwitchOnStates(btn_Visible,"Is Visible");
		verifySwitchOnStates(btn_Required,"Is Required");
		//setDefaultDate();
		Thread.sleep(1000);	
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		//isCustomFieldAdded(name);
		
		
		return this;

	}
	
	
	public CustomFieldProjectsPage_EP addDeviceCustomField(String UserRole,String methodName,HashMap<String, String> testData,String type) throws InterruptedException

	{

		setupExtentReport("EP: As a User, I should be able to create Device custom field in Audio "+type+" type project ("+UserRole+")", methodName,UserRole,story.DeviceCustomField_EP_809.toString(),"Smoke","EP");
		giveTestRoleInfo(testData,"UserRole");

		String name="Device"+getDate();
		String labels="Your Device Information";
		String Des="This Device field information (using autoscript)";

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
		//isCustomFieldAdded(name);
		
		
		return this;

	}
	
	
	public CustomFieldProjectsPage_EP addDropdownCustomField(String UserRole,String methodName,HashMap<String, String> testData,String type) throws InterruptedException

	{

		setupExtentReport("EP: As a User, I should be able to create Autocomplete Dropdown custom field in Audio "+type+" type project ("+UserRole+")", methodName,UserRole,story.DeviceCustomField_EP_809.toString(),"Smoke","EP");
		giveTestRoleInfo(testData,"UserRole");

		String name="Country"+getDate();
		String labels="Country";
		String Des="Select your country (using autoscript)";
		String pair="india India\r\n"
				+ "usa USA\r\n"
				+ "canada Canada\r\n"
				+ "americansamoa American Samao\r\n"
				+ "srilanka SriLanka\r\n"
				+ "china China";
		waitUntilClickable(drp_dropdown);
		click_custom(drp_dropdown, "Dropdown Field");
		Thread.sleep(2000);
		waitUntilClickable(drp_subtype);
		click_custom(drp_subtype, "SubTypeInput Field");
		waitUntilClickable(tpl_autocomplete);
		Thread.sleep(1000);	
		click_custom(tpl_autocomplete, "Autocomplete SubTypeInput Field");
		Thread.sleep(2000);	
		sendKeys_custom(txt_name,"Dropdown field name",name);
		sendKeys_custom(txt_label,"Dropdown field name",labels);
		sendKeys_custom(txt_description,"Dropdown field Description",Des);
		verifySwitchOnStates(btn_Editable,"Is Editable");
		verifySwitchOnStates(btn_Visible,"Is Visible");
		verifySwitchOnStates(btn_Required,"Is Required");
		waitUntilClickable(btn_bulkAdd);
		click_custom(btn_bulkAdd, "Bulk Country Add");
		Thread.sleep(1000);
		waitForVisibility(btn_saveKeyValues);
		click_custom(txt_keyvalue, "KeyValues");
		sendKeys_custom(txt_keyvalue, "KeyValues", pair);
		waitUntilClickable(btn_saveKeyValues);
		click_custom(btn_saveKeyValues, " Save KeyValues");
		Thread.sleep(1000);
		waitUntilClickable(btn_saveCustomField);
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		
		/*sendKeys_custom(txt_Key,"dropdown key","bharat");
		sendKeys_custom(txt_Value,"dropdown value","India");
		click_custom(btn_addCustomField, "Add custom field");
		Thread.sleep(1000);	
		sendKeys_custom(txt_Key,"dropdown key","bharat");
		sendKeys_custom(txt_Value,"dropdown value","India");
		click_custom(btn_addCustomField, "Add custom field");
		Thread.sleep(1000);*/
		
		//isCustomFieldAdded(name);
		
		
		return this;

	}public CustomFieldProjectsPage_EP addDropdownCustomField2(String UserRole,String methodName,HashMap<String, String> testData,String type) throws InterruptedException

	{

		setupExtentReport("EP: As a User, I should be able to create Dropdown custom field of type 'Select'  in Audio  "+type+" type project ("+UserRole+")", methodName,UserRole,story.DeviceCustomField_EP_809.toString(),"Smoke","EP");
		giveTestRoleInfo(testData,"UserRole");

		String name="ageRange";
		String labels="Age Range";
		String Des="Select your Age Range criteria (using autoscript)";
		String pair="10-20 10-20\r\n"
				+ "21-30 21-30\r\n"
				+ "31-40 31-40";
		waitUntilClickable(drp_dropdown);
		click_custom(drp_dropdown, "Dropdown Field");
		Thread.sleep(2000);	
		sendKeys_custom(txt_name,"Dropdown field name",name);
		sendKeys_custom(txt_label,"Dropdown field name",labels);
		sendKeys_custom(txt_description,"Dropdown field Description",Des);
		verifySwitchOnStates(btn_Editable,"Is Editable");
		verifySwitchOnStates(btn_Visible,"Is Visible");
		verifySwitchOnStates(btn_Required,"Is Required");
		waitUntilClickable(btn_bulkAdd);
		click_custom(btn_bulkAdd, "Bulk Country Add");
		Thread.sleep(1000);
		waitForVisibility(btn_saveKeyValues);
		click_custom(txt_keyvalue, "KeyValues");
		sendKeys_custom(txt_keyvalue, "KeyValues", pair);
		waitUntilClickable(btn_saveKeyValues);
		click_custom(btn_saveKeyValues, " Save KeyValues");
		Thread.sleep(1000);
		waitUntilClickable(btn_saveCustomField);
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		
		return this;

	}
	
	
	
	public CustomFieldProjectsPage_EP addInputCustomField(String UserRole,String methodName,HashMap<String, String> testData,String type) throws InterruptedException

	{
		setupExtentReport("EP: As a User, I should be able to create Input custom field with subtype as 'Any' in Audio "+type+" type project ("+UserRole+")", methodName,UserRole,story.InputCustomField_EP_693.toString(),"Smoke","EP");
		giveTestRoleInfo(testData,"UserRole");

		String name="emotions";
		String labels="Emotions";
		String Des="Write down emotions of the audio files (using autoscript)";
		waitUntilClickable(drp_input);
		click_custom(drp_input, "Input Field");
		Thread.sleep(2000);	
		sendKeys_custom(txt_name,"Input field name",name);
		sendKeys_custom(txt_label,"Input field name",labels);
		sendKeys_custom(txt_description,"Input field Description",Des);
		verifySwitchOnStates(btn_Editable,"Is Editable");
		verifySwitchOnStates(btn_Visible,"Is Visible");
		verifySwitchOnStates(btn_Required,"Is Required");
		//sendKeys_custom(txt_inputDefault,"Input Default","");
		sendKeys_custom(txt_minLength,"Input min length","4");
		sendKeys_custom(txt_maxLength,"Input max length","12");
		Thread.sleep(1000);	
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		//isCustomFieldAdded(name);
		
		
		return this;

	}
	
	public CustomFieldProjectsPage_EP addInputEmailTypeCustomField(String UserRole,String methodName,HashMap<String, String> testData,String type) throws InterruptedException

	{
		setupExtentReport("EP: As a User, I should be able to create Input custom field with subtype as 'Email' in Audio "+type+" type project ("+UserRole+")", methodName,UserRole,story.InputCustomField_EP_693.toString(),"Smoke","EP");
		giveTestRoleInfo(testData,"UserRole");

		String name="Emailid";
		String labels="Email Id";
		String Des="Please enter your email address (using autoscript)";
		waitUntilClickable(drp_input);
		click_custom(drp_input, "Input Field");
		Thread.sleep(2000);	
		waitUntilClickable(drp_subtype);
		click_custom(drp_subtype, "SubTypeInput Field");
		waitUntilClickable(tpl_Email);
		Thread.sleep(1000);	
		click_custom(tpl_Email, "Email SubTypeInput Field");
		Thread.sleep(2000);	
		sendKeys_custom(txt_name,"Input field name",name);
		sendKeys_custom(txt_label,"Input field name",labels);
		sendKeys_custom(txt_description,"Input field Description",Des);
		verifySwitchOnStates(btn_Editable,"Is Editable");
		verifySwitchOnStates(btn_Visible,"Is Visible");
		verifySwitchOnStates(btn_Required,"Is Required");
		Thread.sleep(1000);	
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		//isCustomFieldAdded(name);
		
		
		return this;

	}
	
	public CustomFieldProjectsPage_EP addInputNumberTypeCustomField(String UserRole,String methodName,HashMap<String, String> testData,String type) throws InterruptedException

	{
		setupExtentReport("EP: As a User, I should be able to create Input custom field with subtype as 'Number' in Audio "+type+" type project ("+UserRole+")", methodName,UserRole,story.InputCustomField_EP_693.toString(),"Smoke","EP");
		giveTestRoleInfo(testData,"UserRole");

		String name="submitedfiles";
		String labels="No Of Collected Files";
		String Des="Please enter how many files you have collected in task (using autoscript)";
		waitUntilClickable(drp_input);
		click_custom(drp_input, "Input Field");
		Thread.sleep(2000);	
		waitUntilClickable(drp_subtype);
		click_custom(drp_subtype, "SubTypeInput Field");
		waitUntilClickable(tpl_Number);
		Thread.sleep(1000);	
		click_custom(tpl_Number, "Number SubTypeInput Field");
		Thread.sleep(2000);	
		sendKeys_custom(txt_name,"Input field name",name);
		sendKeys_custom(txt_label,"Input field name",labels);
		sendKeys_custom(txt_description,"Input field Description",Des);
		verifySwitchOnStates(btn_Editable,"Is Editable");
		verifySwitchOnStates(btn_Visible,"Is Visible");
		verifySwitchOnStates(btn_Required,"Is Required");
		Thread.sleep(1000);	
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		//isCustomFieldAdded(name);
		
		
		return this;

	}
	
	public CustomFieldProjectsPage_EP addLocationCustomField(String UserRole,String methodName,HashMap<String, String> testData,String type) throws InterruptedException

	{
		setupExtentReport("EP: As a User, I should be able to create Location custom field in Audio "+type+" type project ("+UserRole+")", methodName,UserRole,story.LocationCustomField_EP_808.toString(),"Smoke","EP");
		giveTestRoleInfo(testData,"UserRole");


		String name="Location";
		String labels="Your Location";
		String Des="Your location Information (using autoscript)";

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
		//isCustomFieldAdded(name);
		
		
		return this;

	}
	
	public CustomFieldProjectsPage_EP addMultiDropdownCustomField(String UserRole,String methodName,HashMap<String, String> testData,String type) throws InterruptedException

	{
		setupExtentReport("EP: As a User, I should be able to create MultiSelectDropdown custom field in Audio "+type+" type project ("+UserRole+")", methodName,UserRole,story.MultiDropdownCustomField_EP_806.toString(),"Smoke","EP");
		giveTestRoleInfo(testData,"UserRole");

		String name="LanguagesKnown";
		String labels="Languages Known";
		String Des="Select Languages which you know (Using auto script)";
		String pair="english English\r\n"
				+ "hindi Hindi\r\n"
				+ "gujarati Gujarati\r\n"
				+ "spanish Spanish\r\n"
				+ "french French\r\n"
				+ "marathi Marathi\r\n"
				+ "urdu Urdu\r\n"
				+ "telugu Telugu\r\n"
				+ "odishi Odishi\r\n"
				+ "nepali Nepali\r\n"
				+ "sinhali Sinhali\r\n"
				+ "tamil Tamil\r\n"
				+ "sanskrit Sanskrit\r\n"
				+ "chinese Chinese\r\n"
				+ "japanese Japanese";
		waitUntilClickable(drp_multiSelectDropdown);
		click_custom(drp_multiSelectDropdown, "MultiDropdown Field");
		Thread.sleep(2000);	
		sendKeys_custom(txt_name,"MultiDropdown field name",name);
		sendKeys_custom(txt_label,"MultiDropdown field name",labels);
		sendKeys_custom(txt_description,"MultiDropdown field Description",Des);
		verifySwitchOnStates(btn_Editable,"Is Editable");
		verifySwitchOnStates(btn_Visible,"Is Visible");
		verifySwitchOnStates(btn_Required,"Is Required");
		waitUntilClickable(btn_bulkAdd);
		click_custom(btn_bulkAdd, "Bulk Country Add");
		Thread.sleep(1000);
		waitForVisibility(btn_saveKeyValues);
		click_custom(txt_keyvalue, "KeyValues");
		sendKeys_custom(txt_keyvalue, "KeyValues", pair);
		waitUntilClickable(btn_saveKeyValues);
		click_custom(btn_saveKeyValues, " Save KeyValues");
		Thread.sleep(1000);
		waitUntilClickable(btn_saveCustomField);
		
		/*sendKeys_custom(txt_Key,"dropdown key","Audio");
		sendKeys_custom(txt_Value,"dropdown value","MP3");
		click_custom(btn_addCustomField, "Add custom field");
		Thread.sleep(1000);	*/
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		//isCustomFieldAdded(name);
		
		
		return this;

	}
	
	public CustomFieldProjectsPage_EP addMultiDropdownCustomField2(String UserRole,String methodName,HashMap<String, String> testData,String type) throws InterruptedException

	{
		

		String name="cuisinePreference";
		String labels="Beverage Preference";
		String Des="Please select cuisine preference (Using auto script)";
		String pair="continental Continental\r\n"
				+ "chinese Chinese\r\n"
				+ "thai Thai\r\n"
				+ "punjabi Punjabi\r\n"
				+ "nonveg NonVeg\r\n"
				+ "fish Fish";
		waitUntilClickable(drp_multiSelectDropdown);
		click_custom(drp_multiSelectDropdown, "MultiDropdown Field");
		Thread.sleep(2000);	
		sendKeys_custom(txt_name,"MultiDropdown field name",name);
		sendKeys_custom(txt_label,"MultiDropdown field name",labels);
		sendKeys_custom(txt_description,"MultiDropdown field Description",Des);
		verifySwitchOnStates(btn_Editable,"Is Editable");
		verifySwitchOnStates(btn_Visible,"Is Visible");
		verifySwitchOnStates(btn_Required,"Is Required");
		waitUntilClickable(btn_bulkAdd);
		click_custom(btn_bulkAdd, "Bulk Country Add");
		Thread.sleep(1000);
		waitForVisibility(btn_saveKeyValues);
		click_custom(txt_keyvalue, "KeyValues");
		sendKeys_custom(txt_keyvalue, "KeyValues", pair);
		waitUntilClickable(btn_saveKeyValues);
		click_custom(btn_saveKeyValues, " Save KeyValues");
		Thread.sleep(1000);
		waitUntilClickable(btn_saveCustomField);
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		
		
		
		return this;

	}
	
	public CustomFieldProjectsPage_EP addRadioCustomField(String UserRole,String methodName,HashMap<String, String> testData,String type) throws InterruptedException

	{

		setupExtentReport("EP: As a User, I should be able to create Radio custom field in Audio "+type+" type project ("+UserRole+")", methodName,UserRole,story.RadioCustomField_EP_695.toString(),"Smoke","EP");
		giveTestRoleInfo(testData,"UserRole");

		String name="Environment";
		String labels="Environment Details";
		String Des="Please select environment of your recording (Using autoscript)";
		String pair="office Office\r\n"
				+ "school School\r\n"
				+ "beach Beach\r\n"
				+ "home Home\r\n"
				+ "busyroad Busyroad\r\n"
				+ "mountains mountains";
		waitUntilClickable(drp_radio);
		click_custom(drp_radio, "Radio Field");
		Thread.sleep(2000);	
		sendKeys_custom(txt_name,"Radio field name",name);
		sendKeys_custom(txt_label,"Radio field name",labels);
		sendKeys_custom(txt_description,"Radio field Description",Des);
		verifySwitchOnStates(btn_Editable,"Is Editable");
		verifySwitchOnStates(btn_Visible,"Is Visible");
		verifySwitchOnStates(btn_Required,"Is Required");
		/*sendKeys_custom(txt_Key,"Radio key","Audio");
		sendKeys_custom(txt_Value,"Radio value","MP3");
		click_custom(btn_addCustomField, "Add custom field");
		Thread.sleep(1000);	*/
		waitUntilClickable(btn_bulkAdd);
		click_custom(btn_bulkAdd, "Bulk Country Add");
		Thread.sleep(1000);
		waitForVisibility(btn_saveKeyValues);
		click_custom(txt_keyvalue, "KeyValues");
		sendKeys_custom(txt_keyvalue, "KeyValues", pair);
		waitUntilClickable(btn_saveKeyValues);
		click_custom(btn_saveKeyValues, " Save KeyValues");
		Thread.sleep(1000);
		waitUntilClickable(btn_saveCustomField);
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		//isCustomFieldAdded(name);
		
		
		return this;

	}
	
	public CustomFieldProjectsPage_EP addRadioCustomField2(String UserRole,String methodName,HashMap<String, String> testData,String type) throws InterruptedException

	{

		

		String name="Gender";
		String labels="Gender Details";
		String Des="Please select your gender(Using autoscript)";
		String pair="male Male\r\n"
				+ "female Female\r\n"
				+ "other Other\r\n";
				
		waitUntilClickable(drp_radio);
		click_custom(drp_radio, "Radio Field");
		Thread.sleep(2000);	
		sendKeys_custom(txt_name,"Radio field name",name);
		sendKeys_custom(txt_label,"Radio field name",labels);
		sendKeys_custom(txt_description,"Radio field Description",Des);
		verifySwitchOnStates(btn_Editable,"Is Editable");
		verifySwitchOnStates(btn_Visible,"Is Visible");
		verifySwitchOnStates(btn_Required,"Is Required");
		/*sendKeys_custom(txt_Key,"Radio key","Audio");
		sendKeys_custom(txt_Value,"Radio value","MP3");
		click_custom(btn_addCustomField, "Add custom field");
		Thread.sleep(1000);	*/
		waitUntilClickable(btn_bulkAdd);
		click_custom(btn_bulkAdd, "Bulk Country Add");
		Thread.sleep(1000);
		waitForVisibility(btn_saveKeyValues);
		click_custom(txt_keyvalue, "KeyValues");
		sendKeys_custom(txt_keyvalue, "KeyValues", pair);
		waitUntilClickable(btn_saveKeyValues);
		click_custom(btn_saveKeyValues, " Save KeyValues");
		Thread.sleep(1000);
		waitUntilClickable(btn_saveCustomField);
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		//isCustomFieldAdded(name);
		
		
		return this;

	}
	
	
	public CustomFieldProjectsPage_EP addTextAreaCustomField(String UserRole,String methodName,HashMap<String, String> testData,String type) throws InterruptedException

	{
		setupExtentReport("EP: As a User, I should be able to create TextArea custom field in Audio "+type+" type project ("+UserRole+")", methodName,UserRole,story.TextAreaCustomField_EP_694.toString(),"Smoke","EP");
		giveTestRoleInfo(testData,"UserRole");

		String name="yourself"+getDate();
		String labels="About Yourself";
		String Des="Explain about yourself (Using autoscript)";
		waitUntilClickable(drp_textarea);
		click_custom(drp_textarea, "TextArea Field");
		Thread.sleep(2000);	
		sendKeys_custom(txt_name,"TextArea field name",name);
		sendKeys_custom(txt_label,"TextArea field name",labels);
		sendKeys_custom(txt_description,"TextArea field Description",Des);
		verifySwitchOnStates(btn_Editable,"Is Editable");
		verifySwitchOnStates(btn_Visible,"Is Visible");
		verifySwitchOnStates(btn_Required,"Is Required");
		//sendKeys_custom(txt_inputDefault,"TextArea Default","");
		sendKeys_custom(txt_minLength,"TextArea min length","2");
		sendKeys_custom(txt_maxLength,"TextArea max length","30");
		Thread.sleep(1000);	
		click_custom(btn_saveCustomField, "Save custom field");
		verifyCustomFieldAddedSuccessToaster();
		//isCustomFieldAdded(name);
		
		
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

	
	
	public CustomFieldProjectsPage_EP isCustomFieldAdded(String addedField) throws InterruptedException

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
	
	
	public CustomFieldProjectsPage_EP clickOnInputType()

	{

		waitUntilClickable(drp_inputType);
		click_custom(drp_inputType, "Type of Custom Field");
		return this;

	}
	
	public CustomFieldProjectsPage_EP clickOnMoreOptions()

	{

		moveToElement_custom(btn_More, "More Options");
		click_custom(btn_More, "More Options");
		return this;

	}
	
	public CustomFieldProjectsPage_EP clickOnSettings()

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
	
	public CustomFieldProjectsPage_EP clickOnCustomFieldTab()

	{

		waitUntilClickable(tab_CustomFieled);
		click_custom(tab_CustomFieled, "Custom field Tab");
		return this;

	}
	
	
	public CustomFieldProjectsPage_EP verifyAddCustomFieledButtonPermissionAccess(String user, String createPermission)
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
	
	public CustomFieldProjectsPage_EP clickOnAddCustomFieldButton()

	{
		waitUntilClickable(btn_addCustom);
		click_custom(btn_addCustom, "Add Custom Field Button");
		waitForVisibility(hdr_addCustomField);
		String projectTitle = getText_custom(hdr_addCustomField, "Add Custom Field Title");
		assert_contains(projectTitle, "Add Custom Field", "Add Custom Field Screen");

		return this;

	}
	
	public CustomFieldProjectsPage_EP addCustomFields(String UserRole,String methodName, HashMap<String, String> testData,String type) throws InterruptedException

	{
		 clickOnAddCustomFieldButton();
	     clickOnInputType();
	     addCheckboxCustomField(UserRole,methodName,testData, type);
	     Thread.sleep(2000);
	     clickOnAddCustomFieldButton();
	     clickOnInputType();
	     addCheckboxCustomField2(UserRole,methodName,testData, type);
	     Thread.sleep(2000);
	     clickOnAddCustomFieldButton();
	     clickOnInputType();
	     addDateCustomField(UserRole,methodName,testData, type);
	     Thread.sleep(2000);
	     clickOnAddCustomFieldButton();
	     clickOnInputType();
	     addDeviceCustomField(UserRole,methodName,testData, type);
	     Thread.sleep(2000);
	     clickOnAddCustomFieldButton();
	     clickOnInputType();
	     addDropdownCustomField(UserRole,methodName,testData, type);
	     Thread.sleep(2000);
	     clickOnAddCustomFieldButton();
	     clickOnInputType();
	     addDropdownCustomField2(UserRole,methodName,testData, type);
	     Thread.sleep(2000);
	     clickOnAddCustomFieldButton();
	     clickOnInputType();
	     addInputCustomField(UserRole,methodName,testData, type);
	     Thread.sleep(2000);
	     clickOnAddCustomFieldButton();
	     clickOnInputType();
	     addInputEmailTypeCustomField(UserRole,methodName,testData, type);
	     Thread.sleep(2000);
	     clickOnAddCustomFieldButton();
	     clickOnInputType();
	     addInputNumberTypeCustomField(UserRole,methodName,testData, type);
	     Thread.sleep(2000);
	     clickOnAddCustomFieldButton();
	     clickOnInputType();
	     addMultiDropdownCustomField(UserRole,methodName,testData, type);
	     Thread.sleep(2000);
	     clickOnAddCustomFieldButton();
	     clickOnInputType();
	     addMultiDropdownCustomField2(UserRole,methodName,testData, type);
	     Thread.sleep(2000);
	     clickOnAddCustomFieldButton();
	     clickOnInputType();
	     addRadioCustomField(UserRole,methodName,testData, type);
	     Thread.sleep(2000);
	     clickOnAddCustomFieldButton();
	     clickOnInputType();
	     addRadioCustomField2(UserRole,methodName,testData, type);
	     Thread.sleep(2000);
	     clickOnAddCustomFieldButton();
	     clickOnInputType();
	     Thread.sleep(2000);
	     addLocationCustomField(UserRole,methodName,testData, type);
	     clickOnAddCustomFieldButton();
	     clickOnInputType();
	     addTextAreaCustomField(UserRole,methodName,testData, type);
	     Thread.sleep(2000);
	     
	     
		return this;

	}
	
	public CustomFieldProjectsPage_EP clickOnHeaderBack() throws InterruptedException

	{

		waitUntilClickable(btn_headerBack);
		click_custom(btn_headerBack,"Back to Project");
		waitForProcessBarToGo();
		Thread.sleep(1000);
		waitUntilClickable(txt_globalSearch);
		return this;

	}

	public CustomFieldProjectsPage_EP verifyDeleteSuccessToaster() throws InterruptedException {

		
		verifyToaster("The custom field have been deleted successfully", "Custom field Toaster");

		return this;

	}
	
	public CustomFieldProjectsPage_EP verifyCustomFieldAddedSuccessToaster() throws InterruptedException {

		
		verifyToaster("The custom field have been added successfully", "Custom field Toaster");

		return this;

	}
	
	
	
}

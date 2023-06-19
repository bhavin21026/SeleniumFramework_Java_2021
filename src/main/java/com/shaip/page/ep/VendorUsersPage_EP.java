package com.shaip.page.ep;

import java.io.IOException;
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
import com.shaip.factories.DriverBase;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.reportng.ExtentFactoryShaip;
import com.shaip.utils.EmailSearcher;

public class VendorUsersPage_EP extends ActionEngineShaip {

	public VendorUsersPage_EP() {

		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	// Users Tab
	@FindBy(xpath = "//div[contains(@class,'mat-tab-label') and contains(text(),'Users')]")
	WebElement tab_users;

	@FindBy(id = "ADD_USER")
	WebElement btn_addVendorUser;

	@FindBy(id = "ADD")
	WebElement btn_addVendorBtn;

	// ***********create
	// users***********************************************************************************************************************************************

	@FindBy(name = "firstName")
	WebElement txt_vendorUserName;

	@FindBy(name = "lastName")
	WebElement txt_vendorUserLastname;

	@FindBy(xpath = "//div[@role='listbox' or @id='mat-select-8-panel']")
	WebElement genderListBox;

	@FindBy(id = "genderName")
	WebElement drp_genderVendor;

	@FindBy(name = "email")
	WebElement txt_vendorUserEmail;

	@FindBy(id = "countryCode")
	WebElement drp_vendorUserCountry;

	@FindBy(id = "roleName")
	WebElement drp_vendorUserRole;

	@FindBy(id = "saveButton")
	WebElement btn_saveVendorUser;

	@FindBy(xpath = "//div[@class='content']//p")
	WebElement toaster_Message;

	@FindBy(xpath = "//mat-icon[text()='clear']")
	WebElement btn_closeToaster;
	
	@FindBy(id = "dtGlobalSearch")
	WebElement txt_globalSearch;

	@FindBy(css = "table > tbody > tr > td:nth-child(2)")
	WebElement searchedEntity;
	
	@FindBy(tagName = "h2")
	WebElement customerHeader;
	
	@FindBy(id = "editUser")
	WebElement btn_editVendor;
	
	@FindBy(xpath = "//td[3]")
	WebElement lbl_firstName;
	
	@FindBy(xpath = "//td[4]")
	WebElement lbl_lastName;

	@FindBy(name = "deActivate")
	WebElement btn_deactivateUser;
	
	@FindBy(name = "activeUser")
	WebElement btn_activateUser;
	
	@FindBy(name = "cdConfirmButton")
	WebElement btn_confirm;

	
	
	public VendorUsersPage_EP deactivateUser() throws InterruptedException

	{
		Thread.sleep(1000);
		moveToElement_custom(btn_deactivateUser, "btn_deactivateUser");
		waitUntilClickable(btn_deactivateUser);
		click_custom(btn_deactivateUser, "btn_deactivateUser");
		waitUntilClickable(btn_confirm);
		click_custom(btn_confirm, "Deactivate User");
		waitForDeactivatedToasterMessage();
		return this;

	}
	
	public VendorUsersPage_EP activateUser() throws InterruptedException

	{
		Thread.sleep(1000);
		moveToElement_custom(btn_activateUser, "btn_activateUser");
		waitUntilClickable(btn_activateUser);
		click_custom(btn_activateUser, "btn_activateUser");
		waitForActivatedToasterMessage();
		return this;


	}
	
	public VendorUsersPage_EP waitForDeactivatedToasterMessage() throws InterruptedException {

		
		
		verifyToaster("The user(s) have been deactivated successfully","User Deactivated Toaster");
		return this;

	}
	
	public VendorUsersPage_EP waitForActivatedToasterMessage() throws InterruptedException {

		
		
		verifyToaster("The user(s) have been activated successfully","User Activated Toaster");
		return this;

	}
	
	public VendorUsersPage_EP searchVendorUser(String searchEntity) throws InterruptedException

	{
		waitUntilClickable(txt_globalSearch);
		clearDrpdownText(txt_globalSearch);
		Thread.sleep(1000);
		click_custom(txt_globalSearch, searchEntity);
		sendKeys_custom(txt_globalSearch, "Global Search", searchEntity);
		Thread.sleep(3000);
		assert_contains(getText_custom(lbl_firstName, "Vendor user FN"), searchEntity, " Vendor User");

		return this;
	}
	
	
	
	
	
	public VendorUsersPage_EP editUSerButton()

	{
		moveToElement_custom(btn_editVendor, "btn_editVendor");
		waitUntilClickable(btn_editVendor);
		click_custom(btn_editVendor, "btn_editVendor");
		return this;

	}

	public VendorUsersPage_EP clickOnUsersTab()

	{
		waitUntilClickable(tab_users);
		click_custom(tab_users, "Users Tab");
		return this;

	}

	public VendorUsersPage_EP clickAddUserMenu() throws InterruptedException

	{
		clickOnAddButton();
		waitUntilClickable(btn_addVendorUser);
		click_custom(btn_addVendorUser, "Add Users");
		Thread.sleep(2000);
		return this;

	}

	private VendorUsersPage_EP clickOnAddButton() {

		waitUntilClickable(btn_addVendorBtn);
		click_custom(btn_addVendorBtn, "Add vendor Button");
		return this;

	}

	

	public void enterFirstName(String firstName)

	{

		click_custom(txt_vendorUserName, "First Name");
		sendKeys_custom(txt_vendorUserName, "First Name", firstName);

	}

	public void enterLastName(String lastname)

	{

		click_custom(txt_vendorUserLastname, "Last Name");
		sendKeys_custom(txt_vendorUserLastname, "Last Name", lastname);

	}

	public void enterEmaiID(String email)

	{

		click_custom(txt_vendorUserEmail, "Email");
		sendKeys_custom(txt_vendorUserEmail, "Email", email);

	}

	public void clickOnSaveUserBtn()

	{

		click_custom(btn_saveVendorUser, "Save User");

	}

	public VendorUsersPage_EP selectUsersCountry(String countryName) throws InterruptedException

	{
		Thread.sleep(1000);
		click_custom(drp_vendorUserCountry, "User Country Dropdown");
		sendKeys_custom(drp_vendorUserCountry, "User Country Dropdown", countryName);
		Thread.sleep(1000);
		List<WebElement> countryToBeSelected = DriverFactoryShaip.getDriver()
				.findElements(By.xpath("//span[@class='mat-option-text']"));
		// System.out.println(countryToBeSelected.size());

		Thread.sleep(1000);

		for (int i = 0; i < countryToBeSelected.size(); i++) {

			String country = getText_custom(countryToBeSelected.get(i), "selectUsersCountry");

			if (country.equalsIgnoreCase(countryName))

			{
				click_custom(countryToBeSelected.get(i), countryName);
				break;
			}

		}
		return this;

	}

	public void selectUsersRole(String roleName) throws InterruptedException

	{
		Thread.sleep(1000);
		click_custom(drp_vendorUserRole, "User Country Dropdown");
		List<WebElement> roleToBeSelected = DriverFactoryShaip.getDriver()
				.findElements(By.xpath("//span[@class='mat-option-text']"));
		// System.out.println(countryToBeSelected.size());

		Thread.sleep(1000);

		for (int i = 0; i < roleToBeSelected.size(); i++) {

			String role = getText_custom(roleToBeSelected.get(i), "select role");
			if (role.equalsIgnoreCase(roleName))

			{
				click_custom(roleToBeSelected.get(i), roleName);
				break;
			}

		}

	}

	public void selectGender(String gender) throws InterruptedException

	{

		click_custom(drp_genderVendor, "Gender Dropdown");
		Thread.sleep(1000);
		List<WebElement> genderToBeSelected = DriverFactoryShaip.getDriver()
				.findElements(By.xpath("//span[@class='mat-option-text']"));
		// System.out.println(countryToBeSelected.size());

		for (int i = 0; i < genderToBeSelected.size(); i++) {

			String genders = getText_custom(genderToBeSelected.get(i), "select Gender");

			if (genders.equalsIgnoreCase(gender))

			{
				click_custom(genderToBeSelected.get(i), gender);
				break;
			}

		}

	}
	
	public VendorUsersPage_EP addNewVendorUserForSelectedCustomerOrganization(ITestContext context, String user,
			String permission) throws InterruptedException

	{

		String name= "Auto_"+getDate();
		String lname="VendorrUser";
		String email=generateNewEmail();
		context.setAttribute("vendorUserName",name);
		context.setAttribute("Email",email);

		enterFirstName(name);
		enterLastName(lname);
		selectGender("Male");
		enterEmaiID(email);
		selectUsersCountry("India");
		selectUsersRole("Vendor Administrator");
		clickOnSaveUserBtn();
		return this;

	}
	
	public void editAndVerifyVendorUserFromListingPage(ITestContext context,String user,String permission) throws InterruptedException

	{

		String updatedName="QA_"+getDate();
		String updatedLast="VendorUser";
		String updatedCouD="Algeria";
		context.setAttribute("vendorUserNameList",updatedName);
		String searchEntity=(String) context.getAttribute("vendorUserName");
		
		
		waitUntilClickable(txt_globalSearch);
		click_custom(txt_globalSearch, searchEntity);
		sendKeys_custom(txt_globalSearch, "Global Search", searchEntity);
		Thread.sleep(2000);
		editUSerButton();
		//waitForProcessBarToGo();
		updateExistingVendorUser(updatedName, updatedLast, updatedCouD);
		Thread.sleep(2000);
		verifyUpdatedCustomerInformation(updatedName,updatedLast,context);

		
		

	}
	
	
	public void updateExistingVendorUser(String Name, String lastname, String countryName)
			throws InterruptedException

	{
		
		waitForVisibility(txt_vendorUserName);
		clearText(txt_vendorUserName);
		enterFirstName(Name);
		clearText(txt_vendorUserLastname);
		enterLastName(lastname);
		clearDrpdownText(drp_vendorUserCountry);
		selectUsersCountry(countryName);
		waitUntilClickable(btn_saveVendorUser);
		clickOnSaveUserBtn();
		waitForVendorUserUpdatedToasterMessage();

	}
	
	
	public void verifyUpdatedCustomerInformation(String updatedName, String updatedLast,ITestContext context) throws InterruptedException

	{
			searchVendorUser((String) context.getAttribute("vendorUserNameList"));
			moveToElement_custom(lbl_firstName, "First Name");
			assert_contains(getText_custom(lbl_firstName, "Vendor user FN"), updatedName, "Edit Vendor User");
			assert_contains(getText_custom(lbl_lastName, "Vendor user LN"), updatedLast, "Edit Vendor User");
	}
	
	public VendorUsersPage_EP verifyInvitationEmailAndSignIn(ITestContext context, String UserRole, String methodName, HashMap<String, String> testData) throws InterruptedException, IOException {

		setupExtentReport("Verify whether an email notification is sent to the email addresses provided by the user while creating the Vendor user from the listing page ("+UserRole+")", methodName,UserRole,story.EmailNotification_EP_135.toString(),"Smoke","EP");
		giveTestRoleInfo(testData,"UserRole");
		String getPassword = EmailSearcher.checkForInvitationMail();
		context.setAttribute("Password",getPassword);
		doLoginWithEmailPassword(context,UserRole,methodName,testData);
	
		return this;


	}
	
	public VendorUsersPage_EP doLoginWithEmailPassword(ITestContext context, String UserRole, String methodName, HashMap<String, String> testData) throws InterruptedException, IOException {

		setupExtentReport("Verify whether  Vendor contributor/Administrator user can login with password got from invitation email ("+UserRole+")", methodName,UserRole,story.EmailNotification_EP_135.toString(),"Smoke","EP");
		giveTestRoleInfo(testData,"UserRole");
		
		new LandingPage_EP()
		.clickOnProfileMenu()
		.clickOnSignOut()
		.isLogoutSuccessfull("EP");
		new LoginPage_EP()
		.navigateToEP("urlEP")
		.loginToEP((String) context.getAttribute("Email"),(String) context.getAttribute("Password"))
		.verifyShaipURI();

		return this;


	}
	

	public VendorUsersPage_EP waitForVendorUserCreatedSuccessToasterMessage() throws InterruptedException {

		
		verifyToaster("The user has been created successfully","Toaster message getting displayed");
		return this;

	}

	public VendorUsersPage_EP waitForVendorUserUpdatedToasterMessage() throws InterruptedException {

		
		verifyToaster("The user has been updated successfully","Toaster message getting displayed");
		return this;


	}

}

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

public class CustomerUsersPage_EP extends ActionEngineShaip {

	public CustomerUsersPage_EP() {

		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	// Users Tab
	@FindBy(xpath = "//div[contains(@class,'mat-tab-label') and contains(text(),'Users')]")
	WebElement tab_users;

	@FindBy(id = "ADD_USER")
	WebElement btn_addCustomerUser;

	@FindBy(id = "ADD")
	WebElement btn_addCustomerBtn;

	// ***********create
	// users***********************************************************************************************************************************************

	@FindBy(name = "firstName")
	WebElement txt_customerUserName;

	@FindBy(name = "lastName")
	WebElement txt_customerUserLastname;

	@FindBy(xpath = "//div[@role='listbox' or @id='mat-select-8-panel']")
	WebElement genderListBox;

	@FindBy(id = "genderName")
	WebElement drp_genderCustomer;

	@FindBy(name = "email")
	WebElement txt_customerUserEmail;

	@FindBy(id = "countryCode")
	WebElement drp_customerUserCountry;

	@FindBy(id = "roleName")
	WebElement drp_customerUserRole;

	@FindBy(id = "saveButton")
	WebElement btn_saveCustomerUser;

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
	WebElement btn_editUser;
	
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

	
	
	public CustomerUsersPage_EP deactivateUser() throws InterruptedException

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
	
	public CustomerUsersPage_EP activateUser() throws InterruptedException

	{
		Thread.sleep(1000);
		moveToElement_custom(btn_activateUser, "btn_activateUser");
		waitUntilClickable(btn_activateUser);
		click_custom(btn_activateUser, "btn_activateUser");
		waitForActivatedToasterMessage();
		return this;


	}
	
	public CustomerUsersPage_EP waitForDeactivatedToasterMessage() throws InterruptedException {

		
		
		verifyToaster("The user(s) have been deactivated successfully","User Deactivated Toaster");
		return this;

	}
	
	public CustomerUsersPage_EP waitForActivatedToasterMessage() throws InterruptedException {

		
		
		verifyToaster("The user(s) have been activated successfully","User Activated Toaster");
		return this;

	}

	
	
	public CustomerUsersPage_EP clickOnUsersTab()

	{
		waitUntilClickable(tab_users);
		click_custom(tab_users, "Users Tab");
		return this;

	}
	
	public CustomerUsersPage_EP editUSerButton()

	{
		moveToElement_custom(btn_editUser, "btn_editUser");
		waitUntilClickable(btn_editUser);
		click_custom(btn_editUser, "btn_editUser");
		return this;

	}
	
	

	public CustomerUsersPage_EP clickAddUserMenu() throws InterruptedException

	{
		clickOnAddButton();
		waitUntilClickable(btn_addCustomerUser);
		click_custom(btn_addCustomerUser, "Add Users");
		Thread.sleep(2000);
		return this;

	}

	private CustomerUsersPage_EP clickOnAddButton() {

		waitUntilClickable(btn_addCustomerBtn);
		click_custom(btn_addCustomerBtn, "Add Customer Button");
		return this;

	}

	
	public void enterFirstName(String firstName)

	{

		click_custom(txt_customerUserName, "First Name");
		sendKeys_custom(txt_customerUserName, "First Name", firstName);

	}

	public void enterLastName(String lastname)

	{

		click_custom(txt_customerUserLastname, "Last Name");
		sendKeys_custom(txt_customerUserLastname, "Last Name", lastname);

	}

	public void enterEmaiID(String email)

	{

		click_custom(txt_customerUserEmail, "Email");
		sendKeys_custom(txt_customerUserEmail, "Email", email);

	}

	public void clickOnSaveUserBtn()

	{

		click_custom(btn_saveCustomerUser, "Save User");

	}

	public CustomerUsersPage_EP selectUsersCountry(String countryName) throws InterruptedException

	{
		Thread.sleep(1000);
		click_custom(drp_customerUserCountry, "User Country Dropdown");
		sendKeys_custom(drp_customerUserCountry, "User Country Dropdown", countryName);
		Thread.sleep(1000);
		List<WebElement> countryToBeSelected = DriverFactoryShaip.getDriver()
				.findElements(By.xpath("//span[@class='mat-option-text']"));
		// System.out.println(countryToBeSelected.size());

		Thread.sleep(1000);

		for (int i = 0; i < countryToBeSelected.size(); i++) {

			String country =getText_custom(countryToBeSelected.get(i), "selectUsersCountry");
					

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
		click_custom(drp_customerUserRole, "User Country Dropdown");
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

		click_custom(drp_genderCustomer, "Gender Dropdown");
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
	
	
	public CustomerUsersPage_EP searchCustomerUser(String searchEntity) throws InterruptedException

	{
		waitUntilClickable(txt_globalSearch);
		clearDrpdownText(txt_globalSearch);
		Thread.sleep(1000);
		click_custom(txt_globalSearch, searchEntity);
		sendKeys_custom(txt_globalSearch, "Global Search", searchEntity);
		Thread.sleep(3000);
		assert_contains(getText_custom(lbl_firstName, "Customer user FN"), searchEntity, " customer User");
		return this;
	}
	
	
	
	public CustomerUsersPage_EP addNewCustomerUserForSelectedCustomerOrganization(ITestContext context) throws InterruptedException

	{
		

		String name= "Auto_"+getDate();
		String lname="CustomerUser";
		String email=generateNewEmail();
		context.setAttribute("customerUserName",name);
		context.setAttribute("Email",email);


		
		enterFirstName(name);
		enterLastName(lname);
		selectGender("Male");
		enterEmaiID(email);
		selectUsersCountry("India");
		selectUsersRole("Customer Administrator");
		clickOnSaveUserBtn();
		return this;

	}

	
	public void editAndVerifyCustomerUserFromListingPage(ITestContext context,String user,String permission) throws InterruptedException

	{

		String updatedName="QA_"+getDate();
		String updatedLast="CustomerUser";
		String updatedCouD="Algeria";
		context.setAttribute("customerUserNameList",updatedName);

		String searchEntity=(String) context.getAttribute("customerUserName");
		
		
		waitUntilClickable(txt_globalSearch);
		click_custom(txt_globalSearch, searchEntity);
		sendKeys_custom(txt_globalSearch, "Global Search", searchEntity);
		Thread.sleep(2000);
		editUSerButton();
		//waitForProcessBarToGo();
		updateExistingCustomerUser(updatedName, updatedLast, updatedCouD);
		Thread.sleep(2000);
		verifyUpdatedCustomerInformation(updatedName,updatedLast,context);
		
		

	}
	
	public void verifyUpdatedCustomerInformation(String updatedName, String updatedLast,ITestContext context) throws InterruptedException

	{
			searchCustomerUser((String) context.getAttribute("customerUserNameList"));
			moveToElement_custom(lbl_firstName, "First Name");
			assert_contains(getText_custom(lbl_firstName, "Customer user FN"), updatedName, "Edit customer User");
			assert_contains(getText_custom(lbl_lastName, "Customer user LN"), updatedLast, "Edit customer User");
	}
	
	public void updateExistingCustomerUser(String Name, String lastname, String countryName)
			throws InterruptedException

	{
		
		waitForVisibility(txt_customerUserName);
		clearText(txt_customerUserName);
		enterFirstName(Name);
		clearText(txt_customerUserLastname);
		enterLastName(lastname);
		clearDrpdownText(drp_customerUserCountry);
		selectUsersCountry(countryName);
		waitUntilClickable(btn_saveCustomerUser);
		clickOnSaveUserBtn();
		waitForCustomerUserUpdatedToasterMessage();

	}
	
	public CustomerUsersPage_EP verifyInvitationEmailAndSignIn(ITestContext context, String UserRole, String methodName, HashMap<String, String> testData) throws InterruptedException, IOException {

		setupExtentReport("EP: Verify whether an email notification is sent to the email addresses provided by the user while creating the Customer user from the listing page ("+UserRole+")", methodName,UserRole,story.EmailNotification_EP_135.toString(),"Smoke","EP");
		giveTestRoleInfo(testData,"UserRole");
		String getPassword = EmailSearcher.checkForInvitationMail();
		System.out.println("Got password from email--->"+getPassword);
		context.setAttribute("Password",getPassword);
		doLoginWithEmailPassword(context,UserRole,methodName,testData);
	
		return this;


	}
	
	public CustomerUsersPage_EP doLoginWithEmailPassword(ITestContext context, String UserRole, String methodName, HashMap<String, String> testData) throws InterruptedException, IOException {

		setupExtentReport("EP: Verify whether customer contributor/Administrator user can login with password got from invitation email ("+UserRole+")", methodName,UserRole,story.EmailNotification_EP_135.toString(),"Smoke","EP");
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
	
	public CustomerUsersPage_EP verifyLoginWithSentPassword(ITestContext context) throws InterruptedException, IOException {

		String getPassword = EmailSearcher.checkForInvitationMail();
		context.setAttribute("password",getPassword);

		return this;


	}


	
	
	public CustomerUsersPage_EP waitForCustomerUserCreatedSuccessToasterMessage() throws InterruptedException {

		verifyToaster("The user has been created successfully","Toaster message getting displayed");
		return this;


	}

	public CustomerUsersPage_EP waitForCustomerUserUpdatedToasterMessage() throws InterruptedException {

		
		
		verifyToaster("The user has been updated successfully","Toaster message getting displayed");
		return this;

	}
	
	
	/*public CustomerUsersPage_EP createBulkCustomerUsers(int count,ITestContext context,String role, String methodname,
			HashMap<String, String> testData) throws InterruptedException, IOException {
	
			
		
		clickAddUserMenu();
		addNewCustomerUserForSelectedCustomerOrganization(context);
		waitForCustomerUserCreatedSuccessToasterMessage();
	    verifyInvitationEmailAndSignIn(context,role,methodname,testData);
			
		return this;
	
		}*/

}

package com.shaip.page.ep;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.shaip.base.ActionEngineShaip;
import com.shaip.factories.DriverFactoryShaip;

public class UsersPage_EP extends ActionEngineShaip {
	
	
	
	public UsersPage_EP() {

		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	// Users Tab
	@FindBy(id = "ADD")
	WebElement btn_addUserBtn;
	
	@FindBy(id = "ADD_USER")
	WebElement btn_addUser;
	
	@FindBy(id = "BULK_UPLOAD")
	WebElement btn_addBulkUsers;
	
	@FindBy(id = "file")
	WebElement btn_uploadFiles;
	
	@FindBy(id = "uploadButton")
	WebElement btn_doUpload;
	

	public UsersPage_EP clickOnAddButton() {

		waitUntilClickable(btn_addUserBtn);
		click_custom(btn_addUserBtn, "Add user Button");
		return this;

	}
	
	public UsersPage_EP clickAddUserMenu() throws InterruptedException

	{
		clickOnAddButton();
		waitUntilClickable(btn_addUserBtn);
		click_custom(btn_addUserBtn, "Add user menu");
		Thread.sleep(2000);
		return this;

	}
	
	
	public UsersPage_EP clickAddBulkUserMenu() throws InterruptedException

	{
		Thread.sleep(1000);
		waitUntilClickable(btn_addBulkUsers);
		click_custom(btn_addBulkUsers, "Add Bulk users");
		Thread.sleep(2000);
		return this;

	}
	
	public UsersPage_EP uploadBulkUserFile() throws InterruptedException

	{
		btn_uploadFiles.sendKeys("C:\\Users\\Bhavin Sangani\\Downloads\\autoupload.xlsx");
		Thread.sleep(2000);
		waitUntilClickable(btn_doUpload);
		click_custom(btn_doUpload, "Upload bulk user");
		waitForProcessBarToGo();
		waitForUsersCreatedSuccessToasterMessage();
		return this;

	}
	
	public UsersPage_EP waitForUsersCreatedSuccessToasterMessage() throws InterruptedException {

		verifyToaster("The user(s) have been created successfully","Toaster message getting displayed");
		return this;


	}


}

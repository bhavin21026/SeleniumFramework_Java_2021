package com.shaip.page.cp;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.shaip.base.ActionEngineShaip;
import com.shaip.enums.story;
import com.shaip.factories.DriverBase;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.reportng.ExtentFactoryShaip;

public class NotificationsPage_CP extends ActionEngineShaip {

	public NotificationsPage_CP() {

		wait = new WebDriverWait(DriverFactoryShaip.getDriver(), Duration.ofSeconds(10));
		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	
	//help menus
	
	@FindBy(id = "DELETE_ALL")
	WebElement btn_deleteAll;
	
	@FindBy(name = "cdConfirmButton")
	WebElement btn_confirmDeleteAll;
	

	@FindBy(xpath = "//mat-dialog-content[@class='mat-dialog-content']")
	WebElement lbl_confirmDelete;
	
	
	@FindBy(id = "ACTIONS")
	WebElement btn_actions;

	@FindBy(xpath = "//mat-icon[@svgicon='delete'or @mattooltip='Delete']")
	WebElement btn_deleteNotification;
	
	@FindBy(xpath = "//mat-icon[@svgicon='mail_read'or @mattooltip='Mark as read']")
	WebElement btn_readNotification;
	
	@FindBy(xpath = "//mat-icon[@svgicon='mail_unread'or @mattooltip='Mark as unread']")
	WebElement btn_unreadNotification;

	@FindBy(xpath = "//td[2]//span")
	WebElement lbl_type;
	
	@FindBy(xpath = "//td[3]")
	WebElement lbl_notification;
	
	@FindBy(xpath = "//td[4]")
	WebElement lbl_module;
	
	@FindBy(xpath = "//div[@class='content']//p")
	WebElement toaster_Message;

	@FindBy(xpath = "//mat-icon[text()='clear']")
	WebElement btn_closeToaster;

	
	@FindBy(xpath = "//mat-list-item[@id='notifications']//span")
	WebElement lbl_notificationCount;

	
	
	public NotificationsPage_CP selectActions() throws InterruptedException
	{
		
		click_custom(btn_actions, "Action Menu Notification");
		Thread.sleep(1000);
		waitUntilClickable(btn_deleteAll);	
		return this;
		
	}
	
	
	public LandingPage_CP deleteAllNotification(String Type) throws InterruptedException
	{
		setupExtentReport("CP: Verify 'Delete All' functionality  "+Type+ " notifications",
				"Delete All Notifications", "General", story.Notifications_CP__32.toString(), "SIT", "CP");
		selectActions();
		waitUntilClickable(btn_deleteAll);
		click_custom(btn_deleteAll, "Delete All notification");
		Thread.sleep(1000);
		waitUntilClickable(btn_confirmDeleteAll);	
		assert_contains(getText_custom(lbl_confirmDelete, "Confirm delete pop up"),"Do you really want to delete all notifications?", "Confirm delete pop up");
		click_custom(btn_confirmDeleteAll, "Confirm Delete All notification");	
		verifyDeleteAllNotificationToaster();
		return new LandingPage_CP();
	}
	
	
	public NotificationsPage_CP verifyChangedPasswordNotification() throws InterruptedException
	{
		
		waitUntilClickable(btn_deleteNotification);
		assert_custom(getText_custom(lbl_type, "Type"), "Warning", "Notification Type");
		assert_custom(getText_custom(lbl_notification, "Notification detail"), "Your password has been changed", "Notification Details");
		assert_custom(getText_custom(lbl_module, "Notification Module"), "Account", "Notification Module");
		assert_custom(getText_custom(lbl_notificationCount, "Notification Count"), "1", "Notification Count");

		return this;
	}
	
	public NotificationsPage_CP verifyResetPasswordNotification() throws InterruptedException
	{
		
		waitUntilClickable(btn_deleteNotification);
		assert_custom(getText_custom(lbl_type, "Type"), "Warning", "Notification Type");
		assert_custom(getText_custom(lbl_notification, "Notification detail"), "Your password has been reset", "Notification Details");
		assert_custom(getText_custom(lbl_module, "Notification Module"), "Account", "Notification Module");
		assert_custom(getText_custom(lbl_notificationCount, "Notification Count"), "1", "Notification Count");

		return this;
	}
	
	
	public NotificationsPage_CP verifyUnreadNotificationCount() throws InterruptedException
	{
		
		waitUntilClickable(btn_deleteNotification);
		assert_custom(getText_custom(lbl_notificationCount, "Notification Count"), "1", "Notification Count");

		return this;
	}
	
	public NotificationsPage_CP verifyReadNotificationCount() throws InterruptedException
	{
		
		waitUntilClickable(btn_deleteNotification);
		assert_custom(getText_custom(lbl_notificationCount, "Notification Count"), "", "Notification Count");

		return this;
	}
	
	public NotificationsPage_CP verifyMarkAsReadNotification(String Type) throws InterruptedException
	{
		
		setupExtentReport("CP: Verify 'Mark As Read' functionality for "+Type+ " notifications",
				"Mark As Read", "General", story.Notifications_CP__32.toString(), "SIT", "CP");
		waitForVisibility(btn_readNotification);
		waitUntilClickable(btn_readNotification);
		click_custom(btn_readNotification, "Read Notification");
		verifyNotificationMarkAsReadToaster();
		
		return this;
	}
	
	public NotificationsPage_CP verifyMarkAsUnReadNotification(String Type) throws InterruptedException
	{
		setupExtentReport("CP: Verify 'Mark As UnRead' functionality "+Type+" notifications",
				"Mark As UnRead", "General", story.Notifications_CP__32.toString(), "SIT", "CP");
		waitForVisibility(btn_unreadNotification);
		waitUntilClickable(btn_unreadNotification);
		click_custom(btn_unreadNotification, "UnRead Notification");
		verifyNotificationMarkAsUnReadToaster();
		return this;
	}
	
	
	
public NotificationsPage_CP verifyDeleteAllNotificationToaster() throws InterruptedException {

	
		
		verifyToaster("The notification(s) have been deleted successfully",
				"Notification Toaster");
		return this;
		

	}

public NotificationsPage_CP verifyDeleteNotificationToaster() throws InterruptedException {

	
	
	verifyToaster("The notification has been deleted successfully",
			"Notification Toaster");
	return this;
	

}

public NotificationsPage_CP verifyNotificationMarkAsReadToaster() throws InterruptedException {

	
	
		verifyToaster("The notification has been marked as read successfully",
				"Notification Toaster");
	return this;
	

}
public NotificationsPage_CP verifyNotificationMarkAsUnReadToaster() throws InterruptedException {

	
	
	verifyToaster("The notification has been marked as unread successfully",
			"Notification Toaster");
	return this;
	

}
	
	
}

	
	
	

	

	

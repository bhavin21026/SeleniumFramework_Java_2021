package com.shaip.page.ep;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.thymeleaf.context.IContext;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.shaip.base.ActionEngineShaip;
import com.shaip.enums.ProjectType;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.factories.ProjectTypeFactory;
import com.shaip.reportng.ExtentFactoryShaip;

public class TeamMemberPage_EP extends ActionEngineShaip {

	public TeamMemberPage_EP() {

		
		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	// Project elements

	@FindBy(id = "teams")
	private WebElement lnk_teams;

	@FindBy(id = "add")
	private WebElement btn_addTeamMembers;
	
	@FindBy(xpath = "//div[contains(@class,'mat-dialog-title')]")
	WebElement hdr_addTeamMember;

	@FindBy(xpath = "//div[@class='content']//p")
	private WebElement toaster_Message;

	@FindBy(xpath = "//mat-icon[text()='clear']")
	private WebElement btn_closeToaster;

	@FindBy(id = "dtGlobalSearch")
	private WebElement txt_globalSearch;

	@FindBy(xpath = "(//div[@role='checkbox' and contains(@class,'p-checkbox-box p-component')])")
	private WebElement chk_members;
	
	@FindBy(xpath = "((//div[@role='checkbox' and @class='p-checkbox-box']))[2]//span")
	private WebElement chk_allMembers;

	@FindBy(id = "saveAMTWButton")
	private WebElement btn_assignMembers;

	@FindBy(id = "removeTeamMember")
	private WebElement btn_removeTeamMember;

	@FindBy(id = "cdConfirmButton")
	private WebElement btn_conRemoveTeamMember;
	
	@FindBy(id = "headerBackIcon")
	private WebElement btn_headerBack;
	
	
	

	public TeamMemberPage_EP clickOnRemoveMember() throws InterruptedException

	{

		waitUntilClickable(btn_removeTeamMember);
		Thread.sleep(1000);
		click_custom(btn_removeTeamMember, "Remove");
		waitUntilClickable(btn_conRemoveTeamMember);
		click_custom(btn_conRemoveTeamMember, "Confirm Remove");
		return this;

	}

	public TeamMemberPage_EP clickOnTeams() throws InterruptedException

	{

		waitUntilClickable(lnk_teams);
		Thread.sleep(1000);
		click_custom(lnk_teams, "Teams");
		waitForProcessBarToGo();
		waitUntilClickable(btn_addTeamMembers);
		return this;

	}

	public TeamMemberPage_EP clickOnSave() throws InterruptedException

	{

		waitUntilClickable(btn_assignMembers);
		click_custom(btn_assignMembers, "Save/assign Members");
		// waitForProcessBarToGo();
		Thread.sleep(1000);
		return this;

	}

	
	
	public TeamMemberPage_EP clickOnAddMembersButton() throws InterruptedException

	{
		waitUntilClickable(btn_addTeamMembers);
		click_custom(btn_addTeamMembers, "Add team member Button");
		waitForVisibility(hdr_addTeamMember);
		String projectTitle = getText_custom(hdr_addTeamMember, "Add Team Members");
		assert_contains(projectTitle, "Add Team Members", "Add Project Screen");
		waitForProcessBarToGo();
		waitUntilClickable(txt_globalSearch);

		return this;

	}

	public TeamMemberPage_EP searchMember(String searchEntity) throws InterruptedException

	{

		waitUntilClickable(txt_globalSearch);
		sendKeys_custom(txt_globalSearch, "Global Search", searchEntity);
		waitForProcessBarToGo();
		Thread.sleep(2000);
		return this;

	}

	public TeamMemberPage_EP removeMember() throws InterruptedException

	{

		clickOnRemoveMember();
		verifyRemoveTeamMemberSuccessToaster();
		return this;

	}

	public TeamMemberPage_EP assignMembers(ITestContext context) throws InterruptedException

	{

		List<WebElement> workflowSteps = DriverFactoryShaip.getDriver()
				.findElements(By.xpath("(//div[@role='tab']//span)"));
		String tabName = null;
		for (int i = 0; i < workflowSteps.size(); i++) {

			tabName = getText_custom(workflowSteps.get(i), "tabName");
			click_custom(workflowSteps.get(i), tabName);
			if (i > 0)
			{
				waitForProcessBarToGo();
			}
			if (tabName.equalsIgnoreCase("Collect")) {
				clickOnAddMembersButton();
				searchMember("automation.shaip+Validate@shaip.com");
				context.setAttribute("member", "automation.shaip+Validate@shaip.com");
			} else if (tabName.equalsIgnoreCase("QA")) {
				clickOnAddMembersButton();
				searchMember("automation.shaip+qa2@shaip.com");
				context.setAttribute("member", "automation.shaip+qa2@shaip.com");

			} else if (tabName.equalsIgnoreCase("Annotate")) {
				clickOnAddMembersButton();
				searchMember("automation.shaip+Annotate@shaip.com");
				context.setAttribute("member", "automation.shaip+Annotate@shaip.com");
			} else if (tabName.equalsIgnoreCase("CQA")) {
				clickOnAddMembersButton();
				searchMember("automation.shaip+Complete@shaip.com");
				context.setAttribute("member", "automation.shaip+Complete@shaip.com");
			}

			click_custom(chk_members, "Member checkbox");
			clickOnSave();
			verifyAssignMemberSuccessToaster();
			verifiedAsssignedMember(context.getAttribute("member").toString());
		}
		return this;

	}
	
	public TeamMemberPage_EP assignProject(ITestContext context,String searchEnity) throws InterruptedException

	{

		List<WebElement> workflowSteps = DriverFactoryShaip.getDriver()
				.findElements(By.xpath("(//div[@role='tab']//span)"));
		String tabName = null;
		for (int i = 0; i <=0; i++) {

			tabName = getText_custom(workflowSteps.get(i), "tabName");
			click_custom(workflowSteps.get(i), tabName);
			if (i > 0)
			{
				waitForProcessBarToGo();
			}
			if (tabName.equalsIgnoreCase("Collect")) {
				clickOnAddMembersButton();
				searchMember(searchEnity);
				Thread.sleep(2000);
				//context.setAttribute("member", "automation.shaip+Validate@shaip.com");
			} 

			click_custom(chk_allMembers, "Select all Member checkbox");
			clickOnSave();
			verifyAssignMemberSuccessToaster();
		}
		return this;

	}
	
	public TeamMemberPage_EP clickOnHeaderBack() throws InterruptedException

	{

		waitUntilClickable(btn_headerBack);
		click_custom(btn_headerBack,"Back to Project");
		waitForProcessBarToGo();
		Thread.sleep(1000);
		waitUntilClickable(txt_globalSearch);
		return this;

	}

	public TeamMemberPage_EP verifiedAsssignedMember(String searchEntity) throws InterruptedException

	{

		Thread.sleep(2000);
		if (DriverFactoryShaip.getDriver().findElements(By.xpath("//td[contains(text(),'" + searchEntity + "')]"))
				.size() != 0) {

			WebElement workflowSteps = DriverFactoryShaip.getDriver()
					.findElement(By.xpath("//td[contains(text(),'" + searchEntity + "')]"));
			if (workflowSteps.isDisplayed()) {

				ExtentFactoryShaip.getTest().log(Status.PASS,
						" Team Member assigned successfully" + " " + searchEntity);
			}
		} else {
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL, " Team Member has not been assigned" + " " + searchEntity);

		}
		// td[contains(text(),'QA')]/following-sibling::td[5]//button[@id='editBtn']

		return this;

	}

	public TeamMemberPage_EP verifyAddedWorkflowStepDetails(ITestContext context, String attribute)
			throws InterruptedException

	{
		String name = context.getAttribute(attribute).toString();

		if (DriverFactoryShaip.getDriver().findElements(By.xpath("//td[contains(text(),'" + name + "')]"))
				.size() != 0) {

			WebElement workflowSteps = DriverFactoryShaip.getDriver()
					.findElement(By.xpath("//td[contains(text(),'" + name + "')]"));
			if (workflowSteps.isDisplayed()) {

				ExtentFactoryShaip.getTest().log(Status.PASS, "Workflow step has  been created/updated successfully"
						+ " " + context.getAttribute(attribute).toString());
			}
		} else {
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL,
					"Workflow step has not been created/updated" + " " + context.getAttribute("stepsName").toString());

		}
		// td[contains(text(),'QA')]/following-sibling::td[5]//button[@id='editBtn']

		return this;

	}

	public TeamMemberPage_EP verifyAssignMemberSuccessToaster() throws InterruptedException {

		
		verifyToaster( "The member(s) have been added successfully", "Team member Toaster");

		return this;

	}

	public TeamMemberPage_EP verifyRemoveTeamMemberSuccessToaster() throws InterruptedException {

		
		verifyToaster("The members(s) have been removed successfully", "Team member Toaster");

		return this;

	}
	
	public  TeamMemberPage_EP verifyAssignTeamMemberPermissionAccess(String user, String createPermission)
			throws InterruptedException

	{

		if (createPermission.equalsIgnoreCase("Yes")) {
			clickOnAddMembersButton();
		} else {
			verifyAssignTeamMemberButtonIsDisabled(user);
		}

		sa.assertAll();
		return this;

	}

	public void verifyAssignTeamMemberButtonIsDisabled(String user)

	{	
		boolean isDisabled = false;
		try {
		String classes = btn_addTeamMembers.getAttribute("disabled");
		 isDisabled = classes.equalsIgnoreCase("true");

		if (isDisabled) {
			sa.assertTrue(isDisabled, "Assign Team Member button should not be visible to this user----> " + user);
			ExtentFactoryShaip.getTest().log(Status.PASS,
					"Assign Team Member button should  be visible to this user ---->  " + user);
			sa.assertAll();

		} else {
			sa.assertTrue(isDisabled, "Assign Team Member button should not be visible to this user----> " + user);
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper.createLabel(
					"Assign Team Member button should not be visible to this user ---->  " + user, ExtentColor.RED));
			sa.assertAll();

		}
		
		}catch (Exception e) {
			e.printStackTrace();
			sa.assertTrue(isDisabled, "Assign Team Member button should not be visible to this user----> " + user);
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper.createLabel(
					"Assign Team Member button should not be visible to this user ---->  " + user, ExtentColor.RED));
			sa.assertAll();
		}

	}


	public void verifyDynamicTeamAssignmenTabsDetails(ProjectType type) {

		Set<String> collection = new HashSet<String>(Arrays.asList("Collect", "QA", "CQA"));
		Set<String> annotation = new HashSet<String>(Arrays.asList("Annotate", "QA", "CQA"));
		Set<String> annotationActual = new HashSet<String>();
		Set<String> collectionActual = new HashSet<String>();
		String catType = ProjectTypeFactory.selectProjectType(type);

		List<WebElement> workflowSteps = DriverFactoryShaip.getDriver()
				.findElements(By.xpath("(//div[@role='tab']//span)"));
		String stepsName = null;
		for (int i = 0; i < workflowSteps.size(); i++)

		{
			stepsName = getText_custom(workflowSteps.get(i), "Dynamic team assignment tabs");
			if (catType.contains("Collection")) {
				collectionActual.add(stepsName);
			} else {
				annotationActual.add(stepsName);

			}
		}
		if (catType.contains("Collection")) {

			if (collectionActual.containsAll(collection) && collection.containsAll(collectionActual)) {

				ExtentFactoryShaip.getTest().log(Status.PASS,
						"Dynamic team members assignment tabs are same as expected for Collection projects like below");
				ExtentFactoryShaip.getTest().info(MarkupHelper.createLabel(
						"Dynamic team members assignment tabs are same as acceptance criteria.", ExtentColor.INDIGO));
				ExtentFactoryShaip.getTest().info(MarkupHelper.createOrderedList(collectionActual).getMarkup());
			} else {

				ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
						"Test case failure screenshot");
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Dynamic team members assignment tabs are not same as expected for Collection projects");
				ExtentFactoryShaip.getTest().info(MarkupHelper.createLabel(
						"Dynamic team members assignment tabs are not matching with acceptance criteria,check below",
						ExtentColor.RED));
				ExtentFactoryShaip.getTest().info(MarkupHelper.createOrderedList(collectionActual).getMarkup());
			}

		} else {

			if (annotationActual.containsAll(annotation) && annotation.containsAll(annotationActual)) {
				ExtentFactoryShaip.getTest().log(Status.PASS,
						"Dynamic team members assignment tabs are same as expected for Annotation projects like below");
				ExtentFactoryShaip.getTest().info(MarkupHelper.createLabel(
						"Dynamic team members assignment tabs are same as acceptance criteria.", ExtentColor.INDIGO));
				ExtentFactoryShaip.getTest().info(MarkupHelper.createOrderedList(annotationActual).getMarkup());

			} else {
				ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
						"Test case failure screenshot");
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Dynamic team members assignment tabs are not same as expected for Annotation projects");
				ExtentFactoryShaip.getTest().info(MarkupHelper.createLabel(
						"Dynamic team members assignment tabs are not matching with acceptance criteria,check below",
						ExtentColor.RED));
				ExtentFactoryShaip.getTest().info(MarkupHelper.createOrderedList(collectionActual).getMarkup());

			}
		}

	}

}

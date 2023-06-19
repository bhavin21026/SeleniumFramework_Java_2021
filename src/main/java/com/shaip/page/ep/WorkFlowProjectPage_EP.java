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

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.shaip.base.ActionEngineShaip;
import com.shaip.enums.ProjectType;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.factories.ProjectTypeFactory;
import com.shaip.reportng.ExtentFactoryShaip;

public class WorkFlowProjectPage_EP extends ActionEngineShaip {

	public WorkFlowProjectPage_EP() {

		
		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	// Project elements

	@FindBy(id = "workflow")
	private WebElement lnk_workflow;

	@FindBy(id = "add")
	private WebElement btn_addWorkflow;

	@FindBy(id = "nameInput")
	private WebElement txt_nameWorkflow;

	@FindBy(id = "discardLabel")
	private WebElement btn_discard;

	@FindBy(id = "rejectToggle")
	private WebElement btn_reject;

	@FindBy(id = "saveBtn")
	private WebElement btn_saveWorkflow;
	
	@FindBy(id = "cdConfirmButton")
	private WebElement btn_conDelete;
	
	@FindBy(xpath = "//div[contains(@class,'mat-dialog-title')]")
	WebElement hdr_addProject;
	
	

	@FindBy(xpath = "//div[@class='content']//p")
	private WebElement toaster_Message;

	@FindBy(xpath = "//mat-icon[text()='clear']")
	private WebElement btn_closeToaster;

	
	@FindBy(id = "headerBackIcon")
	private WebElement btn_headerBack;
	
	@FindBy(id = "dtGlobalSearch")
	private WebElement txt_globalSearch;
	
	
	public WorkFlowProjectPage_EP clickOnHeaderBack() throws InterruptedException

	{

		waitUntilClickable(btn_headerBack);
		click_custom(btn_headerBack,"Back to Project");
		waitForProcessBarToGo();
		Thread.sleep(1000);
		waitUntilClickable(txt_globalSearch);
		return this;

	}
	public WorkFlowProjectPage_EP clickOnWorkflow() throws InterruptedException

	{

		waitUntilClickable(lnk_workflow);
		Thread.sleep(1000);
		click_custom(lnk_workflow, "Add Work flow step");
		waitForProcessBarToGo();
		waitUntilClickable(btn_addWorkflow);
		return this;

	}

	
	public WorkFlowProjectPage_EP clickOnAddWorkflowStepButton()

	{
		waitUntilClickable(btn_addWorkflow);
		click_custom(btn_addWorkflow, "Add workflow step Button");
		waitForVisibility(hdr_addProject);
		String projectTitle = getText_custom(hdr_addProject, "Add step Title");
		assert_contains(projectTitle, "Add Step", "Add step Screen");
		waitUntilClickable(txt_nameWorkflow);

		return this;

	}
	public WorkFlowProjectPage_EP giveStepName(String name) throws InterruptedException

	{

		waitUntilClickable(txt_nameWorkflow);
		sendKeys_custom(txt_nameWorkflow, "workflow step name", name);
		return this;
	}

	public WorkFlowProjectPage_EP clickOnDiscard() throws InterruptedException

	{

		waitUntilClickable(btn_discard);
		click_custom(btn_discard, "Discard step");
		return this;

	}

	public WorkFlowProjectPage_EP clickOnReject() throws InterruptedException

	{

		waitUntilClickable(btn_reject);
		click_custom(btn_reject, "Reject step");
		return this;

	}

	public WorkFlowProjectPage_EP saveStep() throws InterruptedException

	{

		waitUntilClickable(btn_saveWorkflow);
		click_custom(btn_saveWorkflow, "save workflow");
		return this;

	}

	public WorkFlowProjectPage_EP addNewWorkflowStep(ITestContext context) throws InterruptedException

	{
		String name = "CQA";
		context.setAttribute("stepName", name);
		clickOnAddWorkflowStepButton();
		giveStepName(name);
		clickOnDiscard();
		clickOnReject();
		saveStep();

		return this;

	}



	public WorkFlowProjectPage_EP verifyAddedWorkflowStepDetails(ITestContext context,String attribute) throws InterruptedException

	{
		String name = context.getAttribute(attribute).toString();

		if (DriverFactoryShaip.getDriver().findElements(By.xpath("//td[contains(text(),'" + name + "')]"))
				.size() != 0) {

			WebElement workflowSteps = DriverFactoryShaip.getDriver()
					.findElement(By.xpath("//td[contains(text(),'" + name + "')]"));
			if (workflowSteps.isDisplayed()) {

				ExtentFactoryShaip.getTest().log(Status.PASS, "Workflow step has  been created/updated successfully" + " "
						+ context.getAttribute(attribute).toString());
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

	public WorkFlowProjectPage_EP editWorkflowDetails(ITestContext context) throws InterruptedException

	{
		Thread.sleep(2000);
		System.out.println("In Edit workflow");
		String name=context.getAttribute("stepName").toString();
		String updatedName = "Auto QA";
		context.setAttribute("updatedStep", updatedName);
		WebElement workflowSteps = DriverFactoryShaip.getDriver().findElement(By.xpath("//td[contains(text(),'"+name+"')]/following-sibling::td[5]//button[@id='editBtn']"));	
		click_custom(workflowSteps, "Edit WorkflowStep");
		waitUntilClickable(txt_nameWorkflow);
		clearDrpdownText(txt_nameWorkflow);
		giveStepName(updatedName);
		clickOnDiscard();
		clickOnReject();
		saveStep();

		return this;

	}
	
	public WorkFlowProjectPage_EP deleteWorkflowStep(ITestContext context) throws InterruptedException

	{
		String name=context.getAttribute("stepName").toString();
		WebElement workflowSteps = DriverFactoryShaip.getDriver().findElement(By.xpath("//td[contains(text(),'"+name+"')]/following-sibling::td[5]//button[@id='deleteBtn']"));	
		click_custom(workflowSteps, "Delete WorkflowStep");
		waitUntilClickable(btn_conDelete);
		click_custom(btn_conDelete, "Delete WorkflowStep");
		return this;

	}
	
	

	public WorkFlowProjectPage_EP verifyWorkflowSuccessToaster() throws InterruptedException {

		
		verifyToaster("The step has been added successfully", "Workflow Steps Toaster");

		return this;

	}
	
	public WorkFlowProjectPage_EP verifyEditWorkflowSuccessToaster() throws InterruptedException {

		
		verifyToaster("The step has been updated successfully", "Workflow Steps Toaster");

		return this;

	}
	
	public WorkFlowProjectPage_EP verifyDeleteWorkflowSuccessToaster() throws InterruptedException {

		
		verifyToaster("The step has been deleted successfully", "Workflow Steps Toaster");

		return this;

	}
	
	public  WorkFlowProjectPage_EP verifyAddWorkflowStepsPermissionAccess(String user, String createPermission)
			throws InterruptedException

	{

		if (createPermission.equalsIgnoreCase("Yes")) {
			clickOnAddWorkflowStepButton();
		} else {
			verifyAddWorkflowStepButtonIsDisabled(user);
		}

		sa.assertAll();
		return this;

	}

	public void verifyAddWorkflowStepButtonIsDisabled(String user)

	{	
		boolean isDisabled = false;
		try {
		String classes = btn_addWorkflow.getAttribute("disabled");
		 isDisabled = classes.equalsIgnoreCase("true");

		if (isDisabled) {
			sa.assertTrue(isDisabled, "Add Workflow step button should not be visible to this user----> " + user);
			ExtentFactoryShaip.getTest().log(Status.PASS,
					"Add Workflow step button should  be visible to this user ---->  " + user);
			sa.assertAll();

		} else {
			sa.assertTrue(isDisabled, "Add Workflow step button should not be visible to this user----> " + user);
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper.createLabel(
					"Add Workflow step button should not be visible to this user ---->  " + user, ExtentColor.RED));
			sa.assertAll();

		}
		
		}catch (Exception e) {
			e.printStackTrace();
			sa.assertTrue(isDisabled, "Add Workflow step button should not be visible to this user----> " + user);
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper.createLabel(
					"Add Workflow step button should not be visible to this user ---->  " + user, ExtentColor.RED));
			sa.assertAll();
		}

	}



	public void verifyDefaultWorkFlowSteps(ProjectType type) {

		Set<String> collection = new HashSet<String>(Arrays.asList("Collect", "Validate", "QA", "Discard", "Complete"));
		Set<String> annotation = new HashSet<String>(Arrays.asList("To Do", "Annotate", "QA", "Discard", "Complete"));
		Set<String> annotationActual = new HashSet<String>();
		Set<String> collectionActual = new HashSet<String>();
		String catType = ProjectTypeFactory.selectProjectType(type);

		List<WebElement> workflowSteps = DriverFactoryShaip.getDriver().findElements(By.xpath("//td[4]"));
		String stepsName = null;
		for (int i = 0; i < workflowSteps.size(); i++)

		{
			stepsName = getText_custom(workflowSteps.get(i), "Workflow steps");
			if (catType.contains("Collection")) {
				collectionActual.add(stepsName);
			} else {
				annotationActual.add(stepsName);

			}
		}
		if (catType.contains("Collection")) {

			if (collectionActual.containsAll(collection) && collection.containsAll(collectionActual)) {

				ExtentFactoryShaip.getTest().log(Status.PASS,
						"Pre configured workflow steps list is same as expected for Collection projects like below");
				ExtentFactoryShaip.getTest().info(MarkupHelper.createLabel(
						"Pre configured workflow steps are same as acceptance criteria.", ExtentColor.INDIGO));
				ExtentFactoryShaip.getTest().info(MarkupHelper.createOrderedList(collectionActual).getMarkup());
			} else {

				ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
						"Test case failure screenshot");
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Pre configured workflow steps is not same as expected for Collection projects");
				ExtentFactoryShaip.getTest()
						.info(MarkupHelper.createLabel(
								"Pre configured workflow steps are not matching with acceptance criteria,check below",
								ExtentColor.RED));
				ExtentFactoryShaip.getTest().info(MarkupHelper.createOrderedList(collectionActual).getMarkup());
			}

		} else {

			if (annotationActual.containsAll(annotation) && annotation.containsAll(annotationActual)) {
				ExtentFactoryShaip.getTest().log(Status.PASS,
						"Pre configured workflow steps list is same as expected for Annotation projects like below");
				ExtentFactoryShaip.getTest().info(MarkupHelper.createLabel(
						"Pre configured workflow steps are same as acceptance criteria.", ExtentColor.INDIGO));
				ExtentFactoryShaip.getTest().info(MarkupHelper.createOrderedList(annotationActual).getMarkup());

			} else {
				ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
						"Test case failure screenshot");
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Pre configured workflow steps is not same as expected for Annotation projects");
				ExtentFactoryShaip.getTest()
						.info(MarkupHelper.createLabel(
								"Pre configured workflow steps are not matching with acceptance criteria,check below",
								ExtentColor.RED));
				ExtentFactoryShaip.getTest().info(MarkupHelper.createOrderedList(collectionActual).getMarkup());

			}
		}

	}

}

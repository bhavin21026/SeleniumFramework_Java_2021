package com.shaip.page.cp;

import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.shaip.base.ActionEngineShaip;
import com.shaip.base.ProjectConfigsAC;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.reportng.ExtentFactoryShaip;

public class taskExecutionScreen_CP extends ActionEngineShaip {

	public taskExecutionScreen_CP() {

		wait = new WebDriverWait(DriverFactoryShaip.getDriver(), Duration.ofSeconds(10));
		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	// Project elements

	@FindBy(id = "projectTasks")
	WebElement btn_assignedTasks;

	@FindBy(xpath = "//header//h2")
	WebElement hdr_addProject;

	@FindBy(id = "add")
	WebElement btn_addTask;

	@FindBy(id = "dtGlobalSearch")
	WebElement txt_globalSearch;

	@FindBy(css = "table > tbody > tr > td:nth-child(2)")
	WebElement searchedEntity;

	@FindBy(xpath = "//div//span[6]")
	WebElement hdr_projectName;
	
	@FindBy(xpath = "//div[contains(@class,'task-title-left')]//span[1]")
	WebElement hdr_batchID;

	
	@FindBy(xpath = "//div[contains(@class,'task-title-left')]//span[4]")
	WebElement hdr_batchName;


	@FindBy(xpath = "(//span//mat-panel-title)[3]")
	WebElement hdr_projectGoal;

	@FindBy(xpath = "//div[contains(@class,'task-title-right')]//span[1]")
	WebElement hdr_projectOp;

	@FindBy(id = "projectDetails")
	WebElement tab_details;

	@FindBy(id = "projectTasks")
	WebElement tab_tasks;

	@FindBy(id = "taskExecutionFileUpload-1")
	WebElement btn_uploadAssets;
	@FindBy(id = "upload")
	WebElement btn_upload;

	@FindBy(id = "audio1")
	WebElement con_audioContainer;

	// comments

	@FindBy(id = "taskExecutionComments")
	WebElement btn_comments;

	@FindBy(id = "task-execution-comment-form")
	WebElement txt_comment;

	@FindBy(id = "task-execution-comments-form-add")
	WebElement btn_addComment;

	@FindBy(xpath = "//mat-panel-title[contains(text(),'Past Comments')]")
	WebElement panel_comments;

	@FindBy(xpath = "//span[contains(text(),'Submit')]//parent::button")
	WebElement btn_Submit;

	@FindBy(xpath = "//span[contains(text(),'Save')]//parent::button")
	WebElement btn_Save;

	@FindBy(xpath = "//span[contains(text(),'Pause')]//parent::button")
	WebElement btn_Pause;

	@FindBy(id = "refresh")
	WebElement btn_Refresh;

	@FindBy(id = "deleteUploadBtn-1")
	WebElement btn_dltAudio1;

	@FindBy(name = "cdConfirmButton")
	WebElement btn_cdCancelButton;

	// task grid

	@FindBy(xpath = "//td[5]")
	WebElement lbl_Step;
	
	@FindBy(xpath = "//td[6]")
	WebElement lbl_Status;
	
	@FindBy(id = "CLEAR_FILTER")
	WebElement btn_clearFilter;
	
	@FindBy(id = "taskExecutionFields")
	WebElement btn_customFields;
	
	
	

	public taskExecutionScreen_CP deleteAudioFiles() throws InterruptedException

	{
		waitUntilClickable(btn_dltAudio1);
		click_custom(btn_dltAudio1, "Delete Audio");
		waitUntilClickable(btn_cdCancelButton);
		click_custom(btn_cdCancelButton, "Confirm Delete Audio");
		Thread.sleep(2000);
		waitUntilClickable(btn_Save);
		return this;

	}

	public taskExecutionScreen_CP verifyRefreshTaskListToaster() throws InterruptedException {

		verifyToaster("The tasks list has been refreshed successfully", "Refresh Batch Toaster");

		return this;

	}
	
	public taskExecutionScreen_CP verifyMandatoryToaster() throws InterruptedException {

		verifyToaster("The mandatory criteria must be fulfilled before clicking on the submit action", "Mandatory Toaster");

		return this;

	}


	public taskExecutionScreen_CP verifyRejectedState() throws InterruptedException {

		clickOnTaskListRefresh();
		assert_custom(getText_custom(lbl_Step, "Task Step"), "Collect", "Task Step");
		assert_custom(getText_custom(lbl_Status, "Task Status"), "Rejected", "Task Status");
		return this;
	}
	
	public taskExecutionScreen_CP verifyPausedState() throws InterruptedException {

		clickOnTaskListRefresh();
		assert_custom(getText_custom(lbl_Step, "Task Step"), "Collect", "Paused Step");
		assert_custom(getText_custom(lbl_Status, "Task Status"), "Paused", "Paused Status");
		return this;
	}

	public taskExecutionScreen_CP doComment(String Comment) throws InterruptedException

	{
		waitUntilClickable(btn_comments);
		click_custom(btn_comments, "Do Comments");
		Thread.sleep(1000);	
		click_custom(txt_comment, "Add Comments");
		sendKeys_custom(txt_comment, "Add comment section", Comment);
		waitUntilClickable(btn_addComment);
		execute_click(btn_addComment, "Add Comment Button");

		return this;

	}

	public taskExecutionScreen_CP clickOnTaskListRefresh() throws InterruptedException

	{
		waitUntilClickable(btn_Refresh);
		click_custom(btn_Refresh, "Refresh Task List");
		verifyRefreshTaskListToaster();
		return this;

	}
	
	public taskExecutionScreen_CP clearFilter() throws InterruptedException

	{
		waitUntilClickable(btn_clearFilter);
		click_custom(btn_clearFilter, "Clear Filter");
		Thread.sleep(1000);
		return this;

	}


	public taskExecutionScreen_CP clickOnPastComments()

	{
		waitUntilClickable(panel_comments);
		click_custom(panel_comments, "Past Comments");
		return this;

	}

	public taskExecutionScreen_CP clickOnSave() throws InterruptedException

	{
		waitUntilClickable(btn_Save);
		click_custom(btn_Save, "Save Task");
		verifySaveToasterMessage();
		return this;

	}

	public taskExecutionScreen_CP clickOnPause() throws InterruptedException

	{
		waitUntilClickable(btn_Pause);
		click_custom(btn_Pause, "Pause Task");
		verifyPauseToasterMessage();
		Thread.sleep(10000);
		return this;

	}

	public taskExecutionScreen_CP clickOnSubmit() throws InterruptedException

	{
		Thread.sleep(2000);
		waitUntilClickable(btn_Submit);
		execute_click(btn_Submit, "Submit Task");
		verifySubmitToasterMessage();
		Thread.sleep(8000);
		return this;

	}

	public taskExecutionScreen_CP uploadAudioAssets(String file) throws InterruptedException

	{
		//waitUntilClickable(btn_Save);
		Thread.sleep(2000);
		btn_uploadAssets.sendKeys(file);
		Thread.sleep(2000);
		waitMoreForVisibility(con_audioContainer);
		Thread.sleep(2000);

		return this;

	}

	public taskExecutionScreen_CP clickOnTasksList(String projectName)

	{
		waitUntilClickable(btn_assignedTasks);
		click_custom(btn_assignedTasks, "Assigned task");
		waitForVisibility(hdr_addProject);

		return this;

	}

	public taskExecutionScreen_CP isTaskExecutionScreenOpened(String projectName)

	{
		waitForVisibility(hdr_projectName);
		String projectTitle = getText_custom(hdr_projectName, "Project Name");
		String projectGoal = getText_custom(hdr_projectGoal, "Project goal");
		String projectOP = getText_custom(hdr_projectOp, "Project Operation");

		assert_contains(projectTitle, projectName, " Project Title");
		assert_contains(projectGoal, "Audio Assets", "Project Goal");
		assert_contains(projectOP, "Collect", "Project Operation");

		return this;

	}
	
	public HashMap<String, String> getBatchAndTaskInfo()

	{
		HashMap<String, String> taskInfo = new HashMap<String, String>();
		taskInfo.put(getText_custom(hdr_batchName, "Batch Name"), getText_custom(hdr_batchID, "Batch ID"));
		return taskInfo;

	}
	
	public taskExecutionScreen_CP collectAllAssignedTask(String status) throws InterruptedException

	{
		int batch=50;
		HashMap<String, String> batchInfo = new HashMap<String, String>();

		
		
		for(int i=0;i<48;i++) 
		
		{
			ExtentFactoryShaip.getTest().log(Status.INFO,
					MarkupHelper.createLabel("STAGE- COLLECT TO QA -TASK-"+i,ExtentColor.INDIGO));
			System.out.println("STAGE- COLLECT TO QA -TASK- "+i);
		new  ProjectsPage_CP()
			.clickOnTaskButton();
		new taskExecutionScreen_CP()
	     	.swithToTaskExecutionScreen();
		     batchInfo=getBatchAndTaskInfo();
		new taskExecutionScreen_CP()
	     	.uploadAudioAssets(ProjectConfigsAC.get1624())
	       // .doComment("Checking COLLECT TO QA functionality")
	        .clickOnSubmit()
	        .switchParentScreen();
	       
		Thread.sleep(1000);
	     	
		}
		return this;

	}
	
	public taskExecutionScreen_CP verifyCollectAndQAFlow(String status) throws InterruptedException

	{
		int batchTotal=Integer.parseInt(ProjectConfigsAC.setBatchTotal());
		HashMap<String, String> batchInfo = new HashMap<String, String>();

		
		
		for(int i=0;i<batchTotal;i++) 
		
		{
			ExtentFactoryShaip.getTest().log(Status.INFO,
					MarkupHelper.createLabel("STAGE- COLLECT TO QA -TASK-"+i,ExtentColor.INDIGO));
			System.out.println("STAGE- COLLECT TO QA -TASK- "+i);

		new  ProjectsPage_CP()
			.clickOnTaskButton();
		new taskExecutionScreen_CP()
	     	.swithToTaskExecutionScreen();
		     batchInfo=getBatchAndTaskInfo();
		new taskExecutionScreen_CP()
	     	.uploadAudioAssets(ProjectConfigsAC.getMinAudio())
	        .doComment("Checking COLLECT TO QA functionality")
	        .clickOnSubmit()
	        .switchParentScreen()
	        .clickOnTaskListRefresh()
	        .clearFilter()
	        .verifyTaskStatusOnGrid(batchInfo,status);
		Thread.sleep(2000);
	     	
		}
		return this;

	}
	
	
	
	public taskExecutionScreen_CP verifyCollectedTask(String status,String status2) throws InterruptedException

	{
		Thread.sleep(1000);
		int batch=1000;
		HashMap<String, String> batchInfo = new HashMap<String, String>();

		
		
		for(int i=0;i<batch;i++) 
		
		{
		
			ExtentFactoryShaip.getTest().log(Status.INFO,
					MarkupHelper.createLabel("STAGE- "+status2.toUpperCase()+" TO "+status.toUpperCase()+" -TASK-"+i,ExtentColor.INDIGO));
			System.out.println("STAGE- QA TO CQA -TASK- "+i);

		new  ProjectsPage_CP()
			.clickOnTaskButton();
		new taskExecutionScreen_CP()
	     	.swithToTaskExecutionScreen();
		     batchInfo=getBatchAndTaskInfo();
		new taskExecutionScreen_CP()
	        .doComment("Performing "+status+" Activity")
	        .clickOnSubmit()
	        .switchParentScreen();
	      
		Thread.sleep(1000);
	     	
		}
		return this;

	}
	
	
	public taskExecutionScreen_CP verifyQAAndCQAFlow(String status,String status2) throws InterruptedException

	{
		Thread.sleep(1000);
		int batchTotal=Integer.parseInt(ProjectConfigsAC.setBatchTotal());
		HashMap<String, String> batchInfo = new HashMap<String, String>();

		
		
		for(int i=0;i<batchTotal;i++) 
		
		{
		
			ExtentFactoryShaip.getTest().log(Status.INFO,
					MarkupHelper.createLabel("STAGE- "+status2.toUpperCase()+" TO "+status.toUpperCase()+" -TASK-"+i,ExtentColor.INDIGO));
			System.out.println("STAGE- QA TO CQA -TASK- "+i);

		new  ProjectsPage_CP()
			.clickOnTaskButton();
		new taskExecutionScreen_CP()
	     	.swithToTaskExecutionScreen();
		     batchInfo=getBatchAndTaskInfo();
		new taskExecutionScreen_CP()
	        .doComment("Performing "+status+" Activity")
	        .clickOnSubmit()
	        .switchParentScreen()
	        .clickOnTaskListRefresh()
	        .clearFilter()
	        .verifyTaskStatusOnGrid(batchInfo,status);
		Thread.sleep(2000);
	     	
		}
		return this;

	}
	
	
	public taskExecutionScreen_CP verifyCompleteTask(String status,String status2) throws InterruptedException

	{
		Thread.sleep(1000);
		int batch=50;
		HashMap<String, String> batchInfo = new HashMap<String, String>();

		
		
		for(int i=0;i<50;i++) 
		
		{
		
			ExtentFactoryShaip.getTest().log(Status.INFO,
					MarkupHelper.createLabel("STAGE- "+status2.toUpperCase()+" TO "+status.toUpperCase()+" -TASK-"+i,ExtentColor.INDIGO));
			System.out.println("STAGE- CQA TO Complete -TASK- "+i);

		new  ProjectsPage_CP()
			.clickOnTaskButton();
		new taskExecutionScreen_CP()
	     	.swithToTaskExecutionScreen();
		     batchInfo=getBatchAndTaskInfo();
		new taskExecutionScreen_CP()
	       // .doComment("Performing "+status+" Activity")
	        .clickOnSubmit()
	        .switchParentScreen();
	      
		Thread.sleep(1000);
	     	
		}
		return this;

	}
	
	public taskExecutionScreen_CP verifyCQAAndCompleteFlow(String status,String status2) throws InterruptedException

	{
		Thread.sleep(1000);
		int batchTotal=Integer.parseInt(ProjectConfigsAC.setBatchTotal());
		HashMap<String, String> batchInfo = new HashMap<String, String>();

		
		
		for(int i=0;i<batchTotal;i++) 
		
		{
		
			ExtentFactoryShaip.getTest().log(Status.INFO,
					MarkupHelper.createLabel("STAGE- "+status2.toUpperCase()+" TO "+status.toUpperCase()+" -TASK-"+i,ExtentColor.INDIGO));
			System.out.println("STAGE- CQA TO Complete -TASK- "+i);
		new  ProjectsPage_CP()
			.clickOnTaskButton();
		new taskExecutionScreen_CP()
	     	.swithToTaskExecutionScreen();
		     batchInfo=getBatchAndTaskInfo();
		new taskExecutionScreen_CP()
	        .doComment("Performing "+status+" Activity")
	        .clickOnSubmit()
	        .switchParentScreen()
	        .clickOnTaskListRefresh()
	        .clearFilter()
	        .verifyTaskStatusOnGrid(batchInfo,status);
		Thread.sleep(2000);
	     	
		}
		return this;

	}
	
	public taskExecutionScreen_CP verifyTaskStatusOnGrid(HashMap<String, String> batchInfo,String status)

	{
		waitUntilClickable(btn_Refresh);
		List<WebElement> noOFTasksOnGrid = DriverFactoryShaip.getDriver()
				.findElements(By.xpath("//td[1]"));
		int size=noOFTasksOnGrid.size();
		
		for(int i=0;i<noOFTasksOnGrid.size();i++) 
		{
		WebElement taskID = DriverFactoryShaip.getDriver()
				.findElement(By.xpath("(//td[1])["+i+"]"));
		String id=getText_custom(taskID, "Task ID");
		if(batchInfo.containsValue(id))
		{
			WebElement taskStep = DriverFactoryShaip.getDriver()
					.findElement(By.xpath("(//td[1])["+i+"]//following-sibling::td[4]"));
			String taskStatus=getText_custom(taskStep, "Task Step");
			assert_custom(taskStatus, status, "Task Status in Grid");
			break;

		}else
		{
			ExtentFactoryShaip.getTest().log(Status.FAIL,
					MarkupHelper.createLabel("Collected/Transcripted task not found on Task listing page at Contributor portal",ExtentColor.RED));
			
		}
		
		}
		return this;

	}

	public taskExecutionScreen_CP swithToTaskExecutionScreen() throws InterruptedException

	{

		String parent = DriverFactoryShaip.getDriver().getWindowHandle();
		Set<String> s = DriverFactoryShaip.getDriver().getWindowHandles();
		Iterator<String> I1 = s.iterator();

		if(s.size()>1) {
			
			System.out.println("Parent-Child window both open");

		while (I1.hasNext()) {

			String child_window = I1.next();

			if (!parent.equals(child_window)) {
				try {
					DriverFactoryShaip.getDriver().switchTo().window(child_window);
					System.out.println("Now waiting for progressbar to go----inside swith method");
					//Thread.sleep(3000);
					waitForProcessBarToGo();
					//waitUntilClickable(btn_Save);
				} catch (NoSuchWindowException e) {
					e.printStackTrace();
					
				}

			}
			Thread.sleep(1000);
		}}
		else
		{
			System.out.println("Child window still not open");

			Thread.sleep(3000);
			String CurrentWindow = DriverFactoryShaip.getDriver().getWindowHandle();
			Set<String> windows = DriverFactoryShaip.getDriver().getWindowHandles();
			//System.out.println("Total now:"+windows.size());
			//System.out.println("Total windows now:"+windows);
			Iterator<String> I2 = windows.iterator();

			while (I2.hasNext()) {

				String child_window1 = I2.next();
				System.out.println("Window after swithcing:"+child_window1);

				if (!CurrentWindow.equals(child_window1)) {
					try {
						DriverFactoryShaip.getDriver().switchTo().window(child_window1);
						System.out.println("Now waiting for progressbar to go----inside switch method");
						//Thread.sleep(3000);
						waitForProcessBarToGo();
						//waitUntilClickable(btn_Save);
					} catch (NoSuchWindowException e) {
						e.printStackTrace();
						
					}

				}
				Thread.sleep(1000);	
			
			}
		}
		return this;
	}

	public taskExecutionScreen_CP switchParentScreen() throws InterruptedException

	{
		//Thread.sleep(5000);
		Set<String> s = DriverFactoryShaip.getDriver().getWindowHandles();
		Iterator<String> I1 = s.iterator();

		while (I1.hasNext()) {

			String Parent_window = I1.next();
			try {
				DriverFactoryShaip.getDriver().switchTo().window(Parent_window);
			} catch (NoSuchWindowException e) {

				e.printStackTrace();
			}
		}

		return this;
	}

	public taskExecutionScreen_CP verifyMinValidationToaster(String file) throws InterruptedException {

		waitUntilClickable(btn_comments);
		btn_uploadAssets.sendKeys(file);
		verifyToaster("File should be of at least 15 seconds", "Min Duration of Audio file:AC");

		return this;

	}
	
	public taskExecutionScreen_CP logoutFromPortal() throws InterruptedException {


		new LandingPage_CP()
		.clickOnProfileMenu()
		.clickOnSignOut()
		.isLogoutSuccessfull("CP");
		
		return this;

	}

	public taskExecutionScreen_CP verifyMaxValidationToaster(String file) throws InterruptedException {

		waitUntilClickable(btn_comments);
		btn_uploadAssets.sendKeys(file);
		verifyToaster("File should not exceed 300 seconds", "Max Duration of Audio file:AC");

		return this;

	}
	
	public taskExecutionScreen_CP verifyPauseToasterMessage() throws InterruptedException {

		verifyToaster("The task has been paused successfully. Please note that the mandatory criteria must be fulfilled before clicking on the submit action.", "Pause Task Toaster");

		return this;

	}
	
	public taskExecutionScreen_CP verifySaveToasterMessage() throws InterruptedException {

		verifyToaster("The task has been saved successfully. Please note that the mandatory criteria must be fulfilled before clicking on the submit action.", "Save Task Toaster");

		return this;

	}
	
	public taskExecutionScreen_CP verifySubmitToasterMessage() throws InterruptedException {

		verifyToaster("The task has been submitted successfully", "Submit Task Toaster");

		return this;

	}

}

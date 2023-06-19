package com.shaip.page.cp;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.shaip.base.ActionEngineShaip;
import com.shaip.base.FrameworkContstants;
import com.shaip.base.ProjectConfigsAT;
import com.shaip.enums.ProjectType;
import com.shaip.factories.DriverBase;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.factories.ProjectTypeFactory;
import com.shaip.page.cp.AccountPage_CP;
import com.shaip.page.cp.LanguageAbilities_CP;
import com.shaip.page.ep.ProjectsPage_EP;
import com.shaip.reportng.ExtentFactoryShaip;

public class ProjectsPage_CP extends ActionEngineShaip {

	public ProjectsPage_CP() {

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
	
	@FindBy(xpath = "//td[2]")
	WebElement hdr_searchedProject;
	
	@FindBy(xpath = "//div//span[6]")
	WebElement hdr_projectName;
	
	@FindBy(xpath = "(//span//mat-panel-title)[5]")
	WebElement hdr_projectGoal;
	
	@FindBy(xpath = "//div[contains(@class,'task-title-right')]//span[1]")
	WebElement hdr_projectOp;
	
	
	
	@FindBy(id = "projectDetails")
	WebElement tab_details;
	
	@FindBy(id = "projectTasks")
	WebElement tab_tasks;
	
	//details
	
	@FindBy(xpath = "//h2")
	WebElement hdr_projectNameDeatails;
	
	@FindBy(xpath = "//p[text()='Project Category']/following-sibling::p")
	WebElement hdr_projectCat;
	
	@FindBy(xpath = "//p[text()='Project Type']/following-sibling::p")
	WebElement hdr_projectType;
	
	@FindBy(xpath = "//p[text()='Start Date']/following-sibling::p")
	WebElement hdr_StartDate;
	
	@FindBy(xpath = "//p[text()='End Date']/following-sibling::p")
	WebElement hdr_EndDate;
	
	@FindBy(xpath = "//p[text()='Description']/following-sibling::p")
	WebElement hdr_Description;
	
	
	public ProjectsPage_CP verifyAssignedProjectDetails(ITestContext context)

	{
		
		/*String projectCategory = "Audio";
		String projectType = getText_custom(hdr_projectType, "Project Type");
		String startDate = getText_custom(hdr_StartDate, "Start Date");
		String endDate = getText_custom(hdr_EndDate, "End Date");
		String projectName = getText_custom(hdr_projectNameDeatails, "projectName");
		String projectDescription = getText_custom(hdr_Description, "projectDescription");

		assert_custom(projectCategory, "Audio", "projectCategory");
		assert_custom(projectType, (String) context.getAttribute("ProjectCat"), "projectType");
		assert_custom(startDate, (String) context.getAttribute("StartDate"), "startDate");
		assert_custom(endDate, (String) context.getAttribute("EndDate"), "endDate");
		assert_custom(projectName, (String) context.getAttribute("DisplayName"), "projectName");
		assert_custom(projectDescription, (String) context.getAttribute("Description"), "projectDescription");*/

		clickOnProjectTask();

		return this;

	}
	
	public ProjectsPage_CP clickOnProjectTask()

	{
		waitUntilClickable(tab_tasks);
		click_custom(tab_tasks, "Project Tasks");
		waitUntilClickable(btn_addTask);		
	    return this;

	}
	
	
	public ProjectsPage_CP clickOnProjectDetails()

	{
		waitUntilClickable(tab_details);
		click_custom(tab_details, "Project details tab");
		return this;

	}
	
	
	public ProjectsPage_CP clickOnTasksList() throws InterruptedException

	{
		waitUntilClickable(btn_assignedTasks);
		click_custom(btn_assignedTasks, "Assigned task");
		waitForProcessBarToGo();
		waitForVisibility(hdr_addProject);
		


		return this;

	}
	
	
	
	public ProjectsPage_CP clickOnTaskButton() throws InterruptedException

	{
		waitUntilClickable(btn_addTask);
		click_custom(btn_addTask, "Add Task Button");
		waitForProcessBarToGo();		
		//Thread.sleep(5000);

		return this;

	}
	
	
	
	public ProjectsPage_CP searchCustomer(String searchEntity) throws InterruptedException

	{
		Thread.sleep(1000);
		waitUntilClickable(txt_globalSearch);
		click_custom(txt_globalSearch, searchEntity);
		sendKeys_custom(txt_globalSearch, "Global Search", searchEntity);
		Thread.sleep(2000);
		waitForVisibility(hdr_searchedProject);
		String searchedResult = getText_custom(hdr_searchedProject, "Searched Project");
		System.out.println("searched Project" + searchedResult);

		if (!searchedResult.equalsIgnoreCase(searchEntity)) {

			//sa.fail("Project search is failed, not found any search record, for searched customer---> "+searchEntity);
			ExtentFactoryShaip.getTest().log(Status.FAIL,"Project search is failed, not found any search record, searched customer--> " +searchEntity);
			

		}else
		{
			
			ExtentFactoryShaip.getTest().log(Status.PASS,"Project search is successfull,Searched Project has been displayed--> " +searchEntity);

		}
		Thread.sleep(2000);
		sa.assertAll();
		return this;
	}
	
	public ProjectsPage_CP searchProject(String searchEntity) throws InterruptedException

	{
		waitUntilClickable(txt_globalSearch);
		click_custom(txt_globalSearch, searchEntity);
		sendKeys_custom(txt_globalSearch, "Global Search", searchEntity);
		Thread.sleep(2000);
		return this;
	}
	

}

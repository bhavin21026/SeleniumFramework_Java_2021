package com.shaip.page.ep;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
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
import com.shaip.enums.story;
import com.shaip.factories.DriverBase;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.factories.ProjectTypeFactory;
import com.shaip.page.cp.AccountPage_CP;
import com.shaip.page.cp.LanguageAbilities_CP;
import com.shaip.reportng.ExtentFactoryShaip;

public class ProjectsPage_EP extends ActionEngineShaip {

	public ProjectsPage_EP() {

		
		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	// Project elements

	@FindBy(id = "add")
	WebElement btn_addProject;

	@FindBy(xpath = "//header//h2")
	WebElement hdr_addProject;

	@FindBy(id = "selectProjectCatAll")
	WebElement cat_all;

	@FindBy(id = "fileNameCtrl")
	WebElement txt_fileName;

	@FindBy(xpath = "(//button[@id='saveBtn'])[2]")
	WebElement btn_saveBtn;

	
	// Basic deatils

	@FindBy(name = "projectName")
	WebElement txt_projectName;

	@FindBy(name = "projectDisplayName")
	WebElement txt_projectDisplayName;

	@FindBy(name = "projectDescription")
	WebElement txt_projectDescription;

	@FindBy(xpath = "//mat-select[@id='projectType']//div//div//span//span")
	WebElement drp_ProjectType;

	@FindBy(name = "saveBtn")
	WebElement btn_saveProject;

	@FindBy(xpath = "//div[@class='content']//p")
	WebElement toaster_Message;

	@FindBy(xpath = "//mat-icon[text()='clear']")
	WebElement btn_closeToaster;
	
	@FindBy(xpath = "//td[2]")
	WebElement hdr_searchedProject;
	
	@FindBy(id = "fileUploadInput")
	WebElement btn_chooseFile;
	
	@FindBy(id = "chooseButton")
	WebElement btn_uploadFile;
	
	@FindBy(id = "deleteBtn")
	WebElement btn_deleteFile;
	
	@FindBy(id = "cdConfirmButton")
	WebElement btn_ConfirmDeleteFile;

	
	@FindBy(id = "editBtn")
	WebElement btn_editFile;
	
	@FindBy(xpath = "//mat-panel-title[contains(text(),'Guidelines')]//parent::span")
	WebElement Guidelinespanel;

	
	// dates

	@FindBy(xpath = "//input[@id='projectStartDate' and @name='projectStartDate']")
	WebElement cal_userStartDate;

	@FindBy(xpath = "//input[@id='projectEndDate' and @name='projectEndDate']")
	WebElement cal_userEndDate;
	
	
	@FindBy(xpath = "//span[contains(@class,'mat-button-wrapper')]//span")
	WebElement cal_MonthYear;

	@FindBy(xpath = "(//label[@class='form-label'])[1]")
	WebElement dt_StartDate;

	@FindBy(xpath = "//div[@class='mat-calendar-controls']//child::button[1]//span[1]//span")
	WebElement MonthValue;

	@FindBy(xpath = "//button[@aria-label='Previous month']")
	WebElement btn_previousMonth;

	@FindBy(xpath = "//button[@aria-label='Next month']")
	WebElement btn_nextMonth;
	
	@FindBy(id = "dtGlobalSearch")
	WebElement txt_globalSearch;

	@FindBy(css = "table > tbody > tr > td:nth-child(2)")
	WebElement searchedEntity;
	
	@FindBy(xpath = "(//button[@id='editProject'])[1]")
	WebElement btn_editProject;
	
	//Status
	
	@FindBy(id = "publishProject")
	WebElement btn_publishProject;
	
	@FindBy(id = "activateProject")
	WebElement btn_activeProject;
	
	@FindBy(id = "pauseProject")
	WebElement btn_pauseProject;
	
	@FindBy(id = "completeProject")
	WebElement btn_completeProject;
	
	@FindBy(id = "archiveProject")
	WebElement btn_archiveProject;
	
	@FindBy(id = "unarchiveProject")
	WebElement btn_unArchiveProject;
	
	@FindBy(id = "archivedProjectTab")
	WebElement tab_unArchiveProject;
	
	@FindBy(id = "projectTab")
	WebElement tab_projectTab;
	
	@FindBy(id = "resumeProject")
	WebElement btn_resumeProject;
	
	@FindBy(id = "cdConfirmButton")
	WebElement btn_ConfirmArchive;
	
	@FindBy(xpath = "//td[5]")
	WebElement lbl_startDate;
	
	@FindBy(xpath = "//td[6]")
	WebElement lbl_endDate;
	
	
	
	public ProjectsPage_EP changeProjectStatus(String UserRole, String methodName) throws InterruptedException

	{
		makeProjectPublish(UserRole,methodName);
		Thread.sleep(1000);
		makeProjectActive(UserRole,methodName);
		Thread.sleep(1000);
		makeProjectPause(UserRole,methodName);
		Thread.sleep(1000);
		makeProjectResume(UserRole,methodName);
		Thread.sleep(1000);

		return this;

	}
	
	
	public ProjectsPage_EP publishProject() throws InterruptedException

	{
	
		moveToElement_custom(btn_publishProject, "Publish Project");
		waitUntilClickable(btn_publishProject);
		click_custom(btn_publishProject, "Publish Project");
		verifyProjectPublishedToaster();
		waitForVisibility(btn_activeProject);
		waitUntilClickable(btn_activeProject);
		Thread.sleep(1000);
		moveToElement_custom(btn_activeProject, "Active Project");
		waitUntilClickable(btn_activeProject);
		click_custom(btn_activeProject, "Active Project");
		verifyProjectActivatedToaster();
		waitForVisibility(btn_pauseProject);
		waitUntilClickable(btn_pauseProject);
		

		return this;

	}
	
	public ProjectsPage_EP completeProject(String UserRole, String methodName) throws InterruptedException

	{
		setupExtentReport("EP: Verify change Project Status functionality From Resume to Complete("+UserRole+")",methodName,UserRole,story.ProjectStatusChange_EP_145.toString(), "Smoke", "EP");

		moveToElement_custom(btn_completeProject, "Complete Project");
		waitUntilClickable(btn_completeProject);
		click_custom(btn_completeProject, "Complete Project");
		verifyProjectCompletedToaster();
		

		return this;

	}
	
	public ProjectsPage_EP makeProjectPublish(String UserRole, String methodName) throws InterruptedException

	{
		setupExtentReport("EP: Verify change Project Status functionality From Ready to Published("+UserRole+")",methodName,UserRole,story.ProjectStatusChange_EP_145.toString(), "Smoke", "EP");

		moveToElement_custom(btn_publishProject, "Publish Project");
		waitUntilClickable(btn_publishProject);
		click_custom(btn_publishProject, "Publish Project");
		verifyProjectPublishedToaster();
		waitForVisibility(btn_activeProject);
		waitUntilClickable(btn_activeProject);
	

		return this;

	}
	
	public ProjectsPage_EP makeProjectActive(String UserRole, String methodName) throws InterruptedException

	{
		setupExtentReport("EP: Verify change Project Status functionality From Published to Active("+UserRole+")",methodName,UserRole,story.ProjectStatusChange_EP_145.toString(), "Smoke", "EP");

		moveToElement_custom(btn_activeProject, "Active Project");
		waitUntilClickable(btn_activeProject);
		click_custom(btn_activeProject, "Active Project");
		verifyProjectActivatedToaster();
		waitForVisibility(btn_pauseProject);
		waitUntilClickable(btn_pauseProject);
	

		return this;

	}
	public ProjectsPage_EP makeProjectPause(String UserRole, String methodName) throws InterruptedException

	{
		setupExtentReport("EP: Verify change Project Status functionality From Active to Pause("+UserRole+")",methodName,UserRole,story.ProjectStatusChange_EP_145.toString(), "Smoke", "EP");

		moveToElement_custom(btn_pauseProject, "Pause Project");
		waitUntilClickable(btn_pauseProject);
		click_custom(btn_pauseProject, "Pause Project");
		verifyProjectPausedToaster();
		waitForVisibility(btn_resumeProject);
		waitUntilClickable(btn_resumeProject);
	

		return this;

	}
	
	public ProjectsPage_EP makeProjectResume(String UserRole, String methodName) throws InterruptedException

	{
		setupExtentReport("EP: Verify change Project Status functionality From Pause to Resume("+UserRole+")",methodName,UserRole,story.ProjectStatusChange_EP_145.toString(), "Smoke", "EP");

		moveToElement_custom(btn_resumeProject, "Resume Project");
		waitUntilClickable(btn_resumeProject);
		click_custom(btn_resumeProject, "Resume Project");
		verifyProjectResumedToaster();
		waitForVisibility(btn_pauseProject);
		waitUntilClickable(btn_pauseProject);
	

		return this;

	}
	
	public ProjectsPage_EP makeProjectComplete() throws InterruptedException

	{

		moveToElement_custom(btn_completeProject, "Complete Project");
		waitUntilClickable(btn_completeProject);
		click_custom(btn_completeProject, "Complete Project");
		verifyProjectCompletedToaster();
	

		return this;

	}
	
	public ProjectsPage_EP makeProjectArchive(String search,String UserRole, String methodName) throws InterruptedException

	{
		setupExtentReport("EP: As a User, I should be able to 'ARCHIVE' projects("+UserRole+")",methodName,UserRole,story.ProjectStatusChange_EP_145.toString(), "Smoke", "EP");

		moveToElement_custom(btn_archiveProject, "Archive Project");
		waitUntilClickable(btn_archiveProject);
		click_custom(btn_archiveProject, "Archive Project");
		waitUntilClickable(btn_ConfirmArchive);
		click_custom(btn_ConfirmArchive, "Confirm Archive Project");
		verifyProjectArchivedToaster();
		waitForProcessBarToGo();
		Thread.sleep(1000);
		isProjectArchived(search);
	
	

		return this;

	}
	
	public ProjectsPage_EP isProjectArchived(String searchEntity) throws InterruptedException

	{
		waitUntilClickable(tab_unArchiveProject);
		click_custom(tab_unArchiveProject, "Tab unacrchived Project");
		waitForProcessBarToGo();
		searchCustomer(searchEntity);

		return this;

	}
	
	public ProjectsPage_EP isProjectUnArchived(String searchEntity) throws InterruptedException

	{
		waitUntilClickable(tab_projectTab);
		click_custom(tab_projectTab, "Project Tab");
		searchCustomer(searchEntity);

		return this;

	}
	
	public ProjectsPage_EP makeProjectUnArchive(String searchEntity,String UserRole, String methodName) throws InterruptedException

	{
		setupExtentReport("EP: As a User, I should be able to 'UNARCHIVE' projects("+UserRole+")",methodName,UserRole,story.ProjectStatusChange_EP_145.toString(), "Smoke", "EP");

		waitUntilClickable(tab_unArchiveProject);
		click_custom(tab_unArchiveProject, "Tab unacrchived Project");
		clearDrpdownText(txt_globalSearch);
		Thread.sleep(1000);
		searchCustomer(searchEntity);
		moveToElement_custom(btn_unArchiveProject, "UnArchive Project");
		waitUntilClickable(btn_unArchiveProject);
		click_custom(btn_unArchiveProject, "UnArchive Project");
		waitUntilClickable(btn_ConfirmArchive);
		click_custom(btn_ConfirmArchive, "Confirm unArchive Project");
		verifyProjectUnArchivedToaster();
		Thread.sleep(2000);
		isProjectUnArchived(searchEntity);
	

		return this;

	}
	
	
	

	public ProjectsPage_EP clickOnAddProjectButton()

	{
		waitUntilClickable(btn_addProject);
		click_custom(btn_addProject, "Add project Button");
		waitForVisibility(hdr_addProject);
		String projectTitle = getText_custom(hdr_addProject, "Add Project Title");
		assert_contains(projectTitle, "Add Project", "Add Project Screen");

		return this;

	}

	public ProjectsPage_EP verifyProjectTypeCategories() throws InterruptedException

	{
		Thread.sleep(1000);
		Set<String> projectTypeCat = new HashSet<String>(Arrays.asList("Audio", "Text", "Image", "Video", "All"));
		List<WebElement> catName = DriverFactoryShaip.getDriver()
				.findElements(By.xpath("//div[contains(@id,'selectProject')]"));

		for (int i = 0; i < catName.size(); i++)

		{
			String caTtitle = getText_custom(catName.get(i), "Project Category Chips");
			System.out.println("Title of columns******" + catName);

			if (!projectTypeCat.contains(caTtitle)) {

				// sa.fail(title + " Project type not belongs to any project type");
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						caTtitle + "     Project type category not belongs to any project type under Project types");

			} else {
				ExtentFactoryShaip.getTest().log(Status.PASS, " Project type category list is same as expected");

			}

		}

		return this;

	}

	public ProjectsPage_EP verifyProjectTypessBasedOnSelectedCategories() throws InterruptedException

	{
		verifyAllProjectList();
		verifyAudioProjectList();
		verifyImageProjectList();
		verifyTextProjectList();
		verifyVideoProjectList();
		return this;

	}

	public ProjectsPage_EP verifyAllProjectList() throws InterruptedException

	{
		Thread.sleep(1000);
		Set<String> allType = new HashSet<String>(Arrays.asList("Audio Classification", "Audio Collection",
				"Audio Segmentation", "Audio Transcription", "Video Classification", "Video Collection",
				"Video Segmentation", "Video Annotation", "Image Classification", "Image Collection",
				"Image Annotation", "Text Extraction", "Text Classification", "Text Annotation"));

		click_custom(cat_all, "All Category");

		List<WebElement> projectBox = DriverFactoryShaip.getDriver().findElements(By.xpath(
				"//div[contains(@class,'projectDataContainer')]//div[contains(@class,'projectBox')]//div[contains(@class,'projectTitle')]"));

		for (int i = 0; i < projectBox.size(); i++)

		{
			String projectTypeTitle = getText_custom(projectBox.get(i), "Project Type");

			if (!allType.contains(projectTypeTitle)) {

				// sa.fail(title + " Project type not belongs to any project type");
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						projectTypeTitle + "Project type category not belongs to any project type under Project types");

			} else {
				ExtentFactoryShaip.getTest().log(Status.PASS, " Project type category list is same as expected");

			}
		}

		return this;
	}

	public ProjectsPage_EP verifyAudioProjectList() throws InterruptedException

	{
		Thread.sleep(1000);

		Set<String> audioType = new HashSet<String>(
				Arrays.asList("Audio Classification", "Audio Collection", "Audio Segmentation", "Audio Transcription"));

		WebElement audioProjects = DriverFactoryShaip.getDriver()
				.findElement(By.xpath("(//div[@id='selectProjectCategory'])[1]"));
		click_custom(audioProjects, "Audio Category");

		List<WebElement> projectBox = DriverFactoryShaip.getDriver().findElements(By.xpath(
				"//div[contains(@class,'projectDataContainer')]//div[contains(@class,'projectBox')]//div[contains(@class,'projectTitle')]"));

		for (int i = 0; i < projectBox.size(); i++)

		{
			String projectTypeTitle = getText_custom(projectBox.get(i), "Project Type");

			if (!audioType.contains(projectTypeTitle)) {

				// sa.fail(title + " Project type not belongs to any project type");
				ExtentFactoryShaip.getTest().log(Status.FAIL, projectTypeTitle
						+ "Audio Project type category not belongs to any project type under Project types");

			} else {
				ExtentFactoryShaip.getTest().log(Status.PASS, "Audio Project type category list is same as expected");

			}
		}
		return this;

	}

	public ProjectsPage_EP verifyVideoProjectList() throws InterruptedException

	{
		Thread.sleep(1000);

		Set<String> videoType = new HashSet<String>(
				Arrays.asList("Video Classification", "Video Collection", "Video Segmentation", "Video Annotation"));

		WebElement viodeProjects = DriverFactoryShaip.getDriver()
				.findElement(By.xpath("(//div[@id='selectProjectCategory'])[4]"));
		click_custom(viodeProjects, "Video Category");

		List<WebElement> projectBox = DriverFactoryShaip.getDriver().findElements(By.xpath(
				"//div[contains(@class,'projectDataContainer')]//div[contains(@class,'projectBox')]//div[contains(@class,'projectTitle')]"));
		for (int i = 0; i < projectBox.size(); i++)

		{
			String projectTypeTitle = getText_custom(projectBox.get(i), "Project Type");

			if (!videoType.contains(projectTypeTitle)) {

				// sa.fail(title + " Project type not belongs to any project type");
				ExtentFactoryShaip.getTest().log(Status.FAIL, projectTypeTitle
						+ "Video Project type category not belongs to any project type under Project types");

			} else {
				ExtentFactoryShaip.getTest().log(Status.PASS, "Video Project type category list is same as expected");

			}
		}
		return this;

	}

	public ProjectsPage_EP verifyImageProjectList() throws InterruptedException

	{
		Thread.sleep(1000);

		Set<String> imageType = new HashSet<String>(
				Arrays.asList("Image Classification", "Image Collection", "Image Annotation"));

		WebElement imageProjects = DriverFactoryShaip.getDriver()
				.findElement(By.xpath("(//div[@id='selectProjectCategory'])[2]"));
		click_custom(imageProjects, "Image Category");

		List<WebElement> projectBox = DriverFactoryShaip.getDriver().findElements(By.xpath(
				"//div[contains(@class,'projectDataContainer')]//div[contains(@class,'projectBox')]//div[contains(@class,'projectTitle')]"));
		for (int i = 0; i < projectBox.size(); i++)

		{
			String projectTypeTitle = getText_custom(projectBox.get(i), "Project Type");
			if (!imageType.contains(projectTypeTitle)) {

				// sa.fail(title + " Project type not belongs to any project type");
				ExtentFactoryShaip.getTest().log(Status.FAIL, projectTypeTitle
						+ "Image Project type category not belongs to any project type under Project types");

			} else {
				ExtentFactoryShaip.getTest().log(Status.PASS, "Image Project type category list is same as expected");

			}
		}
		return this;

	}

	public ProjectsPage_EP verifyTextProjectList() throws InterruptedException

	{
		Thread.sleep(1000);

		Set<String> textType = new HashSet<String>(
				Arrays.asList("Text Extraction", "Text Classification", "Text Annotation"));

		WebElement textProjects = DriverFactoryShaip.getDriver()
				.findElement(By.xpath("(//div[@id='selectProjectCategory'])[3]"));
		click_custom(textProjects, "Image Category");

		List<WebElement> projectBox = DriverFactoryShaip.getDriver().findElements(By.xpath(
				"//div[contains(@class,'projectDataContainer')]//div[contains(@class,'projectBox')]//div[contains(@class,'projectTitle')]"));

		for (int i = 0; i < projectBox.size(); i++)

		{
			String projectTypeTitle = getText_custom(projectBox.get(i), "Project Type");

			if (!textType.contains(projectTypeTitle)) {

				// sa.fail(title + " Project type not belongs to any project type");
				ExtentFactoryShaip.getTest().log(Status.FAIL, projectTypeTitle
						+ "Text Project type category not belongs to any project type under Project types");

			} else {
				ExtentFactoryShaip.getTest().log(Status.PASS, "Text Project type category list is same as expected");

			}
		}
		return this;

	}

	public ProjectsPage_EP verifyAddProjectButtonPermissionAccess(String user, String createPermission)
			throws InterruptedException

	{

		if (createPermission.equalsIgnoreCase("Yes")) {
			clickOnAddProjectButton();
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
		String classes = btn_addProject.getAttribute("disabled");
		 isDisabled = classes.equalsIgnoreCase("true");

		if (isDisabled) {
			sa.assertTrue(isDisabled, "Add project button should not be visible to this user----> " + user);
			ExtentFactoryShaip.getTest().log(Status.PASS,
					"Add project button should  be visible to this user ---->  " + user);
			sa.assertAll();

		} else {
			sa.assertTrue(isDisabled, "Add project button should not be visible to this user----> " + user);
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper.createLabel(
					"Add project button should not be visible to this user ---->  " + user, ExtentColor.RED));
			sa.assertAll();

		}
		
		}catch (Exception e) {
			e.printStackTrace();
			sa.assertTrue(isDisabled, "Add project button should not be visible to this user----> " + user);
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper.createLabel(
					"Add project button should not be visible to this user ---->  " + user, ExtentColor.RED));
			sa.assertAll();
		}

	}

	public ProjectsPage_EP createNewProject(ProjectType type) throws InterruptedException

	{
		List<WebElement> projectBox = DriverFactoryShaip.getDriver().findElements(By.xpath(
				"//div[contains(@class,'projectDataContainer')]//div[contains(@class,'projectBox')]//div[contains(@class,'projectTitle')]"));
		List<WebElement> projectBox1 = DriverFactoryShaip.getDriver().findElements(
				By.xpath("//div[contains(@class,'projectDataContainer')]//div[contains(@class,'projectBox')]"));

		for (int i = 0; i < projectBox.size(); i++)

		{
			String projectTypeTitle = getText_custom(projectBox.get(i), "Project Type");
			String catType = ProjectTypeFactory.selectProjectType(type);

			if (projectTypeTitle.equalsIgnoreCase(catType)) {

				WebElement btn_addProject = projectBox1.get(i).findElement(By.id("addProject"));
				click_custom(btn_addProject, "Add Project Button");
			}

		}
		return this;

	}

	// Add basic project details

	public void enterProjectName(String Name)

	{

		click_custom(txt_projectName, "Project Name");
		sendKeys_custom(txt_projectName, "Project Name", Name);

	}

	public void enterProjectDisplayName(String Display)

	{

		click_custom(txt_projectDisplayName, "Display Name");
		sendKeys_custom(txt_projectDisplayName, "Display Name", Display);

	}

	public void enterProjectDescription(String Desc) throws InterruptedException

	{

		click_custom(txt_projectDescription, "Project Description");
		sendKeys_custom(txt_projectDescription, "Project Description", Desc);
		Thread.sleep(1000);

	}

	public void submitProject() throws InterruptedException

	{
		Thread.sleep(2000);
		waitUntilClickable(btn_saveProject);
		execute_click(btn_saveProject, "Save Project");


	}
	
	public ProjectsPage_EP clickOnEditProject()

	{

		moveToElement_custom(btn_editProject, "Edit Project");
		click_custom(btn_editProject, "Edit Project");
		return this;

	}

	
	
	public void selectStartDate() throws InterruptedException

	{

		Thread.sleep(1000);
		
		waitUntilClickable(cal_userStartDate);
		execute_click(cal_userStartDate, "Calendar Start Date");
		String dt_StartDate = getTomorrow();
		String tomorrowMonth=getTomorrowMonthYear();
		Thread.sleep(2000);
		waitForVisibility(cal_MonthYear);
		String currentMonthYear=getText_custom(cal_MonthYear, "Current Month Year");
		
		if (!currentMonthYear.equalsIgnoreCase(tomorrowMonth)) {
		
			execute_click(btn_nextMonth, "Next Month Button");
			Thread.sleep(2000);

		}

		WebElement picker = DriverFactoryShaip.getDriver().findElement(By.xpath("//tbody[@class='mat-calendar-body']"));

		List<WebElement> Days = picker.findElements(By.xpath("//tr//td[@role='gridcell']//div[1]"));

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
				execute_click(Days.get(i), "Select Start date");
				Thread.sleep(2000);
				ExtentFactoryShaip.getTest().log(Status.INFO, "Start date selected as "+DayValue);
				flag = 1;
				break;
			}

		}
		ExtentFactoryShaip.getTest().log(Status.INFO, "Start Date selected for project");

	}

	public void selectEndDate() throws InterruptedException

	{
		waitUntilClickable(cal_userEndDate);
		execute_click(cal_userEndDate, "Calendar End Date");
		String dt_EndDate = getDayAfterTomorrow();
		String dayAftertomorrowMonth=getDayAfterTomorrowMonthYear();

		Thread.sleep(2000);
		waitForVisibility(cal_MonthYear);
		String currentMonthYear=getText_custom(cal_MonthYear, "Current Month Year");
		
		if (!currentMonthYear.equalsIgnoreCase(dayAftertomorrowMonth)) {
		
			execute_click(btn_nextMonth, "Next Month Button");
			Thread.sleep(2000);

		}
		WebElement picker = DriverFactoryShaip.getDriver().findElement(By.xpath("//tbody[@class='mat-calendar-body']"));

		List<WebElement> Days = picker.findElements(By.xpath("//tr//td[@role='gridcell']//div[1]"));

		int totalelementsfind = Days.size();
		String DayValue = null;

		for (int i = 0; i < totalelementsfind; i++) {

			
			DayValue = Days.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ");

			if (DayValue.length() <= 1)

			{
				DayValue = "0" + DayValue;

			}
			if (DayValue.equalsIgnoreCase(dt_EndDate)) {
				Thread.sleep(2000);
				execute_click(Days.get(i), "Select End date");
				Thread.sleep(2000);
				ExtentFactoryShaip.getTest().log(Status.INFO, "End date selected as "+DayValue);
				break;
			}

		}
		ExtentFactoryShaip.getTest().log(Status.INFO, "End Date selected for project");

	}

	public ProjectsPage_EP addBasicInformationOfProject(ProjectType type,ITestContext context,String Name,String DisplayName) throws InterruptedException

	{
		String catType = ProjectTypeFactory.selectProjectType(type);
		String projectName =Name +"("+catType+")"+ getDate();
		String projectDisplayName = DisplayName +"("+catType+")"+ getDate();
		
		context.setAttribute("DisplayName",projectDisplayName);
		context.setAttribute("ProjectName",projectName);
		context.setAttribute("Description",projectName);

		enterProjectName(projectName);
		selectStartDate();
		enterProjectDisplayName(projectDisplayName);
		selectEndDate();
		enterProjectDescription(ProjectConfigsAT.setProjectDiscription());
		String projectCategory = getText_custom(drp_ProjectType, "Project Type");
		assert_custom(projectCategory, catType, "Project category");
		context.setAttribute("ProjectCat",projectCategory);
		
		submitProject();
		verifyProjectSuccessToaster();
		return this;

	}
	
	public ProjectsPage_EP getDatesOFProjectsITest(ITestContext context) throws InterruptedException

	{
		

		String eDt=getText_custom(lbl_startDate, "Start Date");
		String sDt=getText_custom(lbl_endDate, "End Date");
		context.setAttribute("StartDate",sDt);
		context.setAttribute("EndDate",eDt);
		return this;

	}
	
	
	public ProjectsPage_EP addBasicInformationAndGuidelines(ProjectType type,ITestContext context,String Name,String DisplayName) throws InterruptedException

	{
		String catType = ProjectTypeFactory.selectProjectType(type);
		String projectName =Name +"("+catType+")"+ getDate();
		String projectDisplayName = DisplayName +"("+catType+")"+ getDate();
		context.setAttribute("ProjectName",projectName);

		enterProjectName(projectName);
		selectStartDate();
		enterProjectDisplayName(projectDisplayName);
		selectEndDate();
		enterProjectDescription(ProjectConfigsAT.setProjectDiscription());
		Thread.sleep(3000);
		uploadGuidelines();
		submitProject();
		verifyProjectSuccessToaster();
		return this;

	}
	
	
	public ProjectsPage_EP verifyChooseFileButtonState() throws InterruptedException

	{
		boolean isDisabled = false;
		try {
		String classes = btn_uploadFile.getAttribute("disabled");
		 isDisabled = classes.equalsIgnoreCase("true");

		if (isDisabled) {
			
			
			ExtentFactoryShaip.getTest().log(Status.PASS,
					"Choose file button becomes disabled after 5 file uploaded as expected");
			sa.assertAll();

		} else {
			
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper.createLabel(
					"Choose file button should not be enabled if 5 guidelines are already uploaded", ExtentColor.RED));
			sa.assertAll();

		}
		
		}catch (Exception e) {
			e.printStackTrace();
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper.createLabel(
					"Choose file button should not be enabled if 5 guidelines are already uploaded", ExtentColor.RED));
			sa.assertAll();
		}
		return this;

		

	}
	
	
	public ProjectsPage_EP deleteGuidelines() throws InterruptedException

	{
		

		if (DriverFactoryShaip.getDriver().findElements(By.id("deleteBtn"))
				.size() != 0)

		{
			List<WebElement> guidelines = DriverFactoryShaip.getDriver()
					.findElements(By.id("deleteBtn"));

			for (int i = 0; i < guidelines.size(); i++) {
				clickOnDeleteGuidelines();
				System.out.println("Delete existing Guidelines");


			}
			Thread.sleep(2000);
			isGuidelinesDeleted();
			ExtentFactoryShaip.getTest().log(Status.PASS,
					"All Guidelines Deleted successfully");
		}
		return this;

		

	}
	
	public ProjectsPage_EP isGuidelinesDeleted() throws InterruptedException

	{

		WebElement message=DriverFactoryShaip.getDriver().findElement(By.xpath("//td[1]"));
		String assertion= getText_custom(message, "Guidelines section");
		assert_contains(assertion, "There are no rows to display", "Guidelines");

		return this;

	}

	
	
	public ProjectsPage_EP clickOnDeleteGuidelines() throws InterruptedException

	{

		Thread.sleep(1000);
		click_custom(btn_deleteFile, "Delete Guidelines");
		Thread.sleep(1000);
		waitUntilClickable(btn_ConfirmDeleteFile);
		click_custom(btn_ConfirmDeleteFile, "Delete Confirm");

		return this;

	}

	public ProjectsPage_EP uploadGuidelines() throws InterruptedException

	{
		waitUntilClickable(Guidelinespanel);
		click_custom(Guidelinespanel, "Guidelines panel");
		Thread.sleep(1000);
		btn_chooseFile.sendKeys(FrameworkContstants.getSample1(),"\n",FrameworkContstants.getSample2(),"\n",FrameworkContstants.getSample3(),"\n",FrameworkContstants.getSample4(),"\n",FrameworkContstants.getSample5());
		Thread.sleep(5000);
		verifyUploadedGuidelines();
		return this;

		

	}
	
	public ProjectsPage_EP EdituploadedGuidelines() throws InterruptedException

	{
		Thread.sleep(1000);
		click_custom(Guidelinespanel, "Guidelines panel");
		Thread.sleep(1000);
		btn_chooseFile.sendKeys(FrameworkContstants.getSample1());
		Thread.sleep(3000);
		moveToElement_custom(btn_editFile, "Edit");
		click_custom(btn_editFile, "Edit");
		waitUntilClickable(txt_fileName);
		clearDrpdownText(txt_fileName);
		sendKeys_custom(txt_fileName, "Edit Guidelines", "Autosample1");
		waitUntilClickable(btn_saveBtn);
		Thread.sleep(1000);
		click_custom(btn_saveBtn, "Save Guidelines");
		verifyEditedGuidelines();

		return this;

		

	}
	
	public ProjectsPage_EP uploadGuidelineWithInvalidFomrat() throws InterruptedException

	{
		Thread.sleep(1000);
		click_custom(Guidelinespanel, "Guidelines panel");
		Thread.sleep(1000);
		btn_chooseFile.sendKeys(FrameworkContstants.getSample6());
		Thread.sleep(5000);
		return this;

		

	}
	
	public ProjectsPage_EP uploadDuplicateFiles() throws InterruptedException

	{
		Thread.sleep(1000);
		click_custom(Guidelinespanel, "Guidelines panel");
		Thread.sleep(1000);
		btn_chooseFile.sendKeys(FrameworkContstants.getSample1(),"\n",FrameworkContstants.getSample1());
		Thread.sleep(5000);
		return this;

		

	}
	
	public ProjectsPage_EP verifyEditedGuidelines() throws InterruptedException

	{
		
		Thread.sleep(2000);
		Set<String> guidelines = new HashSet<String>(Arrays.asList("Autosample1"));
		Set<String> guidelinesActual = new HashSet<String>();

		List<WebElement> noOFGuidelines = DriverFactoryShaip.getDriver().findElements(By.xpath("//td[1]"));
		String stepsName = null;
		for (int i = 0; i < noOFGuidelines.size(); i++)

		{
			stepsName = getText_custom(noOFGuidelines.get(i), "Uploaded guidelines");
			
				guidelinesActual.add(stepsName);
			
		}
		
		System.out.println(guidelinesActual);
			if (guidelinesActual.containsAll(guidelines) && guidelines.containsAll(guidelinesActual)) {

				ExtentFactoryShaip.getTest().log(Status.PASS,
						"All Guidelines with valid file format edited successfully");
				ExtentFactoryShaip.getTest().info(MarkupHelper.createOrderedList(guidelinesActual).getMarkup());
			} else {

				ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
						"Test case failure screenshot");
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Guidelines has not been edited successfully...");
				ExtentFactoryShaip.getTest()
						.info(MarkupHelper.createLabel(
								"Guidelines has not been edited successfully...",
								ExtentColor.RED));
				ExtentFactoryShaip.getTest().info(MarkupHelper.createOrderedList(guidelinesActual).getMarkup());
			}
			return this;

	}	

	
	
	public ProjectsPage_EP verifyUploadedGuidelines() throws InterruptedException

	{
		Set<String> guidelines = new HashSet<String>(Arrays.asList("QAsample1","QAsample2","QAsample3","QAsample4","QAsample5"));
		Set<String> guidelinesActual = new HashSet<String>();

		List<WebElement> noOFGuidelines = DriverFactoryShaip.getDriver().findElements(By.xpath("//td[1]"));
		String stepsName = null;
		for (int i = 0; i < noOFGuidelines.size(); i++)

		{
			stepsName = getText_custom(noOFGuidelines.get(i), "Uploaded guidelines");
			
				guidelinesActual.add(stepsName);
			
		}
		
		System.out.println(guidelinesActual);
			if (guidelinesActual.containsAll(guidelines) && guidelines.containsAll(guidelinesActual)) {

				ExtentFactoryShaip.getTest().log(Status.PASS,
						"All Guidelines with valid file format uploaded successfully");
				ExtentFactoryShaip.getTest().info(MarkupHelper.createOrderedList(guidelinesActual).getMarkup());
			} else {

				ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
						"Test case failure screenshot");
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Guidelines has not been uploaded successfully...");
				ExtentFactoryShaip.getTest()
						.info(MarkupHelper.createLabel(
								"Guidelines has not been uploaded successfully...",
								ExtentColor.RED));
				ExtentFactoryShaip.getTest().info(MarkupHelper.createOrderedList(guidelinesActual).getMarkup());
			}
			return this;

	}	

	

	
	
	public ProjectsPage_EP editAndVerifyProjectDetails(ProjectType type,ITestContext context) throws InterruptedException

	{
		String catType = ProjectTypeFactory.selectProjectType(type);
		String projectUpdatedName ="QA_Updated" +"("+catType+")"+ getDate();
		String projectDisplayName = "Test_Updated" +"("+catType+")"+ getDate();
		context.setAttribute("ProjectUpdatedName",projectUpdatedName);

		clearDrpdownText(txt_projectName);
		enterProjectName(projectUpdatedName);
		selectStartDate();
		clearDrpdownText(txt_projectDisplayName);
		enterProjectDisplayName(projectDisplayName);
		selectEndDate();

		clearDrpdownText(txt_projectDescription);
		enterProjectDescription("This is project created using automation scripts, Please Do not delete it.Updating Description");
		String projectCategory = getText_custom(drp_ProjectType, "Project Type");
		assert_custom(projectCategory, catType, "Project category");
		submitProject();
		verifyProjectUpdateSuccessToaster();
		return this;

		

	}

	public ProjectsPage_EP verifyProjectSuccessToaster() throws InterruptedException {

		verifyToaster("The project has been added successfully","Project Toaster");

		return this;

	}

	public ProjectsPage_EP verifyProjectUpdateSuccessToaster() throws InterruptedException {

		verifyToaster("The project details has been saved successfully","Project Update Toaster");

		return this;

	}
	
	public ProjectsPage_EP verifyGuidelineErrorToaster() throws InterruptedException {

		
		verifyContainsToaster("The file exceeded the size limit of 2 MB","Project Guideline Toaster");
		
		return this;

	}
	
	public ProjectsPage_EP verifyDuplicateFileErrorToaster() throws InterruptedException {

		verifyContainsToaster("The file already exists","Project Guideline Toaster");

		return this;

	}
	
	public ProjectsPage_EP searchCustomer(String searchEntity) throws InterruptedException

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
	
	public ProjectsPage_EP searchProject(String searchEntity) throws InterruptedException

	{
		waitUntilClickable(txt_globalSearch);
		click_custom(txt_globalSearch, searchEntity);
		sendKeys_custom(txt_globalSearch, "Global Search", searchEntity);
		Thread.sleep(2000);
		return this;
	}
	
	public ProjectsPage_EP verifyProjectPublishedToaster() throws InterruptedException {

		
		verifyToaster("The project has been published successfully","Project Published Status Toaster");

		return this;

	}
	
	public ProjectsPage_EP verifyProjectActivatedToaster() throws InterruptedException {

		
		verifyToaster("The project has been activated successfully","Project Activated Status Toaster");

		return this;

	}
	
	public ProjectsPage_EP verifyProjectPausedToaster() throws InterruptedException {

		
		verifyToaster("The project has been paused successfully","Project Paused Status Toaster");
		return this;

	}
	public ProjectsPage_EP verifyProjectResumedToaster() throws InterruptedException {

	

		verifyToaster("The project has been resumed successfully","Project Resumed Status Toaster");
		return this;

	}
	
	public ProjectsPage_EP verifyProjectCompletedToaster() throws InterruptedException {


		verifyToaster("The project has been completed successfully","Project Completed Status Toaster");

		return this;

	}
	
	public ProjectsPage_EP verifyProjectArchivedToaster() throws InterruptedException {

		verifyToaster("The project has been archived successfully","Project Archived Status Toaster");

		return this;

	}
	
	public ProjectsPage_EP verifyProjectUnArchivedToaster() throws InterruptedException {

		
		verifyToaster("The project has been unarchived successfully","Project Unarchived Status Toaster");

		return this;

	}



}

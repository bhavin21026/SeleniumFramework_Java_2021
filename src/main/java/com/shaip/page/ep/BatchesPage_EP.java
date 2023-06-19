package com.shaip.page.ep;

import java.util.Arrays;
import java.util.HashMap;
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
import com.shaip.base.FrameworkContstants;
import com.shaip.enums.ProjectType;
import com.shaip.enums.story;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.factories.ProjectTypeFactory;
import com.shaip.page.cp.NotificationsPage_CP;
import com.shaip.reportng.ExtentFactoryShaip;

public class BatchesPage_EP extends ActionEngineShaip {

	public BatchesPage_EP() {

		
		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	// Project elements
	
	@FindBy(id = "dtGlobalSearch")
	WebElement txt_globalSearch;


	@FindBy(id = "batches")
	private WebElement lnk_batches;

	@FindBy(id = "add")
	private WebElement btn_addNewBatch;

	@FindBy(xpath = "//div[contains(@class,'mat-dialog-title')]")
	WebElement hdr_addBatchTitle;

	@FindBy(xpath = "//div[@class='content']//p")
	private WebElement toaster_Message;

	@FindBy(xpath = "//mat-icon[text()='clear']")
	private WebElement btn_closeToaster;

	@FindBy(name = "name")
	private WebElement txt_batchName;

	@FindBy(name = "total")
	private WebElement txt_total;

	@FindBy(xpath = "(//div[@role='checkbox' and contains(@class,'p-checkbox-box p-component')])")
	private WebElement chk_members;

	@FindBy(id = "saveButton")
	private WebElement btn_saveBatch;

	@FindBy(id = "removeTeamMember")
	private WebElement btn_removeTeamMember;

	@FindBy(id = "cdConfirmButton")
	private WebElement btn_conRemoveTeamMember;

	// file upload local/ cloud

	@FindBy(id = "cloudUploadButton")
	private WebElement btn_cloudUpload;

	@FindBy(id = "localUploadButton")
	private WebElement btn_localUpload;

	@FindBy(id = "cloudProviderAmazonS3")
	private WebElement rdo_amazoneS3;

	@FindBy(id = "accessKey")
	private WebElement txt_accessKey;

	@FindBy(id = "secretKey")
	private WebElement txt_secreatKey;

	@FindBy(id = "bucketName")
	private WebElement txt_bucketName;

	@FindBy(id = "fileType")
	private WebElement drp_fileType;

	@FindBy(id = "folderName")
	private WebElement txt_folderName;

	@FindBy(id = "region")
	private WebElement drp_Region;

	@FindBy(xpath = "//button[@name='testIDCSButton' and @id='testIDCSButton']")
	private WebElement btn_TestConnection;

	@FindBy(id = "syncIDCSButton")
	private WebElement btn_SyncFiles;

	@FindBy(xpath = "//span[@class='mat-option-text']//parent::mat-option")
	private List<WebElement> drpdwnTupple;

	@FindBy(xpath = "//div[contains(@class,'mat-dialog-title') and contains(@id,'mat-dialog-title-6')]")
	private WebElement syncDialogue;

	@FindBy(id = "cdConfirmButton")
	private WebElement btn_conSyncFiles;

	@FindBy(id = "closeUDLSIcon")
	private WebElement btn_closeUploadSection;

	@FindBy(id = "REFRESH")
	private WebElement btn_refreshBatches;
	
	@FindBy(xpath = "//td[4]")
	WebElement lbl_Status;
	
	@FindBy(xpath = "//td[5]")
	WebElement lbl_Total;
	
	@FindBy(xpath = "//td[6]")
	WebElement lbl_ToDo;
	
	@FindBy(xpath = "//td[7]")
	WebElement lbl_Annotate;
	
	@FindBy(xpath = "//td[8]")
	WebElement lbl_QA;
	
	@FindBy(xpath = "//td[10]")
	WebElement lbl_Comlete;
	
	@FindBy(xpath = "//td[9]")
	WebElement lbl_Discard;
	
	@FindBy(id = "fileUploadInput")
	WebElement btn_chooseFile;
	
	@FindBy(id = "chooseButton")
	WebElement btn_uploadFile;
	
	@FindBy(id = "uploadLSButton")
	WebElement btn_upload;
	
	@FindBy(xpath = "//td[3]")
	WebElement lbl_batchName;
	
	
	public BatchesPage_EP uploadAudioFilesFromLocal() throws InterruptedException

	{
		Thread.sleep(1000);
		waitUntilClickable(btn_uploadFile);		
		Thread.sleep(1000);
		btn_chooseFile.sendKeys(FrameworkContstants.getAudio1(),"\n",FrameworkContstants.getAudio2());
		waitUntilClickable(btn_upload);	
		Thread.sleep(1000);
		click_custom(btn_upload, "Upload Local Audio");
		verifyLocalFilesUploadSuccessToaster();
		return this;

		

	}

	public void selectFileType(String region) throws InterruptedException

	{

		click_custom(drp_fileType, "fileType Dropdown");
		Thread.sleep(1000);
		List<WebElement> genderToBeSelected = drpdwnTupple;
		// System.out.println(countryToBeSelected.size());

		for (int i = 0; i < genderToBeSelected.size(); i++) {

			String regionName = getText_custom(genderToBeSelected.get(i), "Region selection");

			if (regionName.equalsIgnoreCase(region))

			{
				click_custom(genderToBeSelected.get(i), regionName);
				Thread.sleep(2000);

				break;
			}

		}

	}

	public void selectRegion(String file) throws InterruptedException

	{

		click_custom(drp_Region, "Region Dropdown");
		Thread.sleep(1000);
		List<WebElement> genderToBeSelected = drpdwnTupple;
		// System.out.println(countryToBeSelected.size());

		for (int i = 0; i < genderToBeSelected.size(); i++) {

			String filetyps = getText_custom(genderToBeSelected.get(i), "fileType selection");

			if (filetyps.equalsIgnoreCase(file))

			{
				click_custom(genderToBeSelected.get(i), filetyps);
				Thread.sleep(2000);

				break;
			}

		}

	}

	public BatchesPage_EP setupAmazonS3ForSync(String AK,String SK,String bucket,String folder) throws InterruptedException

	{
		Thread.sleep(2000);
		waitUntilClickable(txt_accessKey);
		giveAccessKey(AK);
		giveSecreatKey(SK);
		giveBucketName(bucket);
		selectRegion("US East (N. Virginia) us-east-1");
		giveFolderName(folder);
		selectFileType("wav");
		testConnection();
		syncConnection();
		verifySyncConfirmation();
		return this;
	}
	
	public BatchesPage_EP searchBatch(String searchEntity) throws InterruptedException

	{
		waitUntilClickable(txt_globalSearch);
		clearDrpdownText(txt_globalSearch);
		Thread.sleep(1000);
		click_custom(txt_globalSearch, searchEntity);
		sendKeys_custom(txt_globalSearch, "Global Search", searchEntity);
		Thread.sleep(2000);
		return this;
	}

	public BatchesPage_EP isFilesUploadedSuccessfully(String noOfFiles,String validFiles,String invalidFiles) throws InterruptedException

	{
		
		WebElement sync=DriverFactoryShaip.getDriver().findElement(By.xpath("(//mat-panel-title)[2]"));
		String syncText=getText_custom(sync, "is Sync Completed");
		assert_contains(syncText, "("+noOfFiles+"/"+noOfFiles+")", "Sync Files");
		waitUntilClickable(btn_closeUploadSection);
		click_custom(btn_closeUploadSection, "Close Upload Section");
		verifyTableGridForAudioUpload(noOfFiles, validFiles, invalidFiles);
		return this;
	}
	
	public BatchesPage_EP isLocalFilesUploadedSuccessfully(String noOfFiles,String validFiles,String invalidFiles) throws InterruptedException

	{
		
		WebElement sync=DriverFactoryShaip.getDriver().findElement(By.xpath("(//mat-panel-title)[2]"));
		String syncText=getText_custom(sync, "is local upload Completed");
		assert_contains(syncText, "("+noOfFiles+"/"+noOfFiles+")", "local upload Files");
		waitUntilClickable(btn_closeUploadSection);
		click_custom(btn_closeUploadSection, "Close Upload Section");
		/*Thread.sleep(5000);
		verifyTableGridForAudiUpload(noOfFiles, validFiles, invalidFiles);*/
		return this;
	}
	
	public BatchesPage_EP verifyTableGridForAudioUpload(String files,String validFiles,String invalidFiles) throws InterruptedException
	{
		
		waitUntilClickable(btn_refreshBatches);
		click_custom(btn_refreshBatches, "Refresh Batch");
		verifyForRefreshBatchToaster();
		waitUntilClickable(btn_refreshBatches);
		
		if(Integer.parseInt(files)>=5)
		{
			Thread.sleep(25000);
			click_custom(btn_refreshBatches, "Refresh Batch");
			verifyForRefreshBatchToaster();
			waitUntilClickable(btn_refreshBatches);
			
		}
		else
		{
			Thread.sleep(10000);
			click_custom(btn_refreshBatches, "Refresh Batch");
			verifyForRefreshBatchToaster();
			waitUntilClickable(btn_refreshBatches);
			
		}
		assert_custom(getText_custom(lbl_Status, "BatchStatus"), "Ready", "Batch Status");
		assert_custom(getText_custom(lbl_Total, "Total Files"),files, "Totals Files Uploaded");
		assert_custom(getText_custom(lbl_ToDo, "To Do"),"0", "To Do Files Uploaded");
		assert_custom(getText_custom(lbl_Annotate, "Annotate Files"),validFiles, "to be Annotated Files");
		assert_custom(getText_custom(lbl_QA, "QA Files"),"0", "To be QA files");
		assert_custom(getText_custom(lbl_Comlete, "Complete Files"),"0", "Completed files");
		assert_custom(getText_custom(lbl_Discard, "Discard Files"),invalidFiles, "Discarded files");



		return this;
	}
	public BatchesPage_EP giveAccessKey(String accessKey) throws InterruptedException

	{

		waitUntilClickable(txt_accessKey);
		sendKeys_custom(txt_accessKey, "Access Key", accessKey);
		return this;
	}
	
	

	public BatchesPage_EP giveSecreatKey(String secreatKey) throws InterruptedException

	{

		waitUntilClickable(txt_secreatKey);
		sendKeys_custom(txt_secreatKey, "Secreat Key", secreatKey);
		return this;
	}

	public BatchesPage_EP giveBucketName(String bucket) throws InterruptedException

	{

		waitUntilClickable(txt_bucketName);
		sendKeys_custom(txt_bucketName, "Bucket Name", bucket);
		return this;
	}

	public BatchesPage_EP giveFolderName(String folder) throws InterruptedException

	{

		waitUntilClickable(txt_folderName);
		sendKeys_custom(txt_folderName, "folder name", folder);
		return this;
	}

	public BatchesPage_EP testConnection() throws InterruptedException

	{
		Thread.sleep(2000);
		waitUntilClickable(btn_TestConnection);
		moveToElement_custom(btn_TestConnection, "Test Connection");
		click_custom(btn_TestConnection, "Test Connection");
		verifyForConnectionToasterMessage();
		Thread.sleep(1000);
		return this;
	}

	public BatchesPage_EP syncConnection() throws InterruptedException

	{

		waitUntilClickable(btn_SyncFiles);
		moveToElement_custom(btn_SyncFiles, "Sync files");
		click_custom(btn_SyncFiles, "Sync files");
		Thread.sleep(1000);
		return this;
	}

	public BatchesPage_EP verifySyncConfirmation() throws InterruptedException

	{

		// waitForVisibility(syncDialogue);
		click_custom(btn_conSyncFiles, "Sync confirm");
		waitForProcessBarToGo();
		Thread.sleep(1000);
		return this;
	}

	public BatchesPage_EP clickOnAmazoneS3() throws InterruptedException

	{
		Thread.sleep(1000);
		waitUntilClickable(rdo_amazoneS3);
		click_custom(rdo_amazoneS3, "Amazone S3");
		return this;

	}
	
	public BatchesPage_EP clickOnAmazoneS3Close() throws InterruptedException

	{
		waitUntilClickable(btn_closeUploadSection);
		click_custom(btn_closeUploadSection, "Close s3 Upload");
		return this;

	}
	
	public BatchesPage_EP clickOnRefreshBatch() throws InterruptedException

	{
		waitUntilClickable(btn_refreshBatches);
		click_custom(btn_refreshBatches, "Refresh Batch Icon");
		verifyForRefreshBatchToaster();
		return this;

	}


	public BatchesPage_EP clickOnLoaclUpload() throws InterruptedException

	{

		moveToElement_custom(btn_localUpload, "Local Upload");
		Thread.sleep(1000);
		waitUntilClickable(btn_localUpload);
		click_custom(btn_localUpload, "Local Upload");
		waitForProcessBarToGo();
		return this;

	}

	public BatchesPage_EP clickOnCloudUpload() throws InterruptedException

	{

		moveToElement_custom(btn_cloudUpload, "Cloud Upload");
		Thread.sleep(1000);
		waitUntilClickable(btn_cloudUpload);
		click_custom(btn_cloudUpload, "Cloud Upload");
		return this;

	}

	public BatchesPage_EP clickOnRemoveMember() throws InterruptedException

	{

		waitUntilClickable(btn_removeTeamMember);
		Thread.sleep(1000);
		click_custom(btn_removeTeamMember, "Remove");
		waitUntilClickable(btn_conRemoveTeamMember);
		click_custom(btn_conRemoveTeamMember, "Confirm Remove");
		waitForProcessBarToGo();
		return this;

	}

	public BatchesPage_EP clickOnBatches() throws InterruptedException

	{

		waitUntilClickable(lnk_batches);
		Thread.sleep(1000);
		click_custom(lnk_batches, "Batch");
		waitForProcessBarToGo();
		waitUntilClickable(btn_addNewBatch);
		return this;

	}

	public BatchesPage_EP clickOnSave() throws InterruptedException

	{

		waitUntilClickable(btn_saveBatch);
		click_custom(btn_saveBatch, "Save Batch");
		Thread.sleep(1000);
		return this;

	}

	public BatchesPage_EP clickOnAddBatchButton()

	{
		waitUntilClickable(btn_addNewBatch);
		click_custom(btn_addNewBatch, "Add new batch Button");
		waitForVisibility(hdr_addBatchTitle);
		String projectTitle = getText_custom(hdr_addBatchTitle, "Add Batch");
		assert_contains(projectTitle, "Add Batch", "Add Project Screen");
		waitUntilClickable(txt_batchName);
		return this;

	}

	public BatchesPage_EP addNewBatch(ITestContext context, ProjectType type,String BatchName,String total) throws InterruptedException

	{
		String catType = ProjectTypeFactory.selectProjectType(type);

		if (catType.equalsIgnoreCase("Audio Collection")) {
			String name = BatchName+"_AC";
			String totals = total;
			context.setAttribute("batch", name);
			clickOnAddBatchButton();
			giveBatchName(name);
			giveBatchTotal(totals);
			clickOnSave();
			verifyBatchCreationSuccessToaster_AC();

		} else {

			String name = BatchName+"_AT";
			context.setAttribute("batch", name);
			clickOnAddBatchButton();
			giveBatchName(name);
			clickOnSave();
			verifyBatchCreationSuccessToaster_AT();

		}

		return this;

	}
	
	public BatchesPage_EP addNewBatch(ITestContext context, ProjectType type,String BatchName,String total,int count) throws InterruptedException

	{
		String catType = ProjectTypeFactory.selectProjectType(type);
		
		for(int i=1;i<=count;i++)
		{
		
		if (catType.equalsIgnoreCase("Audio Collection")) {
			String name = BatchName+"_AC_"+i;
			String totals = total;
			context.setAttribute("batch", name);
			clickOnAddBatchButton();
			giveBatchName(name);
			giveBatchTotal(totals);
			clickOnSave();
			verifyBatchCreationSuccessToaster_AC();
			verifiedCreatedBatch(context.getAttribute("batch").toString());

		} else {

			String name = BatchName+"_AT_"+i;
			context.setAttribute("batch", name);
			clickOnAddBatchButton();
			giveBatchName(name);
			clickOnSave();
			verifyBatchCreationSuccessToaster_AT();
			verifiedCreatedBatch(context.getAttribute("batch").toString());

		}
		}
		return this;

	}
	
	public BatchesPage_EP verifyWithUplaodingInvalidFiles(ITestContext context,String UserRole,String methodName, HashMap<String, String> testData) throws InterruptedException

	{

		setupExtentReport("EP: Verify that project having configurations like ("+testData.get("Rate")+"-"+testData.get("Depth")+") should not allow to upload any other audio files having different configurations" , methodName,testData.get("UserRole"),story.DataImportFromCloudUpload_2824.toString(), "SMOKE", "EP");
		giveTestRoleInfo(testData,"UserRole");
		
		Thread.sleep(2000);
      new BatchesPage_EP()
		 .addNewBatch(context,ProjectType.AudioTranscription,testData.get("NewBatch"),testData.get("BatchTotal"))
		 .verifiedCreatedBatch(context.getAttribute("batch").toString())
		 .clickOnCloudUpload()
		 .setupAmazonS3ForSync(testData.get("AccessKey"),testData.get("Skey"),testData.get("BucketName"),testData.get("InvalidFiles"))
		 .isFilesUploadedSuccessfully("1", "0", "1");
		return this;
	}

	public BatchesPage_EP giveBatchTotal(String total) throws InterruptedException

	{

		waitUntilClickable(txt_total);
		sendKeys_custom(txt_total, "Total", total);
		return this;
	}

	public BatchesPage_EP giveBatchName(String name) throws InterruptedException

	{

		waitUntilClickable(txt_batchName);
		click_custom(txt_batchName, "Batch name");
		sendKeys_custom(txt_batchName, "Batch name", name);
		return this;
	}

	public BatchesPage_EP verifiedCreatedBatch(String searchEntity) throws InterruptedException

	{
		
		searchBatch(searchEntity);
		assert_custom(getText_custom(lbl_batchName, "Batch Name"), searchEntity, "Batch Name");
		waitUntilClickable(btn_addNewBatch);
		Thread.sleep(1000);
		return this;

	}

	public BatchesPage_EP verifyBatchCreationSuccessToaster_AC() throws InterruptedException {

		verifyToaster(
				"The batch has been created successfully. The process of creating tasks may take a couple of minutes, kindly refresh to see the Total count",
				"AC_Batch toaster");
		return this;

	}
	
	public BatchesPage_EP verifyLocalFilesUploadSuccessToaster() throws InterruptedException {

		verifyToaster("The file(s) have been uploaded successfully","Local file upload toaster");
		return this;

	}

	public BatchesPage_EP verifyBatchCreationSuccessToaster_AT() throws InterruptedException {

		verifyToaster("The batch has been created successfully", "AT Batch Toaster");
		return this;

	}

	public BatchesPage_EP verifyForConnectionToasterMessage() throws InterruptedException {

		verifyToaster("The connection has been established successfully", "Connection established Toaster");

		return this;

	}

	public BatchesPage_EP verifyForRefreshBatchToaster() throws InterruptedException {

		verifyToaster("The batches list has been refreshed successfully", "Refresh Batch Toaster");

		return this;

	}

	

	public BatchesPage_EP verifyRemoveTeamMemberSuccessToaster() throws InterruptedException {

		verifyToaster("The members(s) have been removed successfully", "Team member Toaster");

		return this;

	}

	public BatchesPage_EP verifyCreateBatchPermissionAccess(String user, String createPermission)
			throws InterruptedException

	{

		if (createPermission.equalsIgnoreCase("Yes")) {
			clickOnAddBatchButton();
		} else {
			verifyCreateBatchButtonIsDisabled(user);
		}

		sa.assertAll();
		return this;

	}

	public void verifyCreateBatchButtonIsDisabled(String user)

	{
		boolean isDisabled = false;
		try {
			String classes = btn_addNewBatch.getAttribute("disabled");
			isDisabled = classes.equalsIgnoreCase("true");

			if (isDisabled) {
				sa.assertTrue(isDisabled,
						"Create Batch button should not be visible to this user as expected----> " + user);
				ExtentFactoryShaip.getTest().log(Status.PASS,
						"Create Batch button should  be visible to this user as expected ---->  " + user);
				sa.assertAll();

			} else {
				sa.assertTrue(isDisabled, "Create Batch button should not be visible to this user----> " + user);
				ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
						"Test case failure screenshot");
				ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper.createLabel(
						"Create Batch button should not be visible to this user ---->  " + user, ExtentColor.RED));
				sa.assertAll();

			}

		} catch (Exception e) {
			e.printStackTrace();
			sa.assertTrue(isDisabled, "Create Batch button should not be visible to this user----> " + user);
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper.createLabel(
					"Create Batch button should not be visible to this user ---->  " + user, ExtentColor.RED));
			sa.assertAll();
		}

	}

}

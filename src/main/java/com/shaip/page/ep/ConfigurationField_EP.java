package com.shaip.page.ep;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
import com.shaip.base.ProjectConfigsAT;
import com.shaip.enums.ProjectType;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.factories.ProjectTypeFactory;
import com.shaip.reportng.ExtentFactoryShaip;

public class ConfigurationField_EP extends ActionEngineShaip {

	public ConfigurationField_EP() {

		
		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	// collection

	@FindBy(id = "settings")
	private WebElement lnk_settings;

	@FindBy(xpath = "(//div[@role='tab'])[1]")
	private WebElement tab_configFields;

	@FindBy(id = "fileFormat")
	private WebElement rd_fileFormat;

	@FindBy(id = "samplingRate")
	private WebElement drp_samplingRate;

	@FindBy(id = "sampleDepth")
	private WebElement drp_sampleDepth;

	@FindBy(id = "minDuration")
	private WebElement drp_minDuration;

	@FindBy(id = "maxDuration")
	private WebElement drp_maxDuration;

	@FindBy(id = "mono channels")
	private WebElement rd_monochannels;

	@FindBy(id = "distributionChannel")
	private WebElement chk_distributionChannel;

	@FindBy(name = "web")
	private WebElement chk_web;

	@FindBy(name = "mobile")
	private WebElement chk_mobile;

	@FindBy(xpath = "//div[@class='content']//p")
	private WebElement toaster_Message;

	@FindBy(xpath = "//mat-icon[text()='clear']")
	private WebElement btn_closeToaster;

	@FindBy(id = "headerBackIcon")
	private WebElement btn_headerBack;

	@FindBy(id = "dtGlobalSearch")
	private WebElement txt_globalSearch;

	@FindBy(xpath = "//span[@class='mat-option-text']//parent::mat-option")
	List<WebElement> drpdwnTupple;

	// transcription

	@FindBy(id = "channels")
	private WebElement chk_channel;

	@FindBy(name = "mono")
	private WebElement chk_mono;

	@FindBy(name = "stereo")
	private WebElement chk_stereo;

	@FindBy(id = "yes multipleSegments-input")
	private WebElement chk_YesMultiSegment;

	@FindBy(id = "no multipleSegments")
	private WebElement chk_NoMultiSegment;

	@FindBy(id = "yes autoSegmentation")
	private WebElement chk_YesAutoSegment;

	@FindBy(id = "no autoSegmentation")
	private WebElement chk_NoAutoSegment;

	@FindBy(xpath = "(//button[@id='controlConfig.key'])[1]")
	private WebElement btn_TagEdit;

	@FindBy(xpath = "(//button[@id='controlConfig.key'])[2]")
	private WebElement btn_EnclosedTagEdit;

	@FindBy(xpath = "(//button[@id='controlConfig.key'])[3]")
	private WebElement btn_FillerWordsEdit;

	@FindBy(id = "dynamicFormSaveButton")
	private WebElement btn_saveConfigurationFields;

	@FindBy(id = "saveCFButton")
	private WebElement btn_saveCustomField;

	@FindBy(id = "web distributionChannel-input")
	private WebElement chk_webDistributionChannel;
	
	@FindBy(xpath = "(//div[@role='tab'])[2]")
	private WebElement tab_CustomFieled;

	@FindBy(name = "label")
	private WebElement txt_tagLabel;
	@FindBy(name = "description")
	private WebElement txt_tagDescription;
	@FindBy(id = "namePV")
	private WebElement txt_name;
	@FindBy(id = "labelPV")
	private WebElement txt_label;
	@FindBy(id = "prefixPV")
	private WebElement txt_prefix;
	@FindBy(id = "suffixPV")
	private WebElement txt_suffix;
	@FindBy(id = "submitPVButton")
	private WebElement txt_submitPV;

	@FindBy(id = "isEditable-input")
	private WebElement btn_Editable;
	@FindBy(id = "isVisible-input")
	private WebElement btn_Visible;
	@FindBy(id = "isRequired-input")
	private WebElement btn_Required;
	
	
	
	public ConfigurationField_EP clickOnHeaderBack() throws InterruptedException

	{

		waitUntilClickable(btn_headerBack);
		click_custom(btn_headerBack,"Back to Project");
		waitForProcessBarToGo();
		Thread.sleep(1000);
		waitUntilClickable(txt_globalSearch);
		return this;

	}

	public ConfigurationField_EP setupConfigFieldsForAC(String SR, String SD, String min, String max,String selection) throws InterruptedException

	{
			selectSamplingRateAC(SR,selection);
			selectSampleDepthAC(SD,selection);
			enterMaxDuration(max);
			enterMinDuration(min);
			clickOnSumbitSetup();


		return this;

	}

	public ConfigurationField_EP setupConfigFieldsForAT(String SR, String SD, String min, String max,String selection)
			throws InterruptedException

	{
		selectSamplingRateAT(SR,selection);
		selectSampleDepthAT(SD,selection);
		enterMaxDuration(max);
		enterMinDuration(min);
		
		clickOnSumbitSetup();
		return this;

	}
	
	public ConfigurationField_EP setupAllConfigFieldsForAT(String SR, String SD, String min, String max,String selection)
			throws InterruptedException

	{
		selectSamplingRateAT(SR,selection);
		selectSampleDepthAT(SD,selection);
		enterMaxDuration(max);
		enterMinDuration(min);
		createTags();
		createEnclosedTags();
		createFillerWords();
		clickOnSumbitSetup();
		return this;

	}

	public ConfigurationField_EP createTags() throws InterruptedException

	{

		waitUntilClickable(btn_TagEdit);
		click_custom(btn_TagEdit, "Edit Tag");
		String labels = ProjectConfigsAT.setTagLabel();
		String Des =ProjectConfigsAT.setTagDescription();
		waitUntilClickable(txt_tagLabel);
		clearDrpdownText(txt_tagLabel);
		click_custom(txt_tagLabel, "Tag Label");
		Thread.sleep(2000);
		sendKeys_custom(txt_tagLabel, "Tag Label", labels);
		sendKeys_custom(txt_tagDescription, "Tag field Description", Des);
		verifySwitchOFFStates(btn_Editable, "Is Editable");
		verifySwitchOnStates(btn_Visible, "Is Visible");
		verifySwitchOFFStates(btn_Required, "Is Required");
		sendKeys_custom(txt_name, "Tag name Key", ProjectConfigsAT.setTag_name());
		sendKeys_custom(txt_label, "Tag lable", ProjectConfigsAT.setTag_label());
		sendKeys_custom(txt_prefix, "Tag prefix", "[");
		sendKeys_custom(txt_suffix, "Tag Suffix", "]");
		click_custom(txt_submitPV, "Add tag Button");
		Thread.sleep(1000);
		waitUntilClickable(btn_saveCustomField);
		click_custom(btn_saveCustomField, "Save custom field");
		verifyTagsSuccessToaster();

		return this;

	}

	public ConfigurationField_EP createEnclosedTags() throws InterruptedException

	{

		waitUntilClickable(btn_EnclosedTagEdit);
		click_custom(btn_EnclosedTagEdit, "Enclosed Tag");
		String labels = ProjectConfigsAT.setenclosedTagLabel();
		String Des =ProjectConfigsAT.setenclosedTagDescription();
		waitUntilClickable(txt_tagLabel);
		clearDrpdownText(txt_tagLabel);
		click_custom(txt_tagLabel, "Tag Label");
		Thread.sleep(2000);
		sendKeys_custom(txt_tagLabel, "Tag Label", labels);
		sendKeys_custom(txt_tagDescription, "Tag field Description", Des);
		verifySwitchOFFStates(btn_Editable, "Is Editable");
		verifySwitchOnStates(btn_Visible, "Is Visible");
		verifySwitchOFFStates(btn_Required, "Is Required");
		sendKeys_custom(txt_name, "Tag name Key", ProjectConfigsAT.setenclosedTag_name());
		sendKeys_custom(txt_label, "Tag lable", ProjectConfigsAT.setenclosedTag_label());
		click_custom(txt_submitPV, "Add tag Button");
		Thread.sleep(1000);
		waitUntilClickable(btn_saveCustomField);
		click_custom(btn_saveCustomField, "Save custom field");
		verifyEnclosedTagsSuccessToaster();

		return this;

	}

	public ConfigurationField_EP createFillerWords() throws InterruptedException

	{

		waitUntilClickable(btn_FillerWordsEdit);
		click_custom(btn_FillerWordsEdit, "Filler Words");
		String labels = ProjectConfigsAT.setFillerLabel();
		String Des = ProjectConfigsAT.setFillerDescription();
		waitUntilClickable(txt_tagLabel);
		clearDrpdownText(txt_tagLabel);
		click_custom(txt_tagLabel, "Tag Label");
		Thread.sleep(2000);
		sendKeys_custom(txt_tagLabel, "Tag Label", labels);
		sendKeys_custom(txt_tagDescription, "Tag field Description", Des);
		verifySwitchOFFStates(btn_Editable, "Is Editable");
		verifySwitchOnStates(btn_Visible, "Is Visible");
		verifySwitchOFFStates(btn_Required, "Is Required");
		sendKeys_custom(txt_name, "Tag name Key", ProjectConfigsAT.setFiller_name());
		sendKeys_custom(txt_label, "Tag lable", ProjectConfigsAT.setFiller_label());
		sendKeys_custom(txt_prefix, "Tag prefix", "#");
		sendKeys_custom(txt_suffix, "Tag Suffix", "#");
		click_custom(txt_submitPV, "Add tag Button");
		Thread.sleep(1000);
		waitUntilClickable(btn_saveCustomField);
		click_custom(btn_saveCustomField, "Save custom field");
		verifyFillerWordsSuccessToaster();

		return this;

	}

	public void verifySwitchOnStates(WebElement element, String locator)

	{
		boolean isDisabled = false;
		try {
			String btnState = element.getAttribute("aria-checked");
			isDisabled = btnState.equalsIgnoreCase("true");

			if (isDisabled) {
				sa.assertTrue(isDisabled, locator + " " + "button has not been in Switch ON mode by default");
				ExtentFactoryShaip.getTest().log(Status.PASS,
						locator + " " + "button is in Switch ON mode by default as expected.");
				sa.assertAll();

			} else {
				ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
						"Test case failure screenshot");
				ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper.createLabel(
						locator + " " + "button has not been in Switch ON mode by default", ExtentColor.RED));
				sa.assertAll();

			}
		} catch (Exception e) {
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper
					.createLabel(locator + " " + "button has not been in Switch ON mode by default", ExtentColor.RED));
			sa.assertAll();
		}

	}

	public void verifySwitchOFFStates(WebElement element, String locator)

	{
		boolean isDisabled = false;
		try {
			String btnState = element.getAttribute("aria-checked");
			isDisabled = btnState.equalsIgnoreCase("false");

			if (isDisabled) {
				sa.assertTrue(isDisabled, locator + " " + "button has not been in Switch OFF mode by default");
				ExtentFactoryShaip.getTest().log(Status.PASS,
						locator + " " + "button is in Switch OFF mode by default as expected.");
				sa.assertAll();

			} else {
				ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
						"Test case failure screenshot");
				ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper.createLabel(
						locator + " " + "button has not been in Switch OFF mode by default", ExtentColor.RED));
				sa.assertAll();

			}
		} catch (Exception e) {
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper
					.createLabel(locator + " " + "button has not been in Switch OFF mode by default", ExtentColor.RED));
			sa.assertAll();
		}

	}

	public ConfigurationField_EP clickOnSumbitSetup() throws InterruptedException

	{

		waitUntilClickable(btn_saveConfigurationFields);
		click_custom(btn_saveConfigurationFields, "Config field setup");
		verifyConfigurationSetupSuccessToaster();
		return this;

	}
	
	public ConfigurationField_EP clickOnCustomFieldTab()

	{

		waitUntilClickable(tab_CustomFieled);
		click_custom(tab_CustomFieled, "Custom field Tab");
		return this;

	}

	public ConfigurationField_EP clickOnSettings() throws InterruptedException

	{

		waitUntilClickable(lnk_settings);
		click_custom(lnk_settings, "Project settings");
		waitForProcessBarToGo();
		Thread.sleep(1000);
		waitUntilClickable(tab_configFields);
		return this;

	}

	public ConfigurationField_EP enterMaxDuration(String max) throws InterruptedException

	{

		waitUntilClickable(drp_maxDuration);
		clearDrpdownText(drp_maxDuration);
		click_custom(drp_maxDuration, "Max duration");
		sendKeys_custom(drp_maxDuration, "Max duration", max);
		return this;

	}

	public ConfigurationField_EP enterMinDuration(String min) throws InterruptedException

	{

		waitUntilClickable(drp_minDuration);
		clearDrpdownText(drp_minDuration);
		click_custom(drp_minDuration, "Min duration");
		sendKeys_custom(drp_minDuration, "Min duration", min);
		return this;

	}

	public ConfigurationField_EP selectSamplingRateAT(String tupple,String selection) throws InterruptedException

	{
		clearSamplingRate();
		waitUntilClickable(drp_samplingRate);
		Thread.sleep(1000);
		selectTupple(tupple.trim(),selection,drp_samplingRate);
		return this;

	}
	
	public ConfigurationField_EP selectSamplingRateAC(String tupple,String selection) throws InterruptedException

	{
		waitUntilClickable(drp_samplingRate);
		Thread.sleep(1000);
		selectTupple(tupple.trim(),selection,drp_samplingRate);
		return this;

	}
	
	public ConfigurationField_EP clearSamplingRate() throws InterruptedException

	{
		List<WebElement> tupples=DriverFactoryShaip.getDriver().findElements(By.xpath("//input[@id='samplingRate']//ancestor::div[1]//mat-icon[contains(text(),'cancel')]"));
		for (int i = 0; i < tupples.size(); i++) {

			click_custom(tupples.get(i), "clearning SamplingRates");
			Thread.sleep(2000);
			


		}
		return this;

	}
	
	public ConfigurationField_EP clearSampleDepth() throws InterruptedException

	{
		List<WebElement> tupples=DriverFactoryShaip.getDriver().findElements(By.xpath("//input[@id='sampleDepth']//ancestor::div[1]//mat-icon[contains(text(),'cancel')]"));
		for (int i = 0; i < tupples.size(); i++) {

			click_custom(tupples.get(i), "clearning Sample Depth");
			Thread.sleep(2000);
			


		}
		return this;

	}

	public ConfigurationField_EP selectSampleDepthAT(String tupple,String selection) throws InterruptedException

	{
		clearSampleDepth();
		waitUntilClickable(drp_sampleDepth);
		Thread.sleep(1000);
		selectTupple(tupple,selection,drp_sampleDepth);
		return this;

	}
	
	public ConfigurationField_EP selectSampleDepthAC(String tupple,String selection) throws InterruptedException

	{
		waitUntilClickable(drp_sampleDepth);
		Thread.sleep(1000);
		selectTupple(tupple,selection,drp_sampleDepth);
		return this;

	}

	public void selectTupple(String value, String selection,WebElement element) throws InterruptedException

	{

		click_custom(element, "Sampling rate/depth");
		List<WebElement> genderToBeSelected = drpdwnTupple;
		// System.out.println(countryToBeSelected.size());

		if (selection.equalsIgnoreCase("single")) {
			for (int i = 0; i < genderToBeSelected.size(); i++) {

				

				String values = getText_custom(genderToBeSelected.get(i), "Gender selection");

				if (values.equalsIgnoreCase(value))

				{
					click_custom(genderToBeSelected.get(i), value);
					Thread.sleep(2000);

					break;
				}

			}
		} else {
			
			for (int i = 0; i < genderToBeSelected.size(); i++) {

				click_custom(genderToBeSelected.get(i), value);
				Thread.sleep(2000);
				click_custom(element, "Sampling rate/depth");


			}

		}
	}

	public ConfigurationField_EP verifyAllConfigurationFieldsAvailable(ProjectType type) throws InterruptedException

	{
		String catType = ProjectTypeFactory.selectProjectType(type);

		if (catType.contains("Collection")) {
			isElementDisplayed(rd_fileFormat, type);
			isElementDisplayed(drp_samplingRate, type);
			isElementDisplayed(drp_sampleDepth, type);
			isElementDisplayed(drp_maxDuration, type);
			isElementDisplayed(drp_minDuration, type);
			isElementDisplayed(chk_mobile, type);
			isElementDisplayed(chk_web, type);
			isElementDisplayed(rd_monochannels, type);

		} else {

			isElementDisplayed(rd_fileFormat, type);
			isElementDisplayed(drp_samplingRate, type);
			isElementDisplayed(drp_sampleDepth, type);
			isElementDisplayed(drp_maxDuration, type);
			isElementDisplayed(drp_minDuration, type);
			isElementDisplayed(chk_webDistributionChannel, type);
			isElementDisplayed(chk_mono, type);
			isElementDisplayed(chk_stereo, type);
			isElementDisplayed(chk_NoAutoSegment, type);
			isElementDisplayed(chk_YesAutoSegment, type);
			isElementDisplayed(chk_YesMultiSegment, type);
			isElementDisplayed(chk_NoMultiSegment, type);
			isElementDisplayed(btn_TagEdit, type);
			isElementDisplayed(btn_EnclosedTagEdit, type);
			isElementDisplayed(btn_FillerWordsEdit, type);

		}

		return this;

	}

	public ConfigurationField_EP isElementDisplayed(WebElement element, ProjectType type) throws InterruptedException

	{
		try {
			if (element.isDisplayed()) {
				ExtentFactoryShaip.getTest().log(Status.PASS,
						MarkupHelper.createLabel(element.toString() + " "
								+ "Webelement is present as expected for project type" + " " + type,
								ExtentColor.GREEN));

			}
		} catch (NoSuchElementException e) {

			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),
					"Test case failure screenshot");
			ExtentFactoryShaip.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							element.toString() + " "
									+ "Webelement is not present, it should be displayed for project type" + " " + type,
							ExtentColor.RED));// TODO: handle exception
		}

		sa.assertAll();

		return this;

	}

	public ConfigurationField_EP verifyEnclosedTagsSuccessToaster() throws InterruptedException {

		verifyToaster("The enclosed tags have been updated successfully", "Enclosed Tags  Toaster");
		return this;

	}

	public ConfigurationField_EP verifyFillerWordsSuccessToaster() throws InterruptedException {

		verifyToaster("The filler words have been updated successfully", "Filler words Toaster");

		return this;

	}

	public ConfigurationField_EP verifyTagsSuccessToaster() throws InterruptedException {

		verifyToaster("The tags have been updated successfully", "Tags Toaster");

		return this;

	}

	public ConfigurationField_EP verifyConfigurationSetupSuccessToaster() throws InterruptedException {

		verifyToaster("The configuration settings have been saved successfully", "Configuration fields Toaster");
		return this;

	}

}

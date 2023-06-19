package com.shaip.page.cp;

import java.time.Duration;
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

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.shaip.base.ActionEngineShaip;
import com.shaip.factories.DriverBase;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.reportng.ExtentFactoryShaip;

public class WorkSelection_CP extends ActionEngineShaip {

	public WorkSelection_CP() {

		wait = new WebDriverWait(DriverFactoryShaip.getDriver(), Duration.ofSeconds(10));
		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	// Profile elements

	@FindBy(xpath = "//mat-panel-title[contains(text(),'Work Selection')]//parent::span")
	WebElement workSelectionPanel;

	@FindBy(xpath = "//div[contains(@class,'work-selection-container')]//div[contains(@class,'row ng-star-inserted')]")
	WebElement workSelectionContainer;

	@FindBy(name = "workSelectionOnSave")
	WebElement btn_saveWorkSelection;

	@FindBy(xpath = "//div[@class='content']//p")
	WebElement toaster_Message;

	@FindBy(xpath = "//mat-icon[text()='clear']")
	WebElement btn_closeToaster;

	public WorkSelection_CP clickOnWorkSelection() throws InterruptedException

	{
		waitForProcessBarToGo();
		Thread.sleep(1000);
		click_custom(workSelectionPanel, "Work selection panel");
		return this;

	}

	public WorkSelection_CP clickOnSaveWorkSelection() throws InterruptedException

	{

		Thread.sleep(1000);
		click_custom(btn_saveWorkSelection, "Save Work selection");
		Thread.sleep(1000);
		return this;

	}

	public WorkSelection_CP verifyWorkPreferencesTypeAndCategory() throws InterruptedException

	{

		Thread.sleep(1000);
		Set<String> projectType = new HashSet<String>(Arrays.asList("Audio", "Text", "Image", "Video"));
		Set<String> audioCategory = new HashSet<String>(
				Arrays.asList("Audio Classification", "Audio Collection", "Audio Segmentation", "Audio Transcription"));
		Set<String> videoCategory = new HashSet<String>(
				Arrays.asList("Video Classification", "Video Collection", "Video Segmentation", "Video Annotation"));
		Set<String> imageCategory = new HashSet<String>(
				Arrays.asList("Image Classification", "Image Collection", "Image Annotation"));
		Set<String> textCategory = new HashSet<String>(
				Arrays.asList("Text Extraction", "Text Classification", "Text Annotation"));

		WebElement container1 = DriverFactoryShaip.getDriver()
				.findElement(By.xpath("//div[contains(@class,'work-selection-container')]"));
		WebElement container = container1.findElement(By.xpath("//div[contains(@class,'row ng-star-inserted')]"));

		List<WebElement> sections = container
				.findElements(By.xpath("//div[contains(@class,'col-3 work-selection-item ng-star-inserted')]"));

		System.out.println("No of columns******" + sections.size());

		for (int i = 0; i < sections.size(); i++) {

			WebElement typeTitle = sections.get(i).findElement(By.className("work-selection-item-title"));
			String title = typeTitle.getText().replaceAll("\r\n", " ").replaceAll("\r", " ");
			System.out.println("Title of columns******" + title);

			if (!projectType.contains(title)) {

				//sa.fail(title + "     Project type not belongs to any project type");
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						title + "     Project type not belongs to any project type under work selection");

			}

		}

		List<WebElement> catSections = DriverFactoryShaip.getDriver()
				.findElements(By.xpath("//span[@class='mat-checkbox-label']"));

		System.out.println("Categories******" + catSections.size());

		for (int j = 0; j < catSections.size(); j++) {

			String category = catSections.get(j).getText().replaceAll("\r\n", " ").replaceAll("\r", " ");
			System.out.println("Title of columns******" + category);

			if (audioCategory.contains(category) || videoCategory.contains(category) || textCategory.contains(category)
					|| imageCategory.contains(category))

			{

				List<WebElement> catSelected = DriverFactoryShaip.getDriver()
						.findElements(By.xpath("//span[@class='mat-checkbox-inner-container']//input"));

				String categorySelected = catSelected.get(j).getAttribute("aria-checked");

				if (categorySelected.equalsIgnoreCase("true"))

				{
					click_custom(catSections.get(j), "Checkbox unchecked ");
					Thread.sleep(500);
					click_custom(catSections.get(j), "Checkbox checked ");
				}else {
					
					click_custom(catSections.get(j), "Checkbox checked");
				}

			}

			else {
				//sa.fail(category + "     Category type not belongs to any project type");

				ExtentFactoryShaip.getTest().log(Status.FAIL,
						category + "     Category type not belongs to any project type under work selection");

			}

		}

		sa.assertAll();
		return this;
	}

	public WorkSelection_CP verifyWorkSelectionSuccessToaster() throws InterruptedException {

		
		
		verifyToaster("The Work Selection has been updated Successfully",
				"Work Selection Toaster");

		return this;

	}

	public WorkSelection_CP verifySelectedTypeAndCategory() throws InterruptedException

	{

		Thread.sleep(2000);

		List<WebElement> catSections = DriverFactoryShaip.getDriver()
				.findElements(By.xpath("//span[@class='mat-checkbox-label']//ancestor::mat-checkbox"));

		System.out.println("Categories******" + catSections.size());

		for (int j = 0; j < catSections.size(); j++) {

			String category = catSections.get(j).getText().replaceAll("\r\n", " ").replaceAll("\r", " ");
			System.out.println("Title of columns******" + category);

			List<WebElement> catSelected = DriverFactoryShaip.getDriver()
					.findElements(By.xpath("//span[@class='mat-checkbox-inner-container']//input"));

			String categorySelected = catSelected.get(j).getAttribute("aria-checked");

			if (!categorySelected.equalsIgnoreCase("true"))

			{
				sa.fail(category + "     Type and category selection has not been done");

				ExtentFactoryShaip.getTest().log(Status.FAIL,
						category + "     Type and category selection has not been done");

			}

		}

		sa.assertAll();
		return this;

	}

}

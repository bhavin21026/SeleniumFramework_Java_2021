package shaip_pagecomponents;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.factories.ExtentFactoryShaip;
import com.shaip.factories.TestBaseShaip;

public class WorkSelection_CP extends TestBaseShaip {

	public WorkSelection_CP() {

		wait = new WebDriverWait(DriverFactoryShaip.getInstance().getDriver(), 30);
		PageFactory.initElements(DriverFactoryShaip.getInstance().getDriver(), this);

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

		WebElement container1 = DriverFactoryShaip.getInstance().getDriver()
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

				sa.fail(title + "     Project type not belongs to any project type");
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						title + "     Project type not belongs to any project type under work selection");

			}

		}

		List<WebElement> catSections = DriverFactoryShaip.getInstance().getDriver()
				.findElements(By.xpath("//span[@class='mat-checkbox-label']"));

		System.out.println("Categories******" + catSections.size());

		for (int j = 0; j < catSections.size(); j++) {

			String category = catSections.get(j).getText().replaceAll("\r\n", " ").replaceAll("\r", " ");
			System.out.println("Title of columns******" + category);

			if (audioCategory.contains(category) || videoCategory.contains(category) || textCategory.contains(category)
					|| imageCategory.contains(category))

			{

				List<WebElement> catSelected = DriverFactoryShaip.getInstance().getDriver()
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
				sa.fail(category + "     Category type not belongs to any project type");

				ExtentFactoryShaip.getTest().log(Status.FAIL,
						category + "     Category type not belongs to any project type under work selection");

			}

		}

		sa.assertAll();
		return this;
	}

	public WorkSelection_CP verifyWorkSelectionSuccessToaster() throws InterruptedException {

		
		if(wait.until(ExpectedConditions.visibilityOf(toaster_Message))!=null)
		{
			sa.assertTrue(toaster_Message.isDisplayed(), "Toaster message is not getting displayed");
			String toasterMessages = getText_custom(toaster_Message, "Toaster Message");
			assert_custom(toasterMessages, "The Work Selection has been updated Successfully",
					"Toaster message getting displayed");
			Thread.sleep(1000);
			click_custom(btn_closeToaster, "Close Toaster");
			Thread.sleep(1000);
			sa.assertAll();

			
		}else
		{
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),"Test case failure screenshot");		
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper
					.createLabel("Toaster Message has not been displayed", ExtentColor.RED));
			sa.assertAll();
		}

		
		return this;

	}

	public WorkSelection_CP verifySelectedTypeAndCategory() throws InterruptedException

	{

		Thread.sleep(2000);

		List<WebElement> catSections = DriverFactoryShaip.getInstance().getDriver()
				.findElements(By.xpath("//span[@class='mat-checkbox-label']//ancestor::mat-checkbox"));

		System.out.println("Categories******" + catSections.size());

		for (int j = 0; j < catSections.size(); j++) {

			String category = catSections.get(j).getText().replaceAll("\r\n", " ").replaceAll("\r", " ");
			System.out.println("Title of columns******" + category);

			List<WebElement> catSelected = DriverFactoryShaip.getInstance().getDriver()
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

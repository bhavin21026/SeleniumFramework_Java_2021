package shaip_pagecomponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
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

public class LanguageAbilities_CP extends TestBaseShaip {

	public LanguageAbilities_CP() {

		wait = new WebDriverWait(DriverFactoryShaip.getInstance().getDriver(), 30);
		PageFactory.initElements(DriverFactoryShaip.getInstance().getDriver(), this);

	}

	// Profile elements

	@FindBy(xpath = "//mat-panel-title[contains(text(),'Language Abilities')]//parent::span")
	WebElement languagePanel;

	@FindBy(name = "addLanguage")
	WebElement btn_addLanguage;

	@FindBy(name = "addLanguageCountry")
	WebElement drp_userCountry;

	@FindBy(name = "language")
	WebElement userLanguage;

	@FindBy(name = "region")
	WebElement drp_userRegion;

	@FindBy(xpath = "//span[@class='mat-option-text']")
	List<WebElement> drpdwnTupple;

	@FindBy(xpath = "//span[contains(@class,'mat-radio-label-content')]")
	List<WebElement> radioTupple;

	@FindBy(id = "saveLanguage")
	WebElement btn_saveLangage;

	@FindBy(xpath = "//div[@class='content']//p")
	WebElement toaster_Message;

	@FindBy(id = "toastDismissButton")
	WebElement btn_closeToaster;

	@FindBy(id =  "editLanguage")
	WebElement btn_editLanguageAbilities;

	@FindBy(id = "deleteLanguage")
	WebElement deleteLanguageAbilities;

	@FindBy(xpath = "//span[contains(text(),'Delete')]//parent::button")
	WebElement btn_deleteLanguage;

	public LanguageAbilities_CP clickOnEditLanguageAbilities() throws InterruptedException

	{

		Thread.sleep(1000);
		click_custom(btn_editLanguageAbilities, "Edit Language Abilities");
		System.out.println("Edit button clicked");

		//waitForProcessBarToGo();
		return this;

	}

	public LanguageAbilities_CP checkForExistingLanguage() throws InterruptedException

	{
		System.out.println("Checking for existing language");

		if (DriverFactoryShaip.getInstance().getDriver().findElements(By.xpath("(//mat-icon[@mattooltip='Delete'])"))
				.size() != 0)

		{
			List<WebElement> languagedAdded = DriverFactoryShaip.getInstance().getDriver()
					.findElements(By.xpath("(//mat-icon[@mattooltip='Delete'])"));

			for (int i = 0; i < languagedAdded.size(); i++) {
				clickOnDeleteLanguageAbilities();
				System.out.println("Delete existing language");


			}
			Thread.sleep(2000);
		}
		return this;

	}

	public LanguageAbilities_CP clickOnDeleteLanguageAbilities() throws InterruptedException

	{

		Thread.sleep(1000);
		click_custom(deleteLanguageAbilities, "Delete Language Abilities");
		Thread.sleep(1000);
		click_custom(btn_deleteLanguage, "Delete Confirm");
		verifyLanguageDeletedSuccessfullyToaster();

		return this;

	}

	public LanguageAbilities_CP clickOnLanguageAbilitiesSection() throws InterruptedException

	{
		waitForProcessBarToGo();
		Thread.sleep(1000);
		click_custom(languagePanel, "Language selection panel");
		System.out.println("Language ability button clicked");

		return this;

	}

	public LanguageAbilities_CP clickOnAddLanguage() throws InterruptedException

	{
		Thread.sleep(1000);
		click_custom(btn_addLanguage, "Add Language Button");
		Thread.sleep(2000);
		// waitForProcessBarToGo();
		return this;

	}

	public LanguageAbilities_CP clickOnSave() throws InterruptedException

	{
		click_custom(btn_saveLangage, "Save Language Button");
		waitForVisibility(toaster_Message);
      
		//captureScreenshotCustom("SaveLanguageButton",getDate());

		



		return this;

	}

	public LanguageAbilities_CP selectUsersCountry(String countryName) throws InterruptedException

	{
		Thread.sleep(1000);
		click_custom(drp_userCountry, "User Country Dropdown");
		sendKeys_custom(drp_userCountry, "User Country Dropdown", countryName);
		Thread.sleep(1000);
		List<WebElement> countryToBeSelected = drpdwnTupple;
		// System.out.println(countryToBeSelected.size());

		Thread.sleep(1000);

		for (int i = 0; i < countryToBeSelected.size(); i++) {

			String country = countryToBeSelected.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
					.replaceAll("\n", " ");
			System.out.println(countryToBeSelected);
			// String[] menues = menu.split(" ");
			// String menuName = menues[1];

			if (country.equalsIgnoreCase(countryName))

			{
				click_custom(countryToBeSelected.get(i), countryName);
				System.out.println("Country selected"+country);

				try {
					waitForProcessBarToGo();
				} catch (WebDriverException e) {

					e.printStackTrace();
				}
				

				break;
			}

		}
		return this;

	}

	public LanguageAbilities_CP selectLevel(String levelName) throws InterruptedException

	{
		Thread.sleep(1000);

		List<WebElement> radioToBeSelected = radioTupple;

		for (int i = 0; i < radioToBeSelected.size(); i++) {

			String radio = radioToBeSelected.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
					.replaceAll("\n", " ");

			if (radio.equalsIgnoreCase(levelName))

			{
				click_custom(radioToBeSelected.get(i), levelName);
				System.out.println("Level selected");

				Thread.sleep(1000);
				break;
			}

		}
		return this;

	}

	public LanguageAbilities_CP selectUsersLanguage(String languageName) throws InterruptedException

	{
		Thread.sleep(1000);
		click_custom(userLanguage, "User language Dropdown");
		sendKeys_custom(userLanguage, "User language Dropdown", languageName);
		Thread.sleep(1000);
		List<WebElement> languageToBeSelected = drpdwnTupple;
		// System.out.println(countryToBeSelected.size());

		Thread.sleep(1000);

		for (int i = 0; i < languageToBeSelected.size(); i++) {

			String language = languageToBeSelected.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
					.replaceAll("\n", " ");
			//System.out.println(languageToBeSelected);
			// String[] menues = menu.split(" ");
			// String menuName = menues[1];

			if (language.equalsIgnoreCase(languageName))

			{
				click_custom(languageToBeSelected.get(i), languageName);
				System.out.println("Language selected"+language);

				//Thread.sleep(100000);
				//waitForProcessBarToGo();

				try {
					waitForProcessBarToGo();
				} catch (WebDriverException e) {

					e.printStackTrace();
				}
				break;
			}

		}
		return this;

	}

	public LanguageAbilities_CP selectUsersRegion(String region) throws InterruptedException

	{
		Thread.sleep(1000);
		click_custom(drp_userRegion, "User region Dropdown");
		sendKeys_custom(drp_userRegion, "User region Dropdown", region);
		Thread.sleep(1000);
		List<WebElement> regionToBeSelected = drpdwnTupple;
		// System.out.println(countryToBeSelected.size());

		Thread.sleep(1000);

		for (int i = 0; i < regionToBeSelected.size(); i++) {

			String regionName = regionToBeSelected.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
					.replaceAll("\n", " ");
			//System.out.println(regionToBeSelected);
			// String[] menues = menu.split(" ");
			// String menuName = menues[1];

			if (regionName.equalsIgnoreCase(region))

			{
				click_custom(regionToBeSelected.get(i), region);
				System.out.println("Region selected"+regionName);

				Thread.sleep(2000);
				waitUntilClickable(btn_saveLangage);
				System.out.println("Region selected");
				captureScreenshotCustom("Region",getDate());



				break;
			}

		}
		return this;

	}

	public LanguageAbilities_CP selectLanguageAbilities(String language, String country, String level)
			throws InterruptedException

	{

		selectUsersLanguage(language);
		System.out.println("language selected");
		selectUsersCountry(country);
		System.out.println("country selected");
		selectLevel(level);
		System.out.println("level selected");
		captureScreenshotCustom("CreateLanguage",getDate());
		return this;

	}

	public LanguageAbilities_CP editLanguageAbilities(String language, String country, String level)
			throws InterruptedException

	{

		clearDrpdownText(userLanguage);
		selectUsersLanguage(language);
		clearDrpdownText(drp_userCountry);
		selectUsersCountry(country);
		selectLevel(level);

		return this;

	}

	public LanguageAbilities_CP verifyAddLanguageButtonState() throws InterruptedException {

		String state = btn_addLanguage.getAttribute("disabled");

		if (!state.equalsIgnoreCase("True")) {

			sa.fail("Add language button is enabled even after 4 languages are already added");
			ExtentFactoryShaip.getTest().log(Status.FAIL,
					"Add language button is enabled even after 4 languages are already added ");
			sa.assertAll();
		}
		return this;

	}

	public LanguageAbilities_CP verifyLanguageSuccessToaster() throws InterruptedException {

		System.out.println("Lets wait for toaster ");


		if(wait.until(ExpectedConditions.visibilityOf(toaster_Message))!=null)
		{
			
			captureScreenshotCustom("ToasterVisible",getDate());

			System.out.println("toaster message visible");
			sa.assertTrue(toaster_Message.isDisplayed(), "Toaster message is not getting displayed");
			String toasterMessages = getText_custom(toaster_Message, "Toaster Message");
			assert_custom(toasterMessages, "The language ability has been added successfully",
					"Toaster message getting displayed");
			Thread.sleep(1000);
			click_custom(btn_closeToaster, "Close Toaster");
			System.out.println("toaster message read and closed");

			Thread.sleep(1000);
			sa.assertAll();

			
		}else
		{
			
			captureScreenshotCustom("ToasterNotVisible",getDate());

			System.out.println("toaster message not visible");

			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),"Test case failure screenshot");		
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper
					.createLabel("Toaster Message has not been displayed", ExtentColor.RED));
			sa.assertAll();
		}
		
		return this;

	}

	public void verifyLanguageUpdatedSuccessfullyToaster() throws InterruptedException {

		
		if(wait.until(ExpectedConditions.visibilityOf(toaster_Message))!=null)
		{
			System.out.println("Updated toaster message visible");

			sa.assertTrue(toaster_Message.isDisplayed(), "Toaster message is not getting displayed");
			String toasterMessages = getText_custom(toaster_Message, "Toaster Message");
			assert_contains(toasterMessages, "The language ability has been updated successfully",
					"Toaster message getting displayed");
			Thread.sleep(1000);
			click_custom(btn_closeToaster, "Close Toaster");
			System.out.println("Updated toaster message read and closed");

			Thread.sleep(1000);
			sa.assertAll();

			
		}else
		{
			ExtentFactoryShaip.getTest().addScreenCaptureFromBase64String(captureScreenshot(),"Test case failure screenshot");		
			ExtentFactoryShaip.getTest().log(Status.FAIL, MarkupHelper
					.createLabel("Toaster Message has not been displayed", ExtentColor.RED));
			sa.assertAll();
		}

		

	}

	public void verifyLanguageDeletedSuccessfullyToaster() throws InterruptedException {

		
		if(wait.until(ExpectedConditions.visibilityOf(toaster_Message))!=null)
		{
			sa.assertTrue(toaster_Message.isDisplayed(), "Toaster message is not getting displayed");
			String toasterMessages = getText_custom(toaster_Message, "Toaster Message");
			assert_contains(toasterMessages, "The language ability has been deleted successfully",
					" Toaster message getting displayed");
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


	}

	public void verifyDuplicateLanguageErrorToaster() throws InterruptedException {

		
		if(wait.until(ExpectedConditions.visibilityOf(toaster_Message))!=null)
		{
			sa.assertTrue(toaster_Message.isDisplayed(), "Toaster message is not getting displayed");
			String toasterMessages = getText_custom(toaster_Message, "Toaster Message");
			assert_contains(toasterMessages, "The language has already been added",
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

		
		

	}
		

	

}

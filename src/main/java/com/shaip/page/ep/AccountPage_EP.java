package com.shaip.page.ep;

import java.util.List;

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

public class AccountPage_EP extends ActionEngineShaip {

	public AccountPage_EP() {

		
		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	
	//Profile elements
	
	
	@FindBy(name= "txt_firstName")
	WebElement txt_firstName;
	
	@FindBy(name= "txt_lastName")
	WebElement txt_lastName;
	
	
	@FindBy(xpath = "//input[@formcontrolname='txt_email']")
	WebElement txt_email;
	
	@FindBy(xpath = "//mat-select[@role='combobox' and @formcontrolname='genderCode']")
	WebElement drp_UserGender;
	
	@FindBy(xpath = "//input[@formcontrolname='country' and @name='country']")
	WebElement drp_userCountry;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='timeZone' and @role='combobox']")
	WebElement drp_userTimeZone;
	
	@FindBy(xpath = "//input[@formcontrolname='ethnicityCode' and @name='ethnicity']")
	WebElement drp_userEthnicity;
	
	@FindBy(xpath = "//input[@formcontrolname='educationQualificationCode' and @name='educationalQualification']")
	WebElement drp_userEducation;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='displayLanguageCode']")
	WebElement drp_userDisplayLanguage;

	
	@FindBy(xpath = "//input[@formcontrolname='phoneCode' and @name='phoneCode']")
	WebElement drp_userPhoneCode;
	
	@FindBy(name= "contactNumber")
	WebElement txt_userContactNumber;
	
	@FindBy(xpath= "//span[@class='mat-option-text']//parent::mat-option")
	List<WebElement> drpdwnTupple;
	
	@FindBy(xpath="//mat-icon[@role='img' and text()='arrow_drop_down']//ancestor::button[@aria-label='Open calendar']")
	WebElement dt_userBirthDate;

	@FindBy(xpath = "(//label[@class='form-label'])[1]")
	WebElement StartDate;
	
	@FindBy(xpath = "//div[@class='mat-calendar-controls']//child::button[1]//span[1]//span")
	WebElement MonthValue;
	
	@FindBy(xpath = "//button[@aria-label='Previous month']")
	WebElement previousMonth;

	
	@FindBy(xpath = "//button[@aria-label='Next month']")
	WebElement nextMonth;
	
	@FindBy(xpath = "//div[contains(@class,'basic-information-action-button-container')]//span[contains(text(),'Save')]//parent::button")
	WebElement accSavBtn;
	
	@FindBy(xpath = "//div[@class='content']//p")
	WebElement toaster_Message;

	@FindBy(xpath = "//mat-icon[text()='clear']")
	WebElement btn_closeToaster;


	public void submitDetails()

	{

		click_custom(accSavBtn, "Submit Account Details");
		

	}

	public void enterFirstName(String Name)

	{

		click_custom(txt_firstName, "First Name");
		sendKeys_custom(txt_firstName, "First Name", Name);

	}

	public void enterLastName(String lastName)

	{

		click_custom(txt_lastName, "Last Name");
		sendKeys_custom(txt_lastName, "Last Name", lastName);

	}

	public String getEmaiID()

	{

		click_custom(txt_email, "Email");
		return getText_custom(txt_email, "Email");

	}
	
	
	public void enterContactNumber(String contact)

	{

		click_custom(txt_userContactNumber, "Contact Number");
		sendKeys_custom(txt_userContactNumber, "Contact Number", contact);
	}
	
	
	
	
	
	public void selectCountryCode(String getcodes) throws InterruptedException

	{

		String code="+"+getcodes;
		click_custom(drp_userPhoneCode, "code Dropdown");
		sendKeys_custom(drp_userPhoneCode, "User code Dropdown", code);
		Thread.sleep(2000);
		List<WebElement> codeToBeSelected = drpdwnTupple;
		// System.out.println(countryToBeSelected.size());

		for (int i = 0; i < codeToBeSelected.size(); i++) {

			String codes = codeToBeSelected.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
					.replaceAll("\n", " ");

			if (codes.equalsIgnoreCase(code))

			{
				click_custom(codeToBeSelected.get(i), code);
				break;
			}

		}

	}
	
	
	
	public void selectGender(String gender) throws InterruptedException

	{
		System.out.println("in Gender");
		click_custom(drp_UserGender, "Gender Dropdown");
		Thread.sleep(1000);
		List<WebElement> genderToBeSelected = drpdwnTupple;
		// System.out.println(countryToBeSelected.size());

		for (int i = 0; i < genderToBeSelected.size(); i++) {

			String genders = genderToBeSelected.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
					.replaceAll("\n", " ");

			if (genders.equalsIgnoreCase(gender))

			{
				click_custom(genderToBeSelected.get(i), gender);
				System.out.println("Hi debugging");
				Thread.sleep(2000);
				
				break;
			}

		}

	}
	
	
	public void selectUsersCountry(String countryName) throws InterruptedException

	{
		Thread.sleep(1000);
		click_custom(drp_userCountry, "User Country Dropdown");
		sendKeys_custom(drp_userCountry, "User Country Dropdown", countryName);
		Thread.sleep(1000);
		List<WebElement> countryToBeSelected = drpdwnTupple;
		// System.out.println(countryToBeSelected.size());

		Thread.sleep(1000);

		for (int i = 0; i < countryToBeSelected.size(); i++) {

			String country =getText_custom(countryToBeSelected.get(i), "Country Selection");			


			if (country.equalsIgnoreCase(countryName))

			{
				click_custom(countryToBeSelected.get(i), countryName);
				Thread.sleep(1000);
				break;
			}

		}

	}
	
	
	
	public void selectUsersTimeZone(String timezone) throws InterruptedException

	{
		Thread.sleep(1000);
		click_custom(drp_userTimeZone, "User timezone Dropdown");
		sendKeys_custom(drp_userTimeZone, "User timezone Dropdown", timezone);
		Thread.sleep(1000);
		List<WebElement> timezoneToBeSelected = drpdwnTupple;
		// System.out.println(countryToBeSelected.size());

		Thread.sleep(1000);

		for (int i = 0; i < timezoneToBeSelected.size(); i++) {

			String timeZ = timezoneToBeSelected.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
					.replaceAll("\n", " ");
			
			// String[] menues = menu.split(" ");
			// String menuName = menues[1];

			if (timeZ.equalsIgnoreCase(timezone))

			{
				click_custom(timezoneToBeSelected.get(i), timezone);
				break;
			}

		}

	}
	
	
	public void selectUsersEthnicity(String userEthn) throws InterruptedException

	{
		Thread.sleep(1000);
		click_custom(drp_userEthnicity, "User Ethnicity Dropdown");
		sendKeys_custom(drp_userEthnicity, "User Ethnicity Dropdown", userEthn);
		Thread.sleep(1000);
		List<WebElement> ethnicityToBeSelected = drpdwnTupple;
		
		Thread.sleep(1000);

		for (int i = 0; i < ethnicityToBeSelected.size(); i++) {

			String ethnicity = ethnicityToBeSelected.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
					.replaceAll("\n", " ");
			
			// String[] menues = menu.split(" ");
			// String menuName = menues[1];

			if (ethnicity.equalsIgnoreCase(userEthn))

			{
				click_custom(ethnicityToBeSelected.get(i), userEthn);
				break;
			}

		}

	}
	
	public void selectUsersEducation(String educationQua) throws InterruptedException

	{
		Thread.sleep(1000);
		click_custom(drp_userEducation, "User education Dropdown");
		sendKeys_custom(drp_userEducation, "User education Dropdown", educationQua);
		Thread.sleep(1000);
		List<WebElement> educationToBeSelected =drpdwnTupple;
		// System.out.println(countryToBeSelected.size());

		Thread.sleep(1000);

		for (int i = 0; i < educationToBeSelected.size(); i++) {

			String education = educationToBeSelected.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
					.replaceAll("\n", " ");
			
			// String[] menues = menu.split(" ");
			// String menuName = menues[1];

			if (education.equalsIgnoreCase(educationQua))

			{
				click_custom(educationToBeSelected.get(i), educationQua);
				break;
			}

		}

	}
	
	
	public void selectUsersLanguage(String language) throws InterruptedException

	{
		Thread.sleep(1000);
		click_custom(drp_userDisplayLanguage, "User language Dropdown");
		sendKeys_custom(drp_userDisplayLanguage, "User language Dropdown", language);
		Thread.sleep(1000);
		List<WebElement> languageToBeSelected =drpdwnTupple;
		System.out.println(languageToBeSelected.size());

		Thread.sleep(1000);

		for (int i = 0; i < languageToBeSelected.size(); i++) {

			String languages = languageToBeSelected.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ")
					.replaceAll("\n", " ");
		

			if (languages.equalsIgnoreCase(language))

			{
				click_custom(languageToBeSelected.get(i), language);
				break;
			}

		}

	}
	
	
	public void selectUsersBirthDate() throws InterruptedException

	{
	
		
		 String StartMonth= "jul 2021";
		 String StartDate= "28";
	     dt_userBirthDate.click();
	     Thread.sleep(2000);
	     System.out.println("Hi debugging");

	

		// scrollToElement(MonthValue);
		while (!MonthValue.getText().equalsIgnoreCase(StartMonth)) {
			System.out.println(MonthValue.getText()); 
			previousMonth.click();
		}
		WebElement picker = DriverFactoryShaip.getDriver().findElement(By.xpath("//tbody[@class='mat-calendar-body']"));

		List<WebElement> Days = picker.findElements(By.xpath("tr//td[@role='gridcell']//div[1]"));

		int totalelementsfind = Days.size();

		int flag = 0;
		for (int i = 0; i < totalelementsfind; i++) {

			String DayValue  = Days.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ");
				

				if (DayValue.equalsIgnoreCase(StartDate)) {
					 Days.get(i).click();
					flag = 1;
					break;
				}

			}

		

		
	}
	
	
	public AccountPage_EP enterProfileDetails(String gender, String code, String number,String country ,String timezone, String ehinicity, String education,String language ) throws InterruptedException
	{
		
		waitForProcessBarToGo();
		String fname= getAttributes(txt_firstName, "txt_firstName");
		clearText(txt_firstName);
		enterFirstName(fname);
		String lname= getAttributes(txt_lastName, "txt_lastName");
		clearText(txt_lastName);
		enterLastName(lname);
		//clearText(drp_UserGender);
		selectGender(gender);
		selectUsersBirthDate();
		clearText(drp_userPhoneCode);
		selectCountryCode(code);
		clearText(txt_userContactNumber);
		enterContactNumber(number);
		clearText(drp_userCountry);
		selectUsersCountry(country);
		clearText(drp_userTimeZone);
		selectUsersTimeZone(timezone);
		clearText(drp_userEthnicity);
		selectUsersEthnicity(ehinicity);
		clearText(drp_userEducation);
		selectUsersEducation(education);
		clearText(drp_userDisplayLanguage);
		selectUsersLanguage(language);
		Thread.sleep(1000);
		submitDetails();
		waitForProcessBarToGo();
		
		return this;
		
		
		
	}
	
	
	
	
	public AccountPage_EP verifyBasicInformationOfRegisteredUser(String first, String last,String emailIDs, String countryDropdown) throws InterruptedException
	{
		
		waitForProcessBarToGo();
		//click_custom(txt_lastName, "Last Name");
		String fname= getAttributes(txt_firstName, "txt_firstName");
		//click_custom(txt_lastName, "Last Name");
		String lname=getAttributes(txt_lastName, "txt_lastName");
		//click_custom(txt_email, "Email");
		String email=getAttributes(txt_email, "email");
		drp_userCountry.click();
		String country=getAttributes(drp_userCountry, "country");
		
		sa.assertEquals(fname, first, "First name is not matching with registered name");
		sa.assertEquals(lname, last, "Last name is not matching with registered name");
		sa.assertEquals(email, emailIDs, "Email ID is not matching with registered name");
		sa.assertEquals(country, countryDropdown, "Country name is not matching with registered name");
		sa.assertAll();
		
		return this;
		
		
	}
	
	
	public AccountPage_EP verifySuccessToaster() throws InterruptedException {
		
		
		
		verifyToaster("The basic information has been updated successfully","Basic Info Toaster");

		return this;
		

	}
	
	
}

	
	
	

	

	

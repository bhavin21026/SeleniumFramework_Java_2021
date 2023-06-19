package com.shaip.page.cp;

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

public class AccountPage_CP extends ActionEngineShaip {

	public AccountPage_CP() {

		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	
	//Profile elements
	
	
	@FindBy(name= "firstName")
	WebElement txt_firstName;
	
	@FindBy(name= "lastName")
	WebElement txt_lastName;
	
	
	@FindBy(id = "profileBasicInformationEmail")
	WebElement txt_emailAddress;
	
	@FindBy(xpath = "//mat-select[@role='combobox' and @formcontrolname='genderCode']")
	WebElement drp_gender;
	
	@FindBy(name = "country")
	WebElement drp_country;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='timeZone' and @role='combobox']")
	WebElement drp_userTimeZone;
	
	@FindBy(name = "ethnicity")
	WebElement drp_userEthnicity;
	
	@FindBy(name = "educationalQualification")
	WebElement drp_userEducation;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='displayLanguageCode']")
	WebElement drp_userDisplayLanguage;

	
	@FindBy(xpath = "//input[@formcontrolname='phoneCode' and @name='phoneCode']")
	WebElement drp_userPhoneCode;
	
	@FindBy(name= "contactNumber")
	WebElement drp_userContactNumber;
	
	@FindBy(xpath= "//span[@class='mat-option-text']//parent::mat-option")
	List<WebElement> drpdwnTupple;
	
	@FindBy(name="birthDate")
	WebElement cal_userBirthDatePicker;

	@FindBy(xpath = "(//label[@class='form-label'])[1]")
	WebElement dt_StartDate;
	
	@FindBy(xpath = "//div[@class='mat-calendar-controls']//child::button[1]//span[1]//span")
	WebElement MonthValue;
	
	@FindBy(xpath = "//button[@aria-label='Previous month']")
	WebElement btn_previousMonth;

	
	@FindBy(xpath = "//button[@aria-label='Next month']")
	WebElement btn_nextMonth;
	
	@FindBy(name = "basicInformationOnSubmit")
	WebElement btn_accSave;
	
	@FindBy(xpath = "//div[@class='content']//p")
	WebElement toaster_Message;

	@FindBy(xpath = "//mat-icon[text()='clear']")
	WebElement btn_closeToaster;


	public void submitDetails()

	{

		waitUntilClickable(btn_accSave);
		click_custom(btn_accSave, "Submit Account Details");
		

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

		click_custom(txt_emailAddress, "Email");
		return getText_custom(txt_emailAddress, "Email");

	}
	
	
	public void enterContactNumber(String contact)

	{

		click_custom(drp_userContactNumber, "Contact Number");
		sendKeys_custom(drp_userContactNumber, "Contact Number", contact);
	}
	
	
	
	
	
	public void selectCountryCode(String getcodes) throws InterruptedException

	{
		//String code="+"+getcodes;

		String code=getcodes;
		click_custom(drp_userPhoneCode, "code Dropdown");
		sendKeys_custom(drp_userPhoneCode, "User code Dropdown", code);
		Thread.sleep(2000);
		List<WebElement> codeToBeSelected = drpdwnTupple;
		// System.out.println(countryToBeSelected.size());

		for (int i = 0; i < codeToBeSelected.size(); i++) {

			String codes = getText_custom(codeToBeSelected.get(i), "Phone Code");
			if (codes.equalsIgnoreCase(code))

			{
				execute_click(codeToBeSelected.get(i), code);
				break;
			}

		}

	}
	
	
	
	public void selectGender(String gender) throws InterruptedException

	{
		System.out.println("in Gender");
		click_custom(drp_gender, "Gender Dropdown");
		Thread.sleep(1000);
		List<WebElement> genderToBeSelected = drpdwnTupple;
		// System.out.println(countryToBeSelected.size());

		for (int i = 0; i < genderToBeSelected.size(); i++) {

			String genders = getText_custom(genderToBeSelected.get(i), "Gender selection");

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
		click_custom(drp_country, "User Country Dropdown");
		sendKeys_custom(drp_country, "User Country Dropdown", countryName);
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

			String timeZ = getText_custom(timezoneToBeSelected.get(i), "time zone selection");
			
			

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

			String ethnicity = getText_custom(ethnicityToBeSelected.get(i), "Ethinicity selection");
		

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

			String education = getText_custom(educationToBeSelected.get(i), "Education selection");
			
		

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
		//System.out.println(languageToBeSelected.size());

		Thread.sleep(1000);

		for (int i = 0; i < languageToBeSelected.size(); i++) {

			String languages = getText_custom(languageToBeSelected.get(i), "Language selection");
		

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
		 String dt_StartDate= "28";
	     cal_userBirthDatePicker.click();
	     Thread.sleep(2000);
	     System.out.println("Hi debugging");

	

		// scrollToElement(MonthValue);
		while (!MonthValue.getText().equalsIgnoreCase(StartMonth)) {
			System.out.println(MonthValue.getText()); 
			btn_previousMonth.click();
		}
		WebElement picker = DriverFactoryShaip.getDriver().findElement(By.xpath("//tbody[@class='mat-calendar-body']"));

		List<WebElement> Days = picker.findElements(By.xpath("tr//td[@role='gridcell']//div[1]"));

		int totalelementsfind = Days.size();

		int flag = 0;
		for (int i = 0; i < totalelementsfind; i++) {

			String DayValue  = Days.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ");
				

				if (DayValue.equalsIgnoreCase(dt_StartDate)) {
					 Days.get(i).click();
					flag = 1;
					break;
				}

			}

		

		
	}
	
	
	public AccountPage_CP enterProfileDetails(String gender, String code, String number,String country ,String timezone, String ehinicity, String education,String language ) throws InterruptedException
	{
		
		waitForProcessBarToGo();
		String fname= getAttributes(txt_firstName, "txt_firstName");
		clearText(txt_firstName);
		enterFirstName(fname);
		String lname= getAttributes(txt_lastName, "txt_lastName");
		clearText(txt_lastName);
		enterLastName(lname);
		//clearText(drp_gender);
		selectGender(gender);
		selectUsersBirthDate();
		clearText(drp_userPhoneCode);
		selectCountryCode(code);
		clearText(drp_userContactNumber);
		enterContactNumber(number);
		clearText(drp_country);
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
		verifySuccessToaster();
		
		return this;
		
		
		
	}
	
	
	
	
	public void verifyBasicInformationOfRegisteredUser(String first, String last,String emailIDs, String countryDropdown) throws InterruptedException
	{
		System.out.println(first);
		System.out.println(last);
		System.out.println(emailIDs);
		System.out.println(countryDropdown);



		
		waitForProcessBarToGo();
		//click_custom(txt_lastName, "Last Name");
		String fname= getAttributes(txt_firstName, "firstName");
		//click_custom(txt_lastName, "Last Name");
		String lname=getAttributes(txt_lastName, "lastName");
		//click_custom(txt_emailAddress, "Email");
		String email=getAttributes(txt_emailAddress, "email");
		drp_country.click();
		String country=getAttributes(drp_country, "country");
		
		assert_custom(fname, first, "First name is  matching with registered name");
		assert_custom(lname, last, "Last name is  matching with registered name");
		assert_custom(email, emailIDs, "Email ID is  matching with registered name");
		assert_custom(country, countryDropdown, "Country name is  matching with registered name");
		sa.assertAll();
		
		
		
	}
	
	
	public AccountPage_CP verifySuccessToaster() throws InterruptedException {


		verifyToaster("The basic information has been updated successfully","Basic Info Toaster");

		return this;

	}
	
	
}

	
	
	

	

	

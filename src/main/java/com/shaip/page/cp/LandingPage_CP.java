package com.shaip.page.cp;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.shaip.base.ActionEngineShaip;
import com.shaip.factories.DriverBase;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.page.ep.ProjectsPage_EP;
import com.shaip.reportng.ExtentFactoryShaip;
import com.shaip.utils.DB_Operations;

public class LandingPage_CP extends ActionEngineShaip {

	public LandingPage_CP() {

		wait = new WebDriverWait(DriverFactoryShaip.getDriver(), Duration.ofSeconds(10));
		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	@FindBy(xpath = "//a[contains(text(),'shaip.com')]/parent::div")
	WebElement copyRights;

	@FindBy(xpath = "//a[text()='shaip.com']")
	WebElement lnk_shaipURI;

	@FindBy(xpath = "//div[contains(@class,'cdk-overlay-pane')]")
	WebElement termsAndCondition;

	@FindBy(xpath = "//div[@class='mat-dialog-title']")
	WebElement ttl_termsAndCondition;

	@FindBy(name = "agreeButton")
	WebElement btn_agree;

	@FindBy(xpath = "//span[contains(text(),'Disagree and Exit')]//parent::button")
	WebElement btn_disagreeAndExit;

	@FindBy(xpath = "//mat-icon[contains(text(),'close') and @role='img']")
	WebElement btn_closePopUp;
	
	@FindBy(id = "sideNavToggle")
	WebElement btn_toggle;
	
	@FindBy(xpath = "//img[@id='shaipLogo']//parent::div")
	WebElement img_shaipLogo;
	
	
	@FindBy(id = "projectNav")
	WebElement lnk_projects;

	@FindBy(id = "notifications")
	WebElement lnk_notifications;

	@FindBy(id = "reportNav")
	WebElement lnk_reports;

	
	
	//footerlinks
	
	@FindBy(id = "logged-in-user")
	WebElement lnk_profileIcon;

	@FindBy(id = "organizationNav")
	WebElement lnk_organization;
	
	@FindBy(id = "accountNav")
	WebElement lnk_account;

	@FindBy(id = "sign-out")
	WebElement lnk_signOut;
	
	
	public LandingPage_CP verifyExpandAndCollapseMenuBarFunctionalityCP() throws InterruptedException {

	
		String className= img_shaipLogo.getAttribute("class");
		assert_custom(className, "shaip-logo full", "Side menu bar");
		click_custom(btn_toggle, "SideBar");
		String className2= img_shaipLogo.getAttribute("class");
		assert_custom(className2, "shaip-logo small", "Side menu bar");
		click_custom(btn_toggle, "SideBar");
		Thread.sleep(1000);
		waitUntilClickable(lnk_profileIcon);
		return this;

		
		
	}

	public void verifyCopyRights() {

		waitForVisibility(copyRights);
		waitUntilClickable(copyRights);
		sa.assertTrue(isElementPresent_custom(copyRights, "CopyRightText"));
	}

	public LandingPage_CP verifyTermsAndConditionPopUp() throws InterruptedException {

		Thread.sleep(1000);
		System.out.println("Going to verify terms condition pop up");
		waitForVisibility(termsAndCondition);
		String title=getText_custom(ttl_termsAndCondition, "Terms & Condition pop up");
		assert_contains(title,"Terms of Service","Terms & Condition pop up");
		return this;

	}

	public LandingPage_CP clickOnAgreeButton() throws InterruptedException {

		Thread.sleep(2000);
		waitUntilClickable(btn_agree);
		execute_click(btn_agree, "I agree terms & condition");
		return this;

	}

	public  LoginPage_CP clickOnDisagreeButton() throws InterruptedException {

		Thread.sleep(1000);
		click_custom(btn_disagreeAndExit, "I disagree terms & condition");
		Thread.sleep(2000);
		return new LoginPage_CP();

	}

	public <T> T selectProfileSubMenu(String submenu, Class<T> expectedPage) throws InterruptedException

	{
		Thread.sleep(1000);

		if (submenu.equalsIgnoreCase("Account")) {
			waitUntilClickable(lnk_account);
			click_custom(lnk_account, "Account Link");
		} else if (submenu.equalsIgnoreCase("Organization")) {
			waitUntilClickable(lnk_organization);
			click_custom(lnk_organization, "Organization Link");

		} else if (submenu.equalsIgnoreCase("Sign Out")) {
			waitUntilClickable(lnk_signOut);
			click_custom(lnk_signOut, "Sign Out Link");

			
		} 
		return PageFactory.initElements(DriverFactoryShaip.getDriver(), expectedPage);
		

	}

	
	
	
	public  AccountPage_CP clickOnAccount() throws InterruptedException{
	    return selectProfileSubMenu("Account", AccountPage_CP.class);
	}

	public   OrganizationPage_CP clickOnOrganization() throws InterruptedException{
	    return selectProfileSubMenu("Organization", OrganizationPage_CP.class);
	}
	public   LoginPage_CP clickOnSignOut() throws InterruptedException{
	    return selectProfileSubMenu("Sign Out", LoginPage_CP.class);
	}
	
	
	
	public <T> T clickOnMenu(String submenu, Class<T> expectedPage) throws InterruptedException {

		if (submenu.equalsIgnoreCase("Report")) {
			waitUntilClickable(lnk_reports);
			click_custom(lnk_reports, "Reports Link");
		} else if (submenu.equalsIgnoreCase("Projects")) {
			waitUntilClickable(lnk_projects);
			click_custom(lnk_projects, "Projects Link");

		} else if (submenu.equalsIgnoreCase("Notifications")) {
			waitUntilClickable(lnk_notifications);
			click_custom(lnk_notifications, "Notifications Link");

		} 
		Thread.sleep(2000);
		return PageFactory.initElements(DriverFactoryShaip.getDriver(), expectedPage);

	}

	public NotificationsPage_CP clickOnNotifications() throws InterruptedException {
		
		Thread.sleep(2000);
		return clickOnMenu("Notifications", NotificationsPage_CP.class);
	}
	
	public ProjectsPage_CP clickOnProjects() throws InterruptedException {
		
		Thread.sleep(2000);
		return clickOnMenu("Projects", ProjectsPage_CP.class);
	}
	

	public LandingPage_CP clickOnProfileMenu() throws InterruptedException {

		Thread.sleep(1000);
		click_custom(lnk_profileIcon, "Account Profile Icon");
		return this;

	}

	public LandingPage_CP clickOnClosePopUp() throws InterruptedException {

		Thread.sleep(1000);
		click_custom(btn_closePopUp, "Close PopUp- Terms & Condition");
		return new LandingPage_CP();

	}

	public LandingPage_CP verifyShaipURI() throws InterruptedException {

		Thread.sleep(1000);
		waitForVisibility(lnk_shaipURI);
		waitUntilClickable(lnk_shaipURI);
		String URI = getText_custom(lnk_shaipURI, "Shaip URI");
		System.out.println("Shaip URI verification completed and URI is--->  " + URI);
		try {
			assert_custom(URI, "shaip.com", "Footer Shaip URI");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
		// click_custom(lnk_shaipURI, "URI");

	}

	public LandingPage_CP verifyMenuPermissions(String user) {

		Set<String> tenantListOfMenus = new HashSet<String>(Arrays.asList("Dashboard", "Projects", "Notifications"));
		Set<String> vendorAdminListOfMenus = new HashSet<String>(Arrays.asList("Dashboard", "Notifications"));
		Set<String> customerAdminListOfMenus = new HashSet<String>(Arrays.asList("Dashboard", "Notifications"));

		List<WebElement> sidebar = DriverFactoryShaip.getDriver()
				.findElements(By.xpath("//div[@class='sidebar-menu-items']//div[@class='mat-list-item-content']"));

		for (int i = 0; i < (sidebar.size()) - 2; i++) {

			String menu = sidebar.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ");

			String[] menues = menu.split(" ");
			String menuName = menues[1];

			if (user.equalsIgnoreCase("parth.parikh@shaip.com") && !tenantListOfMenus.contains(menuName))

			{
				sa.assertFalse(true);
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Menu not bleongs to Teanant user" + menuName + "it is..");

			} else if (user.equalsIgnoreCase("c.orac@yopmail.com") && !customerAdminListOfMenus.contains(menuName)) {

				sa.assertFalse(false);
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Menu not bleongs to cutomer user" + menuName + "it is..");

			} else if (user.equalsIgnoreCase("dkak@bitesatlanta.com") && !vendorAdminListOfMenus.contains(menuName)) {

				sa.assertFalse(false);
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Menu not bleongs to vendor user" + menuName + "it is..");

			}
			sa.assertAll();

		}
		return this;

	}

	public LandingPage_CP verifyPrimaryAndSecondaryMenuPermissionsFromDB(String user) {

		Set<String> contributors = new HashSet<String>(Arrays.asList("Projects", "Notifications","Dashboard"));
		Set<String> vendorcontributors = new HashSet<String>(Arrays.asList("Projects", "Notifications","Dashboard"));
		Set<String> customercontributors = new HashSet<String>(Arrays.asList("Projects", "Notifications","Dashboard"));
		Set<String> freelancer = new HashSet<String>(Arrays.asList("Notifications", "Projects","Dashboard"));
		Set<String> tenantAdmin = new HashSet<String>(Arrays.asList("Notifications", "Projects", "Dashboard"));

		DB_Operations db = new DB_Operations();

		HashMap<String, String> dbData = db.getSqlResultInMap("SELECT r.* FROM shaip_security.user_ u "
				+ "left join shaip_security.users_roles ur on ur.userId=u.userId "
				+ "left join shaip_security.role_ r on r.roleId = ur.roleId WHERE userName='" + user + "'");

		String roleName = dbData.get("name");

		List<WebElement> sidebar = DriverFactoryShaip.getDriver()
				.findElements(By.xpath("//div[@class='sidebar-menu-items']//div[@class='mat-list-item-content']"));

		for (int i = 0; i < (sidebar.size()) - 2; i++) {

			String menu = sidebar.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ");

			String menuName = menu.substring(menu.lastIndexOf(" ") + 1);

			System.out.println(menuName);

			if (roleName.equalsIgnoreCase("contributor") && !contributors.contains(menuName))

			{
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Primary Menu not bleongs to Teanant user" + menuName + "it is..");

			} else if (roleName.equalsIgnoreCase("customer_contributor") && !customercontributors.contains(menuName)) {

				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Primary Menu not bleongs to cutomer user" + menuName + "it is..");

			} else if (roleName.equalsIgnoreCase("vendor_contributor") && !vendorcontributors.contains(menuName)) {

				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Primary Menu not bleongs to vendor user" + menuName + "it is..");

			} else if (roleName.equalsIgnoreCase("freelancer") && !freelancer.contains(menuName)) {

				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Primary Menu not bleongs to vendor user" + menuName + "it is..");

			} else if (roleName.equalsIgnoreCase("omni_administrator") && !tenantAdmin.contains(menuName)) {

				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Primary Menu not bleongs to cutomer user" + menuName + "it is..");

			} 

			verifySecondaryMenuPermissionsFromDB(user, roleName);
			sa.assertAll();
		}
		return this;
	}

	public void verifySecondaryMenuPermissionsFromDB(String user, String roleName) {

		Set<String> secondaryMenu = new HashSet<String>(Arrays.asList("help","person"));

		List<WebElement> sidebar = DriverFactoryShaip.getDriver()
				.findElements(By.xpath("//div[@class='sidebar-menu-items']//div[@class='mat-list-item-content']"));

		
		int endCount=(sidebar.size())-1;
	
		
		System.out.println(endCount);
	
		
		System.out.println(endCount);
		System.out.println(sidebar.size() - 3);

		for (int i = endCount; i >= 3; i--) {

			String menu = sidebar.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ");

			int a = menu.indexOf(' ');
			String menuName = menu.substring(0, a);
			String rest = menu.substring(a);

			System.out.println(menuName);

			if (roleName.equalsIgnoreCase("contributor") && !secondaryMenu.contains(menuName))

			{
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Secondary Menu not bleongs to Teanant user " + menuName + "it is..");

			} else if (roleName.equalsIgnoreCase("customer_contributor") && !secondaryMenu.contains(menuName)) {

				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Secondary Menu not bleongs to cutomer user " + menuName + "it is..");

			} else if (roleName.equalsIgnoreCase("vendor_contributor") && !secondaryMenu.contains(menuName)) {

				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Secondary Menu not bleongs to vendor user " + menuName + "it is..");

			} else if (roleName.equalsIgnoreCase("freelancer") && !secondaryMenu.contains(menuName)) {

				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Secondary Menu not bleongs to vendor user " + menuName + "it is..");

			}
			else if (roleName.equalsIgnoreCase("omni_administrator") && !secondaryMenu.contains(menuName)) {

				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Secondary Menu not bleongs to vendor user " + menuName + "it is..");

			}

		}
		sa.assertAll();

	}

	public void clickOnMenu(String menuToBeClicked) throws InterruptedException {

		Thread.sleep(2000);

		List<WebElement> sidebar = DriverFactoryShaip.getDriver()
				.findElements(By.xpath("//div[@class='sidebar-menu-items']//div[@class='mat-list-item-content']"));

		for (int i = 0; i < (sidebar.size()) - 3; i++) {

			String menu = sidebar.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ");

			String menuName = menu.substring(menu.lastIndexOf(" ") + 1);

			System.out.println(menuName);

			if (menuName.contains(menuToBeClicked))

			{
				click_custom(sidebar.get(i), menuToBeClicked);

				System.out.println("Menu admin clicked");
				break;
			}

		}

	}

}
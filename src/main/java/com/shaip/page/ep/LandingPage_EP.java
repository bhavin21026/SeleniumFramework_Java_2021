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
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.shaip.base.ActionEngineShaip;
import com.shaip.factories.DriverBase;
import com.shaip.factories.DriverFactoryShaip;
import com.shaip.reportng.ExtentFactoryShaip;
import com.shaip.utils.DB_Operations;

public class LandingPage_EP extends ActionEngineShaip {

	public LandingPage_EP() {

		
		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	@FindBy(xpath = "//a[contains(text(),'shaip.com')]/parent::div")
	WebElement copyRights;

	@FindBy(xpath = "//a[text()='shaip.com']")
	WebElement shaipURI;

	
	// navbar components

	@FindBy(id = "dashBoardNav")
	WebElement lnk_dashboard;

	@FindBy(id = "projectsNav")
	WebElement lnk_projects;

	@FindBy(id = "notifications")
	WebElement lnk_notifications;

	@FindBy(id = "administrationNav")
	WebElement lnk_Administrator;
	
	@FindBy(id = "sideNavToggle")
	WebElement btn_toggle;
	
	@FindBy(xpath = "//img[@id='shaipLogo']//parent::div")
	WebElement img_shaipLogo;
	

	// footerlinks

	@FindBy(id = "logged-in-user")
	WebElement lnk_profileIcon;

	@FindBy(id = "organizationNav")
	WebElement lnk_organization;

	@FindBy(id = "accountNav")
	WebElement lnk_account;

	@FindBy(id = "sign-out")
	WebElement lnk_signOut;

	public void verifyCopyRights() {

		waitForVisibility(copyRights);
		waitUntilClickable(copyRights);

		sa.assertTrue(isElementPresent_custom(copyRights, "CopyRightText"));
	}
	
	public LandingPage_EP verifyExpandAndCollapseMenuBarFunctionalityEP() throws InterruptedException {

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

	public LandingPage_EP verifyShaipURI() throws InterruptedException {

		Thread.sleep(1000);
		waitForVisibility(shaipURI);
		waitUntilClickable(shaipURI);
		String URI = getText_custom(shaipURI, "Shaip URI");
		System.out.println("Shaip URI verification completed and URI is--->  " + URI);
		try {
			assert_custom(URI, "shaip.com", "Footer Shaip URI");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;

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

	public AccountPage_EP clickOnAccount() throws InterruptedException {
		return selectProfileSubMenu("Account", AccountPage_EP.class);
	}

	public OrganizationPage_EP clickOnOrganization() throws InterruptedException {
		return selectProfileSubMenu("Organization", OrganizationPage_EP.class);
	}

	public LoginPage_EP clickOnSignOut() throws InterruptedException {
		return selectProfileSubMenu("Sign Out", LoginPage_EP.class);
	}

	public LandingPage_EP clickOnProfileMenu() throws InterruptedException {

		Thread.sleep(1000);
		click_custom(lnk_profileIcon, "Account Profile Icon");
		return this;

	}

	public void verifyMenuPermissionsFromDB(String user) {

		Set<String> tenantListOfMenus = new HashSet<String>(
				Arrays.asList("Dashboard", "Projects", "Notifications", "Administration"));
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
				ExtentFactoryShaip.getTest().log(Status.FAIL, "Menu not bleongs to vendor user" + menuName + "it is..");

			}
			sa.assertAll();

		}

	}

	public LandingPage_EP verifyPrimaryAndSecondaryMenuPermissionsFromDB(String user, String role) {

		Set<String> tenantListOfMenus = new HashSet<String>(
				Arrays.asList("Dashboard", "Projects", "Notifications", "Administration"));
		Set<String> tenantOwnerListOfMenus = new HashSet<String>(
				Arrays.asList("Dashboard", "Projects", "Notifications", "Administration"));
		Set<String> tenantAdminListOfMenus = new HashSet<String>(
				Arrays.asList("Dashboard", "Projects", "Notifications", "Administration"));
		Set<String> businessManagerListOfMenus = new HashSet<String>(
				Arrays.asList("Dashboard", "Projects", "Notifications", "Administration"));
		Set<String> projectManagerListOfMenus = new HashSet<String>(
				Arrays.asList("Dashboard", "Projects", "Notifications", "Administration"));
		Set<String> projectCoordinatorListOfMenus = new HashSet<String>(
				Arrays.asList("Dashboard", "Projects", "Notifications", "Administration"));
		Set<String> vendorAdminListOfMenus = new HashSet<String>(Arrays.asList("Dashboard", "Notifications"));
		Set<String> customerAdminListOfMenus = new HashSet<String>(Arrays.asList("Dashboard", "Notifications"));

		DB_Operations db = new DB_Operations();

		HashMap<String, String> dbData = db.getSqlResultInMap("SELECT r.* FROM shaip_security.user_ u "
				+ "left join shaip_security.users_roles ur on ur.userId=u.userId "
				+ "left join shaip_security.role_ r on r.roleId = ur.roleId WHERE userName='" + user + "'");

		String roleName = dbData.get("name");

		List<WebElement> sidebar = DriverFactoryShaip.getDriver()
				.findElements(By.xpath("//div[@class='sidebar-menu-items']//div[@class='mat-list-item-content']"));

		for (int i = 0; i < (sidebar.size()) - 2; i++) {

			String menu = sidebar.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ");
			System.out.println(menu);
			// String menuName = menu.substring(menu.lastIndexOf(" ") + 1);
			String[] menues = menu.split(" ");

			String menuName = null;

			if (menues.length > 2) {

				String menuName1 = menues[1];
				String menuName2 = menues[2];

				if (tenantListOfMenus.contains(menuName1) || vendorAdminListOfMenus.contains(menuName1))

				{
					menuName = menuName1;

				} else {

					menuName = menuName2;
				}
			}

			else {

				menuName = menues[1];
			}

			System.out.println(menuName);

			if (roleName.equalsIgnoreCase("omni_administrator") && !tenantListOfMenus.contains(menuName))

			{
				sa.fail(menuName + "    Menu name not belongs to logged in user  " + roleName);
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Primary Menu not bleongs to Teanant user" + menuName + "it is..");

			} else if (roleName.equalsIgnoreCase("tenant_owner") && !tenantOwnerListOfMenus.contains(menuName)) {

				sa.fail(menuName + "    Menu name not belongs to logged in user  " + roleName);
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Primary Menu not bleongs to cutomer user" + menuName + "it is..");

			} else if (roleName.equalsIgnoreCase("tenant_administrator")
					&& !tenantAdminListOfMenus.contains(menuName)) {

				sa.fail(menuName + "    Menu name not belongs to logged in user  " + roleName);
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Primary Menu not bleongs to vendor user" + menuName + "it is..");

			} else if (roleName.equalsIgnoreCase("business_manager")
					&& !businessManagerListOfMenus.contains(menuName)) {

				sa.fail(menuName + "    Menu name not belongs to logged in user  " + roleName);
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Primary Menu not bleongs to vendor user" + menuName + "it is..");

			} else if (roleName.equalsIgnoreCase("project_manager") && !projectManagerListOfMenus.contains(menuName)) {

				sa.fail(menuName + "    Menu name not belongs to logged in user  " + roleName);
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Primary Menu not bleongs to cutomer user" + menuName + "it is..");

			} else if (roleName.equalsIgnoreCase("project_coordinator")
					&& !projectCoordinatorListOfMenus.contains(menuName)) {

				sa.fail(menuName + "    Menu name not belongs to logged in user  " + roleName);
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Primary Menu not bleongs to cutomer user" + menuName + "it is..");

			} else if (roleName.equalsIgnoreCase("customer_administrator")
					&& !customerAdminListOfMenus.contains(menuName)) {

				sa.fail(menuName + "    Menu name not belongs to logged in user  " + roleName);
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Primary Menu not bleongs to cutomer user" + menuName + "it is..");

			} else if (roleName.equalsIgnoreCase("vendor_administrator")
					&& !vendorAdminListOfMenus.contains(menuName)) {

				sa.fail(menuName + "    Menu name not belongs to logged in user  " + roleName);
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Primary Menu not bleongs to cutomer user" + menuName + "it is..");

			}

		}
		verifySecondaryMenuPermissionsFromDB(user, roleName);
		sa.assertAll();
		return this;
	}

	public void verifySecondaryMenuPermissionsFromDB(String user, String roleName) {

		Set<String> secondaryMenu = new HashSet<String>(Arrays.asList("help", "person"));

		List<WebElement> sidebar = DriverFactoryShaip.getDriver()
				.findElements(By.xpath("//div[@class='sidebar-menu-items']//div[@class='mat-list-item-content']"));

		int endCount = (sidebar.size()) - 1;
		int takeCount = (sidebar.size()) - 1;

		System.out.println(endCount);
		System.out.println(takeCount);

		for (int i = endCount; i >= 4; i--) {

			String menu = sidebar.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ");

			int a = menu.indexOf(' ');
			String menuName = menu.substring(0, a);
			String rest = menu.substring(a);

			System.out.println(menuName);

			if (roleName.equalsIgnoreCase("omni_administrator") && !secondaryMenu.contains(menuName))

			{
				sa.fail(menuName + "    Menu name not belongs to logged in user  " + roleName);
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Secondary Menu not bleongs to Teanant user" + menuName + "it is..");

			} else if (roleName.equalsIgnoreCase("tenant_owner") && !secondaryMenu.contains(menuName)) {

				sa.fail(menuName + "    Menu name not belongs to logged in user  " + roleName);
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Secondary Menu not bleongs to cutomer user" + menuName + "it is..");

			} else if (roleName.equalsIgnoreCase("tenant_administrator") && !secondaryMenu.contains(menuName)) {

				sa.fail(menuName + "    Menu name not belongs to logged in user  " + roleName);
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Secondary Menu not bleongs to vendor user" + menuName + "it is..");

			} else if (roleName.equalsIgnoreCase("business_manager") && !secondaryMenu.contains(menuName)) {

				sa.fail(menuName + "    Menu name not belongs to logged in user  " + roleName);
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Secondary Menu not bleongs to vendor user" + menuName + "it is..");

			} else if (roleName.equalsIgnoreCase("project_manager") && !secondaryMenu.contains(menuName)) {

				sa.fail(menuName + "    Menu name not belongs to logged in user  " + roleName);
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Secondary Menu not bleongs to cutomer user" + menuName + "it is..");

			} else if (roleName.equalsIgnoreCase("project_coordinator") && !secondaryMenu.contains(menuName)) {

				sa.fail(menuName + "    Menu name not belongs to logged in user  " + roleName);
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Secondary Menu not bleongs to cutomer user" + menuName + "it is..");

			} else if (roleName.equalsIgnoreCase("customer_administrator") && !secondaryMenu.contains(menuName)) {

				sa.fail(menuName + "    Menu name not belongs to logged in user  " + roleName);
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Secondary Menu not bleongs to cutomer user" + menuName + "it is..");

			} else if (roleName.equalsIgnoreCase("vendor_administrator") && !secondaryMenu.contains(menuName)) {

				sa.fail(menuName + "    Menu name not belongs to logged in user  " + roleName);
				ExtentFactoryShaip.getTest().log(Status.FAIL,
						"Secondary Menu not bleongs to cutomer user" + menuName + "it is..");

			}

		}
		sa.assertAll();

	}

	public <T> T clickOnMenu(String submenu, Class<T> expectedPage) throws InterruptedException {

		if (submenu.equalsIgnoreCase("Dashboard")) {
			waitUntilClickable(lnk_dashboard);
			click_custom(lnk_dashboard, "Dashboard Link");
		} else if (submenu.equalsIgnoreCase("Projects")) {
			waitUntilClickable(lnk_projects);
			click_custom(lnk_projects, "Projects Link");
			waitForProcessBarToGo();
			Thread.sleep(1000);

		} else if (submenu.equalsIgnoreCase("Notifications")) {
			waitUntilClickable(lnk_notifications);
			click_custom(lnk_notifications, "Notifications Link");

		} else if (submenu.equalsIgnoreCase("Administration")) {
			waitUntilClickable(lnk_Administrator);
			click_custom(lnk_Administrator, "Administrator Link");

		}
		Thread.sleep(2000);
		return PageFactory.initElements(DriverFactoryShaip.getDriver(), expectedPage);

	}

	public AdministrationPage_EP clickOnAdministration() throws InterruptedException {
		
		Thread.sleep(2000);
		return clickOnMenu("Administration", AdministrationPage_EP.class);
	}
	
	public ProjectsPage_EP clickOnProjects() throws InterruptedException {
		
		Thread.sleep(2000);
		return clickOnMenu("Projects", ProjectsPage_EP.class);
	}

	/*
	 * public CustomersPage_EP clickOnCustomers() throws InterruptedException{
	 * return clickOnMenu("Customers", CustomersPage_EP.class); } public
	 * VendorsPage_EP clickOnSignOutVendors() throws InterruptedException{ return
	 * clickOnMenu("Vendors", VendorsPage_EP.class); } public Users_EP
	 * clickOnUsers() throws InterruptedException{ return clickOnMenu("Users",
	 * Users_EP.class); }
	 */

	/*
	 * public <T> T clickOnMenu(String menuToBeClicked,Class<T> expectedPage) throws
	 * InterruptedException {
	 * 
	 * Thread.sleep(2000);
	 * 
	 * List<WebElement> sidebar = DriverFactoryShaip.getDriver()
	 * .findElements(By.xpath(
	 * "//div[@class='sidebar-menu-items']//div[@class='mat-list-item-content']"));
	 * 
	 * for (int i = 0; i < (sidebar.size()) - 1; i++) {
	 * 
	 * String menu = sidebar.get(i).getText().replaceAll("\r\n",
	 * " ").replaceAll("\r", " ").replaceAll("\n", " ");
	 * 
	 * String[] menues = menu.split(" "); String menuName = menues[1];
	 * 
	 * if(menuName.contains(menuToBeClicked))
	 * 
	 * { click_custom(sidebar.get(i), menuToBeClicked);
	 * 
	 * System.out.println("Menu admin clicked"); break; }
	 * 
	 * 
	 * } return
	 * PageFactory.initElements(DriverFactoryShaip.getDriver(),
	 * expectedPage);
	 * 
	 * 
	 * }
	 */

}
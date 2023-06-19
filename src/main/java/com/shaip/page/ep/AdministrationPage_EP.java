package com.shaip.page.ep;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.shaip.base.ActionEngineShaip;
import com.shaip.factories.DriverBase;
import com.shaip.factories.DriverFactoryShaip;

public class AdministrationPage_EP extends ActionEngineShaip {

	public AdministrationPage_EP() {

		
		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	@FindBy(xpath = "//div[contains(@class,'mat-menu-content')]")
	WebElement subMenuContent;

	@FindBy(id = "rolesNav")
	WebElement lnk_roles;

	@FindBy(id = "customersNav")
	WebElement lnk_customers;

	@FindBy(id = "vendorsNav")
	WebElement lnk_vendors;

	@FindBy(id = "usersNav")
	WebElement lnk_users;

	public <T> T selectAdministrationSubMenu(String submenu, Class<T> expectedPage) throws InterruptedException

	{
		if (submenu.equalsIgnoreCase("Roles")) {
			
			waitUntilClickable(lnk_roles);
			click_custom(lnk_roles, "Roles Link");
		} else if (submenu.equalsIgnoreCase("Customers")) {
			waitUntilClickable(lnk_customers);
			click_custom(lnk_customers, "Customers Link");

		} else if (submenu.equalsIgnoreCase("Vendors")) {
			waitUntilClickable(lnk_vendors);
			click_custom(lnk_vendors, "Vendors Link");

			
		} else if (submenu.equalsIgnoreCase("Users")) {
			waitUntilClickable(lnk_users);
			click_custom(lnk_users, "Users Link");

		}
		waitForProcessBarToGo();
		return PageFactory.initElements(DriverFactoryShaip.getDriver(), expectedPage);

	}

	public RolesPage_EP clickOnRoles() throws InterruptedException {
		return selectAdministrationSubMenu("Roles", RolesPage_EP.class);
	}

	public CustomersPage_EP clickOnCustomers() throws InterruptedException {
		return selectAdministrationSubMenu("Customers", CustomersPage_EP.class);
	}

	public VendorsPage_EP clickOnVendors() throws InterruptedException {
		return selectAdministrationSubMenu("Vendors", VendorsPage_EP.class);
	}

	public UsersPage_EP clickOnUsers() throws InterruptedException {
		return selectAdministrationSubMenu("Users", UsersPage_EP.class);
	}
	
	

	/*
	 * public <T> T selectAdministrationSubMenu(String submenu,Class<T>
	 * expectedPage) throws InterruptedException
	 * 
	 * { Thread.sleep(1000);
	 * 
	 * List<WebElement>
	 * subMenues=DriverFactoryShaip.getDriver().findElements(By.xpath(
	 * "//div[@role='menu']//child::button"));
	 * 
	 * for (int i = 0; i < subMenues.size(); i++) {
	 * 
	 * String menu = subMenues.get(i).getText().replaceAll("\r\n",
	 * " ").replaceAll("\r", " ").replaceAll("\n", " "); System.out.println(menu);
	 * //String[] menues = menu.split(" "); //String menuName = menues[1];
	 * 
	 * if(menu.contains(submenu))
	 * 
	 * { click_custom(subMenues.get(i), submenu); break; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * } waitForProcessBarToGo(); return
	 * PageFactory.initElements(DriverFactoryShaip.getDriver(),
	 * expectedPage);
	 * 
	 * }
	 */

}

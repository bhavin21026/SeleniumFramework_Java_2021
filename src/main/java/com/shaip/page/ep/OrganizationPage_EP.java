package com.shaip.page.ep;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.shaip.base.ActionEngineShaip;
import com.shaip.factories.DriverBase;
import com.shaip.factories.DriverFactoryShaip;

public class OrganizationPage_EP extends ActionEngineShaip {

	public OrganizationPage_EP() {

		
		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	@FindBy(xpath = "//div[contains(@class,'mat-menu-content')]")
	WebElement subMenuContent;
	
	
	public void selectAdministrationSubMenu(String submenu) throws InterruptedException
	
	{
		Thread.sleep(1000);
	
		List<WebElement> subMenues=DriverFactoryShaip.getDriver().findElements(By.xpath("//div[@role='menu']//child::button"));
		
		for (int i = 0; i < subMenues.size(); i++) {

			String menu = subMenues.get(i).getText().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ");
			System.out.println(menu);
			//String[] menues = menu.split(" ");
			//String menuName = menues[1];
			
			if(menu.contains(submenu)) 
			
			{
				click_custom(subMenues.get(i), submenu);
				break;
			}
			

		
		
		
	}
		waitForProcessBarToGo();
		
	}
}
	
	
	

	

	

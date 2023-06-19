package com.shaip.page.cp;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.shaip.base.ActionEngineShaip;
import com.shaip.factories.DriverBase;
import com.shaip.factories.DriverFactoryShaip;

public class HelpPage_CP extends ActionEngineShaip {

	public HelpPage_CP() {

		wait = new WebDriverWait(DriverFactoryShaip.getDriver(), Duration.ofSeconds(10));
		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}

	
	//help menus
	
	@FindBy(id = "helpNav")
	WebElement lnk_helpMenu;

	@FindBy(id = "faqsNav")
	WebElement lnk_FAQs;

	@FindBy(id = "contactUsNav")
	WebElement lnk_contactUs;

	@FindBy(id = "privacyNav")
	WebElement lnk_privacyPolicy;
	
	@FindBy(id = "termsOfServiceNav")
	WebElement lnk_termsOfService;
	
	
	public HelpPage_CP clickOnHelpMenu() throws InterruptedException {

		Thread.sleep(1000);
		click_custom(lnk_helpMenu, "Help Icon");
		return this;

	}
	
	
	
	public <T> T selectHelpSubMenu(String submenu, Class<T> expectedPage) throws InterruptedException

	{
		Thread.sleep(1000);

		if (submenu.equalsIgnoreCase("FAQ")) {
			waitUntilClickable(lnk_FAQs);
			click_custom(lnk_FAQs, "FAQ Link");
		} else if (submenu.equalsIgnoreCase("ContactUs")) {
			waitUntilClickable(lnk_contactUs);
			click_custom(lnk_contactUs, "Contact Us Link");

		} else if (submenu.equalsIgnoreCase("PrivacyPolicy")) {
			waitUntilClickable(lnk_privacyPolicy);
			click_custom(lnk_privacyPolicy, "Privacy Policy Link");
		} else if (submenu.equalsIgnoreCase("TermsOfService")) {
			waitUntilClickable(lnk_termsOfService);
			click_custom(lnk_termsOfService, "Terms Of Service Link");

			
		} 
		return PageFactory.initElements(DriverFactoryShaip.getDriver(), expectedPage);
		

	}

	public HelpPage_CP clickOnFAQ() throws InterruptedException {
		return selectHelpSubMenu("FAQ", HelpPage_CP.class);
	}

	public HelpPage_CP clickOnContactUs() throws InterruptedException {
		return selectHelpSubMenu("ContactUs", HelpPage_CP.class);
	}

	public HelpPage_CP clickOnTermsOfService() throws InterruptedException {
		return selectHelpSubMenu("TermsOfService", HelpPage_CP.class);
	}
	public HelpPage_CP clickOnPrivacyPolicy() throws InterruptedException {
		return selectHelpSubMenu("PrivacyPolicy", HelpPage_CP.class);
	}

	

	
	
	
	public HelpPage_CP getAccessOfHelpSubMenu(String Link) throws InterruptedException

	{
		
		
		Thread.sleep(1000);
		String parent=DriverFactoryShaip.getDriver().getWindowHandle();

		
		
		Set<String>s=DriverFactoryShaip.getDriver().getWindowHandles();
		Iterator<String> I1= s.iterator();

		while(I1.hasNext())
		{

		String child_window=I1.next();


		if(!parent.equals(child_window))
		{
			DriverFactoryShaip.getDriver().switchTo().window(child_window);
			String title=DriverFactoryShaip.getDriver().switchTo().window(child_window).getTitle();
			
			if(Link.equalsIgnoreCase("FAQs"))
			{
				assert_custom(title, "Solutions : Support", "FAQ'");
				
			} else if(Link.equalsIgnoreCase("Contact Us"))
			{
				assert_custom(title, "Support : Support", "Contact Us ");
				
			} else if(Link.equalsIgnoreCase("Terms Of Service"))
			{
				assert_custom(title, "Terms of Service - Shaip", "Terms of Service");
				
			} else if(Link.equalsIgnoreCase("Privacy Policy"))
			{
				assert_custom(title, "Privacy Policy - Shaip", "Privacy Policy ");
				
			}

		   System.out.println(DriverFactoryShaip.getDriver().switchTo().window(child_window).getTitle());
		     DriverFactoryShaip.getDriver().close();
		     DriverFactoryShaip.getDriver().switchTo().window(parent);
		   
		  
		
		}

		}
		//switch to the parent w
		sa.assertAll();
		return new HelpPage_CP();

	}
	
	
	
}

	
	
	

	

	

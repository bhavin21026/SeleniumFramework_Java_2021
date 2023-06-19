package com.shaip.page.cp;

import java.time.Duration;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.shaip.base.ActionEngineShaip;
import com.shaip.factories.DriverBase;
import com.shaip.factories.DriverFactoryShaip;

public class OrganizationPage_CP extends ActionEngineShaip {

	public OrganizationPage_CP() {

		wait = new WebDriverWait(DriverFactoryShaip.getDriver(), Duration.ofSeconds(10));
		PageFactory.initElements(DriverFactoryShaip.getDriver(), this);

	}
	
	

	}
	
	
	

	

	

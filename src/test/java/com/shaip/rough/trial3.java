package com.shaip.rough;


import org.testng.annotations.Test;

import com.shaip.basetest.BaseTest;
import com.shaip.page.ep.LoginPage_EP;

public class trial3 extends BaseTest {
	
	@Test(groups= {"smoke"})
	public void trial_method_3() throws InterruptedException {
		
		System.out.println("Welcome to Gmail.com");
		LoginPage_EP loginPage = new LoginPage_EP();
		//LandingPage_EP landingPage = new LandingPage_EP();				
		loginPage.loginToEP("6hesham2092m@soantiy.com","UFtoB@6176");
		//landingPage.verifyCopyRights();
			

		System.out.println("Hii this is bhavin Sangani 3");
	}

	
	
	
}

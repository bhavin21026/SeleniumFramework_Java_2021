package com.shaip.rough;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.shaip.base.ActionEngineShaip;
import com.shaip.base.FrameworkContstants;
import com.shaip.base.ShaipTestDataMaker;
import com.shaip.basetest.BaseTest;
import com.shaip.factories.DriverBase;
import com.shaip.utils.DB_Operations;
import com.shaip.utils.EmailSearcher;
import com.shaip.utils.EmailUtility;
import com.shaip.utils.PropertiesOperationsShaip;

public class trial extends BaseTest {

	//ExcelOperations excel = new ExcelOperations("LoginCreationData", "EP");

	// ,groups= {"smoke"}(dataProvider = "LoginCreationData")
	@Test
	public void AT1_verifyLoginWithDifferentUsers() throws Throwable {
		
		//EmailSearcher.checkForInvitationMail();

		DB_Operations db = new DB_Operations();
		db.resetAllPassword("Test@123");
		/*String string = "1/4/2022";
		String[] parts = string.split("/");

		String year = parts[2].substring(parts[2].length() - 2);
		

		String newDate = parts[0] + "/" + parts[1] + "/" + year;
		
		System.out.println(newDate);*/
		
		//ShaipTestDataMaker st=new ShaipTestDataMaker();
	    //st.getCurrentMonthYear();
		//public void AT1_verifyLoginWithDifferentUsers(Method method,Object obj1) throws Throwable {
	
		   //EmailUtility email=new EmailUtility(); 
		 
		   //email.doSendEmail();

		
		//rarFile(FrameworkContstants.getEmailAttachmentPath());
		   
		   
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//System.out.println(passwordEncoder.encode("Test@123"));


		/*@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;

		setupExtentReport("EP: Verify user is able to 'Login' and 'Logout' with a valid Credentials with ---> "
				+ testData.get("UserRole"), method.getName(), "SC2-73-Login & Logout (EP)", "Smoke", "EP");
		giveTestRoleInfo(testData, "UserRole");

		loginPage.navigateToEP("urlEP").loginToEP(testData.get("UserName"), testData.get("Password")).verifyShaipURI()
				.clickOnProfileMenu().clickOnSignOut();

		try {
			sa.assertTrue(true);
		} catch (AssertionError e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		sa.assertAll();
		// .isLogoutSuccessfull("EP");*/

	}


	
	    /*@DataProvider(name = "LoginCreationData")
		public Object[][] getTestData() throws IOException {

			return getData("EP","login");
		}*/

   
	
	
	

}

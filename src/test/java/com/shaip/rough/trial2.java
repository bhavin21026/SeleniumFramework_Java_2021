package com.shaip.rough;


import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shaip.basetest.BaseTest;
import com.shaip.factories.DriverBase;
import com.shaip.utils.ExcelOperations;

public class trial2 extends BaseTest {
	
ExcelOperations excel = new ExcelOperations("LoginCreationData", "CP");
	
	@Test(dataProvider = "LoginCreationData",priority = 2,description="CP1: Verify that as a Guest user, I should be able to sign up to the portal",enabled=true )
	public void trial_method_2(Object obj1) throws InterruptedException {

		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;

		
		//ExtentFactoryShaip.startTest("description_"+testData.get("UserName"),"this is test2");
		 
		 
		//giveExtentReportInfo(testData,"UserName");
		
		System.out.println("Welcome to EP 1111111" +testData.get("UserName"));
		/*LoginPage_EP loginPage = new LoginPage_EP();
		LandingPage_EP landingPage = new LandingPage_EP();				
		loginPage.loginToEP("parth.parikh@shaip.com","P@ssw0rd@123");
		landingPage.verifyShaipURI();
		Thread.sleep(10000);
	
	
		//verify DB
		
		
				DB_Operations db=new DB_Operations();
				
				String username=	"parth.parikh@shaip.com";
				HashMap<String, String> dbData = db.getSqlResultInMap("SELECT r.* FROM shaip_security.user_ u "
						+ "left join shaip_security.users_roles ur on ur.userId=u.userId "
						+ "left join shaip_security.role_ r on r.roleId = ur.roleId WHERE userName='"+username+"'");
				
				String roleName = dbData.get("name");
				assertEquals(roleName, "omni_administrator", "Matched");*/


	
		
		
		

	}
	
	
	@DataProvider(name = "LoginCreationData")
	public Object[][] testDataSupplier2() throws Exception {
		int rowcount = excel.getRowCount();
		Object[][] obj = new Object[excel.getRowCount()][1];
		for (int i = 1; i <= excel.getRowCount(); i++) {
			HashMap<String, String> testData2 = excel.getTestDataInMap(i);
			obj[i - 1][0] = testData2;
		}
		return obj;
	}

}






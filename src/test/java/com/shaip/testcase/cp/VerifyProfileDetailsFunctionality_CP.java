package com.shaip.testcase.cp;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shaip.annotations.FrameworkAnnotation;
import com.shaip.basetest.BaseTest;
import com.shaip.enums.CategoryType;
import com.shaip.enums.PortalType;
import com.shaip.enums.story;

public class VerifyProfileDetailsFunctionality_CP extends BaseTest {


    String username = "automation.shaip+9@shaip.com"; //Automation Freelancer user
    String password = "Test@123";

    //,groups= {"smoke"}
    @Test(priority = 1, enabled = true, dataProvider = "ProfileData",groups= {"Smoke","35"})
	@FrameworkAnnotation(category= {CategoryType.SMOKE},storyId= {story.BasicInformation_CP_35},portal= {PortalType.CP})

    public void verifyBasicInformationSaveAndEdit(Object obj1, Method method) throws Throwable {


        @SuppressWarnings("unchecked")
        HashMap < String, String > testData = (HashMap < String, String > ) obj1;
        setupExtentReport("CP: Verify that user should be able to Save/Edit their Basic information (profile) details ( "+testData.get("UserRole")+")", method.getName(),testData.get("UserRole"));
        giveTestRoleInfo(testData, "UserRole");

        loginPagecp
            .navigateToCP("urlCP")
            .loginToCP(testData.get("UserName"), testData.get("Password"))
            .verifyTermsAndConditionPopUp()
            .clickOnAgreeButton()
            .verifyShaipURI()
            .clickOnProfileMenu()
            .clickOnAccount()
            .enterProfileDetails(testData.get("Gender"), testData.get("Code"), testData.get("ContactNo"), testData.get("Country"), testData.get("TimeZone"), testData.get("Ethinicity"), testData.get("Education"), testData.get("Language"));
          
    }



    @DataProvider(name = "ProfileData")
    public Object[][] getTestData() throws IOException {

		return getData("CP","profileData_cp");
	}




}
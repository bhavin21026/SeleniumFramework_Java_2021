package shaip_testcases.shaip_Enterpriseportal;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import shaip_base.ExtentFactoryShaip;
import shaip_base.TestBaseShaip;
import shaip_pageobjects.EP.LandingPage_EP;
import shaip_pageobjects.EP.LoginPage_EP;
import shaip_utilitiescomponents.ExcelOperations;
import shaip_utilitiescomponents.PropertiesOperationsShaip;

public class VerifyMenuPermissionBasedOnUser extends TestBaseShaip {

	LoginPage_EP loginPage;
	LandingPage_EP landingPage;
	ExcelOperations excel=new ExcelOperations("LoginCreationData","EP");

	
	@Test(dataProvider = "LoginCreationData",groups="Regression")
	public void verifyMenuPermissionsForDifferentUsers(Object obj1) throws Throwable {

		loginPage = new LoginPage_EP();
		landingPage = new LandingPage_EP();
		HashMap<String, String> testData = (HashMap<String, String>) obj1;

		ExtentFactoryShaip.getTest().info("Test Data for this execution run is: " + testData);
		loginPage.loginToEP(testData.get("UserName"), testData.get("Password"));
		landingPage.verifyShaipURI();
		landingPage.verifyMenuPermissions(testData.get("UserName"));

	}

	
	@DataProvider(name = "LoginCreationData")
	public Object[][] testDataSupplier() throws Exception {
		int rowcount=excel.getRowCount();
		Object[][] obj = new Object[excel.getRowCount()][1];
		for (int i = 1; i <= excel.getRowCount(); i++) {
			HashMap<String, String> testData = excel.getTestDataInMap(i);
			obj[i - 1][0] = testData;
		}
		return obj;

	}

}

/*<plugin>
<groupId>net.masterthought</groupId>
<artifactId>maven-cucumber-reporting</artifactId>
<version>5.5.4</version>

<executions>
	<execution>
		<id>execution</id>
		<phase>verify</phase>
		<goals>
			<goal>generate</goal>
		</goals>
		<configuration>
			<projectName>ShaipCloud2</projectName>
			<outputDirectory>target/cucumber-reports/advanced-reports</outputDirectory>
			<cucumberOutput>target/cucumber-reports/cucumber.json</cucumberOutput>
			<buildNumber>1</buildNumber>
			<parallelTesting>false</parallelTesting>
			<skip>false</skip>
			<inputDirectory>${project.build.directory}/cucumber-reports</inputDirectory>
			<jsonFiles> <!-- supports wildcard or name pattern -->
				<param>**/*.json</param>
			</jsonFiles> <!-- optional, defaults to outputDirectory if not specified -->
			<classificationDirectory>${project.build.directory}/cucumber-reports</classificationDirectory>
			<checkBuildResult>false</checkBuildResult>
		</configuration>
	</execution>
</executions>



</plugin>*/


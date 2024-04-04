package TestCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ForCampusPage;
import utilities.ApachePOI;
import utilities.ScreenShot;

public class TC_03 extends BaseClass {

	ForCampusPage fcp = new ForCampusPage();
	ScreenShot ss = new ScreenShot();
	ApachePOI ap = new ApachePOI();

	@DataProvider(name = "dp")
	public Object[][] testData() throws Exception {
		String directoryPath = System.getProperty("user.dir");
		String relativePath = "/src/test/resources/ExcelData/"; 
		Object[][] data = ap.getData(directoryPath + relativePath, "TestData","Sheet1");
		return data;
	}

	@Test(priority = 1)
	public void clickForCampus() throws InterruptedException {
		jse.executeScript("window.scrollBy(0,-document.body.scrollHeight);");
		fcp.clickCampus();
		Thread.sleep(1000);
		String expectedTitle = "Coursera for Campus";
		String actualTitle = fcp.checkTitle();
		Assert.assertEquals(expectedTitle, actualTitle);
	}

	@Test(priority = 2, dataProvider = "dp", dependsOnMethods = { "clickForCampus" })
	public void enterFormValues(String fname, String lname, String eMail, String phoneNumber, String comp)
			throws Exception {
		Thread.sleep(1000);
		fcp.enterFirstName(fname);
		fcp.enterLastName(lname);
		fcp.enterEmail(eMail);
		fcp.enterPhoneNumber(phoneNumber);
		fcp.selectInstituteType("Graduate or Professional School");
		fcp.enterInstituteName(comp);
		fcp.selectRole("Student");
		fcp.selectDepartment("Continuing Education");
		fcp.selectNeed("Tech Support");
		fcp.selectCountry("Canada");
		fcp.selectState();
		fcp.clickCheckBox();
		fcp.clickSubmit();
		Thread.sleep(1000);
		String expectedResult = "Must be valid email.\nexample@yourdomain.com";
		String actualResult = fcp.returnErrorMsg();
		Assert.assertEquals(expectedResult, actualResult);
	}

	@Test(priority = 3, dependsOnMethods = { "enterFormValues" })
	public void captureErrorMessage() {
		boolean res = fcp.captureEmailErrorMsg();
		Assert.assertTrue(res);
	}
}

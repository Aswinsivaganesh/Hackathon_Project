package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;

public class TC_01 extends BaseClass {
	HomePage home = new HomePage();

	@Test(priority = 1, groups = { "smoke" })
	public void checkUrl() {
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://www.coursera.org/");
	}

	@Test(priority = 2, dependsOnMethods = { "checkUrl" })
	public void search() throws InterruptedException {
		home.searchAndSubmit("Web development");
		Thread.sleep(2000);
		String exp_title = "Top Web Development Courses - Learn Web Development Online";
		String act_title = driver.getTitle();

		Assert.assertEquals(exp_title, act_title);
	}

	@Test(priority = 3)
	public void clickBeginner() throws InterruptedException {
		boolean result = home.selectBeginner();
		Assert.assertTrue(result);
	}

	@Test(priority = 4)
	public void clickEnglish() throws InterruptedException {
		boolean result = home.selectEnglish();
		Assert.assertTrue(result);
	}

	@Test(priority = 5)
	public void ExtractCouseName() {

		Boolean result = home.printCourses();
		Assert.assertTrue(result);
	}

	@Test(priority = 6)
	public void checkRatings() throws InterruptedException {
		boolean res = home.ratings();
		Assert.assertTrue(res);
	}

	@Test(priority = 7)
	public void checkCountHours() throws InterruptedException {
		String url = home.printHours();
		Assert.assertEquals(url, "https://www.coursera.org/professional-certificates/meta-front-end-developer");
	}
}

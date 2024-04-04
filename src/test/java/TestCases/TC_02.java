package TestCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.DisplayPage;

public class TC_02 extends BaseClass {
	DisplayPage disp = new DisplayPage();

	@Test(priority = 1)
	public void checkTotalLanguages() throws InterruptedException {

		List<String> lan1 = disp.countLang();
		Boolean flag = true;
		if (!(lan1.size() == 22 || lan1.isEmpty())) {
			flag = false;
		}
		Assert.assertTrue(flag);

	}

	@Test(priority = 2)
	public void checkForLevels() {

		List<String> level1 = disp.extractLevels();
		Boolean flag = true;
		if (!(level1.size() == 4 || level1.isEmpty())) {
			flag = false;
		}
		Assert.assertTrue(flag);
	}

}
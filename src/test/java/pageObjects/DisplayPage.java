package pageObjects;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import TestCases.BaseClass;

public class DisplayPage extends BaseClass {

	// Locators
	By showAll_loc = By.xpath("//button[@aria-label='Show more Language options']");
	By languages_loc = By.xpath("//div[@id='checkbox-group']/div//div[1]/span/span");
	By closeButton_loc = By.xpath("//div[@class=\"css-jyd7rb\"]//button");
	By levels_loc = By.xpath("//div[@data-testid=\"search-filter-group-Level\"]//div/span/span");

	// Action Methods
	public List<String> countLang() throws InterruptedException {
		driver.findElement(showAll_loc).click();

		List<WebElement> lang = driver.findElements(languages_loc);
		List<String> lang1 = new ArrayList<String>();
		System.out.println("Languages and their count : ");
		for (int i = 0; i < lang.size(); i++) {
			String languages = lang.get(i).getText();
			System.out.println(languages);
			lang1.add(languages);
		}
		driver.findElement(closeButton_loc).click();

		return lang1;

	}

	public List<String> extractLevels() {
		List<WebElement> levels = driver.findElements(levels_loc);
		List<String> lev1 = new ArrayList<String>();
		System.out.println("Levels and their count : ");
		for (int i = 0; i < levels.size(); i++) {
			String doubles = levels.get(i).getText();
			System.out.println(doubles);
			lev1.add(doubles);
		}
		return lev1;
	}
}
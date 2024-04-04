package pageObjects;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import TestCases.BaseClass;

public class HomePage extends BaseClass {
	// Web elements
	WebElement searchBox, searchButton, beginnerCB, EnglishCB;
	List<WebElement> tiles;
	// locator
	By searchCB_loc = By.xpath("//input[@placeholder='What do you want to learn?']");
	By searchSearchButton_loc = By.xpath("//button[@class='nostyle search-button']//div[@class='magnifier-wrapper']");
	By english_loc = By.xpath("//div[2]/div[@role='group']/div//input[1]");
	By beginner_loc = By.xpath("//div[4]/div[@role='group']/div//input[1]");
	By rating_loc = By.xpath("//p[@class='css-2xargn']");
	By title_loc = By.tagName("h3");
	By tiles_loc = By.cssSelector("div.cds-CommonCard-clickArea");
	By hours_loc = By.cssSelector("div.css-lt1dx1 div:nth-child(3) div:nth-child(1)");

	// Action methods
	public void searchAndSubmit(String search) {
		searchBox = driver.findElement(searchCB_loc);
		searchBox.sendKeys(search);
		searchButton = driver.findElement(searchSearchButton_loc);
		searchButton.click();
	}

	public boolean selectBeginner() throws InterruptedException {
		beginnerCB = driver.findElement(beginner_loc);
		beginnerCB.click();
		Thread.sleep(2000);
		if (beginnerCB.isSelected()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean selectEnglish() throws InterruptedException {
		EnglishCB = driver.findElement(english_loc);
		jse.executeScript("arguments[0].click();", EnglishCB);
		Thread.sleep(2000);
		if (EnglishCB.isSelected()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean ratings() throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> ratings = driver.findElements(rating_loc);
		Thread.sleep(2000);
		int num = 0;
		System.out.println("Rating : ");
		for (int i = 0; i < 2; i++) {
			String one = ratings.get(i).getText();
			System.out.println(one);
			num++;
		}
		if (num == 2) {
			return true;
		} else {
			return false;
		}
	}

	public boolean printCourses() {
		tiles = driver.findElements(tiles_loc);
		int num = 0;
		System.out.println("courses : ");
		for (int i = 0; i < tiles.size(); i++) {
			if (i == 0 || i == 1) {
				WebElement ele = tiles.get(i).findElement(title_loc);
				String value = ele.getText();
				System.out.println(value);
				num++;
			}
		}
		if (num == 2) {
			return true;
		} else {
			return false;
		}
	}

	public String printHours() throws InterruptedException {
		Actions builder = new Actions(driver);
		builder.moveToElement(tiles.get(0)).click().build().perform();
		Thread.sleep(2000);
		Set<String> window = driver.getWindowHandles();
		String parentwin = driver.getWindowHandle();
		System.out.println("Hours : ");
		for (String o : window) {
			if (!o.equals(parentwin)) {
				driver.switchTo().window(o);
			}
		}
		String hours = (String) jse.executeScript(
				"return document.getElementsByClassName('css-lt1dx1')[0].childNodes[2].childNodes[0].textContent");
		System.out.println(hours);
		String url = driver.getCurrentUrl();
		driver.close();

		driver.switchTo().window(parentwin);
		builder.moveToElement(tiles.get(1)).click().build().perform();
		Thread.sleep(2000);
		Set<String> child = driver.getWindowHandles();
		for (String o : child) {
			if (!o.equals(parentwin)) {
				driver.switchTo().window(o);
			}
		}
		Thread.sleep(1000);
		String hours1 = (String) jse.executeScript(
				"return document.getElementsByClassName('css-lt1dx1')[0].childNodes[2].childNodes[0].textContent");
		System.out.println(hours1);
		driver.close();
		driver.switchTo().window(parentwin);
		return url;
	}
}
package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import TestCases.BaseClass;
import utilities.ScreenShot;

public class ForCampusPage extends BaseClass {
	// Utilities
	ScreenShot ss = new ScreenShot();
	// WebElements
	WebElement forCampus, campTitle, fName, lName, email, phone, instituteType, instituteName, role, department, needs,
			country, state, CB, submit, errMsg1, errMsg2;
	// locators
	By camp_loc = By.partialLinkText(" Universities");
	By title_loc = By.xpath("//img[@alt='Coursera for Campus']");
	By fName_loc = By.id("FirstName");
	By lName_loc = By.id("LastName");
	By email_loc = By.id("Email");
	By phone_loc = By.id("Phone");
	By it_loc = By.id("Institution_Type__c");
	By in_loc = By.id("Company");
	By role_loc = By.id("Title");
	By dept_loc = By.id("Department");
	By needs_loc = By.id("What_the_lead_asked_for_on_the_website__c");
	By country_loc = By.id("Country");
	By state_loc = By.id("State");
	By CB_loc = By.xpath("//input[@name='Express_Consent__c']");
	By submit_loc = By.xpath("//button[@type='submit']");
	By errMsg1_loc = By.xpath("//div[@id='ValidMsgEmail'][@role='alert']");
	By errMsg2_loc = By.xpath("//div[@id='ValidMsgPhone'][@role='alert']");

	// actions
	public void clickCampus() {
		forCampus = driver.findElement(camp_loc);
		forCampus.click();
	}

	public String checkTitle() {
		campTitle = driver.findElement(title_loc);
		String title = campTitle.getAttribute("alt");
		return title;
	}

	public void enterFirstName(String fname) {
		fName = driver.findElement(fName_loc);
		fName.sendKeys(fname);
	}

	public void enterLastName(String lname) {
		lName = driver.findElement(lName_loc);
		lName.sendKeys(lname);
	}

	public void enterEmail(String eMail) {
		email = driver.findElement(email_loc);
		email.sendKeys(eMail);
	}

	public void enterPhoneNumber(String Phone) {
		double num = Double.parseDouble(Phone);
		Long num2 = (long) num;
		String value = num2.toString();
		phone = driver.findElement(phone_loc);
		phone.sendKeys(value);
	}

	public void selectInstituteType(String type) {
		instituteType = driver.findElement(it_loc);
		Select select = new Select(instituteType);
		select.selectByVisibleText(type);
	}

	public void enterInstituteName(String name) {
		instituteName = driver.findElement(in_loc);
		instituteName.sendKeys(name);
	}

	public void selectRole(String Role) {
		role = driver.findElement(role_loc);
		Select select = new Select(role);
		select.selectByVisibleText(Role);
	}

	public void selectDepartment(String dept) {
		department = driver.findElement(dept_loc);
		Select select = new Select(department);
		select.selectByVisibleText(dept);
	}

	public void selectNeed(String need) {
		needs = driver.findElement(needs_loc);
		Select select = new Select(needs);
		select.selectByVisibleText(need);
	}

	public void selectCountry(String con) {
		country = driver.findElement(country_loc);
		Select select = new Select(country);
		select.selectByVisibleText(con);
	}

	public void selectState() {
		try {
			state = driver.findElement(state_loc);
			Select select = new Select(state);
			select.selectByIndex(2);
		} catch (Exception e) {
			System.out.println("No state displayed");
		}
	}

	public void clickCheckBox() {
		try {
			CB = driver.findElement(CB_loc);
			CB.click();
		} catch (Exception e) {
			System.out.println("No checkBox displayed");
		}
	}

	public void clickSubmit() throws InterruptedException {
		submit = driver.findElement(submit_loc);
		jse.executeScript("arguments[0].click()", submit);
	}

	public String returnErrorMsg() {
		try {
			errMsg1 = driver.findElement(errMsg1_loc);
			return errMsg1.getText();
		} catch (Exception e) {
			return null;
		}
	}

	public boolean captureEmailErrorMsg() {
		try {
			if (errMsg1.isDisplayed()) {
				ss.takeScreenShot("EmailError");
				System.out.println("Error message : " + errMsg1.getText());
				System.out.println("Screenshot taken");
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println("No email error msg");
			return false;
		}
	}
}

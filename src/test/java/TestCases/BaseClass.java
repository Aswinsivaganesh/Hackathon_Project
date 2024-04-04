package TestCases;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import utilities.reportGenerator;

public class BaseClass extends reportGenerator {
	public static WebDriver driver;
	public static JavascriptExecutor jse;
	public static TakesScreenshot ts;

	@BeforeTest(groups = {"smoke"})
	@Parameters({"browser"})
	public void beforeSuite(String br) throws Exception {
		if(br.equals("Chrome")) {
		driver = new ChromeDriver();
		}
		else if(br.equals("Edge")) {
			driver = new EdgeDriver();
		}
		jse = (JavascriptExecutor) driver;
		ts = (TakesScreenshot) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.coursera.org/");
		Thread.sleep(1000);
	}

	@AfterTest(groups = {"smoke"})
	public void afterSuite() {
		System.out.println("Test Passed");
		driver.quit();
	}
	@BeforeMethod
	public void beforeMethod(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		System.out.println("\nRunning test : " + testName);
	}
	@AfterMethod
	public void afterMethod(ITestResult result) {
		if(result.isSuccess()) {
			System.out.println("Result : PASSED");
		}else {
			System.out.println("Result : FAILED");
		}
	}
}

package tests.suite;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class TestGroup {
	WebDriver driver;
	JavascriptExecutor jse;

	@BeforeMethod
	public void initializeChrome() {
		System.setProperty("webdriver.chrome.driver",
				"/media/cesar-rayo/948688348688193E/java/ubuntu/selenium/webdrivers/chromedriver_linux64/chromedriver");
		driver = new ChromeDriver();

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void closeBrowser(){
		driver.close();
	}
	
	@Test(priority=0)
	public void searchProduct() {
		driver.get("http://www.amazon.com");

		String searchXpath = "//*[@id=\"twotabsearchtextbox\"]";
		String btnSearchXpath = "//*[@id=\"nav-search\"]/form/div[2]/div/input";

		driver.findElement(By.xpath(searchXpath)).sendKeys("PS4");
		driver.findElement(By.xpath(btnSearchXpath)).click();

		jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, 500)");

		driver.findElement(By.xpath("//*[@id=\"p_89/Microsoft\"]/span/a/span")).click();

	}
	
	@Test(priority=1)
	public void typeUserFacebook() {
		driver.get("http://www.facebook.com");

		WebElement txtUser = driver.findElement(By.id("email"));

		Actions builder = new Actions(driver);

		Action act = builder.moveToElement(txtUser).click(txtUser).keyDown(Keys.SHIFT).sendKeys("hello")
				.keyUp(Keys.SHIFT).doubleClick(txtUser).contextClick().build();

		act.perform();
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File srcFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile,
					new File("/home/cesar-rayo/Desktop/workspace/SeleniumBasics/screenShots/typeUserFacebook.png"));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	@Test(priority=10)
	public void navigationBar() {
		try {
			// Moving back and forward
			driver.navigate().to("http://www.amazon.com");
			driver.findElement(By.linkText("Today's Deals")).click();
			System.out.println("Current Url: " + driver.getCurrentUrl());

			driver.findElement(By.linkText("Help")).click();
			System.out.println("Current Url: " + driver.getCurrentUrl());

			driver.navigate().back();
			System.out.println("Current Url: " + driver.getCurrentUrl());

			driver.navigate().forward();
			System.out.println("Current Url: " + driver.getCurrentUrl());

			driver.navigate().refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(enabled = false)
	public void waitForScpecificELement() {
		WebDriverWait wait = new WebDriverWait(driver, 20); // driver waits 20 sec till div appears
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=div-some]")));
		
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", element); //Scroll till particular element
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent()); // driver waits 20 sec till alert appears
		
		Actions act = new Actions(driver); //Keyboard Mouse Actions
		act.clickAndHold(element);
		act.doubleClick(element);
		act.sendKeys("Some keys");
		
		act.keyDown(Keys.CONTROL);
		act.keyUp(Keys.CONTROL); //Send Control key pressed signal
		
		//Handling dropdown
		Select dropdown = new Select(driver.findElement(By.id("my-select")));
		dropdown.selectByValue("option2");
		dropdown.selectByIndex(2);
		dropdown.selectByVisibleText("Visible Option2");
	}
}

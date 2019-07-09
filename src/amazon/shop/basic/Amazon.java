package amazon.shop.basic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class Amazon {
	WebDriver driver;
	JavascriptExecutor jse;
	Screen s;


	public void initializeChrome() {
		System.setProperty("webdriver.chrome.driver",
				"/media/cesar-rayo/948688348688193E/java/ubuntu/selenium/webdrivers/chromedriver_linux64/chromedriver");
		driver = new ChromeDriver();

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		//driver.close(); // Closes current tab
		// driver.quit //Closes the entire browser
	}

	public void locationTecniques() {
		/*
		 * <input id="abc" class="xyz" .. />
		 * 
		 * driver.findElement(By.cssSelector("input#abc")); >> Searching by tag
		 * name and id driver.findElement(By.cssSelector(input.xyz)); >>
		 * Searching by tag name and class
		 */
		String selection = "tag#id_value";
		driver.findElement(By.cssSelector(selection));

		/*
		 * Relative path
		 * "//Element[@Atribute='something']/child[@Attribute='else']/child[index]"
		 * 
		 * Calendar example
		 * "//div[@id='Paq_0_month_06_2019']/div/table/tbody/tr[4]/td[4]/a/span"
		 * "//div[@id='Paq_0_month_06_2019']//tr[4]/td[4]/a/span" >> even better
		 */
		String signInXpath = "//div[@id='nav-tools']/a[@id='nav-link-account']/span[1]";
		driver.findElement(By.xpath(signInXpath));
	}
	
	public void searchImageProductos(){
		try {
			//sikuli
			driver.get("https://luzavargas.github.io/cosmetics/");
			s = new Screen();
			Pattern searchImage = new Pattern("/home/cesar-rayo/Desktop/workspace/SeleniumBasics/images/productos.png");
			
			jse = (JavascriptExecutor) driver;
			jse.executeScript("scroll(150,0)");
			s.wait(searchImage, 10);
			s.find(searchImage).click();
			System.out.println("Current URL" + driver.getCurrentUrl());
		} catch (FindFailed e) {
			e.printStackTrace();
		}

	}

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

	public void navigationBar() {
		try {
			// Moving back and forward
			driver.navigate().to("http://www.amazon.com");
			driver.findElement(By.linkText("Today's Deals")).click();
			System.out.println("Current Url: " + driver.getCurrentUrl());

			driver.findElement(By.linkText("Gift Ideas")).click();
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

	public static void main(String[] args) {
		Amazon test = new Amazon();

		test.initializeChrome();
		test.searchProduct();
	}

}
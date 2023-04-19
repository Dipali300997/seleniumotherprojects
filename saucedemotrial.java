package Doreamon;

import java.awt.Window;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import freemarker.template.utility.Execute;
import io.github.bonigarcia.wdm.WebDriverManager;

public class saucedemotrial {
	public static String url = "https://www.saucedemo.com/";
	ExtentReports er;
	ExtentHtmlReporter ehr;
	ExtentTest et;
	WebDriver driver;

	@BeforeTest
	public void bt() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		er = new ExtentReports();
		ehr = new ExtentHtmlReporter("extent.html");
		er.attachReporter(ehr);

		et = er.createTest("Before Test");

	}

	@Test(priority = -1,dataProvider = "Dips")
	public void login(String M, String N) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		et = er.createTest("Login");
		driver.findElement(By.xpath("//*[@placeholder='Username']")).sendKeys(M);
		et.pass("User is able to enter an username");
		driver.findElement(By.xpath("//*[@placeholder='Password']")).sendKeys(N);
		et.pass("User is able to enter a password");
		driver.findElement(By.xpath("//*[@id='login-button']")).click();
		et.info("User is able to login");
	}

	@DataProvider
	public Object[][] Dips(){
		return new Object[][] {
			new Object[] { "standard_user", "secret_sauce" }
			};
	}

	@Test(priority = 0)
	public void filters() throws InterruptedException {

		et = er.createTest("filters");
		WebElement filter = driver.findElement(By.xpath("//*[@class='product_sort_container']"));
		Thread.sleep(3000);
		filter.click();
		Thread.sleep(3000);
		Select s1 = new Select(filter);
		Thread.sleep(3000);
		s1.selectByIndex(3);
		Thread.sleep(3000);
		et.fail("user is unable to click on filters");
	}

	@Test(priority = 2)
	public void buyproduct() throws InterruptedException {
		et = er.createTest("Buy Product");
		driver.findElement(By.xpath("//*[@id='item_4_title_link']")).click();
		Thread.sleep(2000);
		et.pass("User is able to click on a product");
		driver.findElement(By.xpath("//*[@name='add-to-cart-sauce-labs-backpack']")).click();
		Thread.sleep(2000);
		et.pass("User is able to add an item in a cart");
		driver.findElement(By.xpath("//*[@name='remove-sauce-labs-backpack']")).click();
		Thread.sleep(2000);
		et.pass("User is able to remove an item from cart");
		driver.findElement(By.xpath("//*[@id='back-to-products']")).click();
		Thread.sleep(2000);
		et.pass("User is able to click on back to home");
		driver.findElement(By.xpath("//*[@id='item_1_title_link']")).click();
		Thread.sleep(2000);
		et.pass("User is able to click on a product again");
		driver.findElement(By.xpath("//*[@name='add-to-cart-sauce-labs-bolt-t-shirt']")).click();
		Thread.sleep(2000);
		et.pass("User is able to add an item in a cart");
		driver.findElement(By.xpath("//*[@class='shopping_cart_badge']")).click();
		Thread.sleep(2000);
		et.pass("User is able to click on a symbol 'cart'");
		driver.findElement(By.xpath("//*[@name='checkout']")).click();
		Thread.sleep(2000);
		et.pass("User is able to click on checkout");
		driver.findElement(By.xpath("//*[@id='first-name']")).sendKeys("Dipali");
		Thread.sleep(2000);
		et.pass("User is able to enter a firstname");
		driver.findElement(By.xpath("//*[@id='last-name']")).sendKeys("Gadam");
		Thread.sleep(2000);
		et.pass("User is able to enter the last name");
		driver.findElement(By.xpath("//*[@id='postal-code']")).sendKeys("456985");
		Thread.sleep(2000);
		et.pass("User is able to enter the postalcode");
		driver.findElement(By.xpath("//*[@id='continue']")).click();
		Thread.sleep(2000);
		et.pass("User is able to click on continue");
		driver.findElement(By.xpath("//*[@id='finish']")).click();
		Thread.sleep(2000);
		et.pass("User is able to click on finish");
		driver.findElement(By.xpath("//*[@id='back-to-products']")).click();
		Thread.sleep(2000);
		et.warning("User is not able to click on back home");
	}

	@Test(priority = 3)
	public void about() throws InterruptedException {

		driver.findElement(By.xpath("//*[@class='bm-burger-button']")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("About")).click();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,6000)");// scroll down till the last
		Thread.sleep(4000);
	}

	@Test(priority = 4)
	public void Logout() throws InterruptedException {
		et = er.createTest("logout");
		driver.navigate().back();// going one step back in the browser
		Thread.sleep(3000);
		et.pass("User is able to go one step back in browser");
		driver.findElement(By.xpath("//*[@id='logout_sidebar_link']")).click();
		Thread.sleep(3000);
		et.pass("User is able to click on logout");
		et.info("User is able to perform all the test cases except 'buy product' method");
		driver.close();
		er.flush();
	}
}

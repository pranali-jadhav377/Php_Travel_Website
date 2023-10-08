import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class php_travel_website {

	public String baseURL = "https://phptravels.net/admin/login.php";
	public WebDriver driver ;
	public String expectedTitle = null;
	public String actualTitle = null;


	@BeforeTest
	public void launchBrowser() {

		String projectLocation = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",projectLocation+"/chromedriver.exe" );
		//		System.out.println("user.dir: "+System.getProperty("user.dir"));
		System.out.println(System.getProperties());
		//		System.out.println("get user dir "+projectLocation);
		System.out.println();
		driver = new ChromeDriver();
		//code to maximize chrome browser
		driver.manage().window().maximize();
		driver.get(baseURL);
	}

	@BeforeClass
	public void intializedriver() {

		driver.manage().window().maximize();
		driver.get(baseURL);

	}
	@BeforeMethod
	public void verifyHomepage(){

		String expectedTitle = "Administrators Login";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);

	}
	@Test	
	public void loginPage() throws InterruptedException {

		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("admin@phptravels.com");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("demoadmin");
		driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
		System.out.println(driver.getTitle());
		String expectedTitle = "Dashboard";
		String title = driver.getTitle();
		if (title.equalsIgnoreCase(expectedTitle)) {
			System.out.println("Title Matched");
		} else {
			System.out.println("Not a match");
		};
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/main/header/ul/li[2]/button")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//a[normalize-space()='Currencies']")).click();
		String expectedTitle1 = "Currencies";
		String actualTitle1 = driver.getTitle();
		Assert.assertEquals(actualTitle1, expectedTitle1);
		System.out.println("User is on Curriencies Page");
		driver.findElement(By.xpath("//a[contains(@class,'xcrud-button xcrud-green xcrud-action')]")).click();
		
		//DropDown for Selecting "Enabled"
		driver.findElement(By.xpath("(//span[@role='combobox'])[1]")).click();
		Thread.sleep(2000);
		WebElement enabled= driver.findElement(By.xpath("//li[@role='option'][1]"));
		enabled.click();
		driver.findElement(By.xpath("(//input[@name='Y3VycmVuY2llcy5uYW1l'])[1]")).sendKeys("IND");
		
		//DropDown for Selecting Country "India"
		driver.findElement(By.xpath("(//span[@role='combobox'])[2]")).click();
		Thread.sleep(2000);
		WebElement IND= driver.findElement(By.xpath("//li[@role='option'][105]"));
		IND.click();
		driver.findElement(By.xpath("(//input[@name='Y3VycmVuY2llcy5yYXRl'])[1]")).sendKeys("10.0");
		driver.findElement(By.linkText("Save")).click();
		String expectedTitle2 = "Currencies";
		String actualTitle2 = driver.getTitle();
		Assert.assertEquals(actualTitle2, expectedTitle2);
		System.out.println("User is on Curriencies Page after adding country IND");
		
		// Editing Currency Details from List of Countries on Currencies Page
		 driver.findElement(By.xpath("(//a[@title='Edit'])[3]")).click();
		 driver.findElement(By.xpath("(//input[@name='Y3VycmVuY2llcy5yYXRl'])[1]")).clear();
		 driver.findElement(By.xpath("(//input[@name='Y3VycmVuY2llcy5yYXRl'])[1]")).sendKeys("9.00");
		 driver.findElement(By.linkText("Save")).click();
		 System.out.println("The EUR Country Currency is Updated to 9.00");
		 
		 // Deleting Country Currencies from Curriences Page
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("(//input[@value='3'])[1]")).click();
		 driver.findElement(By.xpath("(//a[@title='Remove'])[2]")).click();
		 driver.switchTo().alert().accept();
		 System.out.println("Country Currency Successfully Deleted");
		 
	}
	@AfterTest
	public void terminateBrowser(){
        driver.close();
		
	}
	
	@AfterSuite
	public void Close()
	{
		
		driver.quit();
	}
}




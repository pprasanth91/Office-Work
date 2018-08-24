package DataDriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.gargoylesoftware.htmlunit.WebConsole.Logger;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import net.jcip.annotations.ThreadSafe;


public class Data_Provider {
	
	public static WebDriver driver; 
	public static String brurl = "https:\\www.amazon.in";
	public static int iBroType = 1 ;
	public static ExtentReports logger = new ExtentReports();	
	
	@BeforeTest	
	public static void browser_Invoke() {
		
		switch (iBroType) {
		case 1:
			System.out.println("User Option is : "+iBroType+", So Invoking Chrome Browser");
			System.out.println("Thread id is " + Thread.currentThread().getId());
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\p.b.prabhakaran\\Downloads\\JDK Path\\Browser Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			logger.init ("C:\\Users\\p.b.prabhakaran\\workspace\\Selenium_Practice\\Report\\Amazon Report.html", true);
			logger.startTest("Invoke Browser" , "Opening Chrome Browser");
			logger.log(LogStatus.INFO, "Browser Invoked Successfully");
			break;
		case 2:
			System.out.println("User Option is : "+iBroType+", So Invoking FireFox Browser");
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\acer\\Downloads\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			logger.startTest("Invoke Browser" , "Opening Firefox Browser");
			logger.log(LogStatus.INFO, "Browser Invoked Successfully");
			logger.endTest();
			break;
			}
	}
	
	@Test (priority=0)
	public static void navigate_url() throws Exception{
		logger.startTest("WebPage Navigation", "Redirecting to Amazon Page");
		driver.manage().deleteAllCookies();
		driver.get(brurl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		Thread.sleep(5000);
		logger.log(LogStatus.INFO, "User Navigated to the Amazon Page Successfully");
		logger.endTest();
	}
	
	@Test (priority=1 , dataProvider ="Search text")
	public static void Search_product(String stext) throws Exception{
		String stxt = "Iphone";
		logger.startTest("Search Product" , "Perform Product Search");
		logger.log(LogStatus.INFO,"Search with text " + stext);
		WebElement sfield , scategory , sbtn , rtxt ;
		sfield = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		sfield.clear();
		sfield.sendKeys(stext);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		scategory = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
		scategory = driver.findElement(By.xpath("//*[@id='searchDropdownBox']"));
		Select oselect = new Select(scategory);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		oselect.selectByVisibleText("Electronics");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		sbtn = driver.findElement(By.xpath("//input[@type='submit'][@class='nav-input']"));
		sbtn.click();
		Thread.sleep(10000);
		logger.endTest();
		
	}
	
	@DataProvider(name="Search text")
	public Object[][] pass_data() throws Exception{
	
	FileInputStream ofile = new FileInputStream("C:\\Users\\p.b.prabhakaran\\workspace\\Selenium_Practice\\Datasheet\\Credo.xlsx");
	XSSFWorkbook oExcel = new XSSFWorkbook (ofile);
	XSSFSheet oSheet = oExcel.getSheet("Amazon");
	XSSFRow oRow = null;
	XSSFCell oCell = null;
	int Rowcount;
	Rowcount = oSheet.getLastRowNum();
	Object[][] data = new Object[Rowcount][1];
	for (int i=1 ; i<=Rowcount ; i++){
	oRow = oSheet.getRow(i);
	oCell = oRow.getCell(0);
	data [i-1][0]= oCell.getStringCellValue();
	System.out.println(data);
			}
	return data;
	}
	
	//@ DataProvider(name="Search text")
	/*public Object[][] pass_data(){
	Object[][] data = new Object[2][1];
	data [0][0] = "Iphone";
	data [1][0] = "Motorola";
	System.out.println(data);
	return data;
	}*/
	
	@AfterTest
	public static void Close_Browser(){
		logger.startTest("Close Browser", "Close the Browser");
		driver.quit();
		logger.log(LogStatus.INFO,"Browser Closed");
		logger.endTest();
	}
	
}






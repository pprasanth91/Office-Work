package IJP_Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.Wrapper_Class;

public class IJP_Homepage {

	public static WebDriver driver; 
	public static int iBroType = 1;
	public static String brurl = "https://india.jobs.ciostage.accenture.com/";
	public static Wrapper_Class owrapper;
	public static ExtentReports ologger = new ExtentReports();
		
	By Cndbtn = By.xpath("//a[text()='Candidate']");
	By Agencybtn = By.xpath("//a[text()='Agency']");
	By Emplbtn = By.linkText("Employee");
	By loginbtn = By.xpath("//input[@id='btnCandLogin']");
	By Signinbtn = By.xpath("//input[@value='Sign In']");
	
	@BeforeTest
	public static void Invoke_Browser(){
		ologger.init("C:\\Users\\p.b.prabhakaran\\workspace\\Selenium_Practice\\Report\\ijpreport.html", true);
		ologger.startTest("Invoke Browser" , "Invokes Browser of User Choice");
		switch (iBroType) {
		case 1:
			System.out.println("User Option is : "+iBroType+", So Invoking Chrome Browser");
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\p.b.prabhakaran\\Downloads\\JDK Path\\Browser Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case 2:
			System.out.println("User Option is : "+iBroType+", So Invoking FireFox Browser");
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\p.b.prabhakaran\\Downloads\\JDK Path\\Browser Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
				}	
		ologger.log(LogStatus.INFO, "Browser Invoked Successfully");
		ologger.endTest();
	}
	
	@Test (priority=0)
	public static void navigate_url(){
		ologger.startTest("Navigate url" , "Navigate to IJP Home Page");
		driver.manage().window().maximize();
		driver.get(brurl);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String pagetitle = driver.getTitle();
		
		if (pagetitle.contains("jobs")){
		ologger.log(LogStatus.PASS, "User Navigated to IJP Home Page");
				}
		else {
		ologger.log(LogStatus.FAIL, "User Not Navigated to IJP Home Page");	
		
		}
		ologger.endTest();
	}
	
	@Test(priority=1)
	public void Candidate_login(){
	ologger.startTest("Candidate Login", "Login as a Candidate");	
	owrapper = new Wrapper_Class(driver);
	boolean result = owrapper.button_click(Cndbtn);	
	if (result=true){
		ologger.log(LogStatus.PASS, "Candidate Button Available and Button Click Successful");
	}
	else {
		ologger.log(LogStatus.FAIL, "Candidate Button Not Available");
	}
	
	boolean value = owrapper.button_click(loginbtn);
	if (value= true){
		ologger.log(LogStatus.PASS, "Login button Available");
			}
	else {
		ologger.log(LogStatus.FAIL, "Login Button Not Available");
	}
	
	WebElement oelement = owrapper.Verify_Elementpresence(Signinbtn); 
	
	if (oelement!=null){
		ologger.log(LogStatus.PASS, "User Redirectd to the Candidate Login Page Successfully");
	}
	else {
		ologger.log(LogStatus.FAIL, "User is not Redirected to the Correct Page");
	}
	ologger.endTest();
	
	}

}

package Driver_Control;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Start_Browser {
	
	public static WebDriver driver; 
	public static String brurl = "https://www.google.co.in/";
	public static int iBroType = 1;
	
	@BeforeTest
	public static void browser_Invoke() {
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

	}
	
	@Test (priority=0)
	public static void navigate_url(){
		driver.get(brurl);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test (priority=1)
	public static WebDriver get_driver(){
		return driver;
	}
	
}

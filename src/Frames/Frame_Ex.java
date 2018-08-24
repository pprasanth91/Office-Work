package Frames;

	import java.util.List;
	import java.util.concurrent.TimeUnit;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.htmlunit.HtmlUnitDriver;
	import org.openqa.selenium.ie.InternetExplorerDriver;


	public class Frame_Ex {
		
		public static WebDriver driver;
		public static int iBroType = 1; //1-Chrome,2-FF,3-IE,4-HTML Unit Driver
		public static String sURL = "http://seleniumhq.github.io/selenium/docs/api/java/index.html";

		
		public static void browserInvoke() {
			switch (iBroType) {
			case 1:
				System.out.println("User Option is : "+iBroType+", So Invoking Chrome Browser");
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\p.b.prabhakaran\\Downloads\\JDK Path\\Browser Drivers\\chromedriver.exe");
				driver = new ChromeDriver();
				break;
			case 2:
				System.out.println("User Option is : "+iBroType+", So Invoking FireFox Browser");
				System.setProperty("webdriver.gecko.driver", "F:\\credo\\Selenium Software\\geckodriver.exe");
				driver = new FirefoxDriver();
				break;
			case 3:
				System.out.println("User Option is : "+iBroType+", So Invoking IE Browser");
				System.setProperty("webdriver.ie.driver", "F:\\credo\\Selenium Software\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				break;
			case 4:
				System.out.println("User Option is : "+iBroType+", So Invoking Headless Browser");
				driver = new HtmlUnitDriver();
				break;

			default:
				System.out.println("User Option is Wrong : "+iBroType+", So Invoking Default Chrome Browser");
				System.setProperty("webdriver.chrome.driver", "F:\\credo\\Selenium Software\\chromedriver.exe");
				driver = new ChromeDriver();
				break;
			}
		}
		
		public static void browserSettings() {
			if(iBroType==2) {
				driver.manage().deleteAllCookies();
			}else {
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
			}
		}
		
		public static void navigateURL() {
			driver.get(sURL);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		}
		
		public static void getPageInfo() {
			System.out.println("Page Title is : "+driver.getTitle());
			System.out.println("Page URL is : "+driver.getCurrentUrl());
		}
		
		public static void Switching_Frames(){
			WebElement oFrame;
			oFrame = driver.findElement(By.name("packageListFrame"));
			driver.switchTo().frame(oFrame);
			driver.findElement(By.linkText("com.thoughtworks.selenium")).click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("packageFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.linkText("CommandProcessor")).click();
			driver.switchTo().defaultContent();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().frame("classFrame");
			driver.findElement(By.linkText("HttpCommandProcessor")).click();
			
		}
		
		
		
		public static void closeBrowser() {
			driver.close();
		}
		
		public static void main(String[] args) {
			
			browserInvoke();
			//browserSettings();
			navigateURL();
			//getPageInfo();
			Switching_Frames();
			closeBrowser();
		}

	}












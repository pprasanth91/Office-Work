package Selenium_Practice;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Chromedriver {
	
	public static int iBroType = 1; //1-Chrome,2-FF,3-IE,4-HTML Unit Driver
	public static String sURL = "https://phptravels.com/demo/";

	public static void browserInvoke() {
		switch (iBroType) {
		case 1:
			System.out.println("User Option is : "+iBroType+", So Invoking Chrome Browser");
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\p.b.prabhakaran\\Downloads\\JDK Path\\Browser Drivers\\chromedriver.exe");
			ChromeDriver driver1 = new ChromeDriver();
			break;
		case 2:
			System.out.println("User Option is : "+iBroType+", So Invoking FireFox Browser");
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\p.b.prabhakaran\\Downloads\\JDK Path\\Browser Drivers\\geckodriver.exe");
			FirefoxDriver driver2 = new FirefoxDriver();
			break;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

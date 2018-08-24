package Driver_Control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class StartBrowser_Child1 extends Start_Browser {
	
	static WebDriver driver;
	
	@Test (priority=3)
	public static void Transfer_driver(){
			driver = get_driver();		
		}
	
	@Test (priority=4)
	public static void search_text() throws Exception{
		WebElement txtbox;
		txtbox = driver.findElement(By.id("lst-ib"));
		txtbox.sendKeys("gmail.com");
		Thread.sleep(10000);
	}
	
	@AfterTest
	public static void close_browser(){
		driver.close();
	}
	
}

package Driver_Control;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class OpenBrowser_Child1 {
	
	static WebDriver driver;
			
	@Test (priority=2)
	public void Transfer_driver(){
	
		driver = Open_Browser.get_driver();
	}
	
	@Test (priority=3)
	public static void goto_cart() throws Exception{
		WebElement cart;
		cart = driver.findElement(By.id("nav-cart-count"));
		cart.click();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		Thread.sleep(10000);		
	}
	
	@AfterTest
	public static void Close_Browser(){
		driver.close();
	}

}

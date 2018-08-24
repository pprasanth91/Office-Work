package Selenium_Practice;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Php_Travels {
	public static WebDriver driver;
	public static int iBroType = 1; //1-Chrome,2-FF,3-IE,4-HTML Unit Driver
	public static String sURL = "https://phptravels.com/demo/";

	public static void browserInvoke() {
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
//[@class='row'] text()[1]
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
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	public static void closebrowser(){
		driver.quit();
	}
	
	public static void get_login(){
		List <WebElement> ologin = driver.findElements(By.xpath("(//div[@class='col-md-8']/div)[2]/strong"));
		WebElement ouser,otest;
		otest = driver.findElement(By.xpath("(//div[@class='col-md-8']/div)[2]"));
		//for (int i=0; i<ologin.size() ; i++){
			//ouser = ologin.get(i);
			System.out.println("User Name is : " + otest.getText());
		}
	
	
	public static void php_login(){
		WebElement ouserid ,opwd ,osubmit, loginlnk ;
		loginlnk = driver.findElement(By.xpath("(//a[@class='btn btn-primary btn-lg btn-block'])[2]"));
		Actions oaction = new Actions (driver);
		oaction.moveToElement(loginlnk).build().perform();
		loginlnk.click();
		driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		ouserid = driver.findElement(By.xpath("//input[@name='email'][@type='text']"));
		opwd = driver.findElement(By.xpath("//input[@name='password'][@type='password']"));
		osubmit = driver.findElement(By.xpath("//button[@type='submit']"));
		ouserid.sendKeys("admin@phptravels.com");
		opwd.sendKeys("demoadmin");
		osubmit.click();
		//logintxt = driver.findElement(By.xpath("//div/strong"));
		//System.out.println("Login Info : " + logintxt.getText());
			}
	
	public static void php_search() throws Exception{
		WebElement osearch,osrchtxt,oGo,otable;
		WebDriverWait owait = new WebDriverWait (driver,20);
		owait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-target='#quickbook']")));
		System.out.println("Page Redirection Successful");
		Actions oaction = new Actions (driver);
		oaction.moveToElement(driver.findElement(By.xpath("//*[@class='xcrud-search-toggle btn btn-default']"))).build().perform();
		osearch = driver.findElement(By.xpath("//*[@class='xcrud-search-toggle btn btn-default']"));
		osearch.click();
		Thread.sleep(3000);
		osrchtxt = driver.findElement(By.xpath("//input[@class='xcrud-searchdata xcrud-search-active input-small form-control']"));
		String txt = "69";
		osrchtxt.sendKeys(txt);
		oGo = driver.findElement(By.xpath("//a[contains(text(),'Go')]"));
		oGo.click();
		Thread.sleep(5000);
		oaction.moveToElement(driver.findElement(By.xpath("//div[@class='xcrud-list-container']/table"))).build().perform();
		otable = driver.findElement(By.xpath("//div[@class='xcrud-list-container']/table/tbody"));
		List <WebElement> orow = otable.findElements(By.tagName("tr"));
		//List <WebElement> olist = driver.findElements(By.xpath("//div[@class='xcrud-list-container']/table/tbody/tr"));
		System.out.println("The Number of Rows in the table is : " + orow.size());
		System.out.println("The Column Values are : ");
		for (int i=0 ; i<orow.size();i++){
			int index = i+1;
			List <WebElement> ocol = driver.findElements(By.xpath("//div[@class='xcrud-list-container']/table/tbody/tr["+index+"]"+"/td"));
			for (int j=0 ; j<ocol.size();j++){
				WebElement ocolvalue;
				ocolvalue = ocol.get(j);
				System.out.print("  " +ocolvalue.getText());
				}
			System.out.println();
			}
		}

public static void main(String[] args) throws Exception {
	// TODO Auto-generated method stub
	browserInvoke();
	browserSettings();
	navigateURL();
	get_login();
	//php_login();
	//php_search();
	//closebrowser();
	}
}

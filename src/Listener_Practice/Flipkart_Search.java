package Listener_Practice;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Exception_Handling.Exception1;

public class Flipkart_Search {
	
	public static WebDriver driver; 
	public static String brurl = "https://www.flipkart.com";
	public static int iBroType = 1;
	public static String Resulttxt; 
	public static String Searchtext;
	public static List <String> oresult = new ArrayList <String>();
	
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
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\p.b.prabhakaran\\Downloads\\JDK Path\\Browser Drivers\\chromedriver.exe");
			driver = new FirefoxDriver();
			break;
			}
	}
	
	@Test
	public static void navigate_url(){
		driver.manage().deleteAllCookies();
		driver.get(brurl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}
	
	@Test
	public static void Read_Excel() throws java.lang.Exception {
		FileInputStream ofile = new FileInputStream("C:\\Users\\p.b.prabhakaran\\workspace\\Selenium_Practice\\Datasheet\\Credo.xlsx");
		XSSFWorkbook oExcel = new XSSFWorkbook(ofile);
		XSSFSheet oSheet = oExcel.getSheet("Flipkart");
		XSSFRow oRow = null;
		XSSFCell oCell = null;
		int Rowcount,Colcount;
		Rowcount = oSheet.getLastRowNum();
		for (int i=1 ; i<=Rowcount ; i++){
			oRow = oSheet.getRow(i);
			Colcount = oRow.getLastCellNum();
			for (int j=0 ; j<Colcount ; j++){
				 Searchtext = oRow.getCell(j).toString();
				System.out.println("Searchtext is " + Searchtext);
			}
			Product_Search(Searchtext);
			Validate_Search(Resulttxt);
		}
		ofile.close();	
		Write_Excel1(oresult);
		System.out.println(oresult);
	}
	
	public static void Write_Excel(List<String> oresult) throws Exception {
		FileInputStream ofile = new FileInputStream("C:\\Users\\p.b.prabhakaran\\workspace\\Selenium_Practice\\Datasheet\\Credo.xlsx");
		XSSFWorkbook oExcel = new XSSFWorkbook(ofile);
		XSSFSheet oSheet = oExcel.getSheet("Flipkart");
		XSSFRow oRow = null;
		XSSFCell oCell = null;
		String temp;
		int Rowcount,Colcount;
		Rowcount = oSheet.getLastRowNum();
		for (int i=1;i<=Rowcount;i++){
			oRow = oSheet.getRow(i);
		//	Colcount = oRow.getLastCellNum();
			oCell = oRow.createCell(1);	
			oCell.setCellValue(oresult.get(i-1).toString());	
		}
		ofile.close();
		FileOutputStream owrite = new FileOutputStream("C:\\Users\\p.b.prabhakaran\\workspace\\Selenium_Practice\\Datasheet\\Credo.xlsx");
		oExcel.write(owrite);
		owrite.close();
		oExcel.close();
	}
	
	public static void Write_Excel1(List<String> oresult) throws Exception  {
		FileInputStream ofile = new FileInputStream("C:\\Users\\p.b.prabhakaran\\workspace\\Selenium_Practice\\Datasheet\\Credo.xlsx");
		XSSFWorkbook oExcel = new XSSFWorkbook(ofile);
		XSSFSheet oSheet = oExcel.getSheet("Flipkart");
		XSSFRow oRow = null;
		XSSFCell oCell = null;
		String temp;
		int j=1,Rowcount;
		Rowcount = oSheet.getLastRowNum();
		for (int i=0 ; i<oresult.size(); i++){
			temp = oresult.get(i).toString();
			
			if (j<=Rowcount){
				oRow = oSheet.getRow(j);
				oCell = oRow.createCell(1);
				oCell.setCellValue(temp);
			}
			j++;
		}
		ofile.close();
		FileOutputStream owrite = new FileOutputStream("C:\\Users\\p.b.prabhakaran\\workspace\\Selenium_Practice\\Datasheet\\Credo.xlsx");
		oExcel.write(owrite);
		owrite.close();
		oExcel.close();
	}
	
	public static void Validate_Search(String Resulttxt){
		String Result;
		String A[] = Resulttxt.split(" ");
		int Resultcount = Integer.parseInt(A[3]);
		System.out.println("Result Count is " + Resultcount);
		if (Resultcount>0){
			 Result = "Valid"; 
		}
		else {
			 Result = "Invalid";
		}
		oresult.add(Result);
	}
	
	public static void Product_Search(String Searchtext) throws Exception{
		boolean presence;
		try {
			presence =	driver.findElement(By.xpath("//div[@class='_3Njdz7']/button")).isDisplayed();
			if (presence=true){
			WebElement Login = driver.findElement(By.xpath("//div[@class='_3Njdz7']/button"));
			Login.click();
			}
			
		} catch (NoSuchElementException e) {
				presence = false;
			}
		Thread.sleep(2000);
		WebElement txtbox = driver.findElement(By.xpath("(//input[@type='text'])[1]"));
		txtbox.sendKeys(Searchtext);
		WebElement Searchbtn = driver.findElement(By.xpath("//button[@type='submit']"));
		Searchbtn.click();
		Thread.sleep(3000);
		WebElement oResult = driver.findElement(By.xpath("//div[@id='container']//h1/span"));
		Resulttxt = oResult.getText();
		System.out.println(Resulttxt);
		List <WebElement> oList = driver.findElements(By.xpath("//div[@class='_3wU53n']"));
		for (int i=0 ; i<oList.size() ; i++){
			WebElement oproduct = oList.get(i);
			System.out.println("Product"+ i + "Name is" + oproduct.getText());
		}
		txtbox.clear();
		
	}
	
	@AfterTest
	public static void Close_Browser(){
		driver.quit();
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		browser_Invoke();
		navigate_url();
		Read_Excel();
		Close_Browser();	
	}

}

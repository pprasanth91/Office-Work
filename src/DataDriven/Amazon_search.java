package DataDriven;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Amazon_search {
	public static WebDriver driver; 
	public static String brurl = "https:\\www.amazon.in";
	public static int iBroType = 1;
	public static String Mobilename;
	public static String Category;
	public static List <String> Result = new ArrayList <String>();;
	
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
	
	public static void navigate_url(){
		driver.get(brurl);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	public static void Search_product(String A , String category) throws Exception{
		WebElement sfield , scategory , sbtn , rtxt ;
		String SearchResult;
		sfield = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		sfield.sendKeys(A);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		scategory = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
		scategory = driver.findElement(By.xpath("//*[@id='searchDropdownBox']"));
		Select oselect = new Select(scategory);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		oselect.selectByVisibleText(category);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		sbtn = driver.findElement(By.xpath("//input[@type='submit'][@class='nav-input']"));
		sbtn.click();
		Thread.sleep(5000);
		rtxt = driver.findElement(By.xpath("//*[@id='s-result-count']"));
		String B = rtxt.getText();
		System.out.println("The Search result is " + B);
		String C[] = B.split(" ");
		String D = C[0];
		String E[] = D.split("-");
		String G = E[1];
		System.out.println("The Search result is " + G);
		int F = Integer.parseInt(G);
		if (F>0) {
			 SearchResult = "Valid";
			 System.out.println("Your Search is " + SearchResult);
		}
		else {
			SearchResult = "Invalid";
			System.out.println("Your Search is " + SearchResult);
			}
		Result.add(SearchResult);
	}

	public static void clear_searchtext() throws Exception{
		WebElement sfield;
		sfield = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		Actions omouse = new Actions(driver);
		omouse.moveToElement(sfield);
		Thread.sleep(2000);
		sfield.clear();
			}
	
	public static void Get_productlist(){
		WebElement oElement;
		List <WebElement> oList = driver.findElements(By.xpath("//ul[@id='s-results-list-atf']/li/descendant::h2"));
		for (int i=0 ; i<oList.size();i++){
			oElement = oList.get(i);
			System.out.println("Product " + i + "Name is : " + oElement.getText());
			}
	
	}
	
		public static void Close_browser(){
		driver.close();
	}
	
	public static void Read_Excel() throws Exception{
		InputStream ofile = new FileInputStream("C:\\Users\\p.b.prabhakaran\\workspace\\Selenium_Practice\\Datasheet\\Credo.xlsx");
		XSSFWorkbook oExcel = new XSSFWorkbook(ofile);
		XSSFSheet oSheet= oExcel.getSheet("Amazon");
		XSSFRow oRow;
		XSSFCell oCell = null;
		int orowcount = oSheet.getLastRowNum();
		for(int i=1 ; i<=orowcount ; i++){
			oRow = oSheet.getRow(i);
			int ocolcount = oRow.getLastCellNum();
			
			for (int j=0 ; j<ocolcount ; j++){
				 oCell = oRow.getCell(j);
				if (j==0){
				Mobilename = oCell.getStringCellValue();
				}
				if (j==1){
				Category = oCell.getStringCellValue();
				}
				
			}
			System.out.println("The Mobile Name is " + Mobilename);
			System.out.println("The Search Category Value is " + Category);
			Search_product(Mobilename,Category);
			Get_productlist();	
			Thread.sleep(5000);
			clear_searchtext();
						
		}	
		oExcel.close();
		ofile.close();
	//	Write_Excel(Result);
	}
	
	public static void Write_Excel(List<String> Result) throws Exception{
		System.out.println("The Values of the ArrayList are " + Result);
		InputStream ofile = new FileInputStream("C:\\Users\\p.b.prabhakaran\\workspace\\Selenium_Practice\\Datasheet\\Credo.xlsx");
		OutputStream owrite = new FileOutputStream("C:\\Users\\p.b.prabhakaran\\workspace\\Selenium_Practice\\Datasheet\\Credo.xlsx");
		XSSFWorkbook oExcel = new XSSFWorkbook(ofile);
		XSSFSheet oSheet = oExcel.getSheet("Amazon"); 
		XSSFRow orow = null;
		XSSFCell ocell = null;
		int rowcount = oSheet.getLastRowNum();
		for (int i=1 ; i<=rowcount ; i++){
		 orow = oSheet.getRow(i);
		 int colcount = orow.getLastCellNum();
		 System.out.println(colcount);
		
		 if(ocell==null)
		 {
			 orow.createCell(2);
			 ocell= orow.getCell(2);
		 }
		 String temp = Result.get(i-1).toString();
		 System.out.println(temp);
		 ocell = orow.getCell(2);
		 ocell.setCellValue(temp);
		 oExcel.write(owrite);
				 			
		}
		
		owrite.close();
		oExcel.close();
		ofile.close();	
		
	}
			
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		browser_Invoke();
		navigate_url();
		//Search_product("Oneplus","Electronics");
		//Get_productlist();
		//clear_searchtext();
		Read_Excel();
		Close_browser();	

	}
}
	




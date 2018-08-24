package Transfer_Driver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Child extends Parent {
	
	public static void Read_Excel() throws Exception{
		
		InputStream ofile = new FileInputStream("C:\\Users\\p.b.prabhakaran\\workspace\\Selenium_Practice\\Datasheet\\Credo.xlsx");
		XSSFWorkbook oExcel = new XSSFWorkbook(ofile);
		XSSFSheet oSheet= oExcel.getSheet("Search_data");
		XSSFRow oRow = null;
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
			Thread.sleep(3000);
			clear_searchtext();
						
		}	
		oExcel.close();
		ofile.close();
	}
	
	public static void Write_Excel(List<String> Result) throws Exception{
		System.out.println("The Values of the ArrayList are " + Result);
		InputStream ofile = new FileInputStream("C:\\Users\\p.b.prabhakaran\\workspace\\Selenium_Practice\\Datasheet\\Credo.xlsx");
		XSSFWorkbook oExcel = new XSSFWorkbook(ofile);
		XSSFSheet oSheet = oExcel.getSheet("Search_data"); 
		XSSFRow orow = null;
		XSSFCell ocell = null;
		int rowcount = oSheet.getLastRowNum();
			
		for (int i=1 ; i<=rowcount ; i++){
		 orow = oSheet.getRow(i);
		 int colcount = orow.getLastCellNum();
		 System.out.println(colcount);
/*		 if(ocell==null)
		 {
			 orow.createCell(2);
			 ocell= orow.getCell(2);
		 }*/
		 String temp = Result.get(i-1).toString();
		 ocell = orow.createCell(2);
		 ocell.setCellValue(temp);
								 			
		}
		ofile.close();	
		OutputStream oFileWrite = new FileOutputStream("C:\\Users\\p.b.prabhakaran\\workspace\\Selenium_Practice\\Datasheet\\Credo.xlsx");
		oExcel.write(oFileWrite);
		oFileWrite.close();
				
	}
	
	public static void Close_browser(){
		driver.close();
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		browser_Invoke();
		navigate_url();
		//Search_product("Oneplus","Electronics");
		//Get_productlist();
		//clear_searchtext();
		Read_Excel();
		Write_Excel(Result);
		Close_browser();	
	}

}

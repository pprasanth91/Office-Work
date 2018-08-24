package Listener_Practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class MyTransformer implements IAnnotationTransformer {
	
	public static boolean runtest;
	public static String testname;
	public static String Run;
	public static int count=1;
	public static Map <String,String> oMap = new HashMap<String,String>();
	
public static void Read_Excel() throws Exception{
	
	FileInputStream ofile = new FileInputStream ("C:\\Users\\p.b.prabhakaran\\workspace\\Selenium_Practice\\Datasheet\\Credo.xlsx");	
	XSSFWorkbook oExcel = new XSSFWorkbook(ofile);	
	XSSFSheet oSheet = oExcel.getSheet("Tests");
	XSSFRow oRow = null;
	XSSFCell oCell = null;
	int Rowcount;
	int Colcount;
	Rowcount = oSheet.getLastRowNum();
	
	for (int i=1 ; i<=Rowcount ; i++){
	oRow = oSheet.getRow(i);	
	testname = oRow.getCell(1).toString();
	//System.out.println(testname);
	Run = oRow.getCell(2).toString();
	//System.out.println(Run);
	oMap.put(testname, Run);
	
	if (Run.contentEquals("Y")){
		
		switch (testname){
		
		case "Flipkart":
			runtest = true;
			System.out.println("Boolean set to Y");
			break;
			
		case "Amazon":
			runtest = true;
			System.out.println("Boolean set to Y");
			break;
			
		default:
			break;
				}
			
			}
	}
System.out.println(oMap);	
ofile.close();
oExcel.close();

	}

@Override
public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
	// TODO Auto-generated method stub
	// To Read Excel only one time
	if (count==1){ 
	try {
		Read_Excel();
		count++;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
	String tcasename = testMethod.getName();
	switch(tcasename){
	case "navigate_url" :
		annotation.setPriority(1);
		annotation.setEnabled(runtest);
		break;
	case "Read_Excel":
		annotation.setPriority(2);
		annotation.setEnabled(runtest);
		break;
	default:
		break;
			
		}
	}
}	




	
package FSO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class File_Ex {
	
	public static void main(String[] args) throws Exception {
		//dirCreation();
		//fileReaderWritter();
		//bufferReaderWritter();
		//printWritter();
		//inputStreamReader();
		//scanner();
	
	}
	
	public static void dirCreation() throws Exception {
		File oDir = new File("C:\\Users\\p.b.prabhakaran\\workspace\\Selenium_Practice\\Datasheet");
		if(oDir.exists()) {
			System.out.println("Folder Already Existing");
		}else {
			System.out.println("Folder Not Existing, Create It");
			oDir.mkdir();
		}
		
		File oFile = new File(oDir, "file.txt");
		if(oFile.exists()) {
			System.out.println("File is already Existing");
		}else {
			System.out.println("File is not Existing, So Creating a new File");
			oFile.createNewFile();
		}
	}
	
	public static void fileReaderWritter() throws Exception {
		File oDir = new File("C:\\Users\\p.b.prabhakaran\\workspace\\Selenium_Practice\\Datasheet");
		if(oDir.exists()) {
			System.out.println("Folder Already Existing");
		}else {
			System.out.println("Folder Not Existing, Create It");
			oDir.mkdir();
		} 
		
		File oFile = new File(oDir, "FileReaderWritter.txt");
		if(oFile.exists()) {
			System.out.println("File is already Existing");
		}else {
			System.out.println("File is not Existing, So Creating a new File");
			oFile.createNewFile();
		}
		
		FileWriter oWrite = new FileWriter(oFile, false);
		oWrite.write("This is Java Session \n.");
		oWrite.write("Followed By Selenium Session\n.");
		oWrite.write(65);
		oWrite.flush();
		oWrite.close();
	
		FileReader oRead = new FileReader(oFile);
		int iRead = oRead.read();
		System.out.println(iRead);
		while(iRead!=-1) {
			System.out.print((char)iRead);
			iRead = oRead.read();
		}
		oRead.close();
	}
	
	public static void bufferReaderWritter() throws Exception {
		File oDir = new File("C:\\Users\\p.b.prabhakaran\\workspace\\Selenium_Practice\\Datasheet");
		if(oDir.exists()) {
			System.out.println("Folder Already Existing");
		}else {
			System.out.println("Folder Not Existing, Create It");
			oDir.mkdir();
		} 
		
		File oFile = new File(oDir, "BufferReaderWritter.txt");
		if(oFile.exists()) {
			System.out.println("File is already Existing");
		}else {
			System.out.println("File is not Existing, So Creating a new File");
			oFile.createNewFile();
		}
		
		FileWriter oWrite = new FileWriter(oFile, true);
		BufferedWriter oBwrite = new BufferedWriter(oWrite);
		oBwrite.write("This is Java Session.");
		oBwrite.newLine();
		oBwrite.write("Followed By Selenium Session.");
		oBwrite.newLine();
		oBwrite.write(65);
		oBwrite.flush();
		oBwrite.close();
		
		FileReader oRead = new FileReader(oFile);
		BufferedReader oBread = new BufferedReader(oRead);
		String sLine = oBread.readLine();
		while(sLine!=null) {
			System.out.println(sLine);
			sLine = oBread.readLine();
		}
		oBread.close();
		oWrite.close();
	}
	
	public static void printWritter() throws Exception {
		File oDir = new File("C:\\Users\\p.b.prabhakaran\\workspace\\Selenium_Practice\\Datasheet");
		if(oDir.exists()) {
			System.out.println("Folder Already Existing");
		}else {
			System.out.println("Folder Not Existing, Create It");
			oDir.mkdir();
		} 
		
		File oFile = new File(oDir, "PrintWritter.txt");
		if(oFile.exists()) {
			System.out.println("File is already Existing");
		}else {
			System.out.println("File is not Existing, So Creating a new File");
			oFile.createNewFile();
		}
		
		FileWriter oWrite = new FileWriter(oFile, true);
		PrintWriter oPwrite = new PrintWriter(oWrite);
		oPwrite.println("This is a Java Session");
		oPwrite.println("Followed By Selenium Session");
		oPwrite.println(100);
		oPwrite.println(10.25);
		oPwrite.println(true);
		oPwrite.flush();
		oPwrite.close();
		FileReader oRead = new FileReader(oFile);
		BufferedReader oBread = new BufferedReader(oRead);
		String sLine = oBread.readLine();
		while(sLine!=null) {
			System.out.println(sLine);
			sLine = oBread.readLine();
		}
		oBread.close();
	}
	
	public static void inputStreamReader() throws Exception {
		InputStreamReader oRead = new InputStreamReader(System.in);
		BufferedReader oBread = new BufferedReader(oRead);
		System.out.println("Enter the Center Name : ");
		String sName = oBread.readLine();
		System.out.println("Center Name is : "+sName);
		System.out.println("Enter the Age of Center : ");
		int iAge = oBread.read();
		System.out.println("Center Age is : "+iAge);
		oBread.close();
	}
	
	public static void scanner() {
		Scanner oScan = new Scanner(System.in);
		System.out.println("Enter the Center Name : ");
		String sName = oScan.next();
		System.out.println("Center Name is : "+sName);
		System.out.println("Enter the Age of Center : ");
		int iAge = oScan.nextInt();
		System.out.println("Center Age is : "+iAge);
		oScan.close();
			
		}
	}
	
	



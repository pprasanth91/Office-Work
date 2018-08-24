package DataDriven;

import java.io.File;
import java.io.FileInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Read_Xml {
	
	public static void readxml(){
		File file = new File("C:\\Users\\p.b.prabhakaran\\workspace\\Selenium_Practice\\Datasheet\\dummy1.xml");
		try {
			FileInputStream ofile = new FileInputStream(file);
			DocumentBuilderFactory oFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder obuild = oFact.newDocumentBuilder();
			Document oDoc = obuild.parse(ofile);
			NodeList oNodelist = oDoc.getChildNodes();
			Node oNode = oNodelist.item(0);
			Element oElement = (Element) oNode;
			System.out.println("Name is : " + oElement.getElementsByTagName("body").item(0).getTextContent());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		readxml();

	}

}

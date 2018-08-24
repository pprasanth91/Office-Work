package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wrapper_Class {

public static WebDriver driver;

public Wrapper_Class (WebDriver driver){
	this.driver = driver;	
}

public static boolean button_click(By locator){
	WebElement btn;
	boolean btnpresence = false;
	btn = driver.findElement(locator);
	if (btn.isDisplayed()){
	btnpresence = true;	
	btn.click();
	
		}	
	return btnpresence;
	}

public static WebElement Verify_Elementpresence(By locator){
	WebElement btn;
	WebDriverWait owait = new WebDriverWait(driver,20);
	btn = driver.findElement(locator);
	owait.until(ExpectedConditions.elementToBeClickable(locator));
	return btn;
	}

}

package framework.tests;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.sun.jna.platform.win32.Sspi.TimeStamp;

import utilities.LogUtil;
/**
 * this simple java class
 *
 */
public class JavaSample {


	public static void main(String[] args) throws Exception {
		String expectedMessage  ="123#$";
		System.out.println(expectedMessage);
		if (expectedMessage == null || expectedMessage.trim().isEmpty()) {
	         System.out.println("Incorrect format of string");
	     }else{
	    	 Pattern p = Pattern.compile("[^A-Za-z0-9]");
	         Matcher m = p.matcher(expectedMessage);
	        // boolean b = m.matches();
	         boolean b = m.find();
	         System.out.println("Any special char:" +b);
	         
	         p = Pattern.compile("[0-9]");
	         m = p.matcher(expectedMessage);
	         
	         b = m.find();
	         System.out.println("Any num char:" +b);
	         
	         p = Pattern.compile("[A-Za-z]");
	         m = p.matcher(expectedMessage);
	         
	         b = m.find();
	         
	         System.out.println("Any alpha char:" +b);
	         
	         System.out.println("Any special char: " +expectedMessage.contains("[^A-Za-z0-9]"));
	         
	         
	         
	         String spcl = expectedMessage.replaceAll("[A-Za-z0-9]", "");
	         System.out.println(spcl.isEmpty());
	         
	         
	         spcl = expectedMessage.replaceAll("[^0-9]", "");
	         System.out.println(spcl);
	         
	         
	         
	     }
		
		
		
		
	}
}

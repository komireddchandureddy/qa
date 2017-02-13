package framework.tests;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		String expectedMessage  ="Midnight Delivery - FREE (on 07-Feb-2017)";
		System.out.println(expectedMessage);
		System.out.println(expectedMessage.indexOf(" - "));
		
		System.out.println(expectedMessage.indexOf("("));
		
		String s = expectedMessage.substring((expectedMessage.indexOf(" - ")+2), expectedMessage.indexOf("("));
		System.out.println(s.trim());
		s= s.replaceAll("[^0-9]", "");
		
		
		
		if(!s.isEmpty())
			{int val =Integer.parseInt(s.trim());
			System.out.println(val);
			}
		
		
		
		
	}
}

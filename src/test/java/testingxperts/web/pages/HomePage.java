package testingxperts.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.ConfigReader;
import utilities.KeywordUtil;

public class HomePage extends KeywordUtil {
	
	public enum GiftBy { FLOWER_AND_CAKE, GIFT_HAMPER,PERSONALIZED} 
	
	public static By linkFlower_and_cake= By.xpath("//p[text()='Flowers & Cakes']");
	public static By logo = By.xpath("//*[@id='absolute-header']/div[1]/div/a/img");
	public static By personalized_gifts=By.xpath("//div[contains(@class,'icon')]/p[text()='Personalized Gifts']");	
	
	public static String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public static void openHomePage() throws Exception {
		navigateToUrl(ConfigReader.getValue("BASE_URL"));

	}

	public static boolean isHomePageOpened() throws Exception {
		return getDriver().
				getCurrentUrl().
				equalsIgnoreCase(ConfigReader.getValue("BASE_URL"));
	}
	
	public static void clickLogo() throws Exception{
		executeStep(click(logo), "Click Logo");
		pause(1000);
		
	}
	
	public static boolean selectItemEditorPick(int index){
		//First index is 5th So adding 4+index
		String xpathOfItem = String.format("(//*[@id='edp-default']//img)[position()=%d]", 4+index);
		 By item=By.xpath(xpathOfItem);
		 return click(item);
	}

	public static boolean sendgiftsWorldwide() throws InterruptedException
	{
		if(isWebElementPresent(By.xpath("//img[@alt='CCS Banner']")))
		{
			return clickAndWait(By.xpath("//img[@alt='CCS Banner']"));
		}
		else
			return false;
	}
	
	public static boolean personalisedGifts() throws Exception
	{
		if(isWebElementPresent(personalized_gifts))
		{
			verifyStep(clickAndWait(personalized_gifts),"Click on Personalized gifts");
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean selectFlowersnCakes()
	{
		return click(linkFlower_and_cake);
	}
	
	public static void selectNextButton() throws Exception
	{
		executeStep(click(By.xpath("//div[@id='edp-default']/button[text()='Next']")),"Click on Next button");
		
	}

}
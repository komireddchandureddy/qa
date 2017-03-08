package testingxperts.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;



public class ProductDetailPage extends HomePage {
	public static By txtPinCode = By.xpath("//*[@id='pincode']");
	public static By btnpinCheck=By.id("pinCheck");

	public static boolean clickShoppingwithIndia()
	{
		return click(By.linkText("Shipping within India"));
	}

	public static boolean verifypincode(String pincode) throws Exception
	{
		writeInInputCharByChar(txtPinCode, pincode);
		click(btnpinCheck);
		if(pincode.length()>6 ||!pincode.contains("^[0-9_]"))
		{
			return isWebElementPresent(By.xpath("//input[contains(@class,'pincode error')]"));
		}

		else 
		{
			return isWebElementVisible(txtPinCode);
		}

	}

	public static boolean verifydeliverylocation(String pincode) throws InterruptedException
	{
		writeInInputCharByChar(txtPinCode, pincode);
		clickAndWait(btnpinCheck);
		if(isWebElementPresent(By.xpath("//p[@id='pin-error-text'][@class='text-dblack']")))
			return true;
		else
			return false;
	}

	public static boolean clickinternationalshipping() throws InterruptedException
	{
		if(isWebElementPresent(By.xpath("//li[contains(@class,'active')][@data-target='#international-del']")))
		{
			return true;
		}
		else if(isWebElementPresent(By.xpath("//li[contains(@class,'active')][@data-target='#international-del']")))
		{
			clickAndWait(By.linkText("International Shipping"));
			return true;
		}
		else
			return false;
	}

	public static boolean enterInvalidcountry(String country) throws Exception
	{
		doubleClick(By.id("country"));
		executeStep(writeInInputCharByChar(By.id("country"), country),"Input country");
		pause(10000);
		clickAndWait(By.id("countryCheck"));
		pause(3000);
		return isWebElementPresent(By.xpath("//div[text()='Sorry no results']"));	

	}

	public static boolean verifyimageangles() throws InterruptedException
	{
		if(isWebElementPresent(By.xpath("//div[contains(@class,'thumbnail-img')][position()=1]")))
		{
			for(int i=1;i<=3;i++)
			{
				clickAndWait(By.xpath("//div[contains(@class,'thumbnail-img')][position()="+i+"]"));
				isWebElementVisible(By.xpath("//div[@class='intrinsic intrinsic-square']/img[position()="+i+"]"));
				pause(2000);
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean zoomOnImage() throws InterruptedException
	{
         WebElement Image = getDriver().findElement(By.xpath("//div[@class='zoomContainer']"));
        
        //Get width of element.
        int ImageWidth = Image.getSize().getWidth();
        System.out.println("Image width Is "+ImageWidth+" pixels");
        
        //Get height of element.
        int ImageHeight = Image.getSize().getHeight();        
        System.out.println("Image height Is "+ImageHeight+" pixels");
        
        Thread.sleep(5000);
		
        Actions clicker = new Actions(getDriver());

        clicker.moveToElement(Image).moveByOffset(ImageWidth/2, ImageHeight/2).click().perform();
  
        Thread.sleep(2000);
		return verifyDisplayAndEnable(By.xpath("//div[@class='zoomWindow']"));	
	}

	public static boolean verifyNamendPrice(int index)
	{
		
		String xpathOfItem = String.format("//div[contains(@class,'edp-group-wrapper')][position()=3]//div[@class='slick-track']/div[position()=%d]", 4+index);
		String Name=String.format("//div[contains(@class,'edp-group-wrapper')][position()=3]//div[@class='slick-track']/div[position()=%d]//p[@class='product-name']/a",4+index);
		String price=String.format("//div[contains(@class,'edp-group-wrapper')][position()=3]//div[@class='slick-track']/div[position()=%d]//p[@class='product-price']/span",4+index);
		By item=By.xpath(xpathOfItem);
		String productName= getElementText(By.xpath(Name));
		String productprice=getElementText(By.xpath(price));
		click(item);
		String itemName=getElementText(By.xpath("//h1[@class='pdp-product-name']"));
		String itemPrice=getElementText(By.xpath("//span[contains(@class,'product-price')]"));
		if(itemName.contentEquals(productName) && itemPrice.contentEquals(productprice))
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	
	public static boolean verifySizeoption()
	{
		return isWebElementVisible(By.xpath("//div[@class='customize-attr size-select']"));
		
	}

	public static boolean needHelpOption(String reason,String query) throws Exception
	{
		if(isWebElementVisible(By.linkText("Need Help ?")))
		{
			executeStep(click(By.linkText("Need Help ?")),"Click on Need Help column");
			selectByVisibleText(By.id("other_query5"), reason);
			writeInInputCharByChar(By.id("contact-umsg"), query);
			clickAndWait(By.id("send-btn"));
			verifyStep(isWebElementVisible(By.xpath("//div[text()='Thanks for the support. Your message have been sent. You will be contacted soon by our customer service.']")), "Query message received");
			return true;
		}
		else
			return false;
	}
	
}

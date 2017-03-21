package testingxperts.web.pages;

import org.openqa.selenium.By;

public class ProductList extends HomePage{

	public static By flowersdisplayed=By.xpath("//span[text()='Flowers']");
	public static boolean selectItem()
	{
		if(isWebElementPresent(By.xpath("(//div[@class='slick-track']/div[contains(@class,'product-grid-item col s3')])[position()=5]")))
		{
			click(By.xpath("(//div[@class='slick-track']/div[contains(@class,'product-grid-item col s3')])[position()=5]"));
			return true;
		}
		
		else
		{
			return false;
		}
		
	}
	
	public static boolean isFlowersnCakesPageloaded()
	{
		return isWebElementVisible(flowersdisplayed);
	}
}

package testingxperts.web.pages;

import org.openqa.selenium.By;

public class ProductList extends HomePage{

	public static boolean selectItem()
	{
		if(isWebElementPresent(By.xpath("//li[@class='product-grid-item col s3'][position()=1]")))
		{
			click(By.xpath("//li[@class='product-grid-item col s3'][position()=1]"));
			return true;
		}
		
		else
		{
			return false;
		}
		
	}
}

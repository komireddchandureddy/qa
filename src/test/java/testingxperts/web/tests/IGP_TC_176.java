package testingxperts.web.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listeners.CustomListeners;
import listeners.ExecutionStartEndListner;
import testingxperts.web.pages.CartPage;
import testingxperts.web.pages.CheckOutPage;
import testingxperts.web.pages.Constants;
import testingxperts.web.pages.DeliveryPage;
import testingxperts.web.pages.HomePage;
import testingxperts.web.pages.OrderSummaryPage;
import utilities.ConfigReader;
import utilities.GlobalUtil;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

@Listeners({CustomListeners.class,ExecutionStartEndListner.class})
public class IGP_TC_176 extends KeywordUtil{
	String stepInfo="";
	int retryCount=getIntValue("retryCount");
	static int retryingNumber=1;
	
	@Test(
			testName="IGP_TC_176",
			groups={"Checkout Page"}, 
			description="Number of Items to Check out : Ensure  that In Order Summary page it has to display ‘count of products  separately for Same Day Delivery  and more than one day delivery’. "
			)
	public void test() throws Throwable {
		try{
			setTestCaseID(getClass().getSimpleName());
			//======================BASIC SETTING FOR TEST==========================================================
			if(retryingNumber==1)
				initTest();
			//================== END BASIC SETTING ============================================================
			/*
				How to Test steps
			 		1. Define step info
			 		2. Log to report and Logger
			 		3. Perform Action
			 		4. Verify Action
			 		
			 		==================================
			 		Login Steps
			 		==================================
			 			stepInfo="Login to application";
						logStep(stepInfo);
						LoginPage.doLogin(ConfigReader.getValue("loginUser"), ConfigReader.getValue("loginPassword"));
						verifyStep(LoginPage.isLogin(), stepInfo);
					==================================
			 		Logout Steps
			 		==================================
		 				stepInfo="Logout from application";
		 				logStep(stepInfo);
		 				LoginPage.logOut();
		 				verifyStep(LoginPage.isLogout(), stepInfo);
		 	
			*/
			
		
			//.........Script Start...........................
			
			stepInfo="Open home page";
			logStep(stepInfo);
			HomePage.openHomePage();
			verifyStep(HomePage.isHomePageOpened(), stepInfo);
			
			stepInfo="Add product into cart";
			logStep(stepInfo);
			
			logStep("Select product for given day delivery");
			CartPage.addItemInCart(HomePage.GiftBy.FLOWER_AND_CAKE);
			
			
			//Select Second Product
			logStep("Select product for same day delivery");
			executeStep(click(CartPage.linkSameDayDelivery),"Click same day delivery");
			executeStep(CartPage.selectItem(2),"Select Item");
			CartPage.selectMidnightDeliveryWithDate();
			executeStep(click(CartPage.btnAddToCart), 
					"Click: "+"ADD TO CART");
					
			
			verifyStep(CartPage.isItemAdded(), stepInfo);
			CartPage.closeCartOverlay();
			
			stepInfo="Buy Now";
			logStep(stepInfo);
			executeStep(CartPage.clikBuyNow(), stepInfo);
			
			stepInfo="The page should navigate to cart page";
			verifyStep(CartPage.verifyOrderDetailsPageLoaded(),stepInfo);
			
			stepInfo="Place order";
			logStep(stepInfo);
			CartPage.clickPlaceOrder();
			pause(2000);
			
			stepInfo="The user should be navigated to checkout page.";
			verifyStep(CheckOutPage.isCheckOutPageLoaded(),
					stepInfo);
			
			stepInfo="Login at checkout page";
			logStep(stepInfo);
			CheckOutPage.doLogin(ConfigReader.getValue("loginUser"), ConfigReader.getValue("loginPassword"));
			verifyStep(DeliveryPage.verifyDeliveryPageLoaded(),
					stepInfo);
			
			
			String name=DeliveryPage.getName();
			String address = DeliveryPage.getAddress();
			String pin =DeliveryPage.getCityAndPin();
			String numberOnly= pin.replaceAll("[^0-9]", "");
			
			
			stepInfo="Click Deliver here";
			executeStep(click(DeliveryPage.btnDeliverHere), stepInfo);
			
			stepInfo="Verify user navigated to Order Summary page";
			logStep(stepInfo);
			verifyStep(OrderSummaryPage.isOrderSummaryPageLoaded(),stepInfo);
						
			
			stepInfo="Check the number of items for same day and other day delivery products.";
			logStep(stepInfo);
			
			String addressLine = OrderSummaryPage.getAddressLine();
			String totalItems =addressLine.substring(0,addressLine.indexOf("("));
			totalItems =totalItems.replaceAll("[^0-9]", "");
			int items = OrderSummaryPage.getTotalItems();
			
			logStep("|Expected Items: " + totalItems +"| |Actual Items: " +items+"|");
			verifyStep(Integer.toString(items).equalsIgnoreCase(totalItems),"Verify Total item in list");
			
			
			//Verify Items has different Delivery dates
			
			//Get First Item Delivery date
			String firstItemDate = OrderSummaryPage.getDeliveryDateForItem(1);
			
			//Get second Item Delivery date
			String secondItemDate = OrderSummaryPage.getDeliveryDateForItem(2);
			
			//Verify both are not similar
			logStep(String.format("|First itme delivery date: %s| |Second itme delivery date: %s|", firstItemDate,secondItemDate));
			
			verifyStep(!firstItemDate.equalsIgnoreCase(secondItemDate), stepInfo);
			
			
			String elementSShot=takeScreenshotWebElement(waitForVisibile(By.xpath("//*[@class='order-item-row']/..")),"ProductsInCart");
			HtmlReportUtil.attachScreenshotForInfo(elementSShot);
			
			getDriver().navigate().back();
		    getDriver().navigate().back();
			 
		
			//.........Script Start...........................
		}
		  catch (Exception e){
			   if(retryCount>0)
			   {
				   String imagePath = takeScreenshot(getDriver(), getTestCaseID()+"_"+ retryingNumber);

				   logStepFail(stepInfo+" - "+KeywordUtil.lastAction);
				   logStepError(e.getMessage());
				   HtmlReportUtil.attachScreenshot(imagePath,false);
			    
				   GlobalUtil.getTestResult().setScreenshotref(imagePath);
			    
				   HtmlReportUtil.stepInfo("Trying to Rerun" + " "+getTestCaseID() +" for " + retryingNumber + " time");
				   retryCount--;
				   retryingNumber++;
				   utilities.LogUtil.infoLog(getClass(), "****************Waiting for " + getIntValue("retryDelayTime") +" Secs before retrying.***********");
				   delay(getIntValue("retryDelayTime"));
			    //Rerun same test
				   test();
			   }
			   else{
				   String imagePath = takeScreenshot(getDriver(), getTestCaseID());
				   logStepFail(stepInfo+" - "+KeywordUtil.lastAction);
				   logStepError(e.getMessage());
				   HtmlReportUtil.attachScreenshot(imagePath,false);
			    
				   GlobalUtil.getTestResult().setScreenshotref(imagePath);
				   GlobalUtil.setTestException(e);
				   throw e;
			   }
		  }
}//End Test
	
	 
	
	
	
}

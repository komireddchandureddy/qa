package testingxperts.web.pages;

import org.openqa.selenium.By;

public class PaymentPage extends HomePage {
	public enum PaymentOptions {
		CREDIT_CARD, DEBIT_CARD, NET_BANKING, WALLETS, PAYPAL
	}

	public static By listPaymentMethod = By.xpath("//ul[@class='payment-method-list']");
	public static By txtTotalAmountPaybleLibe = By
			.xpath("//div[@class='payment-header']/span[contains(.,'Total amount payable')]");

	// Credit card form fields
	public static By inputCreditCardNumber = By.xpath("//*[@id='c-card-form']//input[@name='ccnum']");
	public static By inputCreditCardCCV = By.xpath("//*[@id='c-card-form']//input[@name='ccvv']");
	public static By inputCreditCardNameOnCard = By
			.xpath("//*[@id='c-card-form']//input[@ placeholder='Name on card']");
	public static By ddCreditCardMonth = By.xpath("//*[@id='c-card-form']//select[@name='ccexpmon']");
	public static By ddCreditCardYear = By.xpath("//*[@id='c-card-form']//select[@name='ccexpyr']");

	// Debit card form fields
	public static By ddDebitCardType = By.xpath("//*[@id='d-card-form']//select[@name='bankcode']");
	public static By inputDebitCardNumber = By.xpath("//*[@id='d-card-form']//input[@name='ccnum']");
	public static By inputDebitCardCCV = By.xpath("//*[@id='d-card-form']//input[@name='ccvv']");
	public static By inputDebitCardNameOnCard = By.xpath("//*[@id='d-card-form']//input[@ placeholder='Name on card']");
	public static By ddDebitCardMonth = By.xpath("//*[@id='d-card-form']//select[@name='ccexpmon']");
	public static By ddDebitCardYear = By.xpath("//*[@id='d-card-form']//select[@name='ccexpyr']");

	public static boolean isPaymentPageLoaded() {
		return isWebElementPresent(listPaymentMethod);
	}

	public static boolean totalAmountPayableIsDisplayed() {
		return isWebElementVisible(txtTotalAmountPaybleLibe);
	}

	public static String getTotalAmountPayableLine() {
		return getElementText(txtTotalAmountPaybleLibe);
	}

	public static void clickPaymentOption(PaymentOptions paymentOpt) throws Exception {

		switch (paymentOpt) {
		case CREDIT_CARD:

			executeStep(click(By.xpath("//li[@data-target='credit-card']")), "Click Debit card");
			break;
		case DEBIT_CARD:
			executeStep(click(By.xpath("//li[@data-target='debit-card']")), "Click Debit card");
			break;
		case NET_BANKING:
			executeStep(click(By.xpath("//li[@data-target='net-banking']")), "Click Net Banking");
			break;
		case WALLETS:
			executeStep(click(By.xpath("//li[@data-target='wallets']")), "Click Wallets");
			break;
		case PAYPAL:
			executeStep(click(By.xpath("//li[@data-target='paypal']")), "Click Paypal");
			break;
		}
	}

	public static boolean verifyPaymentOptionIsDisplayed(PaymentOptions paymentOpt) {
		String option = "";
		switch (paymentOpt) {
		case CREDIT_CARD:
			option = "Credit Card";
			break;
		case DEBIT_CARD:
			option = "Debit Card";
			break;
		case NET_BANKING:
			option = "Net Banking";
			break;
		case WALLETS:
			option = "Wallets";
			break;
		case PAYPAL:
			option = " Paypal";
			break;
		}
		String xpath = String.format(
				"//div[contains(@class,'payment-method-wrapper')]/ul/li[contains(.,'%s')][contains(@class,'active')]",
				option);
		return isWebElementPresent(By.xpath(xpath));
	}

	public static void verifyCreditCardInfoForm() throws Exception {
		clickPaymentOption(PaymentOptions.CREDIT_CARD);
		pause(1000);
	}

	public static void inputCreditCardNumber(String number) throws Exception {
		executeStep(inputText(By.xpath("//*[@id='credit-card']//input[@name='ccnum']"), number),
				"Input credit card number: " + number);
	}

	public static boolean verifyCardTypeIsMasterCard() throws Exception {
		return isWebElementPresent(By.xpath("//*[@id='credit-card']//i[contains(@class,'mastercard')]"));
	}

}

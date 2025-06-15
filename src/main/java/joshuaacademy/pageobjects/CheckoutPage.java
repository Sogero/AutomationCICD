package joshuaacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import joshuaacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".user__address input")
	WebElement userAddress;
	@FindBy(css = ".ta-results button")
	List<WebElement> suggestResults;
	@FindBy(css = ".action__submit")
	WebElement placeOrderBtn;
	
	By suggestResultsBy = By.cssSelector(".ta-results button");

	public void checkoutDetails(String initialCountry, String country) {
		userAddress.sendKeys(initialCountry);
		waitForListElementToAppear(suggestResultsBy);
		suggestResults.stream().filter(suggestResult -> suggestResult.getText().equalsIgnoreCase(country)).findFirst()
				.ifPresent(result -> result.click());
	}

	
	public ConfirmationPage submitOrder() {
		placeOrderBtn.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
}

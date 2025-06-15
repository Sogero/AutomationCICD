package joshuaacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import joshuaacademy.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{

	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css = ".hero-primary")
	WebElement successMessage;
	@FindBy(xpath = "//tr[@class='ng-star-inserted'] //label")
	WebElement orderNumber;

	public String verifySuccessScreen() {
		waitForElementToAppear(successMessage);
		String message = successMessage.getText();
		return message;
	}

	public String getOrderNumber() {
		String[] raw = orderNumber.getText().split(" ");
		String orderNumber = raw[1];
		return orderNumber;
	}

}

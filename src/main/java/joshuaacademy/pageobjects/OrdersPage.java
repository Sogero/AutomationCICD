package joshuaacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import joshuaacademy.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{
	
	WebDriver driver; 

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//tr[@class='ng-star-inserted']/th") List<WebElement> ordersID;
	
	public Boolean verifyOrderDisplay(String orderNumber) {
		waitForListWebElementToAppear(ordersID);
		Boolean match = ordersID.stream().anyMatch(orderNum -> orderNum.getText().equalsIgnoreCase(orderNumber));
		return match;
	}
}

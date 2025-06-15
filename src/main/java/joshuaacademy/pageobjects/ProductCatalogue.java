package joshuaacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import joshuaacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// PageFactory
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	@FindBy(id = ".ng-animating")
	WebElement spinner;
	@FindBy(css = ".user__address input")
	WebElement userAddress;
	@FindBy(css = ".ta-results button")
	List<WebElement> suggestResults;
	@FindBy(css = ".action__submit ")
	WebElement placeOrderBtn;
	@FindBy(id = "toast-container")
	WebElement toastMessageDisappear;

	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.id("toast-container");
	By cartProducts = By.cssSelector(".cartSection h3");
	By addressResult = By.cssSelector(".list-group-item");
	By orderNumber = By.xpath("//tr[@class='ng-star-inserted'] //label");
	By cartIcon = By.cssSelector("[routerlink*='cart']");
	By successMessage = By.cssSelector(".hero-primary");

	String initialCountry = "ind";

	public List<WebElement> getProductList() {

		waitForListElementToAppear(productBy);
		return products;

	}

	public WebElement getProductByName(String productName) {

		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) {

		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForListElementToAppear(toastMessage);
		waitForElementToDisappear(toastMessageDisappear);

	}

}

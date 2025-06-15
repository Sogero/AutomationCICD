package joshuaacademy.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import joshuaacademy.TestComponents.BaseTest;
import joshuaacademy.pageobjects.CartPage;
import joshuaacademy.pageobjects.CheckoutPage;
import joshuaacademy.pageobjects.ConfirmationPage;
import joshuaacademy.pageobjects.LandingPage;
import joshuaacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImp extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {

		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {

		productCatalogue = landingPage.loginApplication(username, password);

	}

	@When("^When I add the product (.+) from cart$")
	public void When_I_add_the_product_from_cart(String product) {

		productCatalogue.addProductToCart(product);
		cartPage = productCatalogue.goToCartPage();
	}

	@And("^Checkout (.+) and submit the order$")
	public void Checkout_product_and_submit_the_order(String product) {
		Boolean match = cartPage.viewAddedProduct(product);
		Assert.assertTrue(match);
		checkoutPage = cartPage.goToCheckout();
		confirmationPage = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void Thankyou_message_is_displayed_on_ConfirmationPage(String expectedMsg) {
		String message = confirmationPage.verifySuccessScreen();
		Assert.assertTrue(message.equalsIgnoreCase(expectedMsg));

	}

}

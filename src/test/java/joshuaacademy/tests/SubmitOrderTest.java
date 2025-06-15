package joshuaacademy.tests;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import joshuaacademy.TestComponents.BaseTest;
import joshuaacademy.pageobjects.CartPage;
import joshuaacademy.pageobjects.CheckoutPage;
import joshuaacademy.pageobjects.ConfirmationPage;
import joshuaacademy.pageobjects.OrdersPage;
import joshuaacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String orderNumber;

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException {
		// TODO Auto-generated method stub

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.viewAddedProduct(input.get("productName"));
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.checkoutDetails(input.get("initialCountry"), input.get("country"));

		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String message = confirmationPage.verifySuccessScreen();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		orderNumber = confirmationPage.getOrderNumber();
		System.out.println(orderNumber);

	}

	@Test(dependsOnMethods = { "submitOrder" }, groups = {"Purchase"}, dataProvider ="getData")
	public void OrderHistoryTest(HashMap<String, String> input) {
 
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(orderNumber));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJasonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\joshuaacadamy\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) } };
	}
	
	
}

package joshuaacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import joshuaacademy.TestComponents.BaseTest;
import joshuaacademy.TestComponents.Retry;
import joshuaacademy.pageobjects.CartPage;
import joshuaacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling", "Purchase"}, dataProvider="getData", retryAnalyzer=Retry.class)
	public void loginErrorValidation(HashMap<String, String> input) throws IOException {
		
		landingPage.loginApplication(input.get("invalidEmail"), input.get("invalidPass"));
		Assert.assertEquals(landingPage.getErrorMessage(), input.get("errorMessage"));
	}
	
	@Test
	public void productErrorValidation() throws IOException {
		// TODO Auto-generated method stub
		String productName = "Zara Coat 3";
	
		ProductCatalogue productCatalogue = landingPage.loginApplication("automationtest1@ggmail.com",
				"AutomationTest@24");
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.viewAddedProduct("baby");
		Assert.assertFalse(match);

	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJasonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\joshuaacademy\\data\\ErrorLoginValidation.json");
		return new Object[][] { {data.get(0)} };
		
	}

}

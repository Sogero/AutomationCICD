package joshuaacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import joshuaacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage = new LandingPage(driver);
		String productName = "Zara Coat 3";
		String country = "india";

		driver.findElement(By.id("userEmail")).sendKeys("automationtest@mail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Automationtest24");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		//List<WebElement> addProducts = driver.findElements(By.cssSelector(".w-10"));
		//List<WebElement> productNames = driver.findElements(By.tagName("h5"));

		WebElement prod = products.stream().filter(product -> 
		product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
		List <WebElement> cartProduts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartProduts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		driver.findElement(By.cssSelector(".user__address input")).sendKeys("ind");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group-item")));
		
		List<WebElement> suggestResults = driver.findElements(By.cssSelector(".ta-results button"));
		
		suggestResults.stream().filter(suggestResult -> suggestResult.getText().equalsIgnoreCase(country)).findFirst().ifPresent(result -> result.click());
		
		driver.findElement(By.cssSelector(".action__submit ")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@class='ng-star-inserted'] //label")));
		String[] raw = driver.findElement(By.xpath("//tr[@class='ng-star-inserted'] //label")).getText().split(" ");
		String orderNumber = raw[1];
		
		System.out.println(orderNumber);
		
		/*driver.findElement(By.cssSelector(".user__address input")).sendKeys(Keys.DOWN);
		driver.findElement(By.cssSelector(".user__address input")).sendKeys(Keys.ENTER);*/
		
		/*int p = 0;
		for (int i = 0; i < productNames.size(); i++) {

			String productName = productNames.get(i).getText();

			if (productName.equalsIgnoreCase(item)) {

				addProducts.get(i).click();
				break;

			}

		}*/

		//driver.quit();

	}

}

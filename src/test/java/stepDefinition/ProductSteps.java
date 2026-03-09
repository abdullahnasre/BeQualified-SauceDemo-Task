package stepDefinition;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.ProductPage;
import pages.LoginPage;
import drivers.DriverHolder;
import util.Utility;

public class ProductSteps {
    // Instanzen der Page Objects
    LoginPage loginPage = new LoginPage(DriverHolder.getDriver());
    ProductPage productPage = new ProductPage(DriverHolder.getDriver());

    @Given("user is logged in with {string} and {string}")
    public void userIsLoggedInWithAnd(String username, String password) {
        // 1. URL laden (Nutze deine Utility-Klasse)
        String url = Utility.getProperty("url");
        DriverHolder.getDriver().get(url);

        // 2. Log-ins ausführen mit den Parametern aus der Feature-Datei
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @When("user adds {string} to the cart")
    public void user_adds_to_the_cart(String productName) {
        productPage.addProductToCart(productName);
    }

    @Then("the shopping cart badge should show {string}")
    public void the_shopping_cart_badge_should_show(String expectedCount) {
        Assert.assertEquals(productPage.getCartBadgeCount(), expectedCount);
    }
}
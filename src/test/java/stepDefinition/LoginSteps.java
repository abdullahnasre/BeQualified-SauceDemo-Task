package stepDefinition;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import drivers.DriverHolder;
import org.testng.Assert;

import java.time.Duration;

public class LoginSteps {

    WebDriver driver = DriverHolder.getDriver();
    LoginPage loginPage = new LoginPage(driver);

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        // 1. Die URL aufrufen
        DriverHolder.getDriver().get("https://www.saucedemo.com/");

        // 2. EXPLICIT WAIT: Warte bis zu 10 Sekunden, bis der Titel "Swag Labs" erscheint
        WebDriverWait wait = new WebDriverWait(DriverHolder.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("Swag Labs"));

        // 3. Jetzt erst die Assertion prüfen (jetzt wird sie erfolgreich sein!)
        String actualTitle = DriverHolder.getDriver().getTitle();
        Assert.assertEquals(actualTitle, "Swag Labs");
    }

    @When("user enters username {string}")
    public void user_enters_username(String username) {
        loginPage.enterUsername(username);
    }

    @When("user enters password {string}")
    public void user_enters_password(String password) {
        loginPage.enterPassword(password);
    }

    @When("user clicks the login button")
    public void user_clicks_the_login_button() {
        loginPage.clickLogin();
    }

    @Then("user should be redirected to inventory page")
    public void user_should_be_redirected_to_inventory_page() {
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "User is not on Inventory page!");
    }
}
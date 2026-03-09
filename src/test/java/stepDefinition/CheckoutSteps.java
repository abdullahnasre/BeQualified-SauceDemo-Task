package stepDefinition;

import io.cucumber.java.en.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.Checkout;
import drivers.DriverHolder;
import org.openqa.selenium.By;

import java.time.Duration;

public class CheckoutSteps {
    // Initialisierung der Page mit dem aktuellen Driver
    Checkout checkout = new Checkout(DriverHolder.getDriver());

    @When("user proceeds to checkout and enters {string}, {string} and {string}")
    public void user_proceeds_to_checkout_and_enters(String first, String last, String zip) {
        // Navigation zum Checkout
        // TODO: Navigation in eine eigene Methode in der CartPage auslagern
        WebDriverWait wait = new WebDriverWait(DriverHolder.getDriver(), Duration.ofSeconds(30));

        // 1. Klick auf den Warenkorb (mit Wait)
        wait.until(ExpectedConditions.elementToBeClickable(By.className("shopping_cart_link"))).click();

        // 2. Klick auf 'Check' (HIER lag der Fehler!)
        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();

        // 3. Details eingeben
        checkout.enterDetails(first, last, zip);
    }

    @Then("the subtotal should match the product price")
    public void verifySubtotal() {
        // TODO: Den erwarteten Preis dynamisch aus der ProductPage laden
        double actualSubtotal = checkout.getSubtotal();
        double expectedPrice = 29.99;

        Assert.assertEquals(actualSubtotal, expectedPrice, "Subtotal auf der Übersichtseite stimmt nicht überein!");
    }

    @And("user finishes the purchase")
    public void user_finishes_the_purchase() {
        // Finaler Klick auf 'Finish'
        checkout.finishCheckout();
    }

    @Then("the order should be confirmed with {string}")
    public void the_order_should_be_confirmed_with(String expectedMessage) {
        String actualMessage = checkout.getConfirmationMessage();
        // TODO: Screenshot aufnehmen, falls die Bestätigung nicht erscheint
        Assert.assertEquals(actualMessage, expectedMessage, "Bestätigungsnachricht weicht ab!");
    }
}
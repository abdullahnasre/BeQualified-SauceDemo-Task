package stepDefinition;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.Checkout;
import drivers.DriverHolder;

public class CheckoutSteps {
    // Initialisierung der Page mit dem aktuellen Driver
    Checkout checkout = new Checkout(DriverHolder.getDriver());

    @When("user proceeds to checkout and enters {string}, {string} and {string}")
    public void user_proceeds_to_checkout_and_enters(String first, String last, String zip) {
        // 1. Nutze die neue Methode aus der Page-Klasse (inklusive WebDriverWait)
        checkout.navigateToCheckoutForm();

        // 2. Details eingeben (CheckoutStepOne -> CheckoutStepTwo)
        checkout.enterDetails(first, last, zip);
    }

    @Then("the subtotal should match the product price")
    public void verifySubtotal() {
        double actualSubtotal = checkout.getSubtotal();
        double expectedPrice = 32.39;

        Assert.assertEquals(actualSubtotal, expectedPrice, "Subtotal auf der Übersichtseite stimmt nicht überein!");
    }

    @And("user finishes the purchase")
    public void user_finishes_the_purchase() {
        checkout.finishCheckout();
    }

    @Then("the order should be confirmed with {string}")
    public void the_order_should_be_confirmed_with(String expectedMessage) {
        String actualMessage = checkout.getConfirmationMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Bestätigungsnachricht weicht ab!");
    }
}
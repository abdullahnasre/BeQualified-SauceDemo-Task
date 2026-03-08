package stepDefinition;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.Checkout;
import drivers.DriverHolder;
import org.openqa.selenium.By;

public class CheckoutSteps {
    // Initialisierung der Page mit dem aktuellen Driver
    Checkout checkout = new Checkout(DriverHolder.getDriver());

    @When("user proceeds to checkout and enters {string}, {string} and {string}")
    public void user_proceeds_to_checkout_and_enters(String first, String last, String zip) {
        // Navigation zum Checkout
        // TODO: Navigation in eine eigene Methode in der CartPage auslagern
        DriverHolder.getDriver().findElement(By.className("shopping_cart_link")).click();
        DriverHolder.getDriver().findElement(By.id("checkout")).click();

        // Details eingeben (CheckoutStepOne -> CheckoutStepTwo)
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
@RegressionScenario
Feature: Checkout functionality

  @RegressionTest
  Scenario: Complete a purchase successfully
    Given user is logged in with "standard_user" and "secret_sauce"
    And user adds "Sauce Labs Backpack" to the cart
    When user proceeds to checkout and enters "Abdullah", "Kamel" and "12345"
    Then the subtotal should match the product price
    And user finishes the purchase
    Then the order should be confirmed with "Thank you for your order!"
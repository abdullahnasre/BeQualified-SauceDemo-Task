@RegressionScenario
Feature: Product Workflow functionality

  @RegressionTest
  Scenario Outline: Add specific products to cart
    Given user is logged in with "standard_user" and "secret_sauce"
    When user adds "<product>" to the cart
    Then the shopping cart badge should show "1"

    Examples:
      | product                  |
      | Sauce Labs Backpack      |
      | Sauce Labs Bike Light    |
      | Sauce Labs Bolt T-Shirt  |
@RegressionScenario
Feature: Product Sort functionality

  @RegressionTest
  Scenario Outline: Sort products by different criteria
    Given user is logged in with "standard_user" and "secret_sauce"
    When user selects sort option "<sortOption>"
    Then products should be sorted correctly by "<sortOption>"

    Examples:
      | sortOption          |
      | Price (low to high) |
      | Price (high to low) |
      | Name (Z to A)       |
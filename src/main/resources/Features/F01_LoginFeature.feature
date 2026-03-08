@RegressionScenario
Feature: Login functionality

  @RegressionTest
  Scenario: Successful login

    Given user is on the login page
    When user enters username "standard_user"
    And user enters password "secret_sauce"
    And user clicks the login button
    Then user should be redirected to inventory page
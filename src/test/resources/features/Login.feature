Feature: Login

  Background:
    Given User is on the login page

  @smoke @regression
  Scenario: Successful login with valid credentials
    When User enters the registered email and password
    And  User clicks on Login button
    Then User should land on My Account page

  @regression
  Scenario Outline: Failed login with <email> email and <password> password
    When User enters email "<email>" and password "<password>"
    And  User clicks on Login button
    Then User should see the warning "Warning: No match for E-Mail Address and/or Password."

    Examples:
      | email  | password |
      | random | random   |
      | random | empty    |
      | empty  | random   |
      | empty  | empty    |
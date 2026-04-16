Feature: Sort Products by Price and Checkout
  As a user of SauceDemo
  I want to sort products from cheapest to most expensive
  And complete the checkout process

  Background:
    Given the user is on the login page
    When the user enters valid username and password
    And clicks the login button
    Then the user should be redirected to the inventory page

  Scenario: Sort products by price low to high
    When the user sorts products by price low to high
    Then the products should be sorted from cheapest to most expensive

  Scenario: Successfully checkout the cheapest product
    When the user sorts products by price low to high
    And the user adds the cheapest product to cart
    And the user navigates to the cart
    Then the cart page should be displayed
    When the user clicks the checkout button
    And the user fills checkout info with first name "John" last name "Doe" postal code "12345"
    And the user clicks the continue button
    Then the order overview page should be displayed
    When the user clicks the finish button
    Then the order confirmation should display "Thank you for your order!"

  Scenario Outline: Checkout fails when required field is empty
    When the user sorts products by price low to high
    And the user adds the cheapest product to cart
    And the user navigates to the cart
    When the user clicks the checkout button
    And the user fills checkout info with first name "<firstName>" last name "<lastName>" postal code "<postalCode>"
    And the user clicks the continue button
    Then the checkout error should display "<errorMessage>"

    Examples:
      | firstName | lastName | postalCode | errorMessage                        |
      |           | Doe      | 12345      | Error: First Name is required       |
      | John      |          | 12345      | Error: Last Name is required        |
      | John      | Doe      |            | Error: Postal Code is required      |

@mobile
Feature: Login Functionality

  @android
  Scenario: Valid user logs in successfully
    Given the user launches the app
    When the user enters valid credentials "standard_user" "secret_sauce"
    And the user taps on login button
    ##Then Validate API call "/login" returns status 200
    Then the user should see the product catalog page

  @android
  Scenario: Validate invalid login attempts
    Given the user launches the app
    When the user enters valid credentials "Ningappa" "Poojari"
    And the user taps on login button
    Then validate the error message on failure

  @android
  Scenario: Verify product list, product details, and checkout flow
    Given the user launches the app
    When the user enters valid credentials "standard_user" "secret_sauce"
    And the user taps on login button
    Then the user should see the product catalog page

    And the list of products should be displayed
    When the user selects a product from the listing page
    Then the user should be redirected to the product details screen

    When the user adds the product to the cart
    Then the product should be added to the cart

    When the user taps on the cart icon
    Then the user should be redirected to the cart page

    When the user taps on the checkout button
    Then the user should be redirected to the checkout information screen

    When the user enters the checkout information 560079
    And the user taps on the continue button
    Then the user should be redirected to the checkout overview screen

    When the user taps on the finish button
    Then the order should be completed successfully

  @android
  Scenario: place order with empty cart
    Given the user launches the app
    When the user enters valid credentials "standard_user" "secret_sauce"
    And the user taps on login button

    Then the user should see the product catalog page
    When the user taps on the cart icon
    Then the user should be redirected to the cart page

    When the user taps on the checkout button
    When the user taps on the checkout button
    Then the user should be redirected to the checkout information screen

    When the user enters the checkout information 560079
    And the user taps on the continue button
    Then the user should be redirected to the checkout overview screen

    When the user taps on the finish button
    Then the order should be completed successfully

  @android
  Scenario: Leave checkout form fields empty and validate error messages
    Given the user launches the app
    When the user enters valid credentials "standard_user" "secret_sauce"
    And the user taps on login button
    Then the user should see the product catalog page
    When the user taps on the cart icon
    Then the user should be redirected to the cart page

    When the user taps on the checkout button
    Then the user should be redirected to the checkout information screen

    And the user taps on the continue button
    Then validate the error messages for empty fields


Feature: Register API

  @api
  Scenario: Successful user registration
    Given the user prepares register payload with username "john123" email "john@test.com" password "pass123"
    And the user sends the register API request
    Then the status code should be 200
    And the register response must contain id and token

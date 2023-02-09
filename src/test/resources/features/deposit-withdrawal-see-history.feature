Feature: A client can deposit or withdrawal money and see the history of all operations

  Scenario: save money
    Given an account exist with the number 111
    When client makes a deposit of 200.0 in his account
    Then the response status should be 201

  Scenario: retrieve some or all of money
    Given an account exist with the number 111
    When client makes a withdrawal of 50.0 from his account
    Then the response status should be 201

  Scenario: client makes call to GET account balance
    Given an account exist with the number 111
    When a client asks for his balance
    Then the response status should be 200
    And the balance should be 150.0

  Scenario: client makes call to GET history of all operations
    Given an account exist with the number 111
    When a client asks for his history
    Then the response status should be 200
    And the response should contains
      | type       | date       | amount | balance |
      | WITHDRAWAL | 2023-02-09 | -50     | 150     |
      | DEPOSIT    | 2023-02-01 | 200    | 200     |




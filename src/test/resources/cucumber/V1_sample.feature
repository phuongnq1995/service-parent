Feature: Sample data

  @GetSampleData
  Scenario: Get sample data
    When the client calls "/api"
    Then the client receives status code of "200"
    And the client receives response data "<column>"
      | column |
      | id     |
      | name   |

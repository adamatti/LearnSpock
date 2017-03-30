Feature: Person
    Tests for Person

Scenario: insert
    Given a new, empty database
    And a new Person
    When save a Person
    Then Person Count should be 1
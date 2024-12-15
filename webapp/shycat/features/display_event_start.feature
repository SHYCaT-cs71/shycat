Feature: Display event start date/time correctly
  Users want to see the correct event date and, if available, time

  Scenario: Display both date and time
    Given event start date is "2024-12-11T09:30:00"
    When the event is displayed
    Then it should be displayed as "December 11th, 2024 9:30 AM"

  Scenario: Display only date
    Given event start date is "2024-12-11"
    When the event is displayed
    Then it should be displayed as "December 11th, 2024"

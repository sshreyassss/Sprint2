Feature: Student Table Verification After Registration
  As an automation tester
  I want to verify that a registered student appears correctly in the student table
  So that data integrity is ensured post registration

  Background:
    Given I open the Smart University registration page

  Scenario: Verify student details appear in the table after registration
    When I register a student with the following details:
      | studName   | TestUser1           |
      | fatherName | Kumar               |
      | phone      | 9876543210          |
      | email      | testuser1@mail.com  |
      | department | CSE                 |
      | dob        | 01/01/2000          |
      | gender     | Male                |
      | country    | India               |
      | state      | Tamil Nadu          |
      | city       | Chennai             |
      | sslc       | 88                  |
      | hsc        | 92                  |
    Then the student "TestUser1" with email "testuser1@mail.com" should appear in the table

  Scenario: Verify duplicate student entries are allowed and appear twice
    When I register a student with the following details:
      | studName   | TestUser1           |
      | fatherName | Kumar               |
      | phone      | 9876543210          |
      | email      | testuser1@mail.com  |
      | department | CSE                 |
      | dob        | 01/01/2000          |
      | gender     | Male                |
      | country    | India               |
      | state      | Tamil Nadu          |
      | city       | Chennai             |
      | sslc       | 88                  |
      | hsc        | 92                  |
    And I register the same student again with the same details:
      | studName   | TestUser1           |
      | fatherName | Kumar               |
      | phone      | 9876543210          |
      | email      | testuser1@mail.com  |
      | department | CSE                 |
      | dob        | 01/01/2000          |
      | gender     | Male                |
      | country    | India               |
      | state      | Tamil Nadu          |
      | city       | Chennai             |
      | sslc       | 88                  |
      | hsc        | 92                  |
    Then I should see the student "TestUser1" with email "testuser1@mail.com" listed twice in the table

  Scenario: Verify duplicate student entries are occurring when I double click Submit Button
    When I register a student with the following details:
      | studName   | TestUser1           |
      | fatherName | Kumar               |
      | phone      | 9876543210          |
      | email      | testuser1@mail.com  |
      | department | CSE                 |
      | dob        | 01/01/2000          |
      | gender     | Male                |
      | country    | India               |
      | state      | Tamil Nadu          |
      | city       | Chennai             |
      | sslc       | 88                  |
      | hsc        | 92                  |
    And I click the submit button twice

    Then I should see the student "TestUser1" with email "testuser1@mail.com" listed twice in the table.


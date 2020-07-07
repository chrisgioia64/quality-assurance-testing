#Author: Christopher Gioia
@tag
Feature: title of your feature

  @smoke @tag1 @def
  Scenario: Opening the home page
    Given I open the home page
    Then verify the home page title

  @tag2 @def
  Scenario: invalid login
    Given I open the home page
    And I click on the account button
    When I enter invalid credentials
    Then verify the login error message appears


#  Scenario: invalid login 2
#    Given I open the home page
#    And I click on the account button
#    When I enter invalid username "abc" and password "abcd"
#    Then verify the login error message appears

  @tag3
  Scenario Outline: Invalid Logins
    Given I open the home page
    And I click on the account button
    When I enter invalid username "<username>" and password "<password>"
    Then verify the login error message appears

  Examples:
    | username | password |
    | cde | cde |
    |     | cde |
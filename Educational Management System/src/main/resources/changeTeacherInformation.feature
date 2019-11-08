#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Change teacher information
  I want to use this template for my feature file

  @tag1
  Scenario: Add address to a teacher
    Given a teacher with a first name "Barbara" and last name "Weber"
    And a teacher register where the teacher already is signed up
    When teacher adds the address "Building 304, 2800 Lyngby, DK"
    Then the address is added to the teacher
    
  @tag2
  Scenario: Add level to a teacher
    Given a teacher with a first name "Barbara" and last name "Weber"
    And a teacher register where the teacher already is signed up
    When teacher adds the level "Assistant"
    Then the level is added to the teacher
    
    
    
    
    

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
Feature: Change Student Information
  I want to use this template for my feature file

  @tag1
  Scenario: Add address to a student
    Given a student with a first name "Moritz" and last name "Hetzel" and with birthdate 08-28-1996
    And a student register where the student already is signed up
    When student adds the address "Vognporten 14, 2620 Albertslund, DK"
    Then the address is added to the student
    
    
    

    


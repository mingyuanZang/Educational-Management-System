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
Feature: Return teacher
  I want to use this template for my feature file

  @tag1
  Scenario: Return teacher by serial number
    Given a teacher with a first name "Barbara" and last name "Weber"
    And a teacher register where the teacher already is signed up
    And a teacher with a first name "Jason" and last name "Weber"
    And teacher sign-up
    When teacher register is searched by serial number 100002
    Then a teacher with first name "Jason" and last name "Weber" should be returned
    
  @tag2
  Scenario: Return teacher by first name
    Given a teacher with a first name "Barbara" and last name "Weber"
    And a teacher register where the teacher already is signed up
    And a teacher with a first name "Jason" and last name "Weber"
    And teacher sign-up
    And a teacher with a first name "Jason" and last name "Walde"
    And teacher sign-up
    When teacher register is searched by first name "Jason"
    Then an array list should be returned containing two teachers with first name "Jason"
    
  @tag3
  Scenario: Return teacher by last name
    Given a teacher with a first name "Barbara" and last name "Weber"
    And a teacher register where the teacher already is signed up
    And a teacher with a first name "Jason" and last name "Weber"
    And teacher sign-up
    And a teacher with a first name "Jason" and last name "Walde"
    And teacher sign-up
    When teacher register is searched by last name "Weber"
    Then an array list should be returned containing two teachers with last name "Weber"
    
  @tag4
  Scenario: Return teacher by email
    Given a teacher with a first name "Barbara" and last name "Weber"
    And a teacher register where the teacher already is signed up
    And a teacher with a first name "Jason" and last name "Weber"
    And teacher sign-up
    And a teacher with a first name "Jason" and last name "May"
    And teacher sign-up
    When teacher register is searched by email "BWeber@dtu.dk"
    Then a teacher with with first name "Barbara" and last name "Weber" should be returned

 
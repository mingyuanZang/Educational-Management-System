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
Feature: Return student
  I want to use this template for my feature file

  @tag1
  Scenario: Return student by first name
    Given a student with a first name "Alison" and last name "Davis" and with birthdate 08-27-1996
    And a student register where the student already is signed up
    And a student with a first name "Alison" and last name "Johnson" and with birthdate 02-14-1994
    And sign-up
    And a student with a first name "Moritz" and last name "Hetzel" and with birthdate 08-09-1996
    And sign-up
    When student register is searched by first name "Alison"
    Then an array list should be returned containing two students with first name "Alison"

	@tag2
  Scenario: Return student by last name
    Given a student with a first name "Alison" and last name "Davis" and with birthdate 08-27-1996
    And a student register where the student already is signed up
    And a student with a first name "Moritz" and last name "Davis" and with birthdate 02-14-1994
    And sign-up
    And a student with a first name "Moritz" and last name "Hetzel" and with birthdate 08-09-1996
    And sign-up
    When student register is searched by last name "Davis"
    Then an array list should be returned containing two students with last name "Davis"
    
  @tag3
  Scenario: Return student by student number
    Given a student with a first name "Alison" and last name "Davis" and with birthdate 08-27-1996
    And a student register where the student already is signed up
    And a student with a first name "Moritz" and last name "Davis" and with birthdate 02-14-1994
    And sign-up
    And a student with a first name "Moritz" and last name "Hetzel" and with birthdate 08-09-1996
    And sign-up
    When student register is searched by student number 210002
    Then student with a first name "Moritz" and last name "Davis" and with birthdate 02-14-1994 should be returned

  
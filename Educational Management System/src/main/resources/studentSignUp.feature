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
Feature: studentSignUp
  I want to use this template for my feature file

  @tag1
  Scenario: Student Sign Up
    Given a student with a first name "Moritz" and last name "Hetzel" and with birthdate 08-28-1996
    And an empty student register
    When sign-up
    Then student is created with student number 210001 and email "s210001@dtu.dk" 
    And  student should be added to register 
    
  Scenario: Student Sign Up Failed Since Student is Already Signed-up
    Given a student with a first name "Moritz" and last name "Hetzel" and with birthdate 08-28-1996
    And a student register where the student already exists
    When sign-up
    Then an error message should be displayed that the sign-up failed
 
  Scenario: Another Student Sign Up
    Given a student with a first name "Alison" and last name "Davis" and with birthdate 08-27-1996
    And a register with one student
    When sign-up
    Then student is created with student number 210002 and email "s210002@dtu.dk" 
    And  student should be added to register 



















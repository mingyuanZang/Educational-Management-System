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
Feature: teacherSignUp
  I want to use this template for my feature file

  @tag1
  Scenario: Teacher Sign Up
    Given a teacher with a first name "Barbara" and last name "Weber"
    And an empty teacher register
    When teacher sign-up
    Then teacher is created with serial number 100001 and email "bweber@dtu.dk" 
    And  teacher should be added to register 

    Scenario: Teacher Sign Up Failed Since Teacher is Already Signed-up
    Given a teacher with a first name "Barbara" and last name "Weber"
    And a teacher register where the teacher already exists
    When teacher sign-up
    Then an error message should be displayed that the teacher sign-up failed

    Scenario: Another teacher Sign Up
    Given a teacher with a first name "John" and last name "Smith"
    And a teacher register with one teacher
    When teacher sign-up
    Then teacher is created with serial number 100002 and email "jsmith@dtu.dk"
    And  teacher should be added to register
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
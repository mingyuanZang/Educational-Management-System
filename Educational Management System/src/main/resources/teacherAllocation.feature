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
Feature: Teacher Allocation
  I want to use this template for my feature file

  @tag1
  Scenario: Allocate one teacher to a course
    Given a teacher with a first name "Barbara" and last name "Weber"
    And a teacher register where the teacher already is signed up
    And a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    When teacher is allocated to course
    Then the teacher is added in the teacher list
    
  @tag2
  Scenario: Allocate one teacher to a course failed because the teacher already exists
    Given a teacher with a first name "Barbara" and last name "Weber"
    And a teacher register where the teacher already is signed up
    And a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    And the teacher is already added to the course
    When teacher is allocated to course
    Then an error message shows that the teacher already exists

  @tag3
  Scenario: Delete one teacher in a course
    Given a teacher with a first name "Barbara" and last name "Weber"
    And a teacher register where the teacher already is signed up
    And a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    And course contains the teacher in the teacher list
    When teacher is deleted from the teacher list
    Then teacher list is empty
    
  @tag4
  Scenario: Delete one teacher in a course failed because teacher is not in the teacher list
    Given a teacher with a first name "Barbara" and last name "Weber"
    And a teacher register where the teacher already is signed up
    And a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    When teacher is deleted from the teacher list
    Then an error message shows that the teacher is not in the teacher list
    
    






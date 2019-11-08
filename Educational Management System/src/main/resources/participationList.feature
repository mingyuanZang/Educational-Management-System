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
Feature: Participation List
  I want to use this template for my feature file

  @tag1
  Scenario: Get participation list
    Given a student with a first name "Moritz" and last name "Hetzel" and with birthdate 08-28-1996
    And a student register where the student already is signed up
    And a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    And containing the course with name "Physics" and course time "2A" and ECTS points 10 and type "BSc"
    And course "Programming" with course time "1A" requires 910002 as prerequisite
    And student has passed course 910002
    And student is enrolled in course with name "Programming" and course time "1A"
    And a programme register containing a programme with name "Computer Science" and type "BSc"
    And student enrolls in programme
    When the teacher wants to save participation list of the course 910001
    Then a response message will appear to show the PDF file has been generated
    

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
Feature: Change course information
  I want to use this template for my feature file

  @tag1
  Scenario: Add teacher assistant to a course
    Given a student with a first name "Moritz" and last name "Hetzel" and with birthdate 08-28-1996
    And a student register where the student already is signed up
    And a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    And student has passed course 910001
    When adding student as a teacher assistant to course 910001
    Then the student is added as a teacher assistant to the course 910001
    
  @tag2
  Scenario: Add teacher assistant to a course failed because student has not passed the course
    Given a student with a first name "Moritz" and last name "Hetzel" and with birthdate 08-28-1996
    And a student register where the student already is signed up
    And a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    When adding student as a teacher assistant to course 910001
    Then an error message shows that the student has not passed the course
    
  @tag3
  Scenario: Add teacher assistant to a course failed because the student aready exits
    Given a student with a first name "Moritz" and last name "Hetzel" and with birthdate 08-28-1996
    And a student register where the student already is signed up
    And a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    And student has passed course 910001
    And adding student as a teacher assistant to course 910001
    When adding student as a teacher assistant to course 910001
    Then an error message shows that the student already exists
    
  @tag4
  Scenario: Delete teacher assistant from a course
    Given a student with a first name "Moritz" and last name "Hetzel" and with birthdate 08-28-1996
    And a student register where the student already is signed up
    And a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    And student has passed course 910001
    And course contains the student in the teacher assistant list
    When deleting student from teacher assistant list
    Then the teacher assistant list is empty
    
  @tag5
  Scenario: Delete teacher assistant from a course failed because the student is not in the teacher assistant list
    Given a student with a first name "Moritz" and last name "Hetzel" and with birthdate 08-28-1996
    And a student register where the student already is signed up
    And a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    When deleting student from teacher assistant list
    Then an error message shows that the student is not in the teacher assistant list
    
  @tag6
  Scenario: Add description to a course
    Given a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    When adding description "This course teaches you basic object-oriented programming skills. The used programming language is Java." to course
    Then the description is added to course
    
  @tag7
  Scenario: Add learning goal to a course
    Given a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    When adding learning goal "After this course, students should be able to understand basic concepts and know how to program in java" to course
    Then the learning goal is added to course
    
  @tag8
  Scenario: Add ECTS points to a course
    Given a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    When adding 10 ECTS points to the course
    Then the ECTS points is added to course
    

  
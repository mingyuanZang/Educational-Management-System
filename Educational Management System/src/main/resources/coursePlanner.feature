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
Feature: Course Planner
  I want to use this template for my feature file

  @tag1
  Scenario: Show the missing courses of a student
    Given a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    And containing the course with name "Physics" and course time "2A" and ECTS points 10 and type "BSc"
    And containing the course with name "Math1" and course time "2B" and ECTS points 10 and type "BSc"
    And a programme register containing a programme with name "Computer Science" and type "BSc"
    And adding the course with name "Physics" and course time "2A" as elective course to a programme
    And adding the course with name "Math1" and course time "2B" as mandatory course to a programme
    And a student with a first name "Jingkun" and last name "Zhu" and with birthdate 06-04-1996
    And a student register where the student already is signed up
    And student enrolls in programme
    And student has passed course 910001
    When student queries the system the missing courses
    Then the message about missing courses should be displayed

  @tag2
  Scenario: a student has passed all the courses required
    Given a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    And containing the course with name "Physics" and course time "2A" and ECTS points 10 and type "BSc"
    And a programme register containing a programme with name "Computer Science" and type "BSc"
    And adding the course with name "Physics" and course time "2A" as elective course to a programme
    And a student with a first name "Jingkun" and last name "Zhu" and with birthdate 06-04-1996
    And a student register where the student already is signed up
    And student enrolls in programme
    And student has passed course 910001
    And student has passed course 910002
    When student queries the system the missing courses
    Then the message that all the courses are completed should be displayed

  @tag3
  Scenario: Show the missing courses of a student (only missing mandatory courses)
    Given a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    And containing the course with name "Physics" and course time "2A" and ECTS points 10 and type "BSc"
    And containing the course with name "Math1" and course time "2B" and ECTS points 10 and type "BSc"
    And a programme register containing a programme with name "Computer Science" and type "BSc"
    And adding the course with name "Physics" and course time "2A" as mandatory course to a programme
    And adding the course with name "Math1" and course time "2B" as mandatory course to a programme
    And a student with a first name "Jingkun" and last name "Zhu" and with birthdate 06-04-1996
    And a student register where the student already is signed up
    And student enrolls in programme
    And student has passed course 910001
    When student queries the system the missing courses
    Then the message about missing courses should be displayed

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
Feature: Student Enrollment
  I want to use this template for my feature file

  @tag1
  Scenario: Enroll one student to a course
    Given a student with a first name "Moritz" and last name "Hetzel" and with birthdate 08-28-1996
    And a student register where the student already is signed up
    And a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    When student enrolls in course
    Then the student is added in the class list
    And the course is added in the current course list

  @tag2
  Scenario: Enrollment of student failed because he already has a course at the same time slot
    Given a student with a first name "Moritz" and last name "Hetzel" and with birthdate 08-28-1996
    And a student register where the student already is signed up
    And a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    And containing the course with name "Physics" and course time "2A" and ECTS points 10 and type "BSc"
    And containing the course with name "Math1" and course time "2B" and ECTS points 10 and type "BSc"
    And course "Programming" with course time "1A" requires 910002 as prerequisite
    And student has passed course 910002
    And student is enrolled in course with name "Programming" and course time "1A"
    When student enrolls in course with name "Physics" and course time "1A"
    Then an error message should be displayed that the enrollment failed
    
  @tag3
  Scenario: Enrollment of student failed because of missing prerequisite
    Given a student with a first name "Moritz" and last name "Hetzel" and with birthdate 08-28-1996
    And a student register where the student already is signed up
    And a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    And containing the course with name "Physics" and course time "2A" and ECTS points 10 and type "BSc"
    And containing the course with name "Math1" and course time "2B" and ECTS points 10 and type "BSc"
    And course "Programming" with course time "1A" requires 910002 as prerequisite
    And course "Programming" with course time "1A" requires 910003 as prerequisite
    And student has passed course 910002
    When student enrolls in course with name "Programming" and course time "1A"
    Then an error message should be displayed that the enrollment failed
    
  @tag4
  Scenario: Enrollment of student failed because he is already enrolled in the course
    Given a student with a first name "Moritz" and last name "Hetzel" and with birthdate 08-28-1996
    And a student register where the student already is signed up
    And a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    And containing the course with name "Physics" and course time "2A" and ECTS points 10 and type "BSc"
    And course "Programming" with course time "1A" requires 910002 as prerequisite
    And student has passed course 910002
    And student enrolls in course with name "Programming" and course time "1A"
    When student enrolls in course with name "Programming" and course time "1A" again
    Then an error message should be displayed that the student is already enrolled in the course
    
  @tag5
  Scenario: Enroll one student to a programme
    Given a student with a first name "Moritz" and last name "Hetzel" and with birthdate 08-28-1996
    And a student register where the student already is signed up
    And a programme register containing a programme with name "Computer Science" and type "BSc"
    When student enrolls in programme
    Then the programme is added to the student
    
    
    
    
    
    
    
    
    
    
    
    
    
    
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
Feature: addCourse
  I want to use this template for my feature file

  @tag1
  Scenario: Adding one course to an empty course register
    Given a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    And an empty course register
    When adding course to the course register
    Then the course is created with a course number 910001
    And the course should be added to the course register

  @tag2
  Scenario: Adding one course to an empty course register
    Given a course with name "Functional Materials" and course time "1A" and ECTS points 10 and type "PhD"
    And an empty course register
    When adding course to the course register
    Then the course is created with a course number 910001
    And the course should be added to the course register

  @tag3
  Scenario: Adding one course to an empty course register
    Given a course with name "Materials Science" and course time "1A" and ECTS points 10 and type "MSc"
    And an empty course register
    When adding course to the course register
    Then the course is created with a course number 910001
    And the course should be added to the course register

  @tag4
  Scenario: Course Adding Failed Since Course is Already In Course Register
    Given a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    And a course register where the course already exists in that time slot
    When adding course to the course register
    Then an error message should be displayed that the adding failed

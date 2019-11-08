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
Feature: Return Course
  I want to use this template for my feature file

  @tag1
  Scenario: Return course by keyword
  	Given a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    And adding description "This course teaches you basic object-oriented programming skills. The used programming language is Java." to course
    And a course with name "Math1" and course time "2B" and ECTS points 10 and type "BSc"
    And adding description "This course teaches you mathematic fundamentals. It gives you deeper insights to algebra and geometry." to course
    And adding course to the course register
    When searching the course register for a course with keyword "Java" in the description or the course name
    Then course with name "Programming" is returned in the array list

  @tag2
  Scenario: Return course by keyword failed
  	Given a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    And adding description "This course teaches you basic object-oriented programming skills. The used programming language is Java." to course
    And a course with name "Math1" and course time "2B" and ECTS points 10 and type "BSc"
    And adding description "This course teaches you mathematic fundamentals. It gives you deeper insights to algebra and geometry." to course
    And adding course to the course register
    When searching the course register for a course with keyword "business" in the description or the course name
    Then a negative response message is displayed
    
  @tag3
  Scenario: Return course by type
  	Given a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
  	And a course with name "Math1" and course time "2B" and ECTS points 10 and type "MSc"
  	When searching the course register for a course with type "BSc"
  	Then course with name "Programming" is returned in the array list

  #@tag4
  #Scenario: Return course by name and time failed
  #	Given a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
  #	And a course with name "Math1" and course time "2B" and ECTS points 10 and type "MSc"
  #	When searching the course register for a course with name "Physics" and course time "3A"
  #	Then null is returned
#
  #@tag5
  #Scenario: Return course by name and time failed
  #	Given a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
  #	And a course with name "Math1" and course time "2B" and ECTS points 10 and type "MSc"
  #	When searching the course register for a course with name "Math1" and course time "1A"
  #	Then null is returned




















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
Feature: Add programme
  I want to use this template for my feature file

  @tag1
  Scenario: Adding one programme to an empty programme register
    Given a programme with name "Computer Science" and type "BSc" 
    And an empty programme register
    When adding programme to the programme register
    Then the programme is created
    And the programme should be added to the programme register
    
  @tag2
  Scenario: Adding a mandatory course to a programme
    Given a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    And a programme register containing a programme with name "Computer Science" and type "BSc"
    When adding the course as mandatory course to a programme
    Then the mandatory course should be added to the programme
    
  @tag3
  Scenario: a mandatory course added to a programme failed because it already exists
    Given a course register containing a course with name "Programming" and course time "1A" and ECTS points 10 and type "BSc"
    And a programme register containing a programme with name "Computer Science" and type "BSc"
    And adding the course as mandatory course to a programme
    When adding the course as mandatory course to a programme
    Then an error message shows that the mandatory course already exists in the list
    
  @tag4
  Scenario: Adding an elective course to a programme
    Given a course register containing a course with name "Project Management" and course time "3B" and ECTS points 10 and type "BSc"
    And a programme register containing a programme with name "Computer Science" and type "BSc"
    When adding the course as elective course to a programme
    Then the elective course should be added to the programme
    
  @tag5
  Scenario: an elective course added to a programme failed because it already exists
    Given a course register containing a course with name "Project Management" and course time "3B" and ECTS points 10 and type "BSc"
    And a programme register containing a programme with name "Computer Science" and type "BSc"
    And adding the course as elective course to a programme
    When adding the course as elective course to a programme
    Then an error message shows that the elective course already exists in the list
    
  @tag6
  Scenario: Programme Adding Failed Since Programme is Already In Programme Register
    Given a programme register containing a programme with name "Computer Science" and type "BSc"
    And a programme with name "Computer Science" and type "BSc" 
    When adding programme to the programme register
    Then an error message should be displayed that the adding of programme failed















  

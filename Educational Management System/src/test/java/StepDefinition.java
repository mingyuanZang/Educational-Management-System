
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import application.model.Course;
import application.model.CourseRegister;
import application.model.Date;
import application.model.InvalidDateException;
import application.model.Level;
import application.model.Programme;
import application.model.ProgrammeRegister;
import application.model.ResponseMessage;
import application.model.Student;
import application.model.StudentRegister;
import application.model.Teacher;
import application.model.TeacherRegister;
import application.model.CourseType;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinition {

	Student student;
	Teacher teacher;
	Programme programme;
	StudentRegister studentRegister;
	ResponseMessage response;
	TeacherRegister teacherRegister;
	ProgrammeRegister programmeRegister;

	
	Course course;
	CourseRegister courseRegister;
	
	//For the return methods
	ArrayList<Student> studentList;
	ArrayList<Teacher> teacherList;
	ArrayList<Course> courseList;

	
	@Given("^a student with a first name \"([^\"]*)\" and last name \"([^\"]*)\" and with birthdate (\\d+)-(\\d+)-(\\d+)$")
	public void a_student_with_a_first_name_and_last_name_and_with_birthdate(String firstName, String lastName, int month, int day, int year) {
		try {
			student = new Student(firstName, lastName, new Date(month, day, year));
		} catch (InvalidDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Given("^an empty student register$")
	public void a_student_register() {
		studentRegister = new StudentRegister();
	}

	@When("^sign-up$")
	public void sign_up() {
		response = studentRegister.signUp(student);
	}

	@Then("^student is created with student number (\\d+) and email \"([^\"]*)\"$")
	public void student_is_creatd_with_student_number_and_email(int TestStudentNumber, String TestEmail) {
		assertEquals(TestStudentNumber, student.getStudentNumber());
		assertEquals(TestEmail, student.getEmail());
	}

	@Then("^student should be added to register$")
	public void student_should_be_added_to_register() {
		assertTrue(studentRegister.studentIsInRegister(student));
	}

	@Given("^a student register where the student already exists$")
	public void a_student_register_where_the_student_already_exists() {
		studentRegister = new StudentRegister();
		studentRegister.addStudent(student);
	}

	@Then("^an error message should be displayed that the sign-up failed$")
	public void an_error_message_should_be_displayed_that_the_sign_up_failed() {
		assertEquals(response.getResponseMessage(), "Student is already in the register");
	}

	@Given("^a register with one student$")
	public void a_register_with_one_student() {
		studentRegister = new StudentRegister();
		try {
			response = studentRegister.signUp(new Student("Jon", "Smith", new Date(11, 11, 2000)));
		} catch (InvalidDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//START RETURN STUDENT FEATURE STEPS
	
	@When("^student register is searched by first name \"([^\"]*)\"$")
	public void student_register_is_searched_by_first_name(String firstName) {
	    studentList = studentRegister.returnStudentByFirstName(firstName);
	}

	@Then("^an array list should be returned containing two students with first name \"([^\"]*)\"$")
	public void an_array_list_should_be_returned_containing_two_students_with_first_name(String firstName) {
		assertEquals(2, studentList.size());
		assertEquals(studentList.get(0).getFirstName(), firstName);
		assertEquals(studentList.get(1).getFirstName(), firstName);
	}
	
	@When("^student register is searched by last name \"([^\"]*)\"$")
	public void student_register_is_searched_by_last_name(String lastName) {
	    studentList = studentRegister.returnStudentByLastName(lastName);
	}

	@Then("^an array list should be returned containing two students with last name \"([^\"]*)\"$")
	public void an_array_list_should_be_returned_containing_two_students_with_last_name(String lastName) {
		assertEquals(2, studentList.size());
		assertEquals(studentList.get(0).getLastName(), lastName);
		assertEquals(studentList.get(1).getLastName(), lastName);
	}

	@When("^student register is searched by student number (\\d+)$")
	public void student_register_is_searched_by_student_number(int studentNumber) {
	    student = studentRegister.returnStudentByNumber(studentNumber);
	}

	@Then("^student with a first name \"([^\"]*)\" and last name \"([^\"]*)\" and with birthdate (\\d+)-(\\d+)-(\\d+) should be returned$")
	public void student_with_a_first_name_and_last_name_and_with_birthdate_should_be_returned(String firstName, String lastName, int month, int day, int year) {
		assertEquals(student.getFirstName(), firstName);
		assertEquals(student.getLastName(), lastName);
		Date birthdate;
		try {
			birthdate = new Date(month, day, year);
		} catch (InvalidDateException e) {
			// TODO Auto-generated catch block
			birthdate = null;
			e.printStackTrace();
		}
		assertEquals(student.returnBirthdateAsString(), birthdate.dateToString());
	}
	
	// START TEACHER FEATURE STEPS

	@Given("^a teacher with a first name \"([^\"]*)\" and last name \"([^\"]*)\"$")
	public void a_teacher_with_a_first_name_and_last_name(String firstName, String lastName) {
		teacher = new Teacher(firstName, lastName);
	}

	@Given("^an empty teacher register$")
	public void an_empty_teacher_register() {
		teacherRegister = new TeacherRegister();
	}

	@When("^teacher sign-up$")
	public void teacher_sign_up() {
		response = teacherRegister.signUp(teacher);
	}

	@Then("^teacher is created with serial number (\\d+) and email \"([^\"]*)\"$")
	public void teacher_is_created_with_serial_number_and_email(int TestSerialNumber, String testEmail) {
		assertEquals(TestSerialNumber, teacher.getSerialNumber());
		assertEquals(testEmail, teacher.getEmail());
	}

	@Then("^teacher should be added to register$")
	public void teacher_should_be_added_to_register() {
		assertTrue(teacherRegister.teacherIsInRegister(teacher));
	}

	@Given("^a teacher register where the teacher already exists$")
	public void a_teacher_register_where_the_teacher_already_exists() {
		teacherRegister = new TeacherRegister();
		teacherRegister.addTeacher(teacher);
	}

	@Then("^an error message should be displayed that the teacher sign-up failed$")
	public void an_error_message_should_be_displayed_that_the_teacher_sign_up_failed() {
		assertEquals(response.getResponseMessage(), "Teacher is already in the register");

	}

	@Given("^a teacher register with one teacher$")
	public void a_teacher_register_with_one_teacher() {
		teacherRegister = new TeacherRegister();
		response = teacherRegister.signUp(new Teacher("Mingyuan", "Zang"));
	}

	//START RETURN TEACHER FEATURE STEPS
	
	@When("^teacher register is searched by serial number (\\d+)$")
	public void teacher_register_is_searched_by_serial_number(int serialNumber) {
	    teacher = teacherRegister.returnTeacherBySerialNumber(serialNumber);
	}

	@Then("^a teacher with first name \"([^\"]*)\" and last name \"([^\"]*)\" should be returned$")
	public void a_teacher_with_first_name_and_last_name_should_be_returned(String firstName, String lastName) {
	    assertEquals(teacher.getFirstName(), firstName);
	    assertEquals(teacher.getLastName(), lastName);
	}
	
	@When("^teacher register is searched by first name \"([^\"]*)\"$")
	public void teacher_register_is_searched_by_first_name(String firstName) {
		teacherList = teacherRegister.returnTeacherByFirstName(firstName);
	}

	@Then("^an array list should be returned containing two teachers with first name \"([^\"]*)\"$")
	public void an_array_list_should_be_returned_containing_two_teachers_with_first_name(String firstName) {
		assertEquals(2, teacherList.size());
		assertEquals(teacherList.get(0).getFirstName(), firstName);
		assertEquals(teacherList.get(1).getFirstName(), firstName);
	}
	
	@When("^teacher register is searched by last name \"([^\"]*)\"$")
	public void teacher_register_is_searched_by_last_name(String lastName) {
	    teacherList = teacherRegister.returnTeacherByLastName(lastName);
	}

	@Then("^an array list should be returned containing two teachers with last name \"([^\"]*)\"$")
	public void an_array_list_should_be_returned_containing_two_teachers_with_last_name(String lastName) {
		assertEquals(2, teacherList.size());
		assertEquals(teacherList.get(0).getLastName(), lastName);
		assertEquals(teacherList.get(1).getLastName(), lastName);
	}
	
	@When("^teacher register is searched by email \"([^\"]*)\"$")
	public void teacher_register_is_searched_by_email(String teacherEmail) {
		teacher = teacherRegister.returnTeacherByEmail(teacherEmail);
	}

	@Then("^a teacher with with first name \"([^\"]*)\" and last name \"([^\"]*)\" should be returned$")
	public void a_teacher_with_with_first_name_and_last_name_should_be_returned(String firstName, String lastName) {
		assertEquals(teacher.getFirstName(), firstName);
	    assertEquals(teacher.getLastName(), lastName);
	}
	
	// START COURSE FEATURE STEPS
	
	@Given("^a course with name \"([^\"]*)\" and course time \"([^\"]*)\" and ECTS points (\\d+) and type \"([^\"]*)\"$")
	public void a_course_with_name_and_course_time_and_ECTS_points_and_type(String courseName, String courseTime, int ECTS, String type) {
		course = new Course(courseName, courseTime, ECTS, CourseType.valueOf(type));
	}

	@Given("^an empty course register$")
	public void an_empty_course_register() {
		courseRegister = new CourseRegister();
	}

	@When("^adding course to the course register$")
	public void adding_course_to_the_course_register() {
		response = courseRegister.adding(course);
	}

	@Then("^the course is created with a course number (\\d+)$")
	public void the_course_is_created_with_a_course_number(int TestCourseNumber) {
		assertEquals(TestCourseNumber, course.getCourseNumber());
	}

	@Then("^the course should be added to the course register$")
	public void the_course_should_be_added_to_the_course_register() {
		assertTrue(courseRegister.courseIsInRegister(course));
	}

	@Given("^a course register where the course already exists in that time slot$")
	public void a_course_register_where_the_course_already_exists_in_that_time_slot() {
		courseRegister = new CourseRegister();
		course = new Course("Programming", "1A", 10, CourseType.valueOf("BSc"));
		courseRegister.addCourse(course);
	}

	@Then("^an error message should be displayed that the adding failed$")
	public void an_error_message_should_be_displayed_that_the_adding_failed() {
		assertEquals(response.getResponseMessage(), "Course is already in the register");
	}
	
	// START COURSE SEARCH FEATURE STEPS
	
	@When("^searching the course register for a course with keyword \"([^\"]*)\" in the description or the course name$")
	public void searching_the_course_register_for_a_course_with_keyword_in_the_description_or_the_course_name(String keyword) {
		courseList = courseRegister.searchByKeyword(keyword);
		if (courseList.isEmpty()) {
			response = new ResponseMessage("No results");
		}
	}

	@Then("^course with name \"([^\"]*)\" is returned in the array list$")
	public void course_with_name_is_returned_in_the_array_list(String courseName) {
		assertEquals(courseList.size(), 1);
	    assertEquals(courseList.get(0).getCourseName(), "Programming");
	}
	
	@Then("^a negative response message is displayed$")
	public void a_negative_response_message_is_displayed() {
	    assertEquals(response.getResponseMessage(), "No results");
	}

	@When("^searching the course register for a course with type \"([^\"]*)\"$")
	public void searching_the_course_register_for_a_course_with_type(String type) {
		courseList = courseRegister.searchByType(CourseType.valueOf(type));
		if (courseList.isEmpty()) {
			response = new ResponseMessage("No results");
		}
	}
	
//	@When("^searching the course register for a course with name \"([^\"]*)\" and course time \"([^\"]*)\"$")
//	public void searching_the_course_register_for_a_course_with_name_and_course_time(String courseName, String courseTime) {
//		course = courseRegister.returnCourseByNameAndTime(courseName, courseTime);
//	}
//
//	@Then("^null is returned$")
//	public void null_is_returned() {
//		assertEquals(course, null);
//	}

	// START STUDENT ENROLLMENT FEATURE STEPS

	@Given("^a student register where the student already is signed up$")
	public void a_student_register_where_the_student_already_is_signed_up() {
		studentRegister = new StudentRegister();
		response = studentRegister.signUp(student);
	}
	
	@Given("^a course register containing a course with name \"([^\"]*)\" and course time \"([^\"]*)\" and ECTS points (\\d+) and type \"([^\"]*)\"$")
	public void a_course_register_containing_a_course_with_name_and_course_time_and_ECTS_points_and_type(String courseName, String courseTime, int ECTS, String type) {
		courseRegister = new CourseRegister();
		course = new Course(courseName, courseTime, ECTS, CourseType.valueOf(type));
		response = courseRegister.adding(course);
	}

	@When("^student enrolls in course$")
	public void student_enrolls_in_course() {
		response = student.enrollCourse(course);

		if (response.getResponseMessage().equals("Student added")) {
			response = course.addStudent(student);
		}
	}

	@Then("^the student is added in the class list$")
	public void the_student_is_added_in_the_class_list() {
		assertTrue(course.studentIsInClassList(student));
	}

	@Then("^the course is added in the current course list$")
	public void the_course_is_added_in_the_current_course_list() {
		assertTrue(student.courseIsInCurrentCourseList(course));
	}
	
	@Given("^containing the course with name \"([^\"]*)\" and course time \"([^\"]*)\" and ECTS points (\\d+) and type \"([^\"]*)\"$")
	public void containing_the_course_with_name_and_course_time_and_ECTS_points_and_type(String courseName, String courseTime, int ECTS, String type) {
		response = courseRegister.adding(new Course(courseName, courseTime, ECTS, CourseType.valueOf(type)));
	}

	@Given("^student is enrolled in course with name \"([^\"]*)\" and course time \"([^\"]*)\"$")
	public void student_is_enrolled_in_course_with_name_and_course_time(String courseName, String courseTime) {
		student.enrollCourse(course);
		course.addStudent(student);
	}

	@When("^student enrolls in course with name \"([^\"]*)\" and course time \"([^\"]*)\"$")
	public void student_enrolls_in_course_with_name_and_course_time(String courseName, String courseTime) {
		Course courseEnroll = courseRegister.returnCourseByNameAndTime(courseName, courseTime);
		response = student.enrollCourse(course);

		if (response.getResponseMessage().equals("Student added")) {
			courseEnroll.addStudent(student);
		}
	}

	@Then("^an error message should be displayed that the enrollment failed$")
	public void an_error_message_should_be_displayed_that_the_enrollment_failed() {
		assertEquals(response.getResponseMessage(), "Student cannot be enrolled");
	}

	@Given("^course \"([^\"]*)\" with course time \"([^\"]*)\" requires (\\d+) as prerequisite$")
	public void course_with_course_time_requires_as_prerequisite(String courseName, String courseTime,
			int courseNumber) {
		Course coursePrereq = courseRegister.returnCourseByNameAndTime(courseName, courseTime);
		coursePrereq.addCourseToPrerequisites(courseRegister.returnCourseByNumber(courseNumber));
	}

	@Given("^student has passed course (\\d+)$")
	public void student_has_passed_course(int courseNumber) {
		Course coursePassed = courseRegister.returnCourseByNumber(courseNumber);
		student.addCourseToPassedCourseList(coursePassed);
	}
	
	@When("^student enrolls in course with name \"([^\"]*)\" and course time \"([^\"]*)\" again$")
	public void student_enrolls_in_course_with_name_and_course_time_again(String courseName, String courseTime) {
		Course course = courseRegister.returnCourseByNameAndTime(courseName, courseTime);
		response = course.addStudent(student);
	}
	
	@Then("^an error message should be displayed that the student is already enrolled in the course$")
	public void an_error_message_should_be_displayed_that_the_student_is_already_enrolled_in_the_course() {
	    assertEquals(response.getResponseMessage(), "Student is already in the course list");
	}
	
	@When("^student enrolls in programme$")
	public void student_enrolls_in_programme() {
		student.enrollProgramme(programme);
	}

	@Then("^the programme is added to the student$")
	public void the_programme_is_added_to_the_student() {
	    assertTrue(student.studentEnrolledToAProgramme());
	}

	// START TEACHER ALLOCATION FEATURE STEPS

	@Given("^a teacher register where the teacher already is signed up$")
	public void a_teacher_register_where_the_teacher_already_is_signed_up() {
		teacherRegister = new TeacherRegister();
		response = teacherRegister.signUp(teacher);
	}

	@When("^teacher is allocated to course$")
	public void teacher_is_allocated_to_course() {
		response = course.addTeacher(teacher);
	}

	@Then("^the teacher is added in the teacher list$")
	public void the_teacher_is_added_in_the_teacher_list() {
		assertTrue(course.teacherIsInTeacherList(teacher));
	}
	
	@Given("^the teacher is already added to the course$")
	public void the_teacher_is_already_added_to_the_course() {
	    response = course.addTeacher(teacher);
	}

	@Then("^an error message shows that the teacher already exists$")
	public void an_error_message_shows_that_the_teacher_already_exists() {
	    assertEquals(response.getResponseMessage(), "Teacher is already in the teacher list");
	}
	
	@Given("^course contains the teacher in the teacher list$")
	public void course_contains_the_teacher_in_the_teacher_list() {
		course.addTeacher(teacher);
	}

	@When("^teacher is deleted from the teacher list$")
	public void teacher_is_deleted_from_the_teacher_list() {
		response = course.deleteTeacher(teacher);
	}

	@Then("^teacher list is empty$")
	public void teacher_list_is_empty() {
	    assertEquals(response.getResponseMessage(), "Teacher deleted");
	}
	
	@Then("^an error message shows that the teacher is not in the teacher list$")
	public void an_error_message_shows_that_the_teacher_is_not_in_the_teacher_list() {
	    assertEquals(response.getResponseMessage(), "Teacher is not in the teacher list");
	}
	
	// START CHANGE STUDENT INFORMATION FEATURE STEPS
	
	@When("^student adds the address \"([^\"]*)\"$")
	public void student_adds_the_address(String address) {
	    student.setAddress(address);
	}

	@Then("^the address is added to the student$")
	public void the_address_is_added_to_the_student() {
	    assertEquals(student.getAddress(), "Vognporten 14, 2620 Albertslund, DK");
	}
	
	
	// START CHANGE TEACHER INFORMATION FEATURE STEPS
	
	@When("^teacher adds the address \"([^\"]*)\"$")
	public void teacher_adds_the_address(String address) {
	    teacher.setAddress(address);
	}

	@Then("^the address is added to the teacher$")
	public void the_address_is_added_to_the_teacher() {
		assertEquals(teacher.getAddress(), "Building 304, 2800 Lyngby, DK");
	}
	
	@When("^teacher adds the level \"([^\"]*)\"$")
	public void teacher_adds_the_level(String level) {
	    teacher.setLevel(Level.valueOf(level));
	}

	@Then("^the level is added to the teacher$")
	public void the_level_is_added_to_the_teacher() {
	    assertEquals(teacher.getLevel(), Level.Assistant);
	}
	
	
	//START CHANGE COURSE INFORMATION STEPS
	
	@When("^adding student as a teacher assistant to course (\\d+)$")
	public void adding_student_as_a_teacher_assistant_to_course(int courseNumber) {
		Course chosenCourse = courseRegister.returnCourseByNumber(courseNumber);
	    response = course.addTeacherAssistant(student, chosenCourse);
	}

	@Then("^the student is added as a teacher assistant to the course (\\d+)$")
	public void the_student_is_added_as_a_teacher_assistant_to_the_course(int courseNumber) {
	    course = courseRegister.returnCourseByNumber(courseNumber);
		assertTrue(course.getTeacherAssistantList().contains(student));
		assertEquals(response.getResponseMessage(), "Student added as a teacher assistant");
	}
	
	@Then("^an error message shows that the student has not passed the course$")
	public void an_error_message_shows_that_the_student_has_not_passed_the_course() throws Throwable {
	    assertEquals(response.getResponseMessage(), "Student did not pass this course");
	}
	
	@Then("^an error message shows that the student already exists$")
	public void an_error_message_shows_that_the_student_already_exists() throws Throwable {
		assertEquals(response.getResponseMessage(), "Student is already in the teacher assistant list");
	}
	
	@Given("^course contains the student in the teacher assistant list$")
	public void course_contains_the_student_in_the_teacher_assistant_list() {
	    response = course.addTeacherAssistant(student, course);
	    assertEquals(response.getResponseMessage(), "Student added as a teacher assistant");
	}

	@When("^deleting student from teacher assistant list$")
	public void deleting_student_from_teacher_assistant_list() {
	    response = course.deleteTeacherAssistant(student);
	}

	@Then("^the teacher assistant list is empty$")
	public void the_teacher_assistant_list_is_empty() throws Throwable {
		assertEquals(course.getTeacherAssistantList().size(), 0);
		assertEquals(response.getResponseMessage(), "Teacher assistant deleted");
	}
	
	@Then("^an error message shows that the student is not in the teacher assistant list$")
	public void an_error_message_shows_that_the_student_is_not_in_the_teacher_assistant_list() throws Throwable {
	    assertEquals(response.getResponseMessage(), "Student is not in the teacher assistant list");
	}
	
	@When("^adding description \"([^\"]*)\" to course$")
	public void adding_description_to_course(String courseDescription) {
	    course.setCourseDescription(courseDescription);
	}

	@Then("^the description is added to course$")
	public void the_description_is_added_to_course() {
	    assertEquals(course.getCourseDescription(), "This course teaches you basic object-oriented programming skills. The used programming language is Java.");
	}
	
	@When("^adding learning goal \"([^\"]*)\" to course$")
	public void adding_learning_goal_to_course(String courseLearningGoal) {
		course.setCourseLearningGoal(courseLearningGoal);
	}

	@Then("^the learning goal is added to course$")
	public void the_learning_goal_is_added_to_course() {
	    assertEquals(course.getCourseLearningGoal(), "After this course, students should be able to understand basic concepts and know how to program in java");
	}
	
	@When("^adding (\\d+) ECTS points to the course$")
	public void adding_ECTS_points_to_the_course(int ECTS) {
	    course.setECTS(ECTS);
	}

	@Then("^the ECTS points is added to course$")
	public void the_ECTS_points_is_added_to_course() {
	    assertEquals(course.getECTS(), 10);
	}
	
	//START ADD PROGRAMME STEPS
	
	@Given("^a programme with name \"([^\"]*)\" and type \"([^\"]*)\"$")
	public void a_programme_with_name_and_type(String programmeName, String type) {
		programme = new Programme(programmeName, CourseType.valueOf(type));
	}

	@Given("^an empty programme register$")
	public void an_empty_programme_register() {
		programmeRegister = new ProgrammeRegister();
	}

	@When("^adding programme to the programme register$")
	public void adding_programme_to_the_programme_register() {
		response = programmeRegister.adding(programme);
	}

	@Then("^the programme is created$")
	public void the_programme_is_created() {
		assertTrue(programmeRegister.programmeIsInRegister(programme));
	}

	@Then("^the programme should be added to the programme register$")
	public void the_programme_should_be_added_to_the_programme_register() {
	    assertEquals(response.getResponseMessage(), "Programme added");
	}
	
	@Given("^a programme register containing a programme with name \"([^\"]*)\" and type \"([^\"]*)\"$")
	public void a_programme_register_containing_a_programme_with_name_and_type(String programmeName, String type) {
		programmeRegister = new ProgrammeRegister();
		programme = new Programme(programmeName, CourseType.valueOf(type));
		response = programmeRegister.adding(programme);
		assertEquals(response.getResponseMessage(), "Programme added");
	}

	@When("^adding the course as mandatory course to a programme$")
	public void adding_the_course_as_mandatory_course_to_a_programme() {
		response = programme.addMandatoryCourseInProgramme(course);
	}

	@Then("^the mandatory course should be added to the programme$")
	public void the_mandatory_course_should_be_added_to_the_programme() {
		assertEquals(response.getResponseMessage(), "Mandatory course added");
	}
	
	@Then("^an error message shows that the mandatory course already exists in the list$")
	public void an_error_message_shows_that_the_mandatory_course_already_exists_in_the_list() {
	    assertEquals(response.getResponseMessage(), "Course is already in mandatory course list");
	}
	
	@When("^adding the course as elective course to a programme$")
	public void adding_the_course_as_elective_course_to_a_programme() {
		response = programme.addElectiveCourseInProgramme(course);
	}

	@Then("^the elective course should be added to the programme$")
	public void the_elective_course_should_be_added_to_the_programme() {
		assertEquals(response.getResponseMessage(), "Elective course added");
	}
	
	@Then("^an error message shows that the elective course already exists in the list$")
	public void an_error_message_shows_that_the_elective_course_already_exists_in_the_list() throws Throwable {
	    assertEquals(response.getResponseMessage(), "Course is already in elective course list");
	}
	
	@Then("^an error message should be displayed that the adding of programme failed$")
	public void an_error_message_should_be_displayed_that_the_adding_of_programme_failed() {
		assertEquals(response.getResponseMessage(), "Programme is already in the register");
	}
		
	// START COURSE PLANNER STEP
	
	@Given("^adding the course with name \"([^\"]*)\" and course time \"([^\"]*)\" as elective course to a programme$")
	public void adding_the_course_with_name_and_course_time_as_elective_course_to_a_programme(String courseName, String courseTime) {
		response = programme.addElectiveCourseInProgramme(courseRegister.returnCourseByNameAndTime(courseName, courseTime));
	}

	@Given("^adding the course with name \"([^\"]*)\" and course time \"([^\"]*)\" as mandatory course to a programme$")
	public void adding_the_course_with_name_and_course_time_as_mandatory_course_to_a_programme(String courseName, String courseTime) {
		response = programme.addMandatoryCourseInProgramme(courseRegister.returnCourseByNameAndTime(courseName, courseTime));
	}

	@When("^student queries the system the missing courses$")
	public void student_queries_the_system_the_missing_courses() {
		response = student.showMissingCourses();
	}

	@Then("^the message about missing courses should be displayed$")
	public void the_message_about_missing_courses_should_be_displayed() {
		assertTrue(response.getResponseMessage().indexOf("Math1") != -1 && response.getResponseMessage().indexOf("Physics") != -1);
	}
	
	@Then("^the message that all the courses are completed should be displayed$")
	public void the_message_that_all_the_courses_are_completed_should_be_displayed() {
		assertEquals(response.getResponseMessage(), "Student has passed all the mandatory and elective courses");
	}
	
	//START GET PARTICIPATION LIST FEATURE STEPS
	@When("^the teacher wants to save participation list of the course (\\d+)$")
	public void the_teacher_wants_to_save_participation_list_of_the_course_to_the_path(int courseNumber) {
		course = courseRegister.returnCourseByNumber(courseNumber);
	    response = course.generateParticipationList(course);
	}

	@Then("^a response message will appear to show the PDF file has been generated$")
	public void a_response_message_will_appear_to_show_the_PDF_file_has_been_generated() {
		assertEquals(response.getResponseMessage(), "Participants list generated");
	}

	@Then("^a response message will appear to show failure of generating participation list$")
	public void a_response_message_will_appear_to_show_failure_of_generating_participation_list() {
		assertEquals(response.getResponseMessage(), "No participant in the course");
	}
	
	
}

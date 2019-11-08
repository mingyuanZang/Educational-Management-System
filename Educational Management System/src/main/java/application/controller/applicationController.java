package application.controller;

import application.model.Course;
import application.model.CourseRegister;
import application.model.Programme;
import application.model.ProgrammeRegister;
import application.model.Student;
import application.model.StudentRegister;
import application.model.TeacherRegister;
import application.view.CourseInventoryView;
import application.view.ProgrammeInventoryView;
import application.view.StudentInventoryView;
import application.view.TeacherInventoryView;

public class applicationController {

	private SelectionController oneSelectionController;
	private StudentInventoryController studentInventoryController;
	private CourseInventoryController courseInventoryController;
	private TeacherInventoryController teacherInventoryController;
	private ProgrammeInventoryController programmeInventoryController;
	private StudentRegister studentRegister = new StudentRegister();
	private CourseRegister courseRegister = new CourseRegister();
	private TeacherRegister teacherRegister = new TeacherRegister();
	private ProgrammeRegister programmeRegister = new ProgrammeRegister();

	public void manageInventory() {
		studentInventoryController = new StudentInventoryController(studentRegister, courseRegister, programmeRegister,
				this);
		StudentInventoryView studentInvView = new StudentInventoryView(studentInventoryController);
		studentInventoryController.setView(studentInvView);
		studentInventoryController.display();
	}

	public void manageClassInventory() {
		courseInventoryController = new CourseInventoryController(courseRegister, studentRegister, teacherRegister,
				this);
		CourseInventoryView courseInvView = new CourseInventoryView(courseInventoryController);
		courseInventoryController.setView(courseInvView);
		courseInventoryController.display();
	}

	public void manageTeacherInventory() {
		teacherInventoryController = new TeacherInventoryController(teacherRegister, this);
		TeacherInventoryView teacherInvView = new TeacherInventoryView(teacherInventoryController);
		teacherInventoryController.setView(teacherInvView);
		teacherInventoryController.display();
	}

	public void manageProgrammeInventory() {
		programmeInventoryController = new ProgrammeInventoryController(courseRegister, programmeRegister, this);
		ProgrammeInventoryView programmeInvView = new ProgrammeInventoryView(programmeInventoryController);
		programmeInventoryController.setView(programmeInvView);
		programmeInventoryController.display();
	}

	public void launchProgram() {
		oneSelectionController = new SelectionController(this);
		oneSelectionController.display();
	}

	public void recreateStudents() {
		studentInventoryController.display();
	}

	public void recreateClasses() {
		courseInventoryController.display();
	}

	public void recreateTeachers() {
		teacherInventoryController.display();
	}

	public void recreateProgrammes() {
		programmeInventoryController.display();
	}

	public void backToSelectionStudent() {
		studentInventoryController.hide();
		oneSelectionController.display();
	}

	public void backToSelectionCourse() {
		courseInventoryController.hide();
		oneSelectionController.display();
	}

	public void backToSelectionTeacher() {
		teacherInventoryController.hide();
		oneSelectionController.display();
	}

	public void backToSelectionProgramme() {
		programmeInventoryController.hide();
		oneSelectionController.display();
	}

	public void launchStudentDetailsView(Student student) {
		studentInventoryController.hide();
		StudentDetailsController details = new StudentDetailsController(student, this);
		details.display();
	}

	public void launchCourseDetailsView(Course course) {
		courseInventoryController.hide();
		CourseDetailsController classdetails = new CourseDetailsController(course, this); // I'm not sure if this is
																							// necessary again
		classdetails.display();
	}

	public void launchProgrammeDetailsView(Programme programme) {
		programmeInventoryController.hide();
		ProgrammeDetailsController programmedetails = new ProgrammeDetailsController(programme, this);
		programmedetails.display();
	}

	public void launchCourseSearchView() {
		courseInventoryController.hide();
		CourseSearchController searchController = new CourseSearchController(courseRegister, studentRegister, this);
		searchController.display();
	}

	public void launchStudentSearchView() {
		studentInventoryController.hide();
		StudentSearchController studentSearchController = new StudentSearchController(studentRegister, this);
		studentSearchController.display();
	}

	public void launchTeacherSearchView() {
		teacherInventoryController.hide();
		TeacherSearchController teacherSearchController = new TeacherSearchController(teacherRegister, this);
		teacherSearchController.display();
	}

	// entry point of our application
	public static void main(String[] args) {
		applicationController app = new applicationController(); // creates new instance of ApplicationController
		app.launchProgram();
	}

}

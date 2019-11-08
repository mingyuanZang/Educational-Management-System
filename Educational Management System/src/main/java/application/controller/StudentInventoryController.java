package application.controller;
import java.awt.Color;

import javax.swing.JOptionPane;

import application.IOStream.IOStream;
import application.model.Course;
import application.model.CourseRegister;
import application.model.Programme;
import application.model.ProgrammeRegister;
import application.model.ResponseMessage;
import application.model.Student;

//import application.model.Inventory;

import application.model.StudentRegister;
import application.view.StudentInventoryView;


public class StudentInventoryController {
	
	private StudentRegister studentRegister;
	private CourseRegister courseRegister;
	private ProgrammeRegister programmeRegister;
	private StudentInventoryView view;
	private applicationController app;
	private MultiOptionPaneStudentController multiController;
	private MultiOptionPaneCompletedCourseController multiControllerCompletedCourse;
	
	
	public StudentInventoryController(StudentRegister studentRegister, CourseRegister courseRegister, ProgrammeRegister programmeRegister, applicationController app) {
		this.studentRegister = studentRegister;
		this.courseRegister = courseRegister;
		this.programmeRegister = programmeRegister;
		this.app = app;
	}
	
	public void addStudent() {
		this.multiController = new MultiOptionPaneStudentController(studentRegister);
		multiController.addStudent();
		IOStream.outputCollection(studentRegister.getSet(), "StudentSetInStudentRegister.xml");
	}
	
	public void deleteStudent(int selectedRow) {
		Student student = studentRegister.returnStudentFromArrayList(selectedRow);
		studentRegister.deleteStudent(student);
		IOStream.outputCollection(studentRegister.getSet(), "StudentSetInStudentRegister.xml");
	}
	
	public void setView(StudentInventoryView view) {
		this.view = view;
		this.view.setTableModel(studentRegister);
	}

	public void display() {
		view.setVisible(true);
	}


	public void back() {
		app.backToSelectionStudent();
	}

	public void hide() {
		view.setVisible(false);
	}
	
	public void viewStudent(int selectedRow) {
		Student student = studentRegister.returnStudentFromArrayList(selectedRow);
		app.launchStudentDetailsView(student);	
	}

	public void addCompletedCourseToStudent(int selectedRow) {
		Student student = studentRegister.returnStudentFromArrayList(selectedRow);
		this.multiControllerCompletedCourse = new MultiOptionPaneCompletedCourseController(student, courseRegister);
		multiControllerCompletedCourse.addCompletedCourseToStudent();
		IOStream.outputCollection(studentRegister.getSet(), "StudentSetInStudentRegister.xml");
	}
	
	public void viewSearch() {
		app.launchStudentSearchView();	
	}

	public void enrollProgramme(int selectedRow) {
		Student student = studentRegister.returnStudentFromArrayList(selectedRow);
		String searchProgrammeName = JOptionPane.showInputDialog("Enter name of programme to enroll:");
		Programme tempProgramme = programmeRegister.returnProgrammeByName(searchProgrammeName);
		student.enrollProgramme(tempProgramme);
		IOStream.outputCollection(studentRegister.getSet(), "StudentSetInStudentRegister.xml");
	}

}





















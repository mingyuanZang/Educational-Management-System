package application.controller;

import java.util.ArrayList;


import application.model.Student;
import application.model.StudentRegister;
import application.view.StudentSearchView;

public class StudentSearchController {
	
	private StudentSearchView view;
	private StudentRegister studentRegister;
	private applicationController app;
	public ArrayList<Student> searchFirstNameResultsList;
	public ArrayList<Student> searchLastNameResultsList;
	
	public StudentSearchController(StudentRegister studentRegister, applicationController app) {	
		this.studentRegister = studentRegister;
		this.app = app;	
		this.view = new StudentSearchView(this);
	}
	
	public ArrayList<Student> returnSearchFirstNameResultsList(String firstName){
		return studentRegister.returnStudentByFirstName(firstName);
	}
	
	public ArrayList<Student> returnSearchLastNameResultsList(String lastName){
		return studentRegister.returnStudentByLastName(lastName);
	}
	
	public Student returnSearchNumberResult(int studentNumber){
		return studentRegister.returnStudentByNumber(studentNumber);
	}
		
	public void backtoStudentList() {
		view.setVisible(false);
		app.recreateStudents();	
	}
	
	public void display() {
		view.setVisible(true);
	}

	
}

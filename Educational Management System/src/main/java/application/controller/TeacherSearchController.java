package application.controller;

import java.util.ArrayList;

import application.model.Person;
import application.model.Teacher;
import application.model.TeacherRegister;
import application.view.TeacherSearchView;

public class TeacherSearchController {

	private TeacherRegister teacherRegister;
	private applicationController app;
	private TeacherSearchView view;

	public TeacherSearchController(TeacherRegister teacherRegister, applicationController app) {
		this.teacherRegister = teacherRegister;
		this.app = app;	
		this.view = new TeacherSearchView(this);
	}
	
	public ArrayList<Teacher> returnSearchByFirstNameResultsList(String firstName){
		return teacherRegister.returnTeacherByFirstName(firstName);
	}
	
	public ArrayList<Teacher> returnSearchByLastNameResultsList(String lastName){
		return teacherRegister.returnTeacherByLastName(lastName);
	}
	
	public void backtoTeacherList() {
		view.setVisible(false);
		app.recreateTeachers();	
	}

	public Person returnSearchByEmailResult(String Email) {
		return teacherRegister.returnTeacherByEmail(Email);
	}
	
	public Person returnSearchBySerialNumberResult(int serialNumber) {
		return teacherRegister.returnTeacherBySerialNumber(serialNumber);
	}
	
	public void display() {
		view.setVisible(true);
	}


}

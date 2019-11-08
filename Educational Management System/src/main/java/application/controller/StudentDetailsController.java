package application.controller;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import application.model.Course;
import application.model.Student;
import application.model.StudentRegister;
import application.view.StudentDetailsView;

public class StudentDetailsController {
	

	//private Session sessionModel;
	private StudentDetailsView view;
	private applicationController app;
	public ArrayList<Course> passedCourseList;
	public ArrayList<Course> missingCourseList;
	//public ArrayList<Course> currentCourseList;
	private Student student;
	
//MUST BE CHANGED FOR REAL MODEL
	public StudentDetailsController(Student student, applicationController app) {
		this.student = student;
		this.app = app;
		passedCourseList = new ArrayList<Course>(student.getPassedCourseList());//Should come from actual model
		student.showMissingCourses();
		missingCourseList = new ArrayList<Course>(student.getMissingCourseList());
		//currentCourseList = new ArrayList<Course>(student.getClass());
		this.view = new StudentDetailsView(this);
	}
	
	public String returnProgrammeName() {
		if (student.getProgramme() == null) {
			return "No programme";
		} else {
			return student.getProgramme().getProgrammeName();
		}

	}
	
	public void backtoStudentList() {
		view.setVisible(false);
		app.recreateStudents();
	}
	
	public void display() {
		view.setVisible(true);
	}

	public Student getStudent() {
		return student;
	}

}

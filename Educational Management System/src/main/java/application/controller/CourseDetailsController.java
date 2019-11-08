package application.controller;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import application.model.Course;
import application.model.CourseRegister;
import application.model.Student;
import application.model.Teacher;
import application.view.CourseDetailsView;

public class CourseDetailsController {
		
		//private Session sessionModel;
		private CourseDetailsView view;
		private applicationController app;
		public ArrayList<Student> classList;
		public ArrayList<Teacher> teacherList;
		public ArrayList<Course> prereqList;
		public ArrayList<Student> teacherAssistantList;
		private Course course;
		
	//MUST BE CHANGED FOR REAL MODEL
		public CourseDetailsController(Course course, applicationController app) {
			this.course = course;
			this.app = app;
			classList = new ArrayList<Student>(course.getClassList());
			teacherList = new ArrayList<Teacher>(course.getTeacherList());
			prereqList = new ArrayList<Course>(course.getPrerequisites());
			teacherAssistantList = new ArrayList<Student>(course.getTeacherAssistantList());
			this.view = new CourseDetailsView(this);

		}
		public void backtoClassList() {
			view.setVisible(false);
			app.recreateClasses();
			
		}
		public void display() {
			view.setVisible(true);
			
		}

	}

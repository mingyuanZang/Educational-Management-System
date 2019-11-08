package application.controller;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import application.IOStream.IOStream;
import application.model.Course;
import application.model.CourseRegister;
import application.model.Person;
import application.model.ResponseMessage;
import application.model.Student;
import application.model.StudentRegister;
import application.model.Teacher;
import application.model.TeacherRegister;
import application.view.CourseInventoryView;

//import application.model.Inventory;
//import application.model.Session;

public class CourseInventoryController {

	private CourseRegister courseRegister;
	private CourseInventoryView view;
	private applicationController app;
	private MultiOptionPaneCourseController multiController;
	private StudentRegister studentRegister;
	private TeacherRegister teacherRegister;

	public CourseInventoryController(CourseRegister courseRegister, StudentRegister studentRegister,
			TeacherRegister teacherRegister, applicationController app) {
		this.courseRegister = courseRegister;
		this.studentRegister = studentRegister;
		this.teacherRegister = teacherRegister;
		this.app = app;
	}

	public void addCourse() {
		this.multiController = new MultiOptionPaneCourseController(courseRegister);
		multiController.addClass();
		IOStream.outputCollection(courseRegister.getSet(), "CourseSetInCourseRegister.xml");
	}
	
	public void deleteCourse(int selectedRow) {
		Course course = courseRegister.returnCourseFromArrayList(selectedRow);
		courseRegister.deleteCourse(course);
		IOStream.outputCollection(courseRegister.getSet(), "CourseSetInCourseRegister.xml");
	}

	public void setView(CourseInventoryView view) {
		this.view = view;
		this.view.setTableModel(courseRegister);
	}

	public void display() {
		view.setVisible(true);
	}

	public void back() {
		app.backToSelectionCourse();
	}

	public void hide() {
		view.setVisible(false);
	}

	public void viewClass(int selectedRow) {
		app.launchCourseDetailsView(courseRegister.returnCourseFromArrayList(selectedRow));
	}

	public void enrollSomeStudent(int selectedRow) {
		Course courseToEnrollIn = courseRegister.returnCourseFromArrayList(selectedRow);
		int searchStudNumber = Integer
				.parseInt(JOptionPane.showInputDialog("Enter number of a student to be enrolled:"));
		Student tempStudent = studentRegister.returnStudentByNumber(searchStudNumber);
		if (tempStudent == null) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Incorrect input!", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			ResponseMessage response = tempStudent.enrollCourse(courseToEnrollIn);
			if (response.getResponseMessage().equals("Student added")) {
				courseToEnrollIn.addStudent(tempStudent);
				IOStream.outputCollection(courseRegister.getSet(), "CourseSetInCourseRegister.xml");
			}
		}
	}

	public void addPrerequisite(int selectedRow) {
		Course courseGettingPrerequisite = courseRegister.returnCourseFromArrayList(selectedRow);
		int searchPrereqNumber = Integer
				.parseInt(JOptionPane.showInputDialog("Enter course number of prerequisite course:"));
		Course courseToAddAsPrerequisite = courseRegister.returnCourseByNumber(searchPrereqNumber);
		if (courseToAddAsPrerequisite == null) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Incorrect input!", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			courseGettingPrerequisite.addCourseToPrerequisites(courseToAddAsPrerequisite);
			IOStream.outputCollection(courseRegister.getSet(), "CourseSetInCourseRegister.xml");
		}
	}

	public void addTeacher(int selectedRow) {
		Course courseGettingTeacher = courseRegister.returnCourseFromArrayList(selectedRow);
		int searchTeachNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter teacher serial number:"));
		Teacher teacher = teacherRegister.returnTeacherBySerialNumber(searchTeachNumber);
		if (teacher == null) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Incorrect input!", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			courseGettingTeacher.addTeacher(teacher);
			IOStream.outputCollection(courseRegister.getSet(), "CourseSetInCourseRegister.xml");
		}	
	}
	
	public void deleteTeacher(int selectedRow) {
		Course courseGettingTeacher = courseRegister.returnCourseFromArrayList(selectedRow);
		int searchTeachNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter teacher serial number:"));
		Person teacher = teacherRegister.returnTeacherBySerialNumber(searchTeachNumber);
		if (teacher == null) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Incorrect input!", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			courseGettingTeacher.deleteTeacher(teacher);
			IOStream.outputCollection(courseRegister.getSet(), "CourseSetInCourseRegister.xml");
		}
	}

	public void addStudentTeacher(int selectedRow) {
		Course courseGettingTA = courseRegister.returnCourseFromArrayList(selectedRow);
		int serachStudentTeachNumber = Integer
				.parseInt(JOptionPane.showInputDialog("Enter student number for desired student teacher:"));
		Student teacherAssistant = studentRegister.returnStudentByNumber(serachStudentTeachNumber);
		if (teacherAssistant == null) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Incorrect input!", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			courseGettingTA.addTeacherAssistant(teacherAssistant, courseGettingTA);
			IOStream.outputCollection(courseRegister.getSet(), "CourseSetInCourseRegister.xml");
		}
	}

	public void deleteStudentTeacher(int selectedRow) {
		Course courseDeletingTA = courseRegister.returnCourseFromArrayList(selectedRow);
		int serachStudentTeachNumber = Integer
				.parseInt(JOptionPane.showInputDialog("Enter student number for student teacher to delete:"));
		Student teacherAssistant = studentRegister.returnStudentByNumber(serachStudentTeachNumber);
		if (teacherAssistant == null) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Incorrect input!", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			courseDeletingTA.deleteTeacherAssistant(teacherAssistant);
			IOStream.outputCollection(courseRegister.getSet(), "CourseSetInCourseRegister.xml");
		}
	}

	public void viewSearch() {
		app.launchCourseSearchView();
	}

	public void generateParticipantList(int selectedRow) {
		Course course = courseRegister.returnCourseFromArrayList(selectedRow);
		course.generateParticipationList(course);
	}

}











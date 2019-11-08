package application.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import application.model.Course;
import application.model.CourseRegister;
import application.model.Student;
import application.model.StudentRegister;
import application.view.MultiOptionPaneCompletedCourse;

public class MultiOptionPaneCompletedCourseController {
	
	private MultiOptionPaneCompletedCourse view;
	Student student;
	CourseRegister courseRegister;
	int grade;
	
	public MultiOptionPaneCompletedCourseController(Student student, CourseRegister courseRegister) {
		this.student = student;
		this.courseRegister = courseRegister;
	}

	public void addCompletedCourseToStudent() {
		this.view = new MultiOptionPaneCompletedCourse();
		this.grade = this.view.getGrade();
		int courseNumber = this.view.getCourseNumber();
		Course completedCourse = courseRegister.returnCourseByNumber(courseNumber);
		if (completedCourse == null && courseNumber != 0) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Course not found", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			if (!(student.getPassedCourseList().contains(completedCourse))) {
				student.addGrade(this.view.getCourseNumber(), grade, completedCourse);
			}
		}
		
	}
	
}



















package application.controller;

import application.model.Course;
import application.model.CourseRegister;
import application.view.MultiOptionPaneCourse;

public class MultiOptionPaneCourseController {
	private MultiOptionPaneCourse view;
	private CourseRegister courseRegister;

	public MultiOptionPaneCourseController(CourseRegister courseRegister) {
		this.courseRegister = courseRegister;
	}

	public void addClass() {
		Course tempClass = FindCoursetoAdd();
		if (tempClass != null) {
			courseRegister.adding(tempClass);
			courseRegister.addCourseToTable(tempClass);
		} else {
			// class was never added
		}
	}

	private Course FindCoursetoAdd() {
		this.view = new MultiOptionPaneCourse();
		return view.getNewCourse();
	}
}
package application.controller;

import application.model.Teacher;
import application.model.TeacherRegister;
import application.view.MultiOptionPaneTeacher;

public class MultiOptionPaneTeacherController {

	private MultiOptionPaneTeacher view;
	private TeacherRegister teacherRegister;

	public MultiOptionPaneTeacherController(TeacherRegister teacherRegister) {
		this.teacherRegister = teacherRegister;
	}

	public void addTeacher() {
		Teacher newTeacher = FindTeachertoAdd();
		if (newTeacher != null) {
			teacherRegister.signUp(newTeacher);
			teacherRegister.addTeacherToTable(newTeacher);
		} else {
			// teacher was never added
		}
	}

	private Teacher FindTeachertoAdd() {
		this.view = new MultiOptionPaneTeacher();
		return view.getNewTeacher();
	}
}
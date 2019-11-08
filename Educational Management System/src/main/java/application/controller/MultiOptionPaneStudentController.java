package application.controller;

import application.model.*;
import application.view.MultiOptionPaneStudent;

public class MultiOptionPaneStudentController {

	private MultiOptionPaneStudent view;
	private StudentRegister studentRegister;

	public MultiOptionPaneStudentController(StudentRegister studentRegister) {
		this.studentRegister = studentRegister;
	}

	public void addStudent() {
		Student tempStudent = FindStudenttoAdd();
		if (tempStudent != null) {
			studentRegister.signUp(tempStudent);
			studentRegister.addStudentToTable(tempStudent);
		} else {
			// student was never added
		}
	}

	private Student FindStudenttoAdd() {
		this.view = new MultiOptionPaneStudent();
		return view.getNewStudent();
	}

}

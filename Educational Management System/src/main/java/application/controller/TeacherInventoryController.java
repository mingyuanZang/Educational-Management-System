package application.controller;

import javax.swing.JOptionPane;

import application.IOStream.IOStream;
import application.model.Person;
import application.model.Teacher;
import application.model.TeacherRegister;
import application.view.TeacherInventoryView;

public class TeacherInventoryController {

	private TeacherRegister teacherRegister;
	private MultiOptionPaneTeacherController multiController;
	private TeacherInventoryView view;
	private applicationController app;

	public TeacherInventoryController(TeacherRegister teacherRegister, applicationController app) {
		this.teacherRegister = teacherRegister;
		this.app = app;
	}

	public void addTeacher() {
		this.multiController = new MultiOptionPaneTeacherController(teacherRegister);
		multiController.addTeacher();
		IOStream.outputCollection(teacherRegister.getSet(), "TeacherSetInTeacherRegister.xml");
	}

	public void deleteTeacher(int selectedRow) {
		Teacher teacher = teacherRegister.returnTeacherFromArrayList(selectedRow);
		teacherRegister.deleteTeacher(teacher);
		IOStream.outputCollection(teacherRegister.getSet(), "TeacherSetInTeacherRegister.xml");
	}

	public void setView(TeacherInventoryView view) {
		this.view = view;
		this.view.setTableModel(teacherRegister);
	}

	public void display() {
		view.setVisible(true);
	}

	public void back() {
		app.backToSelectionTeacher();
	}

	public void hide() {
		view.setVisible(false);
	}

	public void viewSearch() {
		app.launchTeacherSearchView();
	}

}

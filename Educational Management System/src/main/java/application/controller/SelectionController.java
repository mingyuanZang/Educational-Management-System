package application.controller;

import application.view.SelectionView;

public class SelectionController {

	private applicationController application;
	// private Session session;
	private SelectionView view;

	public SelectionController(applicationController application) {
		this.application = application; // set the application equal to application controller
		// this.session = new Session();
		this.view = new SelectionView(this);
	}

	// this is executed when student button is clicked
	public void launchStudents() {
		view.setVisible(false);
		application.manageInventory();
	}

	// this is executed when class button is clicked
	public void launchClasses() {
		view.setVisible(false);
		application.manageClassInventory();
	}

	public void launchTeachers() {
		view.setVisible(false);
		application.manageTeacherInventory();
	}
	
	public void launchProgrammes() {
		view.setVisible(false);
		application.manageProgrammeInventory();
	}

	public void display() {
		view.setVisible(true);
	}

	public void relaunchStudents() {
		view.setVisible(false);
		application.recreateStudents();
	}

	public void relaunchClasses() {
		view.setVisible(false);
		application.recreateClasses();
	}

	public void relaunchTeachers() {
		view.setVisible(false);
		application.recreateTeachers();
	}
	
	public void relaunchProgrammes() {
		view.setVisible(false);
		application.recreateProgrammes();
	}
	
}




















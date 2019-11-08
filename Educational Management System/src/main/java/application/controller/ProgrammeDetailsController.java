package application.controller;

import java.util.ArrayList;

import application.model.Course;
import application.model.Programme;
import application.view.ProgrammeDetailsView;

public class ProgrammeDetailsController {

	private ProgrammeDetailsView view;
	private applicationController app;
	private Programme programme;
	public ArrayList<Course> mandatoryCourses;
	public ArrayList<Course> electiveCourses;
	
	public ProgrammeDetailsController(Programme programme, applicationController app) {
		this.programme = programme;
		this.app = app;
		mandatoryCourses = new ArrayList<Course>(programme.getMandatoryCourses());
		electiveCourses = new ArrayList<Course>(programme.getElectiveCourses());
		this.view = new ProgrammeDetailsView(this);

	}
	public void backtoProgrammeList() {
		view.setVisible(false);
		app.recreateProgrammes();
		
	}
	public void display() {
		view.setVisible(true);
		
	}
	
}

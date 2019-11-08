package application.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import application.IOStream.IOStream;
import application.model.Course;
import application.model.CourseRegister;
import application.model.Programme;
import application.model.ProgrammeRegister;
import application.model.Student;
import application.model.Teacher;
import application.view.ProgrammeInventoryView;

public class ProgrammeInventoryController {
	
	private ProgrammeRegister programmeRegister;
	private Programme programme;
	private CourseRegister courseRegister;
	private MultiOptionPaneProgrammeController multiController;
	private applicationController app;
	private ProgrammeInventoryView view;
	
	public ProgrammeInventoryController(CourseRegister courseRegister, ProgrammeRegister programmeRegister, applicationController app) {
		this.app = app;
		this.programmeRegister = programmeRegister;
		this.courseRegister = courseRegister;
	}
	
	public void setView(ProgrammeInventoryView view) {
		this.view = view;
		this.view.setTableModel(programmeRegister);
	}

	public void display() {
		view.setVisible(true);
	}

	public void back() {
		app.backToSelectionProgramme();
	}

	public void hide() {
		view.setVisible(false);
	}

	public void viewProgramme(int selectedRow) {
		app.launchProgrammeDetailsView(programmeRegister.returnProgrammeFromArrayList(selectedRow));
	}

	public void addProgramme() {
		this.multiController = new MultiOptionPaneProgrammeController(programmeRegister);
		multiController.addProgramme();
		IOStream.outputCollection(programmeRegister.getSet(), "ProgrammeSetInProgrammeRegister.xml");
	}

	public void deleteProgramme(int selectedRow) {
		Programme programme = programmeRegister.returnProgrammeFromArrayList(selectedRow);
		programmeRegister.deleteProgramme(programme);
		IOStream.outputCollection(programmeRegister.getSet(), "ProgrammeSetInProgrammeRegister.xml");
	}
	
	public void addMandatoryCourse(int selectedRow) {
		Programme programme = programmeRegister.returnProgrammeFromArrayList(selectedRow);	
		int mandatoryCourseNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter course number of mandatory course:"));
		Course courseToAdd = courseRegister.returnCourseByNumber(mandatoryCourseNumber);
		if (courseToAdd == null) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Incorrect input!", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			programme.addMandatoryCourseInProgramme(courseToAdd);
			IOStream.outputCollection(programmeRegister.getSet(), "ProgrammeSetInProgrammeRegister.xml");
		}
	}
	
	public void addElectiveCourse(int selectedRow) {
		Programme programme = programmeRegister.returnProgrammeFromArrayList(selectedRow);	
		int electiveCourseNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter course number of elective course:"));
		Course courseToAdd = courseRegister.returnCourseByNumber(electiveCourseNumber);
		if (courseToAdd == null) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Incorrect input!", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			programme.addElectiveCourseInProgramme(courseToAdd);
			IOStream.outputCollection(programmeRegister.getSet(), "ProgrammeSetInProgrammeRegister.xml");
		}
	}
	
}


















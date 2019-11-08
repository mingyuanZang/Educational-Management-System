package application.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
//import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;
//import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
//import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import application.controller.CourseInventoryController;


public class CourseInventoryView extends JFrame {

	private static final long serialVersionUID = 1L;
	private CourseInventoryController controller; // keep reference to controller
	private JTable tblInventory;

	public CourseInventoryView(CourseInventoryController controller) {
		this.controller = controller;
		initGUI();
	}

	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Class List");
		setPreferredSize(new Dimension(1100, 600));
		

		// buttons
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.back();
			}
		});

		JButton btnNew = new JButton("Add Course");
		btnNew.addActionListener(new ActionListener() {
			@Override // only once because only use it here, could also create own class
			public void actionPerformed(ActionEvent e) {
				controller.addCourse();
			}
		});

		JButton btnDelete = new JButton("Remove Selected Course");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.deleteCourse(tblInventory.getSelectedRow());
			}
		});

		JButton btnEnrollStudent = new JButton("Enroll Student");
		btnEnrollStudent.setEnabled(false);
		btnEnrollStudent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.enrollSomeStudent(tblInventory.getSelectedRow());
			}
		});
		
		JButton btnParticipantList = new JButton("Participant List");
		btnParticipantList.setEnabled(false);
		btnParticipantList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.generateParticipantList(tblInventory.getSelectedRow());
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "Participation list generated", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		JButton btnAddPrerequisites = new JButton("Add Prerequisite");
		btnAddPrerequisites.setEnabled(false);
		btnAddPrerequisites.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.addPrerequisite(tblInventory.getSelectedRow());
			}
		});

		JButton btnResponsibleTeacher = new JButton("Add Teacher");
		btnResponsibleTeacher.setEnabled(false);
		btnResponsibleTeacher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.addTeacher(tblInventory.getSelectedRow());
			}
		});
		
		JButton btnRemoveResponsibleTeacher = new JButton("Remove Teacher");
		btnRemoveResponsibleTeacher.setEnabled(false);
		btnRemoveResponsibleTeacher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.deleteTeacher(tblInventory.getSelectedRow());
			}
		});

		JButton btnStudentTeacher = new JButton("Add Student Teacher");
		btnStudentTeacher.setEnabled(false);
		btnStudentTeacher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.addStudentTeacher(tblInventory.getSelectedRow());
			}
		});
		
		JButton btnRemoveStudentTeacher = new JButton("Remove Student Teacher");
		btnRemoveStudentTeacher.setEnabled(false);
		btnRemoveStudentTeacher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.deleteStudentTeacher(tblInventory.getSelectedRow());
			}
		});
		
		JButton btnView = new JButton("Detail View");
		btnView.setEnabled(false);
		btnView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.viewClass(tblInventory.getSelectedRow());
			}
		});

		JButton btnSearch = new JButton("Search");
		btnSearch.setEnabled(true);
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.viewSearch();
			}
		});
		

		// toolbar

		JToolBar toolbar = new JToolBar();
		toolbar.add(btnBack);
		toolbar.add(btnNew);
		toolbar.add(btnDelete);
		toolbar.add(btnEnrollStudent);
		toolbar.add(btnParticipantList);
		toolbar.add(btnResponsibleTeacher);
		toolbar.add(btnRemoveResponsibleTeacher);
		toolbar.add(btnStudentTeacher);
		toolbar.add(btnRemoveStudentTeacher);
		toolbar.add(btnAddPrerequisites);
		toolbar.add(btnView);
		toolbar.add(btnSearch);
		toolbar.add(Box.createHorizontalGlue());
		add(toolbar, BorderLayout.NORTH);

		// table
		tblInventory = new JTable();
		tblInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tblInventory.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnView.setEnabled((tblInventory.getSelectedRow() >= 0));
				btnDelete.setEnabled((tblInventory.getSelectedRow() >= 0));
				btnEnrollStudent.setEnabled((tblInventory.getSelectedRow() >= 0));
				btnAddPrerequisites.setEnabled((tblInventory.getSelectedRow() >= 0));
				btnResponsibleTeacher.setEnabled((tblInventory.getSelectedRow() >= 0));
				btnRemoveResponsibleTeacher.setEnabled((tblInventory.getSelectedRow() >= 0));
				btnStudentTeacher.setEnabled((tblInventory.getSelectedRow() >= 0));
				btnRemoveStudentTeacher.setEnabled((tblInventory.getSelectedRow() >= 0));
				btnParticipantList.setEnabled((tblInventory.getSelectedRow() >= 0));
			}
		});
		
		add(new JScrollPane(tblInventory), BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);
	}

	public void setTableModel(TableModel model) {
		tblInventory.setModel(model);
	}

}

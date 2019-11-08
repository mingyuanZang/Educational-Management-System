package application.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import application.controller.ProgrammeInventoryController;

public class ProgrammeInventoryView extends JFrame {

	private static final long serialVersionUID = 1L;

	private ProgrammeInventoryController controller;
	private JTable tblInventory;
	private JLabel lblSession;

	public ProgrammeInventoryView(ProgrammeInventoryController controller) {
		this.controller = controller;
		initGUI();
	}

	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Programme List");
		setPreferredSize(new Dimension(800, 600));

		// buttons
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.back();
			}
		});

		JButton btnNew = new JButton("Add Programme");
		btnNew.addActionListener(new ActionListener() {
			@Override // only once because only use it here, could also create own class
			public void actionPerformed(ActionEvent e) {
				controller.addProgramme();
			}
		});
		
		JButton btnDelete = new JButton("Remove Selected Programme");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.deleteProgramme(tblInventory.getSelectedRow());
			}
		});

		JButton btnAddMandatoryCourse = new JButton("Add Mandatory Course");
		btnAddMandatoryCourse.setEnabled(false);
		btnAddMandatoryCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.addMandatoryCourse(tblInventory.getSelectedRow());
			}
		});

		JButton btnAddElectiveCourse = new JButton("Add Elective Course");
		btnAddElectiveCourse.setEnabled(false);
		btnAddElectiveCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.addElectiveCourse(tblInventory.getSelectedRow());
			}
		});

		JButton btnView = new JButton("Detail View");
		btnView.setEnabled(false);
		btnView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.viewProgramme(tblInventory.getSelectedRow());
			}
		});

		// toolbar

		JToolBar toolbar = new JToolBar();
		toolbar.add(btnBack);
		toolbar.add(btnNew);
		toolbar.add(btnDelete);
		toolbar.add(btnAddMandatoryCourse);
		toolbar.add(btnAddElectiveCourse);
		toolbar.add(btnView);
		toolbar.add(Box.createHorizontalGlue());
		add(toolbar, BorderLayout.NORTH);

		// table
		
		tblInventory = new JTable();
		tblInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblInventory.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnView.setEnabled((tblInventory.getSelectedRow() >= 0));
				btnDelete.setEnabled((tblInventory.getSelectedRow() >= 0)); //set to enabled
				// if there is at least one selected row (gives you index of selected row)
				btnAddMandatoryCourse.setEnabled((tblInventory.getSelectedRow() >= 0));
				btnAddElectiveCourse.setEnabled((tblInventory.getSelectedRow() >= 0));
			}
		});

		add(new JScrollPane(tblInventory), BorderLayout.CENTER); // JScrollPane means that if you have more than you can
																	// see a scroll bar appears

		pack();
		setLocationRelativeTo(null);
	}

	public void setTableModel(TableModel model) {
		tblInventory.setModel(model);
	}

}

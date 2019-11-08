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

import application.controller.TeacherInventoryController;

public class TeacherInventoryView extends JFrame{

	private TeacherInventoryController controller; //keep reference to controller
	private JTable tblInventory;
	private JLabel lblSession;
	
	public TeacherInventoryView(TeacherInventoryController controller) {
		this.controller = controller;
		initGUI();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Teacher List");
		setPreferredSize(new Dimension(800, 600));
		
		// buttons
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.back();
			}
		});
		
		JButton btnNew = new JButton("Add Teacher");
		btnNew.addActionListener(new ActionListener() {
			@Override //only once because only use it here, could also create own class
			public void actionPerformed(ActionEvent e) {
				controller.addTeacher();
			}
		});
		
		JButton btnDelete = new JButton("Remove Selected Teacher");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.deleteTeacher(tblInventory.getSelectedRow());
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
		toolbar.add(btnSearch);
		toolbar.add(Box.createHorizontalGlue());
		add(toolbar, BorderLayout.NORTH);
		
		// table
		tblInventory = new JTable();
		tblInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblInventory.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnDelete.setEnabled((tblInventory.getSelectedRow() >= 0));
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

package application.view;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import application.controller.SelectionController;
//import application.controller.LoginController;
//import application.controller.LoginController;
import application.utils.GridBagLayoutUtils;

public class SelectionView extends JFrame {

	// what is this?
	private static final long serialVersionUID = 8981053836072595592L;

	// Create boolean to only make new table on first click
	private boolean firstClick = true;
	private boolean teacherfirstClick = true;
	private boolean firstClickClass = true;
	private boolean firstClickProgramme = true;
	// 3 graphical widgets
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private SelectionController controller;
	// keep reference to controller
	// private LoginController controller;

	// constructor of view delegates the initialisations of the GUI
	public SelectionView(SelectionController controller) {
		this.controller = controller;
		initGUI();
	}

	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // set to fixed dimension
		setTitle("Panel Selection");
		setLayout(new GridBagLayout()); // recommended
		
		btn1 = new JButton("Students"); // 15 = num of inputs you can provide
		btn1.addActionListener(new ActionListener() {
			@Override // used anonymous classes because this is done only once, is waiting for clicks
			public void actionPerformed(ActionEvent e) {
				// StudentInventory studInvView = new StudentInventory();
				if (firstClick) {
					firstClick = !firstClick;
					controller.launchStudents();
				} else {
					controller.relaunchStudents();
				}
				// setVisible(false);
				// studInvView.InventoryView(new StudentInventoryController());
				// studInvView.setVisible(true); //will eventually be controller.allthisstuff
			}
		});
		
		btn2 = new JButton("Teachers");
		btn2.addActionListener(new ActionListener() {
			@Override // used anonymous classes because this is done only once, is waiting for clicks
			public void actionPerformed(ActionEvent e) {
				if (teacherfirstClick) {
					teacherfirstClick = !teacherfirstClick;
					controller.launchTeachers();
				} else {
					controller.relaunchTeachers();
				}
			}
		});
		
		btn3 = new JButton("Courses");
		btn3.addActionListener(new ActionListener() {
			@Override // used anonymous classes because this is done only once, is waiting for clicks
			public void actionPerformed(ActionEvent e) {
				if (firstClickClass) {
					firstClickClass = !firstClickClass;
					controller.launchClasses();
				} else {
					controller.relaunchClasses();
				}
			}
		});
		
		btn4 = new JButton("Programmes");
		btn4.addActionListener(new ActionListener() {
			@Override // used anonymous classes because this is done only once, is waiting for clicks
			public void actionPerformed(ActionEvent e) {
				if (firstClickProgramme) {
					firstClickProgramme = !firstClickProgramme;
					controller.launchProgrammes();
				} else {
					controller.relaunchProgrammes();
				}
			}
		});

		add(btn1, GridBagLayoutUtils.constraint(1, 0, 5));
		add(btn2, GridBagLayoutUtils.constraint(1, 1, 5)); // variable names are usually preceeded by 3 or 4 letters
		add(btn3, GridBagLayoutUtils.constraint(1, 2, 5));
		add(btn4, GridBagLayoutUtils.constraint(1, 3, 5));
		
		pack();
		setLocationRelativeTo(null);
	}
}


















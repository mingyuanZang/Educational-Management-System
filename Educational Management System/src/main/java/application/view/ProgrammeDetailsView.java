package application.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import application.controller.ProgrammeDetailsController;
import application.utils.GridBagLayoutUtils;

public class ProgrammeDetailsView extends JFrame {

	private static final long serialVersionUID = 8981053836072595592L;

	// graphical widgets
	private JButton btnBack;
	private JList mandatoryCoursesList;
	private JList electiveCoursesList;

	// keep reference to controller
	private ProgrammeDetailsController controller;

	// constructor of view delegates the initializations of the GUI
	public ProgrammeDetailsView(ProgrammeDetailsController controller) {
		this.controller = controller;
		initGUI();
	}

	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // set to fixed dimension
		setTitle("Detailed View");
		setLayout(new GridBagLayout()); // reccommended
		GridBagConstraints gbc = new GridBagConstraints();

		btnBack = new JButton("Back to List");
		btnBack.addActionListener(new ActionListener() {
			@Override // used anonymous classes because this is done only once, is waiting for clicks
			public void actionPerformed(ActionEvent e) {
				controller.backtoProgrammeList();
			}
		});

		JLabel manCour = new JLabel("Mandatory Courses");
		GridBagConstraints lgbc = new GridBagConstraints();
		lgbc.gridx = 0;
		lgbc.gridy = 0;
		lgbc.insets = new Insets(20, 0, 0, 0);
		add(manCour, lgbc);

		JLabel elecCour = new JLabel("Elective Courses");
		lgbc.gridx = 1;
		lgbc.gridy = 0;
		lgbc.insets = new Insets(20, 0, 0, 0);
		add(elecCour, lgbc);

		final JScrollPane sp = new JScrollPane();
		mandatoryCoursesList = new JList<>(controller.mandatoryCourses.toArray());
		sp.getViewport().add(mandatoryCoursesList);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 2;
		gbc.ipady = 20;
		gbc.ipadx = 20;
		gbc.insets = new Insets(0, 10, 10, 10);
		sp.setPreferredSize(new Dimension(100, 200));
		add(sp, gbc);

		final JScrollPane sp2 = new JScrollPane();
		electiveCoursesList = new JList<>(controller.electiveCourses.toArray());
		sp2.getViewport().add(electiveCoursesList);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 2;
		gbc.ipady = 20;
		gbc.ipadx = 20;
		gbc.insets = new Insets(0, 10, 10, 10);
		sp2.setPreferredSize(new Dimension(100, 200));
		add(sp2, gbc);

		add(btnBack, GridBagLayoutUtils.constraint(6, 6, 5));

		pack();
		setLocationRelativeTo(null);
	}

}

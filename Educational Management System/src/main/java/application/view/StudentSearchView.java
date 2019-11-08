package application.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import application.controller.StudentSearchController;
import application.model.Course;
import application.model.Student;

public class StudentSearchView extends JFrame {

	private static final long serialVersionUID = 8846245931063149908L;

	// graphical widgets
	private JButton btnBack;
	private JList searchFirstNameResultsList;
	private JList searchLastNameResultsList;
	private JTextField searchBar;

	// keep reference to controller
	private StudentSearchController controller;

	// constructor of view delegates the initializations of the GUI
	public StudentSearchView(StudentSearchController controller) {
		this.controller = controller;
		initGUI();
	}

	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // set to fixed dimension
		setTitle("Search");
		setLayout(new GridBagLayout()); // recommended
		GridBagConstraints gbc = new GridBagConstraints();

		btnBack = new JButton("Back to List");
		btnBack.addActionListener(new ActionListener() {
			@Override // used anonymous classes because this is done only once, is waiting for clicks
			public void actionPerformed(ActionEvent e) {
				controller.backtoStudentList();
			}
		});
		
		final JScrollPane sp = new JScrollPane();
		searchBar = new JTextField(15);
		
		JButton btnSearchFirstName = new JButton("Search By First Name");
		btnSearchFirstName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (searchBar.getText().isEmpty()) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Cannot search, no first name provided.", "Empty Search Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					searchFirstNameResultsList = new JList<>(controller.returnSearchFirstNameResultsList(searchBar.getText()).toArray());
					sp.getViewport().add(searchFirstNameResultsList);
					sp.revalidate();
					sp.repaint();
				}
			}
		});

		JButton btnSearchLastName = new JButton("Search By Last Name");
		btnSearchLastName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (searchBar.getText().isEmpty()) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Cannot search, no last name provided.", "Empty Search Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					searchLastNameResultsList = new JList<>(controller.returnSearchLastNameResultsList(searchBar.getText()).toArray());
					sp.getViewport().add(searchLastNameResultsList);
					sp.revalidate();
					sp.repaint();
				}
			}
		});
		
		JButton btnSearchNumber = new JButton("Search By Student Number");
		btnSearchNumber.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (searchBar.getText().isEmpty()) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Cannot search, no number provided.", "Empty Search Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Student searchNumberResult = controller.returnSearchNumberResult(Integer.parseInt(searchBar.getText()));
					ArrayList<String> arrayList = new ArrayList<String>();
					arrayList.add(searchNumberResult.getFirstName() + " " + searchNumberResult.getLastName());
					JList list = new JList<>(arrayList.toArray());
					sp.getViewport().add(list);
					sp.revalidate();
					sp.repaint();
				}
			}
		});
		
		gbc.insets = new Insets(10, 10, 10, 10);

		JLabel searchLabel = new JLabel("Search: ");
		add(searchLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		add(searchBar, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		add(btnBack, gbc);

		JLabel results = new JLabel("Search Results");
		GridBagConstraints lgbc = new GridBagConstraints();
		lgbc.gridx = 1;
		lgbc.gridy = 1;
		lgbc.insets = new Insets(20, 10, 10, 0);
		add(results, lgbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 10, 10, 10);
		sp.setPreferredSize(new Dimension(200, 200));
		add(sp, gbc);		//Add JScrollPane
		
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		GridBagConstraints bpgbc = new GridBagConstraints();
		
		bpgbc.gridx = 0;
		bpgbc.gridy = 0;
		bpgbc.insets = new Insets(10, 10, 10, 10);
		buttonPanel.add(btnSearchFirstName, bpgbc);
		
		bpgbc.gridx = 0;
		bpgbc.gridy = 1;
		bpgbc.insets = new Insets(10, 10, 10, 10);
		buttonPanel.add(btnSearchLastName, bpgbc);

		bpgbc.gridx = 0;
		bpgbc.gridy = 2;
		bpgbc.insets = new Insets(10, 10, 10, 10);
		buttonPanel.add(btnSearchNumber, bpgbc);

		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 10, 10, 10);
		add(buttonPanel, gbc);
		
		pack();
		setLocationRelativeTo(null);
	}
}



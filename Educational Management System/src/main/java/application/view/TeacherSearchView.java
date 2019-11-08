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

import application.controller.TeacherSearchController;
import application.model.Course;
import application.model.Person;

public class TeacherSearchView extends JFrame {

	private static final long serialVersionUID = -6170452136485764396L;

	// graphical widgets
	private JButton btnBack;
	private JList searchByFirstNameResultsList;
	private JList searchByLastNameResultsList;
	private JTextField searchBar;

	// keep reference to controller
	private TeacherSearchController controller;

	// constructor of view delegates the initializations of the GUI
	public TeacherSearchView(TeacherSearchController controller) {
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
				controller.backtoTeacherList();
			}
		});

		final JScrollPane sp = new JScrollPane();
		searchBar = new JTextField(15);

		JButton btnSearchByFirstname = new JButton("Search By First Name");
		btnSearchByFirstname.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (searchBar.getText().isEmpty()) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Cannot search, no keyword provided.", "Empty Search Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					searchByFirstNameResultsList = new JList<>(
							controller.returnSearchByFirstNameResultsList(searchBar.getText()).toArray());
					sp.getViewport().add(searchByFirstNameResultsList);
					sp.revalidate();
					sp.repaint();
				}
			}
		});

		JButton btnSearchByLastname = new JButton("Search By Last Name");
		btnSearchByLastname.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (searchBar.getText().isEmpty()) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Cannot search, no keyword provided.", "Empty Search Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					searchByLastNameResultsList = new JList<>(
							controller.returnSearchByLastNameResultsList(searchBar.getText()).toArray());
					sp.getViewport().add(searchByLastNameResultsList);
					sp.revalidate();
					sp.repaint();
				}
			}
		});

		JButton btnSearchByEmail = new JButton("Search By Email");
		btnSearchByEmail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (searchBar.getText().isEmpty()) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Cannot search, no number provided.", "Empty Search Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Person searchByEmailResult = controller.returnSearchByEmailResult(searchBar.getText());
					ArrayList<String> arrayList = new ArrayList<String>();
					arrayList.add(searchByEmailResult.getFirstName() + " " + searchByEmailResult.getLastName());
					JList list = new JList<>(arrayList.toArray());
					sp.getViewport().add(list);
					sp.revalidate();
					sp.repaint();
				}
			}
		});

		JButton btnSearchBySerialNumber = new JButton("Search By Serial Number");
		btnSearchBySerialNumber.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (searchBar.getText().isEmpty()) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Cannot search, no number provided.", "Empty Search Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Person searchBySerialNumberResult = controller
							.returnSearchBySerialNumberResult(Integer.parseInt(searchBar.getText()));
					ArrayList<String> arrayList = new ArrayList<String>();
					arrayList.add(searchBySerialNumberResult.getFirstName() + " "
							+ searchBySerialNumberResult.getLastName());
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
		buttonPanel.add(btnSearchByFirstname, bpgbc);

		bpgbc.gridx = 0;
		bpgbc.gridy = 1;		
		bpgbc.insets = new Insets(10, 10, 10, 10);
		buttonPanel.add(btnSearchByLastname, bpgbc);

		bpgbc.gridx = 0;
		bpgbc.gridy = 2;		
		bpgbc.insets = new Insets(10, 10, 10, 10);
		buttonPanel.add(btnSearchByEmail, bpgbc);

		bpgbc.gridx = 0;
		bpgbc.gridy = 3;		
		bpgbc.insets = new Insets(10, 10, 10, 10);
		buttonPanel.add(btnSearchBySerialNumber, bpgbc);

		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 10, 10, 10);
		add(buttonPanel, gbc);
		
		pack();
		setLocationRelativeTo(null);
	}

}






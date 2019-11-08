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

import application.controller.CourseSearchController;
import application.model.Course;
import application.model.CourseType;

public class CourseSearchView extends JFrame {

	private static final long serialVersionUID = 8846245931063149908L;

	// graphical widgets
	private JButton btnBack;
	private JList searchByKeywordResultsList;
	private JList searchByTypeResultsList;
	private JList coursesSortedByAttendanceList;
	private JList coursesSortedByGradesList;

	private JTextField searchBar;

	// keep reference to controller
	private CourseSearchController controller;

	// constructor of view delegates the initializations of the GUI
	public CourseSearchView(CourseSearchController controller) {
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
				controller.backtoClassList();
			}
		});

		final JScrollPane sp = new JScrollPane();
		searchBar = new JTextField(15);

		JButton btnSearchByKeyword = new JButton("Search By Keyword");
		btnSearchByKeyword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (searchBar.getText().isEmpty()) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Cannot search, no keyword provided.", "Empty Search Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					searchByKeywordResultsList = new JList<>(
							controller.returnSearchByKeywordResultsList(searchBar.getText()).toArray());
					sp.getViewport().add(searchByKeywordResultsList);
					sp.revalidate();
					sp.repaint();
				}
			}
		});

		JButton btnSearchByType = new JButton("Search By Type");
		btnSearchByType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (searchBar.getText().isEmpty()) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Cannot search, no Type provided.", "Empty Search Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					searchByTypeResultsList = new JList<>(
							controller.returnSearchByTypeResultsList(CourseType.valueOf(searchBar.getText())).toArray());
					sp.getViewport().add(searchByTypeResultsList);
					sp.revalidate();
					sp.repaint();
				}
			}
		});

		JButton btnSearchByCourseNumber = new JButton("Search By Course Number");
		btnSearchByCourseNumber.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (searchBar.getText().isEmpty()) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Cannot search, no number provided.", "Empty Search Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Course searchByCourseNumberResult = controller.returnSearchByCourseNumberResult(Integer.parseInt(searchBar.getText()));
					ArrayList<String> arrayList = new ArrayList<String>();
					arrayList.add(searchByCourseNumberResult.getCourseName());
					JList list = new JList<>(arrayList.toArray());
					sp.getViewport().add(list);
					sp.revalidate();
					sp.repaint();
				}
			}
		});

		JButton btnCoursesWithMostStudentsAttending = new JButton("Courses with most students attending");
		btnCoursesWithMostStudentsAttending.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Course> coursesSortedByAttendance = new ArrayList<Course>();
				coursesSortedByAttendance = controller.returnCoursesSortedByAttendanceList();
				ArrayList<String> coursesSortedByAttendanceString = new ArrayList<String>();
				for (Course c : coursesSortedByAttendance) {
					coursesSortedByAttendanceString.add(c.getCourseName() + " (" + c.getClassList().size() + ")");
				}
				coursesSortedByAttendanceList = new JList(coursesSortedByAttendanceString.toArray());
				sp.getViewport().add(coursesSortedByAttendanceList);
				sp.revalidate();
				sp.repaint();
			}
		});
		
		JButton btnCoursesWithHighestAverageGrades = new JButton("Courses with highest average grades");
		btnCoursesWithHighestAverageGrades.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Course> coursesSortedByGrades = new ArrayList<Course>();
				coursesSortedByGrades = controller.returnCoursesSortedByGradesList();
				ArrayList<String> coursesSortedByGradesString = new ArrayList<String>();
				for (Course c : coursesSortedByGrades) {
					coursesSortedByGradesString.add(c.getCourseName() + " (" + c.getAverageGrade() + ")");
				}
				coursesSortedByGradesList = new JList(coursesSortedByGradesString.toArray());
				sp.getViewport().add(coursesSortedByGradesList);
				sp.revalidate();
				sp.repaint();
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
		buttonPanel.add(btnSearchByKeyword, bpgbc);

		bpgbc.gridx = 0;
		bpgbc.gridy = 1;		
		bpgbc.insets = new Insets(10, 10, 10, 10);
		buttonPanel.add(btnSearchByType, bpgbc);

		bpgbc.gridx = 0;
		bpgbc.gridy = 2;		
		bpgbc.insets = new Insets(10, 10, 10, 10);
		buttonPanel.add(btnSearchByCourseNumber, bpgbc);
		
		bpgbc.gridx = 0;
		bpgbc.gridy = 3;		
		bpgbc.insets = new Insets(10, 10, 10, 10);
		buttonPanel.add(btnCoursesWithMostStudentsAttending, bpgbc);
		
		bpgbc.gridx = 0;
		bpgbc.gridy = 4;		
		bpgbc.insets = new Insets(10, 10, 10, 10);
		buttonPanel.add(btnCoursesWithHighestAverageGrades, bpgbc);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 10, 10, 10);
		add(buttonPanel, gbc);

		pack();
		setLocationRelativeTo(null);
	}
}














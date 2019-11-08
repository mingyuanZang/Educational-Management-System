package application.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import application.model.Course;
import application.model.CourseType;

public class MultiOptionPaneCourse {
	private Course newCourse;
	private int repeat = 0;

	public Course getNewCourse() {
		return newCourse;
	}

	public MultiOptionPaneCourse() {
		while (repeat == 0) {
			JTextField courseNameField = new JTextField(20);
			JTextField courseTimeField = new JTextField(20);
			JTextField ectsField = new JTextField(20);
			JRadioButton bachelors = new JRadioButton("Bachelors", true);
			JRadioButton masters = new JRadioButton("Masters");
			JRadioButton phd = new JRadioButton("Ph.D.");
			ButtonGroup programButts = new ButtonGroup();
			programButts.add(bachelors);
			programButts.add(masters);
			programButts.add(phd);
			JTextField courseDescriptionField = new JTextField();
			JTextField learningObjectivesField = new JTextField();

			String courseName;
			String courseTime;
			int courseNumber;
			String ects;
			int ECTS;
			CourseType type;
			String courseDescription;
			String learningObjectives;

			JPanel myPanel = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 0;
			c.anchor = GridBagConstraints.LINE_START;
			c.insets = new Insets(5, 5, 5, 5);
			myPanel.add(new JLabel("Course name:"), c);
			c.gridx++;
			myPanel.add(courseNameField, c);
			// myPanel.add(Box.createHorizontalStrut(20)); // a spacer
			c.gridy++;
			c.gridx = 0;
			myPanel.add(new JLabel("Course time:"), c);
			c.gridx++;
			myPanel.add(courseTimeField, c);
			c.gridy++;
			c.gridx = 0;
			myPanel.add(new JLabel("ECTS:"), c);
			c.gridx++;
			myPanel.add(ectsField, c);
			c.gridy++;
			c.gridx = 0;
			myPanel.add(new JLabel("Course type:"), c);

			JPanel pnlProgramSelection = new JPanel(new GridBagLayout());
			GridBagConstraints b = new GridBagConstraints();
			b.gridx = 0;
			b.gridy = 0;
			b.insets = new Insets(1, 1, 1, 1);
			pnlProgramSelection.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			pnlProgramSelection.add(bachelors, b);
			b.gridx++;
			pnlProgramSelection.add(masters, b);
			b.gridx++;
			pnlProgramSelection.add(phd, b);

			c.gridx++;
			myPanel.add(pnlProgramSelection, c);
			c.fill = GridBagConstraints.HORIZONTAL;

			c.gridy++;
			c.gridx = 0;
			JLabel courseDescriptLabel = new JLabel("Course Description");
			myPanel.add(courseDescriptLabel, c);
			c.gridx++;
			myPanel.add(courseDescriptionField, c);

			c.gridy++;
			c.gridx = 0;
			JLabel learningObjectivesLabel = new JLabel("Learning Objectives");
			myPanel.add(learningObjectivesLabel, c);
			c.gridx++;
			myPanel.add(learningObjectivesField, c);

			int result = JOptionPane.showConfirmDialog(null, myPanel, "New student information",
					JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				courseName = courseNameField.getText();
				courseTime = courseTimeField.getText();
				// ECTS = Integer.parseInt(ectsField.getText());
				ects = ectsField.getText();
				courseDescription = courseDescriptionField.getText();
				learningObjectives = learningObjectivesField.getText();

				if (bachelors.isSelected()) {
					type = CourseType.BSc;
				} else if (masters.isSelected()) {
					type = CourseType.MSc;
				} else {
					type = CourseType.PhD;
				}

				validatePulledFields(courseName, courseTime, ects, type, courseDescription, learningObjectives);
			} else {
				repeat = 1;
				this.newCourse = null;
			}
		}
	}

	private void validatePulledFields(String courseName, String courseTime, String ects, CourseType type,
			String courseDescription, String learningGoal) {
		if (nameCheck(courseName) == true && checkCourseTime(courseTime) == true && ects.matches("\\d+")
				&& courseDescription != null && learningGoal != null) {
			courseName = properCase(courseName);
			int ECTS = Integer.parseInt(ects);
			this.newCourse = new Course(courseName, courseTime, ECTS, type);
			newCourse.setCourseDescription(courseDescription);
			newCourse.setCourseLearningGoal(learningGoal);
			repeat = 1;
		} else {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Incorrect input, cannot process course.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean nameCheck(String name) {
		if (name.matches("^[a-zA-Z ]+$") && name != null) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkCourseTime(String courseTime) {

		if (courseTime.length() == 2 && courseTime != null) {
			String tempString = (courseTime.substring(0, 1));
			if (tempString.equals("1") || tempString.equals("2") || tempString.equals("3") || tempString.equals("4")
					|| tempString.equals("5")) {
				// System.out.println("first char good");
				if (courseTime.substring(1).equals("A") || courseTime.substring(1).equals("B")) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public String properCase(String input) {
		StringTokenizer tokenizer = new StringTokenizer(input);
		StringBuilder builder = new StringBuilder();

		while (tokenizer.hasMoreTokens()) {
			String word = tokenizer.nextToken();
			word = word.substring(0, 1).toUpperCase() + word.substring(1);
			builder.append(word);
			if (tokenizer.hasMoreTokens()) {
				builder.append(" ");
			}
		}

		String capitalized = builder.toString();
		return capitalized;
	}
}

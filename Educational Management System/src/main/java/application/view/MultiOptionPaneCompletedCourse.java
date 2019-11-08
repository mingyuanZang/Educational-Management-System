package application.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import application.model.Date;
import application.model.Level;
import application.model.Teacher;

public class MultiOptionPaneCompletedCourse {

	private int repeat = 0;
	private int courseNumber;
	private int grade;

	public int getCourseNumber() {
		return courseNumber;
	}

	public int getGrade() {
		return grade;
	}

	public MultiOptionPaneCompletedCourse() {

		while (repeat == 0) {
			JTextField cNumber = new JTextField(20);
			JTextField grd = new JTextField(20);

			JPanel myPanel = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.anchor = GridBagConstraints.LINE_START;
			gbc.insets = new Insets(5, 5, 5, 5);
			myPanel.add(new JLabel("Course Number:"), gbc);
			gbc.gridx++;
			myPanel.add(cNumber, gbc);
			gbc.gridy++;
			gbc.gridx = 0;
			myPanel.add(new JLabel("Grade:"), gbc);
			gbc.gridx++;
			myPanel.add(grd, gbc);

			int result = JOptionPane.showConfirmDialog(null, myPanel, "Completed Course", JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {
				validatePulledFields(cNumber.getText(), grd.getText());
			} else {
				courseNumber = 0;		//Code that MultiOptionPane was cancelled deliberately
				repeat = 1;
			}
		}
	}

	private void validatePulledFields(String cNumber, String grd) {
		if (cNumber != null && grd != null && cNumber.matches("\\d+") && grd.matches("\\d+")
				&& Integer.parseInt(grd) < 11) {
			this.courseNumber = Integer.parseInt(cNumber);
			this.grade = Integer.parseInt(grd);
			repeat = 1;
		} else {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Incorrect input, cannot process grade assignment.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}

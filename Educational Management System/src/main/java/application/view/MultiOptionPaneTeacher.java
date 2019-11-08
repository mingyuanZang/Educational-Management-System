package application.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import application.model.Level;
import application.model.Teacher;

public class MultiOptionPaneTeacher {
	private Teacher newTeacher;
	private int repeat = 0;

	public Teacher getNewTeacher() {
		return newTeacher;
	}

	public MultiOptionPaneTeacher() {

		while (repeat == 0) {
			JTextField fName = new JTextField(20);
			JTextField lName = new JTextField(20);
			JTextField add = new JTextField(20);
			// JTextField lvl = new JTextField(20);

			JRadioButton assistant = new JRadioButton("Assistant", true);
			JRadioButton associate = new JRadioButton("Associate");
			JRadioButton professor = new JRadioButton("Professor");
			ButtonGroup programButts = new ButtonGroup();
			programButts.add(assistant);
			programButts.add(associate);
			programButts.add(professor);

			String firstName;
			String lastName;
			String address;
			String level;
			// SampleProgram sampProg = null;

			JPanel myPanel = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 0;
			c.anchor = GridBagConstraints.LINE_START;
			c.insets = new Insets(5, 5, 5, 5);
			myPanel.add(new JLabel("First name:"), c);
			c.gridx++;
			myPanel.add(fName, c);
			// myPanel.add(Box.createHorizontalStrut(20)); // a spacer
			c.gridy++;
			c.gridx = 0;
			myPanel.add(new JLabel("Last name:"), c);
			c.gridx++;
			myPanel.add(lName, c);
			c.gridy++;
			c.gridx = 0;
			myPanel.add(new JLabel("Address:"), c);
			c.gridx++;
			myPanel.add(add, c);
			c.gridy++;
			c.gridx = 0;
			myPanel.add(new JLabel("Level:"), c);
			c.gridx++;
			// myPanel.add(lvl, c);

			c.fill = GridBagConstraints.HORIZONTAL;
			JPanel pnlProgramSelection = new JPanel(new GridBagLayout());
			GridBagConstraints b = new GridBagConstraints();
			b.gridx = 0;
			b.gridy = 0;
			b.insets = new Insets(1, 1, 1, 1);
			pnlProgramSelection.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			pnlProgramSelection.add(assistant, b);
			b.gridx++;
			pnlProgramSelection.add(associate, b);
			b.gridx++;
			pnlProgramSelection.add(professor, b);
			myPanel.add(pnlProgramSelection, c);

			int result = JOptionPane.showConfirmDialog(null, myPanel, "New teacher information",
					JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {
				firstName = fName.getText();
				lastName = lName.getText();
				address = add.getText();
				// level = lvl.getText();

				if (assistant.isSelected()) {
					level = "Assistant";

				} else if (associate.isSelected()) {
					level = "Associate";

				} else {
					level = "Professor";

				}

				validatePulledFields(firstName, lastName, address, level);

			} else {
				repeat = 1;
				this.newTeacher = null;
			}
		}
	}

	private void validatePulledFields(String firstName, String lastName, String address, String level) {
		if (nameCheck(firstName) == true && nameCheck(lastName) == true && address != null) {
			firstName = properCase(firstName);
			lastName = properCase(lastName);
			this.newTeacher = new Teacher(firstName, lastName);
			newTeacher.setAddress(address);
			newTeacher.setLevel(Level.valueOf((level)));
			repeat = 1;
		} else {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Incorrect input, cannot process teacher.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean nameCheck(String name) {
		if (name.matches("^[a-zA-Z]+$")) {
			return true;
		} else {
			return false;
		}
	}

	String properCase(String inputVal) {
		// Empty strings should be returned as-is.

		if (inputVal.length() == 0)
			return "";

		// Strings with only one character uppercased.

		if (inputVal.length() == 1)
			return inputVal.toUpperCase();

		// Otherwise uppercase first letter, lowercase the rest.

		return inputVal.substring(0, 1).toUpperCase() + inputVal.substring(1).toLowerCase();
	}

}

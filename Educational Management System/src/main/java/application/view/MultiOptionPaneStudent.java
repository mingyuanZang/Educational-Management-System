package application.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import application.model.Date;
import application.model.InvalidDateException;
import application.model.Student;

public class MultiOptionPaneStudent {
	private Student newStudent;
	private int repeat = 0;

	public Student getNewStudent() {
		return newStudent;
	}

	public MultiOptionPaneStudent() {
		while (repeat == 0) {
			JTextField fName = new JTextField(20);
			JTextField lName = new JTextField(20);
			JTextField day = new JTextField(2);
			JTextField month = new JTextField(2);
			JTextField year = new JTextField(5);
			JTextField add = new JTextField(20);

			String firstName;
			String lastName;
			Date birthdate;
			String address;

			JPanel datePanel = new JPanel(new GridBagLayout());
			GridBagConstraints d = new GridBagConstraints();
			d.gridx = 0;
			JLabel monthLabel = new JLabel("Month:");
			datePanel.add(monthLabel, d);
			d.gridx++;
			datePanel.add(month, d);

			d.gridx++;
			JLabel dayLabel = new JLabel("Day:");
			datePanel.add(dayLabel, d);
			d.gridx++;
			datePanel.add(day, d);

			d.gridx++;
			JLabel yearLabel = new JLabel("Year:");
			datePanel.add(yearLabel, d);
			d.gridx++;
			datePanel.add(year, d);

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
			myPanel.add(new JLabel("Date of Birth:"), c);
			c.gridx++;
			myPanel.add(datePanel, c);
			c.gridy++;
			c.gridx = 0;

			int result = JOptionPane.showConfirmDialog(null, myPanel, "New student information",
					JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {
				firstName = fName.getText();
				lastName = lName.getText();
				address = add.getText();

				try {
					birthdate = new Date(Integer.parseInt(month.getText()), Integer.parseInt(day.getText()),
							Integer.parseInt(year.getText()));
				} catch (InvalidDateException e) {
					birthdate = null;
				}

				validatePulledFields(firstName, lastName, address, birthdate);

			} else {
				repeat = 1; // MultiOptionPane closed
				this.newStudent = null;
			}
		}
	}

	private void validatePulledFields(String firstName, String lastName, String address, Date birthdate) {
		if (nameCheck(firstName) == true && nameCheck(lastName) == true && address != null && birthdate != null) {
			firstName = properCase(firstName);
			lastName = properCase(lastName);
			this.newStudent = new Student(firstName, lastName, birthdate);
			newStudent.setAddress(address);
			repeat = 1;
		} else {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Incorrect input, cannot process student.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean nameCheck(String name) {
		if (name.matches("^[a-zA-Z]+$") && name != null) {
			return true;
		} else {
			return false;
		}
	}

	public String properCase(String inputVal) {
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

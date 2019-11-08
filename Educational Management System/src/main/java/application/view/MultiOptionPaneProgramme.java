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

import application.model.CourseType;
import application.model.Programme;

public class MultiOptionPaneProgramme {
	private Programme newProgramme;
	private int repeat = 0;

	public Programme getNewProgramme() {
		return newProgramme;
	}

	public MultiOptionPaneProgramme() {

		while (repeat == 0) {
			JTextField nameField = new JTextField(20);
			// JTextField typeField = new JTextField(20);

			JRadioButton bachelors = new JRadioButton("Bachelors", true);
			JRadioButton masters = new JRadioButton("Masters");
			JRadioButton phd = new JRadioButton("Ph.D.");
			ButtonGroup programButts = new ButtonGroup();
			programButts.add(bachelors);
			programButts.add(masters);
			programButts.add(phd);

			String programmeName;
			CourseType type;
			// Type programmeType = null;
			// SampleProgram sampProg = null;

			JPanel myPanel = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 0;
			c.anchor = GridBagConstraints.LINE_START;
			c.insets = new Insets(5, 5, 5, 5);
			myPanel.add(new JLabel("Programme name:"), c);
			c.gridx++;
			myPanel.add(nameField, c);
			// myPanel.add(Box.createHorizontalStrut(20)); // a spacer
			c.gridy++;
			c.gridx = 0;
			myPanel.add(new JLabel("Type:"), c);
			c.gridx++;
			// myPanel.add(typeField, c);

			c.fill = GridBagConstraints.HORIZONTAL;
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
			myPanel.add(pnlProgramSelection, c);

			int result = JOptionPane.showConfirmDialog(null, myPanel, "New programme information",
					JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {
				programmeName = nameField.getText();
				// type = typeField.getText();

				if (bachelors.isSelected()) {
					type = CourseType.BSc;
				} else if (masters.isSelected()) {
					type = CourseType.MSc;
				} else {
					type = CourseType.PhD;
				}

				validatePulledFields(programmeName, type);

			} else {
				repeat = 1;
				this.newProgramme = null;
			}
		}
	}

	private void validatePulledFields(String programmeName, CourseType programmeType) {
		if (nameCheck(programmeName) == true && programmeName != null) {
			programmeName = properCase(programmeName);
			this.newProgramme = new Programme(programmeName, programmeType);
			repeat = 1;
		} else {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Incorrect input, cannot process programme.", "Error",
					JOptionPane.ERROR_MESSAGE);
			repeat = 0;
		}

		// checkRepeat(repeat);
	}

	private boolean nameCheck(String name) {
		if (name.matches("^[a-zA-Z ]+$")) {
			return true;
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

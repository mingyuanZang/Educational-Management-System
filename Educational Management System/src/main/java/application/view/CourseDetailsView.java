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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import application.controller.CourseDetailsController;
import application.model.Student;
import application.utils.GridBagLayoutUtils;

public class CourseDetailsView extends JFrame {

		private static final long serialVersionUID = 8981053836072595592L;
		
		//graphical widgets
		private JButton btnBack;
		private JList currentStudentList;
		private JList prereqList;
		private JList teacherAssistantList;
		private JList teacherList;
		
		
		//keep reference to controller
		private CourseDetailsController controller;

		//constructor of view delegates the initializations of the GUI
		public CourseDetailsView(CourseDetailsController controller) {
			this.controller = controller;
			initGUI();
		}
		
		private void initGUI() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(false); //set to fixed dimension
			setTitle("Detailed View");
			setLayout(new GridBagLayout()); //recommended
		    GridBagConstraints gbc = new GridBagConstraints();

			btnBack = new JButton("Back to List");
			btnBack.addActionListener(new ActionListener() {
				@Override //used anonymous classes because this is done only once, is waiting for clicks
				public void actionPerformed(ActionEvent e) {
					controller.backtoClassList();
				}
			});
			
			
			JLabel currStud = new JLabel("Current Students");
			GridBagConstraints lgbc = new GridBagConstraints();
			lgbc.gridx = 0;
			lgbc.gridy = 0;
	        lgbc.insets = new Insets(20,0,0,0);
			add(currStud,lgbc);
			
			JLabel prereq = new JLabel("Prerequisites");
			lgbc.gridx = 1;
			lgbc.gridy = 0;
	        lgbc.insets = new Insets(20,0,0,0);
			add(prereq,lgbc);
			
			JLabel teach = new JLabel("Teachers");
			lgbc.gridx = 2;
			lgbc.gridy = 0;
	        lgbc.insets = new Insets(20,0,0,0);
			add(teach,lgbc);
			
			JLabel teachAss = new JLabel("Teacher Assistants");
			lgbc.gridx = 3;
			lgbc.gridy = 0;
	        lgbc.insets = new Insets(20,0,0,0);
			add(teachAss,lgbc);
			
		    final JScrollPane sp = new JScrollPane();
			currentStudentList = new JList<>(controller.classList.toArray());
			sp.getViewport().add(currentStudentList);
	        gbc.gridx = 0;
	        gbc.gridy = 1;
	        gbc.weightx = 1;
	        gbc.weighty = 2;
	        gbc.ipady = 20;
	        gbc.ipadx = 20;
	        gbc.insets = new Insets(0,10,10,10); 
	        sp.setPreferredSize(new Dimension(100,200));
	        add(sp,gbc);

			final JScrollPane sp2 = new JScrollPane();
			prereqList = new JList<>(controller.prereqList.toArray());
			sp2.getViewport().add(prereqList);
	        gbc.gridx = 1;
	        gbc.gridy = 1;
	        gbc.weightx = 1;
	        gbc.weighty = 2;
	        gbc.ipady = 20;
	        gbc.ipadx = 20;
	        gbc.insets = new Insets(0,10,10,10); 
	        sp2.setPreferredSize(new Dimension(100,200));
	        add(sp2,gbc);
	        
			final JScrollPane sp3 = new JScrollPane();
			teacherList = new JList<>(controller.teacherList.toArray());
			sp3.getViewport().add(teacherList);
	        gbc.gridx = 2;
	        gbc.gridy = 1;
	        gbc.weightx = 1;
	        gbc.weighty = 2;
	        gbc.ipady = 20;
	        gbc.ipadx = 20;
	        gbc.insets = new Insets(0,10,10,10); 
	        sp3.setPreferredSize(new Dimension(100,200));
	        add(sp3,gbc);
	        
			final JScrollPane sp4 = new JScrollPane();
			teacherAssistantList = new JList<>(controller.teacherAssistantList.toArray());
			sp4.getViewport().add(teacherAssistantList);
	        gbc.gridx = 3;
	        gbc.gridy = 1;
	        gbc.weightx = 1;
	        gbc.weighty = 2;
	        gbc.ipady = 20;
	        gbc.ipadx = 20;
	        gbc.insets = new Insets(0,10,10,10); 
	        sp4.setPreferredSize(new Dimension(100,200));
	        add(sp4,gbc);
	        
	        //private void setPosition(nt gridx, int gridy, weightx, weighty, ipady, ipadx)

			add(btnBack, GridBagLayoutUtils.constraint(6, 6, 5));
			
			pack();
			setLocationRelativeTo(null);
		}
		
	}
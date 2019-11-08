package application.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import application.controller.StudentDetailsController;
import application.model.Course;
//import application.controller.LoginController;
import application.utils.GridBagLayoutUtils;

public class StudentDetailsView extends JFrame {

	private static final long serialVersionUID = 8981053836072595592L;
	
	//graphical widgets
	private JButton btnBack;
	private JList passedCourseList;
	private JList missingCourseList;
	
	
	//keep reference to controller
	private StudentDetailsController controller;


	//constructor of view delegates the initializations of the GUI
	public StudentDetailsView(StudentDetailsController controller) {
		this.controller = controller;
		initGUI();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); //set to fixed dimension
		setTitle("Detailed View");
		setLayout(new GridBagLayout()); //reccommended
	    GridBagConstraints gbc = new GridBagConstraints();
		
		
		 JPanel[][] box = new JPanel[5][2];
         
         for(int x = 0; x <5; x++)
         {
         	for(int y = 0; y<2; y++) {
                 box[x][y] = new JPanel(new GridBagLayout());
                 box[x][y].setBorder(new LineBorder(Color.RED));
                 gbc.weightx = 1;
                 gbc.weighty = 1;
                 gbc.ipady = 40;
                 gbc.ipadx = 40;
                 gbc.fill = GridBagConstraints.BOTH;
                // gbc.insets = new Insets(40, 40, 40, 40);
                 //box[x][y].add(new JLabel("I'm a box"), gbc);
         	}
         }
         
         box[0][0].add(new JLabel("1A"), gbc);
         box[1][0].add(new JLabel("2A"), gbc);
         box[2][0].add(new JLabel("3A"), gbc);
         box[3][0].add(new JLabel("4A"), gbc);
         box[4][0].add(new JLabel("5A"), gbc);
         
         box[0][1].add(new JLabel("1B"), gbc);
         box[1][1].add(new JLabel("2B"), gbc);
         box[2][1].add(new JLabel("3B"), gbc);
         box[3][1].add(new JLabel("4B"), gbc);
         box[4][1].add(new JLabel("5B"), gbc);

         HashSet<Course> currentCourseList = controller.getStudent().getCurrentCourseList();
         for (Course course : currentCourseList) {
        	 String str = course.getCourseTime();
        	 int x = Integer.parseInt(str.substring(0, 1)) - 1;
        	 int y = str.charAt(1) - 65;
        	 box[x][y].add(new JLabel(course.getCourseName()), gbc);
         }
         
		btnBack = new JButton("Back to List");
		btnBack.addActionListener(new ActionListener() {
			@Override //used anonymous classes because this is done only once, is waiting for clicks
			public void actionPerformed(ActionEvent e) {
				controller.backtoStudentList();
			}
		});
		
	    GridBagConstraints a = new GridBagConstraints();
		JLabel passedCourseListLabel = new JLabel("Passed Courses");
		a.gridx = 6;
		a.gridy = 0;
 		a.insets = new Insets(10,10,0,10);
		add(passedCourseListLabel,a);
		
	    final JScrollPane sp = new JScrollPane();
		passedCourseList = new JList<>(controller.passedCourseList.toArray());

        sp.getViewport().add(passedCourseList);
        gbc.gridx = 6;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.ipady = 20;
        gbc.ipadx = 20;
        gbc.insets = new Insets(10,10,10,10); 
        sp.setPreferredSize(new Dimension(100,200));
		//add(sp, GridBagLayoutUtils.constraint(6, 0, 0));
        add(sp,gbc);
  	
		
   		JLabel missingCourseListLabel = new JLabel(controller.returnProgrammeName() + ": Missing Courses");
		a.gridx = 8;
		a.gridy = 0;
 		a.insets = new Insets(10,10,0,10);
		add(missingCourseListLabel, a);
		
		
		final JScrollPane sp2 = new JScrollPane();
		missingCourseList = new JList<>(controller.missingCourseList.toArray());

        sp2.getViewport().add(missingCourseList);
        a.gridx = 8;
        a.gridy = 1;
        a.weightx = 0;
        a.weighty = 0;
        a.gridheight = 2;
        a.gridwidth = 1;
        a.ipady = 20;
        a.ipadx = 20;
        a.insets = new Insets(10,10,10,10); 
        sp2.setPreferredSize(new Dimension(100,200));
		//add(sp, GridBagLayoutUtils.constraint(6, 0, 0));
        add(sp2,a);


        GridBagConstraints q = new GridBagConstraints();
        JLabel scheduleLabel = new JLabel("Current Schedule");
        q.gridx = 0;
        	q.gridy = 0;
        q.insets = new Insets(10, 10, 0, 10);
        add(scheduleLabel,q);
        
		 for(int x = 0; x < 5; x++)
         {
         		for(int y = 0; y < 2; y++) {
         			if(x == 0) {
         				gbc.insets = new Insets(0,10,0,0);
         				if(y == 0)
         				{
         					gbc.insets = new Insets(10,10,0,0);
         				}
         			}
         			else if (y == 0) {
         			 gbc.insets = new Insets(10,0,0,0); 
         			 	if(x == 0){
         			 		gbc.insets = new Insets(10,0,10,0);
         			 	}
         			}
         			else {
        		 		gbc.insets = new Insets(0,0,0,0);
         			}
                     gbc.weighty = 0;
                     gbc.weightx = 0;
                     gbc.gridx = x;
                     gbc.gridy = y+1;
                     gbc.gridheight = 1;
                     gbc.gridwidth = 1;
                     box[x][y].setPreferredSize(new Dimension(100,100));
                     add(box[x][y], gbc); 	
         		}
         }
		

		add(btnBack, GridBagLayoutUtils.constraint(8, 8, 5));
		
		pack();
		setLocationRelativeTo(null);
	}

}




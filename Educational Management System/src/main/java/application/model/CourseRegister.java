package application.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.swing.table.AbstractTableModel;

import application.IOStream.IOStream;

public class CourseRegister extends AbstractTableModel {

	private static final long serialVersionUID = 8981302399437973910L;
	private HashSet<Course> set;
	private final String[] headers = { "Course Name", "Course Time", "Course Number", "ECTS", "Type", "Course Description", "Learning Objectives"};
	private int counter;

	public CourseRegister() {
		if (IOStream.inputCollection("CourseSetInCourseRegister.xml") != null) {
			set = (HashSet<Course>) IOStream.inputCollection("CourseSetInCourseRegister.xml");
			counter = set.size() + 1;
		}
		else {
			set = new HashSet<Course>();
			counter = 1;
		}
	}
	
	public ResponseMessage adding(Course course) {
		ResponseMessage response;

		if (courseIsInRegister(course)) {
			response = new ResponseMessage("Course is already in the register");
		} else {
			set.add(course);
			course.setCourseNumber(generateCourseNumber(course.getType()));
			counter++;
			response = new ResponseMessage("Course added to register");
		}

		return response;
	}
	

	public void deleteCourse(Course course) {
		set.remove(course);
		fireTableDataChanged();
	}

	public void addCourse(Course course) {
		set.add(course);
	}

	private int generateCourseNumber(CourseType type) {
		return 910000 + counter;
	}

	public boolean courseIsInRegister(Course course) {
		return set.contains(course);
	}

	public Course returnCourseByNumber(int courseNumber) {
		Course course = null;
		for (Course c : set) {
			if (c.getCourseNumber() == courseNumber) {
				course = c;
			}
		}
		return course;
	}

	public Course returnCourseByNameAndTime(String courseName, String courseTime) {
		Course course = null;
		for (Course c : set) {
			if ((c.getCourseName().equals(courseName)) && (c.getCourseTime().equals(courseTime))) {
				course = c;
			}
		}
		return course;
	}

	public ArrayList<Course> searchByKeyword(String keyword) {
		ArrayList<Course> courseList = new ArrayList<Course>();

		for (Course course : set) {
			
			boolean hasKeywordInName = Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE).matcher(course.getCourseName()).find();
			boolean hasKeywordInDescription = Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE).matcher(course.getCourseDescription()).find();
			
			if (hasKeywordInName || hasKeywordInDescription) {
				courseList.add(course);
			}
		}
		return courseList;
	}

	public ArrayList<Course> searchByType(CourseType type) {
		ArrayList<Course> courseList = new ArrayList<Course>();

		for (Course course : set) {
			if (course.getType().equals(type)) {
				courseList.add(course);
			}
		}
		return courseList;
	}

	public HashSet<Course> getSet() {
		return set;
	}

	// Begin Table Extension Functions
	public int getRowCount() {
		return set.size();
	}

	public int getColumnCount() {
		return headers.length;
	}

	public String getColumnName(int columnIndex) {
		return headers[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// ArrayList<Course> courseArrayList = new ArrayList<Course>(set);
		ArrayList<Course> courseArrayList = arrayListSorted();
		switch (columnIndex) {
		case 0:
			return courseArrayList.get(rowIndex).getCourseName();
		case 1:
			return courseArrayList.get(rowIndex).getCourseTime();
		case 2:
			return courseArrayList.get(rowIndex).getCourseNumber();
		case 3:
			return courseArrayList.get(rowIndex).getECTS();
		case 4:
			return courseArrayList.get(rowIndex).getType();
		case 5:
			return courseArrayList.get(rowIndex).getCourseDescription();
		case 6:
			return courseArrayList.get(rowIndex).getCourseLearningGoal();
		default:
			return null; // Must never happens
		}
	}

	public Class getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 2:
			return int.class;

		case 3:
			return int.class;

		case 4:
			return CourseType.class;

		default:
			return Object.class;
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public void addCourseToTable(Course course) {
		ArrayList<Course> courseArrayList = new ArrayList<Course>(set);
		courseArrayList.add(course);
		fireTableRowsInserted(courseArrayList.size() - 1, courseArrayList.size() - 1);
	}

	public Course returnCourseFromArrayList(int selectedRow) {
		// ArrayList<Course> courseArrayList = new ArrayList<Course>(set);
		ArrayList<Course> courseArrayList = arrayListSorted();
		return courseArrayList.get(selectedRow);
	}

	/**
	 * @return
	 */
	public ArrayList<Course> arrayListSorted() {
		ArrayList<Course> courseArrayList = new ArrayList<Course>(set);
		Collections.sort(courseArrayList, new Comparator<Course>() {
			@Override
			public int compare(Course o1, Course o2) {
				Course c1 = o1;
				Course c2 = o2;
				int temp = c1.getCourseNumber() - c2.getCourseNumber();
				return temp;
			}
		});
		return courseArrayList;
	}
	
}

package application.model;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

import application.IOStream.IOStream;

public class StudentRegister extends AbstractTableModel {

	private HashSet<Student> set;
	private int counter;
	private final String[] headers = { "First name", "Last Name", "Address", "Birthdate", "Student Number", "Email" };

	public StudentRegister() {
		if (IOStream.inputCollection("StudentSetInStudentRegister.xml") != null) {
			set = (HashSet<Student>) IOStream.inputCollection("StudentSetInStudentRegister.xml");
			counter = set.size() + 1;
		} else {
			set = new HashSet<Student>();
			counter = 1;
		}
	}

	public ResponseMessage signUp(Student student) {
		ResponseMessage response;

		if (studentIsInRegister(student)) {
			response = new ResponseMessage("Student is already in the register");
		} else {
			set.add(student);
			student.setStudentNumber(generateStudentNumber());
			student.setEmail(generateEmail(student));
			counter++;
			response = new ResponseMessage("Student signed up");
		}
		return response;
	}

	public void addStudent(Student student) {
		set.add(student);
	}
	
	public void deleteStudent(Student student) {
		set.remove(student);
		fireTableDataChanged();
	}

	private int generateStudentNumber() {
		return 210000 + counter;
	}

	public String generateEmail(Student student) {
		return "s" + student.getStudentNumber() + "@dtu.dk";
	}

	public boolean studentIsInRegister(Student student) {
		return set.contains(student);
	}

	public Student returnStudentByNumber(int studentNumber) {
		for (Student student : set) {
			if (student.getStudentNumber() == studentNumber) {
				return student;
			}
		}
		return null;
	}

	public ArrayList<Student> returnStudentByFirstName(String firstName) {
		ArrayList<Student> studentList = new ArrayList<Student>();

		for (Student student : set) {
			if (student.getFirstName().equalsIgnoreCase(firstName)) {
				studentList.add(student);
			}
		}
		return studentList;
	}

	public ArrayList<Student> returnStudentByLastName(String lastName) {
		ArrayList<Student> studentList = new ArrayList<Student>();

		for (Student student : set) {
			if (student.getLastName().equalsIgnoreCase(lastName)) {
				studentList.add(student);
			}
		}
		return studentList;
	}

	public HashSet<Student> getSet() {
		return set;
	}

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
		// ArrayList<Student> studentArrayList = new ArrayList<Student>(set);
		ArrayList<Student> studentArrayList = arrayListSorted();
		switch (columnIndex) {
		case 0:
			return studentArrayList.get(rowIndex).getFirstName();
		case 1:
			return studentArrayList.get(rowIndex).getLastName();
		case 2:
			return studentArrayList.get(rowIndex).getAddress();
		case 3:
			return studentArrayList.get(rowIndex).getBirthdate().dateToString();
		case 4:
			return studentArrayList.get(rowIndex).getStudentNumber();
		case 5:
			return studentArrayList.get(rowIndex).getEmail();
		default:
			return null; // Must never happens
		}
	}

	public Class getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 4:
			return int.class;
		// case 5 :
		// return Type.class;
		default:
			return Object.class;
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {

		if (columnIndex == 2) {
			return true;
		} else {
			return false; // All other cells not editable
		}
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (aValue != null) {
			ArrayList<Student> studentArrayList = new ArrayList<Student>(set);
			Student student = studentArrayList.get(rowIndex);

			switch (columnIndex) {
			case 2:
				student.setAddress((String) aValue);
				break;

			}
		}
	}

	public void addStudentToTable(Student student) {
		ArrayList<Student> studentArrayList = new ArrayList<Student>(set);
		studentArrayList.add(student);
		fireTableRowsInserted(studentArrayList.size() - 1, studentArrayList.size() - 1);
	}

	public Student returnStudentFromArrayList(int selectedRow) {
		// ArrayList<Student> studentArrayList = new ArrayList<Student>(set);
		ArrayList<Student> studentArrayList = arrayListSorted();
		return studentArrayList.get(selectedRow);
	}
	
	public ArrayList<Student> arrayListSorted() {
		ArrayList<Student> studentArrayList = new ArrayList<Student>(set);
		Collections.sort(studentArrayList, new Comparator<Student>() {
			@Override
			public int compare(Student s1, Student s2) {
				int temp = s1.getStudentNumber() - s2.getStudentNumber();
				return temp;
			}
		});
		return studentArrayList;
	}
}














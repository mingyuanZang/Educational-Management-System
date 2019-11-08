package application.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

import application.IOStream.IOStream;

public class TeacherRegister extends AbstractTableModel {

	private HashSet<Teacher> set;
	private int counter;
	private final String[] headers = { "First name", "Last name", "Address", "Level", "Serial Number", "Email" };

	public TeacherRegister() {
		if (IOStream.inputCollection("TeacherSetInTeacherRegister.xml") != null) {
			set = (HashSet<Teacher>) IOStream.inputCollection("TeacherSetInTeacherRegister.xml");
			counter = set.size() + 1;
		}
		else {
			set = new HashSet<Teacher>();
			counter = 1;
		}
	}
	
	public ResponseMessage signUp(Teacher teacher) {
		ResponseMessage response;

		if (teacherIsInRegister(teacher)) {
			response = new ResponseMessage("Teacher is already in the register");
		} else {
			set.add(teacher);
			teacher.setSerialNumber(generateSerialNumber());
			teacher.setEmail(generateTeacherEmail(teacher));
			counter++;
			response = new ResponseMessage("Teacher signed up");
		}

		return response;
	}

	public void deleteTeacher(Teacher teacher) {
		set.remove(teacher);
		fireTableDataChanged();
	}

	public boolean teacherIsInRegister(Teacher teacher) {
		return set.contains(teacher);
	}

	private int generateSerialNumber() {
		return 100000 + counter;
	}

	public void addTeacher(Teacher teacher) {
		set.add(teacher);
	}

	public String generateTeacherEmail(Teacher teacher) {
		return teacher.getFirstName().toLowerCase().substring(0, 1) + teacher.getLastName().toLowerCase() + "@dtu.dk";
	}

	public Teacher returnTeacherBySerialNumber(int serialNumber) {
		Teacher teacher = null;
		for (Teacher t : set) {
			if (t.getSerialNumber() == serialNumber) {
				teacher = t;
			}
		}
		return teacher;
	}

	public ArrayList<Teacher> returnTeacherByFirstName(String firstName) {
		ArrayList<Teacher> teacherList = new ArrayList<Teacher>();

		for (Teacher teacher : set) {
			if ((teacher.getFirstName().equalsIgnoreCase(firstName))) {
				teacherList.add(teacher);
			}
		}
		return teacherList;
	}

	public ArrayList<Teacher> returnTeacherByLastName(String lastName) {
		ArrayList<Teacher> teacherList = new ArrayList<Teacher>();

		for (Teacher teacher : set) {
			if ((teacher.getLastName().equalsIgnoreCase(lastName))) {
				teacherList.add(teacher);
			}
		}
		return teacherList;
	}
	
	public Teacher returnTeacherByEmail(String teacherEmail) {
		Teacher teacher = null;
		for (Teacher t : set) {
			if (t.getEmail().equalsIgnoreCase(teacherEmail)) {
				teacher = t;
			}
		}
		return teacher;
	}

	public HashSet<Teacher> getSet() {
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
		ArrayList<Teacher> teacherArrayList = arrayListSorted();
		switch (columnIndex) {
		case 0:
			return teacherArrayList.get(rowIndex).getFirstName();
		case 1:
			return teacherArrayList.get(rowIndex).getLastName();
		case 2:
			return teacherArrayList.get(rowIndex).getAddress();
		case 3:
			return teacherArrayList.get(rowIndex).getLevel();
		case 4:
			return teacherArrayList.get(rowIndex).getSerialNumber();
		case 5: 
			return teacherArrayList.get(rowIndex).getEmail();
		default:
			return null; // Must never happen
		}
	}

	public Class getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 4:
			return int.class;
		default:
			return Object.class;
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 2 || columnIndex == 3) {
			return true;
		} else {
			return false; // All other cells not editable
		}
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (aValue != null) {
			ArrayList<Teacher> teacherArrayList = new ArrayList<Teacher>(set);
			Teacher teacher = teacherArrayList.get(rowIndex);

			switch (columnIndex) {
			case 2:
				teacher.setAddress((String) aValue);
				break;
			case 3:
				teacher.setLevel(Level.valueOf((String) aValue));
			}
		}
	}

	public void addTeacherToTable(Teacher teacher) {			
		ArrayList<Teacher> teacherArrayList = new ArrayList<Teacher>(set);
		teacherArrayList.add(teacher);
		fireTableRowsInserted(teacherArrayList.size() - 1, teacherArrayList.size() - 1);
	}

	public Teacher returnTeacherFromArrayList(int selectedRow) {
		ArrayList<Teacher> teacherArrayList = arrayListSorted();
		return teacherArrayList.get(selectedRow);
	}
	
	public ArrayList<Teacher> arrayListSorted() {
		ArrayList<Teacher> teacherArrayList = new ArrayList<Teacher>(set);

		Collections.sort(teacherArrayList, new Comparator<Teacher>() {
			@Override
			public int compare(Teacher t1, Teacher t2) {
				int temp = t1.getSerialNumber() - t2.getSerialNumber();
				return temp;
			}
		});

		return teacherArrayList;
	}
	
}

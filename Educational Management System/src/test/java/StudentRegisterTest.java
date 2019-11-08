import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import application.model.Date;
import application.model.InvalidDateException;
import application.model.Student;
import application.model.StudentRegister;

public class StudentRegisterTest {

	private StudentRegister studentRegister;
	private Student someStudent;
	private Date date;

	@Before
	public void setUp() {
		studentRegister = new StudentRegister();
		try {
			date = new Date(1, 1, 1111);
		} catch (InvalidDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getRowCountTest() {
		studentRegister.addStudent(someStudent);
		assertEquals(1, studentRegister.getRowCount());
	}

	@Test
	public void getColumnCountTest() {
		studentRegister.addStudent(someStudent);
		assertEquals(6, studentRegister.getColumnCount());

	}

	@Test
	public void getColumnNameTest() {
		studentRegister.addStudent(someStudent);
		assertEquals("Last Name", studentRegister.getColumnName(1));
	}

	@Test
	public void getValueAtTest() {

		someStudent = new Student("Alison", "Davis", date);
		someStudent.setAddress("Hawkeye Dr");
		studentRegister.addStudent(someStudent);
		assertEquals("Alison", studentRegister.getValueAt(0, 0));
		assertEquals("Davis", studentRegister.getValueAt(0, 1));
		assertEquals("Hawkeye Dr", studentRegister.getValueAt(0, 2));
		assertEquals("1/1/1111", studentRegister.getValueAt(0, 3));
		assertEquals(someStudent.getStudentNumber(), studentRegister.getValueAt(0, 4));
		assertEquals(someStudent.getEmail(), studentRegister.getValueAt(0, 5));
		assertEquals(null, studentRegister.getValueAt(6, 6));
	}

	@Test
	public void getColumnClassTest() {
		assertEquals(studentRegister.getColumnClass(4), int.class);
		assertEquals(studentRegister.getColumnClass(3), Object.class);
	}

	@Test
	public void isCellEditableTest() {
		assertEquals(studentRegister.isCellEditable(0, 2), true);
		assertEquals(studentRegister.isCellEditable(0, 3), false);
	}

	@Test
	public void setValueAtTest() {
		someStudent = new Student("Alison", "Davis", date);
		someStudent.setAddress("Hawkeye Dr");
		studentRegister.addStudent(someStudent);
		studentRegister.setValueAt("newAdd", 0, 2);
		assertEquals("newAdd", studentRegister.getValueAt(0, 2));
		studentRegister.setValueAt(null, 0, 2);
		assertEquals("newAdd", studentRegister.getValueAt(0, 2));
		studentRegister.setValueAt(null, 0, 0);
		assertEquals("Alison", studentRegister.getValueAt(0, 0));
	}

	@Test
	public void returnStudentFromArrayListTest() {
		someStudent = new Student("Alison", "Davis", date);
		someStudent.setAddress("Hawkeye Dr");
		studentRegister.addStudent(someStudent);
		assertEquals(studentRegister.returnStudentFromArrayList(0), someStudent);
	}

	@Test
	public void AddRemoveArrayListTest() {
		someStudent = new Student("Alison", "Davis", date);
		someStudent.setAddress("Hawkeye Dr");
		studentRegister.addStudent(someStudent);
		studentRegister.addStudentToTable(someStudent);
		assertEquals(someStudent, studentRegister.returnStudentFromArrayList(0));
	}

	@Test
	public void returnStudentByNumberTest() {
		someStudent = new Student("Alison", "Davis", date);
		studentRegister.addStudent(someStudent);
		assertEquals(null, studentRegister.returnStudentByNumber(100));
	}

	@Test
	public void getSetTest() {
		someStudent = new Student("Alison", "Davis", date);
		studentRegister.addStudent(someStudent);
		assertFalse(studentRegister.getSet().isEmpty());
	}

	@Test
	public void arrayListSortedTest() {
		someStudent = new Student("Jingkun", "Zhu", date);
		someStudent.setStudentNumber(210001);
		studentRegister.addStudent(someStudent);
		someStudent = new Student("Mingyuan", "Zang", date);
		someStudent.setStudentNumber(210002);
		studentRegister.addStudent(someStudent);
		studentRegister.arrayListSorted();
	}
}

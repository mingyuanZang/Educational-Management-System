import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import application.model.Date;
import application.model.InvalidDateException;
import application.model.Level;
import application.model.Student;
import application.model.StudentRegister;
import application.model.Teacher;
import application.model.TeacherRegister;

public class TeacherRegisterTest {

	private TeacherRegister teacherRegister;
	private Teacher someTeacher;
	private Date date;

	@Before
	public void setUp() {
		teacherRegister = new TeacherRegister();
		try {
			date = new Date(1, 1, 1111);
		} catch (InvalidDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getSetTest() {
		assertTrue(teacherRegister.getSet().isEmpty());
	}

	@Test
	public void getRowCountTest() {
		teacherRegister.addTeacher(someTeacher);
		assertEquals(1, teacherRegister.getRowCount());
	}

	@Test
	public void getColumnCountTest() {
		teacherRegister.addTeacher(someTeacher);
		assertEquals(6, teacherRegister.getColumnCount());

	}

	@Test
	public void getColumnNameTest() {
		teacherRegister.addTeacher(someTeacher);
		assertEquals("Last name", teacherRegister.getColumnName(1));
	}

	@Test
	public void getValueAtTest() {

		someTeacher = new Teacher("Alison", "Davis");
		someTeacher.setAddress("Hawkeye Dr");
		someTeacher.setLevel(Level.Assistant);
		teacherRegister.addTeacher(someTeacher);
		assertEquals("Alison", teacherRegister.getValueAt(0, 0));
		assertEquals("Davis", teacherRegister.getValueAt(0, 1));
		assertEquals("Hawkeye Dr", teacherRegister.getValueAt(0, 2));
		assertEquals(Level.Assistant, teacherRegister.getValueAt(0, 3));
		assertEquals(someTeacher.getSerialNumber(), teacherRegister.getValueAt(0, 4));
		assertEquals(someTeacher.getEmail(), teacherRegister.getValueAt(0, 5));
		assertEquals(null, teacherRegister.getValueAt(6, 6));
	}

	@Test
	public void getColumnClassTest() {
		assertEquals(teacherRegister.getColumnClass(4), int.class);
		assertEquals(teacherRegister.getColumnClass(3), Object.class);
	}

	@Test
	public void isCellEditableTest() {
		assertEquals(teacherRegister.isCellEditable(0, 2), true);
		assertEquals(teacherRegister.isCellEditable(0, 3), true);
		assertEquals(teacherRegister.isCellEditable(0, 4), false);
	}

	@Test
	public void setValueAtTest() {
		someTeacher = new Teacher("Alison", "Davis");
		someTeacher.setAddress("Hawkeye Dr");
		teacherRegister.addTeacher(someTeacher);
		teacherRegister.setValueAt("newAdd", 0, 2);
		assertEquals("newAdd", teacherRegister.getValueAt(0, 2));
		teacherRegister.setValueAt(null, 0, 2);
		assertEquals("newAdd", teacherRegister.getValueAt(0, 2));
		teacherRegister.setValueAt(null, 0, 0);
		assertEquals("Alison", teacherRegister.getValueAt(0, 0));
		teacherRegister.setValueAt("Assistant", 0, 3);
		assertEquals("Alison", teacherRegister.getValueAt(0, 0));
	}

	@Test
	public void returnTeacherFromArrayListTest() {
		someTeacher = new Teacher("Alison", "Davis");
		someTeacher.setAddress("Hawkeye Dr");
		teacherRegister.addTeacher(someTeacher);
		assertEquals(teacherRegister.returnTeacherFromArrayList(0), someTeacher);
	}

	@Test
	public void AddRemoveArrayListTest() {
		someTeacher = new Teacher("Alison", "Davis");
		someTeacher.setAddress("Hawkeye Dr");
		teacherRegister.addTeacher(someTeacher);
		teacherRegister.addTeacherToTable(someTeacher);
		assertEquals(someTeacher, teacherRegister.returnTeacherFromArrayList(0));
	}
	
	@Test
	public void arrayListSortedTest() {
		someTeacher = new Teacher("Jingkun", "Zhu");
		someTeacher.setSerialNumber(100001);
		teacherRegister.addTeacher(someTeacher);
		someTeacher = new Teacher("Alison", "Davis");
		someTeacher.setSerialNumber(100002);
		teacherRegister.addTeacher(someTeacher);
		teacherRegister.arrayListSorted();
	}

}
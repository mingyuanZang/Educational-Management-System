import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import application.model.Date;
import application.model.InvalidDateException;
import application.model.Person;
import application.model.Student;
import application.model.Teacher;

public class AdditionalTeacherTests {

	// private Teacher teacher;
	private Date date;

	@Before
	public void setUp() {
		// teacher = new Teacher("Jane", "Smith");
		try {
			date = new Date(1, 1, 1111);
		} catch (InvalidDateException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void equalsTest() {
		Person teacher = new Teacher("Jane", "Smith");
		assertEquals(true, teacher.equals(teacher));
		assertEquals(false, teacher.equals(null));
		Student student = new Student("Alison", "Davis", date);
		assertEquals(false, teacher.equals(student));
		Person teacherTwo = new Teacher("Joe", "Har");
		assertEquals(false, teacher.equals(teacherTwo));
		Person teacherThree = new Teacher("Joe", "Davis");
		assertEquals(false, teacher.equals(teacherThree));
		Person blank = new Teacher("Jane", null);
		assertEquals(false, blank.equals(teacher));
		Person blankTwo = new Teacher(null, "Smith");
		blankTwo.hashCode();
		assertEquals(false, blankTwo.equals(teacher));
		Person blankThree = new Teacher(null, "Davis");
		assertEquals(false, blankTwo.equals(blankThree));
		Person blankFour = new Teacher("Jane", null);
		blankFour.hashCode();
		assertEquals(true, blank.equals(blankFour));
		Person teacherFour = new Teacher("Joe", "Davis");
		assertEquals(true, teacherFour.equals(teacherThree));
		assertEquals(true, teacher.toString().equals("Jane Smith"));
	}
}

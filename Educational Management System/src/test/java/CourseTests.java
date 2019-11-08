import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import application.model.Course;
import application.model.CourseType;
import application.model.Teacher;

public class CourseTests {

	CourseType type = CourseType.BSc;

	@Test
	public void equalsTest() {
		Course c1 = new Course("Java", "1A", 1, type);
		Course c2 = new Course("Java", "1A", 1, type);
		assertEquals(true, c1.equals(c2));
		assertEquals(false, c1.equals(null));
		Teacher t = new Teacher("Andrew", "Something");
		assertEquals(false, c1.equals(t));
		Course c3 = new Course(null, "1A", 1, type);
		c3.hashCode();
		assertEquals(false, c3.equals(c1));
		Course c4 = new Course("Java", null, 1, type);
		c4.hashCode();
		assertEquals(false, c4.equals(c1));
		Course c5 = new Course(null, "1A", 1, type);
		assertEquals(true, c3.equals(c5));
		Course c6 = new Course("Math", "2B", 1, type);
		assertEquals(false, c1.equals(c6));
		Course c7 = new Course("Java", null, 1, type);
		assertEquals(true, c7.equals(c4));
		Course c8 = new Course("Java", "2B", 1, type);
		assertEquals(false, c8.equals(c1));
	}

	@Test
	public void getTeacherListTest() {
		Course c1 = new Course("Java", "1A", 1, type);
		assertTrue(c1.getTeacherList().isEmpty());
	}

	@Test
	public void setAndGetAverageGradeTest() {
		Course c1 = new Course("Java", "1A", 1, type);
		c1.setAverageGrade(10);
		assertEquals(10, c1.getAverageGrade());
	}

	@Test
	public void toStringTest() {
		Course c1 = new Course("Java", "1A", 1, type);
		assertEquals("Java", c1.toString());
	}
}

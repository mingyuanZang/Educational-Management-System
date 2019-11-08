import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;

import application.model.Course;
import application.model.CourseType;
import application.model.Date;
import application.model.InvalidDateException;
import application.model.Person;
import application.model.Programme;
import application.model.Student;
import application.model.Teacher;

public class StudentTests {
	private Date date;

	@Test
	public void equalsTest() throws InvalidDateException {
		date = new Date(1, 1, 1111);

		Student s1 = new Student("Jingkun", "Zhu", date);
		Student s2 = new Student("Jingkun", "Zhu", date);
		assertEquals(true, s1.equals(s2));
		assertEquals(false, s1.equals(null));
		Person teacher = new Teacher("Jane", "Smith");
		assertEquals(false, s1.equals(teacher));
		Student s3 = new Student(null, "Zhu", date);
		s3.hashCode();
		assertEquals(false, s3.equals(s1));
		Student s4 = new Student("Jingkun", null, date);
		s4.hashCode();
		assertEquals(false, s1.equals(s4));
		assertEquals(false, s4.equals(s1));
		Student s5 = new Student(null, "Zhu", date);
		assertEquals(true, s3.equals(s5));
		Student s6 = new Student("Mingyuan", "Zang", date);
		assertEquals(false, s1.equals(s6));
		Student s7 = new Student("Jingkun", null, date);
		assertEquals(true, s4.equals(s7));
	}

	@Test
	public void setAndGetCurrentCourseListTest() {
		Student s1 = new Student("Jingkun", "Zhu", date);
		CourseType type = CourseType.BSc;
		Course c1 = new Course("Java", "1A", 10, type);
		HashSet<Course> currentCourseList = new HashSet<Course>();
		currentCourseList.add(c1);
		s1.setCurrentCourseList(currentCourseList);
		assertFalse(s1.getCurrentCourseList().isEmpty());
	}

	@Test
	public void GetMissingCourseListTest() {
		Student s1 = new Student("Jingkun", "Zhu", date);
		assertTrue(s1.getMissingCourseList().isEmpty());

	}

	@Test
	public void getGradeListTest() {
		Student s1 = new Student("Jingkun", "Zhu", date);
		assertTrue(s1.getGradeList().isEmpty());
	}

	@Test
	public void addGradeTest() {
		Student s1 = new Student("Jingkun", "Zhu", date);
		CourseType type = CourseType.BSc;
		Course c1 = new Course("Java", "1A", 10, type);
		s1.addGrade(910001, 9, c1);
	}

	@Test
	public void toStringTest() {
		Student s1 = new Student("Jingkun", "Zhu", date);
		assertEquals(s1.toString(), "Jingkun Zhu");
	}

	@Test
	public void studentEnrolledToAProgrammeTest() {
		CourseType type = CourseType.BSc;
		Student s1 = new Student("Jingkun", "Zhu", date);
		Programme p = new Programme("Computer Science", type);
		assertFalse(s1.studentEnrolledToAProgramme());
	}

	@Test
	public void courseTimeOccupiedTest() {
		Student s1 = new Student("Jingkun", "Zhu", date);
		CourseType type = CourseType.BSc;
		Course c1 = new Course("Java", "1A", 10, type);
		Course c2 = new Course("Programming", "1A", 10, type);
		Course c3 = new Course("Signaling", "1B", 10, type);
		s1.enrollCourse(c1);
		assertTrue(s1.courseTimeOccupied(c2));
		assertFalse(s1.courseTimeOccupied(c3));
	}
}

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import application.model.Course;
import application.model.CourseRegister;
import application.model.CourseType;
import application.model.Date;
import application.model.InvalidDateException;
import application.model.Student;

public class CourseRegisterTest {

	private CourseRegister courseRegister;
	private Course someCourse;
	private Date date;

	@Before
	public void setUp() {
		courseRegister = new CourseRegister();
		try {
			date = new Date(1, 1, 1111);
		} catch (InvalidDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getSetTest() {
		assertTrue(courseRegister.getSet().isEmpty());
	}

	@Test
	public void getRowCountTest() {
		courseRegister.addCourse(someCourse);
		assertEquals(1, courseRegister.getRowCount());
	}

	@Test
	public void getColumnCountTest() {
		courseRegister.addCourse(someCourse);
		assertEquals(7, courseRegister.getColumnCount());

	}

	@Test
	public void getColumnNameTest() {
		courseRegister.addCourse(someCourse);
		assertEquals("Course Time", courseRegister.getColumnName(1));
	}

	@Test
	public void getValueAtTest() {

		someCourse = new Course("Physics", "1A", 10, CourseType.BSc);
		someCourse.setCourseDescription("something");
		someCourse.setCourseLearningGoal("leanring goals");

		courseRegister.addCourse(someCourse);

		assertEquals("Physics", courseRegister.getValueAt(0, 0));
		assertEquals("1A", courseRegister.getValueAt(0, 1));
		assertEquals(someCourse.getCourseNumber(), courseRegister.getValueAt(0, 2));
		assertEquals(someCourse.getECTS(), courseRegister.getValueAt(0, 3));
		assertEquals(someCourse.getType(), courseRegister.getValueAt(0, 4));
		assertEquals(someCourse.getCourseDescription(), courseRegister.getValueAt(0, 5));
		assertEquals(someCourse.getCourseLearningGoal(), courseRegister.getValueAt(0, 6));
		assertEquals(null, courseRegister.getValueAt(8, 8));
	}

	@Test
	public void getColumnClassTest() {
		assertEquals(courseRegister.getColumnClass(2), int.class);
		assertEquals(courseRegister.getColumnClass(3), int.class);
		assertEquals(courseRegister.getColumnClass(4), CourseType.class);
		assertEquals(courseRegister.getColumnClass(1), Object.class);
	}

	@Test
	public void isCellEditableTest() {
		assertEquals(courseRegister.isCellEditable(0, 2), false);
	}

	@Test
	public void returnCourseFromArrayListTest() {
		someCourse = new Course("Physics", "1A", 10, CourseType.BSc);
		someCourse.setCourseDescription("something");
		someCourse.setCourseLearningGoal("leanring goals");
		courseRegister.addCourseToTable(someCourse);
		courseRegister.addCourse(someCourse);
		assertEquals(courseRegister.returnCourseFromArrayList(0), someCourse);
	}

	@Test
	public void arrayListSortedTest() {
		someCourse = new Course("Physics", "1A", 10, CourseType.BSc);
		someCourse.setCourseNumber(910001);
		courseRegister.addCourse(someCourse);
		someCourse = new Course("Math", "2A", 10, CourseType.BSc);
		someCourse.setCourseNumber(910002);
		courseRegister.addCourse(someCourse);
		someCourse = new Course("Java", "3A", 10, CourseType.BSc);
		someCourse.setCourseNumber(910003);
		courseRegister.addCourse(someCourse);
		courseRegister.arrayListSorted();
	}

	@Test
	public void courseComparatorTest() {
		Student s1 = new Student("Jingkun1", "Zhu", date);
		Student s2 = new Student("Jingkun2", "Zhu", date);
		Student s3 = new Student("Jingkun3", "Zhu", date);
		someCourse = new Course("Physics", "1A", 10, CourseType.BSc);
		someCourse.setAverageGrade(10);
		someCourse.addStudent(s1);
		someCourse.addStudent(s2);
		courseRegister.addCourse(someCourse);
		someCourse = new Course("Math", "2A", 10, CourseType.BSc);
		someCourse.setAverageGrade(7);
		someCourse.addStudent(s3);
		courseRegister.addCourse(someCourse);
		ArrayList<Course> courseList = new ArrayList<Course>(courseRegister.getSet());
		Collections.sort(courseList, Course.Comparators.ATTENDANCE);
		Collections.sort(courseList, Course.Comparators.GRADE);

	}
}
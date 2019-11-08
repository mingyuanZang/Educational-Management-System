import static org.junit.Assert.assertEquals;

import org.junit.Test;

import application.model.CourseType;
import application.model.Person;
import application.model.Programme;
import application.model.Teacher;

public class ProgrammeTests {

	CourseType type = CourseType.BSc;
	
	@Test
	public void equalsTest() {
		Programme p1 = 	new Programme("Computer", type);
		Programme p2 = 	new Programme("Computer", type);
		assertEquals(true, p1.equals(p2));
		assertEquals(false, p1.equals(null));
		Person t = new Teacher("Andrew", "Something");
		assertEquals(false, p1.equals(t));
		Programme p3 = new Programme(null, type);
		p3.hashCode();
		assertEquals(false, p3.equals(p1));
		assertEquals(false, p1.equals(p3));
		Programme p4 = new Programme(null, type);
		assertEquals(true, p3.equals(p4));
	}
	
}

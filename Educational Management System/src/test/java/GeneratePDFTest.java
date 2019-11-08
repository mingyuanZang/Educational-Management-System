import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import application.IOStream.GeneratePDF;
import application.model.Course;
import application.model.CourseRegister;
import application.model.CourseType;
import application.model.Date;
import application.model.Student;
import application.model.StudentRegister;

public class GeneratePDFTest {
	private Date date;
	CourseType type;
	StudentRegister studentRegister;
	CourseRegister courseRegister;
	
	@Test
	public void EmptyListTest() {
		Course c1 = new Course("Java", "1A", 2, type.BSc);
		GeneratePDF pdf = new GeneratePDF();
		pdf.generateClassList(c1);
	}
	
	@Test
	public void NoProgrammeTest() {
		Course c1 = new Course("programing", "1A", 2, type.BSc);
		Student s1 = new Student("Mingyuan", "Zang", date);
		Student s2 = new Student("Jingkun", "Zhu", date);
		c1.addStudent(s1);
		c1.addStudent(s2);
		assertFalse(c1.getClassList().isEmpty());
		assertTrue(s1.getProgramme()== null);
		GeneratePDF pdf = new GeneratePDF();
		pdf.generateClassList(c1);
	}

}

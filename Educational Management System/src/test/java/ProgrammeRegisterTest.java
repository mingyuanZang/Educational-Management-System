import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import application.model.CourseRegister;
import application.model.CourseType;
import application.model.Date;
import application.model.InvalidDateException;
import application.model.Programme;
import application.model.ProgrammeRegister;
import application.model.Student;
import application.model.StudentRegister;

public class ProgrammeRegisterTest {

	private ProgrammeRegister programmeRegister;
	private Programme someProgramme;
	private Date date;
	private CourseType type;

	@Before
	public void setUp() {
		programmeRegister = new ProgrammeRegister();
		try {
			date = new Date(1, 1, 1111);
		} catch (InvalidDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getSetTest() {
		assertTrue(programmeRegister.getSet().isEmpty());
	}

	@Test
	public void getRowCountTest() {
		someProgramme = new Programme("Physics", CourseType.MSc);
		programmeRegister.adding(someProgramme);
		programmeRegister.addProgrammeToTable(someProgramme);
		assertEquals(1, programmeRegister.getRowCount());
	}

	@Test
	public void getColumnCountTest() {
		someProgramme = new Programme("Physics", CourseType.MSc);
		programmeRegister.adding(someProgramme);
		programmeRegister.addProgrammeToTable(someProgramme);
		assertEquals(2, programmeRegister.getColumnCount());

	}

	@Test
	public void getColumnNameTest() {
		programmeRegister.adding(someProgramme);
		assertEquals("Programme Type", programmeRegister.getColumnName(1));
	}

	@Test
	public void getValueAtTest() {
		type = CourseType.MSc;
		someProgramme = new Programme("Physics", CourseType.MSc);
		programmeRegister.adding(someProgramme);
		programmeRegister.addProgrammeToTable(someProgramme);
		assertEquals(true, programmeRegister.getValueAt(0, 0).equals("Physics"));
		assertEquals(true, programmeRegister.getValueAt(0, 1).equals(type));
		assertEquals(null, programmeRegister.getValueAt(6, 6));
	}

	@Test
	public void returnProgrammeFromArrayListTest() {
		someProgramme = new Programme("Physics", CourseType.MSc);
		programmeRegister.adding(someProgramme);
		programmeRegister.addProgrammeToTable(someProgramme);
		assertEquals(programmeRegister.returnProgrammeFromArrayList(0), someProgramme);
	}

	@Test
	public void returnProgrammeByNameTest() {
		someProgramme = new Programme("Physics", CourseType.MSc);
		programmeRegister.adding(someProgramme);
//		programmeRegister.addProgrammeToTable(someProgramme);
		assertEquals(programmeRegister.returnProgrammeByName("Physics"), someProgramme);
		assertEquals(programmeRegister.returnProgrammeByName("Programming"), null);
	}
}

import static org.junit.Assert.*;

import org.junit.Test;

import application.model.Date;
import application.model.InvalidDateException;

public class DateTest {
	private Date date;

	@Test
	public void LeapCheck() {
		try {
			date = new Date(2, 29, 2000);
		} catch (InvalidDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void dateValidCheck1() {
		try {
			date = new Date(0, 1, 1);
		} catch (InvalidDateException e) {
			assertEquals(e.getMessage(), "Invalid birthdate");
		}
		try {
			date = new Date(13, 1, 1);
		} catch (InvalidDateException e) {
			assertEquals(e.getMessage(), "Invalid birthdate");
		}
		try {
			date = new Date(1, 0, 1);
		} catch (InvalidDateException e) {
			assertEquals(e.getMessage(), "Invalid birthdate");
		}
		try {
			date = new Date(1, 40, 1);
		} catch (InvalidDateException e) {
			assertEquals(e.getMessage(), "Invalid birthdate");
		}
		try {
			date = new Date(2, 29, 100);
		} catch (InvalidDateException e) {
			assertEquals(e.getMessage(), "Invalid birthdate");
		}
		try {
			date = new Date(2, 29, 120);
		} catch (InvalidDateException e) {
			assertEquals(e.getMessage(), "Invalid birthdate");
		}
		try {
			date = new Date(2, 29, 121);
		} catch (InvalidDateException e) {
			assertEquals(e.getMessage(), "Invalid birthdate");
		}
	}

	@Test
	public void testToString() {
		try {
			date = new Date(1, 28, 2018);
		} catch (InvalidDateException e) {
			e.printStackTrace();
		}
		assertEquals(date.dateToString(), "1/28/2018");
	}
}

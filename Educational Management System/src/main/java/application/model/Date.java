package application.model;

import java.io.Serializable;

public class Date implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6134324144566663661L;

	private static final int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	private int month;
	private int day;
	private int year;

	public Date(int month, int day, int year) throws InvalidDateException {
		if (!dateIsValid(month, day, year)) {
			throw new InvalidDateException("Invalid birthdate");
		} else {
			this.month = month;
			this.day = day;
			this.year = year;
		}
	}

	private static boolean dateIsValid(int month, int day, int year) {
		if (month < 1 || month > 12)
			return false;
		if (day < 1 || day > DAYS[month])
			return false;
		if (month == 2 && day == 29 && !isLeapYear(year))
			return false;
		return true;
	}

	private static boolean isLeapYear(int year) {
		if (year % 400 == 0)
			return true;
		if (year % 100 == 0)
			return false;
		return (year % 4 == 0);
	}

	public String dateToString() {
		return month + "/" + day + "/" + year;
	}
}

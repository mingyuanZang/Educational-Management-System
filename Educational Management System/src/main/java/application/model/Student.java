package application.model;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.swing.RepaintManager;

public class Student extends Person implements Serializable {
	
	private static final long serialVersionUID = 6511808405437880915L;
	
	private Date birthdate;
	private int studentNumber;
	private Programme programme;

	private HashSet<Course> currentCourseList = new HashSet<Course>();
	private HashSet<Course> passedCourseList = new HashSet<Course>();
	private HashSet<Course> missingCourseList = new HashSet<Course>();
	
	private HashMap<Integer, Integer> gradeList = new HashMap<Integer, Integer>();

	
	public Student(String firstName, String lastName, Date birthdate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
	}
	
	public Date getBirthdate() {
		return birthdate;
	}
	
	public String returnBirthdateAsString() {
		return birthdate.dateToString();
	}
	
	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	public int getStudentNumber() {
		return studentNumber;
	}
	
	public HashSet<Course> getPassedCourseList() {
		return passedCourseList;
	}
	
	public void setCurrentCourseList(HashSet<Course> currentCourseList) {
		this.currentCourseList = currentCourseList;
	}
	
	public HashSet<Course> getCurrentCourseList() {
		return currentCourseList;
	}
	
	public HashSet<Course> getMissingCourseList() {
		return missingCourseList;
	}
	
	public HashMap<Integer, Integer> getGradeList() {
		return gradeList;
	}

	public Programme getProgramme() {
		return programme;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	public ResponseMessage enrollCourse(Course course) {
		ResponseMessage response;
		
		if ((currentCourseList.contains(course)) || (courseTimeOccupied(course)) || !(passedCourseList.containsAll(course.getPrerequisites()))) {
			response = new ResponseMessage("Student cannot be enrolled");
		} else {
			currentCourseList.add(course);
			response = new ResponseMessage("Student added");
		}
		return response;
	}

	public boolean courseIsInCurrentCourseList(Course course) {
		return currentCourseList.contains(course);
	}
	
	public boolean courseTimeOccupied(Course course1) {
		for (Course course : currentCourseList) {
			if (course.getCourseTime().equals(course1.getCourseTime())) {
				return true;
			}
		}
		return false;
	}

	public void enrollProgramme(Programme programme) {
		this.programme = programme;
	}
	
	public boolean studentEnrolledToAProgramme() {
		if (programme != null) {
			return true;
		} else return false;
	}

	public ResponseMessage showMissingCourses() {
		ResponseMessage response;
		String message = "";
		HashSet<Course> missingCourses = new HashSet<Course>();
		HashSet<Course> commonCourses = new HashSet<Course>();
		if (programme == null || (programme.getMandatoryCourses().isEmpty() && programme.getElectiveCourses().isEmpty())) {
		
		}
		else if (programme.getMandatoryCourses().isEmpty()) {
			missingCourses.addAll(programme.getElectiveCourses());
			commonCourses.addAll(programme.getElectiveCourses());
			commonCourses.retainAll(passedCourseList);
			missingCourses.removeAll(commonCourses);
		} 
		else if (programme.getElectiveCourses().isEmpty()) {
			missingCourses.addAll(programme.getMandatoryCourses());
			commonCourses.addAll(programme.getMandatoryCourses());
			commonCourses.retainAll(passedCourseList);
			missingCourses.removeAll(commonCourses);
		}
		else {
		missingCourses.addAll(programme.getMandatoryCourses());
		missingCourses.addAll(programme.getElectiveCourses());
		commonCourses.addAll(programme.getMandatoryCourses());
		commonCourses.addAll(programme.getElectiveCourses());
		commonCourses.retainAll(passedCourseList);
		missingCourses.removeAll(commonCourses);
		}
		missingCourseList = missingCourses;
		
		
		if (missingCourses.isEmpty()) {
			response = new ResponseMessage("Student has passed all the mandatory and elective courses");
		} else {
			for (Course course : missingCourses) {
				message = message + course.getCourseName() + " ";
			}
			response = new ResponseMessage(message.trim());
		}
		return response;
	}
	
	public void addGrade(int courseNumber, int grade, Course course) {
			gradeList.put(courseNumber, grade);
			if (grade > 3) {
				addCourseToPassedCourseList(course);
			}
	}
	
	public void addCourseToPassedCourseList(Course course) {
		passedCourseList.add(course);
	}
	
}



























package application.model;
import java.io.Serializable;
import java.util.HashSet;

public class Programme implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3536305107955037497L;
	private String programmeName;
	private CourseType type;
	private HashSet<Course> mandatoryCourses = new HashSet<Course>();
	private HashSet<Course> electiveCourses = new HashSet<Course>();
	
	
	public Programme(String programmeName, CourseType type) {
		super();
		this.programmeName = programmeName;
		this.type = type;
	}
	
	public String getProgrammeName() {
		return programmeName;
	}

	public CourseType getType() {
		return type;
	}
	
	public HashSet<Course> getMandatoryCourses() {
		return mandatoryCourses;
	}
	
	public HashSet<Course> getElectiveCourses() {
		return electiveCourses;
	}
	
	public ResponseMessage addMandatoryCourseInProgramme(Course course) {
		ResponseMessage response;

			if (courseIsInMandatoryCourseList(course)) {
				response = new ResponseMessage("Course is already in mandatory course list");
			} else {
				mandatoryCourses.add(course);
				response = new ResponseMessage("Mandatory course added");
			}
		return response;
	}
	
	public boolean courseIsInMandatoryCourseList(Course course) {
		return mandatoryCourses.contains(course);
	}
	
	public ResponseMessage addElectiveCourseInProgramme(Course course) {
		ResponseMessage response;

			if (courseIsInElectiveCourseList(course)) {
				response = new ResponseMessage("Course is already in elective course list");
			} else {
				electiveCourses.add(course);
				response = new ResponseMessage("Elective course added");
			}
		return response;
	}
	
	public boolean courseIsInElectiveCourseList(Course course) {
		return electiveCourses.contains(course);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((programmeName == null) ? 0 : programmeName.hashCode());
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
		Programme other = (Programme) obj;
		if (programmeName == null) {
			if (other.programmeName != null)
				return false;
		} else if (!programmeName.equals(other.programmeName))
			return false;
		return true;
	}

	

	

}

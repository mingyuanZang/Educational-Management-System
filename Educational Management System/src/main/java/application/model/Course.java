package application.model;
import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;

import application.IOStream.GeneratePDF;



public class Course implements Serializable, Comparable<Course> {

	private static final long serialVersionUID = -4792198776908983765L;
	private String courseName;
	private String courseTime;
	private int courseNumber;
	private int ECTS;
	private CourseType type;
	private String courseDescription;
	private String courseLearningGoal;
	GeneratePDF generatePDF = new GeneratePDF();
	

	private HashSet<Student> classList = new HashSet<Student>();
	private HashSet<Course> prerequisites = new HashSet<Course>();
	private HashSet<Teacher> teacherList = new HashSet<Teacher>();
	private HashSet<Student> teacherAssistantList = new HashSet<Student>();
	
	private int averageGrade;
	
	
	public Course(String courseName, String courseTime, int ECTS, CourseType type) {
		this.courseName = courseName;
		this.courseTime = courseTime;
		this.ECTS = ECTS;
		this.type = type;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getCourseTime() {
		return courseTime;
	}

	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}

	public int getCourseNumber() {
		return courseNumber;
	}
	
	public String getCourseDescription() {
		return courseDescription;
	}
	
	public String getCourseLearningGoal() {
		return courseLearningGoal;
	}
	
	public int getECTS() {
		return ECTS;
	}
	
	public CourseType getType() {
		return type;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
	
	public void setCourseLearningGoal(String courseLearningGoal) {
		this.courseLearningGoal = courseLearningGoal;
	}
	
	public void setECTS(int ECTS) {
		this.ECTS = ECTS;
	}

	public int getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(int averageGrade) {
		this.averageGrade = averageGrade;
	}

	public HashSet<Student> getClassList() {
		return classList;
	}
	
	public HashSet<Teacher> getTeacherList() {
		return teacherList;
	}

	public HashSet<Course> getPrerequisites() {
		return prerequisites;
	}
	
	public String toString() {
		return courseName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
		result = prime * result + ((courseTime == null) ? 0 : courseTime.hashCode());
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
		Course other = (Course) obj;
		if (courseName == null) {
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;
		if (courseTime == null) {
			if (other.courseTime != null)
				return false;
		} else if (!courseTime.equals(other.courseTime))
			return false;
		return true;
	}

	public boolean studentIsInClassList(Student student) {
		return classList.contains(student);
	}

	public ResponseMessage addStudent(Student student) {
		ResponseMessage response;

		if (classList.contains(student)) {
			response = new ResponseMessage("Student is already in the course list");
		} else {
			classList.add(student);
			response = new ResponseMessage("Student added");
		}
		return response;
	}

	public void addCourseToPrerequisites(Course course) {
		prerequisites.add(course);
	}

	public ResponseMessage addTeacher(Teacher teacher) {
		ResponseMessage response;

		if (teacherList.contains(teacher)) {
			response = new ResponseMessage("Teacher is already in the teacher list");
		} else {
			teacherList.add(teacher);
			response = new ResponseMessage("Teacher added");
		}
		return response;
	}

	public boolean teacherIsInTeacherList(Person teacher) {
		return teacherList.contains(teacher);
	}
	
	public ResponseMessage deleteTeacher(Person teacher) {
		ResponseMessage response;

		if (teacherList.contains(teacher)) {
			teacherList.remove(teacher);
			response = new ResponseMessage("Teacher deleted");
		} else {
			response = new ResponseMessage("Teacher is not in the teacher list");
		}
		return response;
	}

	public ResponseMessage addTeacherAssistant(Student teacherAssistant, Course chosenCourse) {
		ResponseMessage response;
		
		if (teacherAssistant.getPassedCourseList().contains(chosenCourse)) {
			if (teacherAssistantList.contains(teacherAssistant)) {
				response = new ResponseMessage("Student is already in the teacher assistant list");
			} else {
				teacherAssistantList.add(teacherAssistant);
				response = new ResponseMessage("Student added as a teacher assistant");
			}
		} else {
			response = new ResponseMessage("Student did not pass this course");
		}
		return response;
	}
	
	public ResponseMessage deleteTeacherAssistant(Student student) {
		ResponseMessage response;
		
		if (teacherAssistantList.contains(student)) {
			teacherAssistantList.remove(student);
			response = new ResponseMessage("Teacher assistant deleted");
		} else {
			response = new ResponseMessage("Student is not in the teacher assistant list");
		}
		return response;
	}

	public HashSet<Student> getTeacherAssistantList() {
		return teacherAssistantList;
	}

	public ResponseMessage generateParticipationList(Course course) {
		ResponseMessage response;
		generatePDF.generateClassList(course);
		response = new ResponseMessage("Participants list generated");
		return response;
	}
	
	@Override
	public int compareTo(Course c) {
    		return Comparators.ATTENDANCE.compare(this, c);
	}
    
    public static class Comparators {
    		public static Comparator<Course> ATTENDANCE = new Comparator<Course>() {
            @Override
            public int compare(Course c1, Course c2) {
            		
            		//descending order
            		return c2.getClassList().size() - c1.getClassList().size();
            		
            		//ascending order
            		//return c1.getClassList().size() - c2.getClassList().size();
            }
        };
        public static Comparator<Course> GRADE = new Comparator<Course>() {
            @Override
            public int compare(Course c1, Course c2) {
            	
            		//descending order
                return c2.getAverageGrade() - c1.getAverageGrade();
                
                //ascending order
                //return c1.getAverageGrade() - c2.getAverageGrade();
            }
        };
    }
	
}















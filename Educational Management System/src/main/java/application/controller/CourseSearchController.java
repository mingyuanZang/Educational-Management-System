package application.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import application.model.Course;
import application.model.CourseRegister;
import application.model.CourseType;
import application.model.Student;
import application.model.StudentRegister;
import application.view.CourseSearchView;

public class CourseSearchController {

	private CourseSearchView view;
	private CourseRegister courseRegister;
	private StudentRegister studentRegister;
	private applicationController app;
	public ArrayList<Course> searchByKeywordResultsList;
	public ArrayList<Course> searchByTypeResultsList;

	public CourseSearchController(CourseRegister courseRegister, StudentRegister studentRegister,
			applicationController app) {
		this.courseRegister = courseRegister;
		this.studentRegister = studentRegister;
		this.app = app;
		this.view = new CourseSearchView(this);
	}

	public ArrayList<Course> returnSearchByKeywordResultsList(String keyword) {
		return courseRegister.searchByKeyword(keyword);
	}

	public Course returnSearchByCourseNumberResult(int courseNumber) {
		return courseRegister.returnCourseByNumber(courseNumber);
	}

	public ArrayList<Course> returnSearchByTypeResultsList(CourseType type) {
		return courseRegister.searchByType(type);
	}

	public ArrayList<Course> returnCoursesSortedByAttendanceList() {
		ArrayList<Course> courseList = new ArrayList<Course>(courseRegister.getSet());
		Collections.sort(courseList, Course.Comparators.ATTENDANCE);
		return courseList;
	}

	public ArrayList<Course> returnCoursesSortedByGradesList() {
		ArrayList<Course> courseList = new ArrayList<Course>(courseRegister.getSet());
		HashMap<Integer, Double> courseGradeList = new HashMap<Integer, Double>();		//Maybe delete

		for (Course c : courseList) {

			int tempCourseNumber = c.getCourseNumber();
			ArrayList<Integer> allGrades = new ArrayList<Integer>();

			for (Student s : studentRegister.getSet()) {
				if (s.getGradeList().containsKey(tempCourseNumber)) {
					int tempGrade = s.getGradeList().get(tempCourseNumber);
					allGrades.add(tempGrade);
				}
			}
			
			double courseAverage = calculateAverage(allGrades);
			c.setAverageGrade((int) Math.round(courseAverage));
			courseGradeList.put(tempCourseNumber, courseAverage);
			
		}
		
		Collections.sort(courseList, Course.Comparators.GRADE);
		return courseList;
	}

	private double calculateAverage(List<Integer> integers) {
		Integer sum = 0;
		if (!integers.isEmpty()) {
			for (Integer integer : integers) {
				sum += integer;
			}
			return sum.doubleValue() / integers.size();
		}
		return sum;
	}

	public void backtoClassList() {
		view.setVisible(false);
		app.recreateClasses();
	}

	public void display() {
		view.setVisible(true);
	}

}

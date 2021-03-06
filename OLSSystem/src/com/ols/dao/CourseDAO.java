package com.ols.dao;

import java.util.ArrayList;
import java.util.List;

import com.ols.po.*;
/**
 * @author adil alim
 *
 */

public interface CourseDAO {
	public ArrayList<Course> getCourseByTeacherID(String TeacherID);
	public ArrayList<Course> getCourseByStudentID(String StudentID);
	public Course getCourseByID(String CourseID);
	public void addCourse(Course course);
	public void updateCourse(Course course);
	public void deleteCourse(String CourseID);
	
}

/**
 * Author Fan Wang by 2014
 */

package com.ols.action;

import java.io.IOException;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ols.po.Course;
import com.ols.po.Quiz;
import com.ols.po.Teacher;
import com.ols.po.Student;
import com.ols.dao.StudentDAO;
import com.ols.dao.TeacherDAO;
import com.ols.service.*;
import com.ols.util.*;

public class AddQuizAction extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		HttpSession httpSession = request.getSession();
		
		Course course  =(Course) httpSession.getAttribute("course");
		
		String CourseID=course.getCourseID();
		System.out.println("AddQuiz----------"+CourseID);
		//Quiz part
		String QuizID = request.getParameter("QuizID");
		String QuizName = request.getParameter("QuizName");
		String QuizDueDate = request.getParameter("duetime");		
		//float QuizFullGrade = Float.parseFloat(request.getParameter("QuizFullGrade"));
		//int TotalQuestionNumber = Integer.parseInt(request.getParameter("QuizFullGrade"));
		
		Quiz quiz = new Quiz();
		QuizServiceImpl quizServiceImpl = new QuizServiceImpl();
		ArrayList<Quiz> quizList = new ArrayList<Quiz>();
		
		if(CourseID != null){
			quiz.setQuizID(QuizID);
			quiz.setQuizName(QuizName);
			quiz.setDueTime(QuizDueDate);
			//quiz.setQuizFullGrade(QuizFullGrade);
			//quiz.setTotalQuestionNumber(TotalQuestionNumber);
			
			quizServiceImpl.addNewQuiz(quiz,CourseID);
			quizList = quizServiceImpl.getQuizByCourseID(CourseID);	
		}
		
		httpSession.setAttribute("quizList", quizList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/teacher/CourseMain.jsp");
		dispatcher.forward(request, response);
	}
}

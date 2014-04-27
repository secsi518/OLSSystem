/**
 * Author Fan Wang by 2014
 */
package com.ols.action;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ols.po.*;
import com.ols.dao.StudentDAO;
import com.ols.dao.TeacherDAO;
import com.ols.service.*;
import com.ols.util.QuestionUtil;

public class StuQuizMainAction extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		HttpSession httpSession = request.getSession();
		String quizID = request.getParameter("quizID");
		Quiz quiz = new Quiz();
		
		if(quizID == null){
			System.out.println("Quiz ID is empty.");
			quizID = "";
		}
		
		HashMap<String, String> totalQuestion = null;
		QuizServiceImpl quizServiceImpl = new QuizServiceImpl();
		QuestionServiceImpl questionServiceImpl = new QuestionServiceImpl();
		ArrayList<Question> arrayListQuestion = new ArrayList<Question>();
		
		
		if(quizID != null){
			arrayListQuestion = questionServiceImpl.getQuestionListByQuizID(quizID);
			totalQuestion = QuestionUtil.questionDisplay(arrayListQuestion);
			quiz =  quizServiceImpl.getQuiz(quizID);
			}
		
		httpSession.setAttribute("quiz", quiz);
		httpSession.setAttribute("questionList", totalQuestion);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/student/quizpage.jsp");
		dispatcher.forward(request, response);
	}
}
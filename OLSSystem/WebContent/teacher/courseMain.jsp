<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.ols.po.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz List</title>
<link media="all" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userindex.css">
<link rel="shortcut icon" href="favicon.ico" >
<link rel="icon" type="image/gif" href="icon.gif" >
<script type="text/javascript">
function loadXMLDoc(filename)
{ 
	var xmlhttp;
	if(window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari   
		xmlhttp=new XMLHttpRequest(); 
		}
	else
	 {// code for IE6, IE5 
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  } 
	xmlhttp.onreadystatechange=function(){ 
	  if(xmlhttp.readyState==4 && xmlhttp.status==200){
		document.getElementById("edit").innerHTML=xmlhttp.responseText;
      		}
	}
xmlhttp.open("GET",filename,true);
xmlhttp.send();
}

</script>
</head>
<body>
<%
Course course=(Course)session.getAttribute("course");
String courseID=(String)course.getCourseID();

ArrayList<Quiz> quizList=new ArrayList<Quiz>();
quizList=(ArrayList<Quiz>)session.getAttribute("quizList");

//System.out.println(quizList.get(1).getQuizID());
%>
<div id="page">
<div id="wrapper">
		<div id="header">
			<div id="title">
			
			<div id="user_nav">
					<div id='nav'>
					<ul>					   
					   <li class='active'><a href='../index.jsp'><span>Home</span></a></li>
					   <li class='active'><a href='index.jsp'><span>Course List</span></a></li>
					   <li class='active'><a href='index.jsp'><span>Serch Quiz</span></a></li>
					   <li class='last'><a href='#'><span>Help</span></a></li>
					   <div id="normala"><h3><%out.print("["+course.getCourseID()+"]"); %><a href="logoutaction">&nbsp;&nbsp;&nbsp;<img alt="as" src="${pageContext.request.contextPath}\img\logout.png">Logout&nbsp;&nbsp;&nbsp;</a></h3>
					</div>				   
					
					</ul>
					</div>
			</div>
		</div>
		</div>
		<div id="content">
			
				<div id="edit">
			 		<button id="button" onclick="loadXMLDoc('${pageContext.request.contextPath}/teacher/addQuizTable.jsp')"><img alt="as" src="${pageContext.request.contextPath}\img\add.png">Add Quiz</button>
			 		<div id="edit_div"> </div>
			    </div>
			    <div id="course_list">
				<table id="c_table">
				<tr>
				  <th width=20>Quiz ID</th>
				  <th width=50>Quiz Name</th>
				  <th width=30>Question No.</th>		
				  <th width=60>Due Date</th>
				  <th width=15>Edit</th>
				  </tr>
				<c:forEach items="${sessionScope['quizList']}" var="Quiz">
                <tr>
                
                    <td><a href="quizMainAction?quizID=${Quiz.quizID}"><c:out value="${Quiz.quizID}" /></a></td>
                    <td><c:out value="${Quiz.quizName}" /></td>
                    <td><c:out value="${Quiz.totalQuestionNumber}" /></td>
                    <td><c:out value="${Quiz.dueTime}" /></td>
                    <td><a href="editQuiz?quizID=${Quiz.quizID}"><img alt="as" src="${pageContext.request.contextPath}\img\edit.png"></a> <a href="deleteCourse?courseID=${Quiz.quizID}"><img alt="as" src="${pageContext.request.contextPath}\img\delete.png"></a></td>
                    
                </tr>
            	</c:forEach>
				
						
				</table>
				</div>
			
			
			</div>
		</div>

</div>
</body>
</html>
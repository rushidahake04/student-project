<%@page import="com.atdservices.pojo.Student"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<%
			Student student = (Student) request.getAttribute("studentRecordForUpdate");
		
		%>
		<form action="<%=request.getContextPath()%>/UpdateStudentServlet" method="post">
			<br>Student Number <input type="text" name="student_no"
				id="student_no" value="<%=student.getStudentNumber()%>"
				readonly="readonly"><br> <br>Student Name <input
				type="text" name="student_name" id="student_name"
				value="<%=student.getStudentName()%>"required><br> <br>Birth
			Date <input type="date" name="student_dob" id="student_dob"
				value="<%=student.getDateOfBirth()%>"required><br> <br>Joining
			Date <input type="date" name="student_doj" id="student_doj"
				value="<%=student.getDateOfJoining()%>"required><br> <br> 
			<input type="submit" value="submit"><br>
		</form>
		 <button type="button">
		<a href="student_home.jsp">Student Home</a>
	</button>
	</div>
</body>
</html>

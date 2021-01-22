<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="com.atdservices.pojo.Student"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%
	ArrayList<Student> list = (ArrayList) request.getAttribute("studentList");
%>

</head>
<body>
	<div align="center">
		<table align="center">
			<thead>Student Details
			</thead>
			<br>
			<tr>
				<th>Student Number &nbsp&nbsp</th>
				<th>Student Name &nbsp&nbsp</th>
				<th>Student DateofBirth &nbsp&nbsp</th>
				<th>Student DateofJoining&nbsp</th>
			</tr>
			<%
				for (Student student : list) {
					String studentNumber = student.getStudentNumber();
					String studentName = student.getStudentName();
					String studentDateOfBirth = student.getDateOfBirth();
					String studentDateOfJoining = student.getDateOfJoining();
			%>
			<tr>
				<td><%=studentNumber%></td>
				<td><%=studentName%></td>
				<td><%=studentDateOfBirth%></td>
				<td><%=studentDateOfJoining%></td>
			</tr>
			<%
				}
			%>
		</table>
		<br><button type="button">
		<a href="student_home.jsp">Back</a>
	</button>
	</div>
	
</body>
</html>
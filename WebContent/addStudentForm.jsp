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
<h1>Enter Student Details</h1>

<form action="<%=request.getContextPath()%>/AddStudentServlet" method="post">
<br>Student Number   <input type="text" name="student_no" id="student_no" ><br>
<br>Student Name <input type="text" name="student_name" id="student_name"><br>
<br>Birth Date   <input type="date" name="student_dob" id="student_dob"><br>
<br>Joining Date <input type="date" name="student_doj" id="student_doj"><br>
<br><input type="submit" value="submit">
<button type="button">
		<a href="student_home.jsp">Student Home</a>
	</button>
</form>
</div>
</body>
</html>
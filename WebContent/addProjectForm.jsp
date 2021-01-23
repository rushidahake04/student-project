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
<h1>Enter Project Details</h1>
<form action="<%=request.getContextPath()%>/AddProjectServlet" method="post">
<br>Project No   <input type="text" name="project_no" id="project_no" required ><br>
<br>Project Name <input type="text" name="project_name" id="project_name" required><br>
<br>Duration   <input type="number" name="project_duration" id="project_duration" required><br>
<br>Platform   <input type="text" name="project_platform" id="project_platform" required><br>
<br><input type="submit" value="submit"><br>
<button type="button">
		<a href="project_home.jsp">Student Home</a>
	</button>
</form>
</div>
</body>
</html>

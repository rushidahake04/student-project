<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.atdservices.pojo.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		Project project = (Project) request.getAttribute("projectRecordForUpdate");
	%>
	<form action="<%=request.getContextPath()%>/UpdateProjectServlet"
		method="post">
		<br>Project No <input type="text" name="project_no"
			id="project_no" value="<%=project.getProjectNumber()%>"
			readonly="readonly"><br> <br>Project Name <input
			type="text" name="project_name" id="project_name"
			value="<%=project.getProjectName()%>"><br> <br>Duration
		<input type="number" name="project_duration" id="project_duration"
			value="<%=project.getProjectDuration()%>"> <br> <br>Platform
		<input type="text" name="project_platform" id="project_platform"
			value="<%=project.getProjectPlatform()%>"><br> <br>
		<input type="submit" value="submit"><br>
	</form>
   <button type="button">
		<a href="project_home.jsp">Project Home</a>
	</button>
</body>
</html>
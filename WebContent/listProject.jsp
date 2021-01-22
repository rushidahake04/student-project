
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.atdservices.db.*"%>
<%@ page import="com.atdservices.pojo.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%
	ArrayList<Project> list = (ArrayList) request.getAttribute("projectList");
%>
</head>
<body>
	<div align="center">
		<table align="center">
			<thead>Project Details
			</thead>
			<br>
			<tr>
				<th>Project Number &nbsp&nbsp</th>
				<th>Project Name &nbsp&nbsp</th>
				<th>Project Duration &nbsp&nbsp</th>
				<th>Project Platform &nbsp</th>
			</tr>
			<%
				for (Project project : list) {
					String projectNumber = project.getProjectNumber();
					String projectName = project.getProjectName();
					int projectDuration = project.getProjectDuration();
					String projectPlatform = project.getProjectPlatform();
			%>
			<tr>
				<td><%=projectNumber%></td>
				<td><%=projectName%></td>
				<td><%=projectDuration%></td>
				<td><%=projectPlatform%></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
	<button type="button">
		<a href="project_home.jsp">Back</a>
	</button>
</body>
</html>
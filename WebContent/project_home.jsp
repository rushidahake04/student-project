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
	<h2>Student Operations</h2>
<button type="button"><a href="<%=request.getContextPath()%>/ProjectServlet">Project List</a></button>
<button type="button"><a href="addProjectForm.jsp">Add Project</a></button>
<button type="button"><a href="updateProjectForm.jsp">Update Project</a></button>
<button type="button"><a href="deleteProjectForm.jsp">Delete Project</a></button>
</div>
<br>	
<br><center><button type="button">
		<a href="index.jsp">Index</a>
	</button></center>
</body>
</html>
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
 <h3>Please Enter Carefully </h3>
   <form action="<%=request.getContextPath()%>/DeleteProjectServlet" method="post">
  Enter Project Number <input type="text" id=projectno name=projectno><br>
  <br> <input type="checkbox"  required>Agree To Delete Permanantly<br>
  <br> <input type="submit" value="submit">
  <button type="button">
		<a href="project_home.jsp">Student Home</a>
	</button>
   </form>
</div>
</body>
</html>
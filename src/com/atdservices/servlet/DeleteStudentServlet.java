package com.atdservices.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


/**
 * Servlet implementation class DeleteStudentServlet
 */

public class DeleteStudentServlet extends HttpServlet {
       	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String studentNumber= request.getParameter("studentno");
	if(deleteStudentUsingHttpClient(studentNumber))
	{
		response.sendRedirect("student_home.jsp");
	}	
	else
	{
	    response.sendRedirect("deleteStudentForm.jsp");	
	}		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}
	
	private boolean deleteStudentUsingHttpClient(String studentNumber) {
		boolean result = false;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpDelete deleteRequest = new HttpDelete(
					"http://localhost:8080/studentnproject/rest/student/delete/"+studentNumber);
			deleteRequest.addHeader("accept", "application/json");

			HttpResponse response = httpClient.execute(deleteRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			} else {
				result = true;
			}
			
			httpClient.getConnectionManager().shutdown();			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Output from Server "+result);
		
		return result;
	}

}

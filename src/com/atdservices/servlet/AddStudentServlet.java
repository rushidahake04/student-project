package com.atdservices.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;



public class AddStudentServlet extends HttpServlet {


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String studentNumber = request.getParameter("student_no");
		String studentName = request.getParameter("student_name");
		String dateOfBirth = request.getParameter("student_dob");
		String dateOfJoining = request.getParameter("student_doj");

		//Student student = new Student(studentNumber, studentName, studentDateOfBirth, studentDateOfJoining);

		boolean flag = addStudentUsingHttpClient(studentNumber, studentName, dateOfBirth, dateOfJoining);
		if (flag) {
			 response.sendRedirect(request.getContextPath()+"/StudentServlet");
		}else{
			response.sendRedirect("addStudentForm.jsp");
		}

	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	
	
	

	private boolean addStudentUsingHttpClient(String studentNumber, String studentName, String dateOfBirth, String dateOfJoining) {
		boolean result = false;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
					"http://localhost:8080/studentnproject/rest/student/add");
			postRequest.addHeader("accept", "application/json");
			
			String data = "{\"studentNumber\":\""
					+ studentNumber + 
					"\",\"studentName\":\""
					+ studentName +
					"\",\"dateOfBirth\":\""
					+ dateOfBirth + 
					"\",\"dateOfJoining\":\""
					+ dateOfJoining + "\"}";
			
			StringEntity input = new StringEntity(data);
	        input.setContentType("application/json");
	        postRequest.setEntity(input);

	        HttpResponse response = httpClient.execute(postRequest);

	        if (response.getStatusLine().getStatusCode() != 201) {
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

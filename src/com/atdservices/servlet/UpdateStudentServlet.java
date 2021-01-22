package com.atdservices.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String studentNumber = request.getParameter("student_no");
		String studentName = request.getParameter("student_name");
		String dateOfBirth = request.getParameter("student_dob");
		String dateOfJoining = request.getParameter("student_doj");

		updateStudentUsingHttpClient(studentNumber, studentName, dateOfBirth, dateOfJoining);
		response.sendRedirect("updateStudentForm.jsp");		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	

	private String updateStudentUsingHttpClient(String studentNumber, String studentName, String dateOfBirth, String dateOfJoining) {
		String result = null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
					"http://localhost:8080/studentnproject/rest/student/update");
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
	        if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}
	        BufferedReader br = new BufferedReader(
					new InputStreamReader((response.getEntity().getContent())));
			String output;
			while ((output = br.readLine()) != null) {
				result = output;
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

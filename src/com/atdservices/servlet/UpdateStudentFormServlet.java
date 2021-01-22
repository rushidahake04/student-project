package com.atdservices.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jettison.json.JSONObject;

import com.atdservices.pojo.Student;

public class UpdateStudentFormServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String studentNumber = request.getParameter("student_no");
		
		String result = searchStudentUsingHttpClient(studentNumber);
		if(result == null) {
			request.setAttribute("error","Could not find student");
			RequestDispatcher rd=request.getRequestDispatcher("error.jsp");
			rd.include(request, response);
		}
		Student student = getStudentFromJson(result);
		request.setAttribute("studentRecordForUpdate", student);
		RequestDispatcher rd=request.getRequestDispatcher("updateStudent.jsp");
		rd.include(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	

	private String searchStudentUsingHttpClient(String studentNumber) {
		String result = null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(
					"http://localhost:8080/studentnproject/rest/student/list/"+studentNumber);
			getRequest.addHeader("accept", "application/json");

			HttpResponse response = httpClient.execute(getRequest);
			 if (response.getStatusLine().getStatusCode() == 204) {
				return result;
			} 
			 else if (response.getStatusLine().getStatusCode() != 200) {
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
	
	private static Student getStudentFromJson(String json) {
		Student student = null;
		try {
			JSONObject inputJsonObj = new JSONObject(json);
			/* {"dateOfBirth":"1998-08-04T00:00:00+05:30","dateOfJoining":"2020-02-01T00:00:00+05:30","studentName":"Rushikesh","studentNumber":"1"} */
			String studentNumber = inputJsonObj.getString("studentNumber");
			String studentName = inputJsonObj.getString("studentName");
			String dateOfBirth = inputJsonObj.getString("dateOfBirth");
			String dateOfJoining = inputJsonObj.getString("dateOfJoining");
			student = new Student(studentNumber, studentName, dateOfBirth, dateOfJoining);
			
		} catch(Exception e) {
			e.printStackTrace();
		}

		return student;

	}
}

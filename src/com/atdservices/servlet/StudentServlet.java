package com.atdservices.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.atdservices.pojo.Student;
import com.atdservices.db.StudentDbOperation;;


public class StudentServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Student> list = new ArrayList<>();
		//get list of studtoents from rest service using httpclient
		String json = getStudentListUsingHttpClient();
		list = convertJsonToList(json);
		request.setAttribute("studentList", list);

		RequestDispatcher rd=request.getRequestDispatcher("listStudent.jsp");
		rd.include(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	private String getStudentListUsingHttpClient () {
		String result = null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(
					"http://localhost:8080/studentnproject/rest/student/list");
			getRequest.addHeader("accept", "application/json");

			HttpResponse response = httpClient.execute(getRequest);

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

	private List<Student> convertJsonToList(String json) {
		List<Student> list = new ArrayList();
		//final ObjectMapper objectMapper = new ObjectMapper();
		try {
			//Student[] langs = objectMapper.readValue(json, Student[].class);
			JSONObject jsonObject = new JSONObject(json);
			JSONArray jsonArray = jsonObject.getJSONArray("student");
			for(int i=0; i<jsonArray.length(); i++){
				JSONObject stud =  (JSONObject) jsonArray.get(i);
				String studentNumber = stud.getString("studentNumber");
				String studentName = stud.getString("studentName");
				String dateOfBirth = stud.getString("dateOfBirth");
				String dateOfJoining = stud.getString("dateOfJoining");
				
				list.add(new Student(studentNumber, studentName, dateOfBirth, dateOfJoining));
				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
}

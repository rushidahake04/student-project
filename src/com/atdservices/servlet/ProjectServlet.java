package com.atdservices.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import com.atdservices.pojo.Project;
public class ProjectServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Project> list = new ArrayList<>();
		//get list of projects from rest service using httpclient
		
		String json = getProjectListUsingHttpClient();
		list = convertJsonToList(json);
		request.setAttribute("projectList", list);

		RequestDispatcher rd=request.getRequestDispatcher("listProject.jsp");
		rd.include(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	private String getProjectListUsingHttpClient () {
		String result = null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(
					"http://localhost:8080/studentnproject/rest/project/list");
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

	private List<Project> convertJsonToList(String json) {
		List<Project> list = new ArrayList();
		//final ObjectMapper objectMapper = new ObjectMapper();
		try {
			
			JSONObject jsonObject = new JSONObject(json);
			JSONArray jsonArray = jsonObject.getJSONArray("project");
			for(int i=0; i<jsonArray.length(); i++){
				JSONObject project=  (JSONObject) jsonArray.get(i);
				String projectNumber = project.getString("projectNumber");
				String projectName =project.getString("projectName");
				int projectDuration = project.getInt("projectDuration");
				String projectPlatform = project.getString("projectPlatform");
				
				list.add(new Project(projectNumber, projectName, projectDuration, projectPlatform));
				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
}

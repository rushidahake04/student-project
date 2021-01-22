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

public class AddProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String projectNumber = request.getParameter("project_no");
		String projectName = request.getParameter("project_name");
		int projectDuration =  Integer.parseInt(request.getParameter("project_duration"));
		String projectPlatform = request.getParameter("project_platform");

		boolean flag = addProjectUsingHttpClient(projectNumber, projectName, projectDuration, projectPlatform);
		if (flag) {
			response.sendRedirect(request.getContextPath()+"/ProjectServlet");
		}else{
			response.sendRedirect("addProjectForm.jsp");
		}		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 doPost(request, response);
	}
	private boolean addProjectUsingHttpClient(String projectNumber, String projectName, int projectDuration,
			String projectPlatform) {

		boolean result = false;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
					"http://localhost:8080/studentnproject/rest/project/add");
			postRequest.addHeader("accept", "application/json");

			String data = "{\"projectNumber\":\""
					+ projectNumber + 
					"\",\"projectName\":\""
					+ projectName +
					"\",\"projectDuration\":\""
					+ projectDuration + 
					"\",\"projectPlatform\":\""
					+ projectPlatform + "\"}";

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

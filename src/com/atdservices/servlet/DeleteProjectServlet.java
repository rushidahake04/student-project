package com.atdservices.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;



public class DeleteProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String projectNumber = request.getParameter("projectno");
		if (deleteProjectUsingHttpClient(projectNumber)) {
			response.sendRedirect("project_home.jsp");
		}
		else
		{
			response.sendRedirect("deleteProjectForm.jsp");	
		}	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	private boolean deleteProjectUsingHttpClient(String projectNumber) {
		boolean result = false;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpDelete deleteRequest = new HttpDelete(
					"http://localhost:8080/studentnproject/rest/project/delete/"+projectNumber);
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

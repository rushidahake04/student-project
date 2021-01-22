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
import com.atdservices.pojo.Project;
public class UpdateProjectFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String projectNumber = request.getParameter("project_no");
		String result = searchProjectUsingHttpClient(projectNumber);

		if(result==null)
		{
			request.setAttribute("error","Could not find project");
			RequestDispatcher rd=request.getRequestDispatcher("error.jsp");
			rd.include(request, response);
		}
		Project project = getProjectFromJson(result);
		request.setAttribute("projectRecordForUpdate", project);
		RequestDispatcher rd=request.getRequestDispatcher("updateProject.jsp");
		rd.include(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}	

	private String searchProjectUsingHttpClient(String projectNumber) {
		String result = null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(
					"http://localhost:8080/studentnproject/rest/project/list/"+projectNumber);
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

	private static Project getProjectFromJson(String json) {
		Project project = null;
		try {
			JSONObject inputJsonObj = new JSONObject(json);

			String projectNumber = inputJsonObj.getString("projectNumber");
			String projectName = inputJsonObj.getString("projectName");
			int projectDuration = inputJsonObj.getInt("projectDuration");
			String projectPlatform = inputJsonObj.getString("projectPlatform");
			project = new Project(projectNumber, projectName, projectDuration, projectPlatform);

		} catch(Exception e) {
			e.printStackTrace();
		}

		return project;

	}
}

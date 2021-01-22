package com.atdservices.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.atdservices.pojo.Project;

public class ProjectDBOperation {

	public static ArrayList<Project> listProjects(){
		ArrayList<Project> list=new ArrayList<>();
		try {
			Connection con=ConnectionProvider.getConnection();
		
		Statement st= con.createStatement();
		ResultSet rs=st.executeQuery("select * from project");

		while(rs.next()) {
			String projectNumber=rs.getString("project_no");
			String projectName=rs.getString("project_name");
			int projectDuration=rs.getInt("project_dur");
			String projectPlatform=rs.getString("project_platform");

			Project project=new Project(projectNumber,projectName,projectDuration,projectPlatform);
			list.add(project);
		}
		}
		catch(Exception e) {
			//		System.out.println("Something went wrong in getprojects()");
			e.printStackTrace();
		}
		return list;
	}	

	public static boolean addProject(Project project) {
		boolean result=false;
		try {
			Connection con=ConnectionProvider.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement("insert into project(project_no,project_name,project_dur,project_platform) values(?,?,?,?)");
			ps.setString(1, project.getProjectNumber());		
			ps.setString(2, project.getProjectName());	
			ps.setInt(3, project.getProjectDuration());	
			ps.setString(4, project.getProjectPlatform());	
			ps.executeUpdate();		
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;	
	}

	public static boolean deleteProject(String projectNumber)
	{
		boolean result = false;
		try {
			Connection con=ConnectionProvider.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from project where project_no = ? " );
			ps.setString(1,projectNumber);
			ps.execute();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static boolean updateProject(Project project)
	{
		boolean result=false;
		try {
			Connection con=ConnectionProvider.getConnection();
			PreparedStatement ps=con.prepareStatement("update project set project_name=?,project_dur=?,project_platform=? where project_number=?");
			ps.setString(1,project.getProjectName());
			ps.setInt(2,project.getProjectDuration());
			ps.setString(3,project.getProjectPlatform());
			ps.setString(4,project.getProjectNumber());
			ps.executeUpdate();
			result=true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	
}

package com.atdservices.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static Connection con = null;

	public static Connection getConnection() throws SQLException{
		try {
			if(con == null) {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student-project","root","root");
				System.out.println("Conne");		
			}
		} catch (Exception e) {
			System.out.print("Something Went wrong");
			e.printStackTrace();
		}

		return con;
	}
}

package com.atdservices.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.atdservices.pojo.Student;

public class StudentDbOperation {

	public static ArrayList<Student> getStudents() {
		ArrayList<Student> list = new ArrayList<>();
		try {
			Connection con = ConnectionProvider.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from student");

			while(rs.next()) {
				String student_no = rs.getString("student_no");
				String student_name = rs.getString("student_name");
				String student_dob = rs.getString("student_dob");
				String student_doj = rs.getString("student_doj");

				Student s = new Student(student_no,student_name,student_dob,student_doj);
				list.add(s);
			}
		}
		catch(Exception e) {
			System.out.println("Something went wrong in Patient Dao");
			e.printStackTrace();
		}

		return list;
	}
	
	public static Student getStudent(String studentNumber) {
		Student studentResult = null;
		try {
			Connection con = ConnectionProvider.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from student  where student_no = "+studentNumber);

			while(rs.next()) {
				String student_no = rs.getString("student_no");
				String student_name = rs.getString("student_name");
				String student_dob = rs.getString("student_dob");
				String student_doj = rs.getString("student_doj");
				studentResult = new Student(student_no,student_name,student_dob,student_doj);		
			}
		}
		catch(Exception e) {
			System.out.println("Something went wrong in Patient Dao");
			e.printStackTrace();
		}
		return studentResult;
	}

	public static boolean addStudent(Student student) {
		boolean result = false;
		
		try {
			Connection con = ConnectionProvider.getConnection();
			Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(student.getDateOfBirth());
			// because PreparedStatement#setDate(..) expects a java.sql.Date argument
			java.sql.Date sqlDobDate = new java.sql.Date(dob.getTime()); 
			Date doj = new SimpleDateFormat("yyyy-MM-dd").parse(student.getDateOfJoining());
			java.sql.Date sqlDojDate = new java.sql.Date(doj.getTime()); 

			PreparedStatement ps= con.prepareStatement("insert into student(student_no,student_name,student_dob,student_doj) values(?,?,?,?)");
			ps.setString(1, student.getStudentNumber());		
			ps.setString(2, student.getStudentName());	
			ps.setDate(3, sqlDobDate);	
			ps.setDate(4, sqlDojDate);	

			ps.executeUpdate();		
			result = true;
		} catch (SQLException e) {
			System.out.println("Something went wrong in Patient Dao");
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		
		return result;
	}

	
	public static boolean updateStudent(Student student) {
		boolean result = false;
		try {
			Connection con = ConnectionProvider.getConnection();
			Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(student.getDateOfBirth());
			// because PreparedStatement#setDate(..) expects a java.sql.Date argument
			java.sql.Date sqlDobDate = new java.sql.Date(dob.getTime()); 
			Date doj = new SimpleDateFormat("yyyy-MM-dd").parse(student.getDateOfJoining());
			java.sql.Date sqlDojDate = new java.sql.Date(doj.getTime()); 

			PreparedStatement ps= con.prepareStatement("update student set student_name = ?,student_dob = ?,student_doj = ? where student_no = ?;");
			ps.setString(1, student.getStudentName());	
			ps.setDate(2, sqlDobDate);	
			ps.setDate(3, sqlDojDate);	
			ps.setString(4, student.getStudentNumber());
			result = ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Something went wrong in Patient Dao");
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} 

		return result;
	}

	
	public static boolean deleteStudent(String studentNumber) {
		boolean result = false;
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement ps= con.prepareStatement("delete from student where student_no = ? ");
			ps.setString(1, studentNumber);		
			ps.execute();		
			result = true;
		} catch (SQLException e) {
			System.out.println("Something went wrong in Patient Dao");
			e.printStackTrace();
		} 

		return result;
	}

	public static Student searchStudent(String studentNumber)
	{  Student student=null;
		try {
		Connection con = ConnectionProvider.getConnection();
		Statement st = con.createStatement();
		PreparedStatement ps= con.prepareStatement("select * from student where student_no = ? ");
		ps.setString(1, studentNumber);	
		ResultSet rs = ps.executeQuery();
     	
    	 while(rs.next()) {
     
			String student_no = rs.getString("student_no");
			String student_name = rs.getString("student_name");
			String student_dob = rs.getString("student_dob");
			String student_doj = rs.getString("student_doj");

			student = new Student(student_no,student_name,student_dob,student_doj);
			
		} 
	}
	catch (SQLException e) {
		
		e.printStackTrace();
	} 
		
	
	return student;
	
	}
	
 }

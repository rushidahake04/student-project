package com.atdservices.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {
	private String studentNumber; 
	private String studentName; 
	private String dateOfBirth;
	private String dateOfJoining;

	public Student() { 	}

	public Student(String studentNumber, String studentName, String dateOfBirth, String dateOfJoining) {
		this.studentNumber = studentNumber;
		this.studentName = studentName;
		this.dateOfBirth = dateOfBirth;
		this.dateOfJoining = dateOfJoining;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

}

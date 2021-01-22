package com.atdservices.pojo;

public class Project {
	private String projectNumber;
	private String projectName;
	private int projectDuration;
	private String projectPlatform;
	public Project(String projectNumber, String projectName, int projectDuration, String projectPlatform) {
		this.projectNumber = projectNumber;
		this.projectName = projectName;
		this.projectDuration = projectDuration;
		this.projectPlatform = projectPlatform;
	}
	public String getProjectNumber() {
		return projectNumber;
	}
	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getProjectDuration() {
		return projectDuration;
	}
	public void setProjectDuration(int projectDuration) {
		this.projectDuration = projectDuration;
	}
	public String getProjectPlatform() {
		return projectPlatform;
	}
	public void setProjectPlatform(String projectPlatform) {
		this.projectPlatform = projectPlatform;
	}

}

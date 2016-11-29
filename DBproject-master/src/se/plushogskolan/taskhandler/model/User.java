package se.plushogskolan.taskhandler.model;

import java.util.LinkedHashMap;

public final class User {

	private int id;
	private int teamid;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private boolean isActive;
	
	public User(String firstName, String lastName, String userName, String password, String isActive, int teamid) {
		this.isActive = isActive.equals("1") ? true : false;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.teamid = teamid;
	}

	public User() {
		
	}

	@Override
	public String toString() {	
		return "Used id=" + id + "firstname: " + firstName;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTeam_id() {
		return teamid;
	}

	public void setTeam_id(int team_id) {
		this.teamid = team_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getVariable(String variable) {
		switch (variable) {
		case "FIRSTNAME":
			return getFirstName();
		case "LASTNAME":
			return getLastName();
		case "USERNAME":
			return getUserName();
		case "TEAMID":
			return "" + getTeam_id();
		case "ID":
			return "" + getId();
		}
		return "";
	}
	public LinkedHashMap<String,String> getVariableMap(){
		LinkedHashMap<String,String> variableList = new LinkedHashMap<>();
		variableList.put("firstName", this.firstName);
		variableList.put("lastName", this.lastName);
		variableList.put("userName", this.userName);
		variableList.put("password", this.password);
		variableList.put("isActive", this.isActive ? "1" : "0");
		variableList.put("teamid", ""+this.teamid);	
		return variableList;
	}

}
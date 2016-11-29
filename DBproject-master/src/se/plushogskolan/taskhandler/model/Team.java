package se.plushogskolan.taskhandler.model;

import java.util.LinkedHashMap;

public class Team {

	private int id;
	private String teamName;
	private boolean isTeamActive;

	public Team(String teamName, String isActive) {
		this.teamName = teamName;
		this.isTeamActive = isActive.equals("1") ? true : false;
	}

	@Override
	public String toString() {
		return "id =" + " " + id + " Teamname= " + teamName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isTeamActive() {
		return isTeamActive;
	}

	public void setTeamActive(boolean isTeamActive) {
		this.isTeamActive = isTeamActive;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamName() {
		return teamName;
	}

	public String getVariable(String variable) {
		switch (variable) {
		case "TEAMNAME":
			return getTeamName();
		}
		return "";
	}

	public LinkedHashMap<String, String> getVariableMap() {
		LinkedHashMap<String, String> variableList = new LinkedHashMap<>();
		variableList.put("teamName", this.teamName);
		variableList.put("isActive", this.isTeamActive ? "1" : "0");
		return variableList;
	}

}
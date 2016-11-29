package se.plushogskolan.taskhandler.model;

import java.util.LinkedHashMap;

public class Issue {

	private int id;
	private int workItemId;
	private String reason;
	
	public Issue(String reason, int workItemId) {
		this.workItemId = workItemId;
		this.reason = reason;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWorkItemId() {
		return workItemId;
	}

	public void setWorkItemId(int workItemId) {
		this.workItemId = workItemId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public LinkedHashMap<String, String> getVariableMap(){
		LinkedHashMap<String, String> variableMap= new LinkedHashMap<>();
		variableMap.put("reason", this.reason);
		variableMap.put("workItemId", ""+this.workItemId);
		return variableMap;
	}
		
}
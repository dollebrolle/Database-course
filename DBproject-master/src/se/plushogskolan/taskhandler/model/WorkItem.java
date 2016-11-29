package se.plushogskolan.taskhandler.model;

import java.util.LinkedHashMap;

import se.plushogskolan.taskhandler.assets.enums.WorkItemStatus;

public class WorkItem {
	
	private int id;
	private int userId;
	private WorkItemStatus status;
	private String name;	
	
	public WorkItem(String name, WorkItemStatus workItemStatus, int userId){
		this.userId = userId;
		this.name = name;
		this.status = workItemStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public WorkItemStatus getStatus() {
		return status;
	}

	public void setStatus(WorkItemStatus status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedHashMap<String, String> getVariableMap(){
		LinkedHashMap<String, String> variableMap= new LinkedHashMap<>();
		variableMap.put("USERID", ""+this.userId);
		variableMap.put("STATUS", this.status.name());
		variableMap.put("NAME", this.name);
		return variableMap;
	}
}
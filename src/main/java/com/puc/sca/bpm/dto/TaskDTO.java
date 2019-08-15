package com.puc.sca.bpm.dto;

public class TaskDTO {

	private String id;
	private String name;
	private String taskDefinitionKey;
	private String actionType;

	public TaskDTO() {
	}

	public TaskDTO(String id, String taskDefinitionKey, String name) {
		this.id = id;
		this.taskDefinitionKey = taskDefinitionKey;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

}

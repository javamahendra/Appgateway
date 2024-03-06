package com.app.handler;

public class Param {
	
	private String name;
	private boolean isMandatory;
	
	public Param(String name, boolean isMandatory){
		this.name = name;
		this.isMandatory = isMandatory;
	}

	public String getName() {
		return name;
	}

	public boolean isMandatory() {
		return isMandatory;
	}

}

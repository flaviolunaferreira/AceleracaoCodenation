package com.cit.projetoPratico.Model.Entity;

public enum LevelEnum {

	ERROR("error"),
	WARNING("warning"),
	INFO("info");
	
	private String description;
	
	LevelEnum(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
}

package com.cit.projetoPratico.model.entity;

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

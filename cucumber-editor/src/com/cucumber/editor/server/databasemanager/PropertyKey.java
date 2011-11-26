package com.cucumber.editor.server.databasemanager;

/**
 * filename: Common.PropertyKey.java ----------------------
 * 
 * @date: 06.05.2011
 * @author: Markus Vieghofer
 * 
 */
public enum PropertyKey {
	DATABASE_PATH("database.path"),
	DATABASE_TYPE("database.type");
	
	private String value;

	private PropertyKey(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

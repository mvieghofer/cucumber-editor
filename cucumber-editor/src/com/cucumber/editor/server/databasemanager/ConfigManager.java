package com.cucumber.editor.server.databasemanager;

/**
 * filename: Common.ConfigManager.java
 * ----------------------
 *     @date: 06.05.2011
 *   @author: Markus Vieghofer
 *
 */
public class ConfigManager {
	private static final String path = "cucumber_editor/settings.xml";
	private ConfigParser parser;
	
	private static ConfigManager instance = new ConfigManager();
	
	public static ConfigManager getInstance() {
		return instance;
	}
	
	private ConfigManager() {
		parser = new ConfigParser(path);
	}
	
	public String getProperty(PropertyKey key){
		return parser.getProperty(key.getValue());
	}
}

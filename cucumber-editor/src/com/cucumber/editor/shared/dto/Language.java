/**
 */
package com.cucumber.editor.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * filename: Language.java
 *
 *     @date: 26.06.2011
 *   @author: Markus Vieghofer
 *
 */
public class Language implements IsSerializable {

	private String language;
	private String languageKey;
	private int id;
	
	public Language() {
		
	}
	
	public Language (int id, String languageKey, String language) {
		setId(id);
		setLanguageKey(languageKey);
		setLanguage(language);
	}
	
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @param languageKey the languageKey to set
	 */
	public void setLanguageKey(String languageKey) {
		this.languageKey = languageKey;
	}

	/**
	 * @return the languageKey
	 */
	public String getLanguageKey() {
		return languageKey;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}

/**
 */
package com.cucumber.editor.client.services;

import java.util.List;

import com.cucumber.editor.shared.dto.Language;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * filename: LanguageServiceAsync.java
 *
 *     @date: 29.08.2011
 *   @author: Markus Vieghofer
 *
 */
public interface LanguageServiceAsync {

	/**
	 * 
	 * @see com.cucumber.editor.client.services.LanguageService#getByKey(java.lang.String)
	 */
	void getByKey(String key, AsyncCallback<Language> callback);

	/**
	 * 
	 * @see com.cucumber.editor.client.services.LanguageService#getLanguages()
	 */
	void getLanguages(AsyncCallback<List<Language>> callback);

}

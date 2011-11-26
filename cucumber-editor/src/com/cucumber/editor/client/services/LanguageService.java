/**
 */
package com.cucumber.editor.client.services;

import java.util.List;

import com.cucumber.editor.client.exceptions.DatabaseException;
import com.cucumber.editor.shared.dto.Language;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * filename: LanguageDao.java
 * 
 * @date: 24.08.2011
 * @author: Markus Vieghofer
 * 
 */

@RemoteServiceRelativePath("LanguageService")
public interface LanguageService extends RemoteService {
	public List<Language> getLanguages() throws DatabaseException;
	public Language getByKey(String key);
}

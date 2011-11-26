/**
 */
package com.cucumber.editor.client.services;

import java.util.List;

import com.cucumber.editor.shared.dto.Keyword;
import com.cucumber.editor.shared.dto.Language;
import com.cucumber.editor.shared.dto.SentenceDto;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * filename: SentenceServiceAsync.java
 *
 *     @date: 29.08.2011
 *   @author: Markus Vieghofer
 *
 */
public interface SentenceServiceAsync {

	void getSentences(Language language, Keyword keyword,
			String sentenceTemplate, AsyncCallback<List<SentenceDto>> callback);

}

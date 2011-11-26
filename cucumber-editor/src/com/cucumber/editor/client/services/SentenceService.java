/**
 */
package com.cucumber.editor.client.services;

import java.util.List;

import com.cucumber.editor.shared.dto.Keyword;
import com.cucumber.editor.shared.dto.Language;
import com.cucumber.editor.shared.dto.SentenceDto;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * filename: SentenceService.java
 *
 *     @date: 24.08.2011
 *   @author: Markus Vieghofer
 *
 */

@RemoteServiceRelativePath("SentenceService")
public interface SentenceService extends RemoteService {
	public List<SentenceDto> getSentences(Language language, Keyword keyword,
			String sentenceTemplate);
}

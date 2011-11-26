/**
 */
package com.cucumber.editor.client.place;

import java.util.List;

import com.cucumber.editor.client.activity.AbstractScenarioActivity;

/**
 * filename: AbstractScenarioPlace.java
 *
 *     @date: 02.09.2011
 *   @author: Markus Vieghofer
 *
 */
public interface AbstractScenarioPlace {
	public List<TagPlace> getTagList();
	public void setTagList(List<TagPlace> tagList);
	public void addTag(TagPlace tag);
	public boolean removeTag(TagPlace tag);
	
	public List<SentencePlace> getSentenceList();
	public void setSentenceList(List<SentencePlace> sentenceList);
	public void addSentence(SentencePlace sentence);
	public boolean removeSentence(SentencePlace sentence);
	
	public String getTitle();
	public void setTitle(String title);
	
	public String getDescription();
	public void setDescription(String description);
	/**
	 * @return
	 */
	public AbstractScenarioActivity getActivity();
	
	public String getScenario();
	public void setScenario(String scenario);
}

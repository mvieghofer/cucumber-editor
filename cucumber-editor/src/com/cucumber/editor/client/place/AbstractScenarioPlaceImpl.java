/**
 */
package com.cucumber.editor.client.place;

import java.util.LinkedList;
import java.util.List;

import com.cucumber.editor.client.activity.AbstractScenarioActivity;
import com.cucumber.editor.client.activity.ActivityFactory;

/**
 * filename: AbstractScenarioPlaceImpl.java
 * 
 * @date: 02.09.2011
 * @author: Markus Vieghofer
 * 
 */
public class AbstractScenarioPlaceImpl implements AbstractScenarioPlace {

	private List<TagPlace> tagList = new LinkedList<TagPlace>();
	private List<SentencePlace> sentenceList = new LinkedList<SentencePlace>();
	private String title = "";
	private String description = "";
	private String scenario = "";
	private ActivityFactory factory;
	private AbstractScenarioActivity activity;

	public AbstractScenarioPlaceImpl() {

	}

	public AbstractScenarioPlaceImpl(List<TagPlace> tagList,
			List<SentencePlace> sentenceList) {
		setTagList(tagList);
		setSentenceList(sentenceList);
	}

	/**
	 * @see com.cucumber.editor.client.place.AbstractScenarioPlace#getTagList()
	 */
	@Override
	public List<TagPlace> getTagList() {
		return tagList;
	}

	/**
	 * @see com.cucumber.editor.client.place.AbstractScenarioPlace#setTagList(java.util.List)
	 */
	@Override
	public void setTagList(List<TagPlace> tagList) {
		this.tagList = tagList;
	}

	/**
	 * @see com.cucumber.editor.client.place.AbstractScenarioPlace#addTag(com.cucumber.editor.client.place.TagPlace)
	 */
	@Override
	public void addTag(TagPlace tag) {
		tagList.add(tag);
	}

	/**
	 * @see com.cucumber.editor.client.place.AbstractScenarioPlace#removeTag(com.cucumber.editor.client.place.TagPlace)
	 */
	@Override
	public boolean removeTag(TagPlace tag) {
		return tagList.remove(tag);
	}

	/**
	 * @see com.cucumber.editor.client.place.AbstractScenarioPlace#getSentenceList()
	 */
	@Override
	public List<SentencePlace> getSentenceList() {
		return sentenceList;
	}

	/**
	 * @see com.cucumber.editor.client.place.AbstractScenarioPlace#setSentenceList(java.util.List)
	 */
	@Override
	public void setSentenceList(List<SentencePlace> sentenceList) {
		this.sentenceList = sentenceList;
	}

	/**
	 * @see com.cucumber.editor.client.place.AbstractScenarioPlace#addSentence(com.cucumber.editor.client.place.SentencePlace)
	 */
	@Override
	public void addSentence(SentencePlace sentence) {
		sentenceList.add(sentence);
	}

	/**
	 * @return
	 * @see com.cucumber.editor.client.place.AbstractScenarioPlace#removeSentence(com.cucumber.editor.client.place.SentencePlace)
	 */
	@Override
	public boolean removeSentence(SentencePlace sentence) {
		return sentenceList.remove(sentence);
	}

	/**
	 * @see com.cucumber.editor.client.place.AbstractScenarioPlace#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/**
	 * @see com.cucumber.editor.client.place.AbstractScenarioPlace#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @see com.cucumber.editor.client.place.AbstractScenarioPlace#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * @see com.cucumber.editor.client.place.AbstractScenarioPlace#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @see com.cucumber.editor.client.place.AbstractScenarioPlace#getActivity()
	 */
	@Override
	public AbstractScenarioActivity getActivity() {
		if (activity == null) {
			activity = getFactory().createAbstractScenarioActivity(this);
		}
		return activity;
	}

	/**
	 * @return the factory
	 */
	public ActivityFactory getFactory() {
		if (factory == null) {
			factory = ActivityFactory.getInstance();
		}
		return factory;
	}

	/**
	 * @see com.cucumber.editor.client.place.AbstractScenarioPlace#getScenario()
	 */
	@Override
	public String getScenario() {
		return scenario;
	}

	/**
	 * @see com.cucumber.editor.client.place.AbstractScenarioPlace#setScenario(java.lang.String)
	 */
	@Override
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

}

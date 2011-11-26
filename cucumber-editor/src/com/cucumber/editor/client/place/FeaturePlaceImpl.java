/**
 */
package com.cucumber.editor.client.place;

import java.util.LinkedList;
import java.util.List;

import com.cucumber.editor.client.activity.ActivityFactory;
import com.cucumber.editor.client.activity.FeatureActivity;

/**
 * filename: FeaturePlaceImpl.java
 * 
 * @date: 04.09.2011
 * @author: Markus Vieghofer
 * 
 */
public class FeaturePlaceImpl implements FeaturePlace {

	private ActivityFactory factory;
	private FeatureActivity activity;

	private String language = "";
	private String title = "";
	private String description = "";
	private String feature = "";

	private List<TagPlace> tagList = new LinkedList<TagPlace>();
	private List<AbstractScenarioPlace> scenarioList = new LinkedList<AbstractScenarioPlace>();

	/**
	 * @see com.cucumber.editor.client.place.FeaturePlace#getActivity()
	 */
	@Override
	public FeatureActivity getActivity() {
		if (activity == null) {
			activity = getFactory().createFeatureActivity(this);
		}
		return activity;
	}

	/**
	 * @see com.cucumber.editor.client.place.FeaturePlace#getAbstractScenarioList()
	 */
	@Override
	public List<AbstractScenarioPlace> getAbstractScenarioList() {
		return scenarioList;
	}

	/**
	 * @see com.cucumber.editor.client.place.FeaturePlace#setAbstractScenarioList(java.util.List)
	 */
	@Override
	public void setAbstractScenarioList(List<AbstractScenarioPlace> scenarioList) {
		this.scenarioList = scenarioList;
	}

	/**
	 * @see com.cucumber.editor.client.place.FeaturePlace#addAbstractScenario(com.cucumber.editor.client.view.AbstractScenario)
	 */
	@Override
	public boolean addAbstractScenario(AbstractScenarioPlace scenario) {
		return scenarioList.add(scenario);
	}

	/**
	 * @see com.cucumber.editor.client.place.FeaturePlace#removeAbstractScenario(com.cucumber.editor.client.view.AbstractScenario)
	 */
	@Override
	public boolean removeAbstractScenario(AbstractScenarioPlace scenario) {
		return scenarioList.remove(scenario);
	}

	/**
	 * @see com.cucumber.editor.client.place.FeaturePlace#getTagList()
	 */
	@Override
	public List<TagPlace> getTagList() {
		return tagList;
	}

	/**
	 * @see com.cucumber.editor.client.place.FeaturePlace#setTagList(java.util.List)
	 */
	@Override
	public void setTagList(List<TagPlace> tagList) {
		this.tagList = tagList;
	}

	/**
	 * @return
	 * @see com.cucumber.editor.client.place.FeaturePlace#addTag(com.cucumber.editor.client.view.Tag)
	 */
	@Override
	public boolean addTag(TagPlace tag) {
		return tagList.add(tag);
	}

	/**
	 * @see com.cucumber.editor.client.place.FeaturePlace#removeTag(com.cucumber.editor.client.view.Tag)
	 */
	@Override
	public boolean removeTag(TagPlace tag) {
		return tagList.remove(tag);
	}

	/**
	 * @see com.cucumber.editor.client.place.FeaturePlace#getLanguage()
	 */
	@Override
	public String getLanguage() {
		return language;
	}

	/**
	 * @see com.cucumber.editor.client.place.FeaturePlace#setLanguage()
	 */
	@Override
	public void setLanguage(String language) {
		this.language = "# language: " + language;
	}

	/**
	 * @see com.cucumber.editor.client.place.FeaturePlace#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/**
	 * @see com.cucumber.editor.client.place.FeaturePlace#setTitle()
	 */
	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @see com.cucumber.editor.client.place.FeaturePlace#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * @see com.cucumber.editor.client.place.FeaturePlace#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @see com.cucumber.editor.client.place.FeaturePlace#insertAbstractScenario(int, com.cucumber.editor.client.place.AbstractScenarioPlace)
	 */
	@Override
	public void insertAbstractScenario(int indexBefore,
			AbstractScenarioPlace scenario) {
		scenarioList.add(indexBefore, scenario);
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
	 * @see com.cucumber.editor.client.place.FeaturePlace#getFeature()
	 */
	@Override
	public String getFeature() {
		return feature;
	}

	/**
	 * @see com.cucumber.editor.client.place.FeaturePlace#setFeature(java.lang.String)
	 */
	@Override
	public void setFeature(String feature) {
		this.feature = feature;
	}

}

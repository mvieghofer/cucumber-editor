/**
 */
package com.cucumber.editor.client.place;

import java.util.List;

import com.cucumber.editor.client.activity.FeatureActivity;

/**
 * filename: FeaturePlace.java
 *
 *     @date: 04.09.2011
 *   @author: Markus Vieghofer
 *
 */
public interface FeaturePlace {
	
	public List<AbstractScenarioPlace> getAbstractScenarioList();
	public void setAbstractScenarioList(List<AbstractScenarioPlace> scenarioList);
	public boolean addAbstractScenario(AbstractScenarioPlace scenario);
	public void insertAbstractScenario(int indexBefore, AbstractScenarioPlace scenario);
	public boolean removeAbstractScenario(AbstractScenarioPlace scenario);

	public List<TagPlace> getTagList();
	public void setTagList(List<TagPlace> tagList);
	public boolean addTag(TagPlace tag);
	public boolean removeTag(TagPlace tag);
	
	public String getLanguage();
	public void setLanguage(String language);
	
	public String getTitle();
	public void setTitle(String title);
	
	public String getDescription();
	public void setDescription(String description);
	
	public FeatureActivity getActivity();
	/**
	 * @return
	 */
	public String getFeature();
	public void setFeature(String feature);
}

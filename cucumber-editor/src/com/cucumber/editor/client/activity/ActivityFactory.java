/**
 */
package com.cucumber.editor.client.activity;

import com.cucumber.editor.client.place.AbstractScenarioPlace;
import com.cucumber.editor.client.place.FeaturePlace;
import com.cucumber.editor.client.place.SentencePlace;
import com.cucumber.editor.client.place.TagPlace;

/**
 * filename: ActivityFactory.java
 * 
 * @date: 02.09.2011
 * @author: Markus Vieghofer
 * 
 */
public class ActivityFactory {

	private static ActivityFactory instance = new ActivityFactory();

	/**
	 * @return
	 */
	public static ActivityFactory getInstance() {
		return instance;
	}

	private ActivityFactory() {

	}

	public AbstractScenarioActivity createAbstractScenarioActivity() {
		return new AbstractScenarioActivityImpl();
	}
	
	public AbstractScenarioActivity createAbstractScenarioActivity(AbstractScenarioPlace place) {
		return new AbstractScenarioActivityImpl(place);
	}

	/**
	 * @return
	 */
	public TagActivity createTagActivity() {
		return new TagActivityImpl();
	}

	/**
	 * @return
	 */
	public TagActivity createTagActivity(TagPlace place) {
		return new TagActivityImpl(place);
	}

	public SentenceActivity createSentenceActivity() {
		return new SentenceActivityImpl();
	}
	
	public SentenceActivity createSentenceActivity(SentencePlace place) {
		return new SentenceActivityImpl(place);
	}

	/**
	 * @return
	 */
	public FeatureActivity createFeatureActivity() {
		return new FeatureActivityImpl();
	}
	
	public FeatureActivity createFeatureActivity(FeaturePlace featurePlace) {
		return new FeatureActivityImpl(featurePlace);
	}
	
	public LocaleManager createLocaleManager() {
	  return LocaleManagerImpl.getInstance();
	}

}

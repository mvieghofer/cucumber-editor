/**
 */
package com.cucumber.editor.client.view;

import com.cucumber.editor.client.CucumberEditor;
import com.google.gwt.user.client.ui.TextBox;

/**
 * filename: WidgetFactory.java
 * 
 * @date: 24.08.2011
 * @author: Markus Vieghofer
 * 
 */
public class WidgetFactory {
	private static WidgetFactory instance = new WidgetFactory();

	public static WidgetFactory getInstance() {
		return instance;
	}

	private WidgetFactory() {

	}

	public AbstractScenario createBackground(String title) {
		return new BackgroundImpl(title);
	}

	public AbstractScenario createBackground(Feature feature, String title) {
		return new BackgroundImpl(feature, title);
	}

	public AbstractScenario createBackground(Feature feature,
			AbstractScenario scenario, String title) {
		return new BackgroundImpl(feature, scenario, title);
	}

	public Feature createFeature(CucumberEditor parent) {
		Feature feature = FeatureImpl.getInstance();
		feature.setParent(parent);
		return feature;
	}

	public AbstractScenario createScenario() {
		return new ScenarioImpl();
	}

	public AbstractScenario createScenario(String title) {
		return new ScenarioImpl(title);
	}

	public AbstractScenario createScenario(Feature feature, String title) {
		return new ScenarioImpl(feature, title);
	}

	public AbstractScenario createScenario(Feature feature,
			AbstractScenario scenario, String title) {
		return new ScenarioImpl(feature, scenario, title);
	}

	public AbstractScenario createScenarioOutline(String title) {
		return new ScenarioOutlineImpl(title);
	}

	public AbstractScenario createScenarioOutline(Feature feature, String title) {
		return new ScenarioOutlineImpl(feature, title);
	}

	public AbstractScenario createScenarioOutline(Feature feature,
			AbstractScenario scenario, String title) {
		return new ScenarioOutlineImpl(feature, scenario, title);
	}

	public Tag createTag() {
		return new TagImpl();
	}

	public Tag createTag(Tagged tagged) {
		return new TagImpl(tagged);
	}

	/**
	 * @param scenarioOutlineImpl
	 * @param tag
	 * @return
	 */
	public Tag createTag(Tagged tagged, String tag) {
		return new TagImpl(tagged, tag);
	}

	public Sentence createSentence(AbstractScenario scenario, String keyword, String sentence) {
		return new SentenceImpl(scenario, keyword, sentence);
	}

	public Sentence createSentence(AbstractScenario scenario) {
		return new SentenceImpl(scenario);
	}

	/**
	 * @param cucumberEditor
	 * @return
	 */
	public Preview createPreview(CucumberEditor cucumberEditor) {
		return new PreviewImpl(cucumberEditor);
	}

	/**
	 * @param cucumberEditor
	 * @param feature
	 * @return
	 */
	public Preview createPreview(CucumberEditor cucumberEditor, Feature feature) {
		return new PreviewImpl(cucumberEditor, feature);
	}

	/**
	 * @param cucumberEditor
	 * @return
	 */
	public Login createLogin(CucumberEditor cucumberEditor) {
		return new LoginImpl(cucumberEditor);
	}

	/**
	 * @param cucumberEditor
	 * @param isAdmin
	 * @return
	 */
	public Settings createSettings(CucumberEditor cucumberEditor,
			boolean isAdmin) {
		if (isAdmin) {
			return new SettingsImpl(cucumberEditor);
		} else {
			return new SettingsImpl(cucumberEditor);
		}
	}
	
	public Example createExample (ScenarioOutlineImpl scenarioOutlineImpl) {
		return new ExampleImpl(scenarioOutlineImpl);
	}

	/**
	 * @param feature
	 * @param sentenceImpl
	 * @return
	 */
	public AbstractScenario createScenarioOutline(Feature feature,
			AbstractScenario scenario) {
		return new ScenarioOutlineImpl(feature, scenario);
	}
	
	public TextBox createVariableSentencePart(String sentencePart, Sentence sentence) {
	  return new VariableSentencePartImpl(sentencePart, sentence);
	}
}

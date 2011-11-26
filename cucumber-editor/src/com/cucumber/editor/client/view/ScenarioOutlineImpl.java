/**
 */
package com.cucumber.editor.client.view;

import com.cucumber.editor.client.exceptions.DuplicateKeyException;
import com.cucumber.editor.client.place.SentencePlace;
import com.cucumber.editor.client.place.TagPlace;

/**
 * filename: ScenarioOutlineImpl.java
 *
 *     @date: 18.08.2011
 *   @author: Markus Vieghofer
 *
 */
public class ScenarioOutlineImpl extends ScenarioImpl implements
		Tagged {
	
	private WidgetFactory factory;
	private Example example;

	public ScenarioOutlineImpl(String title) {
		init();
		configScenarioOutline(title);
	}

	public ScenarioOutlineImpl(Feature feature, String title) {
		init();
		setFeature(feature);
		configScenarioOutline(title);
	}
	
	public ScenarioOutlineImpl(Feature feature, AbstractScenario scenario, String title) {
		init();
		setFeature(feature);
		configScenarioOutline(title, scenario);
	}
	
	/**
	 * @param feature
	 * @param scenario
	 */
	public ScenarioOutlineImpl(Feature feature, AbstractScenario scenario) {
	  init();
    setFeature(feature);
    configScenarioOutline(loadScenarioOutlineKeyword(), scenario);
	}

	/**
   * @return
   */
  private String loadScenarioOutlineKeyword() {
    String keyword = "Scenario Outline:"; // TODO load from database
    return keyword;
  }

  private void configScenarioOutline(String title) {
		lblScenario.setText(title);
		txtTitle.removeStyleName("nameTextBoxCb");
		txtTitle.addStyleName("outlineNameTextBox");
		examplePanel.add(getExample().getWidgetComposite());
	}

  private void configScenarioOutline(String title, AbstractScenario scenario) {
    configScenarioOutline(title);
    for (TagPlace t : scenario.getScenarioPlace().getTagList()) {
      addTag(factory.createTag(this, t.getTag()));
    }
    txtTitle.setText(scenario.txtTitle.getText());
    txtDescription.setText(scenario.txtDescription.getText());
    clearSentences();
    for (SentencePlace s : scenario.getScenarioPlace().getSentenceList()) {
      addSentence(factory.createSentence(this, s.getKeyword(), s.getSentence()));
    }
    titlePanel.removeStyleName("nameTextBoxPanelCb");
    titlePanel.addStyleName("outlineNameTextBoxPanel");
  }

	/**
	 * @param colName
	 */
	public void addExampleColumn(String colName) {
		try {
			getExample().addColumn(colName);
		} catch (DuplicateKeyException e) {
			// TODO
		}
	}

	/**
	 * @param factory the factory to set
	 */
	public void setFactory(WidgetFactory factory) {
		this.factory = factory;
	}

	/**
	 * @return the factory
	 */
	public WidgetFactory getFactory() {
		if (factory == null) {
			factory = WidgetFactory.getInstance();
		}
		return factory;
	}

	/**
	 * @param example the example to set
	 */
	public void setExample(Example example) {
		this.example = example;
	}

	/**
	 * @return the example
	 */
	public Example getExample() {
		if (example == null) {
			example = getFactory().createExample(this);
		}
		return example;
	}

}

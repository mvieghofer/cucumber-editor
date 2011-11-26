/**
 */
package com.cucumber.editor.client.view;

import com.cucumber.editor.client.place.SentencePlace;




/**
 * filename: BackgroundImpl.java
 * 
 * @date: 13.08.2011
 * @author: Markus Vieghofer
 * 
 */
public class BackgroundImpl extends AbstractScenario {


	public BackgroundImpl(String title) {
		init();
		configBackground(title);
	}


	public BackgroundImpl(Feature feature, String title) {
		init();
		setFeature(feature);
		configBackground(title);
	}
	
	public BackgroundImpl(Feature feature, AbstractScenario scenario, String title) {
		init();
		setFeature(feature);
		txtTitle.setText(scenario.txtTitle.getText());
		txtDescription.setText(scenario.txtDescription.getText());
		clearSentences();
		for (SentencePlace s : scenario.getScenarioPlace().getSentenceList()) {
			addSentence(factory.createSentence(this, s.getKeyword(), s.getSentence()));
		}
		configBackground(title);
	}

	/**
	 * @param title 
	 * 
	 */
	private void configBackground(String title) {
		lblScenario.setText(title);
		titlePanel.removeStyleName("nameTextBoxPanelCb");
		titlePanel.addStyleName("backgroundNameTextBoxPanel");
	}

	@Override
	public void remove() {
		feature.removeScenario(this);
	}
}

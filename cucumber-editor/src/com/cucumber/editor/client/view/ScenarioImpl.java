/**
 */
package com.cucumber.editor.client.view;

import com.cucumber.editor.client.place.SentencePlace;
import com.cucumber.editor.client.place.TagPlace;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;

/**
 * filename: ScenarioImpl.java
 * 
 * @date: 25.07.2011
 * @author: Markus Vieghofer
 * 
 */
public class ScenarioImpl extends AbstractScenario implements Tagged {

	protected Button btnAddTag = createTagButton();
	private int tagPanelSize;

	public ScenarioImpl() {
		init();
	}

	public ScenarioImpl(String title) {
		init();
		configScenario(title);
	}

	public ScenarioImpl(Feature feature, String title) {
		init();
		setFeature(feature);
		configScenario(title);
	}

	/**
	 * @param title
	 */
	private void configScenario(String title) {
		lblScenario.setText(title);
		txtTitle.removeStyleName("nameTextBoxCb");
		txtTitle.addStyleName("nameTextBox");
	}

	/**
	 * @param feature
	 * @param title
	 * @param abstractScenario
	 */
	public ScenarioImpl(Feature feature, AbstractScenario scenario, String title) {
		init();
		setFeature(feature);
		configScenario(title);
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
		titlePanel.addStyleName("nameTextBoxPanel");
	}

	private Button createTagButton() {
		Button btnAddTag = new Button("Tag");
		btnAddTag.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				createTag();
			}
		});
		buttonPanel.insert(btnAddTag, 0);
		return btnAddTag;
	}

	private void createTag() {
		Tag tag = new TagImpl(this);
		addTag(tag);
	}

	@Override
	public void removeTag(Tag tag) {
		getTagPanel().remove(tag.getWidgetComposite());
		tagPanelSize = feature.calulateTagPanelHeight(getTagPanel(),
				tagPanelSize);
		getTagPanel().setHeight(tagPanelSize + "px");
		getScenarioPlace().removeTag(tag.getTagPlace());
	}

	@Override
	public void addTag(Tag tag) {
		getTagPanel().add(tag.getWidgetComposite());
		tagPanelSize = feature.calulateTagPanelHeight(getTagPanel(),
				tagPanelSize);
		getTagPanel().setHeight(tagPanelSize + "px");
		getScenarioPlace().addTag(tag.getTagPlace());
	}

	@Override
	public Panel getWidgetComposite() {
		return scenarioPanel;
	}

}

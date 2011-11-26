/**
 * 
 */
package com.cucumber.editor.client.view;

import java.util.List;

import com.cucumber.editor.client.CucumberEditor;
import com.cucumber.editor.client.activity.ActivityFactory;
import com.cucumber.editor.client.activity.FeatureActivity;
import com.cucumber.editor.client.place.FeaturePlace;
import com.cucumber.editor.client.place.PlaceFactory;
import com.cucumber.editor.client.services.LanguageService;
import com.cucumber.editor.client.services.LanguageServiceAsync;
import com.cucumber.editor.shared.dto.Language;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

/**
 * filename: FeatureImpl.java
 * 
 * @date: 25.07.2011
 * @author: Markus Vieghofer
 * 
 */
public class FeatureImpl implements Feature, Tagged {

	private static Feature instance;
	private WidgetFactory factory = WidgetFactory.getInstance();
	
	private FlowPanel scenarioPanel = createScenarioPanel();
	private FlowPanel tagPanel = createTagPanel();
	private FlowPanel headerPanel = createHeaderPanel();
	private FlowPanel buttonPanel = createButtonPanel();
	private FlowPanel headlinePanel = createHeadlinePanel();
	private FlowPanel featurePanel = createFeaturePanel();
	private FlowPanel languagePanel;
	private FlowPanel titlePanel = createTitlePanel();
	private FlowPanel descriptionPanel = createDescriptionPanel();

	private TextBox txtName = createFeatureNameTextBox();
	private TextArea txtDescription = createDescriptionTextArea();
	
	private Label lblFeature = createFeatureLabel();
	
	private Button btnAddTag = createTagButton();
	private Button btnAddScenario = createScenarioButton();
	private Button btnAddBackground = createBackgroundButton();
	
	private int tagPanelSize = 50;

	private CucumberEditor parent;
	private Label lblLanguage;
	private ListBox lbLanguage;

	private PlaceFactory placeFactory;
	private FeaturePlace place;
	private ActivityFactory activityFactory;
	private FeatureActivity activity;

	/**
	 * @return
	 */
	public static Feature getInstance() {
	  if (instance == null) {
	    instance = new FeatureImpl();
	  }
		return instance;
	}

	private FeatureImpl() {
		init();
	}

	/**
	 * 
	 */
	private void init() {
		languagePanel = createLanguagePanel();

		buttonPanel.add(btnAddTag);
		buttonPanel.add(btnAddScenario);
		buttonPanel.add(btnAddBackground);

		headerPanel.add(languagePanel);
		headerPanel.add(buttonPanel);

		titlePanel.add(txtName);

		headlinePanel.add(lblFeature);
		headlinePanel.add(titlePanel);

		descriptionPanel.add(txtDescription);

		featurePanel.add(headerPanel);
		featurePanel.add(tagPanel);
		featurePanel.add(headlinePanel);
		featurePanel.add(descriptionPanel);
		featurePanel.add(scenarioPanel);
	}

	public void setErrorText(String text) {
		getParent().setErrorLabelText("Error: " + text);
	}

	/**
	 * @return
	 */
	private FlowPanel createDescriptionPanel() {
		FlowPanel panel = new FlowPanel();
		panel.addStyleName("fullWidthPanel");
		return panel;
	}

	/**
	 * @return
	 */
	private FlowPanel createTitlePanel() {
		FlowPanel panel = new FlowPanel();
		panel.addStyleName("nameTextBoxPanel");
		return panel;
	}

	/**
	 * @return
	 */
	private FlowPanel createHeaderPanel() {
		FlowPanel panel = new FlowPanel();
		panel.addStyleName("headerPanel");
		panel.addStyleName("fullWidthPanel");
		return panel;
	}

	/**
	 * @return
	 */
	private TextArea createDescriptionTextArea() {
		final TextArea ta = new TextArea();
		ta.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getPlace().setDescription(ta.getValue());
			}
		});
		return ta;
	}

	/**
	 * @return
	 */
	private TextBox createFeatureNameTextBox() {
		txtName = new TextBox();
		txtName.addStyleName("nameTextBox");
		txtName.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getPlace().setTitle(txtName.getValue());
			}
		});
		return txtName;
	}

	/**
	 * @return
	 */
	private FlowPanel createHeadlinePanel() {
		FlowPanel panel = new FlowPanel();
		panel.addStyleName("headlinePanel");
		panel.addStyleName("fullWidthPanel");
		return panel;
	}

	/**
	 * @return
	 */
	private FlowPanel createButtonPanel() {
		FlowPanel panel = new FlowPanel();
		panel.addStyleName("buttonPanel");
		return panel;
	}

	/**
	 * @return
	 */
	private FlowPanel createTagPanel() {
		FlowPanel panel = new FlowPanel();
		panel.addStyleName("tagPanel");
		return panel;
	}

	/**
	 * @return
	 */
	private FlowPanel createFeaturePanel() {
		FlowPanel panel = new FlowPanel();
		panel.addStyleName("featurePanel");
		return panel;
	}

	/**
	 * @return
	 */
	private Button createBackgroundButton() {
		Button btn = new Button("Background");
		btn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				createBackground();
			}
		});
		return btn;
	}

	/**
	 * 
	 */
	protected void createBackground() {
		AbstractScenario bg = factory.createBackground(this, "Background:"); // TODO
																				// db
		addBackground(bg);
	}

	/**
	 * @return
	 */
	private FlowPanel createLanguagePanel() {
		FlowPanel panel = new FlowPanel();
		lblLanguage = new Label("# language:");
		lbLanguage = createLanguageListBox();
		panel.add(lblLanguage);
		panel.add(lbLanguage);
		panel.addStyleName("languagePanel");
		return panel;
	}

	/**
	 * @return a ListBox containing all Languages that are supported by
	 *         Cucumber. The file that defines that list of languages can be
	 *         found <a href=
	 *         "https://github.com/cucumber/gherkin/blob/master/lib/gherkin/i18n.yml"
	 *         target="_blank">here</a>.
	 */
	private ListBox createLanguageListBox() {
		final ListBox lb = new ListBox(false);
		AsyncCallback<List<Language>> callBack = new AsyncCallback<List<Language>>() {

			@Override
			public void onFailure(Throwable caught) {
				getParent().setErrorLabelText("Languages couldn't be loaded");
			}

			@Override
			public void onSuccess(List<Language> languages) {
				for (Language lang : languages) {
					lb.addItem(lang.getLanguage());
				}
				getPlace().setLanguage(lb.getItemText(lb.getSelectedIndex()));
			}
		};

		lb.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getPlace().setLanguage(lb.getItemText(lb.getSelectedIndex()));
			}
		});
		LanguageServiceAsync langService = (LanguageServiceAsync) GWT
				.create(LanguageService.class);
//		langService.getLanguages(callBack);
		lb.setVisibleItemCount(1);
		return lb;
	}

	/**
	 * @return
	 */
	private FlowPanel createScenarioPanel() {
		scenarioPanel = new FlowPanel();
		AbstractScenario scenario = factory.createScenario(this, "Scenario:"); // TODO
																				// db
		addScenario(scenario);
		scenarioPanel.addStyleName("fullWidthPanel");
		return scenarioPanel;
	}

	/**
	 * @return
	 */
	private Label createFeatureLabel() {
		String lblText = "Feature:"; // TODO get text according to language
		Label lbl = new Label(lblText);
		place.setFeature(lblText);
		return lbl;
	}

	/**
	 * @return
	 * 
	 */
	private Button createTagButton() {
		Button btnTag = new Button("Tag");
		btnTag.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				createTag();
			}
		});
		return btnTag;
	}

	private void createTag() {
		Tag tag = factory.createTag(this);
		addTag(tag);
	}

	/**
	 * @return
	 * 
	 */
	private Button createScenarioButton() {
		Button btnScenario = new Button("Scenario"); // TODO db
		btnScenario.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				createScenario();
			}
		});
		return btnScenario;
	}

	private void createScenario() {
		AbstractScenario scenario = factory.createScenario(this, "Scenario:"); // TODO
																				// db
		addScenario(scenario);
	}

	@Override
	public void removeScenario(AbstractScenario scenario) {
		scenarioPanel.remove(scenario.getWidgetComposite());
		getPlace().removeAbstractScenario(scenario.getScenarioPlace());
	}

	@Override
	public void removeTag(Tag tag) {
		tagPanel.remove(tag.getWidgetComposite());
		tagPanelSize = calulateTagPanelHeight(tagPanel, tagPanelSize);
		tagPanel.setHeight(tagPanelSize + "px");
		getPlace().removeTag(tag.getTagPlace());
	}

	@Override
	public void addScenario(AbstractScenario scenario) {
		scenarioPanel.add(scenario.getWidgetComposite());
		getPlace().addAbstractScenario(scenario.getScenarioPlace());
	}

	@Override
	public void addTag(Tag tag) {
		tagPanel.add(tag.getWidgetComposite());
		tagPanelSize = calulateTagPanelHeight(tagPanel, tagPanelSize);
		tagPanel.setHeight(tagPanelSize + "px");
		getPlace().addTag(tag.getTagPlace());
	}

	/**
	 * @param tagPanelSize2
	 * @return
	 */
	@Override
	public int calulateTagPanelHeight(ComplexPanel panel, int size) {
		int widgetCount = panel.getWidgetCount();
		if ((widgetCount % 3) == 1) {
			size = 50 * ((widgetCount / 3) + 1);
		}
		return size;
	}

	@Override
	public Panel getWidgetComposite() {
		return featurePanel;
	}

	@Override
	public void addBackground(AbstractScenario background) {
		scenarioPanel.insert(background.getWidgetComposite(), 0);
		getPlace().insertAbstractScenario(0, background.getScenarioPlace());
	}

	/**
	 * @see com.cucumber.editor.client.view.Feature#setParent(CucumberEditor)
	 */
	@Override
	public void setParent(CucumberEditor parent) {
		this.parent = parent;
	}

	/**
	 * @return the parent
	 */
	public CucumberEditor getParent() {
		return parent;
	}

	/**
	 * @see com.cucumber.editor.client.view.Feature#getPlace()
	 */
	@Override
	public FeaturePlace getPlace() {
		if (place == null) {
			place = getPlaceFactory().createFeaturePlace();
		}
		return place;
	}

	/**
	 * @see com.cucumber.editor.client.view.Feature#getActivity()
	 */
	@Override
	public FeatureActivity getActivity() {
		if (activity == null) {
			activity = getActivityFactory().createFeatureActivity(getPlace());
		}
		return activity;
	}

	/**
	 * @return the placeFactory
	 */
	public PlaceFactory getPlaceFactory() {
		if (placeFactory == null) {
			placeFactory = PlaceFactory.getInstance();
		}
		return placeFactory;
	}

	/**
	 * @return the activityFactory
	 */
	public ActivityFactory getActivityFactory() {
		if (activityFactory == null) {
			activityFactory = ActivityFactory.getInstance();
		}
		return activityFactory;
	}

	/**
	 * @see com.cucumber.editor.client.view.Feature#replaceScenario(com.cucumber.editor.client.view.AbstractScenario, com.cucumber.editor.client.view.AbstractScenario)
	 */
	@Override
	public void replaceScenario(AbstractScenario oldScenario,
			AbstractScenario newScenario) {
		int widgetIndex = scenarioPanel.getWidgetIndex(oldScenario.getWidgetComposite());
		scenarioPanel.remove(oldScenario.getWidgetComposite());
		scenarioPanel.insert(newScenario.getWidgetComposite(), widgetIndex);
	}
}

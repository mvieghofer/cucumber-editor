/**
 */
package com.cucumber.editor.client.view;

import java.util.LinkedList;
import java.util.List;

import com.cucumber.editor.client.place.AbstractScenarioPlace;
import com.cucumber.editor.client.place.PlaceFactory;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

/**
 * filename: AbstractScenario.java
 * 
 * @date: 14.08.2011
 * @author: Markus Vieghofer
 * 
 */
public abstract class AbstractScenario {

  protected Feature feature;
  private AbstractScenarioPlace scenarioPlace;

  protected WidgetFactory factory = WidgetFactory.getInstance();
  private PlaceFactory placeFactory;

  protected TextBox txtTitle = createTitleTextBox();

  protected TextArea txtDescription = createDescriptionTextArea();

  protected Button btnRemove = createRemoveButton();
  protected Button btnAddSentence = createSentenceButton();

  protected Label lblScenario = createClickableScenarioLabel();

  protected FlowPanel buttonPanel = createButtonPanel();
  protected FlowPanel headerPanel = createHeaderPanel();
  protected FlowPanel descriptionPanel = createDescriptionPanel();
  protected FlowPanel sentencePanel = createSentencePanel();
  protected FlowPanel tagPanel = createTagPanel();
  protected FlowPanel headlinePanel = createHeadlinePanel();
  protected FlowPanel titlePanel = createTitlePanel();
  protected FlowPanel examplePanel = createExamplePanel();
  protected FlowPanel scenarioPanel = createScenarioPanel();

  public void remove() {
    feature.removeScenario(this);
  }

  /**
   * @return
   */
  private FlowPanel createScenarioPanel() {
    FlowPanel panel = new FlowPanel();
    panel.addStyleName("abstractScenario");
    return panel;
  }

  /**
   * @return
   */
  private FlowPanel createExamplePanel() {
    FlowPanel panel = new FlowPanel();
    return panel;
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
  private FlowPanel createTagPanel() {
    FlowPanel panel = new FlowPanel();
    panel.addStyleName("tagPanel");
    return panel;
  }

  /**
   * @return
   */
  private FlowPanel createHeaderPanel() {
    FlowPanel panel = new FlowPanel();
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
  private FlowPanel createHeadlinePanel() {
    FlowPanel panel = new FlowPanel();
    panel.addStyleName("headlinePanel");
    panel.addStyleName("fullWidthPanel");
    return panel;
  }

  /**
   * @return
   */
  private TextBox createTitleTextBox() {
    final TextBox tb = new TextBox();
    tb.addStyleName("nameTextBox");
    tb.addChangeHandler(new ChangeHandler() {

      @Override
      public void onChange(ChangeEvent event) {
        getScenarioPlace().setTitle(tb.getText());
      }
    });
    return tb;
  }

  /**
   * @return
   */
  private TextArea createDescriptionTextArea() {
    final TextArea ta = new TextArea();
    ta.addChangeHandler(new ChangeHandler() {

      @Override
      public void onChange(ChangeEvent event) {
        getScenarioPlace().setDescription(ta.getText());
      }
    });
    return ta;
  }

  protected void init() {
    buttonPanel.add(btnAddSentence);
    buttonPanel.add(btnRemove);

    headerPanel.add(getTagPanel());
    headerPanel.add(buttonPanel);

    titlePanel.add(txtTitle);

    headlinePanel.add(lblScenario);
    headlinePanel.add(titlePanel);

    descriptionPanel.add(txtDescription);

    scenarioPanel.add(headerPanel);
    scenarioPanel.add(headlinePanel);
    scenarioPanel.add(descriptionPanel);
    scenarioPanel.add(sentencePanel);
    scenarioPanel.add(examplePanel);
  }

  protected Label createClickableScenarioLabel() {
    String title = "Scenario:";
    return createClickableScenarioLabel(title);
  }

  protected Label createClickableScenarioLabel(String title) {
    Label lbl = new Label(title); // TODO get keyword according to
    // language
    getScenarioPlace().setScenario(title);
    lbl.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        headlinePanel.remove(lblScenario);
        headlinePanel.insert(createScenarioListBox(), 0);
        titlePanel.removeStyleName("nameTextBoxPanel");
        titlePanel.removeStyleName("backgroundNameTextBoxPanel");
        titlePanel.removeStyleName("outlineNameTextBoxPanel");
        titlePanel.addStyleName("nameTextBoxPanelCb");
      }
    });
    return lbl;
  }

  private Button createSentenceButton() {
    Button btn = new Button("Sentence");
    btn.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        createSentence();
      }
    });
    return btn;
  }

  protected void createSentence() {
    Sentence sentence = factory.createSentence(this);
    addSentence(sentence);
  }

  /**
   * @return
   */
  protected ListBox createScenarioListBox() {
    final ListBox lb = new ListBox();
    addListboxEntries(lb);
    lb.addChangeHandler(new ChangeHandler() {

      @Override
      public void onChange(ChangeEvent event) {
        headlinePanel.remove(lb);
        lblScenario = createClickableScenarioLabel(lb.getItemText(lb
            .getSelectedIndex()) + ":");// , 0);
        headlinePanel.insert(lblScenario, 0);
        String itemText = lb.getItemText(lb.getSelectedIndex());
        if (equalsScenarioKeyword(itemText)) {
          convertToScenario();
        } else if (equalsScenarioOutlineKeyword(itemText)) {
          convertToScenarioOutline();
        } else if (equalsBackgroundKeyword(itemText)) {
          convertToBackground();
        }
      }
    });
    for (int i = 0; i < lb.getItemCount(); i++) {
      if (lb.getItemText(i).equals(
          lblScenario.getText()
              .substring(0, lblScenario.getText().length() - 1))) {
        lb.setSelectedIndex(i);
        break;
      }
    }
    return lb;
  }

  /**
   * @param itemText
   * @return
   */
  protected boolean equalsBackgroundKeyword(String itemText) {
    List<String> list = new LinkedList<String>(); //TODO get keywords from database
    boolean equals = false;
    for (String s : list) {
      if (s.equals(itemText)) {
        equals = true;
        break;
      }
    }
    return equals;
  }

  /**
   * @param itemText
   * @return
   */
  protected boolean equalsScenarioOutlineKeyword(String itemText) {
    List<String> list = new LinkedList<String>(); //TODO get keywords from database
    boolean equals = false;
    for (String s : list) {
      if (s.equals(itemText)) {
        equals = true;
        break;
      }
    }
    return equals;
  }

  /**
   * @param itemText
   * @return
   */
  protected boolean equalsScenarioKeyword(String itemText) {
    List<String> list = new LinkedList<String>(); //TODO get keywords from database
    boolean equals = false;
    for (String s : list) {
      if (s.equals(itemText)) {
        equals = true;
        break;
      }
    }
    return equals;
  }

  /**
	 * 
	 */
  protected void clearSentences() {
    sentencePanel.clear();
    getScenarioPlace().getSentenceList().clear();
  }

  protected void convertToScenarioOutline() {
    String title = lblScenario.getText();
    feature.replaceScenario(this,
        factory.createScenarioOutline(feature, this, title));
  }

  protected void convertToScenario() {
    String title = lblScenario.getText();
    feature.replaceScenario(this, factory.createScenario(feature, this, title));

  }

  /**
	 * 
	 */
  protected void convertToBackground() {
    feature.removeScenario(this);
    String title = lblScenario.getText();
    feature.addBackground(factory.createBackground(feature, this, title));
  }

  /**
   * @param lb
   */
  private void addListboxEntries(final ListBox lb) {
    lb.addItem("Scenario");
    lb.addItem("Scenario Outline");
    lb.addItem("Background");
  }

  private Button createRemoveButton() {
    Button btnRemove = new Button("X");
    btnRemove.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        remove();
      }
    });
    return btnRemove;
  }

  public FlowPanel createSentencePanel() {
    sentencePanel = new FlowPanel();
    addSentence(factory.createSentence(this));
    sentencePanel.addStyleName("fullWidthPanel");
    return sentencePanel;
  }

  public void addSentence(Sentence sentence) {
    sentencePanel.add(sentence.getWidgetComposite());
    getScenarioPlace().addSentence(sentence.getSentencePlace());
  }

  public void removeSentence(Sentence sentence) {
    sentencePanel.remove(sentence.getWidgetComposite());
    getScenarioPlace().removeSentence(sentence.getSentencePlace());
  }

  public void setFeature(Feature feature) {
    this.feature = feature;
  }

  public Panel getWidgetComposite() {
    return scenarioPanel;
  }

  /**
   * @return
   */
  public String getTitle() {
    return lblScenario.getText() + txtTitle.getValue();
  }

  /**
   * @return
   */
  public String getDescription() {
    return txtDescription.getText();
  }

  /**
   * @param tagPanel
   *          the tagPanel to set
   */
  public void setTagPanel(FlowPanel tagPanel) {
    this.tagPanel = tagPanel;
  }

  /**
   * @return the tagPanel
   */
  public FlowPanel getTagPanel() {
    return tagPanel;
  }

  /**
   * @param scenarioPlace
   *          the scenarioPlace to set
   */
  public void setScenarioPlace(AbstractScenarioPlace scenarioPlace) {
    this.scenarioPlace = scenarioPlace;
  }

  /**
   * @return the scenarioPlace
   */
  public AbstractScenarioPlace getScenarioPlace() {
    if (scenarioPlace == null) {
      scenarioPlace = getPlaceFactory().createAbstractScenarioPlace();
    }
    return scenarioPlace;
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

  public Feature getFeature() {
    return feature;
  }

}

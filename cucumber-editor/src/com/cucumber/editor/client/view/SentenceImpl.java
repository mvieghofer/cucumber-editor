/**
 */
package com.cucumber.editor.client.view;

import java.util.LinkedList;
import java.util.List;

import com.cucumber.editor.client.place.PlaceFactory;
import com.cucumber.editor.client.place.SentencePlace;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * filename: SentenceImpl.java
 * 
 * @date: 25.07.2011
 * @author: Markus Vieghofer
 * 
 */
public class SentenceImpl implements Sentence {

  private Label lblKeyword = createClickableKeywordLabel(getGivenKeyword());
  private TextBox txtSenctence = createSentenceTextBox();
  private Button btnDelete = createDeleteButton();
  private FlowPanel textPanel = createTextPanel();
  private FlowPanel sentencePanel = createSentencePanel();

  private AbstractScenario scenario;
  private PlaceFactory placeFactory;
  private SentencePlace sentencePlace;
  private WidgetFactory widgetFactory;

  public SentenceImpl() {
    init();
  }

  public SentenceImpl(AbstractScenario scenario, String keyword, String sentence) {
    setScenarion(scenario);
    init();
    lblKeyword = createClickableKeywordLabel(keyword);
    setSentence(sentence);
  }

  public SentenceImpl(AbstractScenario scenario) {
    setScenarion(scenario);
    init();
  }

  /**
   * @param sentence
   */
  private void setSentence(String sentence) {
    getSentencePlace().setSentence(sentence);
    createSentenceRepresentation();
    getSentencePlace().setSentence(sentence);
  }

  /**
   * @return
   */
  private FlowPanel createTextPanel() {
    FlowPanel panel = new FlowPanel();
    panel.addStyleName("sentenceTextBoxPanel");
    return panel;
  }

  /**
   * @return
   */
  private TextBox createSentenceTextBox() {
    final TextBox tb = new TextBox();
    tb.setText(getSentencePlace().getSentence());
    tb.addStyleName("sentenceTextBox");
    tb.addBlurHandler(new BlurHandler() {

      @Override
      public void onBlur(BlurEvent event) {
        getSentencePlace().setSentence(tb.getValue());
        if (!getSentencePlace().getSentence().equals("")) {
          createSentenceRepresentation();
        }
      }
    });
    return tb;
  }

  /**
	 * 
	 */
  protected void createSentenceRepresentation() {
    getTextPanel().clear();
    if (containsScenarioOutlineParts()
        && !(getScenario() instanceof ScenarioOutlineImpl)) {
      getScenario().getFeature().replaceScenario(
          getScenario(),
          WidgetFactory.getInstance().createScenarioOutline(
              getScenario().getFeature(), getScenario()));
    } else if (!getSentencePlace().getSentence().equals("")) {
      List<String> sentenceParts = createSentenceParts();
      int i = 1;
      for (String s : sentenceParts) {
        if (i == 1) {
          getTextPanel().add(createFixedPart(s));
        } else {
          getTextPanel().add(createVariablePart(s));
        }
        i = (i + 1) % 2;
      }
    }
  }

  /**
   * @return
   */
  private boolean containsScenarioOutlineParts() {
    String s = getSentencePlace().getSentence();
    if (s.contains("<") && s.contains(">")) {
      int firstIndex = s.indexOf('<');
      int secIndex = s.indexOf('>');
      if (firstIndex < secIndex) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  /**
   * @return
   */
  private List<String> createSentenceParts() {
    List<String> l = new LinkedList<String>();
    String[] split = getSentencePlace().getSentence().split("\"");
    int i = 1;
    for (String s : split) {
      if (i == 1) {
        if (s.contains("<") && s.contains(">")) {
          int firstIndex = s.indexOf('<');
          int secIndex = s.indexOf('>');
          if (firstIndex < secIndex) {
            l.add(s.substring(0, firstIndex));
            l.add(s.substring(firstIndex, secIndex + 1));
            l.add(s.substring(secIndex + 1));
          }
        } else {
          l.add(s);
        }
      } else {
        l.add(s);
      }
      i = (i + 1) % 2;
    }
    return l;
  }

  /**
   * @param s
   * @return
   */
  private TextBox createVariablePart(String s) {
    TextBox tb = getWidgetFactory().createVariableSentencePart(s, this);
    return tb;
  }

  /**
   * @param value
   * @return
   */
  protected boolean isExample(String value) {
    return value.trim().startsWith("<") && value.trim().endsWith(">");
  }

  /**
   * @param s
   * @return
   */
  private Label createFixedPart(String s) {
    Label lbl = new Label(s);
    lbl.addClickHandler(new SentenceClickHandler());
    lbl.addStyleName("fixedSentencePart");
    return lbl;
  }

  /**
   * @return
   */
  private FlowPanel createSentencePanel() {
    final FlowPanel panel = new FlowPanel();
    panel.addStyleName("sentence");
    panel.addStyleName("fullWidthPanel");
    return panel;
  }

  /**
   * @return
   */
  private Label createClickableKeywordLabel(String keyword) {
    Label lbl = new Label(keyword);
    getSentencePlace().setKeyword(keyword);
    // lbl.setStyleName("fixedElement");
    lbl.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        sentencePanel.remove(lblKeyword);
        sentencePanel.insert(createListBox(lblKeyword.getText()), 0);
      }
    });
    return lbl;
  }

  /**
   * @return the given keyword in the selected language
   */
  private String getGivenKeyword() {
    // TODO get Given keyword according to language
    String given = "Given";
    return given;
  }

  private void init() {
    getTextPanel().add(txtSenctence);

    sentencePanel.add(lblKeyword);
    sentencePanel.add(getTextPanel());
    sentencePanel.add(btnDelete);

    if (!getSentencePlace().getSentence().equals("")) {
      createSentenceRepresentation();
    }
  }

  /**
   * @param selectedItem
   * @return
   */
  private ListBox createListBox(String selectedItem) {
    final ListBox lb = new ListBox();
    getTextPanel().removeStyleName("sentenceTextBoxPanel");
    getTextPanel().addStyleName("sentenceTextBoxPanelCb");
    // TODO get keywords according to used language
    lb.insertItem("Given", 0);
    lb.insertItem("When", 1);
    lb.insertItem("Then", 2);
    lb.insertItem("And", 3);
    lb.insertItem("But", 4);
    lb.addChangeHandler(new ChangeHandler() {

      @Override
      public void onChange(ChangeEvent event) {
        String selectedItem = lb.getItemText(lb.getSelectedIndex());
        lblKeyword = createClickableKeywordLabel(selectedItem);
        sentencePanel.remove(lb);
        sentencePanel.insert(lblKeyword, 0);
        getTextPanel().removeStyleName("sentenceTextBoxPanelCb");
        getTextPanel().addStyleName("sentenceTextBoxPanel");
        getSentencePlace().setKeyword(selectedItem);
      }
    });
    for (int i = 0; i < lb.getItemCount(); i++) {
      if (lb.getItemText(i).equals(selectedItem)) {
        lb.setSelectedIndex(i);
        break;
      }
    }
    return lb;
  }

  /**
   * @return
   */
  private Button createDeleteButton() {
    Button btnDelete = new Button("X");
    btnDelete.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        delete();
      }
    });
    btnDelete.addStyleName("sentenceButton");
    return btnDelete;
  }

  @Override
  public void delete() {
    getScenario().removeSentence(this);
  }

  @Override
  public void setScenarion(AbstractScenario scenario) {
    this.scenario = scenario;
  }

  @Override
  public Panel getWidgetComposite() {
    return sentencePanel;
  }

  /**
   * @see com.cucumber.editor.client.view.Sentence#getSentence()
   */
  @Override
  public String getSentence() {
    StringBuilder sb = new StringBuilder();
    sb.append(lblKeyword.getText());
    sb.append(" ");
    sb.append(txtSenctence.getValue());
    return sb.toString();
  }

  /**
   * @see com.cucumber.editor.client.view.Sentence#getSentencePlace()
   */
  @Override
  public SentencePlace getSentencePlace() {
    if (sentencePlace == null) {
      sentencePlace = getPlaceFactory().createSentencePlace();
    }
    return sentencePlace;
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
   * @param scenario
   *          the scenario to set
   */
  public void setScenario(AbstractScenario scenario) {
    this.scenario = scenario;
  }

  /**
   * @return the scenario
   */
  @Override
  public AbstractScenario getScenario() {
    return scenario;
  }

  /**
   * @return the textPanel
   */
  @Override
  public FlowPanel getTextPanel() {
    return textPanel;
  }

  /**
   * @param widgetFactory the widgetFactory to set
   */
  public void setWidgetFactory(WidgetFactory widgetFactory) {
    this.widgetFactory = widgetFactory;
  }

  /**
   * @return the widgetFactory
   */
  public WidgetFactory getWidgetFactory() {
    if (widgetFactory == null) {
      widgetFactory = WidgetFactory.getInstance();
    }
    return widgetFactory;
  }

  private class SentenceClickHandler implements ClickHandler {

    /**
     * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
     */
    @Override
    public void onClick(ClickEvent event) {
      getTextPanel().clear();
      TextBox tb = createSentenceTextBox();
      tb.setFocus(true);
      getTextPanel().add(tb);
    }

  }
}

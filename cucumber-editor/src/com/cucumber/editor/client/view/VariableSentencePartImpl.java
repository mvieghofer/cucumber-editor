/**
 */
package com.cucumber.editor.client.view;

import com.cucumber.editor.client.place.PlaceFactory;
import com.cucumber.editor.client.place.VariableSentencePartPlace;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * filename: VariableSentencePart.java
 *
 *     @date: 15.09.2011
 *   @author: Markus Vieghofer
 *
 */
public class VariableSentencePartImpl extends TextBox {

  private PlaceFactory placeFactory = PlaceFactory.getInstance();
  private VariableSentencePartPlace place;
  
  private Sentence sentence;
  
  public VariableSentencePartImpl(String sentencePart, Sentence sentence) {
    setPlace(placeFactory.createVariableSentencePart(sentencePart));
    setText(sentencePart);
    setSentence(sentence);
    addChangeHandler(new VariableSentencePartChangeHandler());
    addStyleName("variableSentencePart");
  }
  
  /**
   * @param sentence the sentence to set
   */
  public void setSentence(Sentence sentence) {
    this.sentence = sentence;
  }

  /**
   * @return the sentence
   */
  public Sentence getSentence() {
    return sentence;
  }

  /**
   * @param value
   * @return
   */
  protected String prepareColName(String value) {
    return value.substring(1, value.length() - 1);
  }
  
  /**
   * @param place the place to set
   */
  public void setPlace(VariableSentencePartPlace place) {
    this.place = place;
  }

  /**
   * @return the place
   */
  public VariableSentencePartPlace getPlace() {
    return place;
  }

  private class VariableSentencePartChangeHandler implements ChangeHandler {

    /**
     * @see com.google.gwt.event.dom.client.ChangeHandler#onChange(com.google.gwt.event.dom.client.ChangeEvent)
     */
    @Override
    public void onChange(ChangeEvent event) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < getSentence().getTextPanel().getWidgetCount(); i++) {
        Widget widget = getSentence().getTextPanel().getWidget(i);
        if (widget instanceof Label) {
          sb.append(((Label) widget).getText());
        } else if (widget instanceof TextBox) {
          String value = ((TextBox) widget).getValue();
          if (getSentence().getScenario() instanceof ScenarioOutlineImpl
              && getPlace().isExample(value)) {
            ((ScenarioOutlineImpl) getSentence().getScenario())
                .addExampleColumn(prepareColName(value));
          }
          if (!getPlace().isExample(value)) {
            sb.append("\"");
          }
          sb.append(value);
          if (!getPlace().isExample(value)) {
            sb.append("\"");
          }
        }
      }
      getSentence().getSentencePlace().setSentence(sb.toString());
    }
    
  }
}

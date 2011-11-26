/**
 */
package com.cucumber.editor.client.view;

import com.cucumber.editor.client.place.SentencePlace;
import com.google.gwt.user.client.ui.FlowPanel;


/**
 * filename: Sentence.java
 *
 *     @date: 25.07.2011
 *   @author: Markus Vieghofer
 *
 */
public interface Sentence extends CucumberEditorWidget {
	public void delete();
	public void setScenarion(AbstractScenario scenario);
	/**
	 * 
	 */
	public String getSentence();
	/**
	 * @return
	 */
	public SentencePlace getSentencePlace();
  /**
   * @return
   */
  AbstractScenario getScenario();
  /**
   * @return
   */
  FlowPanel getTextPanel();
}

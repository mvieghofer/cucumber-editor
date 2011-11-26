/**
 */
package com.cucumber.editor.client.view;

import com.cucumber.editor.client.CucumberEditor;
import com.cucumber.editor.client.activity.FeatureActivity;
import com.cucumber.editor.client.place.FeaturePlace;
import com.google.gwt.user.client.ui.ComplexPanel;


/**
 * filename: Feature.java
 *
 *     @date: 25.07.2011
 *   @author: Markus Vieghofer
 *
 */
public interface Feature extends CucumberEditorWidget  {

	public void addScenario(AbstractScenario scenario);
	public void removeScenario(AbstractScenario scenario);
	public void addBackground(AbstractScenario background);
	public int calulateTagPanelHeight(ComplexPanel panel, int size);
	/**
	 * @param parent
	 */
	public void setParent(CucumberEditor parent);
	public FeaturePlace getPlace();
	/**
	 * @return
	 */
	public FeatureActivity getActivity();
	/**
	 * @param oldScenario
	 * @param newScenario
	 */
	public void replaceScenario(AbstractScenario oldScenario,
			AbstractScenario newScenario);
}

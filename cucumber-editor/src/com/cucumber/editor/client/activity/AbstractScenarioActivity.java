/**
 */
package com.cucumber.editor.client.activity;

import com.cucumber.editor.client.place.AbstractScenarioPlace;
import com.google.gwt.user.client.ui.Panel;

/**
 * filename: AbstractScenarioActivity.java
 *
 *     @date: 02.09.2011
 *   @author: Markus Vieghofer
 *
 */
public interface AbstractScenarioActivity {
	public AbstractScenarioPlace getAbstractScenarioPlace();
	public void setAbstractScenarioPlace(AbstractScenarioPlace scenarioPlace);
	public void write(Panel lbl);
}

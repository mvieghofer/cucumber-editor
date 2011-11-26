/**
 */
package com.cucumber.editor.client.activity;

import com.cucumber.editor.client.place.AbstractScenarioPlace;
import com.cucumber.editor.client.place.SentencePlace;
import com.cucumber.editor.client.place.TagPlace;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

/**
 * filename: AbstractScenarioActivityImpl.java
 * 
 * @date: 02.09.2011
 * @author: Markus Vieghofer
 * 
 */
public class AbstractScenarioActivityImpl implements AbstractScenarioActivity {

	/**
	 * 
	 */
	private static final String PREVIEW_SCENARIO = "previewScenario";
	private AbstractScenarioPlace place;
	
	public AbstractScenarioActivityImpl() {
		
	}
	
	public AbstractScenarioActivityImpl(AbstractScenarioPlace place) {
		setAbstractScenarioPlace(place);
	}

	/**
	 * @see com.cucumber.editor.client.activity.AbstractScenarioActivity#write(com.google.gwt.user.client.ui.Panel)
	 */
	@Override
	public void write(Panel lbl) {
		for (TagPlace t : getAbstractScenarioPlace().getTagList()) {
			t.getActivity().write(lbl, PREVIEW_SCENARIO);
		}
		Label lblClear = new Label();
		lblClear.addStyleName("clearLeft");
		lbl.add(lblClear);
		Label lblTitle = createPreviewLabel(getAbstractScenarioPlace().getScenario() + " " + getAbstractScenarioPlace().getTitle());
		Label lblDescription = createPreviewLabel(getAbstractScenarioPlace().getDescription());
		lbl.add(lblTitle);
		lbl.add(lblDescription);
		for (SentencePlace s : getAbstractScenarioPlace().getSentenceList()) {
			s.getActivity().write(lbl);
		}
		Label lblPrev = new Label();
		lblPrev.addStyleName("previewLabel");
		lbl.add(lblPrev);
	}

	/**
	 * @param title
	 * @return
	 */
	private Label createPreviewLabel(String title) {
		Label lbl = new Label(title);
		lbl.addStyleName(PREVIEW_SCENARIO);
		return lbl;
	}

	/**
	 * @see com.cucumber.editor.client.activity.AbstractScenarioActivity#getAbstractScenarioPlace()
	 */
	@Override
	public AbstractScenarioPlace getAbstractScenarioPlace() {
		return place;
	}

	/**
	 * @see com.cucumber.editor.client.activity.AbstractScenarioActivity#setAbstractScenarioPlace(com.cucumber.editor.client.place.AbstractScenarioPlace)
	 */
	@Override
	public void setAbstractScenarioPlace(AbstractScenarioPlace scenarioPlace) {
		this.place = scenarioPlace;
	}

}

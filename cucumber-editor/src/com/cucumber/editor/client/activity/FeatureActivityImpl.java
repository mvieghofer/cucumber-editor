/**
 */
package com.cucumber.editor.client.activity;

import com.cucumber.editor.client.place.AbstractScenarioPlace;
import com.cucumber.editor.client.place.FeaturePlace;
import com.cucumber.editor.client.place.TagPlace;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

/**
 * filename: FeatureActivityImpl.java
 *
 *     @date: 04.09.2011
 *   @author: Markus Vieghofer
 *
 */
public class FeatureActivityImpl implements FeatureActivity {

	private FeaturePlace place;
	
	public FeatureActivityImpl() {
		
	}
	
	public FeatureActivityImpl(FeaturePlace place) {
		setPlace(place);
	}
	
	/**
	 * @see com.cucumber.editor.client.activity.FeatureActivity#write(com.google.gwt.user.client.ui.Panel)
	 */
	@Override
	public void write(Panel lbl) {
		Label l = new Label(getPlace().getLanguage());
		l.addStyleName("previewLabel");
		lbl.add(l);
		for (TagPlace t : getPlace().getTagList()) {
			t.getActivity().write(lbl, "");
		}
		Label lblClear = new Label();
		lblClear.addStyleName("clearLeft");
		lbl.add(lblClear);
		lbl.add(new Label(getPlace().getFeature() + " " + getPlace().getTitle()));
		Label lblDesc = new Label(getPlace().getDescription());
		lblDesc.addStyleName("previewLabel");
		lbl.add(lblDesc);
		for (AbstractScenarioPlace s : getPlace().getAbstractScenarioList()) {
			s.getActivity().write(lbl);
		}
	}

	/**
	 * @param place the place to set
	 */
	public void setPlace(FeaturePlace place) {
		this.place = place;
	}

	/**
	 * @return the place
	 */
	public FeaturePlace getPlace() {
		return place;
	}

}

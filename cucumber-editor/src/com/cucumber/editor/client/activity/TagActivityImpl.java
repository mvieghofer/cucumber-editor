/**
 */
package com.cucumber.editor.client.activity;

import com.cucumber.editor.client.place.TagPlace;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

/**
 * filename: TagActivityImpl.java
 * 
 * @date: 02.09.2011
 * @author: Markus Vieghofer
 * 
 */
public class TagActivityImpl implements TagActivity {

	private TagPlace place;

	public TagActivityImpl() {

	}

	public TagActivityImpl(TagPlace place) {
		setTagPlace(place);
	}

	/**
	 * @see com.cucumber.editor.client.activity.TagActivity#setTagPlace(com.cucumber.editor.client.place.TagPlace)
	 */
	@Override
	public void setTagPlace(TagPlace place) {
		this.place = place;
	}

	/**
	 * @see com.cucumber.editor.client.activity.TagActivity#getTagPlace()
	 */
	@Override
	public TagPlace getTagPlace() {
		return place;
	}

	/**
	 * @see com.cucumber.editor.client.activity.TagActivity#write(com.google.gwt.user.client.ui.Panel,
	 *      java.lang.String)
	 */
	@Override
	public void write(Panel lbl, String cssClass) {
		Label lblPrev = createPreviewLabel("@" + getTagPlace().getTag(),
				cssClass);
		lbl.add(lblPrev);
	}

	/**
	 * @param title
	 * @param cssClass
	 * @return
	 */
	private Label createPreviewLabel(String title, String cssClass) {
		Label lbl = new Label(title);
		if (cssClass != null && cssClass != "") {
			lbl.addStyleName(cssClass);
		}
		lbl.addStyleName("previewTag");
		return lbl;
	}

}

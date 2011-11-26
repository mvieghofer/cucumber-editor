/**
 */
package com.cucumber.editor.client.activity;

import com.cucumber.editor.client.place.SentencePlace;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

/**
 * filename: SentenceActivityImpl.java
 * 
 * @date: 02.09.2011
 * @author: Markus Vieghofer
 * 
 */
public class SentenceActivityImpl implements SentenceActivity {

	private SentencePlace place;
	
	public SentenceActivityImpl()  {
		
	}
	
	public SentenceActivityImpl(SentencePlace place)  {
		setSentencePlace(place);
	}

	/**
	 * @see com.cucumber.editor.client.activity.SentenceActivity#write(com.google.gwt.user.client.ui.Panel)
	 */
	@Override
	public void write(Panel lbl) {
		Label lblPrev = createPreviewLabel(getSentencePlace().getWholeSentence());
		lbl.add(lblPrev);
	}

	/**
	 * @param sentence
	 * @return
	 */
	private Label createPreviewLabel(String sentence) {
		Label lbl = new Label(sentence);
		lbl.addStyleName("previewSentence");
		return lbl;
	}

	/**
	 * @see com.cucumber.editor.client.activity.SentenceActivity#setSentencePlace(com.cucumber.editor.client.place.SentencePlace)
	 */
	@Override
	public void setSentencePlace(SentencePlace place) {
		this.place = place;
	}

	/**
	 * @see com.cucumber.editor.client.activity.SentenceActivity#getSentencePlace()
	 */
	@Override
	public SentencePlace getSentencePlace() {
		return place;
	}

}

/**
 */
package com.cucumber.editor.client.activity;

import com.cucumber.editor.client.place.SentencePlace;
import com.google.gwt.user.client.ui.Panel;

/**
 * filename: SentenceActivity.java
 *
 *     @date: 02.09.2011
 *   @author: Markus Vieghofer
 *
 */
public interface SentenceActivity {
	public void write(Panel lbl);
	public void setSentencePlace(SentencePlace place);
	public SentencePlace getSentencePlace();
	
}

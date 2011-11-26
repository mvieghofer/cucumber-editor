/**
 */
package com.cucumber.editor.client.activity;

import com.cucumber.editor.client.place.TagPlace;
import com.google.gwt.user.client.ui.Panel;

/**
 * filename: TagActivity.java
 *
 *     @date: 02.09.2011
 *   @author: Markus Vieghofer
 *
 */
public interface TagActivity {
	public void write(Panel lbl, String cssClass);
	public void setTagPlace(TagPlace place);
	public TagPlace getTagPlace();
}

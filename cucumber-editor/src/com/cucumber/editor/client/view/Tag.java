/**
 */
package com.cucumber.editor.client.view;

import com.cucumber.editor.client.place.TagPlace;


/**
 * filename: Tag.java
 *
 *     @date: 29.07.2011
 *   @author: Markus Vieghofer
 *
 */
public interface Tag extends CucumberEditorWidget {
	public void remove();
	public void setTagged(Tagged tagged);
	/**
	 * @return
	 */
	public TagPlace getTagPlace();
	
}

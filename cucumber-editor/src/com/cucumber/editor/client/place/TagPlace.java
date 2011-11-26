/**
 */
package com.cucumber.editor.client.place;

import com.cucumber.editor.client.activity.TagActivity;

/**
 * filename: TagPlace.java
 *
 *     @date: 02.09.2011
 *   @author: Markus Vieghofer
 *
 */
public interface TagPlace {
	public String getTag();
	public void setTag(String tag);
	/**
	 * @return
	 */
	public TagActivity getActivity();
}

/**
 */
package com.cucumber.editor.client.place;

import com.cucumber.editor.client.activity.ActivityFactory;
import com.cucumber.editor.client.activity.TagActivity;

/**
 * filename: TagPlaceImpl.java
 * 
 * @date: 02.09.2011
 * @author: Markus Vieghofer
 * 
 */
public class TagPlaceImpl implements TagPlace {

	private String tag = "";
	private ActivityFactory factory;
	private TagActivity activity;

	public TagPlaceImpl() {

	}

	public TagPlaceImpl(String tag) {
		setTag(tag);
	}

	/**
	 * @see com.cucumber.editor.client.place.TagPlace#getTag()
	 */
	@Override
	public String getTag() {
		return tag;
	}

	/**
	 * @see com.cucumber.editor.client.place.TagPlace#setTag(java.lang.String)
	 */
	@Override
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * @see com.cucumber.editor.client.place.TagPlace#getActivity()
	 */
	@Override
	public TagActivity getActivity() {
		if (activity == null) {
			activity = getFactory().createTagActivity(this);
		}
		return activity;
	}

	/**
	 * @return the factory
	 */
	public ActivityFactory getFactory() {
		if (factory == null) {
			factory = ActivityFactory.getInstance();
		}
		return factory;
	}
}

/**
 */
package com.cucumber.editor.client.place;

import com.cucumber.editor.client.activity.ActivityFactory;
import com.cucumber.editor.client.activity.SentenceActivity;

/**
 * filename: SentencePlaceImpl.java
 * 
 * @date: 02.09.2011
 * @author: Markus Vieghofer
 * 
 */
public class SentencePlaceImpl implements SentencePlace {

	private String keyword = "";
	private String sentence = "";
	private ActivityFactory factory;
	private SentenceActivity activity;

	public SentencePlaceImpl() {

	}

	public SentencePlaceImpl(String keyword, String sentence) {
		setKeyword(keyword);
		setSentence(sentence);
	}

	/**
	 * @see com.cucumber.editor.client.place.SentencePlace#getKeyword()
	 */
	@Override
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @see com.cucumber.editor.client.place.SentencePlace#setKeyword(java.lang.String)
	 */
	@Override
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * @see com.cucumber.editor.client.place.SentencePlace#getSentence()
	 */
	@Override
	public String getSentence() {
		return sentence;
	}

	/**
	 * @see com.cucumber.editor.client.place.SentencePlace#setSentence()
	 */
	@Override
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	/**
	 * @see com.cucumber.editor.client.place.SentencePlace#getWholeSentence()
	 */
	@Override
	public String getWholeSentence() {
		return keyword + " " + sentence;
	}

	/**
	 * @see com.cucumber.editor.client.place.SentencePlace#setWholeSentence(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public void setWholeSentence(String keyword, String sentence) {
		setKeyword(keyword);
		setSentence(sentence);
	}

	/**
	 * @see com.cucumber.editor.client.place.SentencePlace#getActivity()
	 */
	@Override
	public SentenceActivity getActivity() {
		if (activity == null) {
			activity = getFactory().createSentenceActivity(this);
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

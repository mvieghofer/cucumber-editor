/**
 */
package com.cucumber.editor.client.place;

import com.cucumber.editor.client.activity.SentenceActivity;

/**
 * filename: SentencePlace.java
 *
 *     @date: 02.09.2011
 *   @author: Markus Vieghofer
 *
 */
public interface SentencePlace {
	public String getKeyword();
	public void setKeyword(String keyword);
	
	public String getSentence();
	public void setSentence(String sentence);
	
	public String getWholeSentence();
	public void setWholeSentence(String keyword, String sentence);
	/**
	 * @return
	 */
	public SentenceActivity getActivity();
}

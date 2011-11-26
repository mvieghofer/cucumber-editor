/**
 */
package com.cucumber.editor.client.view;

/**
 * filename: Settings.java
 *
 *     @date: 11.09.2011
 *   @author: Markus Vieghofer
 *
 */
public interface Settings extends CucumberEditorWidget {
	public void changeLanguage(String language);

	/**
	 * 
	 */
	public void remove();
}

/**
 */
package com.cucumber.editor.client.view;

/**
 * filename: Login.java
 *
 *     @date: 07.09.2011
 *   @author: Markus Vieghofer
 *
 */
public interface Login extends CucumberEditorWidget {
	public boolean login(String username, String password);

	/**
	 * @param errorMsg
	 */
	void addError(String errorMsg);
	void clearErrors();
}

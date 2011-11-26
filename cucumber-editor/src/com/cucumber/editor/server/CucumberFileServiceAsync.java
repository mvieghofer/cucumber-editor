/**
 */
package com.cucumber.editor.server;

import java.io.File;

import com.cucumber.editor.client.view.Feature;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * filename: CucumberFileServiceAsync.java
 *
 *     @date: 22.08.2011
 *   @author: Markus Vieghofer
 *
 */
public interface CucumberFileServiceAsync {

	/**
	 * 
	 * @see com.cucumber.editor.server.CucumberFileService#execute(com.cucumber.editor.client.view.Feature)
	 */
	void execute(Feature feature, AsyncCallback<Void> callback);

	void save(Feature feature, AsyncCallback<File> asyncCallback);

}

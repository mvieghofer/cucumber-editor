/**
 */
package com.cucumber.editor.server;

import java.io.File;

import com.cucumber.editor.client.view.Feature;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * filename: SaveCucumberFileService.java
 *
 *     @date: 22.08.2011
 *   @author: Markus Vieghofer
 *
 */
@RemoteServiceRelativePath("CucumberFileService")
public interface CucumberFileService extends RemoteService {
	public File save(Feature feature);
	public void execute(Feature feature);
}

/**
 */
package com.cucumber.editor.server;

import java.io.File;

import com.cucumber.editor.client.view.Feature;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * filename: CucumberFileServiceImpl.java
 *
 *     @date: 22.08.2011
 *   @author: Markus Vieghofer
 *
 */
public class CucumberFileServiceImpl extends RemoteServiceServlet implements
		CucumberFileService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see com.cucumber.editor.server.com.cucumber.editor.client.serivces.CucumberFileService#save(com.cucumber.editor.client.view.Feature)
	 */
	@Override
	public File save(Feature feature) {
		File f = new File("tmp.feature");
		
//		FileWriter fw = new FileWriter("tmp.feature");
		return f;
	}

	/**
	 * @see com.cucumber.editor.server.com.cucumber.editor.client.serivces.CucumberFileService#execute(com.cucumber.editor.client.view.Feature)
	 */
	@Override
	public void execute(Feature feature) {
		// TODO Auto-generated method stub

	}

}

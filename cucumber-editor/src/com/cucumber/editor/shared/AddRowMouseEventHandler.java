/**
 */
package com.cucumber.editor.shared;

import com.cucumber.editor.client.view.AddRowPanel;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;

/**
 * filename: AddRowMouseEventHandler.java
 * 
 * @date: 14.09.2011
 * @author: Markus Vieghofer
 * 
 */
public class AddRowMouseEventHandler implements MouseOutHandler,
		MouseOverHandler {

	/**
	 * @see com.google.gwt.event.dom.client.MouseOverHandler#onMouseOver(com.google.gwt.event.dom.client.MouseOverEvent)
	 */
	@Override
	public void onMouseOver(MouseOverEvent event) {
		AddRowPanel panel = (AddRowPanel) event.getSource();
		panel.addMouseOverRow();
	}

	/**
	 * @see com.google.gwt.event.dom.client.MouseOutHandler#onMouseOut(com.google.gwt.event.dom.client.MouseOutEvent)
	 */
	@Override
	public void onMouseOut(MouseOutEvent event) {
		AddRowPanel panel = (AddRowPanel) event.getSource();
		panel.removeMouseOverRow();
	}

}

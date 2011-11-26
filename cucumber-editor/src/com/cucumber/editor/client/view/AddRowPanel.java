/**
 */
package com.cucumber.editor.client.view;

import java.util.List;

import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * filename: AddRowPanel.java
 * 
 * @date: 14.09.2011
 * @author: Markus Vieghofer
 * 
 */
public class AddRowPanel extends FlowPanel {

	private Example example;

	public AddRowPanel(Example example) {
		setExample(example);
		setStyleName("addRow");
	}

	public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
		return addDomHandler(handler, MouseOverEvent.getType());
	}

	public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
		return addDomHandler(handler, MouseOutEvent.getType());
	}

	/**
	 * @param example
	 *            the example to set
	 */
	public void setExample(Example example) {
		this.example = example;
	}

	/**
	 * @return the example
	 */
	public Example getExample() {
		return example;
	}

	/**
	 * 
	 */
	public void addMouseOverRow() {
		List<String> columns = getExample().getPlace().getColumns();
		for (String s : columns) {
			if (!s.equals("")) {
				addMouseOverTextBox();
			}
		}
	}

	/**
	 * 
	 */
	private void addMouseOverTextBox() {
		TextBox tb = new TextBox();
		tb.addStyleName("mouseOverTextBox");
		tb.addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent event) {
				getExample().addEmptyRow();
			}
		});
		add(tb);
	}

	/**
	 * 
	 */
	public void removeMouseOverRow() {
		clear();
	}
}

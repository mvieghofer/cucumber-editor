/**
 */
package com.cucumber.editor.client.view;

import com.cucumber.editor.client.CucumberEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;

/**
 * filename: PreviewImpl.java
 * 
 * @date: 01.09.2011
 * @author: Markus Vieghofer
 * 
 */
public class PreviewImpl implements Preview {

	private Anchor anchorClose = createCloseAnchor();
	private Panel textPanel = createTextPanel();
	private FlowPanel mainPanel = createMainPanel();

	private CucumberEditor parent;

	public PreviewImpl(CucumberEditor parent) {
		setParent(parent);
	}

	/**
	 * @return
	 */
	private Panel createTextPanel() {
		Panel panel = new FlowPanel();
		panel.addStyleName("previewText");
		return panel;
	}

	/**
	 * @param cucumberEditor
	 * @param feature
	 */
	public PreviewImpl(CucumberEditor cucumberEditor, Feature feature) {
		setParent(cucumberEditor);
		createPreview(feature);
	}

	/**
	 * @see com.cucumber.editor.client.view.Preview#createPreview(com.cucumber.editor.client.view.Feature)
	 */
	@Override
	public void createPreview(Feature f) {
		f.getActivity().write(textPanel);
	}

	/**
	 * @return
	 */
	private FlowPanel createMainPanel() {
		FlowPanel panel = new FlowPanel();
		panel.addStyleName("previewMainPanel");
		panel.add(anchorClose);
		panel.add(textPanel);
		return panel;
	}

	/**
	 * @return
	 */
	private Anchor createCloseAnchor() {
		Anchor close = new Anchor("close"); // TODO db
		close.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				parent.removePreview();
			}
		});
		return close;
	}

	/**
	 * @see com.cucumber.editor.client.view.CucumberEditorWidget#getWidgetComposite()
	 */
	@Override
	public Panel getWidgetComposite() {
		return mainPanel;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(CucumberEditor parent) {
		this.parent = parent;
	}

	/**
	 * @return the parent
	 */
	public CucumberEditor getParent() {
		return parent;
	}
}

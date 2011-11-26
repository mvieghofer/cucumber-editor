/**
 */
package com.cucumber.editor.client.view;

import com.cucumber.editor.client.place.PlaceFactory;
import com.cucumber.editor.client.place.TagPlace;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * filename: TagImpl.java
 * 
 * @date: 29.07.2011
 * @author: Markus Vieghofer
 * 
 */
public class TagImpl implements Tag {

	private TextBox txtTag = createTextBox("");
	private Button btnRemove = createRemoveButton();
	private FlowPanel tagPanel = createTagPanel();
	private Tagged tagged;
	private PlaceFactory placeFactory;
	private TagPlace tagPlace;

	public TagImpl(Tagged tagged) {
		setTagged(tagged);
		init();
	}

	/**
	 * @return
	 */
	private TextBox createTextBox(String text) {
		final TextBox tb = new TextBox();
		tb.setText(text);
		getTagPlace().setTag(text);
		tb.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getTagPlace().setTag(tb.getValue() + " ");
			}
		});
		return tb;
	}

	/**
	 * @return
	 */
	private FlowPanel createTagPanel() {
		FlowPanel panel = new FlowPanel();
		panel.addStyleName("tag");
		panel.addHandler(new MouseMoveHandler() {

			@Override
			public void onMouseMove(MouseMoveEvent event) {
			}
		}, MouseMoveEvent.getType());
		return panel;
	}

	public TagImpl() {
		init();
	}

	/**
	 * @param tagged
	 * @param tag
	 */
	public TagImpl(Tagged tagged, String tag) {
		setTagged(tagged);
		txtTag = createTextBox(tag);
		init();
	}

	private void init() {
		tagPanel.add(txtTag);
		tagPanel.add(btnRemove);
	}

	/**
	 * @return
	 */
	private Button createRemoveButton() {
		Button btn = new Button("x");
		// btn.setVisible(false);
		btn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				remove();
			}
		});
		return btn;
	}

	@Override
	public void remove() {
		tagged.removeTag(this);
	}

	@Override
	public void setTagged(Tagged tagged) {
		this.tagged = tagged;
	}

	@Override
	public Panel getWidgetComposite() {
		return tagPanel;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.cucumber.editor.client.view.CucumberEditorWidget#write(com.google.gwt.user.client.ui.FlowPanel)
	 */
	public void write(FlowPanel lblText) {
		Label lbl = createPreviewLabel("@" + getTagPlace().getTag());
		lblText.add(lbl);
	}

	/**
	 * @param title
	 * @return
	 */
	private Label createPreviewLabel(String title) {
		Label lbl = new Label(title);
		lbl.addStyleName("previewTag");
		return lbl;
	}

	/**
	 * @see com.cucumber.editor.client.view.Tag#getTagPlace()
	 */
	@Override
	public TagPlace getTagPlace() {
		if (tagPlace == null) {
			tagPlace = getPlaceFactory().createTagPlace();
		}
		return tagPlace;
	}

	/**
	 * @return the placeFactory
	 */
	public PlaceFactory getPlaceFactory() {
		if (placeFactory == null) {
			placeFactory = PlaceFactory.getInstance();
		}
		return placeFactory;
	}

}

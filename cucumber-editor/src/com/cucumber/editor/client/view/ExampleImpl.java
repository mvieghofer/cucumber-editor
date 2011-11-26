/**
 */
package com.cucumber.editor.client.view;

import java.util.List;
import java.util.Map;

import com.cucumber.editor.client.exceptions.DuplicateKeyException;
import com.cucumber.editor.client.place.ExamplePlace;
import com.cucumber.editor.client.place.PlaceFactory;
import com.cucumber.editor.shared.AddRowMouseEventHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * filename: ExampleImpl.java
 * 
 * @date: 12.09.2011
 * @author: Markus Vieghofer
 * 
 */
public class ExampleImpl implements Example {

	private Panel examplePanel = createExamplePanel();

	private PlaceFactory placeFactory;
	private ExamplePlace place;

	private ScenarioOutlineImpl parent;

	/**
	 * @param parent
	 */
	public ExampleImpl(ScenarioOutlineImpl parent) {
		this.setParent(parent);
	}

	/**
	 * @see com.cucumber.editor.client.view.CucumberEditorWidget#getWidgetComposite()
	 */
	@Override
	public Panel getWidgetComposite() {
		return examplePanel;
	}

	/**
	 * @return
	 */
	private Panel createExamplePanel() {
		Panel panel = new FlowPanel();
		return panel;
	}

	/**
	 * @throws DuplicateKeyException
	 * @see com.cucumber.editor.client.view.Example#addColumn(java.lang.String)
	 */
	@Override
	public void addColumn(String colName) throws DuplicateKeyException {
		getPlace().addColumn(colName);
		renderExampleTable();
	}

	/**
	 * @see com.cucumber.editor.client.view.Example#removeColumn(java.lang.String)
	 */
	@Override
	public void removeColumn(String colName) {
		getPlace().removeColumn(colName);
		renderExampleTable();
	}

	/**
	 * @see com.cucumber.editor.client.view.Example#addRow(java.util.Map)
	 */
	@Override
	public void addRow(Map<String, String> row) {
		getPlace().addRow(row);
		renderExampleTable();
	}

	/**
	 * @see com.cucumber.editor.client.view.Example#removeRow(int)
	 */
	@Override
	public void removeRow(int index) {
		getPlace().removeRow(index);
		renderExampleTable();
	}

	private void renderExampleTable() {
		examplePanel.clear();
		List<String> columns = getPlace().getColumns();
		Map<String, List<String>> table = getPlace().getTable();
		Panel row = createRowPanel();
		for (String key : columns) {
			if (!key.equals("")) {
				row.add(createValueTextBox(key));
			}
		}
		examplePanel.add(row);
		int size = table.get(columns.get(0)).size();
		for (int i = 0; i < size; i++) {
			row = createRowPanel();
			for (String key : columns) {
				if (!key.equals("")) {
					row.add(createValueTextBox(key, i, table.get(key).get(i)));
				} else {
					row.add(createRemoveButton(i));
				}
			}
			examplePanel.add(row);
		}
		examplePanel.add(createAddRowPanel());
	}

	/**
	 * @param key
	 * @return
	 */
	private Label createValueTextBox(String key) {
		Label lbl = new Label(key);
		return lbl;
	}

	/**
	 * @param i
	 * @return
	 */
	private Button createRemoveButton(final int i) {
		Button btn = new Button("Remove");
		btn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				removeRow(i);
			}
		});
		return btn;
	}

	/**
	 * @return
	 */
	private Panel createAddRowPanel() {
		AddRowPanel panel = new AddRowPanel(this);
		panel.addMouseOverHandler(new AddRowMouseEventHandler());
		panel.addMouseOutHandler(new AddRowMouseEventHandler());
		return panel;
	}

	/**
	 * @param key
	 * @param row 
	 * @param value 
	 * @return
	 */
	private TextBox createValueTextBox(final String key, final int row, String value) {
		final TextBox tb = new TextBox();
		tb.setText(value == null ? "" : value);
		getPlace().setRow(key, row, value);
		tb.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				getPlace().setRow(key, row, tb.getValue());
			}
		});
		return tb;
	}

	/**
	 * @return
	 */
	private Panel createRowPanel() {
		Panel panel = new FlowPanel();
		return panel;
	}

	/**
	 * @param place
	 *            the place to set
	 */
	public void setPlace(ExamplePlace place) {
		this.place = place;
	}

	/**
	 * 
	 * @return the place
	 * @see com.cucumber.editor.client.view.Example#getExamplePlace()
	 */
	@Override
	public ExamplePlace getPlace() {
		if (place == null) {
			place = getPlaceFactory().createExamplePlace();
		}
		return place;
	}

	/**
	 * @param placeFactory
	 *            the placeFactory to set
	 */
	public void setPlaceFactory(PlaceFactory placeFactory) {
		this.placeFactory = placeFactory;
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

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(ScenarioOutlineImpl parent) {
		this.parent = parent;
	}

	/**
	 * @return the parent
	 */
	public ScenarioOutlineImpl getParent() {
		return parent;
	}

	/**
	 * @see com.cucumber.editor.client.view.Example#addEmptyRow()
	 */
	@Override
	public void addEmptyRow() {
		getPlace().addEmptyRow();
		renderExampleTable();
	}
}

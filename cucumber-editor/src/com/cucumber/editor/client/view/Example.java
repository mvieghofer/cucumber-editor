/**
 */
package com.cucumber.editor.client.view;

import java.util.Map;

import com.cucumber.editor.client.exceptions.DuplicateKeyException;
import com.cucumber.editor.client.place.ExamplePlace;

/**
 * filename: Example.java
 *
 *     @date: 12.09.2011
 *   @author: Markus Vieghofer
 *
 */
public interface Example extends CucumberEditorWidget {
	public void addColumn(String colName) throws DuplicateKeyException;
	public void removeColumn(String colName);
	public void addRow(Map<String, String> values);
	public void removeRow(int index);
	public void addEmptyRow();
	public ExamplePlace getPlace();
}

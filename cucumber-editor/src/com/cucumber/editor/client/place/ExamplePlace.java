/**
 */
package com.cucumber.editor.client.place;

import java.util.List;
import java.util.Map;

import com.cucumber.editor.client.exceptions.DuplicateKeyException;

/**
 * filename: ExamplePlace.java
 *
 *     @date: 12.09.2011
 *   @author: Markus Vieghofer
 *
 */
public interface ExamplePlace {
	public Map<String, List<String>> getTable();
	public void setTable(Map<String, List<String>> table);
	public List<String> getColumns();
	public void setColumns(List<String> columns);
	public void addRow(Map<String, String> row);
	public void removeRow(int index);
	public void removeRow(Map<String, String> row);
	public List<String> addColumn(String col) throws DuplicateKeyException;
	public List<String> addColumn(String col, List<String> value) throws DuplicateKeyException;
	public List<String> removeColumn(String col);
	/**
	 * 
	 */
	public void addEmptyRow();
	/**
	 * @param key
	 * @param row
	 * @param value
	 * @return 
	 */
	public String setRow(String key, int row, String value);
}

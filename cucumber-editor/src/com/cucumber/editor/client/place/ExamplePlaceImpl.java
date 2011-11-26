/**
 */
package com.cucumber.editor.client.place;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.cucumber.editor.client.exceptions.DuplicateKeyException;

/**
 * filename: ExamplePlaceImpl.java
 * 
 * @date: 12.09.2011
 * @author: Markus Vieghofer
 * 
 */
public class ExamplePlaceImpl implements ExamplePlace {

	private List<String> columns = new LinkedList<String>();
	private Map<String, List<String>> table = new HashMap<String, List<String>>();
	
	/**
	 * 
	 */
	public ExamplePlaceImpl() {
		getColumns().add("");
		getTable().put("", new LinkedList<String>());
		getTable().get("").add("");
	}

	/**
	 * @see com.cucumber.editor.client.place.ExamplePlace#getTable()
	 */
	@Override
	public Map<String, List<String>> getTable() {
		return table;
	}

	/**
	 * @see com.cucumber.editor.client.place.ExamplePlace#setTable(java.util.Map)
	 */
	@Override
	public void setTable(Map<String, List<String>> table) {
		this.table = table;
	}

	/**
	 * @see com.cucumber.editor.client.place.ExamplePlace#addRow(java.util.Map)
	 */
	@Override
	public void addRow(Map<String, String> row) {
		for (String s : getColumns()) {
			getTable().get(s).add(row.get(s));
		}
	}

	/**
	 * @see com.cucumber.editor.client.place.ExamplePlace#removeRow(int)
	 */
	@Override
	public void removeRow(int index) {
		for (String s : getColumns()) {
			getTable().get(s).remove(index);
		}
	}

	/**
	 * @return
	 * @throws DuplicateKeyException
	 * @see com.cucumber.editor.client.place.ExamplePlace#addColumn(java.lang.String)
	 */
	@Override
	public List<String> addColumn(String col) throws DuplicateKeyException {
		if (!getTable().containsKey(col)) {
			getColumns().add(getColumns().size() - 1, col);
			getTable().put(col, new LinkedList<String>());
			getTable().get(col).add("");
			return getTable().get(col);
		} else {
			throw new DuplicateKeyException();
		}
	}

	/**
	 * @return
	 * @throws DuplicateKeyException
	 * @see com.cucumber.editor.client.place.ExamplePlace#addColumn(java.lang.String,
	 *      java.util.List)
	 */
	@Override
	public List<String> addColumn(String col, List<String> values)
			throws DuplicateKeyException {
		if (!getTable().containsKey(col)) {
			getColumns().add(getColumns().size() - 1, col);
			return getTable().put(col, values);
		} else {
			throw new DuplicateKeyException();
		}
	}

	/**
	 * @return
	 * @see com.cucumber.editor.client.place.ExamplePlace#removeColumn(java.lang.String)
	 */
	@Override
	public List<String> removeColumn(String col) {
		return getTable().remove(col);
	}

	/**
	 * @see com.cucumber.editor.client.place.ExamplePlace#addEmptyRow()
	 */
	@Override
	public void addEmptyRow() {
		for (String key : getColumns()) {
			getTable().get(key).add("");
		}
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	/**
	 * @return the columns
	 */
	public List<String> getColumns() {
		return columns;
	}

	/**
	 * 
	 * @see com.cucumber.editor.client.place.ExamplePlace#removeRow(java.util.Map)
	 */
	@Override
	public void removeRow(Map<String, String> row) {
		for (String s : getColumns()) {
			getTable().get(s).remove(row.get(s));
		}
	}

	/**
	 * @return 
	 * @see com.cucumber.editor.client.place.ExamplePlace#setRow(java.lang.String, int, java.lang.String)
	 */
	@Override
	public String setRow(String key, int row, String value) {
		return table.get(key).set(row, value);
	}
}

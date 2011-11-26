/**
 */
package com.cucumber.editor.shared.dto;

/**
 * filename: Keyword.java
 *
 *     @date: 26.06.2011
 *   @author: Markus Vieghofer
 *
 */
public enum Keyword {
	GIVEN(1),
	WHEN(2),
	THEN(3),
	AND(4),
	BUT(5);
	
	int value;

	private Keyword(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}

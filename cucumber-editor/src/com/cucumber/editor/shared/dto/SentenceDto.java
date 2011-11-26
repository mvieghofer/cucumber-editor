/**
 */
package com.cucumber.editor.shared.dto;

import java.util.List;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

/**
 * filename: Sentence.java
 *
 *     @date: 26.06.2011
 *   @author: Markus Vieghofer
 *
 */
public class SentenceDto {
	private List<Object> senctance;
	private String str;
	
	public SentenceDto(String template) {
		setStr(template);
		processTemplate(template);
	}

	/**
	 * @param template
	 */
	private void processTemplate(String template) {
		String[] split = template.split(" ");
		for (String str : split) {
			if (str.startsWith("(") && str.endsWith(")")) {
				createParameterInput(str);
			} else {
				createText(str);
			}
		}
	}

	/**
	 * @param str
	 */
	private void createText(String str) {
		Label lbl = new Label(str);
		senctance.add(lbl);
	}

	/**
	 * @param str
	 */
	private void createParameterInput(String str) {
		TextBox tb = new TextBox();
		// add validator
		senctance.add(tb);
	}
	
	public List<Object> getSentence() {
		return senctance;
	}

	/**
	 * @param str the str to set
	 */
	public void setStr(String str) {
		this.str = str;
	}

	/**
	 * @return the str
	 */
	public String getStr() {
		return str;
	}
}

/**
 */
package com.cucumber.editor.client.place;

/**
 * filename: VariableSentencePart.java
 *
 *     @date: 15.09.2011
 *   @author: Markus Vieghofer
 *
 */
public interface VariableSentencePartPlace {
  public String getSentencePart();
  public void setSentencePart(String sentencePart);
  
  public boolean isExampleColumn();
  public boolean isExample(String value);
}

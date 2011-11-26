/**
 */
package com.cucumber.editor.client.place;

/**
 * filename: VariableSentencePartPlaceImpl.java
 *
 *     @date: 15.09.2011
 *   @author: Markus Vieghofer
 *
 */
public class VariableSentencePartPlaceImpl implements VariableSentencePartPlace {

  private String sentencePart;
  private boolean exampleColumn;
  
  public VariableSentencePartPlaceImpl(String sentencePart) {
    setSentencePart(sentencePart);
  }
  
  /**
   * @see com.cucumber.editor.client.place.VariableSentencePartPlace#getSentencePart()
   */
  @Override
  public String getSentencePart() {
    return sentencePart;
  }

  /**
   * @see com.cucumber.editor.client.place.VariableSentencePartPlace#setSentencePart(java.lang.String)
   */
  @Override
  public void setSentencePart(String sentencePart) {
    this.sentencePart = sentencePart;
    exampleColumn = isExample(sentencePart);
  }

  /**
   * @see com.cucumber.editor.client.place.VariableSentencePartPlace#isExampleColumn()
   */
  @Override
  public boolean isExampleColumn() {
    return exampleColumn;
  }

  
  /**
   * @param value
   * @return
   */
  @Override
  public boolean isExample(String value) {
    return value.trim().startsWith("<") && value.trim().endsWith(">");
  }

}

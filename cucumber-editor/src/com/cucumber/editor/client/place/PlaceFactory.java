/**
 */
package com.cucumber.editor.client.place;

import java.util.List;

/**
 * filename: PlaceFactory.java
 * 
 * @date: 02.09.2011
 * @author: Markus Vieghofer
 * 
 */
public class PlaceFactory {

  private static PlaceFactory instance = new PlaceFactory();

  public static PlaceFactory getInstance() {
    return instance;
  }

  private PlaceFactory() {

  }

  public TagPlace createTagPlace() {
    return new TagPlaceImpl();
  }

  public TagPlace createTagPlace(String tag) {
    return new TagPlaceImpl(tag);
  }

  public SentencePlace createSentencePlace() {
    return new SentencePlaceImpl();
  }

  public SentencePlace createSentencePlace(String keyword, String sentence) {
    return new SentencePlaceImpl(keyword, sentence);
  }

  public AbstractScenarioPlace createAbstractScenarioPlace() {
    return new AbstractScenarioPlaceImpl();
  }

  public AbstractScenarioPlace createAbstractScenarioPlace(
      List<TagPlace> tagList, List<SentencePlace> sentenceList) {
    return new AbstractScenarioPlaceImpl(tagList, sentenceList);
  }

  public FeaturePlace createFeaturePlace() {
    return new FeaturePlaceImpl();
  }

  public ExamplePlace createExamplePlace() {
    return new ExamplePlaceImpl();
  }

  public VariableSentencePartPlace createVariableSentencePart(
      String sentencePart) {
    return new VariableSentencePartPlaceImpl(sentencePart);
  }
}

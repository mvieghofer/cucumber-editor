/**
 */
package com.cucumber.editor.client.activity;

import java.util.Locale;

import com.cucumber.editor.client.view.LocaleListener;

/**
 * filename: LocaleManager.java
 *
 *     @date: 24.10.2011
 *   @author: Markus Vieghofer
 *
 */
public interface LocaleManager {
  public void changeLocale(Locale locale);
  public void changeLocale(String language);
  public void changeLocale(String language, String country);
  
  public void observeChangeLocale(LocaleListener listener);
  public void removeChangeLocale(LocaleListener listener);
 
  public Locale getLocale();
}

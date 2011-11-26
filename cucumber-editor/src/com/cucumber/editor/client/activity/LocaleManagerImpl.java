/**
 */
package com.cucumber.editor.client.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.cucumber.editor.client.view.LocaleListener;

/**
 * filename: LocaleManagerImpl.java
 *
 *     @date: 24.10.2011
 *   @author: Markus Vieghofer
 *
 */
public class LocaleManagerImpl implements LocaleManager {
  
  private List<LocaleListener> localeListener = new ArrayList<LocaleListener>();
  
  private Locale locale;
  
  private static LocaleManagerImpl instance = new LocaleManagerImpl();
  
  public static LocaleManager getInstance() {
    return instance;
  }
  
  private LocaleManagerImpl() {
    
  }

  /**
   * @see com.cucumber.editor.client.activity.LocaleManager#changeLocale(java.util.Locale)
   */
  @Override
  public void changeLocale(Locale locale) {
    // TODO save to database
    this.locale = locale;
    for (LocaleListener l : localeListener) {
      l.setLocale(locale);
    }
  }

  /**
   * @see com.cucumber.editor.client.activity.LocaleManager#changeLocale(java.lang.String)
   */
  @Override
  public void changeLocale(String language) {
     changeLocale(new Locale(language));
  }

  /**
   * @see com.cucumber.editor.client.activity.LocaleManager#changeLocale(java.lang.String, java.lang.String)
   */
  @Override
  public void changeLocale(String language, String country) {
    changeLocale(new Locale(language, country));
  }

  /**
   * @see com.cucumber.editor.client.activity.LocaleManager#observeChangeLocale(com.cucumber.editor.client.view.LocaleListener)
   */
  @Override
  public void observeChangeLocale(LocaleListener listener) {
    localeListener.add(listener);
  }

  /**
   * @see com.cucumber.editor.client.activity.LocaleManager#removeChangeLocale(com.cucumber.editor.client.view.LocaleListener)
   */
  @Override
  public void removeChangeLocale(LocaleListener listener) {
    localeListener.remove(listener);
  }

  /* (non-Javadoc)
   * @see com.cucumber.editor.client.activity.LocaleManager#getLocale()
   */
  @Override
  public Locale getLocale() {
    // TODO get Locale from db
    locale = new Locale("en", "US");
    return locale;
  }

}

/**
 */
package com.cucumber.editor.client;

import com.google.gwt.i18n.client.Constants;
import com.google.gwt.safehtml.shared.SafeHtml;

/**
 * filename: CucumberEditorConstants.java
 * 
 * @date: 01.11.2011
 * @author: Markus Vieghofer
 * 
 */
public interface CucumberEditorConstants extends Constants {
  @DefaultStringValue("Preview")
  String preview();

  @DefaultStringValue("Settings")
  String settings();

  @DefaultStringValue("Save")
  String save();

  @DefaultStringValue("Login")
  String login();

  @DefaultStringValue("Password")
  String password();

  @DefaultStringValue("RepeatPassword")
  String repeatPassword();

  @DefaultStringValue("Register")
  String register();

  @DefaultStringValue("Name")
  String name();

  @DefaultStringValue("Email")
  String email();

  @DefaultStringValue("ErrName")
  String errName();

  @DefaultStringValue("errPasswordMistmatch")
  String errPasswordMismatch();

  @DefaultStringValue("Cancel")
  String cancel();

  @DefaultStringValue("ErrPasswordLength")
  String errPasswordLength();

  @DefaultStringValue("ErrPassword")
  String errPassword();

  @DefaultStringValue("ErrValidEmail")
  String errValidEmail();

  @DefaultStringValue("ErrInsertMail")
  String errInsertMail();

  @DefaultStringValue("ErrCredentials")
  String errCredentials();

  @DefaultStringValue("Language")
  String language();
}

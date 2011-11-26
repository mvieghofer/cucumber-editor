/**
 */
package com.cucumber.editor.client.view;

import com.cucumber.editor.client.CucumberEditor;
import com.cucumber.editor.client.CucumberEditorConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;

/**
 * filename: SettingsImpl.java
 * 
 * @date: 11.09.2011
 * @author: Markus Vieghofer
 * 
 */
public class SettingsImpl implements Settings {

  private boolean languageChanged = false;

  private static final String LOCALE = "locale";

  private Label lblLang = createLabel(getConstants().language(), true);
  private Label lblMsg = createLabel("", false);

  private ListBox lbLang = createLanguageListBox();

  private Button btnSave = createSaveButton();

  private Anchor aClose = createCloseAnchor();

  private Panel languagePanel = createLanguagePanel();
  private Panel innerPanel = createInnerPanel();
  private Panel mainPanel = createMainPanel();

  private CucumberEditor parent;
  private CucumberEditorConstants constants = GWT.create(CucumberEditorConstants.class);

  /**
   * @param cucumberEditor
   */
  public SettingsImpl(CucumberEditor parent) {
    setParent(parent);
    init();
  }

  /**
	 * 
	 */
  private void init() {
    innerPanel.add(lblMsg);
    innerPanel.add(languagePanel);
    innerPanel.add(btnSave);

    mainPanel.add(aClose);
    mainPanel.add(innerPanel);
  }

  /**
   * @return
   */
  private Panel createInnerPanel() {
    Panel panel = new FlowPanel();
    panel.addStyleName("previewText");
    return panel;
  }

  /**
   * @return
   */
  private Anchor createCloseAnchor() {
    Anchor a = new Anchor("Close");
    a.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        remove();
      }
    });
    return a;
  }

  /**
	 * 
	 */
  @Override
  public void remove() {
    getParent().removeSettings();
  }

  /**
   * @see com.cucumber.editor.client.view.Settings#changeLanguage(java.lang.String)
   */
  @Override
  public void changeLanguage(String language) {
    UrlBuilder urlBuilder = Window.Location.createUrlBuilder();
    urlBuilder.removeParameter(LOCALE);
    urlBuilder.setParameter(LOCALE, language);
    Window.Location.replace(urlBuilder.buildString());
  }

  /**
   * @return
   */
  private Panel createLanguagePanel() {
    Panel panel = new FlowPanel();
    panel.addStyleName("fullwidthPanel");
    panel.add(lblLang);
    panel.add(lbLang);
    return panel;
  }

  /**
   * @return
   */
  private Panel createMainPanel() {
    Panel panel = new FlowPanel();
    panel.addStyleName("previewMainPanel");
    return panel;
  }

  /**
   * @return
   */
  private Button createSaveButton() {
    Button btn = new Button(getConstants().save());
    btn.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        if (languageChanged) {
          changeLanguage(lbLang.getValue(lbLang
              .getSelectedIndex()));
        }
      }
    });
    return btn;
  }

  /**
   * @return
   */
  private ListBox createLanguageListBox() {
    ListBox lb = new ListBox(false);
    lb.addItem("Deutsch", "de");
    lb.addItem("English", "en");
    String value = getSelectedItemValue();
    if (value == null) {
      value = "en";
    }
    for (int i = 0; i < lb.getItemCount(); i++) {
      if (lb.getValue(i).equals(value)) {
        lb.setSelectedIndex(i);
      }
    }

    lb.addChangeHandler(new ChangeHandler() {

      @Override
      public void onChange(ChangeEvent event) {
        languageChanged = true;
      }
    });
    return lb;
  }

  /**
   * 
   */
  private String getSelectedItemValue() {
    return Window.Location.getParameter(LOCALE);
  }

  /**
   * @param string
   * @param b
   * @return
   */
  private Label createLabel(String title, boolean visible) {
    Label lbl = new Label(title);
    lbl.setVisible(visible);
    return lbl;
  }

  /**
   * @see com.cucumber.editor.client.view.CucumberEditorWidget#getWidgetComposite()
   */
  @Override
  public Panel getWidgetComposite() {
    return mainPanel;
  }

  /**
   * @param parent
   *          the parent to set
   */
  public void setParent(CucumberEditor parent) {
    this.parent = parent;
  }

  /**
   * @return the parent
   */
  public CucumberEditor getParent() {
    return parent;
  }

  /**
   * @return the constants
   */
  public CucumberEditorConstants getConstants() {
    if (constants == null) {
      constants = GWT.create(CucumberEditorConstants.class);
    }
    return constants;
  }

}

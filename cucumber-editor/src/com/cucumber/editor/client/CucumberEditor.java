package com.cucumber.editor.client;

import com.cucumber.editor.client.view.Feature;
import com.cucumber.editor.client.view.Login;
import com.cucumber.editor.client.view.Preview;
import com.cucumber.editor.client.view.Settings;
import com.cucumber.editor.client.view.WidgetFactory;
import com.cucumber.editor.shared.dto.User;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CucumberEditor implements EntryPoint {//, LocaleListener {

  private Preview preview;
  private Settings settings;

  private WidgetFactory factory = WidgetFactory.getInstance();

  private Button btnSave = createSaveButton();
  private Button btnPreview = createPreviewButton();
  private Button btnSettings;

  private Panel buttonPanel;
  private Panel featurePanel;
  private Panel settingsPanel = createSettingsPanel();
  private Panel mainPanel = createMainPanel();
  private Panel previewPanel = createPreviewPanel();
  final private Panel errorPanel = createErrorPanel();

  private Feature feature;
  private User user;
  private Login login;
  private CucumberEditorConstants constants = GWT.create(CucumberEditorConstants.class);

  // private CucumberFileServiceAsync fileService = (CucumberFileServiceAsync)
  // GWT
  // .create(CucumberFileService.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    if (user == null || !user.isLoggedIn()) {
      login = factory.createLogin(this);
      mainPanel.add(login.getWidgetComposite());
    }
    RootPanel.get("msgContainer").add(errorPanel);
    RootPanel.get("editorContainer").add(mainPanel);
    RootPanel.get("previewContainer").add(previewPanel);
    RootPanel.get("settingsContainer").add(settingsPanel);
  }

  /**
   * @return
   */
  private Panel createSettingsPanel() {
    Panel panel = new FlowPanel();
    return panel;
  }

  /**
   * @param isAdmin
   * @return
   */
  private void createSettingsButton(final boolean isAdmin) {
    btnSettings = new Button(getConstants().settings());
    btnSettings.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        createSettingsView(isAdmin);
      }
    });
  }

  /**
   * @param isAdmin
   * 
   */
  protected void createSettingsView(boolean isAdmin) {
    setSettings(factory.createSettings(this, isAdmin));
    settingsPanel.add(getSettings().getWidgetComposite());
  }

  /**
	 * 
	 */
  protected void createAdminSettingsView() {
    // TODO Auto-generated method stub

  }

  /**
   * @return
   */
  private Panel createPreviewPanel() {
    FlowPanel panel = new FlowPanel();
    return panel;
  }

  /**
   * @return
   */
  private Panel createErrorPanel() {
    FlowPanel panel = new FlowPanel();
    panel.addStyleName("errorMsg");
    panel.setVisible(false);
    return panel;
  }

  /**
   * @return preview button with an onclick event that creates the preview.
   */
  private Button createPreviewButton() {
    Button btn = new Button(getConstants().preview());
    btn.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        createPreview();
      }
    });
    return btn;
  }

  /**
   * This method creates the preview and adds it to the main panel.
   */
  protected void createPreview() {
    setPreview(factory.createPreview(this, feature));
    previewPanel.add(getPreview().getWidgetComposite());
  }

  /**
   * This method sets the text of the error label. Additionally it makes the
   * error label visible.
   * 
   * @param text
   *          the text to be displayed
   */
  public void setErrorLabelText(String text) {
    errorPanel.add(new Label(text));
    if (errorPanel.isVisible()) {
      errorPanel.setVisible(true);
    }
  }

  /**
   * Removes an error message.
   */
  public void clearErrorLabelText() {
    errorPanel.clear();
    errorPanel.setVisible(false);
  }

  /**
   * @return
   */
  private void createButtonPanel() {
    buttonPanel = new FlowPanel();
    buttonPanel.addStyleName("outerButtonPanel");
    createSettingsButton(getUser().isAdmin());
    buttonPanel.add(new Image("germany.png"));
    buttonPanel.add(createSpaceDiv());
    buttonPanel.add(new Image("usa.png"));
    buttonPanel.add(createSpaceDiv());
    buttonPanel.add(btnSave);
    buttonPanel.add(createSpaceDiv());
    buttonPanel.add(btnPreview);
    buttonPanel.add(createClearRightDiv());
  }

  /**
   * @return
   */
  private Widget createClearRightDiv() {
    FlowPanel p = new FlowPanel();
    p.addStyleName("clearRight");
    return p;
  }

  /**
   * @return
   */
  private Widget createSpaceDiv() {
    FlowPanel p = new FlowPanel();
    p.addStyleName("space");
    return p;
  }
  
  

  /**
   * @return
   */
  private Button createSaveButton() {
    Button btn = new Button(getConstants().save());
    btn.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        createDocument();
      }

    });
    return btn;
  }

  private void createDocument() {
    // fileService.save(f, new AsyncCallback<File>() {
    //
    // @Override
    // public void onFailure(Throwable caught) {
    // setErrorLabelText("Error while creating file");
    // }
    //
    // @Override
    // public void onSuccess(File result) {
    // // TODO Auto-generated method stub
    //
    // }
    // });
    Window.open("/cucumber_editor/CucumberFileService", "_blank", "");
  }

  /**
   * @return
   */
  private FlowPanel createMainPanel() {
    FlowPanel panel = new FlowPanel();
    panel.addStyleName("fullWidthPanel");
    return panel;
  }

  /**
   * @return
   */
  private void initFeature() {
    feature = factory.createFeature(this);
    featurePanel = feature.getWidgetComposite();
  }

  public void removePreview() {
    previewPanel.remove(getPreview().getWidgetComposite());
  }

  /**
   * @return the preview
   */
  public Preview getPreview() {
    return preview;
  }

  /**
   * @param preview
   */
  private void setPreview(Preview preview) {
    this.preview = preview;
  }

  /**
   * @param user
   * 
   */
  public void createFeature(User user) {
    setUser(user);

    createButtonPanel();
    initFeature();

    mainPanel.clear();
    mainPanel.add(buttonPanel);
    mainPanel.add(featurePanel);
  }

  /**
   * @param user
   */
  public void setUser(User user) {
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  /**
   * @param settings
   */
  public void removeSettings() {
    settingsPanel.remove(getSettings().getWidgetComposite());
  }

  /**
   * @param settings
   *          the settings to set
   */
  public void setSettings(Settings settings) {
    this.settings = settings;
  }

  /**
   * @return the settings
   */
  public Settings getSettings() {
    return settings;
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

  /**
   * @see com.cucumber.editor.client.view.LocaleListener#setLocale(java.util.Locale)
   */
//  @Override
//  public void setLocale(Locale locale) {
//    this.locale = locale;
//    messages = ResourceBundle.getBundle("MessagesBundle", locale);
//    btnSave = createSaveButton();
//    createSettingsButton(user.isAdmin());
//  }
}

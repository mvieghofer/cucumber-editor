/**
 */
package com.cucumber.editor.client.view;

import com.cucumber.editor.client.CucumberEditor;
import com.cucumber.editor.client.CucumberEditorConstants;
import com.cucumber.editor.shared.dto.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 * filename: LoginImpl.java
 * 
 * @date: 07.09.2011
 * @author: Markus Vieghofer
 * 
 */
public class LoginImpl implements Login {

	/**
	 * 
	 */
	private static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$";
	private static final String PASSWORD_REGEX = "^.{6,14}$";
	private TextBox txtEmail;
	private TextBox txtPassword;
	private TextBox txtPasswordRepeat;
	private TextBox txtName;

	private Label lblEmail;
	private Label lblPassword;
	private Label lblPasswordRepeat;
	private Label lblName;

	private FlowPanel emailPanel;
	private FlowPanel passwordPanel;
	private FlowPanel passwordRepeatPanel;
	private FlowPanel loginPanel;
	private FlowPanel errorPanel;
	private FlowPanel namePanel;
	private FlowPanel mainPanel;

	private boolean isLogin;
	
	private User user;

	private Button btnLogin = createLoginButton();
	private Button btnRegister = createRegisterButton();
	private Button btnCancleRegister = createCancleButton();

	private Anchor aRegister = createRegisterAnchor();

	private CucumberEditor parent;
  private CucumberEditorConstants constants = GWT.create(CucumberEditorConstants.class);

	/**
	 * @param widgetFactory
	 */
	public LoginImpl(CucumberEditor cucumberEditor) {
		setParent(cucumberEditor);
		createTextBoxes();
		createLabels();
		initMainPanel();
		setLogin(true);
	}

	/**
	 * @return
	 */
	private Button createCancleButton() {
		Button btn = new Button(getConstants().cancel());
		btn.addStyleName("loginButton");
		btn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				setLogin(true);
				mainPanel = null;
				createMainPanel();
			}
		});
		return btn;
	}

	/**
	 * @return
	 */
	private Anchor createRegisterAnchor() {
		Anchor a = new Anchor(getConstants().register());
		a.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clearErrors();
				createRegisterForm();
			}
		});
		return a;
	}

	/**
	 * @return
	 */
	private Button createRegisterButton() {
		Button btn = new Button(getConstants().register());
		btn.addStyleName("loginButton");
		btn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clearErrors();
				setLogin(false);
				validate();
			}

		});
		return btn;
	}

	/**
	 * @return
	 */
	private Button createLoginButton() {
		Button btn = new Button(getConstants().login());
		btn.addStyleName("loginButton");
		btn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clearErrors();
				boolean validate = validate();
				boolean login = login(txtEmail.getValue(),
						txtPassword.getValue());
				if (validate && login) {
					getParent().createFeature(getUser());
				} else {
					addError(getConstants().errCredentials());
				}
			}

		});
		return btn;
	}

	/**
	 * 
	 */
	protected boolean validate() {
		boolean validLoginData = validateLoginData();
		boolean validRegisterData = true;
		if (!isLogin) {
			validRegisterData = validateRegisterData();
		}
		return validLoginData && validRegisterData;
	}

	/**
	 * @return 
	 * 
	 */
	private boolean validateRegisterData() {
		boolean valid = true;
		if (!txtPassword.getValue().equals(txtPasswordRepeat.getValue())) {
			addError(getConstants().errPasswordMismatch());
			valid = false;
		}
		if (txtName.getValue() == null || txtName.getValue().equals("")) {
			addError(getConstants().errName());
			valid = false;
		}
		return valid;
	}

	/**
	 * @return 
	 * 
	 */
	private boolean validateLoginData() {
		boolean valid = true;
		if (txtEmail.getValue() == null || txtEmail.getValue().equals("")) {
			addError(getConstants().errInsertMail());
			valid = false;
		} else if (!txtEmail.getValue().toUpperCase().matches(EMAIL_REGEX)) {
			addError(getConstants().errValidEmail());
			valid = false;
		}
		if (txtPassword.getValue() == null || txtPassword.getValue().equals("")) {
			addError(getConstants().errPassword());
			valid = false;
		} else if (!txtPassword.getValue().toUpperCase().matches(PASSWORD_REGEX)) {
			addError(getConstants().errPasswordLength());
			valid = false;
		}
		return valid;
	}

	/**
	 * @param errorMsg
	 */
	@Override
	public void addError(String errorMsg) {
		errorPanel.add(new Label(errorMsg));
		errorPanel.setVisible(true);
	}

	/**
	 * 
	 */
	private void createTextBoxes() {
		txtEmail = createLoginTextBox();
		txtPassword = createPasswordTextBox();
		txtEmail.setValue("asdf@asdf.at");
		txtPassword.setValue("asdfas");
	}

	/**
	 * 
	 */
	private void createLabels() {
		lblEmail = createLabel(getConstants().email());
		lblPassword = createLabel(getConstants().password());
	}

	/**
	 * @return
	 */
	private Label createLabel(String title) {
		Label lbl = new Label(title);
		return lbl;
	}

	/**
	 * @see com.cucumber.editor.client.view.Login#login(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public boolean login(String username, String password) {
		User u = new User();
		u.setAdmin(true);
		u.setPassword(txtPassword.getValue());
		u.setLoggedIn(true);
		u.setEmail(txtEmail.getValue());
		u.setName("Markus Vieghofer");
		u.setId("1234");
		setUser(u);
		return true;
	}

	/**
	 * @return
	 */
	private TextBox createLoginTextBox() {
		TextBox txt = new TextBox();
		return txt;
	}

	/**
	 * @return
	 */
	private TextBox createPasswordTextBox() {
		TextBox txt = new PasswordTextBox();
		return txt;
	}
	
	/**
	 * 
	 */
	protected void createMainPanel() {
		loginPanel.remove(passwordRepeatPanel);
		loginPanel.remove(namePanel);
		
		loginPanel.add(aRegister);
		
		loginPanel.add(btnLogin);
		loginPanel.remove(btnRegister);
		loginPanel.remove(btnCancleRegister);
	}
	
	/**
	 * 
	 */
	protected void createRegisterForm() {
		passwordRepeatPanel = createPanel("loginPanelRow");
		namePanel = createPanel("loginPanelRow");
		
		txtPasswordRepeat = createPasswordTextBox();
		txtName = createLoginTextBox();
		
		lblPasswordRepeat = createLabel(getConstants().repeatPassword());
		lblName = createLabel(getConstants().name());
		
		passwordRepeatPanel.add(lblPasswordRepeat);
		passwordRepeatPanel.add(txtPasswordRepeat);
		
		namePanel.add(lblName);
		namePanel.add(txtName);
		
		loginPanel.add(passwordRepeatPanel);
		loginPanel.add(namePanel);
		
		loginPanel.remove(aRegister);
		
		loginPanel.remove(btnLogin);
		loginPanel.add(btnRegister);
		loginPanel.add(btnCancleRegister);
	}

	/**
	 * @return
	 */
	private void initMainPanel() {
		emailPanel = createPanel("loginPanelRow");
		passwordPanel = createPanel("loginPanelRow");
		errorPanel = createPanel("loginErrorMsg");
		errorPanel.setVisible(false);

		emailPanel.add(lblEmail);
		emailPanel.add(txtEmail);

		passwordPanel.add(lblPassword);
		passwordPanel.add(txtPassword);

		loginPanel = new FlowPanel();
		loginPanel.addStyleName("loginPanel");

		loginPanel.add(emailPanel);
		loginPanel.add(passwordPanel);
		loginPanel.add(btnLogin);
		loginPanel.add(aRegister);

		mainPanel = new FlowPanel();
		mainPanel.addStyleName("mainLoginPanel");
		mainPanel.add(errorPanel);
		mainPanel.add(loginPanel);
	}

	/**
	 * @param styleName
	 * @param passwordRepeatPanel2
	 * @return
	 */
	private FlowPanel createPanel(String styleName) {
		FlowPanel panel = new FlowPanel();
		panel.addStyleName(styleName);
		return panel;
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
	 *            the parent to set
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
	 * @see com.cucumber.editor.client.view.Login#clearErrors()
	 */
	@Override
	public void clearErrors() {
		errorPanel.clear();
		errorPanel.setVisible(false);
	}

	/**
	 * @param isLogin
	 *            the isLogin to set
	 */
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	/**
	 * @return the isLogin
	 */
	public boolean isLogin() {
		return isLogin;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
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

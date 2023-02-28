package com.solvd.saucedemo.web.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.PageOpeningStrategy;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @FindBy(id = "%s")
    private ExtendedWebElement loginInput;

    @FindBy(id = "login-button")
    private ExtendedWebElement loginButton;

    @FindBy(className = "error-message-container")
    private ExtendedWebElement errorMessageContainer;

    public void fillLoginInput(LoginField loginField, String value) {
        loginInput.format(loginField.getIdOfField()).type(value);
    }

    public ShoppingPage login() {
        loginButton.click();
        return new ShoppingPage(driver);
    }

    public String getContainerErrorMessage() {
        return errorMessageContainer.getText();
    }

    public boolean isErrorMessagePresent() {
        return errorMessageContainer.isPresent();
    }

    public enum LoginField {
        USERNAME("user-name"),
        PASSWORD("password");

        private String idOfField;

        LoginField(String idOfField) {
            this.idOfField = idOfField;
        }

        public String getIdOfField() {
            return idOfField;
        }
    }

    public LoginPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_URL);
    }
}
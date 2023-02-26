package com.solvd.saucedemo.web.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.PageOpeningStrategy;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @FindBy(id = "user-name")
    private ExtendedWebElement usernameField;

    @FindBy(id = "password")
    private ExtendedWebElement passwordFiled;

    @FindBy(id = "login-button")
    private ExtendedWebElement loginButton;

    @FindBy(className = "error-message-container")
    private ExtendedWebElement errorMessageContainer;

    public ShoppingPage loginWithData(String username, String password){
        usernameField.type(username);
        passwordFiled.type(password);
        loginButton.click();
        return new ShoppingPage(driver);
    }

    public String getContainerErrorMessage(){
        return errorMessageContainer.getText();
    }

    public boolean isErrorMessagePresent() {
        return errorMessageContainer.isPresent();
    }
    public LoginPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_URL);
    }
}

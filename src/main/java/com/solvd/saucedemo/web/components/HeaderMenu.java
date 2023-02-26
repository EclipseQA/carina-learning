package com.solvd.saucedemo.web.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.solvd.saucedemo.web.pages.LoginPage;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HeaderMenu extends AbstractUIObject {

    @FindBy(xpath = "//div[@class='bm-menu-wrap']//a[text()='%s']")
    private ExtendedWebElement option;

    @FindBy(className = "bm-burger-button")
    private ExtendedWebElement menuButton;

    public void openHeaderMenu() {
        menuButton.click();
    }
    public LoginPage logout() {
        option.format(MenuOptions.LOGOUT.getValue()).click();
        return new LoginPage(driver);
    }

    public HeaderMenu(WebDriver driver) {
        super(driver);
    }

    public HeaderMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }
    public enum MenuOptions {
        ALL_ITEMS("All Items"),
        ABOUT("About"),
        LOGOUT("Logout"),
        RESET_APP_STATE("Reset App State");

        private String value;

        private MenuOptions(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}

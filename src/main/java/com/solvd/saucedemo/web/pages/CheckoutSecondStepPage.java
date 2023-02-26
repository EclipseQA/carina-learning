package com.solvd.saucedemo.web.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CheckoutSecondStepPage extends AbstractPage {


    @FindBy(id = "finish")
    private ExtendedWebElement finishButton;

    public CheckoutCompletePage makeAnOrder() {
        finishButton.click();
        return new CheckoutCompletePage(driver);
    }

    public CheckoutSecondStepPage(WebDriver driver) {
        super(driver);
    }
}

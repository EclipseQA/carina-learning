package com.solvd.saucedemo.web.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CheckoutFirstStepPage extends AbstractPage {

    @FindBy(id = "continue")
    private ExtendedWebElement continueButton;

    @FindBy(id = "cancel")
    private ExtendedWebElement cancelButton;

    @FindBy(id = "first-name")
    private ExtendedWebElement firstNameField;

    @FindBy(id = "last-name")
    private ExtendedWebElement lastNameField;

    @FindBy(id = "postal-code")
    private ExtendedWebElement zipCodeField;


    public void fillCheckoutInformation(String firstName,
                                        String lastName,
                                        String zipCode) {
        firstNameField.type(firstName);
        lastNameField.type(lastName);
        zipCodeField.type(zipCode);
    }

    public CheckoutSecondStepPage proceedToSecondStep() {
        continueButton.click();
        return new CheckoutSecondStepPage(driver);
    }

    public CheckoutFirstStepPage(WebDriver driver) {
        super(driver);
    }
}

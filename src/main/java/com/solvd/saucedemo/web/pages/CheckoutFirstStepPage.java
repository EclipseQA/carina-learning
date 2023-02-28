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

    @FindBy(id = "%s")
    private ExtendedWebElement checkoutFieldInput;


    public void fillCheckoutInformation(CheckoutField field, String value) {
        checkoutFieldInput.format(field.getIdOfCheckoutInput()).type(value);
    }

    public CheckoutSecondStepPage proceedToSecondStep() {
        continueButton.click();
        return new CheckoutSecondStepPage(driver);
    }

    public CheckoutFirstStepPage(WebDriver driver) {
        super(driver);
    }

    public enum CheckoutField {
        FIRST_NAME("first-name"),
        LAST_NAME("last-name"),
        ZIP_CODE("postal-code");

        private String idOfCheckoutInput;

        CheckoutField(String idOfCheckoutInput) {
            this.idOfCheckoutInput = idOfCheckoutInput;
        }

        public String getIdOfCheckoutInput() {
            return idOfCheckoutInput;
        }
    }
}
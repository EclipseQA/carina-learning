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
        switch (field) {
            case FIRST_NAME:
                checkoutFieldInput.format(CheckoutField.FIRST_NAME.getIdOfCheckoutInput()).type(value);
                break;
            case LAST_NAME:
                checkoutFieldInput.format(CheckoutField.LAST_NAME.getIdOfCheckoutInput()).type(value);
                break;
            case ZIP_CODE:
                checkoutFieldInput.format(CheckoutField.ZIP_CODE.getIdOfCheckoutInput()).type(value);
                break;
            default:
                throw new IllegalArgumentException("Invalid field: " + field);
        }
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
package com.solvd.saucedemo.web.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends AbstractPage {

    @FindBy(id = "checkout")
    private ExtendedWebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private ExtendedWebElement continueShoppingButton;

    @FindBy(className = "cart_item_label")
    private List<ExtendedWebElement> productsInCart;


    public List<String> getNamesOfProductsInCart() {
        List<String> listOfNames = new ArrayList<>();
        for (ExtendedWebElement element :
                productsInCart) {
            String nameToFormat = element.getText();
            int indexOfCut = nameToFormat.indexOf("\n");
            String formattedName = nameToFormat.substring(0, indexOfCut);
            listOfNames.add(formattedName);
            ;
        }
        return listOfNames;
    }

    public CheckoutFirstStepPage proceedToCheckout() {
        checkoutButton.check();
        return new CheckoutFirstStepPage(driver);
    }

    public int getNumberOfProductsInCart() {
        return productsInCart.size();
    }

    public CartPage(WebDriver driver) {
        super(driver);
    }
}

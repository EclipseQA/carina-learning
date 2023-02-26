package com.solvd.saucedemo.web;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.saucedemo.web.pages.*;
import com.zebrunner.carina.utils.R;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest implements IAbstractTest {

    @Test
    public void testMakeAnOrderWithValidData() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        loginPage.loginWithData(
                R.TESTDATA.get("valid_username"),
                R.TESTDATA.get("valid_password"));

        ShoppingPage shoppingPage = new ShoppingPage(getDriver());
        shoppingPage.addProductToCart();

        CartPage cartPage = shoppingPage.goToCartPage();

        CheckoutFirstStepPage checkoutFirstStepPage = cartPage.proceedToCheckout();
        checkoutFirstStepPage.fillCheckoutInformation(
                R.TESTDATA.get("valid_first_name"),
                R.TESTDATA.get("valid_last_name"),
                R.TESTDATA.get("valid_zipcode"));

        CheckoutSecondStepPage checkoutSecondStepPage = checkoutFirstStepPage.proceedToSecondStep();

        CheckoutCompletePage checkoutCompletePage = checkoutSecondStepPage.makeAnOrder();

        Assert.assertTrue(checkoutCompletePage.isPageOpened());
    }
}

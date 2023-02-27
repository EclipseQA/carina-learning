package com.solvd.saucedemo.web;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.saucedemo.web.pages.*;
import com.zebrunner.carina.utils.R;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

import static com.solvd.saucedemo.web.pages.CheckoutFirstStepPage.CheckoutField;

public class CheckoutTest implements IAbstractTest {

    @Test
    public void testMakeAnOrderWithValidData() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        loginPage.fillLoginInput(
                LoginPage.LoginField.USERNAME,
                R.TESTDATA.get("valid_username"));
        loginPage.fillLoginInput(
                LoginPage.LoginField.PASSWORD,
                R.TESTDATA.get("valid_password"));

        ShoppingPage shoppingPage = loginPage.login();
        int expectedAmountOfAddedProducts = new Random().nextInt(5) + 1;
        shoppingPage.addProductToCart(expectedAmountOfAddedProducts);

        CartPage cartPage = shoppingPage.goToCartPage();

        CheckoutFirstStepPage checkoutFirstStepPage = cartPage.proceedToCheckout();
        checkoutFirstStepPage.fillCheckoutInformation(
                CheckoutField.FIRST_NAME,
                R.TESTDATA.get("valid_first_name"));
        checkoutFirstStepPage.fillCheckoutInformation(
                CheckoutField.LAST_NAME,
                R.TESTDATA.get("valid_last_name"));
        checkoutFirstStepPage.fillCheckoutInformation(
                CheckoutField.ZIP_CODE,
                R.TESTDATA.get("valid_zipcode"));


        CheckoutSecondStepPage checkoutSecondStepPage = checkoutFirstStepPage.proceedToSecondStep();

        CheckoutCompletePage checkoutCompletePage = checkoutSecondStepPage.makeAnOrder();

        Assert.assertTrue(checkoutCompletePage.isPageOpened());
    }
}

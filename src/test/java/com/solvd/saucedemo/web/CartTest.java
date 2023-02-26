package com.solvd.saucedemo.web;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.saucedemo.web.pages.LoginPage;
import com.solvd.saucedemo.web.pages.ShoppingPage;
import com.solvd.saucedemo.web.pages.CartPage;
import com.zebrunner.carina.utils.R;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CartTest implements IAbstractTest {

    @Test
    public void testPresenceAddedItemsInCart() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        loginPage.loginWithData(
                R.TESTDATA.get("valid_username"),
                R.TESTDATA.get("valid_password"));

        ShoppingPage shoppingPage = new ShoppingPage(getDriver());
        shoppingPage.addProductToCart();
        List<String> addedProductsList = shoppingPage.getAddedProductsNames();

        CartPage cartPage = shoppingPage.goToCartPage();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getNumberOfProductsInCart()
                , shoppingPage.getExpectedAmountOfAddedProducts());
        Assert.assertEquals(cartPage.getNamesOfProductsInCart(), addedProductsList);
    }
}

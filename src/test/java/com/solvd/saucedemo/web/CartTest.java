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
import java.util.Random;

public class CartTest implements IAbstractTest {

    @Test
    public void testPresenceAddedItemsInCart() {
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
        List<String> addedProductsList = shoppingPage.getAddedProductsNames();

        CartPage cartPage = shoppingPage.goToCartPage();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getNumberOfProductsInCart()
                , expectedAmountOfAddedProducts);
        Assert.assertEquals(cartPage.getNamesOfProductsInCart(), addedProductsList);
    }
}

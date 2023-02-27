package com.solvd.saucedemo.web;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.saucedemo.web.pages.LoginPage;
import com.solvd.saucedemo.web.pages.ShoppingPage;;
import com.zebrunner.carina.utils.R;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ShoppingTest implements IAbstractTest {

    @Test
    public void testProductsSortedByPriceDescending() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        loginPage.fillLoginInput(
                LoginPage.LoginField.USERNAME,
                R.TESTDATA.get("valid_username"));
        loginPage.fillLoginInput(
                LoginPage.LoginField.PASSWORD,
                R.TESTDATA.get("valid_password"));

        ShoppingPage shoppingPage = loginPage.login();
        shoppingPage.sortProducts(ShoppingPage.SortProductsType.PRICE_DESC);

        List<Float> actualSortedPrices = shoppingPage.getProductsPrices();
        List<Float> expectedSortedPrices = new ArrayList<>(actualSortedPrices);
        expectedSortedPrices.sort(Comparator.reverseOrder());

        Assert.assertEquals(actualSortedPrices
                , expectedSortedPrices);
    }

    @Test
    public void testAddToCartProducts() {
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

        Assert.assertEquals(shoppingPage.getActualAmountOfAddedProducts()
                , expectedAmountOfAddedProducts);
    }

    @Test
    public void testDeleteAddedProduct() {
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

        shoppingPage.deleteProductsFromCart();
        Assert.assertEquals(shoppingPage.getActualAmountOfAddedProducts()
                , shoppingPage.getActualAmountOfAddedProducts());
    }
}

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

public class ShoppingTest implements IAbstractTest {

    @Test
    public void testProductsSortedByPriceDescending() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        loginPage.loginWithData(
                R.TESTDATA.get("valid_username"),
                R.TESTDATA.get("valid_password"));

        ShoppingPage shoppingPage = new ShoppingPage(getDriver());
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
        loginPage.loginWithData(
                R.TESTDATA.get("valid_username"),
                R.TESTDATA.get("valid_password"));

        ShoppingPage shoppingPage = new ShoppingPage(getDriver());
        shoppingPage.addProductToCart();

        Assert.assertEquals(shoppingPage.getActualAmountOfAddedProducts()
                , shoppingPage.getExpectedAmountOfAddedProducts());
    }

    @Test
    public void testDeleteAddedProduct() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        loginPage.loginWithData(
                R.TESTDATA.get("valid_username"),
                R.TESTDATA.get("valid_password"));

        ShoppingPage shoppingPage = new ShoppingPage(getDriver());
        shoppingPage.addProductToCart();

        shoppingPage.deleteProductsFromCart();
        Assert.assertEquals(shoppingPage.getActualAmountOfAddedProducts()
                , shoppingPage.getActualAmountOfAddedProducts());
    }
}

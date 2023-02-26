package com.solvd.saucedemo.web;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.saucedemo.web.components.HeaderMenu;
import com.solvd.saucedemo.web.pages.LoginPage;
import com.solvd.saucedemo.web.pages.ShoppingPage;
import com.zebrunner.carina.utils.R;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest implements IAbstractTest {

    @Test
    public void testLogoutFromShoppingPage() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        ShoppingPage shoppingPage = loginPage.loginWithData(
                R.TESTDATA.get("valid_username"),
                R.TESTDATA.get("valid_password"));

        HeaderMenu headerMenu = shoppingPage.switchToHeaderMenu();
        headerMenu.openHeaderMenu();

        LoginPage loginPage1 = headerMenu.logout();

        Assert.assertTrue(loginPage1.isPageOpened());
    }
}

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
        loginPage.fillLoginInput(
                LoginPage.LoginField.USERNAME,
                R.TESTDATA.get("valid_username"));
        loginPage.fillLoginInput(
                LoginPage.LoginField.PASSWORD,
                R.TESTDATA.get("valid_password"));

        ShoppingPage shoppingPage = loginPage.login();

        HeaderMenu headerMenu = shoppingPage.switchToHeaderMenu();
        headerMenu.openHeaderMenu();

        LoginPage loginPage1 = headerMenu.logout();

        Assert.assertTrue(loginPage1.isPageOpened());
    }
}

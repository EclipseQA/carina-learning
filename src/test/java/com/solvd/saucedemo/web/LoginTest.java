package com.solvd.saucedemo.web;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.saucedemo.web.pages.LoginPage;
import com.solvd.saucedemo.web.pages.ShoppingPage;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.utils.R;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest implements IAbstractTest {

    @Test
    @MethodOwner(owner = "EclipseQA")
    public void testLoginWithValidCreds() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        loginPage.loginWithData(
                R.TESTDATA.get("valid_username"),
                R.TESTDATA.get("valid_password"));

        ShoppingPage shoppingPage = new ShoppingPage(getDriver());

        Assert.assertTrue(shoppingPage.isPageOpened());
    }

    @Test
    @MethodOwner(owner = "EclipseQA")
    public void testLoginWithInvalidUsername() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        loginPage.loginWithData(
                R.TESTDATA.get("invalid_username"),
                R.TESTDATA.get("valid_password"));

        Assert.assertTrue(loginPage.isErrorMessagePresent());
    }

    @Test
    @MethodOwner(owner = "EclipseQA")
    public void testLoginBlockedUser() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        loginPage.loginWithData(
                R.TESTDATA.get("blocked_username"),
                R.TESTDATA.get("valid_password"));

        Assert.assertEquals(loginPage.getContainerErrorMessage(),
                R.TESTDATA.get("warning_message"));
    }
}

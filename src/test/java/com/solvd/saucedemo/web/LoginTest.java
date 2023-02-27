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
        loginPage.fillLoginInput(
                LoginPage.LoginField.USERNAME,
                R.TESTDATA.get("valid_username"));
        loginPage.fillLoginInput(
                LoginPage.LoginField.PASSWORD,
                R.TESTDATA.get("valid_password"));

        ShoppingPage shoppingPage = loginPage.login();

        Assert.assertTrue(shoppingPage.isPageOpened());
    }

    @Test
    @MethodOwner(owner = "EclipseQA")
    public void testLoginWithInvalidUsername() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        loginPage.fillLoginInput(
                LoginPage.LoginField.USERNAME,
                R.TESTDATA.get("invalid_username"));
        loginPage.fillLoginInput(
                LoginPage.LoginField.PASSWORD,
                R.TESTDATA.get("valid_password"));
        loginPage.login();
        Assert.assertTrue(loginPage.isErrorMessagePresent());
    }

    @Test
    @MethodOwner(owner = "EclipseQA")
    public void testLoginBlockedUser() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        loginPage.fillLoginInput(
                LoginPage.LoginField.USERNAME,
                R.TESTDATA.get("blocked_username"));
        loginPage.fillLoginInput(
                LoginPage.LoginField.PASSWORD,
                R.TESTDATA.get("valid_password"));
        loginPage.login();

        Assert.assertEquals(loginPage.getContainerErrorMessage(),
                R.TESTDATA.get("warning_message"));
    }
}

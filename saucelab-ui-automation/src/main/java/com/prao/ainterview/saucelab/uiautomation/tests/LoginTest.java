package com.prao.ainterview.saucelab.uiautomation.tests;
import com.prao.ainterview.saucelab.uiautomation.pages.HomePage;
import com.prao.ainterview.saucelab.uiautomation.util.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends BaseTest {

    @Test
    public void validLogin() throws Exception {
        SoftAssert softAssert = new SoftAssert();


        HomePage.Login.login("standard_user", "secret_sauce");

       // softAssert.assertTrue(HomePage.MenuBar.menuBarButtonExists(), "Unable to find Menu Bar button, login must have failed");
        softAssert.assertTrue(HomePage.Dashboard.productContainerIsVisible(), "Unable to find Product Container, dashboard must have failed to load");

        softAssert.assertAll();
    }

    @Test
    public void invalidLogin() {
        final String expectedMessage = "do not match any user";

        HomePage.Login.login("standard_user", "invalid");

       // Wait.For("Error Message to Exist ", HomePage.Login::isErrorVisible);
        String actualMessage = HomePage.Login.getErrorText();
        Assert.assertTrue(actualMessage.contains(expectedMessage), String.format("Incorrect Login error message.  Expected partial message: '%s'  Found: '%s'", expectedMessage, actualMessage));
    }
}


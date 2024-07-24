package org.example.Selenium.tests;

import org.example.Selenium.pages.RegisterPage;
import org.example.Selenium.utils.Driver;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class RegisterTest {

    public RegisterPage registerPage;

    public RegisterTest() {
        this.registerPage = new RegisterPage();
    }

    @BeforeMethod
    public void init() {
         Driver.login();
    }

    @AfterMethod
    public void afterEach() {
         registerPage.logout();
    }

    @Test
    public void registerSuccessfullyTest() {

        registerPage.navigateToHomePage();
        registerPage.clickRegisterButton();

        Assert.assertEquals(Driver.getDriver().getTitle(), "nopCommerce demo store. Register");

        registerPage.register("Sara", "Mekshaj", "saramekshaj19@gmail.com", "LHIND", "111222333", "14", "April", "2001") ;
            String registrationMessage = registerPage.getRegistrationMessage();
            Assert.assertTrue(registrationMessage.contains("Your registration completed"));
        }
    }



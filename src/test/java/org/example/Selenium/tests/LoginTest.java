package org.example.Selenium.tests;

import org.example.Selenium.pages.LoginPage;
import org.example.Selenium.utils.Driver;
import org.testng.Assert;
import org.testng.annotations.*;



public class LoginTest   {

    public LoginPage loginPage;

    public LoginTest() {
        this.loginPage = new LoginPage();
    }


    public void init()
    {
       Driver.login();
    }

    @AfterMethod
    public void logout()

    {
        loginPage.logout();
    }

    @Test
    public void loginTest(){
        loginPage.navigateToHomePage();
        loginPage.clickLoginButton();
        loginPage.login("saramekshaj19@gmail.com", "111222333");
        loginPage.submit();

        Assert.assertEquals("Welcome to our store", loginPage.successLoginMessage.getText());
    }
}

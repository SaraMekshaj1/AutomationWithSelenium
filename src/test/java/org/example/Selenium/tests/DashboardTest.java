package org.example.Selenium.tests;
import org.example.Selenium.pages.NoteBookPage;
import org.junit.Assert;
import org.testng.annotations.*;
import org.example.Selenium.pages.DashboardPage;
import org.example.Selenium.pages.LoginPage;
import org.example.Selenium.utils.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class DashboardTest {

    public LoginPage loginPage;

    public DashboardPage dashbordPage;

   public NoteBookPage noteBookPage;


    public DashboardTest() {
        loginPage = new LoginPage();
        dashbordPage = new DashboardPage();
        noteBookPage= new NoteBookPage();
    }

    @BeforeMethod
    public void init() {
        Driver.login();
    }

    @AfterMethod
    public void close() {
        noteBookPage.logout();
    }

    @Test
    public void ClickElementsToAdd() {

        int initialWishingListElements = Integer.parseInt(Driver.removeParanthesis(noteBookPage.wishlistCardNumberDisplayed.getText()));
        int initialShoppingCartElement = Integer.parseInt(Driver.removeParanthesis(noteBookPage.shoppingCartNumberDisplayed.getText()));

        dashbordPage.clickNotebooks();

        // Click on the second element
        // Verify that the message is displayed after clicking the second element
        Assert.assertEquals(noteBookPage.clickSecondElementAndDisplayMessage(), "The product has been added to your wishlist");

        // Click on the third element
        // Verify that the message is displayed after clicking the third element
        Assert.assertEquals(noteBookPage.clickThirdElementAndDisplayMessage(), "The product has been added to your wishlist");

        // Click on the fourth element
        // Verify that the message is displayed after clicking the fourth element
        Assert.assertEquals(noteBookPage.clickFourthElementAndDisplayMessage(), "The product has been added to your shopping cart");

        // Click on the fifth element
        // Verify that the message is displayed after clicking the fifth element
        Assert.assertEquals(noteBookPage.clickfifthElementAndDisplayMessage(), "The product has been added to your shopping cart");
        // Click on the sixth element
        // Verify that the message is displayed after clicking the sixth element
        Assert.assertEquals(noteBookPage.clickSixthElementAndDisplayMessage(), "The product has been added to your shopping cart");

        //Verify that Wishlist on Menu bar displays +2
        Assert.assertEquals(Integer.parseInt(Driver.removeParanthesis(noteBookPage.wishlistCardNumberDisplayed.getText())), initialWishingListElements + 2);
        //Verify that Shopping Cart on Menu bar displays +3
        Assert.assertEquals(Integer.parseInt(Driver.removeParanthesis(noteBookPage.shoppingCartNumberDisplayed.getText())), initialShoppingCartElement + 3);
    }



}

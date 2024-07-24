package org.example.Selenium.tests;

import org.example.Selenium.pages.CheckoutPage;
import org.example.Selenium.pages.LoginPage;
import org.example.Selenium.pages.NoteBookPage;
import org.example.Selenium.pages.ShoppingCartPage;
import org.example.Selenium.utils.Driver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class ShopingCardTest {
    public ShoppingCartPage shoppingCartPage;
    public LoginPage loginPage;
    public CheckoutPage checkoutPage;
    public NoteBookPage noteBookPage;

    public ShopingCardTest() {
        shoppingCartPage = new ShoppingCartPage();
        loginPage = new LoginPage();
        checkoutPage = new CheckoutPage();
    }

    @BeforeMethod
    public void init() {
        Driver.login();
        int initialShoppingCartElement = Integer.parseInt(Driver.removeParanthesis(shoppingCartPage.shoppingCartNumberDisplayed.getText()));
        if(initialShoppingCartElement == 0){
            noteBookPage = new NoteBookPage();
            noteBookPage.navigateToNotebookPage();
            noteBookPage.clickSixthElementAndDisplayMessage();
        }
    }
    @Test
    public void shoppingCardTest() {
        //Hover over Shopping Cart – Menu
        shoppingCartPage.hoverShoppingCardButton();
        //Verify that ‘Go To Cart’ – button is displayed
        Assertions.assertTrue(shoppingCartPage.goToCartButton.isDisplayed());
        // Click ‘Go To Cart’ – button
        shoppingCartPage.navigate2GoToCard();
        //Verify that we have navigate to Shopping Cart Page
        Assertions.assertEquals(shoppingCartPage.shoppingCartURL, Driver.getDriver().getCurrentUrl());
        Assertions.assertEquals("Shopping cart", shoppingCartPage.pageTitle.getText());
        // Verify that following buttons are displayed
        //Apperantely, "Update shopping cart button" is not visible.
        Assertions.assertTrue(shoppingCartPage.continueShoppingButton.isDisplayed() && shoppingCartPage.estimateShippingButton.isDisplayed());
        //Verify that the prices sum for all items is equal to Total Price in the end of the page
        double totalPrice = shoppingCartPage.getTotalPrice();
        Assertions.assertEquals(shoppingCartPage.sumProductUnitPriceInTheShoppingCart(), totalPrice);
        //Click “Update shopping cart” – button
        //shoppingCartPageDK.clickUpdateShoppingCartButton();
        //Check checkbox with text: I agree with the terms of service and I adhere to them unconditionally
        //EXTRA: Check if the checkbox is not selected first
        Assertions.assertFalse(shoppingCartPage.inputTermsAndConditions.isSelected());
        shoppingCartPage.checkTermsAndConditions();
        Assertions.assertTrue(shoppingCartPage.inputTermsAndConditions.isSelected());
        // Click “Checkout” – button
        shoppingCartPage.clickCheckoutButton();
        //Verify “First name”, “Last name” and “Email” input fields displays the values you entered while Register.
        Assertions.assertEquals("Sara", checkoutPage.firstName.getAttribute("value"));
        Assertions.assertEquals("Mekshaj", checkoutPage.lastName.getAttribute("value"));
        Assertions.assertEquals("saramekshaj19@gmail.com", checkoutPage.email.getAttribute("value"));


        checkoutPage.clickContinueButton("Billing address");
        if(checkoutPage.listOfValidationErrorMessages.size() != 0){
            Assertions.assertEquals(5, checkoutPage.listOfValidationErrorMessages.size());
            //EXTRA: Checking if the "City is required" error validation message has appeared
            Assertions.assertTrue(checkoutPage.checkIfErrorValidationMessageHasAppeared("City is required"));
            //EXTRA: The following error message: "INVALID ERROR" should not be present
            Assertions.assertFalse(checkoutPage.checkIfErrorValidationMessageHasAppeared("INVALID ERROR"));
            checkoutPage.fillShippingAddressInformation("Albania", "Lezhe", "Tale, Lezhe", "4500", "+355676920560");
            //Click “Continue” - button
            checkoutPage.clickContinueButton("Billing address");
        }
        //In Shipping method, select the second option (Next Day Air)

        checkoutPage.clickOption(checkoutPage.shippingOptions, 0);
        Assertions.assertTrue(checkoutPage.shippingOptions.get(0).isSelected() && !checkoutPage.shippingOptions.get(1).isSelected() && !checkoutPage.shippingOptions.get(2).isSelected());
        checkoutPage.clickOption(checkoutPage.shippingOptions, 2);
        Assertions.assertTrue(!checkoutPage.shippingOptions.get(0).isSelected() && !checkoutPage.shippingOptions.get(1).isSelected() && checkoutPage.shippingOptions.get(2).isSelected());
        //------ Let's ensure that the second option is "Next Day Air"
        Assertions.assertTrue(checkoutPage.labelForNextDayShippingOption.getText().contains("Next Day Air"));
        //In Shipping method, select the second option (Next Day Air)
        checkoutPage.clickOption(checkoutPage.shippingOptions, 1);
        // Click “Continue” – button
        checkoutPage.clickContinueButton("Shipping method");
        //EXTRA: Check if there are two payment options
        Assertions.assertEquals(2, checkoutPage.listOfPaymentOptions.size());
        // In Payment method select the first option (Check/Money Order)
        checkoutPage.clickOption(checkoutPage.listOfPaymentOptions, 0);
        //  Click “Continue” – button
        checkoutPage.clickContinueButton("Payment method");
        // In Payment Information click “Continue” – button
        checkoutPage.clickContinueButton("Payment Information");
        // In Confirm Order, verify that the price displayed in Total is the same as the one in step 6.
        Assertions.assertEquals(totalPrice, checkoutPage.getTotalPrice());
        //  Click “Confirm” – button
        checkoutPage.clickConfirmButton();
        // Verify your order is done successful and an order number is displayed.
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(7));
        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.orderConfirmationMessage));
        Assertions.assertTrue(checkoutPage.orderConfirmationMessage.isDisplayed() && checkoutPage.isOrderNumberDisplayed());
    }


}

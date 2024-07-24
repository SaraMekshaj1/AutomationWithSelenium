package org.example.Selenium.pages;

import org.example.Selenium.utils.Driver;
import org.example.Selenium.utils.GlobalConfigs;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage{

    @FindBy(css = "a.ico-login")
    public WebElement Login;

    @FindBy(css = ".button-1.login-button")
    public WebElement loginButton;


    @FindBy(css = ".topic-block-title>h2")
    public WebElement successLoginMessage;

    @FindBy(className = "email")
    public WebElement email;

    @FindBy(className = "password")
    public WebElement passwordInput;

    @FindBy(xpath = "//div[@class='message-error validation-summary-errors']")
    public WebElement errorMessage;

    public void login(String Email, String password)
    {
        email.sendKeys(Email);
        passwordInput.sendKeys(password);

    }

    public void navigateToHomePage() {
        Driver.getDriver().get(GlobalConfigs.URL);
    }

    public void clickLoginButton(){
        Login.click();
    }

    public void submit(){
        loginButton.click();
    }

}

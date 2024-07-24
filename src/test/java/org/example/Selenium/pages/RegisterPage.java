package org.example.Selenium.pages;

import org.example.Selenium.utils.Driver;
import org.example.Selenium.utils.GlobalConfigs;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends BasePage{
    @FindBy(linkText=("Register"))
    public WebElement Register;

    @FindBy(id = "gender-female")
    public WebElement female;

    @FindBy(id = "FirstName")
    public WebElement inputFieldName;

    @FindBy(id = "LastName")
    public WebElement inputFieldSurname;

    @FindBy(name = "DateOfBirthDay")
    public WebElement DayDropDown;

    @FindBy(name = "DateOfBirthMonth")
    public WebElement dateofBirthMonthDropdown;

    @FindBy(name = "DateOfBirthYear")
    public WebElement yearDropdown;

    @FindBy(id = "Email")
    public WebElement emailInput;

    @FindBy(id = "Company")
    public WebElement companyInput;

    @FindBy(id = "Password")
    public WebElement passwordReg;

    @FindBy(id = "ConfirmPassword")
    public WebElement confirmPasswordReg;

    @FindBy(id = "register-button")
    public WebElement registerButton;

    @FindBy(className = "result")
    WebElement registrationMessage;

    public void register(String firstName, String lastName, String Email, String Company, String password, String Day, String Month, String Year)
    {
        female.click();
        inputFieldName.sendKeys(firstName);
        inputFieldSurname.sendKeys(lastName);

        Select select = new Select(DayDropDown);
        select.selectByVisibleText(Day);

        Select DateMonth = new Select(dateofBirthMonthDropdown);
        DateMonth.selectByVisibleText(Month);

        Select yearSelect = new Select(yearDropdown);
        yearSelect.selectByValue(Year);


        emailInput.sendKeys(Email);
        companyInput.sendKeys(Company);
        passwordReg.sendKeys(password);
        confirmPasswordReg.sendKeys(password);

        registerButton.click();

    }

    public String getEmail(){

        return emailInput.getText();
    }

    public String getPassword(){

        return passwordReg.getText();
    }

    public void navigateToHomePage() {
        Driver.getDriver().get(GlobalConfigs.URL);
    }

    public void clickRegisterButton() {
        Register.click();
    }

    public String getRegistrationMessage() {
        return registrationMessage.getText();
    }


}

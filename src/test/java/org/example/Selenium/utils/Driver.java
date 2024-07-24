package org.example.Selenium.utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.Selenium.enums.BrowserType;
import org.example.Selenium.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class Driver {


    private static WebDriver driver = initDriver(BrowserType.CHROME);

    public static WebDriver getDriver() {
        return driver;
    }

    private static WebDriver initDriver(BrowserType type) {

        switch (type) {
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "C:\\Users\\Dell\\Desktop\\geckodriver.exe");
                FirefoxOptions options = new FirefoxOptions();
                options.setBinary("C:\\Program Files\\Mozilla Firefox\\Firefox.exe");
                driver = new FirefoxDriver(options);
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions optionsChrome = new ChromeOptions();
                optionsChrome.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(optionsChrome);
                break;
        }

        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public static void login() {
        LoginPage loginPage = new LoginPage();

        loginPage.navigateToHomePage();
        loginPage.clickLoginButton();

        loginPage.login("saramekshaj19@gmail.com", "111222333");
        loginPage.submit();
    }

    public static String removeParanthesis(String text) {
        text = text.replace("(", "");
        text = text.replace(")", "");
        return text;
    }
}
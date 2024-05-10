package tests;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import config.AuthConfig;
import config.DriverConfig;
import helper.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.AuthenticationPage;
import pages.RegistrationPage;


import java.util.Map;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());
    Faker faker = new Faker();
    String wrongEmail = faker.internet().emailAddress();
    String wrongPassword = faker.internet().password();
    String wrongConfirmPassword = faker.internet().password();
    AuthenticationPage authPage = new AuthenticationPage();
    RegistrationPage reg = new RegistrationPage();
    @BeforeAll
    static void setup() {
        DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class, System.getProperties());
        Configuration.baseUrl = "https://barfin.network";
        RestAssured.baseURI = "https://api.barfin.network/api";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
        Configuration.browser = driverConfig.browserName();
        Configuration.browserVersion = driverConfig.browserVersion();
        Configuration.browserSize = driverConfig.browserSize();
        Configuration.remote = driverConfig.browserRemoteUrl();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());


    }

    @AfterEach
    void tearDown() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            Attach.addVideo();
            clearBrowserCookies();
            closeWebDriver();
        }


    }
}
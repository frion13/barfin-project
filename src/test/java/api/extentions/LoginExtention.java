package api.extentions;


import api.models.CookiesModel;
import api.models.LoginModel;

import static api.spec.AuthSpec.requestSpec;
import static api.spec.AuthSpec.responseSpec;

import config.AuthConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

import org.openqa.selenium.Cookie;

public class LoginExtention extends TestBase implements BeforeEachCallback {
    AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());

    @Override
    public void beforeEach(ExtensionContext context) {
        basePath = "/auth/sign-in";
        LoginModel login = new LoginModel();
        login.setEmail(config.email());
        login.setPassword(config.password());
        CookiesModel cookie = given(requestSpec)
                .body(login)
                .when()
                .post()
                .then()
                .spec(responseSpec)
                .extract().
                as(CookiesModel.class);
        String token = String.format("{\"token\":\"%s\",\"permissions\":[\"detector_owner\"]}", cookie.getAccessToken());
        System.out.println(token);


        open("/favicon.ico");

        getWebDriver().manage().addCookie(
                new Cookie("AUTH_CRED", token));

    }
}

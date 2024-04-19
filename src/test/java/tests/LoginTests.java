package tests;

import com.github.javafaker.Faker;
import config.AuthConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.AuthenticationPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LoginTests extends TestBase {
    Faker faker = new Faker();
    String wrongEmail = faker.internet().emailAddress();
    String wrongPassword = faker.internet().password();
    AuthenticationPage authPage = new AuthenticationPage();
    AuthConfig auth = ConfigFactory.create(AuthConfig.class, System.getProperties());



    @Test
    @DisplayName("Тестирование Валидного Входа")
    void successLoginTest() {
        step("Открыть страницу", () ->
                authPage.openPage());
        step("Перейти на страницу входа.", () ->
                authPage.openLoginPage());
        step("Ввести верные логин пользователя и пароль", () ->
                authPage.setLoginAndPassword(auth.email(), auth.password())
        );
        step("Проверить, что в профиле пользователя отображается введенный при входе email", () ->
                authPage.checkSuccessLogin(auth.email()));

    }


    @Test
    @DisplayName("Тестирование Входа с Неверным Паролем")
    void loginWithWrongPasswordTest() {
        step("Перейти на страницу студии", () ->
                authPage.openStudio());
        step("Ввести верные имя пользователя и неверный пароль", () ->
                authPage.setLoginAndPassword(auth.email(), wrongPassword));
        step("Система выдает ошибку Invalid credentials", () ->
                authPage.checkAlert("Invalid credentials"));
    }


    @Test
    @DisplayName("Тестирование Входа с Неверным Именем Пользователя")
    void loginWithWrongEmailTest() {
        step("Перейти на страницу студии", () ->
                authPage.openStudio());
        step("Ввести неверный логин пользователя и верный пароль", () ->
                authPage.setLoginAndPassword(wrongEmail, auth.password()));
        step("Система выдает ошибку Email or password is incorrect", () ->
                authPage.checkAlert("Email or password is incorrect"));
    }

    @Test
    @DisplayName("Тестирование Входа с пустыми полями ввода")
    void loginWithEmtyFieldsTest() {
        step("Перейти на страницу студии", () ->
                authPage.openStudio());
        step("Ввести неверный логин пользователя и верный пароль", () ->
                authPage.setLoginAndPassword("", ""));
        step("Система выдает ошибку Необходимо заполнить все поля", () ->
                authPage.checkerrorHint());
    }

    @Test
    @DisplayName("Тестирование Входа с пустыми полями ввода")
    void forgotPasswordForRegisteredUserTest() {
        step("Перейти на страницу студии", () ->
                authPage.openStudio());
        $("#barfin-link-forgot-password").click();
        $("#email").setValue("testcartis@gmail.com");





//        step("Ввести неверный логин пользователя и верный пароль", () ->
//                authPage.setLoginAndPassword("", ""));
//        step("Система выдает ошибку Необходимо заполнить все поля", () ->
//                authPage.checkerrorHint());
    }





}

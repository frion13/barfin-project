package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class LoginTests extends TestBase {
    @Test
    @DisplayName("Тестирование валидного входа")
    void SuccessLoginTest() {
        step("Открыть страницу", () ->
                authPage.openPage());
        step("Перейти на страницу входа.", () ->
                authPage.openLoginPage());
        step("Ввести верные логин пользователя и пароль", () ->
                authPage.setLoginAndPassword(config.email(), config.password())
        );
        step("Проверить, что в профиле пользователя отображается введенный при входе email", () ->
                authPage.checkSuccessLogin(config.email()));

    }


    @Test
    @DisplayName("Тестирование входа с неверным паролем")
    void loginWithWrongPasswordTest() {
        step("Перейти на страницу студии", () ->
                authPage.openStudio());
        step("Ввести верные имя пользователя и неверный пароль", () ->
                authPage.setLoginAndPassword(config.email(), wrongPassword));
        step("Система выдает ошибку Invalid credentials", () ->
                authPage.checkAlert("Invalid credentials"));
    }


    @Test
    @DisplayName("Тестирование входа с неверным именем пользователя")
    void loginWithWrongEmailTest() {
        step("Перейти на страницу студии", () ->
                authPage.openStudio());
        step("Ввести неверный логин пользователя и верный пароль", () ->
                authPage.setLoginAndPassword(wrongEmail, config.password()));
        step("Система выдает ошибку Email or password is incorrect", () ->
                authPage.checkAlert("Email or password is incorrect"));
    }

    @Test
    @DisplayName("тестирование входа с пустыми полями ввода")
    void loginWithEmptyFieldsTest() {
        step("Перейти на страницу студии", () ->
                authPage.openStudio());
        step("Ввести неверный логин пользователя и верный пароль", () ->
                authPage.setLoginAndPassword("", ""));
        step("Система выдает ошибку Необходимо заполнить все поля", () ->
                authPage.checkErrorHint());
    }

    @Test
    @DisplayName("Восстановление пароля с зарегистрированного пользователя")
    void forgotPasswordForRegisteredUserTest() {
        step("Перейти на страницу студии", () ->
                authPage.openStudio());
        step("Нажать на кнопку восстановления пароля", () ->
                authPage.forgotPasswordClick());
        step("Ввести емаил зарегистрированного пользователя", () ->
                authPage.setEmail(config.email()));
        step("Проверить алерт об успешном отправлении емэила на почту", () ->
                authPage.checkSuccessAlert());
    }

    @Test
    @DisplayName("Восстановление пароля для незарегистрированного пользователя")
    void forgotPasswordForNotRegisteredUserTest() {
        step("Перейти на страницу студии", () ->
                authPage.openStudio());
        step("Нажать на кнопку восстановления пароля", () ->
                authPage.forgotPasswordClick());
        step("Ввести емаил незарегистрированного пользователя", () ->
                authPage.setEmail(config.registrationEmail()));
        step("Проверить алерт об ошибке", () ->
                authPage.checkAlert("There is no such user registered in the system"));
    }


}

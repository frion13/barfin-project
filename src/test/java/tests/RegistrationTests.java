package tests;

import api.extentions.WithDeleteUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class RegistrationTests extends TestBase {
    @WithDeleteUser
    @Test
    @DisplayName("Регистрация с валидными данными")
    void successRegistrationTest() {
        step("Открыть главную страницу", () ->
                authPage.openPage());
        step("Нажать кнопку 'Войти'", () ->
                authPage.openLoginPage());
        step("Нажать на кнопку регистрации", () ->
                reg.openRegistrationForm());
        step("Ввести верные логин пользователя и пароль", () ->
                reg.setRegistrationLoginAndPasswords(config.registrationEmail(), config.password(), config.password())
        );
        step("Проверить уведомление об успешной регистрации", () ->
                reg.checkSuccessRegistration());

    }


    @Test
    @DisplayName("Регистрация с некорректным подтверждением пароля")
    void registrationWithWrongConfirmPasswordTest() {
        step("Открыть страницу регистрации", () ->
                reg.openPage());
        step("Ввести верные логин пользователя и пароль, ввести неправильно повторный пароль", () ->
                reg.setRegistrationLoginAndPasswords(config.registrationEmail(), config.password(), wrongConfirmPassword)
        );
        step("Проверить, что появляется сообщение об ошибке, указывающее на некорректный формат email.",
                () -> authPage.checkAlert("Passwords do not match"));
    }

    @Test
    @DisplayName("Регистрация с некорректным форматом email")
    void registrationWithWrongFormatEmailTest() {
        step("Открыть страницу регистрации", () ->
                reg.openPage());
        step("Ввести email с неправильным форматом, ввести верные пароли", () ->
                reg.setRegistrationLoginAndPasswords(wrongEmail, config.password(), wrongConfirmPassword)
        );
        step("Проверить, что появляется сообщение об ошибке, указывающее на некорректный формат email.",
                () -> authPage.checkErrorHint());
    }

    @Test
    @DisplayName("Регистрация с уже зарегистрированным email")
    void registrationWithExistUserTest() {
        step("Открыть страницу регистрации", () ->
                reg.openPage());
        step("Ввести верные логин пользователя и пароль", () ->
                reg.setRegistrationLoginAndPasswords(config.email(), config.password(), config.password())
        );
        step("Проверить, что появляется сообщение об ошибке, указывающее на уже существующий email",
                () -> authPage.checkAlert("Email already in use"));
    }


}












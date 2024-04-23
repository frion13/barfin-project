package tests;

import api.extentions.WithDeleteUser;
import com.github.javafaker.Faker;
import config.AuthConfig;
import io.qameta.allure.Feature;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.AuthenticationPage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@Feature("Registration")
public class RegistrationTests extends TestBase {
    Faker faker = new Faker();
    String invalidEmail = faker.internet().domainWord() + "@" + faker.internet().domainWord();
    String wrongCobfirmPassword = faker.internet().password();
    AuthenticationPage authPage = new AuthenticationPage();
    AuthConfig auth = ConfigFactory.create(AuthConfig.class, System.getProperties());

    @WithDeleteUser
    @Test
    @DisplayName("Регистрация с валидными данными")
    void successRegistrationTest() {
        step("Открыть главную страницу", () ->
                authPage.openPage());
        step("Нажать кнопку 'Войти'", () ->
                authPage.openLoginPage());
        step("Нажать на кнопку регистрации", () ->
                authPage.openRegistrationForm());
        step("Ввести верные логин пользователя и пароль", () ->
                authPage.setRegistrationLoginAndPasswords(auth.registrationEmail(), auth.password(), auth.password())
        );
        step("Проверить уведомление об успешной регистрации", () ->
                authPage.checkSuccessRegistratiom());

    }


    @Test
    @DisplayName("Регистрация с некорректным подтверждением пароля")
    void registrationWithWrongConfitmPasswordTest() {
        step("Открыть страницу регистрации", () ->
                authPage.openStudioRegister());
        step("Ввести верные логин пользователя и пароль, ввести неправильно повторный пароль", () ->
                authPage.setRegistrationLoginAndPasswords(auth.registrationEmail(), auth.password(), wrongCobfirmPassword)
        );
        step("Проверить, что появляется сообщение об ошибке, указывающее на некорректный формат email.",
                () -> authPage.checkAlert("Passwords do not match"));
    }

    @Test
    @DisplayName("Регистрация с некорректным форматом email")
    void registrationWithWrongFormatEmailTest() {
        step("Открыть страницу регистрации", () ->
                authPage.openStudioRegister());
        step("Ввести email с неправильным форматом, ввести верные пароли", () ->
                authPage.setRegistrationLoginAndPasswords(invalidEmail, auth.password(), wrongCobfirmPassword)
        );
        step("Проверить, что появляется сообщение об ошибке, указывающее на некорректный формат email.",
                () -> authPage.checkerrorHint());
    }

    @Test
    @DisplayName("Регистрация с уже зарегистрированным email")
    void registrationWithExistUserTest() {
        step("Открыть страницу регистрации", () ->
                authPage.openStudioRegister());
        step("Ввести верные логин пользователя и пароль", () ->
                authPage.setRegistrationLoginAndPasswords(auth.email(), auth.password(), auth.password())
        );
        step("Проверить, что появляется сообщение об ошибке, указывающее на уже существующий email",
                () -> authPage.checkAlert("Email already in use"));
    }


}












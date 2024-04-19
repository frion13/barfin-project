package tests;

import api.endpoints.SignIn;
import api.models.CookiesModel;

import com.github.javafaker.Faker;
import config.AuthConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class LoginWithApiTests extends TestBase {
    SignIn signIn = new SignIn();

    AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());
    Faker faker = new Faker();
    String invalidEmail = faker.internet().emailAddress();
    String invalodPassword = faker.internet().password();


    @Test
    @DisplayName("Тестирование Валидного Входа")
    void authSuccessApiTest() {
        CookiesModel response = step("Отправить запрос на авторизацию с логином и паролем", () ->
                signIn.successLogin(config.email(), config.password())
        );
        step("Проверить, что ответе пришел email переданный в запросе", () ->
                assertThat(response.getUser().getEmail()).isEqualTo(config.email()));
    }

    @Test
    @DisplayName("Тестирование Входа с Неверным Паролем")
    void authWithWrongPasswordApiTest() {
        CookiesModel response = step("Отправить запрос на авторизацию с логином и паролем", () ->
                signIn.loginWithError(config.email(), invalodPassword)
        );
        step("Проверить, что ответе пришел StatusCode 409", () ->
                assertThat(response.getStatusCode()).isEqualTo(409));
        step("Проверить, что ответе пришла ошибка Conflict", () ->
                assertThat(response.getError()).isEqualTo("Conflict"));
        step("Проверить, что ответе пришло сообщение Invalid credentials", () ->
                assertThat(response.getMessage()).isEqualTo("Invalid credentials"));
    }

    @Test
    @DisplayName("Тестирование Входа с Неверным логином")
    void authWithWrongEmailApiTest() {
        CookiesModel response = step("Отправить запрос на авторизацию с логином и паролем", () ->
                signIn.loginWithError(invalidEmail, config.password())
        );
        step("Проверить, что ответе пришел StatusCode 409", () ->
                assertThat(response.getStatusCode()).isEqualTo(409));
        step("Проверить, что ответе пришла ошибка Conflict", () ->
                assertThat(response.getError()).isEqualTo("Conflict"));
        step("Проверить, что ответе пришло сообщение Email or password is incorrect", () ->
                assertThat(response.getMessage()).isEqualTo("Email or password is incorrect"));
    }




}

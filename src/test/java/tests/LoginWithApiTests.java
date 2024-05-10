package tests;

import api.endpoints.SignIn;
import api.models.CookiesModel;

import api.models.ErrorResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;


public class LoginWithApiTests extends TestBase {
    @Test
    @DisplayName("Тестирование валидного входа")
    void authSuccessApiTest() {
        SignIn signIn = new SignIn(config.email(), config.password());
        CookiesModel response = step("Отправить запрос на авторизацию с логином и паролем", () ->
                signIn.signIn(signIn)
                        .statusCode(200)
                        .body(matchesJsonSchemaInClasspath("schemas/barfin-signin-schema.json"))
                        .extract().as(CookiesModel.class)
        );
        step("Проверить, что ответе пришел email переданный в запросе", () ->
                assertThat(response.getUser().getEmail()).isEqualTo(config.email()));

    }

    @Test
    @DisplayName("Тестирование входа с веверным паролем")
    void authWithWrongPasswordApiTest() {
        SignIn signIn = new SignIn(config.email(), wrongPassword);
        ErrorResponseModel response = step("Отправить запрос на авторизацию с логином и паролем", () ->
                signIn.signIn(signIn)
                        .statusCode(409)
                        .body(matchesJsonSchemaInClasspath("schemas/barfin-error-schema.json"))
                        .extract().as(ErrorResponseModel.class)
        );
        step("Проверить, что ответе пришла ошибка Conflict", () ->
                assertThat(response.getError()).isEqualTo("Conflict"));
        step("Проверить, что ответе пришло сообщение Invalid credentials", () ->
                assertThat(response.getMessage()).contains("Invalid credentials"));
    }

    @Test
    @DisplayName("Тестирование входа с неверным логином")
    void authWithWrongEmailApiTest() {
        SignIn signIn = new SignIn(wrongEmail, config.password());
        ErrorResponseModel response = step("Отправить запрос на авторизацию с логином и паролем", () ->
                signIn.signIn(signIn).statusCode(409).extract().as(ErrorResponseModel.class)
        );
        step("Проверить, что ответе пришла ошибка Conflict", () ->
                assertThat(response.getError()).isEqualTo("Conflict"));
        step("Проверить, что ответе пришло сообщение Email or password is incorrect", () ->
                assertThat(response.getMessage()).contains("Email or password is incorrect"));
    }


}

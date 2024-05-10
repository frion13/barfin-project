package tests;

import api.endpoints.Signup;
import api.extentions.WithDeleteUser;
import api.models.ErrorResponseModel;
import api.models.RegistrationResponseModel;
import org.junit.jupiter.api.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;


public class RegistrationWithApiTests extends TestBase {
    @WithDeleteUser
    @Test
    @DisplayName("Регистрация с валидными данными")
    void authSuccessApiTest() {
        Signup signup = new Signup(config.registrationEmail(), config.password(), config.password());
        RegistrationResponseModel response =
                step("Отправить запрос на регистрацию c валидными данными", () ->
                        signup.signUp(signup)
                                .statusCode(200)
                                .body(matchesJsonSchemaInClasspath("schemas/barfin-signup-schema.json"))
                                .extract().as(RegistrationResponseModel.class)
                );
        step("Проверить, что ответе пришел email переданный в запросе", () ->
                assertThat(response.getMessage()).isEqualTo("Registration successful"));

    }

    @Test
    @DisplayName("Регистрация с некорректным подтверждением паролем")
    void registrationWithWrongConfirmPasswordApiTest() {
        Signup signup = new Signup(config.registrationEmail(), config.password(), wrongPassword);
        ErrorResponseModel response = step("Отправить запрос на регистрацию с верными логином и паролем, и неверным повторным паролем", () ->
                signup.signUp(signup).statusCode(400)
                        .body(matchesJsonSchemaInClasspath("schemas/barfin-error-schema.json"))
                        .extract().as(ErrorResponseModel.class)
        );
        step("Проверить, что ответе сообщение Passwords do not match", () ->
                assertThat(response.getMessage()).contains("Passwords do not match"));
        step("Проверить, что пришла ошибка Bad Request", () ->
                assertThat(response.getError()).isEqualTo("Bad Request"));

    }

    @Test
    @DisplayName("Регистрация с некорректным форматом email")
    void registrationWithWrongFormatEmailApiTest() {
        Signup signup = new Signup(wrongEmail, config.password(), config.password());
        ErrorResponseModel response = step("Отправить запрос на регистрацию email с неправильным форматом и верными паролями", () ->
                signup.signUp(signup)
                        .statusCode(400)
                        .body(matchesJsonSchemaInClasspath("schemas/barfin-error-schema.json"))
                        .extract().as(ErrorResponseModel.class)
        );
        step("Проверить, что ответе сообщение email must be an email", () ->
                assertThat(response.getMessage()).contains("email must be an email"));
        step("Проверить, что пришла ошибка Bad Request", () ->
                assertThat(response.getError()).isEqualTo("Bad Request"));

    }


}

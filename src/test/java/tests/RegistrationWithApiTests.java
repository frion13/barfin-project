package tests;

import api.endpoints.Signup;
import api.extentions.WithDeleteUser;
import api.models.ErrorResponseModel;
import api.models.RegistrationResponseModel;
import com.github.javafaker.Faker;
import config.AuthConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class RegistrationWithApiTests extends TestBase {
    Signup signup = new Signup();

    AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());
    Faker faker = new Faker();
    String invalidEmail = faker.internet().domainWord() + "@" + faker.internet().domainWord();
    String invalodPassword = faker.internet().password();

    @WithDeleteUser
    @Test
    @DisplayName("Регистрация с валидными данными")
    void authSuccessApiTest() {
        RegistrationResponseModel response =
                step("Отправить запрос на регистрацию c валидными данными", () ->
                signup.successRegistration(config.registrationEmail(), config.password(), config.password())
        );
        step("Проверить, что ответе пришел email переданный в запросе", () ->
                assertThat(response.getMessage()).isEqualTo("Registration successful"));

    }

    @Test
    @DisplayName("Регистрация с некорректным подтверждением паролем")
    void registrationWithWrongConfitmPasswordApiTest() {
        ErrorResponseModel response = step("Отправить запрос на регистрацию с верными логином и паролем, и неверным повторным паролем", () ->
                signup.registrationWithWrongData(config.registrationEmail(), config.password(), invalodPassword)
        );
        step("Проверить статускод", () ->
                assertThat(response.getStatusCode()).isEqualTo(400));
        step("Проверить, что ответе сообщение Passwords do not match", () ->
                assertThat(response.getMessage()).contains("Passwords do not match"));
        step("Проверить, что пришла ошибка Bad Request", () ->
                assertThat(response.getError()).isEqualTo("Bad Request"));

    }

    @Test
    @DisplayName("Регистрация с некорректным подтверждением паролем")
    void registrationWithWrongFormatEmailApiTest() {
        ErrorResponseModel response = step("Отправить запрос на регистрацию email с неправильным форматом и верными паролями", () ->
                signup.registrationWithWrongData(invalidEmail, config.password(), config.password())
        );
        step("Проверить статускод 400", () ->
                assertThat(response.getStatusCode()).isEqualTo(400));
        step("Проверить, что ответе сообщение email must be an email", () ->
                assertThat(response.getMessage()).contains("email must be an email"));
        step("Проверить, что пришла ошибка Bad Request", () ->
                assertThat(response.getError()).isEqualTo("Bad Request"));

    }


}

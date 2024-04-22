package api.endpoints;

import api.models.*;

import static api.spec.AuthSpec.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Signup {
    private static final String ENDPOINT = "/auth/sign-up";

    private <T> T signUp(RegistrationModel registration, String schemaPath, Class<T> responseType) {
        return given(requestSpec)
                .body(registration)
                .when()
                .post(ENDPOINT)
                .then()
                .spec(responseSpec)
                .body(matchesJsonSchemaInClasspath(schemaPath))
                .extract().as(responseType);
    }

    private RegistrationModel createRegistrationModel(String email, String password, String confirmPassword) {
        RegistrationModel registration = new RegistrationModel();
        registration.setEmail(email);
        registration.setPassword(password);
        registration.setConfirmPassword(confirmPassword);
        return registration;
    }

    public RegistrationResponseModel successRegistration(String email, String password, String confirmPassword) {
        return signUp(createRegistrationModel(email, password, confirmPassword), "schemas/barfin-signup-schema.json",
                RegistrationResponseModel.class);
    }

    public ErrorResponseModel registrationWithWrongData(String email, String password, String confirmPassword) {
        return signUp(createRegistrationModel(email, password, confirmPassword), "schemas/barfin-error-schema.json", ErrorResponseModel.class);
    }
}
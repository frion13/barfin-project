package api.endpoints;

import api.models.*;

import static api.spec.AuthSpec.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Signup {
    private final String endpoint = "/auth/sign-up";

    private RegistrationResponseModel signUp(RegistrationModel registration, String schemaPath) {
        return given(requestSpec)
                .body(registration)
                .when()
                .post(endpoint)
                .then()
                .spec(responseSpec)
                .body(matchesJsonSchemaInClasspath(schemaPath))
                .extract().as(RegistrationResponseModel.class);
    }

    private RegistrationModel createRegistrationModel(String email, String password, String confirmPassword) {
        RegistrationModel registration = new RegistrationModel();
        registration.setEmail(email);
        registration.setPassword(password);
        registration.setConfirmPassword(confirmPassword);
        return registration;
    }

    public RegistrationResponseModel successRegistration(String email, String password, String confirmPassword) {
        return signUp(createRegistrationModel(email, password, confirmPassword), "schemas/barfin-signup-schema.json");
    }

    public RegistrationResponseModel registrationWithWrongData(String email, String password, String confirmPassword) {
        return signUp(createRegistrationModel(email, password, confirmPassword), "schemas/barfin-signup-error-schema.json");
    }
}
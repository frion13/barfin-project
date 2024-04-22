package api.endpoints;

import api.models.*;

import static api.spec.AuthSpec.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SignIn {

    private static final String ENDPOINT = "/auth/sign-in";

    private <T> T signIn(LoginModel login, String schemaPath, Class<T> responseType) {
        return given(requestSpec)
                .body(login)
                .when()
                .post(ENDPOINT)
                .then()
                .spec(responseSpec)
                .body(matchesJsonSchemaInClasspath(schemaPath))
                .extract().as(responseType);
    }


    private LoginModel createLoginModel(String email, String password) {
        LoginModel login = new LoginModel();
        login.setEmail(email);
        login.setPassword(password);
        return login;
    }


    public CookiesModel successLogin(String email, String password) {
        return signIn(createLoginModel(email, password), "schemas/barfin-signin-schema.json", CookiesModel.class);
    }

    public ErrorResponseModel loginWithError(String email, String password) {
        return signIn(createLoginModel(email, password), "schemas/barfin-error-schema.json", ErrorResponseModel.class);
    }
}

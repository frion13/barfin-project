package api.endpoints;

import api.models.*;

import static api.spec.AuthSpec.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SignIn {

    private final String endpoint = "/auth/sign-in";

    private CookiesModel signIn(LoginModel login, String schemaPath) {
        return given(requestSpec)
                .body(login)
                .when()
                .post(endpoint)
                .then()
                .spec(responseSpec)
                .body(matchesJsonSchemaInClasspath(schemaPath))
                .extract().as(CookiesModel.class);
    }

    private LoginModel createLoginModel(String email, String password) {
        LoginModel login = new LoginModel();
        login.setEmail(email);
        login.setPassword(password);
        return login;
    }


    public CookiesModel successLogin(String email, String password) {
        LoginModel login = createLoginModel(email, password);
        return signIn(login, "schemas/barfin-signin-schema.json");
    }

    public CookiesModel loginWithError(String email, String password) {
        LoginModel login = createLoginModel(email, password);
        return signIn(login, "schemas/barfin-signin-error-schema.json");
    }
}

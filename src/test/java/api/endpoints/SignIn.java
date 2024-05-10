package api.endpoints;

import io.restassured.response.ValidatableResponse;

import static api.spec.AuthSpec.*;
import static io.restassured.RestAssured.given;

public class SignIn {
    private String email;
    private String password;


    private static final String ENDPOINT = "/auth/sign-in";

    public SignIn(String email, String password) {
        this.email = email;
        this.password = password;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ValidatableResponse signIn(SignIn signIn) {
        return given(requestSpec)
                .body(signIn)
                .when()
                .post(ENDPOINT)
                .then()
                .spec(loggingOnlySpec);
    }


}

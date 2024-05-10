package api.endpoints;

import io.restassured.response.ValidatableResponse;

import static api.spec.AuthSpec.*;
import static io.restassured.RestAssured.given;

public class Signup {
    private String email;
    private String password;
    private String confirmPassword;
    private static final String ENDPOINT = "/auth/sign-up";

    public Signup(String email, String password, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public ValidatableResponse signUp(Signup signup) {
        return given(requestSpec)
                .body(signup)
                .when()
                .post(ENDPOINT)
                .then()
                .spec(loggingOnlySpec);
    }
}
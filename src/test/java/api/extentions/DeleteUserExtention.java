package api.extentions;


import api.models.DeleteUserModel;
import api.models.LoginModel;
import api.models.CookiesModel;
import config.AuthConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import tests.TestBase;

import static api.spec.AuthSpec.requestSpec;
import static api.spec.AuthSpec.responseSpec;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

public class DeleteUserExtention extends TestBase implements AfterEachCallback {
    AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());

    @Override
    public void afterEach(ExtensionContext context) {
        DeleteUserModel user = new DeleteUserModel();
        user.setEmail(config.registrationEmail());
        given(requestSpec)
                .body(user)
                .when()
                .post("/users/delete-testuser")
                .then()
                .spec(responseSpec)
                .extract();

    }
}

package api.extentions;


import api.models.DeleteUserModel;
import config.AuthConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static api.spec.AuthSpec.*;
import static io.restassured.RestAssured.given;

public class DeleteUserExtension implements AfterEachCallback {
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
                .spec(loggingOnlySpec)
                .extract();

    }
}

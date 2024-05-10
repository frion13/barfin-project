package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CookiesModel {
    private User user;
    String accessToken, email;


    private String message, error;
    private int statusCode;

    @JsonProperty("AUTH_CRED")
    String auth;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class User {
        private int id;
        private String email;
    }


}
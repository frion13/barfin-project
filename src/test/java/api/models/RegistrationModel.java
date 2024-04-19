package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationModel {
    private String email;

    @JsonSetter("password1")
    private String password;

    @JsonSetter("password2")
    private String confirmPassword;


}
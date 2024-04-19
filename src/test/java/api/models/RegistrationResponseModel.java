package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationResponseModel {
    private String id, message;
    private int statusCode;
    private String error;

}
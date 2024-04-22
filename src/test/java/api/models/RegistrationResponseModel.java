package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationResponseModel {
    private String id, message;



}
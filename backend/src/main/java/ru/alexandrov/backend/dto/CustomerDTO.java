package ru.alexandrov.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;

@Getter
@Setter
public class CustomerDTO {
    private int id;

    @Max(30)
    private String name;

    @Max(30)
    private String surname;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String role;
}

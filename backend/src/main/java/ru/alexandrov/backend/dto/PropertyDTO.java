package ru.alexandrov.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDTO {
    private int id;

    private String value;

    @JsonIgnore
    private CharacteristicDTO characteristic;
}

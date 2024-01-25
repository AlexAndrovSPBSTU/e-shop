package ru.alexandrov.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharacteristicDTO {
    private int id;

    private String name;

    private Boolean isRange;

    private Float from;

    private Float to;

    private List<PropertyDTO> properties;

    public List<PropertyDTO> getProperties() {
        return isRange ? Collections.emptyList() : properties;
    }
}

package ru.alexandrov.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CategoryDTO {
    private int id;

    private String name;

    private Boolean isDiverged;

    private List<CategoryDTO> children;

    private int productAmountOfCategory;

}

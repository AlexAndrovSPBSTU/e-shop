package ru.alexandrov.backend.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import ru.alexandrov.backend.util.PropertyListSerializer;

import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer id;

    private String name;

    private Double price;

    private Integer amount;

    private String description;

    private Integer discount;

    @PositiveOrZero
    private Double rating;

    private String category;

    @JsonSerialize(using = PropertyListSerializer.class)
    private List<PropertyDTO> properties;

    private List<PhotoDTO> photos;

    private List<CommentDTO> comments;

    private String amount_status;
}

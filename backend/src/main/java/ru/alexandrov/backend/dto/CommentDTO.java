package ru.alexandrov.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CommentDTO {
    private int id;
    @Min(0)
    @Max(5)
    private Integer rating;

    private String note;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date date;

    private CustomerDTO customer;

    private List<PhotoDTO> photos;
}

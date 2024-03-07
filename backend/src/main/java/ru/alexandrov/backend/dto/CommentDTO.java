package ru.alexandrov.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CommentDTO {
    private int id;

    private Integer rating;

    private String note;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date date;

    private CustomerDTO customer;

    private List<PhotoDTO> photos;
}

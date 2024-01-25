package ru.alexandrov.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Photos {
    List<PhotoDTO> photos;
}

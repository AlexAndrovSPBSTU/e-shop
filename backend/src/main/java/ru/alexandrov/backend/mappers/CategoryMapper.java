package ru.alexandrov.backend.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alexandrov.backend.dto.CategoryDTO;
import ru.alexandrov.backend.models.Category;

import java.util.Objects;

@Component
public class CategoryMapper {
    @Autowired
    private ModelMapper mapper;


    public Category toEntity(CategoryDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Category.class);
    }


    public CategoryDTO toDto(Category entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, CategoryDTO.class);
    }
}

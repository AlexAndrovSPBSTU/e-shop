package ru.alexandrov.backend.services;

import org.springframework.stereotype.Component;
import ru.alexandrov.backend.dto.CategoryDTO;
import ru.alexandrov.backend.dto.CharacteristicDTO;
import ru.alexandrov.backend.dto.ProductDTO;

import java.util.List;
import java.util.Set;

@Component
public interface CategoryService {
    List<CategoryDTO> getRootCategories();

    List<ProductDTO> getCategoryProducts(int id, Integer page, Integer order, String price, String[] filter);

    Set<CharacteristicDTO> getCharacteristicsByCategoryId(int id, Boolean isRange);

    void save(CategoryDTO categoryDTO, int parentId);

    void deleteParentChildRelation(int child_id, int parent_id);

    void delete(int id);

    void insert(int child_id, int parent);

    void rename(int categoryId, String newName);
}

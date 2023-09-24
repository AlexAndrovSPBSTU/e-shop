package ru.alexandrov.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.models.Characteristic;
import ru.alexandrov.backend.repositories.CategoryRepository;
import ru.alexandrov.backend.models.Category;
import ru.alexandrov.backend.models.Product;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getRootCategories() {
        return categoryRepository.getRootCategories();
    }

    public List<Product> getProductsByCategoryId(int id) {
        return categoryRepository.findById(id).get().getProducts();
    }

    public List<Characteristic> getCharacteristicsByCategoryId(int id) {
        return categoryRepository.findById(id).get().getCharacteristics();
    }
}

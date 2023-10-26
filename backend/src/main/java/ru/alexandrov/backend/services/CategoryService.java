package ru.alexandrov.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.models.Characteristic;
import ru.alexandrov.backend.repositories.CategoryRepository;
import ru.alexandrov.backend.models.Category;
import ru.alexandrov.backend.models.Product;

import javax.transaction.Transactional;
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

    @Transactional
    public void save(Category category, int parentId) {
        category = categoryRepository.save(category);
        categoryRepository.insert(category.getId(), parentId);
    }

    public boolean exists(int id){
        return categoryRepository.findById(id).isPresent();
    }

    @Transactional
    public void deleteParentChildRelation(int child_id, int parent_id) {
        categoryRepository.deleteParentChildRelation(child_id, parent_id);
    }


    @Transactional
    public void delete(int child_id) {
        categoryRepository.deleteById(child_id);
    }

    @Transactional
    public void insert(int child_id, int parent) {
        categoryRepository.insert(child_id, parent);
    }

    @Transactional
    public void rename(int categoryId, String newName) {
        categoryRepository.rename(categoryId, newName);
    }
}

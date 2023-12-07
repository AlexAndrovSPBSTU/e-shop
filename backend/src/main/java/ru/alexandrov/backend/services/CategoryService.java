package ru.alexandrov.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.constants.ProjectConstants;
import ru.alexandrov.backend.models.Characteristic;
import ru.alexandrov.backend.repositories.CategoryRepository;
import ru.alexandrov.backend.models.Category;
import ru.alexandrov.backend.models.Product;
import ru.alexandrov.backend.repositories.ProductRepository;
import ru.alexandrov.backend.util.ProductSorting;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<Category> getRootCategories() {
        return categoryRepository.getRootCategories();
    }


    public List<Product> getCategoryProducts(int id, int page, Integer order, Double from, Double to) {
        Category category = categoryRepository.findById(id).get();
        order = order == null ? 4 : order;
        ProductSorting productSorting = null;
        switch (order) {
            case 1:
                productSorting = ProductSorting.BY_PRICE_ASCENDING;
                break;
            case 2:
                productSorting = ProductSorting.BY_PRICE_DESCENDING;
                break;
            case 3:
                productSorting = ProductSorting.BY_DISCOUNT;
                break;
            case 4:
                productSorting = ProductSorting.BY_RATING;
                break;
        }
        PageRequest pageRequest = PageRequest.of(page - 1, ProjectConstants.PAGE_SIZE, Sort.by(productSorting.getDirection(), productSorting.getProperty()));
        List<Category> categories = category.getChildren();
        if (from != null && to != null) {
            return productRepository.findAllByCategoryInAndPriceBetween(pageRequest, categories, from, to).getContent();
        }
        return productRepository.findAllByCategoryIn(pageRequest, categories).getContent();
    }

    public List<Characteristic> getCharacteristicsByCategoryId(int id) {
        return categoryRepository.findById(id).get().getCharacteristics();
    }

    @Transactional
    public void save(Category category, int parentId) {
        category = categoryRepository.save(category);
        categoryRepository.insert(category.getId(), parentId);
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

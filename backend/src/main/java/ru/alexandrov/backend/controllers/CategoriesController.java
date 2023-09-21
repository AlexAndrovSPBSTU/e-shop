package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexandrov.backend.models.Product;
import ru.alexandrov.backend.services.CategoryService;
import ru.alexandrov.backend.models.Category;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    private final CategoryService categoryService;

    @Autowired
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getRootCategories();
    }

    @GetMapping("/{category_id}/products")
    public List<Product> getProductsByCategory(@PathVariable("category_id") int id) {
        return categoryService.getProductsByCategoryId(id);
    }

}

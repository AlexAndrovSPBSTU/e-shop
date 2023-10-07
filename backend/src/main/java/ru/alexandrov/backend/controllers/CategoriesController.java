package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexandrov.backend.models.Characteristic;
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

    @GetMapping("/{category_id}/characteristics")
    public List<Characteristic> getCharacteristicsByCategory(@PathVariable("category_id") int id) {
        return categoryService.getCharacteristicsByCategoryId(id);
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> createCategory(@RequestBody Category category,
                                                     @RequestParam int parentId) {
        categoryService.save(category, parentId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{category_id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("category_id") int id,
                                                     @RequestParam int parentId) {
        categoryService.delete(id, parentId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/insert/{category_id}")
    public ResponseEntity<HttpStatus> insert(@PathVariable("category_id") int id,
                                             @RequestParam Integer parentId) {
        categoryService.insert(id, parentId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/rename/{category_id}")
    public ResponseEntity<HttpStatus> rename(@PathVariable("category_id") int category_id,
                                             @RequestParam("newName") String newName) {
        categoryService.rename(category_id, newName);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

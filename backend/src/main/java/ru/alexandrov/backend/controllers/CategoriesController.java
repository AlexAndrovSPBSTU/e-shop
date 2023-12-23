package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<?> getProductsByCategory(@PathVariable("category_id") int id,
                                                   @RequestParam(required = false) Integer page,
                                                   @RequestParam(required = false) Integer order,
                                                   @RequestParam(required = false) String price,
                                                   @RequestParam(required = false) String[] query) {
        page = (page == null) ? 1 : page;
        return ResponseEntity.ok(categoryService.getCategoryProducts(id, page, order, price, query));
    }

    @GetMapping("/{category_id}/characteristics")
    public ResponseEntity<?> getCharacteristicsByCategory(@PathVariable("category_id") int id,
                                                          @RequestParam(required = false) Boolean isRange) {
        return ResponseEntity.ok(categoryService.getCharacteristicsByCategoryId(id, isRange));
    }

    @PostMapping("/new")
    public ResponseEntity<?> createCategory(@RequestBody Category category,
                                            @RequestParam int parentId) {
        categoryService.save(category, parentId);
        return ResponseEntity.ok("Category has been created");
    }

    /**
     * Deletes a category by id. If there is parentId, deletes
     * parent child relation between categories.
     *
     * @param id       - id of category for deleting
     * @param parentId - parent's id
     * @return {@code 200} if the category was deleted, {@code 409} otherwise
     * //     * @see #authenticate(AuthenticationRequest)
     */
    @DeleteMapping("/{category_id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("category_id") int id,
                                            @RequestParam(required = false) Integer parentId) {
        if (null == parentId) {
            categoryService.delete(id);
            return ResponseEntity.ok("The category has been deleted");
        } else {
            categoryService.deleteParentChildRelation(id, parentId);
            return ResponseEntity.ok("The parent-child relation has been deleted");
        }
    }

    @PatchMapping("/insert/{category_id}")
    public ResponseEntity<?> insert(@PathVariable("category_id") Integer id,
                                    @RequestParam int parentId
    ) {
        categoryService.insert(id, parentId);
        return ResponseEntity.ok("Category has been inserted");
    }

    @PatchMapping("/rename/{category_id}")
    public ResponseEntity<?> rename(@PathVariable("category_id") int id,
                                    @RequestParam("newName") String newName) {
        categoryService.rename(id, newName);
        return ResponseEntity.ok("Category has been renamed");
    }
}

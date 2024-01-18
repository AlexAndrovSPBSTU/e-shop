package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexandrov.backend.dto.CategoryDTO;
import ru.alexandrov.backend.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    private final CategoryService categoryService;

    @Autowired
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    /**
     * Returns all categories.
     */
    @GetMapping
    public List<CategoryDTO> getCategories() {
        return categoryService.getRootCategories();
    }


    /**
     * Returns category's products.
     *
     * @param id    category's id
     * @param page  numeration starts with 1
     * @param order 1 - восходящая по цене 2 - нисходящая по цене 3 - по убыванию скидки 4 - по убыванию рейтинга(по умолчанию)
     * @param price price range
     * @param filter Consists of characteristicName and its properties, by which we find appropriate goods.
     * @return {@code 200} if ok, {@code 409} otherwise
     */
    @GetMapping("/{category_id}/products")
    public ResponseEntity<?> getProductsByCategory(@PathVariable("category_id") int id,
                                                   @RequestParam(required = false) Integer page,
                                                   @RequestParam(required = false) Integer order,
                                                   @RequestParam(required = false) String price,
                                                   @RequestParam(required = false) String[] filter) {
        return ResponseEntity.ok(categoryService.getCategoryProducts(id, page, order, price, filter));
    }

    /**
     * Returns category's characteristics. In case isRange is not passed method
     * returns all characteristics. If it is, only that compatible with isRange.
     *
     * @param id      category's id
     * @param isRange non-required param
     * @return {@code 200} if ok, {@code 409} otherwise
     */
    @GetMapping("/{category_id}/characteristics")
    public ResponseEntity<?> getCharacteristicsByCategory(@PathVariable("category_id") int id,
                                                          @RequestParam(required = false) Boolean isRange) {
        return ResponseEntity.ok(categoryService.getCharacteristicsByCategoryId(id, isRange));
    }

    /**
     * Creates a new category and assign to an existing one.
     *
     * @param category new category
     * @param parentId parent's id
     * @return {@code 200} if the category was created, {@code 409} otherwise
     */
    @PostMapping("/new")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO category,
                                            @RequestParam int parentId) {
        categoryService.save(category, parentId);
        return ResponseEntity.ok("Category has been created");
    }

    /**
     * Deletes a category by id. If there is also parentId as a param, deletes
     * parent child relation in categories.
     *
     * @param id       category's id
     * @param parentId parent's id
     * @return {@code 200} if the category was deleted, {@code 409} otherwise
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

    /**
     * Inserts a category into another one.
     *
     * @param id       category's id for inserting
     * @param parentId parent's id
     * @return {@code 200} if the category was inserted, {@code 409} otherwise
     */
    @PatchMapping("/insert/{category_id}")
    public ResponseEntity<?> insert(@PathVariable("category_id") Integer id,
                                    @RequestParam int parentId
    ) {
        categoryService.insert(id, parentId);
        return ResponseEntity.ok("Category has been inserted");
    }

    /**
     * Renames an existing category.
     *
     * @param id      category's id
     * @param newName new name
     * @return {@code 200} if the category was renamed, {@code 409} otherwise
     */
    @PatchMapping("/rename/{category_id}")
    public ResponseEntity<?> rename(@PathVariable("category_id") int id,
                                    @RequestParam("newName") String newName) {
        categoryService.rename(id, newName);
        return ResponseEntity.ok("Category has been renamed");
    }
}

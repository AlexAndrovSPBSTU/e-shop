package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexandrov.backend.models.Product;
import ru.alexandrov.backend.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }


    /**
     * Returns product by id
     *
     * @param id product's id
     * @return {@code 200} if ok, {@code 409} otherwise
     */
    @GetMapping("/{product_id}")
    public ResponseEntity<?> getProductById(@PathVariable("product_id") int id) {
        return ResponseEntity.ok(productService.getProductById(id).get());
    }

    /**
     * Assigns a property to a product.
     *
     * @param productId  product's id
     * @param propertyId property's id
     * @return {@code 200} if ok, {@code 409} otherwise
     */
    @PatchMapping("/{product_id}/addProperty")
    public ResponseEntity<?> setProductProperty(@PathVariable("product_id") int productId,
                                                @RequestParam int propertyId) {
        productService.assignProperty(productId, propertyId);
        return ResponseEntity.ok("Property has been assigned");
    }

    /**
     * Creates a new product and assigns to an existing category.
     *
     * @param product    new category
     * @param categoryId parent's id
     * @return {@code 200} if the category was created, {@code 409} otherwise
     */
    @PostMapping("/new")
    public ResponseEntity<?> createProduct(@RequestBody Product product,
                                           @RequestParam int categoryId) {
        productService.save(product, categoryId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Deletes a product by id.
     *
     * @param id product's id
     * @return {@code 200} if the product was deleted, {@code 409} otherwise
     */
    @DeleteMapping("/{product_id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("product_id") int id) {
        productService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Changes a product. Almost all params are not required, so we pass only those
     * we want to change in our product.
     *
     * @param id          product's id
     * @param name        new name
     * @param price       new price
     * @param amount      new amount
     * @param description new description
     * @param discount    new discount
     * @param categoryId  category's id to which assign
     * @return {@code 200} if the product was changed, {@code 409} otherwise
     */
    @PatchMapping("/change/{product_id}")
    public ResponseEntity<?> change(@PathVariable("product_id") int id,
                                    @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "price", required = false) Double price,
                                    @RequestParam(value = "amount", required = false) Integer amount,
                                    @RequestParam(value = "description", required = false) String description,
                                    @RequestParam(value = "discount", required = false) Integer discount,
                                    @RequestParam(value = "categoryId", required = false) Integer categoryId) {
        productService.change(id, name, price, amount, description, discount, categoryId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

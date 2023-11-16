package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexandrov.backend.models.Photo;
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


    @GetMapping("/{product_id}")
    public ResponseEntity getProductById(@PathVariable("product_id") int id) {
        return ResponseEntity.ok(productService.getProductById(id).get());
    }

    @PostMapping("/new")
    public ResponseEntity createProduct(@RequestBody Product product,
                                        @RequestParam int categoryId) {
        for (Photo photo : product.getPhotos()) {
            photo.setProduct(product);
        }
        productService.save(product, categoryId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity deleteProduct(@PathVariable("product_id") int id) {
        productService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/change/{product_id}")
    public ResponseEntity change(@PathVariable("product_id") int id,
                                 @RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "price", required = false) Float price,
                                 @RequestParam(value = "amount", required = false) Integer amount,
                                 @RequestParam(value = "description", required = false) String description,
                                 @RequestParam(value = "discount", required = false) Integer discount,
                                 @RequestParam(value = "categoryId", required = false) Integer categoryId) {
        productService.change(id, name, price, amount, description, discount, categoryId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

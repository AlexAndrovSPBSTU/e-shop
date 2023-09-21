package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexandrov.backend.models.Product;
import ru.alexandrov.backend.models.Property;
import ru.alexandrov.backend.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{product_id}")
    public Product getProductById(@PathVariable("product_id") int product_id) {
        return productService.getProductById(product_id).get();
    }

    @GetMapping("/{product_id}/properties")
    public List<Property> getProductPropertiesById(@PathVariable("product_id") int product_id) {
        return productService.getProductProperties(product_id);
    }

}

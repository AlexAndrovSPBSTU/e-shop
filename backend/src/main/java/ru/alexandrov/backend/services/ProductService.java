package ru.alexandrov.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.models.Category;
import ru.alexandrov.backend.models.Product;
import ru.alexandrov.backend.repositories.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    public void save(Product product, int categoryId) {
        Category category = new Category();
        category.setId(categoryId);
        product.setCategory(category);
        productRepository.save(product);
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }

    public void change(int id, String name, Float price, Integer amount,
                       String description, Integer discount, Integer categoryId) {
        Product product = productRepository.findById(id).get();
        if (categoryId != null) {
            Category category = new Category();
            category.setId(categoryId);
            product.setCategory(category);
        }
        if (name != null) {
            product.setName(name);
        }
        if (price != null) {
            product.setPrice(price);
        }
        if (amount != null) {
            product.setAmount(amount);
        }
        if (discount != null) {
            product.setDiscount(discount);
        }
        if (description != null) {
            product.setDescription(description);
        }
        productRepository.save(product);
    }
}

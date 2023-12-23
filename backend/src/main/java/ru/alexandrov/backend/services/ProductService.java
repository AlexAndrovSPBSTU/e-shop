package ru.alexandrov.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.models.Category;
import ru.alexandrov.backend.models.Product;
import ru.alexandrov.backend.repositories.CategoryRepository;
import ru.alexandrov.backend.repositories.ProductRepository;
import ru.alexandrov.backend.repositories.PropertyRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PropertyRepository propertyRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, PropertyRepository propertyRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.propertyRepository = propertyRepository;
    }

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    public void save(Product product, int categoryId) {
        product.setCategory(categoryRepository.findById(categoryId).get());
        product.setRating(0.0);
        productRepository.save(product);
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }

    public void change(int id, String name, Double price, Integer amount,
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

    @Transactional
    public void assignProperty(int productId, int propertyId) {
        productRepository.insertProductPropertyRelation(productId, propertyId);
    }
}

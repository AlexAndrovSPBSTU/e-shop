package ru.alexandrov.backend.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.dto.ProductDTO;
import ru.alexandrov.backend.models.Category;
import ru.alexandrov.backend.models.Product;
import ru.alexandrov.backend.models.Property;
import ru.alexandrov.backend.repositories.CategoryRepository;
import ru.alexandrov.backend.repositories.CharacteristicRepository;
import ru.alexandrov.backend.repositories.ProductRepository;
import ru.alexandrov.backend.repositories.PropertyRepository;
import ru.alexandrov.backend.services.ProductService;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PropertyRepository propertyRepository;
    private final CharacteristicRepository characteristicRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, PropertyRepository propertyRepository, CharacteristicRepository characteristicRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.propertyRepository = propertyRepository;
        this.characteristicRepository = characteristicRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Optional<ProductDTO> getProductById(int id) {
        return productRepository.findById(id).map(product -> modelMapper.map(product, ProductDTO.class));
    }

    public void save(ProductDTO productDTO, int categoryId) {
        if (productDTO.getDiscount() == null) {
            productDTO.setDiscount(0);
        }
        if (productDTO.getAmount() == null) {
            productDTO.setAmount(0);
        }
        if (productDTO.getDescription() == null) {
            productDTO.setDescription("");
        }
        if (productDTO.getPrice() == null) {
            productDTO.setPrice(0.0);
        }
        productDTO.setRating(0.0);
        Product product = modelMapper.map(productDTO, Product.class);
        product.setCategory(categoryRepository.findById(categoryId).get());
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
    public void assignProperty(int productId, Integer propertyId, Integer characteristicId, String newValue) {
        if (propertyId != null) {
            productRepository.insertProductPropertyRelation(productId, propertyId);
        } else {
            Property property = new Property();
            property.setValue(newValue);
            property.setCharacteristic(characteristicRepository.findById(characteristicId).get());
            property = propertyRepository.save(property);
            productRepository.insertProductPropertyRelation(productId, property.getId());
        }
    }
}

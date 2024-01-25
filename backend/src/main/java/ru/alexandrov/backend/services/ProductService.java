package ru.alexandrov.backend.services;

import org.springframework.stereotype.Component;
import ru.alexandrov.backend.dto.ProductDTO;

import java.util.Optional;

@Component
public interface ProductService {

    Optional<ProductDTO> getProductById(int id);
    void save(ProductDTO productDTO, int categoryId);
    void delete(int id);
    void change(int id, String name, Double price, Integer amount,
            String description, Integer discount, Integer categoryId);
    void assignProperty(int productId, Integer propertyId, Integer characteristicId, String newValue);
}

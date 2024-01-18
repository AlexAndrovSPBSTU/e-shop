package ru.alexandrov.backend.services;

import org.springframework.stereotype.Component;
import ru.alexandrov.backend.dto.ProductDTO;

import java.util.Optional;

@Component
public interface ProductService {

    public Optional<ProductDTO> getProductById(int id);

    public void save(ProductDTO productDTO, int categoryId);

    public void delete(int id);

    public void change(int id, String name, Double price, Integer amount,
                       String description, Integer discount, Integer categoryId);

    public void assignProperty(int productId, Integer propertyId, Integer characteristicId, String newValue);
}

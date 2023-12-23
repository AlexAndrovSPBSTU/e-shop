package ru.alexandrov.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import ru.alexandrov.backend.models.Category;
import ru.alexandrov.backend.models.Product;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Optional<Product> findByName(String name);

    Page<Product> findAllByCategoryInAndPriceBetween(Pageable pageable, Collection<Category> category, Double from, Double to);

    List<Product> findAllByCategoryInAndPriceBetween(Sort sort, Collection<Category> category, Double from, Double to);


    @Query(value = "insert into product_property values (:productId,:propertyId)", nativeQuery = true)
    @Modifying
    void insertProductPropertyRelation(@Param("productId") int productId, @Param("propertyId") int propertyId);

}

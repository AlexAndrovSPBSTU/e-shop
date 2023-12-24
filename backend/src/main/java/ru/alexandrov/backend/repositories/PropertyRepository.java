package ru.alexandrov.backend.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.alexandrov.backend.models.Property;

import java.util.Optional;

@Repository
public interface PropertyRepository extends CrudRepository<Property, Integer> {
    @Modifying
    @Query(value = "update property set value=:newValue where property_id=:id", nativeQuery = true)
    void changeValue(@Param("id") int id, @Param("newValue") String newValue);

}

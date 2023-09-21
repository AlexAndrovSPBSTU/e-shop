package ru.alexandrov.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.alexandrov.backend.models.Property;

@Repository
public interface PropertyRepository extends CrudRepository<Property, Integer> {
}

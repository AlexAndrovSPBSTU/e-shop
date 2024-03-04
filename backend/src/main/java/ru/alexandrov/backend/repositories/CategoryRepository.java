package ru.alexandrov.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.alexandrov.backend.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

}

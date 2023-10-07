package ru.alexandrov.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.alexandrov.backend.models.Photo;

import java.util.Collection;

@Repository
public interface PhotosRepository extends CrudRepository<Photo, Integer> {
    void deleteAllByUrlIn(Collection<String> strings);
}

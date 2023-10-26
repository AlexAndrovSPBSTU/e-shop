package ru.alexandrov.backend.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.alexandrov.backend.models.Characteristic;

import java.util.Optional;

@Repository
public interface CharacteristicRepository extends CrudRepository<Characteristic, Integer> {
    @Modifying
    @Query(value = "update characteristic set name=:newName where characteristic_id=:id", nativeQuery = true)
    void rename(@Param("id") int category_id, @Param("newName") String newName);

    Optional<Characteristic> findByName(String name);
}

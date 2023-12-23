package ru.alexandrov.backend.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.alexandrov.backend.models.Characteristic;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacteristicRepository extends CrudRepository<Characteristic, Integer> {
    @Modifying
    @Query(value = "update characteristic set name=:newName where characteristic_id=:id", nativeQuery = true)
    void rename(@Param("id") int category_id, @Param("newName") String newName);

    @Query(value = "select * from characteristic where category_id=:id and is_range=:isRange", nativeQuery = true)
    List<Characteristic> getCharacteristicsByCategory(@Param("id") int id, @Param("isRange") boolean isRange);

    Optional<Characteristic> findByName(String name);
}

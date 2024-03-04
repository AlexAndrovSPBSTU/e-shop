package ru.alexandrov.backend.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.alexandrov.backend.models.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {


    @Query(value = "select * from category where category_id in (select child_id from category_parent_child where parent_id = 1)",
            nativeQuery = true)
    List<Category> getRootCategories();

    @Modifying
    @Query(value = "insert into category_parent_child values (:parentId,:childId)",
            nativeQuery = true)
    void insert(@Param("childId") int childId, @Param("parentId") int parentId);

}

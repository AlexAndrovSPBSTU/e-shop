package ru.alexandrov.backend.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.alexandrov.backend.models.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {


    @Query(value = "select * from category where category_id in (select child_id from category_parent_child where parent_id = 1)",
            nativeQuery = true)
    List<Category> getRootCategories();

    @Modifying
    @Query(value = "insert into category_parent_child values (:parentId,:childId)",
            nativeQuery = true)
    void insert(@Param("childId") int childId, @Param("parentId") int parentId);

    @Modifying
    @Query(value = "delete from category_parent_child where parent_id=:parentId and child_id=:childId",
            nativeQuery = true)
    void deleteParentChildRelation(@Param("childId") int childId, @Param("parentId") int parentId);

    @Modifying
    @Query(value = "update category set name=:newName where category_id=:id",
            nativeQuery = true)
    void rename(@Param("id") int categoryId, @Param("newName") String newName);

    Optional<Category> findByName(String name);
}

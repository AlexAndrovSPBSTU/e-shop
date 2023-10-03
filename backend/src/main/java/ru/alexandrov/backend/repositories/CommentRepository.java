package ru.alexandrov.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.alexandrov.backend.models.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

}

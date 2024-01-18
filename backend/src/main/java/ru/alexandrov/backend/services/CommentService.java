package ru.alexandrov.backend.services;

import org.springframework.stereotype.Component;
import ru.alexandrov.backend.dto.CommentDTO;

@Component
public interface CommentService {


    void save(CommentDTO commentDTO, int productId);

    void deleteComment(int id);
}

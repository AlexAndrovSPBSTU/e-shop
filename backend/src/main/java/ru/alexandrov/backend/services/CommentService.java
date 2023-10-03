package ru.alexandrov.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.models.Comment;
import ru.alexandrov.backend.repositories.CommentRepository;

import java.util.Date;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void save(Comment comment) {
        comment.setDate(new Date());
//         TODO finish when jwt is presented
//         comment.setCustomer();
        commentRepository.save(comment);
    }
}

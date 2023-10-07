package ru.alexandrov.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.models.Comment;
import ru.alexandrov.backend.repositories.CommentRepository;
import ru.alexandrov.backend.repositories.PhotosRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PhotosRepository photosRepository;


    @Autowired
    public CommentService(CommentRepository commentRepository, PhotosRepository photosRepository) {
        this.commentRepository = commentRepository;
        this.photosRepository = photosRepository;
    }

    public void save(Comment comment) {
        comment.setDate(new Date());
//         TODO finish when jwt is presented
//         comment.setCustomer();
        commentRepository.save(comment);
    }

    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }

    public void deleteCommentPhotos(String[] urls) {
        photosRepository.deleteAllByUrlIn(Arrays.asList(urls));
    }
}

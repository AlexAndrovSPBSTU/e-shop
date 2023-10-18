package ru.alexandrov.backend.services;

import org.hibernate.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.models.Photo;
import ru.alexandrov.backend.repositories.CommentRepository;
import ru.alexandrov.backend.repositories.PhotoRepository;
import ru.alexandrov.backend.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
public class PhotosService {
    private final PhotoRepository photoRepository;
    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public PhotosService(PhotoRepository photoRepository, ProductRepository productRepository, CommentRepository commentRepository) {
        this.photoRepository = photoRepository;
        this.productRepository = productRepository;
        this.commentRepository = commentRepository;
    }

    public void delete(String url) {
        photoRepository.deleteById(url);
    }

    @Transactional
    public void save(Photo photo, int id, String type) {
        if (type.equals("product")) {
            photo.setProduct(productRepository.findById(id).get());
        } else if (type.equals("comment")) {
            photo.setComment(commentRepository.findById(id).get());
        } else {
            throw new TypeMismatchException("Wrong type!");
        }
        photoRepository.save(photo);
    }
}

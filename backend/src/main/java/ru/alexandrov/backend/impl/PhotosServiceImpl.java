package ru.alexandrov.backend.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.models.Photos;
import ru.alexandrov.backend.repositories.CommentRepository;
import ru.alexandrov.backend.repositories.PhotoRepository;
import ru.alexandrov.backend.repositories.ProductRepository;
import ru.alexandrov.backend.services.PhotosService;

import javax.transaction.Transactional;

@Service
public class PhotosServiceImpl implements PhotosService {
    private final PhotoRepository photoRepository;
    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public PhotosServiceImpl(PhotoRepository photoRepository, ProductRepository productRepository, CommentRepository commentRepository) {
        this.photoRepository = photoRepository;
        this.productRepository = productRepository;
        this.commentRepository = commentRepository;
    }

    public void delete(String[] urls) {
        for (String url : urls) {
            photoRepository.deleteById(url);
        }
    }

    @Transactional
    public void save(Photos photos, Integer productId, Integer commentId) {
        if (productId != null) {
            photos.getPhotos().forEach(photo -> photo.setProduct(productRepository.findById(productId).get()));
        } else {
            photos.getPhotos().forEach(photo -> photo.setComment(commentRepository.findById(commentId).get()));
        }
        photoRepository.saveAll(photos.getPhotos());
    }
}

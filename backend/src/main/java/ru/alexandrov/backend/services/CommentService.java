package ru.alexandrov.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.models.Comment;
import ru.alexandrov.backend.models.Product;
import ru.alexandrov.backend.repositories.CommentRepository;
import ru.alexandrov.backend.repositories.CustomerRepository;
import ru.alexandrov.backend.repositories.PhotoRepository;
import ru.alexandrov.backend.repositories.ProductRepository;

import java.util.Date;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.commentRepository = commentRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public void save(Comment comment, int productId) {
        Product product = productRepository.findById(productId).get();
        comment.setDate(new Date());
        comment.setProduct(product);
        comment.setCustomer(customerRepository.findByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).get());
        commentRepository.save(comment);
        product.updateRating();
        productRepository.save(product);
    }

    public void deleteComment(int id) {
        int productId = commentRepository.findById(id).get().getProduct().getId();
        commentRepository.deleteById(id);
        Product product = productRepository.findById(productId).get();
        product.updateRating();
        productRepository.save(product);
    }


}

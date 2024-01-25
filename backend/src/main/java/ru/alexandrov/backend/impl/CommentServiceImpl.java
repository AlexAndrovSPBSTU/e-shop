package ru.alexandrov.backend.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.dto.CommentDTO;
import ru.alexandrov.backend.models.Comment;
import ru.alexandrov.backend.models.Product;
import ru.alexandrov.backend.repositories.CommentRepository;
import ru.alexandrov.backend.repositories.CustomerRepository;
import ru.alexandrov.backend.repositories.ProductRepository;
import ru.alexandrov.backend.services.CommentService;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ProductRepository productRepository, CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    public void save(CommentDTO commentDTO, int productId) {
        Product product = productRepository.findById(productId).get();
        Comment comment = modelMapper.map(commentDTO, Comment.class);
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

package ru.alexandrov.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexandrov.backend.models.Comment;
import ru.alexandrov.backend.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    private final CommentService commentService;

    @Autowired
    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> createComment(@RequestBody Comment comment) {
        commentService.save(comment);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
